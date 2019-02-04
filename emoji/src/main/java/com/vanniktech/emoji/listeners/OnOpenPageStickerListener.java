package com.vanniktech.emoji.listeners;

import com.vanniktech.emoji.sticker.struct.StructGroupSticker;

import java.util.ArrayList;

public interface OnOpenPageStickerListener {
  void addSticker(String page);
  void openSetting(ArrayList<StructGroupSticker> stickerList);
}
