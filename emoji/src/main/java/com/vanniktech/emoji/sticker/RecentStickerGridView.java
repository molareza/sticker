package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.widget.GridView;

import com.vanniktech.emoji.R;
import com.vanniktech.emoji.listeners.OnStickerListener;

import java.util.ArrayList;


final class RecentStickerGridView extends GridView {
    private RecentSticker recentSticker;
    StickerArrayAdapter stickerArrayAdapter;

    RecentStickerGridView(@NonNull final Context context) {
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

    public RecentStickerGridView init(OnStickerListener onStickerListener, RecentSticker recentSticker) {

        this.recentSticker = recentSticker;
        stickerArrayAdapter = new StickerArrayAdapter(getContext(), (ArrayList<String>) recentSticker.getRecentSticker(), onStickerListener, recentSticker);
        setAdapter(stickerArrayAdapter);
        return this;
    }

    public void invalidateStrickers() {
        stickerArrayAdapter.updateSticker(recentSticker.getRecentSticker());
    }
}
