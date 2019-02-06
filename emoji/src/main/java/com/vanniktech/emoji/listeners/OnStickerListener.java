package com.vanniktech.emoji.listeners;

import com.vanniktech.emoji.sticker.struct.StructItemSticker;

import java.util.List;

public interface OnStickerListener {
  void onStickerPath(List<StructItemSticker> path);
}
