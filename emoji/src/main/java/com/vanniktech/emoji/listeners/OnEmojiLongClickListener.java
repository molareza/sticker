package com.vanniktech.emoji.listeners;


import com.vanniktech.emoji.EmojiImageView;
import com.vanniktech.emoji.emoji.Emoji;

import androidx.annotation.NonNull;

public interface OnEmojiLongClickListener {
  void onEmojiLongClick(@NonNull EmojiImageView view, @NonNull Emoji emoji);
}
