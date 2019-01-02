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
import com.vanniktech.emoji.sticker.StickerDatabase;
import com.vanniktech.emoji.sticker.StructSticker;

import java.io.File;
import java.util.ArrayList;

@SuppressLint("ViewConstructor")
final class MainEmojiView extends LinearLayout implements ViewPager.OnPageChangeListener {
    private final MianPagerAdapter emojiPagerAdapter;
    private ViewPager emojisPager;
    private OnPageChangeMainViewPager onChangeViewPager;
    private static final String DIR_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String emoji = "/emoji";
    private static String DIR_APP = DIR_SDCARD + emoji;
    private StickerDatabase stickerDatabase;

    @Nullable
    OnEmojiBackspaceClickListener onEmojiBackspaceClickListener;

    MainEmojiView(final Activity context, final OnEmojiClickListener onEmojiClickListener,
                  final OnEmojiLongClickListener onEmojiLongClickListener, @NonNull final RecentEmoji recentEmoji,
                  @NonNull final VariantEmoji variantManager, int backgroundColor, int iconColor, OnEmojiBackspaceClickListener onEmojiBackspaceClickListener, int dividerColor, OnStickerListener onStickerListener) {
        super(context);

        stickerDatabase = new StickerDatabase(context);
        ArrayList<StructSticker> stickerList = getStickerPackage(context);


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

        emojiPagerAdapter = new MianPagerAdapter(context, onEmojiClickListener, onEmojiLongClickListener, recentEmoji, variantManager, backgroundColor, iconColor, dividerColor, onEmojiBackspaceClickListener, onChangeViewPager, stickerList, onStickerListener);

        emojisPager.setAdapter(emojiPagerAdapter);
        emojisPager.setCurrentItem(0);
        onPageSelected(0);


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
            stickerDatabase.insertCategorySticker(aDigi.getName(), "" + id++);

            for (File anInto : into) {
                path.add(anInto.getPath());
                stickerDatabase.insertSticker("" + id, "" + id_sticker++, anInto.getPath());
            }



            stickerList.add(new StructSticker(aDigi.getName(), String.valueOf(into.length), file, path));
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
