package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.widget.GridView;

import com.vanniktech.emoji.R;
import com.vanniktech.emoji.listeners.OnStickerListener;

import java.util.ArrayList;


final class RecentStickerGridView extends GridView {
    private ArrayList<StructRecentSticker> recentSticker;
    private RecentlyStickerAdapter recentlyStickerArrayAdapter;

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

    public RecentStickerGridView init(OnStickerListener onStickerListener, ArrayList<StructRecentSticker> recentSticker) {

        this.recentSticker = recentSticker;
        recentlyStickerArrayAdapter = new RecentlyStickerAdapter(getContext(), onStickerListener,recentSticker);
        setAdapter(recentlyStickerArrayAdapter);
        return this;
    }

    public void invalidateStrickers(ArrayList<StructRecentSticker> recentlySticker) {
        recentlyStickerArrayAdapter.updateSticker(recentlySticker);
    }
}
