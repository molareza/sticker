package com.vanniktech.emoji.listeners;

import com.vanniktech.emoji.sticker.struct.StructAllSticker;

import java.util.ArrayList;

public interface OnUpdateStickerListener {
  ArrayList<StructAllSticker> onUpdateStickerPath(ArrayList<StructAllSticker> categoryStickerList);
}
