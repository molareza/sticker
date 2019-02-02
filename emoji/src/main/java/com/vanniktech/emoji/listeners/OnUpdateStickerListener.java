package com.vanniktech.emoji.listeners;

import com.vanniktech.emoji.sticker.struct.StructGroupSticker;

import java.util.ArrayList;

public interface OnUpdateStickerListener {
  void onUpdateStickerPath(ArrayList<StructGroupSticker> categoryStickerList);
}
