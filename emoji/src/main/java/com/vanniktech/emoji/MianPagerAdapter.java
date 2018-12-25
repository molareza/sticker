package com.vanniktech.emoji;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.vanniktech.emoji.listeners.OnEmojiClickListener;
import com.vanniktech.emoji.listeners.OnEmojiLongClickListener;
import com.vanniktech.emoji.sticker.StickerEmojiView;
import com.vanniktech.emoji.sticker.StructSticker;

import java.util.ArrayList;

public final class MianPagerAdapter extends PagerAdapter {
    private static final int RECENT_POSITION = 0;

    private RecentEmojiGridView recentEmojiGridView;
    private Activity context;
    private OnEmojiClickListener clickListener;
    private OnEmojiLongClickListener longClickListener;
    private RecentEmoji recentEmoji;
    private VariantEmoji variantEmoji;
    private int backgroundColor;
    private int iconColor;
    private int dividerColor;
    private OnPageChangeMainViewPager onChangeViewPager;
    private ArrayList<StructSticker> stickerList;

    MianPagerAdapter(Activity context, OnEmojiClickListener clickListener, OnEmojiLongClickListener longClickListener, RecentEmoji recentEmoji, VariantEmoji variantEmoji, int backgroundColor, int iconColor, int dividerColor, OnPageChangeMainViewPager onChangeViewPager, ArrayList<StructSticker> stickerList) {

        this.context = context;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
        this.recentEmoji = recentEmoji;
        this.variantEmoji = variantEmoji;
        this.backgroundColor = backgroundColor;
        this.iconColor = iconColor;
        this.dividerColor = dividerColor;
        this.onChangeViewPager = onChangeViewPager;
        this.stickerList = stickerList;

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(final ViewGroup pager, final int position) {
        final View newView;

        if (position == RECENT_POSITION) {
            newView = new EmojiView(context, clickListener, longClickListener, recentEmoji, variantEmoji, backgroundColor, iconColor, dividerColor, onChangeViewPager);
        } else {
            newView = new StickerEmojiView(context, backgroundColor, iconColor, dividerColor, onChangeViewPager ,stickerList);
        }

        pager.addView(newView);
        return newView;
    }

    @Override
    public void destroyItem(final ViewGroup pager, final int position, final Object view) {
        pager.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }
}
