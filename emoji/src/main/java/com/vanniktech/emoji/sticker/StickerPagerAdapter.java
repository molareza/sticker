package com.vanniktech.emoji.sticker;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.vanniktech.emoji.sticker.struct.StructGroupSticker;
import com.vanniktech.emoji.sticker.struct.StructItemSticker;

import java.util.ArrayList;


public final class StickerPagerAdapter extends PagerAdapter {
    private static final int RECENT_POSITION = 0;
    private Activity context;
    private int backgroundColor;
    private int iconColor;
    private int dividerColor;
    private ArrayList<StructGroupSticker> stickerList;
    private ArrayList<StructItemSticker> recentStickerList;
    private OnPageChangeMainViewPager onChangeViewPager;
    private OnStickerListener onStickerListener;
    private RecentStickerGridView recentStickerGridView;
    private StickerGridView stickerGridView;
    private OnUpdateStickerListener onUpdateStickerListener;

    StickerPagerAdapter(Activity context, int backgroundColor, int iconColor, int dividerColor, ArrayList<StructGroupSticker> stickerList, OnPageChangeMainViewPager onChangeViewPager, OnStickerListener onStickerListener, ArrayList<StructItemSticker> recentSticker, OnUpdateStickerListener onUpdateStickerListener) {
        this.context = context;
        this.backgroundColor = backgroundColor;
        this.iconColor = iconColor;
        this.dividerColor = dividerColor;
        this.stickerList = stickerList;
        this.onChangeViewPager = onChangeViewPager;
        this.onStickerListener = onStickerListener;
        this.recentStickerList = recentSticker;
        this.recentStickerGridView = null;
        this.onUpdateStickerListener = onUpdateStickerListener;
    }

    @Override
    public int getCount() {
        return stickerList.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup pager, final int position) {
        final View newView;

        if (position == RECENT_POSITION) {
            newView = new RecentStickerGridView(pager.getContext()).init(onStickerListener, recentStickerList);
            recentStickerGridView = (RecentStickerGridView) newView;
        } else {
            newView = new StickerGridView(pager.getContext()).init(stickerList.get(position), onStickerListener , onUpdateStickerListener);
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

    void invalidateRecentStickers(ArrayList<StructItemSticker> recentlySticker) {
        if (recentStickerGridView != null) {
            recentStickerGridView.invalidateStrickers(recentlySticker);
        }
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void updateStickerAdapter(ArrayList<StructGroupSticker> categoryStickerList) {

        this.stickerList = categoryStickerList;
        notifyDataSetChanged();

    }

    public void onUpdateSticker(int updatePosition) {
        stickerGridView.onUpdateSticker(updatePosition);
    }


}
