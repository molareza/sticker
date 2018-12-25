package com.vanniktech.emoji.listeners;

import android.support.annotation.NonNull;
import com.vanniktech.emoji.EmojiImageView;
import com.vanniktech.emoji.emoji.Emoji;

public interface OnEmojiClickListener {
  void onEmojiClick(@NonNull EmojiImageView emoji, @NonNull Emoji imageView);
}
