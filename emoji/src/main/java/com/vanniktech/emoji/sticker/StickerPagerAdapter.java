package com.vanniktech.emoji.sticker;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.vanniktech.emoji.OnPageChangeMainViewPager;
import com.vanniktech.emoji.listeners.OnStickerListener;
import com.vanniktech.emoji.sticker.struct.StructAllSticker;
import com.vanniktech.emoji.sticker.struct.StructStickerItemGroup;

import java.util.ArrayList;

public final class StickerPagerAdapter extends PagerAdapter {
    private static final int RECENT_POSITION = 0;
    private Activity context;
    private int backgroundColor;
    private int iconColor;
    private int dividerColor;
    private ArrayList<StructAllSticker> stickerList;
    private ArrayList<StructRecentSticker> recentStickerList;
    private OnPageChangeMainViewPager onChangeViewPager;
    private OnStickerListener onStickerListener;
    private RecentStickerGridView recentStickerGridView;
    private StickerGridView stickerGridView;

    StickerPagerAdapter(Activity context, int backgroundColor, int iconColor, int dividerColor, ArrayList<StructAllSticker> stickerList, OnPageChangeMainViewPager onChangeViewPager, OnStickerListener onStickerListener, ArrayList<StructRecentSticker> recentSticker) {
        this.context = context;
        this.backgroundColor = backgroundColor;
        this.iconColor = iconColor;
        this.dividerColor = dividerColor;
        this.stickerList = stickerList;
        this.onChangeViewPager = onChangeViewPager;
        this.onStickerListener = onStickerListener;
        this.recentStickerList = recentSticker;
        this.recentStickerGridView = null;
    }

    @Override
    public int getCount() {
        return stickerList.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup pager, final int position) {
        final View newView;

        Log.i("CCCCCDD", "instantiateItem: " + position);
        if (position == RECENT_POSITION) {
            newView = new RecentStickerGridView(pager.getContext()).init(onStickerListener, recentStickerList);
            recentStickerGridView = (RecentStickerGridView) newView;
        } else {
            newView = new StickerGridView(pager.getContext()).init(stickerList.get(position), onStickerListener);
            stickerGridView = (StickerGridView) newView;
        }

        pager.addView(newView);
        return newView;
    }

    @Override
    public void destroyItem(final ViewGroup pager, final int position, final Object view) {
        pager.removeView((View) view);

        if (position == RECENT_POSITION) {
            recentStickerGridView = null;
        }
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    void invalidateRecentStickers(ArrayList<StructRecentSticker> recentlySticker) {
        if (recentStickerGridView != null) {
            recentStickerGridView.invalidateStrickers(recentlySticker);
        }
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void updateStickerAdapter(ArrayList<StructAllSticker> categoryStickerList) {

        this.stickerList = categoryStickerList;
        notifyDataSetChanged();

    }


}