package com.vanniktech.emoji.listeners;


import com.vanniktech.emoji.EmojiImageView;
import com.vanniktech.emoji.emoji.Emoji;

import androidx.annotation.NonNull;

public interface OnEmojiClickListener {
  void onEmojiClick(@NonNull EmojiImageView emoji, @NonNull Emoji imageView);
}
