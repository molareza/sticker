package com.vanniktech.emoji;


import android.support.annotation.NonNull;

import com.vanniktech.emoji.emoji.Emoji;
import com.vanniktech.emoji.emoji.EmojiCategory;

class StickerCategory implements EmojiCategory {
  @NonNull
  @Override
  public Emoji[] getEmojis() {
    return new Emoji[0];
  }

  @Override
  public int getIcon() {
    return 0;
  }

}
