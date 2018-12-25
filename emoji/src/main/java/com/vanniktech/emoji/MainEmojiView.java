package com.vanniktech.emoji;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener;
import com.vanniktech.emoji.listeners.OnEmojiClickListener;
import com.vanniktech.emoji.listeners.OnEmojiLongClickListener;
import com.vanniktech.emoji.listeners.OnStickerListener;
import com.vanniktech.emoji.sticker.StructSticker;

import java.io.File;
import java.util.ArrayList;

@SuppressLint("ViewConstructor")
final class MainEmojiView extends LinearLayout implements ViewPager.OnPageChangeListener {
    private final MianPagerAdapter emojiPagerAdapter;
    private ViewPager emojisPager;
    private OnPageChangeMainViewPager onChangeViewPager;
    public static final String DIR_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String emoji = "/emoji";
    public static String DIR_APP = DIR_SDCARD + emoji;

    @Nullable
    OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

    MainEmojiView(final Activity context, final OnEmojiClickListener onEmojiClickListener,
                  final OnEmojiLongClickListener onEmojiLongClickListener, @NonNull final RecentEmoji recentEmoji,
                  @NonNull final VariantEmoji variantManager, int backgroundColor, int iconColor, int dividerColor, OnStickerListener onStickerListener) {
        super(context);

        ArrayList<StructSticker> stickerList = getStickerPackage();

        View.inflate(context, R.layout.emoji_main_view_pager, this);

        setOrientation(VERTICAL);
        if (backgroundColor != 0)
            setBackgroundColor(backgroundColor);
        else
            setBackgroundColor(ContextCompat.getColor(context, R.color.emoji_background));


        emojisPager = findViewById(R.id.main_pager);

        emojisPager.addOnPageChangeListener(this);
        onChangeViewPager = new OnPageChangeMainViewPager() {
            @Override
            public void changePage() {

                if (emojisPager.getCurrentItem() == 0) {
                    emojisPager.setCurrentItem(1);
                } else {
                    emojisPager.setCurrentItem(0);
                }


            }
        };

        emojiPagerAdapter = new MianPagerAdapter(context, onEmojiClickListener, onEmojiLongClickListener, recentEmoji, variantManager, backgroundColor, iconColor, dividerColor, onChangeViewPager, stickerList ,onStickerListener);

        emojisPager.setAdapter(emojiPagerAdapter);
        emojisPager.setCurrentItem(0);
        onPageSelected(0);


    }

    private ArrayList<StructSticker> getStickerPackage() {

        ArrayList<StructSticker> stickerList = new ArrayList<>();
        File folder = new File(DIR_APP);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Do something on success
        File[] digi = folder.listFiles();
        for (File aDigi : digi) {
            ArrayList<String> path = new ArrayList<>();
            File file = new File(DIR_APP + "/" + aDigi.getName());
            File[] into = file.listFiles();
            for (File anInto : into) {
                path.add(anInto.getPath());
            }
             stickerList.add(new StructSticker(aDigi.getName(),String.valueOf(into.length),file, path));
        }

        return stickerList;
    }

    public void setOnEmojiBackspaceClickListener(@Nullable final OnEmojiBackspaceClickListener onEmojiBackspaceClickListener) {
        this.onEmojiBackspaceClickListener = onEmojiBackspaceClickListener;
    }

    @Override
    public void onPageSelected(final int i) {
    }

    @Override
    public void onPageScrolled(final int i, final float v, final int i2) {
        // No-op.
    }

    @Override
    public void onPageScrollStateChanged(final int i) {
        // No-op.
    }

}
