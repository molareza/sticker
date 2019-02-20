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
import com.vanniktech.emoji.sticker.struct.StructItemSticker;

import java.io.File;
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
        final String s = mSticker.get(position).getUri();
        Glide.with(context)
                .load(new File(s)) // Uri of the picture
                .apply(new RequestOptions().override(160, 160))
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StickerEmojiView.getStickerDatabase(context).insertOrUpdateRecentlySticker( mSticker.get(position).getId(),mSticker.get(position).getRefId() , mSticker.get(position).getName() ,mSticker.get(position).getToken(),mSticker.get(position).getUri(),mSticker.get(position).getSort(),mSticker.get(position).getGroupId(), System.currentTimeMillis());
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
