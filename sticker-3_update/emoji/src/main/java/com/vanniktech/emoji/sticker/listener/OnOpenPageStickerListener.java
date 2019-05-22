package com.vanniktech.emoji.sticker.listener;

import com.vanniktech.emoji.sticker.struct.StructGroupSticker;
import com.vanniktech.emoji.sticker.struct.StructItemSticker;

import java.util.ArrayList;

public interface OnOpenPageStickerListener {
  void addSticker(String page);
  void openSetting(ArrayList<StructGroupSticker> stickerList, ArrayList<StructItemSticker> recentStickerList);
}
