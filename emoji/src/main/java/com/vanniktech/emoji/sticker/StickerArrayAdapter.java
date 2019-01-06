package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.vanniktech.emoji.EmojiImageView;
import com.vanniktech.emoji.R;
import com.vanniktech.emoji.listeners.OnStickerListener;

import java.io.File;
import java.util.Collection;


final class StickerArrayAdapter extends ArrayAdapter<StructEachSticker> {
    private StructSticker mSticker;
    private OnStickerListener onStickerListener;

    StickerArrayAdapter(@NonNull final Context context, @NonNull StructSticker mSticker, OnStickerListener onStickerListener) {
        super(context, 0, mSticker.getEachSticker());
        this.mSticker = mSticker;
        this.onStickerListener = onStickerListener;
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull final ViewGroup parent) {
        EmojiImageView image = (EmojiImageView) convertView;

        final Context context = getContext();

        if (image == null) {
            image = (EmojiImageView) LayoutInflater.from(context).inflate(R.layout.emoji_item, parent, false);
        }
        final String s = mSticker.getEachSticker().get(position).getUrl();
        Glide.with(context)
                .load(new File(s)) // Uri of the picture
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StickerDatabase stickerDatabase = StickerEmojiView.getStickerDatabase(context);
                stickerDatabase.insertOrUpdateRecentlySticker( mSticker.getEachSticker().get(position).getIdSticker(),mSticker.getIdCategory() , mSticker.getEachSticker().get(position).getUrl() , System.currentTimeMillis());
                if (onStickerListener != null) onStickerListener.onStickerPath(s);
            }
        });

        return image;
    }

    void updateSticker(final Collection<StructEachSticker> sticker) {
        clear();
        addAll(sticker);
        notifyDataSetChanged();
    }
}
