package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vanniktech.emoji.EmojiGridView;
import com.vanniktech.emoji.RecentEmoji;
import com.vanniktech.emoji.listeners.OnEmojiClickListener;
import com.vanniktech.emoji.listeners.OnEmojiLongClickListener;

import java.util.Collection;

final class RecentStickerGridView extends EmojiGridView {
  private RecentEmoji recentEmojis;

  RecentStickerGridView(@NonNull final Context context) {
    super(context);
  }

  public RecentStickerGridView init() {


    return this;
  }
}
