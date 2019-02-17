package com.vanniktech.emoji.listeners;

import com.vanniktech.emoji.sticker.struct.StructGroupSticker;

import java.util.ArrayList;

public interface OnUpdateStickerListener {
    void onUpdateSticker(String token, String extention, long avatarSize, int positionAdapter);

    void onUpdateRecentSticker();

    void onUpdateTabSticker(String token, String extention, long avatarSize , int positionAdapter);
}
