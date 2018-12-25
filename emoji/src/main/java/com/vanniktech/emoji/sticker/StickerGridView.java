package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.vanniktech.emoji.EmojiGridView;
import com.vanniktech.emoji.R;

import java.util.ArrayList;

final class StickerGridView extends EmojiGridView {
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

    public StickerGridView init(@NonNull ArrayList<String> mSticker) {
        StickerArrayAdapter stickerArrayAdapter = new StickerArrayAdapter(getContext(), mSticker);
        setAdapter(stickerArrayAdapter);
        return this;
    }
}
