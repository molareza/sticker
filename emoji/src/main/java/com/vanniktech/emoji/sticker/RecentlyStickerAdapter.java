package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.vanniktech.emoji.EmojiImageView;
import com.vanniktech.emoji.R;
import com.vanniktech.emoji.sticker.listener.OnStickerListener;
import com.vanniktech.emoji.sticker.struct.StructItemSticker;

import java.util.Collection;
import java.util.List;



final class RecentlyStickerAdapter extends ArrayAdapter<StructItemSticker> {
    private List<StructItemSticker> mSticker;
    private OnStickerListener onStickerListener;
    private ProgressBar prgLoading;
    private EmojiImageView image;
    RecentlyStickerAdapter(@NonNull final Context context, OnStickerListener onStickerListener, List<StructItemSticker> mSticker) {
        super(context, 0, mSticker);
        this.mSticker = mSticker;
        this.onStickerListener = onStickerListener;
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull final ViewGroup parent) {
//        EmojiImageView image = (EmojiImageView) convertView.findViewById(R.id.emoji_image);
//        prgLoading = convertView.findViewById(R.id.prgLoading);

        final Context context = getContext();

//        if (image == null) {
            View v = LayoutInflater.from(context).inflate(R.layout.emoji_item, parent, false);
            image = (EmojiImageView) v.findViewById(R.id.emoji_image);
            prgLoading = v.findViewById(R.id.emoji_image);
//        }
        if (mSticker.get(position).getImageUrl() == null)return image;

        Glide.with(context)
                .load(mSticker.get(position).getImageUrl()) // Uri of the picture
                .apply(new RequestOptions().override(160, 160))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (prgLoading !=null) prgLoading.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (prgLoading !=null)prgLoading.setVisibility(View.GONE);
                        return false;
                    }
                })
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
