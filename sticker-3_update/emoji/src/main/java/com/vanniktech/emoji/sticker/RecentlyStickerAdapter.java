package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vanniktech.emoji.EmojiImageView;
import com.vanniktech.emoji.R;
import com.vanniktech.emoji.sticker.listener.OnStickerListener;
import com.vanniktech.emoji.sticker.struct.StructItemSticker;

import java.util.Collection;
import java.util.List;



final class RecentlyStickerAdapter extends ArrayAdapter<StructItemSticker> {
    private List<StructItemSticker> mSticker;
    private OnStickerListener onStickerListener;

    RecentlyStickerAdapter(@NonNull final Context context, OnStickerListener onStickerListener, List<StructItemSticker> mSticker) {
        super(context, 0, mSticker);
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
        if (mSticker.get(position).getImageUrl() == null)return image;

        Glide.with(context)
                .load(mSticker.get(position).getImageUrl()) // Uri of the picture
                .apply(new RequestOptions().override(160, 160))
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StickerEmojiView.getStickerDatabase(context).insertOrUpdateRecentlySticker(mSticker.get(position));
                if (onStickerListener != null) onStickerListener.onItemSticker(mSticker.get(position));
            }
        });

        return image;
    }

    void updateSticker(final Collection<StructItemSticker> sticker) {
        clear();
        addAll(sticker);
        notifyDataSetChanged();
    }
}
