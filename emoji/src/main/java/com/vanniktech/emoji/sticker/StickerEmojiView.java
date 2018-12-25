package com.vanniktech.emoji.sticker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.vanniktech.emoji.OnPageChangeMainViewPager;
import com.vanniktech.emoji.R;
import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener;

import java.io.File;
import java.util.ArrayList;

@SuppressLint("ViewConstructor")
public final class StickerEmojiView extends LinearLayout implements ViewPager.OnPageChangeListener {

    private RecyclerView rcvTab;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ArrayList<String> tabImageList = new ArrayList<>();
    private boolean isOnClickWithAdapter = false;
    private OnPageChangeMainViewPager onChangeViewPager;
    private final StickerPagerAdapter stickerPagerAdapter;
    public static OnNotifyList onNotifyList;
    @Nullable
    OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

    public StickerEmojiView(final Activity context, int backgroundColor, int iconColor, int dividerColor, final OnPageChangeMainViewPager onChangeViewPager, final ArrayList<StructSticker> stickerList) {
        super(context);

        View.inflate(context, R.layout.emoji_sticker_view, this);
        this.onChangeViewPager = onChangeViewPager;

        setOrientation(VERTICAL);
        if (backgroundColor != 0)
            setBackgroundColor(backgroundColor);
        else
            setBackgroundColor(ContextCompat.getColor(context, R.color.emoji_background));

        final View emojiDivider = findViewById(R.id.emojiDivider);
        if (dividerColor != 0) {
            emojiDivider.setBackgroundColor(dividerColor);
        } else {
            emojiDivider.setBackgroundColor(getResources().getColor(R.color.emoji_divider));
        }

        ImageView setting = findViewById(R.id.imgStickerSetting);
        setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(context, EmojiSettingPage.class);
                getContext().startActivity(myIntent);
            }
        });

        ImageView imgSmilePage = findViewById(R.id.imgEmojiPage);
        imgSmilePage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChangeViewPager!=null)onChangeViewPager.changePage();
            }
        });

        rcvTab = findViewById(R.id.rcvTabImageSticker);
        final ViewPager emojisPager = findViewById(R.id.stickerPager);

        emojisPager.addOnPageChangeListener(this);

        stickerPagerAdapter = new StickerPagerAdapter(context, backgroundColor, iconColor, dividerColor, stickerList , onChangeViewPager);

        onNotifyList=  new OnNotifyList() {
            @Override
            public void notifyList(int po) {
                stickerPagerAdapter.notifyDataSetChanged();
                myRecyclerViewAdapter.notifyDataSetChanged();
            }
        };
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, stickerList, emojisPager, 0);

        myRecyclerViewAdapter.indexItemSelect = 0;

        rcvTab.setAdapter(myRecyclerViewAdapter);
        rcvTab.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        emojisPager.setAdapter(stickerPagerAdapter);
        emojisPager.setCurrentItem(0);
        onPageSelected(0);
    }


    @Override
    public void onPageSelected(final int i) {
        if (!isOnClickWithAdapter) {
            myRecyclerViewAdapter.indexItemSelect = i;
            myRecyclerViewAdapter.notifyDataSetChanged();

            if (i >= 4 && (i + 2 <= tabImageList.size())) {
                rcvTab.smoothScrollToPosition(tabImageList.size());
            } else {
                if ((i - 1) >= 0) rcvTab.smoothScrollToPosition(0);
            }
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

        private ArrayList<StructSticker> mData;
        private LayoutInflater mInflater;
        private ViewPager emojisPager;
        public int indexItemSelect = 0;
        private Context context;
//        private ItemClickListener mClickListener;

        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, ArrayList<StructSticker> data, ViewPager emojisPager, int startIndex) {
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

            if (position == 0){
                holder.imgSticker.setImageResource(R.drawable.emoji_recent);
                return;
            }else if (position >= mData.size()){

                holder.imgSticker.setImageResource(R.drawable.sticker_emoji);
                return;
            }

            if (indexItemSelect == position){
                holder.itemView.setBackgroundColor(getResources().getColor(R.color.emoji_background_sticker_tab));
            }else {
                holder.itemView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }

            StructSticker item = mData.get(position);
            Glide.with(context)
                    .load(new File(item.getPath().get(0))) // Uri of the picture
                    .into(holder.imgSticker);
            isOnClickWithAdapter = false;
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size() + 2;
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
                isOnClickWithAdapter = true;
                indexItemSelect = getAdapterPosition();
                emojisPager.setCurrentItem(getAdapterPosition());
                notifyDataSetChanged();

            }
        }
    }

    public interface OnNotifyList{
        void notifyList(int po);
    }


}
