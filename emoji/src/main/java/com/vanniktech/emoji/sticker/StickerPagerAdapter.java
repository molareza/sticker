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

    StickerPagerAdapter(Activity context, int backgroundColor, int iconColor, int dividerColor, ArrayList<StructSticker> stickerList, OnPageChangeMainViewPager onChangeViewPager, OnStickerListener onStickerListener) {
        this.context = context;
        this.backgroundColor = backgroundColor;
        this.iconColor = iconColor;
        this.dividerColor = dividerColor;
        this.stickerList = stickerList;
        this.onChangeViewPager = onChangeViewPager;
        this.onStickerListener= onStickerListener;
    }

    @Override
    public int getCount() {
        return stickerList.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup pager, final int position) {
        final View newView;

        if (position == RECENT_POSITION){
            newView = new RecentStickerGridView(pager.getContext()).init();
        }else {
            newView = new StickerGridView(pager.getContext()).init(stickerList.get(position).getPath() ,onStickerListener);
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
