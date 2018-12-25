package com.vanniktech.emoji.sticker;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.vanniktech.emoji.OnPageChangeMainViewPager;
import com.vanniktech.emoji.listeners.OnStickerListener;

import java.util.ArrayList;

public final class StickerPagerAdapter extends PagerAdapter {
    private static final int RECENT_POSITION = 0;
    private Activity context;
    private int backgroundColor;
    private int iconColor;
    private int dividerColor;
    private ArrayList<StructSticker> stickerList;
    private OnPageChangeMainViewPager onChangeViewPager;
    private OnStickerListener onStickerListener;
    private RecentSticker recentSticker;
    private RecentStickerGridView recentStickerGridView;

    StickerPagerAdapter(Activity context, int backgroundColor, int iconColor, int dividerColor, ArrayList<StructSticker> stickerList, OnPageChangeMainViewPager onChangeViewPager, OnStickerListener onStickerListener, RecentSticker recentSticker) {
        this.context = context;
        this.backgroundColor = backgroundColor;
        this.iconColor = iconColor;
        this.dividerColor = dividerColor;
        this.stickerList = stickerList;
        this.onChangeViewPager = onChangeViewPager;
        this.onStickerListener = onStickerListener;
        this.recentSticker = recentSticker;
        this.recentStickerGridView = null;

    }

    @Override
    public int getCount() {
        return stickerList.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup pager, final int position) {
        final View newView;

        if (position == RECENT_POSITION) {
            newView = new RecentStickerGridView(pager.getContext()).init(onStickerListener , recentSticker);
            recentStickerGridView = (RecentStickerGridView) newView;
        } else {
            newView = new StickerGridView(pager.getContext()).init(stickerList.get(position).getPath(), onStickerListener , recentSticker);
        }

        pager.addView(newView);
        return newView;
    }

    @Override public void destroyItem(final ViewGroup pager, final int position, final Object view) {
        pager.removeView((View) view);

        if (position == RECENT_POSITION) {
            recentStickerGridView = null;
        }
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    int numberOfRecentEmojis() {
        return recentSticker.getRecentSticker().size();
    }

    void invalidateRecentStickers() {
        if (recentStickerGridView != null) {
            recentStickerGridView.invalidateStrickers();
        }
    }

}
