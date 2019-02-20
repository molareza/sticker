package com.vanniktech.emoji.sticker;

public interface OnUpdateStickerListener {
    void onUpdateSticker(String token, String extention, long avatarSize, int positionAdapter);

    void onUpdateRecentSticker();

    void onUpdateTabSticker(String token, String extention, long avatarSize, int positionAdapter);
}
