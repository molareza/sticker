package com.vanniktech.emoji.sticker.listener;

import com.vanniktech.emoji.sticker.struct.StructItemSticker;

public interface OnUpdateStickerListener {
    void onUpdateSticker(StructItemSticker structItemSticker);

    void onUpdateRecentSticker();

    void onUpdateTabSticker(String token, String extention, long avatarSize, int positionAdapter);
}
