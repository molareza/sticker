package com.vanniktech.emoji;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener;
import com.vanniktech.emoji.listeners.OnEmojiClickListener;
import com.vanniktech.emoji.listeners.OnEmojiLongClickListener;
import com.vanniktech.emoji.sticker.OnOpenPageStickerListener;
import com.vanniktech.emoji.sticker.OnPageChangeMainViewPager;
import com.vanniktech.emoji.sticker.OnStickerListener;
import com.vanniktech.emoji.sticker.OnUpdateStickerListener;
import com.vanniktech.emoji.sticker.StickerEmojiView;
import com.vanniktech.emoji.sticker.struct.StructGroupSticker;

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
    private OnStickerListener onStickerListener;
    private OnUpdateStickerListener onUpdateStickerListener;
    private OnOpenPageStickerListener onOpenPageStickerListener;
    private OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;
    private StickerEmojiView stickerEmojiView;
    private ViewPager.PageTransformer pageTransformer;

    public MianPagerAdapter(Activity context, OnEmojiClickListener onEmojiClickListener, OnEmojiLongClickListener onEmojiLongClickListener, RecentEmoji recentEmoji, VariantEmoji variantManager, int backgroundColor, int iconColor, int dividerColor, OnEmojiBackspaceClickListener onEmojiBackspaceClickListener, OnPageChangeMainViewPager onChangeViewPager, OnStickerListener onStickerListener, OnUpdateStickerListener onUpdateStickerListener, OnOpenPageStickerListener onOpenPageStickerListener, ViewPager.PageTransformer pageTransformer) {
        this.context = context;
        this.clickListener = onEmojiClickListener;
        this.longClickListener = onEmojiLongClickListener;
        this.recentEmoji = recentEmoji;
        this.variantEmoji = variantManager;
        this.backgroundColor = backgroundColor;
        this.iconColor = iconColor;
        this.dividerColor = dividerColor;
        this.onChangeViewPager = onChangeViewPager;
        this.onStickerListener = onStickerListener;
        this.onUpdateStickerListener = onUpdateStickerListener;
        this.onEmojiBackspaceClickListener = onEmojiBackspaceClickListener;
        this.onOpenPageStickerListener = onOpenPageStickerListener;
        this.pageTransformer = pageTransformer;

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(final ViewGroup pager, final int position) {
        final View newView;

        if (position == RECENT_POSITION) {
            newView = new EmojiView(context, clickListener, longClickListener, recentEmoji, variantEmoji, backgroundColor, iconColor, dividerColor, onEmojiBackspaceClickListener,onChangeViewPager,pageTransformer);
        } else {
            stickerEmojiView = new StickerEmojiView(context, backgroundColor, iconColor, dividerColor, onChangeViewPager, onStickerListener,onUpdateStickerListener , onOpenPageStickerListener);
            newView = stickerEmojiView;
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

    public void updateSticker(final ArrayList<StructGroupSticker> structAllStickers){

        if (stickerEmojiView ==null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (stickerEmojiView !=null) stickerEmojiView.updateListStickers(structAllStickers);
                }
            },2000);
        }else {
             stickerEmojiView.updateListStickers(structAllStickers);
        }
    }

    public void onUpdateSticker(int updatePosition) {
        stickerEmojiView.onUpdateSticker(updatePosition);
    }

    public void onUpdateRecentSticker(ArrayList<String> structAllStickers) {
        stickerEmojiView.onUpdateRecentSticker(structAllStickers);
    }

    public void onUpdateTabSticker(int updatePosition) {
        stickerEmojiView.onUpdateTabSticker(updatePosition);
    }
}
