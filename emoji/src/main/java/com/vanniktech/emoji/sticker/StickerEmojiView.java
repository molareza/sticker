package com.vanniktech.emoji.sticker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vanniktech.emoji.OnPageChangeMainViewPager;
import com.vanniktech.emoji.R;
import com.vanniktech.emoji.listeners.OnOpenPageStickerListener;
import com.vanniktech.emoji.listeners.OnStickerListener;
import com.vanniktech.emoji.listeners.OnUpdateStickerListener;
import com.vanniktech.emoji.sticker.struct.StructGroupSticker;
import com.vanniktech.emoji.sticker.struct.StructItemSticker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public final class StickerEmojiView extends LinearLayout implements ViewPager.OnPageChangeListener {

    private RecyclerView rcvTab;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ArrayList<String> tabImageList = new ArrayList<>();
    private OnPageChangeMainViewPager onChangeViewPager;
    private final StickerPagerAdapter stickerPagerAdapter;
    //    public static OnNotifyList onNotifyList;
    private int stickerTabLastSelectedIndex = -1;
    private static StickerDatabase stickerDatabase;
    private Context context;
    private OnOpenPageStickerListener myOnOpenPageStickerListener;
    private ArrayList<StructGroupSticker> categoryStickerList;
    private ViewPager emojisPager;
    public static OnUpdateStickerListener mOnUpdateStickerListener;

    protected static StickerDatabase getStickerDatabase(Context context) {
        if (stickerDatabase == null) stickerDatabase = new StickerDatabase(context);
        return stickerDatabase;
    }

    public StickerEmojiView(final Activity context, int backgroundColor, int iconColor, int dividerColor, final OnPageChangeMainViewPager onChangeViewPager, OnStickerListener onStickerListener, OnUpdateStickerListener onUpdateStickerListener, final OnOpenPageStickerListener onOpenPageStickerListener) {
        super(context);
        View.inflate(context, R.layout.emoji_sticker_view, this);
        this.onChangeViewPager = onChangeViewPager;
        setOrientation(VERTICAL);

        this.context = context;
        this.myOnOpenPageStickerListener = onOpenPageStickerListener;
        this.mOnUpdateStickerListener = onUpdateStickerListener;

        if (backgroundColor != 0) {
            setBackgroundColor(backgroundColor);
        } else {
            setBackgroundColor(ContextCompat.getColor(context, R.color.emoji_background));
        }

        final View emojiDivider = findViewById(R.id.emojiDivider);
        if (dividerColor != 0) {
            emojiDivider.setBackgroundColor(dividerColor);
        } else {
            emojiDivider.setBackgroundColor(getResources().getColor(R.color.emoji_divider));
        }

        ImageView setting = findViewById(R.id.imgStickerSetting);
        setting.setColorFilter(R.color.emoji_background_sticker_tab, PorterDuff.Mode.SRC_IN);
        setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOpenPageStickerListener != null)
                    onOpenPageStickerListener.openSetting(categoryStickerList);
            }
        });

        ImageView imgSmilePage = findViewById(R.id.imgEmojiPage);
        imgSmilePage.setColorFilter(R.color.emoji_background_sticker_tab, PorterDuff.Mode.SRC_IN);
        imgSmilePage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChangeViewPager != null) onChangeViewPager.changePage();
            }
        });

        categoryStickerList = new ArrayList<>();

        categoryStickerList.add(0, new StructGroupSticker());

        rcvTab = findViewById(R.id.rcvTabImageSticker);
        emojisPager = findViewById(R.id.stickerPager);

        emojisPager.addOnPageChangeListener(this);

        /**
         * addSticker sticker
         */
        ArrayList<StructItemSticker> recentStickerList = getStickerDatabase(context).getRecentlySticker();

        stickerPagerAdapter = new StickerPagerAdapter(context, backgroundColor, iconColor, dividerColor, categoryStickerList, onChangeViewPager, onStickerListener, recentStickerList, onUpdateStickerListener);

        final int startIndex = recentStickerList.size() > 0 ? 0 : 1;
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, categoryStickerList, emojisPager, startIndex);

        myRecyclerViewAdapter.indexItemSelect = 0;

        rcvTab.setAdapter(myRecyclerViewAdapter);
        rcvTab.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        emojisPager.setOffscreenPageLimit(0);
        emojisPager.setAdapter(stickerPagerAdapter);
        emojisPager.setCurrentItem(startIndex);
        onPageSelected(startIndex);

    }


    @Override
    public void onPageSelected(final int i) {

        if (stickerTabLastSelectedIndex != i) {

            if (i == 0) {
                stickerPagerAdapter.invalidateRecentStickers(getStickerDatabase(context).getRecentlySticker());
            }

            myRecyclerViewAdapter.indexItemSelect = i;
            myRecyclerViewAdapter.notifyItemChanged(myRecyclerViewAdapter.lastIndexSelect);
            myRecyclerViewAdapter.notifyItemChanged(i);

            if (i >= 4 && (i + 2 <= tabImageList.size())) {
                rcvTab.smoothScrollToPosition(tabImageList.size());
            } else {
                if ((i - 1) >= 0) rcvTab.smoothScrollToPosition(0);
            }

            stickerTabLastSelectedIndex = i;

        }

    }

    @Override
    public void onPageScrolled(final int i, final float v, final int i2) {
        // No-op.
    }

    @Override
    public void onPageScrollStateChanged(final int i) {
        // No-op.
    }

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        private ArrayList<StructGroupSticker> mData;
        private LayoutInflater mInflater;
        private ViewPager emojisPager;
        public int indexItemSelect = 0;
        private Context context;
        private int lastIndexSelect;

//        private ItemClickListener mClickListener;

        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, ArrayList<StructGroupSticker> data, ViewPager emojisPager, int startIndex) {
            this.mInflater = LayoutInflater.from(context);
            this.context = context;
            this.mData = data;
            this.emojisPager = emojisPager;
            this.indexItemSelect = startIndex;

        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.emoji_category, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            if (position >= mData.size()) {
                holder.imgSticker.setImageResource(R.drawable.emoji_add);
                holder.imgSticker.setColorFilter(R.color.emoji_background_sticker_tab, PorterDuff.Mode.SRC_IN);
                return;
            }

            StructGroupSticker item = mData.get(position);
            if (position == 0) {
                holder.imgSticker.setImageResource(R.drawable.emoji_recent);
                holder.imgSticker.setColorFilter(R.color.emoji_background_sticker_tab, PorterDuff.Mode.SRC_IN);
            } else {
                if (item.getUri() == null) return;
                holder.imgSticker.clearColorFilter();

                if (new File(item.getUri()).exists()) {
                    Glide.with(context)
                            .load(new File(item.getUri())) // Uri of the picture
                            .into(holder.imgSticker);
                } else {
                    if (mOnUpdateStickerListener != null)
                        mOnUpdateStickerListener.onUpdateTabSticker(item.getAvatarToken(), item.getName(), item.getAvatarSize(), position);
                }

            }

            if (indexItemSelect == position) {
                holder.itemView.setBackgroundColor(getResources().getColor(R.color.emoji_background_sticker_tab));
                lastIndexSelect = position;
            } else {
                holder.itemView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size() + 1;
        }

        public void updateStickerAdapter(ArrayList<StructGroupSticker> categoryStickerList) {

            this.mData = categoryStickerList;
            notifyDataSetChanged();

        }

        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageButton imgSticker;

            ViewHolder(View itemView) {
                super(itemView);
                imgSticker = itemView.findViewById(R.id.imgTab);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                if (getAdapterPosition() >= mData.size()) {
                    if (myOnOpenPageStickerListener != null) {
                        myOnOpenPageStickerListener.addSticker("ADD");
                    } else {
                        Toast.makeText(context, "something is wrong", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                emojisPager.setCurrentItem(getAdapterPosition());
                indexItemSelect = getAdapterPosition();
            }
        }
    }

    public void updateListStickers(ArrayList<StructGroupSticker> structAllStickers) {

        if (structAllStickers != null) {
            categoryStickerList = new ArrayList<>();
            categoryStickerList.addAll(structAllStickers);
            categoryStickerList.add(0, new StructGroupSticker());
            updateStickerList();
        }
    }

    private void updateStickerList() {

        stickerPagerAdapter.updateStickerAdapter(categoryStickerList);
        myRecyclerViewAdapter.updateStickerAdapter(categoryStickerList);
        onPageSelected(0);
    }

    public void onUpdateSticker(int updatePosition) {
        stickerPagerAdapter.onUpdateSticker(updatePosition);
    }

    public void onUpdateRecentSticker(ArrayList<StructGroupSticker> structAllStickers) {
    }

    public void onUpdateTabSticker(int updatePosition) {
        myRecyclerViewAdapter.notifyItemChanged(updatePosition);
    }


}
