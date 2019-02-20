package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.widget.GridView;

import com.vanniktech.emoji.R;
import com.vanniktech.emoji.sticker.struct.StructItemSticker;

import java.util.ArrayList;
import java.util.List;


final class RecentStickerGridView extends GridView {
    private List<StructItemSticker> recentSticker;
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

    public RecentStickerGridView init(OnStickerListener onStickerListener, List<StructItemSticker> recentSticker) {

        this.recentSticker = recentSticker;
        recentlyStickerArrayAdapter = new RecentlyStickerAdapter(getContext(), onStickerListener,recentSticker);
        setAdapter(recentlyStickerArrayAdapter);
        return this;
    }

    public void invalidateStrickers(ArrayList<StructItemSticker> recentlySticker) {
        recentlyStickerArrayAdapter.updateSticker(recentlySticker);
    }
}
