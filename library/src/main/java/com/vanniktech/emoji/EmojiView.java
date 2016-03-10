package com.vanniktech.emoji;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vanniktech.emoji.emoji.Nature;
import com.vanniktech.emoji.emoji.Objects;
import com.vanniktech.emoji.emoji.People;
import com.vanniktech.emoji.emoji.Places;
import com.vanniktech.emoji.emoji.Symbols;
import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener;
import com.vanniktech.emoji.listeners.OnEmojiClickedListener;
import com.vanniktech.emoji.listeners.RepeatListener;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressLint("ViewConstructor")
final class EmojiView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final int                        PEOPLE_INDEX              = 0;
    private static final int                        NATURE_INDEX              = 1;
    private static final int                        OBJECTS_INDEX             = 2;
    private static final int                        CARS_INDEX                = 3;
    private static final int                        PUNCTUATION_INDEX         = 4;

    private static final long                       INITIAL_INTERVAL          = TimeUnit.SECONDS.toMillis(1) / 2;
    private static final int                        NORMAL_INTERVAL           = 50;

    @ColorInt private final int                     themeAccentColor;

    @Nullable private OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

    private int                                     emojiTabLastSelectedIndex = -1;
    private final ImageView[]                       emojiTabs;

    EmojiView(final Context context, final OnEmojiClickedListener onEmojiClickedListener) {
        super(context);

        View.inflate(context, R.layout.emoji_view, this);

        final ViewPager emojisPager = (ViewPager) findViewById(R.id.emojis_pager);
        emojisPager.addOnPageChangeListener(this);

        final List<EmojiGridView> views = getViews(context, onEmojiClickedListener);
        final EmojiPagerAdapter emojisAdapter = new EmojiPagerAdapter(views);
        emojisPager.setAdapter(emojisAdapter);

        emojiTabs = new ImageView[PUNCTUATION_INDEX + 1];
        emojiTabs[PEOPLE_INDEX] = (ImageView) findViewById(R.id.emojis_tab_0_people);
        emojiTabs[NATURE_INDEX] = (ImageView) findViewById(R.id.emojis_tab_1_nature);
        emojiTabs[OBJECTS_INDEX] = (ImageView) findViewById(R.id.emojis_tab_2_objects);
        emojiTabs[CARS_INDEX] = (ImageView) findViewById(R.id.emojis_tab_3_cars);
        emojiTabs[PUNCTUATION_INDEX] = (ImageView) findViewById(R.id.emojis_tab_4_punctuation);

        handleOnClicks(emojisPager);

        findViewById(R.id.emojis_backspace).setOnTouchListener(new RepeatListener(INITIAL_INTERVAL, NORMAL_INTERVAL, new OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (onEmojiBackspaceClickListener != null) {
                    onEmojiBackspaceClickListener.onEmojiBackspaceClicked(view);
                }
            }
        }));

        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        themeAccentColor = value.data;

        emojisPager.setCurrentItem(PEOPLE_INDEX);
        onPageSelected(PEOPLE_INDEX);
    }

    @SuppressFBWarnings(value = "SIC_INNER_SHOULD_BE_STATIC_ANON", justification = "Do not care in this one")
    private void handleOnClicks(final ViewPager emojisPager) {
        for (int i = 0; i < emojiTabs.length; i++) {
            final int position = i;
            emojiTabs[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    emojisPager.setCurrentItem(position);
                }
            });
        }
    }

    public void setOnEmojiBackspaceClickListener(@Nullable final OnEmojiBackspaceClickListener onEmojiBackspaceClickListener) {
        this.onEmojiBackspaceClickListener = onEmojiBackspaceClickListener;
    }

    @NonNull
    private List<EmojiGridView> getViews(final Context context, @Nullable final OnEmojiClickedListener onEmojiClickedListener) {
        final EmojiGridView peopleGridView = new EmojiGridView(context).init(People.DATA, onEmojiClickedListener);
        final EmojiGridView natureGridView = new EmojiGridView(context).init(Nature.DATA, onEmojiClickedListener);
        final EmojiGridView objectsGridView = new EmojiGridView(context).init(Objects.DATA, onEmojiClickedListener);
        final EmojiGridView lacesGridView = new EmojiGridView(context).init(Places.DATA, onEmojiClickedListener);
        final EmojiGridView symbolsGridView = new EmojiGridView(context).init(Symbols.DATA, onEmojiClickedListener);
        return Arrays.asList(peopleGridView, natureGridView, objectsGridView, lacesGridView, symbolsGridView);
    }

    @Override
    public void onPageSelected(final int i) {
        if (emojiTabLastSelectedIndex != i) {
            switch (i) {
                case PEOPLE_INDEX:
                case NATURE_INDEX:
                case OBJECTS_INDEX:
                case CARS_INDEX:
                case PUNCTUATION_INDEX:
                    if (emojiTabLastSelectedIndex >= 0 && emojiTabLastSelectedIndex < emojiTabs.length) {
                        emojiTabs[emojiTabLastSelectedIndex].setSelected(false);
                        emojiTabs[emojiTabLastSelectedIndex].clearColorFilter();
                    }

                    emojiTabs[i].setSelected(true);
                    emojiTabs[i].setColorFilter(themeAccentColor, PorterDuff.Mode.SRC_IN);

                    emojiTabLastSelectedIndex = i;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onPageScrolled(final int i, final float v, final int i2) {
        // Don't care
    }

    @Override
    public void onPageScrollStateChanged(final int i) {
        // Don't care
    }
}
