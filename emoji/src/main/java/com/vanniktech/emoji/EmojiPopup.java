package com.vanniktech.emoji;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;

import com.vanniktech.emoji.emoji.Emoji;
import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener;
import com.vanniktech.emoji.listeners.OnEmojiClickListener;
import com.vanniktech.emoji.listeners.OnEmojiLongClickListener;
import com.vanniktech.emoji.listeners.OnEmojiPopupDismissListener;
import com.vanniktech.emoji.listeners.OnEmojiPopupShownListener;
import com.vanniktech.emoji.listeners.OnOpenPageStickerListener;
import com.vanniktech.emoji.listeners.OnSoftKeyboardCloseListener;
import com.vanniktech.emoji.listeners.OnSoftKeyboardOpenListener;
import com.vanniktech.emoji.listeners.OnStickerListener;
import com.vanniktech.emoji.listeners.OnUpdateStickerListener;
import com.vanniktech.emoji.sticker.struct.StructGroupSticker;

import java.util.ArrayList;

import static com.vanniktech.emoji.Utils.checkNotNull;

public final class EmojiPopup {
    static final int MIN_KEYBOARD_HEIGHT = 100;

    final View rootView;
    private Activity context;
    private int backgroundColor, iconColor, dividerColor;

    @NonNull
    final RecentEmoji recentEmoji;
    @NonNull
    final VariantEmoji variantEmoji;
    @NonNull
    final EmojiVariantPopup variantPopup;

    final PopupWindow popupWindow;
    final EmojiEditTextInterface emojiEditText;

    boolean isPendingOpen;
    boolean isKeyboardOpen;

    private MainEmojiView mainEmojiView;

    @Nullable
    OnEmojiPopupShownListener onEmojiPopupShownListener;
    @Nullable
    OnSoftKeyboardCloseListener onSoftKeyboardCloseListener;
    @Nullable
    OnSoftKeyboardOpenListener onSoftKeyboardOpenListener;

    @Nullable
    OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;
    @Nullable
    OnEmojiClickListener onEmojiClickListener;
    @Nullable
    OnEmojiPopupDismissListener onEmojiPopupDismissListener;

    @Nullable
    OnStickerListener onStickerListener;

    @Nullable
    OnUpdateStickerListener onUpdateStickerListener;

    @Nullable
    OnOpenPageStickerListener onOpenPageStickerListener;

    final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            final Rect rect = Utils.windowVisibleDisplayFrame(context);
            final int heightDifference = Utils.screenHeight(context) - rect.bottom;

            if (heightDifference > Utils.dpToPx(context, MIN_KEYBOARD_HEIGHT)) {
                popupWindow.setHeight(heightDifference);
                popupWindow.setWidth(rect.right);

                if (!isKeyboardOpen && onSoftKeyboardOpenListener != null) {
                    onSoftKeyboardOpenListener.onKeyboardOpen(heightDifference);
                }

                isKeyboardOpen = true;

                if (isPendingOpen) {
                    showAtBottom();
                    isPendingOpen = false;
                }
            } else {
                if (isKeyboardOpen) {
                    isKeyboardOpen = false;

                    if (onSoftKeyboardCloseListener != null) {
                        onSoftKeyboardCloseListener.onKeyboardClose();
                    }

                    dismiss();
                    Utils.removeOnGlobalLayoutListener(context.getWindow().getDecorView(), onGlobalLayoutListener);
                }
            }
        }
    };

    public EmojiPopup(@NonNull final View rootView, @NonNull final EmojiEditTextInterface emojiEditText,
                      @Nullable final RecentEmoji recent, @Nullable final VariantEmoji variant, int backgroundColor, int iconColor, int dividerColor, @Nullable OnStickerListener onStickerListener, OnUpdateStickerListener onUpdateStickerListener, OnOpenPageStickerListener onOpenPageStickerListener) {
        this.context = Utils.asActivity(rootView.getContext());
        this.rootView = rootView.getRootView();
        this.emojiEditText = emojiEditText;
        this.recentEmoji = recent != null ? recent : new RecentEmojiManager(context);
        this.variantEmoji = variant != null ? variant : new VariantEmojiManager(context);
        this.backgroundColor = backgroundColor;
        this.iconColor = iconColor;
        this.dividerColor = dividerColor;
        this.onStickerListener = onStickerListener;
        this.onUpdateStickerListener = onUpdateStickerListener;
        this.onOpenPageStickerListener = onOpenPageStickerListener;

        popupWindow = new PopupWindow(context);

        final OnEmojiLongClickListener longClickListener = new OnEmojiLongClickListener() {
            @Override
            public void onEmojiLongClick(@NonNull final EmojiImageView view, @NonNull final Emoji emoji) {
                variantPopup.show(view, emoji);
            }
        };

        final OnEmojiClickListener clickListener = new OnEmojiClickListener() {
            @Override
            public void onEmojiClick(@NonNull final EmojiImageView imageView, @NonNull final Emoji emoji) {
                emojiEditText.input(emoji);

                recentEmoji.addEmoji(emoji);
                variantEmoji.addVariant(emoji);
                imageView.updateEmoji(emoji);

                if (onEmojiClickListener != null) {
                    onEmojiClickListener.onEmojiClick(imageView, emoji);
                }

                variantPopup.dismiss();
            }
        };

        variantPopup = new EmojiVariantPopup(this.rootView, clickListener);

        OnEmojiBackspaceClickListener onEmojiBackspaceClickListener = new OnEmojiBackspaceClickListener() {
            @Override
            public void onEmojiBackspaceClick(final View v) {
                emojiEditText.backspace();

                if (EmojiPopup.this.onEmojiBackspaceClickListener != null) {
                    EmojiPopup.this.onEmojiBackspaceClickListener.onEmojiBackspaceClick(v);
                }
            }
        };
        mainEmojiView = new MainEmojiView(context, clickListener, longClickListener, recentEmoji, variantEmoji, backgroundColor, iconColor, onEmojiBackspaceClickListener, dividerColor, onStickerListener, onUpdateStickerListener, onOpenPageStickerListener);

        popupWindow.setContentView(mainEmojiView);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null)); // To avoid borders and overdraw.
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (onEmojiPopupDismissListener != null) {
                    onEmojiPopupDismissListener.onEmojiPopupDismiss();
                }
            }
        });
    }

    public void toggle() {
        if (!popupWindow.isShowing()) {
            // Remove any previous listeners to avoid duplicates.
            Utils.removeOnGlobalLayoutListener(context.getWindow().getDecorView(), onGlobalLayoutListener);
            context.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);

            if (isKeyboardOpen) {
                // If the keyboard is visible, simply show the emoji popup.
                showAtBottom();
            } else if (emojiEditText instanceof View) {
                final View view = (View) emojiEditText;

                // Open the text keyboard first and immediately after that show the emoji popup.
                view.setFocusableInTouchMode(true);
                view.requestFocus();

                showAtBottomPending();

                final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            } else {
                throw new IllegalArgumentException("The provided editInterace isn't a View instance.");
            }
        } else {
            dismiss();
        }

        // Manually dispatch the event. In some cases this does not work out of the box reliably.
        context.getWindow().getDecorView().getViewTreeObserver().dispatchOnGlobalLayout();
    }

    public boolean isShowing() {
        return popupWindow.isShowing();
    }

    public void dismiss() {
        popupWindow.dismiss();
        variantPopup.dismiss();
        recentEmoji.persist();
        variantEmoji.persist();
    }

    void showAtBottom() {
        final Point desiredLocation = new Point(0, Utils.screenHeight(context) - popupWindow.getHeight());

        popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, desiredLocation.x, desiredLocation.y);
        Utils.fixPopupLocation(popupWindow, desiredLocation);

        if (onEmojiPopupShownListener != null) {
            onEmojiPopupShownListener.onEmojiPopupShown();
        }
    }

    private void showAtBottomPending() {
        if (isKeyboardOpen) {
            showAtBottom();
        } else {
            isPendingOpen = true;
        }
    }

    public static final class Builder {
        @NonNull
        private final View rootView;
        private int backgroundColor;
        private int iconColor;
        private int dividerColor;
        @Nullable
        private OnEmojiPopupShownListener onEmojiPopupShownListener;
        @Nullable
        private OnSoftKeyboardCloseListener onSoftKeyboardCloseListener;
        @Nullable
        private OnSoftKeyboardOpenListener onSoftKeyboardOpenListener;
        @Nullable
        private OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;
        @Nullable
        private OnEmojiClickListener onEmojiClickListener;
        @Nullable
        private OnEmojiPopupDismissListener onEmojiPopupDismissListener;

        @Nullable
        private OnStickerListener onStickerListener;
        @Nullable
        private OnUpdateStickerListener onUpdateStickerListener;

        @Nullable
        private OnOpenPageStickerListener onOpenPageStickerListener;
        @Nullable
        private RecentEmoji recentEmoji;
        @Nullable
        private VariantEmoji variantEmoji;

        private Builder(final View rootView) {
            this.rootView = checkNotNull(rootView, "The root View can't be null");
        }

        /**
         * @param rootView The root View of your layout.xml which will be used for calculating the height
         *                 of the keyboard.
         * @return builder For building the {@link EmojiPopup}.
         */
        @CheckResult
        public static Builder fromRootView(final View rootView) {
            return new Builder(rootView);
        }

        @CheckResult
        public Builder setOnSoftKeyboardCloseListener(@Nullable final OnSoftKeyboardCloseListener listener) {
            onSoftKeyboardCloseListener = listener;
            return this;
        }

        @CheckResult
        public Builder setOnEmojiClickListener(@Nullable final OnEmojiClickListener listener) {
            onEmojiClickListener = listener;
            return this;
        }

        @CheckResult
        public Builder setOnSoftKeyboardOpenListener(@Nullable final OnSoftKeyboardOpenListener listener) {
            onSoftKeyboardOpenListener = listener;
            return this;
        }

        @CheckResult
        public Builder setOnEmojiPopupShownListener(@Nullable final OnEmojiPopupShownListener listener) {
            onEmojiPopupShownListener = listener;
            return this;
        }

        @CheckResult
        public Builder setOnEmojiPopupDismissListener(@Nullable final OnEmojiPopupDismissListener listener) {
            onEmojiPopupDismissListener = listener;
            return this;
        }

        @CheckResult
        public Builder setOnStickerListener(@Nullable final OnStickerListener listener) {
            onStickerListener = listener;
            return this;
        }

        @CheckResult
        public Builder setOnUpdateSticker(@Nullable final OnUpdateStickerListener listener) {
            onUpdateStickerListener = listener;
            return this;
        }

        @CheckResult
        public Builder setOpenPageSticker(@Nullable final OnOpenPageStickerListener listener) {
            onOpenPageStickerListener = listener;
            return this;
        }

        @CheckResult
        public Builder setOnEmojiBackspaceClickListener(@Nullable final OnEmojiBackspaceClickListener listener) {
            onEmojiBackspaceClickListener = listener;
            return this;
        }

        /**
         * Allows you to pass your own implementation of recent emojis. If not provided the default one
         * {@link RecentEmojiManager} will be used.
         *
         * @since 0.2.0
         */
        @CheckResult
        public Builder setRecentEmoji(@Nullable final RecentEmoji recent) {
            recentEmoji = recent;
            return this;
        }

        /**
         * Allows you to pass your own implementation of variant emojis. If not provided the default one
         * {@link VariantEmojiManager} will be used.
         *
         * @since 0.5.0
         */
        @CheckResult
        public Builder setVariantEmoji(@Nullable final VariantEmoji variant) {
            variantEmoji = variant;
            return this;
        }

        @CheckResult
        public Builder setBackgroundColor(final int backgroundColor) {
            this.backgroundColor = backgroundColor;

            return this;
        }

        @CheckResult
        public Builder setIconColor(final int iconColor) {
            this.iconColor = iconColor;
            return this;
        }

        @CheckResult
        public Builder setDividerColor(final int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        @CheckResult
        public EmojiPopup build(@NonNull final EmojiEditTextInterface emojiEditText) {
            EmojiManager.getInstance().verifyInstalled();
            checkNotNull(emojiEditText, "EmojiEditText can't be null");
            final EmojiPopup emojiPopup = new EmojiPopup(rootView, emojiEditText, recentEmoji, variantEmoji, backgroundColor, iconColor, dividerColor, onStickerListener, onUpdateStickerListener, onOpenPageStickerListener);
            emojiPopup.onSoftKeyboardCloseListener = onSoftKeyboardCloseListener;
            emojiPopup.onEmojiClickListener = onEmojiClickListener;
            emojiPopup.onSoftKeyboardOpenListener = onSoftKeyboardOpenListener;
            emojiPopup.onEmojiPopupShownListener = onEmojiPopupShownListener;
            emojiPopup.onEmojiPopupDismissListener = onEmojiPopupDismissListener;
//            emojiPopup.onStickerListener = onStickerListener;
            emojiPopup.onEmojiBackspaceClickListener = onEmojiBackspaceClickListener;
            emojiPopup.onStickerListener = onStickerListener;
            emojiPopup.onOpenPageStickerListener = onOpenPageStickerListener;
            return emojiPopup;
        }
    }

    public void updateStickerAdapter(ArrayList<StructGroupSticker> structAllStickers) {
        mainEmojiView.updateSticker(structAllStickers);

    }
}
