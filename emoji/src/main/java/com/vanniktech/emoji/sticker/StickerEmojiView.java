package com.vanniktech.emoji.sticker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Environment;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vanniktech.emoji.OnPageChangeMainViewPager;
import com.vanniktech.emoji.R;
import com.vanniktech.emoji.RecentEmoji;
import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener;
import com.vanniktech.emoji.listeners.OnStickerListener;

import java.io.File;
import java.util.ArrayList;

@SuppressLint("ViewConstructor")
public final class StickerEmojiView extends LinearLayout implements ViewPager.OnPageChangeListener {

    private RecyclerView rcvTab;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ArrayList<String> tabImageList = new ArrayList<>();
    private OnPageChangeMainViewPager onChangeViewPager;
    private final StickerPagerAdapter stickerPagerAdapter;
    public static OnNotifyList onNotifyList;
    private final String RECENT = "RECENT";
    private RecentSticker recentSticker;
    private int stickerTabLastSelectedIndex = -1;
    private static final String DIR_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String emoji = "/emoji";
    private static String DIR_APP = DIR_SDCARD + emoji;
    private static StickerDatabase stickerDatabase;

    @Nullable
    OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

    protected static StickerDatabase getStickerDatabase(Context context) {

        if (stickerDatabase == null) stickerDatabase = new StickerDatabase(context);

        return stickerDatabase;
    }

    public StickerEmojiView(final Activity context, int backgroundColor, int iconColor, int dividerColor, final OnPageChangeMainViewPager onChangeViewPager, OnStickerListener onStickerListener) {
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
        setting.setColorFilter(R.color.cardview_shadow_start_color, PorterDuff.Mode.SRC_IN);
        setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(context, EmojiSettingPage.class);
                getContext().startActivity(myIntent);
            }
        });

        ImageView imgSmilePage = findViewById(R.id.imgEmojiPage);
        imgSmilePage.setColorFilter(R.color.cardview_shadow_start_color, PorterDuff.Mode.SRC_IN);
        imgSmilePage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChangeViewPager != null) onChangeViewPager.changePage();
            }
        });

        ArrayList<StructSticker> stickerList = getStickerPackage(context);


        rcvTab = findViewById(R.id.rcvTabImageSticker);
        final ViewPager emojisPager = findViewById(R.id.stickerPager);

        emojisPager.addOnPageChangeListener(this);

        stickerList.add(0, new StructSticker(RECENT, "", null, null));


        recentSticker = new RecentStickeriManager(context);

        stickerPagerAdapter = new StickerPagerAdapter(context, backgroundColor, iconColor, dividerColor, stickerList, onChangeViewPager, onStickerListener, recentSticker);

        onNotifyList = new OnNotifyList() {
            @Override
            public void notifyList(int po) {
                stickerPagerAdapter.notifyDataSetChanged();
                myRecyclerViewAdapter.notifyDataSetChanged();
            }
        };
        final int startIndex = stickerPagerAdapter.numberOfRecentEmojis() > 0 ? 0 : 1;

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, stickerList, emojisPager, startIndex);


        myRecyclerViewAdapter.indexItemSelect = 0;

        rcvTab.setAdapter(myRecyclerViewAdapter);
        rcvTab.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        emojisPager.setAdapter(stickerPagerAdapter);
        emojisPager.setCurrentItem(startIndex);
        onPageSelected(startIndex);
    }


    @Override
    public void onPageSelected(final int i) {

        if (stickerTabLastSelectedIndex != i) {

            if (i == 0) {
                stickerPagerAdapter.invalidateRecentStickers();
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

        private ArrayList<StructSticker> mData;
        private LayoutInflater mInflater;
        private ViewPager emojisPager;
        public int indexItemSelect = 0;
        private Context context;
        private int lastIndexSelect;

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

            if (position >= mData.size()) {
                holder.imgSticker.setImageResource(R.drawable.sticker_emoji);
                holder.imgSticker.setColorFilter(R.color.cardview_shadow_start_color, PorterDuff.Mode.SRC_IN);
                return;
            }

            StructSticker item = mData.get(position);
            if (position == 0) {
                holder.imgSticker.setImageResource(R.drawable.emoji_recent);
                holder.imgSticker.setColorFilter(R.color.cardview_shadow_start_color, PorterDuff.Mode.SRC_IN);
            } else {
                holder.imgSticker.clearColorFilter();
                Glide.with(context)
                        .load(new File(item.getPath().get(0))) // Uri of the picture
                        .into(holder.imgSticker);
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
                    Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show();
                    return;
                }
                emojisPager.setCurrentItem(getAdapterPosition());
                indexItemSelect = getAdapterPosition();
            }
        }
    }

    public interface OnNotifyList {
        void notifyList(int po);
    }

    private ArrayList<StructSticker> getStickerPackage(Activity context) {

        ArrayList<StructSticker> stickerList = new ArrayList<>();
        File folder = new File(DIR_APP);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Do something on success

        int id = 1000;
        int id_sticker = 5000;

        File[] digi = folder.listFiles();
        for (File aDigi : digi) {
            ArrayList<String> path = new ArrayList<>();
            File file = new File(DIR_APP + "/" + aDigi.getName());
            File[] into = file.listFiles();

            if (stickerDatabase.checkIsDataAlreadyInDBorNot("" + id)) {
                continue;
            }
            stickerDatabase.insertCategorySticker(aDigi.getName(), "" + id++);

            for (File anInto : into) {
                path.add(anInto.getPath());
                stickerDatabase.insertSticker("" + id, "" + id_sticker++, anInto.getPath());
            }


            stickerList.add(new StructSticker(aDigi.getName(), String.valueOf(into.length), file, path));
        }

        return stickerList;
    }


}
