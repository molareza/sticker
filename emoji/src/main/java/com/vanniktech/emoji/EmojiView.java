package com.vanniktech.emoji;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vanniktech.emoji.emoji.EmojiCategory;
import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener;
import com.vanniktech.emoji.listeners.OnEmojiClickListener;
import com.vanniktech.emoji.listeners.OnEmojiLongClickListener;
import com.vanniktech.emoji.listeners.RepeatListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SuppressLint("ViewConstructor")
final class EmojiView extends LinearLayout implements ViewPager.OnPageChangeListener {
    private static final long INITIAL_INTERVAL = TimeUnit.SECONDS.toMillis(1) / 2;
    private static final int NORMAL_INTERVAL = 50;

    @ColorInt
    private final int themeAccentColor;
    @ColorInt
    private int themeIconColor = 0;

    private final EmojiPagerAdapter emojiPagerAdapter;
    private ArrayList<Integer> tabImageList = new ArrayList<>();
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private final String RECENT = "RECENT";
    private final String STICKER = "STICKER";
    private final String CONST = "CONST";
    private RecyclerView rcvTab;
    private OnPageChangeMainViewPager onChangeViewPager;

    @Nullable
    OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

    private int emojiTabLastSelectedIndex = -1;

    EmojiView(final Context context, final OnEmojiClickListener onEmojiClickListener,
              final OnEmojiLongClickListener onEmojiLongClickListener, @NonNull final RecentEmoji recentEmoji,
              @NonNull final VariantEmoji variantManager, int backgroundColor, int iconColor, int dividerColor, @Nullable OnEmojiBackspaceClickListener onEmojiBackspaceClickListener, OnPageChangeMainViewPager onChangeViewPager) {
        super(context);

        View.inflate(context, R.layout.emoji_view, this);
        this.onChangeViewPager = onChangeViewPager;
        this.onEmojiBackspaceClickListener = onEmojiBackspaceClickListener;
        setOrientation(VERTICAL);
        if (backgroundColor != 0)
            setBackgroundColor(backgroundColor);
        else
            setBackgroundColor(ContextCompat.getColor(context, R.color.emoji_background));


        if (iconColor != 0)
            themeIconColor = iconColor;
        else
            themeIconColor = ContextCompat.getColor(context, R.color.emoji_icons);


        final TypedValue value = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getTheme().resolveAttribute(android.R.attr.colorAccent, value, true);
        }
        themeAccentColor = value.data;

        final ViewPager emojisPager = findViewById(R.id.emojis_pager);
        final View emojiDivider = findViewById(R.id.emoji_divider);
        if (dividerColor != 0) {
            emojiDivider.setBackgroundColor(dividerColor);
        } else {
            emojiDivider.setBackgroundColor(getResources().getColor(R.color.emoji_divider));
        }

        rcvTab = findViewById(R.id.rcvTabImage);
        emojisPager.addOnPageChangeListener(this);

        final EmojiCategory[] categories = EmojiManager.getInstance().getCategories();

        for (EmojiCategory category : categories) {
            tabImageList.add(category.getIcon());
        }

        tabImageList.add(0, R.drawable.emoji_recent);
        tabImageList.add((tabImageList.size()), R.drawable.sticker_emoji);

        ImageView btnBack = findViewById(R.id.imgBack);
        btnBack.setColorFilter(R.color.emoji_background_sticker_tab, PorterDuff.Mode.SRC_IN);

        btnBack.setOnTouchListener(new RepeatListener(INITIAL_INTERVAL, NORMAL_INTERVAL, new OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (EmojiView.this.onEmojiBackspaceClickListener != null) {
                    EmojiView.this.onEmojiBackspaceClickListener.onEmojiBackspaceClick(view);
                }
            }
        }));

        emojiPagerAdapter = new EmojiPagerAdapter(onEmojiClickListener, onEmojiLongClickListener, recentEmoji, variantManager);
        final int startIndex = emojiPagerAdapter.numberOfRecentEmojis() > 0 ? 0 : 1;

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(context, tabImageList, emojisPager, startIndex);
        rcvTab.setAdapter(myRecyclerViewAdapter);
        rcvTab.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        emojisPager.setOffscreenPageLimit(0);
        emojisPager.setAdapter(emojiPagerAdapter);
        emojisPager.setCurrentItem(startIndex);
        onPageSelected(startIndex);
    }


    @Override
    public void onPageSelected(final int i) {
        if (emojiTabLastSelectedIndex != i) {
            if (i == 0) {
                emojiPagerAdapter.invalidateRecentEmojis();
            }
            myRecyclerViewAdapter.indexItemSelect = i;
            myRecyclerViewAdapter.notifyDataSetChanged();
            if (i >= 4 && (i + 2 <= tabImageList.size())) {
                rcvTab.smoothScrollToPosition(tabImageList.size());
            } else {
                if ((i - 1) >= 0) rcvTab.smoothScrollToPosition(0);
            }
            emojiTabLastSelectedIndex = i;
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

        private ArrayList<Integer> mData;
        private LayoutInflater mInflater;
        private ViewPager emojisPager;
        public int indexItemSelect;
        private int lastIndexSelect;
        private Context context;
//        private ItemClickListener mClickListener;

        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, ArrayList<Integer> data, ViewPager emojisPager, int startIndex) {
            this.mInflater = LayoutInflater.from(context);
            this.context = context;
            this.mData = data;
            this.emojisPager = emojisPager;
            this.indexItemSelect = startIndex;
        }

        // inflates the row layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.emoji_category, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int item = mData.get(position);

            holder.myTextView.setImageResource(item);

            holder.myTextView.setColorFilter(R.color.emoji_background_sticker_tab, PorterDuff.Mode.SRC_IN);
            if (indexItemSelect == position) {
                lastIndexSelect = position;
                holder.myTextView.setColorFilter(themeIconColor, PorterDuff.Mode.SRC_IN);
            }

        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageButton myTextView;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.imgTab);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mData.get(getAdapterPosition()) == mData.get(mData.size() - 1)) {
                    if (onChangeViewPager != null) onChangeViewPager.changePage();
                    return;
                }
                indexItemSelect = getAdapterPosition();
                emojisPager.setCurrentItem(getAdapterPosition());


            }
        }
    }


    public class StrauctTabItem {
        public int imageUri;
        public String tag;

        public StrauctTabItem(int imageUri, String tag) {
            this.imageUri = imageUri;
            this.tag = tag;
        }

        public int getImageUri() {
            return imageUri;
        }

        public void setImageUri(int imageUri) {
            this.imageUri = imageUri;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

}
