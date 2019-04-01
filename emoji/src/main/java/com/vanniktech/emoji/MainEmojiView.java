package com.vanniktech.emoji;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener;
import com.vanniktech.emoji.listeners.OnEmojiClickListener;
import com.vanniktech.emoji.listeners.OnEmojiLongClickListener;
import com.vanniktech.emoji.sticker.listener.OnOpenPageStickerListener;
import com.vanniktech.emoji.sticker.listener.OnPageChangeMainViewPager;
import com.vanniktech.emoji.sticker.listener.OnStickerListener;
import com.vanniktech.emoji.sticker.listener.OnUpdateStickerListener;

import java.util.ArrayList;



@SuppressLint("ViewConstructor")
final class MainEmojiView extends LinearLayout implements ViewPager.OnPageChangeListener {
    private final MainPagerAdapter emojiPagerAdapter;
    private ViewPager emojisPager;
    private OnPageChangeMainViewPager onChangeViewPager;


    @Nullable
    OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

    MainEmojiView(final Activity context, final OnEmojiClickListener onEmojiClickListener,
                  final OnEmojiLongClickListener onEmojiLongClickListener, @NonNull final RecentEmoji recentEmoji,
                  @NonNull final VariantEmoji variantManager, int backgroundColor, int iconColor, OnEmojiBackspaceClickListener onEmojiBackspaceClickListener, int dividerColor, OnStickerListener onStickerListener, OnUpdateStickerListener onUpdateStickerListener, OnOpenPageStickerListener onOpenPageStickerListener, ViewPager.PageTransformer pageTransformer) {
        super(context);

        View.inflate(context, R.layout.emoji_main_view_pager, this);

        setOrientation(VERTICAL);
        if (backgroundColor != 0)
            setBackgroundColor(backgroundColor);
        else
            setBackgroundColor(ContextCompat.getColor(context, R.color.emoji_background));


        emojisPager = findViewById(R.id.main_pager);

        emojisPager.addOnPageChangeListener(this);
        onChangeViewPager = new OnPageChangeMainViewPager() {
            @Override
            public void changePage() {

                if (emojisPager.getCurrentItem() == 0) {
                    emojisPager.setCurrentItem(1);
                } else {
                    emojisPager.setCurrentItem(0);
                }

            }
        };

        emojisPager.setOffscreenPageLimit(0);
        emojiPagerAdapter = new MainPagerAdapter(context, onEmojiClickListener, onEmojiLongClickListener, recentEmoji, variantManager, backgroundColor, iconColor, dividerColor, onEmojiBackspaceClickListener, onChangeViewPager, onStickerListener , onUpdateStickerListener ,onOpenPageStickerListener ,pageTransformer);

        emojisPager.setAdapter(emojiPagerAdapter);
        emojisPager.setCurrentItem(0);
        onPageSelected(0);


    }

    public void setOnEmojiBackspaceClickListener(@Nullable final OnEmojiBackspaceClickListener onEmojiBackspaceClickListener) {
        this.onEmojiBackspaceClickListener = onEmojiBackspaceClickListener;
    }

    @Override
    public void onPageSelected(final int i) {
    }

    @Override
    public void onPageScrolled(final int i, final float v, final int i2) {
        // No-op.
    }

    @Override
    public void onPageScrollStateChanged(final int i) {
        // No-op.
    }

    public <T> void updateSticker(T structAllStickers){
        emojiPagerAdapter.updateSticker(structAllStickers);
    }

    public void onUpdateRecentSticker(ArrayList<String> idStickerList) {
        emojiPagerAdapter.onUpdateRecentSticker(idStickerList);
    }

}
