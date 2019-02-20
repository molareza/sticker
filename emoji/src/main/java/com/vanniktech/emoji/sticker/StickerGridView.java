package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.widget.GridView;

import com.vanniktech.emoji.R;
import com.vanniktech.emoji.sticker.struct.StructGroupSticker;


final class StickerGridView extends GridView {
    StickerArrayAdapter stickerArrayAdapter;

    StickerGridView(@NonNull final Context context) {
        super(context);

        final Resources resources = getResources();
        final int width = resources.getDimensionPixelSize(R.dimen.emoji_sticker_grid_view_column_width);
        final int spacing = resources.getDimensionPixelSize(R.dimen.emoji_sticker_grid_view_spacing);

        setColumnWidth(width);
        setHorizontalSpacing(spacing);
        setVerticalSpacing(spacing);
        setPadding(spacing, spacing, spacing, spacing);
        setNumColumns(AUTO_FIT);
        setClipToPadding(false);
        setVerticalScrollBarEnabled(false);
    }

    public StickerGridView init(@NonNull StructGroupSticker mSticker, OnStickerListener onStickerListener, OnUpdateStickerListener onUpdateStickerListener) {
        stickerArrayAdapter = new StickerArrayAdapter(getContext(), mSticker.getStickers(), onStickerListener, onUpdateStickerListener);

        setAdapter(stickerArrayAdapter);
        return this;
    }

    public void onUpdateSticker(int updatePosition) {
        stickerArrayAdapter.onUpdateSticker(updatePosition);
    }
}
