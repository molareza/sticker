package com.vanniktech.emoji.listeners;

import com.vanniktech.emoji.sticker.struct.StructAllSticker;

import java.util.ArrayList;

public interface OnOpenPageStickerListener {
  void addSticker(String page);
  void openSetting(String page);
}
