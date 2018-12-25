package com.vanniktech.emoji.sticker;

import android.support.annotation.NonNull;

import com.vanniktech.emoji.emoji.Emoji;

import java.util.Collection;



public interface RecentSticker {

   Collection<String> getRecentSticker();

  void addSticker(String stickerPath);

  void persist();
}
