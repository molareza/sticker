package com.vanniktech.emoji.listeners;

import com.vanniktech.emoji.sticker.struct.StructGroupSticker;

import java.util.ArrayList;

public interface OnUpdateStickerListener {
    void onUpdateSticker(String token , int positionAdapter);

    void onUpdateRecentSticker();

    void onUpdateTabSticker(String token , int positionAdapter);
}
