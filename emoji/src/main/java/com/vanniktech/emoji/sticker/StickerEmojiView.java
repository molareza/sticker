package com.vanniktech.emoji.sticker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Environment;
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
import com.vanniktech.emoji.listeners.OnOpenPageStickerListener;
import com.vanniktech.emoji.listeners.OnStickerListener;
import com.vanniktech.emoji.listeners.OnUpdateStickerListener;
import com.vanniktech.emoji.sticker.struct.StructGroupSticker;
import com.vanniktech.emoji.sticker.struct.StructItemSticker;

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
    private int stickerTabLastSelectedIndex = -1;
    private static final String DIR_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String emoji = "/emoji";
    private static String DIR_APP = DIR_SDCARD + emoji;
    private static StickerDatabase stickerDatabase;
    private Context context;
    private OnOpenPageStickerListener myOnOpenPageStickerListener;
    private ArrayList<StructGroupSticker> categoryStickerList;
    private  ViewPager emojisPager;

    protected static StickerDatabase getStickerDatabase(Context context) {
        if (stickerDatabase == null) stickerDatabase = new StickerDatabase(context);
        return stickerDatabase;
    }

    public StickerEmojiView(final Activity context, int backgroundColor, int iconColor, int dividerColor, final OnPageChangeMainViewPager onChangeViewPager, OnStickerListener onStickerListener, OnUpdateStickerListener onUpdateStickerListener, OnOpenPageStickerListener onOpenPageStickerListener) {
        super(context);
        View.inflate(context, R.layout.emoji_sticker_view, this);
        this.onChangeViewPager = onChangeViewPager;
        setOrientation(VERTICAL);

        this.context = context;
        this.myOnOpenPageStickerListener = onOpenPageStickerListener;

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
        setting.setColorFilter(R.color.cardview_shadow_start_color, PorterDuff.Mode.SRC_IN);
        setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(context, EmojiSettingPage.class);
                myIntent.putExtra("ALL", categoryStickerList);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

        categoryStickerList = getStickerDatabase(context).getAllCategory();

        categoryStickerList.add(0, new StructGroupSticker());

        rcvTab = findViewById(R.id.rcvTabImageSticker);
         emojisPager = findViewById(R.id.stickerPager);

        emojisPager.addOnPageChangeListener(this);

        /**
         * addSticker sticker
         */
        ArrayList<StructItemSticker> recentStickerList = getStickerDatabase(context).getRecentlySticker();

        Log.i("CCCCC", "StickerEmojiView: " + categoryStickerList.size());
        stickerPagerAdapter = new StickerPagerAdapter(context, backgroundColor, iconColor, dividerColor, categoryStickerList, onChangeViewPager, onStickerListener, recentStickerList);

        onNotifyList = new OnNotifyList() {
            @Override
            public void notifyList(int po) {
                updateStickerList();
            }
        };
        final int startIndex = recentStickerList.size() > 0 ? 0 : 1;
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, categoryStickerList, emojisPager, startIndex);

        myRecyclerViewAdapter.indexItemSelect = 0;

        rcvTab.setAdapter(myRecyclerViewAdapter);
        rcvTab.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        emojisPager.setOffscreenPageLimit(0);
        emojisPager.setAdapter(stickerPagerAdapter);
        emojisPager.setCurrentItem(startIndex);
        onPageSelected(startIndex);


//        setting.setColorFilter(R.color.cardview_shadow_start_color, PorterDuff.Mode.SRC_IN);
//        setting.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FragmentTransaction ft =  getfra().beginTransaction();
//                Fragment fragment = SettingFragment.newInstance(categoryStickerList);
//                ft.replace(R.id.rootFragmentSetting, fragment);
//                ft.commit();
//
//            }
//        });

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
                holder.imgSticker.setImageResource(R.drawable.sticker_emoji);
                holder.imgSticker.setColorFilter(R.color.cardview_shadow_start_color, PorterDuff.Mode.SRC_IN);
                return;
            }

            StructGroupSticker item = mData.get(position);
            if (position == 0) {
                holder.imgSticker.setImageResource(R.drawable.emoji_recent);
                holder.imgSticker.setColorFilter(R.color.cardview_shadow_start_color, PorterDuff.Mode.SRC_IN);
            } else {
                if (item.getUri() == null) return;
                holder.imgSticker.clearColorFilter();
                Glide.with(context)
                        .load(new File(item.getUri())) // Uri of the picture
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

    public interface OnNotifyList {
        void notifyList(int po);
    }


    public void updateDB(ArrayList<StructGroupSticker> structAllStickers) {
        /**
         *
         * insert to db
         *
         *
         */

        boolean isChange = false;

        for (StructGroupSticker item : structAllStickers) {
            if (getStickerDatabase(context).checkIsDataAlreadyInDBorNot(item.getId())) {
                continue;
            }
            isChange = true;

            if (item.getStickers().size() <= 0) return;
            getStickerDatabase(context).insertCategorySticker("" + item.getCreatedAt(), item.getId(), item.getRefId(), item.getName(), item.getAvatarToken(), item.getUri(), item.getPrice(), item.getIsVip(), item.getSort(), "" + item.getCreatedBy());

            for (StructItemSticker anInto : item.getStickers()) {
                getStickerDatabase(context).insertSticker(anInto.getId(), anInto.getRefId(), anInto.getName(), anInto.getToken(), anInto.getUri(), anInto.getSort(), anInto.getGroupId());
            }
        }
        if (!isChange) return;

        updateStickerList();
    }

    private void updateStickerList() {
        categoryStickerList = getStickerDatabase(context).getAllCategory();
        categoryStickerList.add(0, new StructGroupSticker());
        stickerPagerAdapter.updateStickerAdapter(categoryStickerList);
        myRecyclerViewAdapter.updateStickerAdapter(categoryStickerList);
        onPageSelected(0);
    }


}
