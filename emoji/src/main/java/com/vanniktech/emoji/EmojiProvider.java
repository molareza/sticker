package com.vanniktech.emoji;


import com.vanniktech.emoji.emoji.EmojiCategory;

import androidx.annotation.NonNull;

/**
 * Interface for a custom emoji implementation that can be used with {@link EmojiManager}.
 *
 * @since 0.4.0
 */
public interface EmojiProvider {
  /**
   * @return The Array of categories.
   * @since 0.4.0
   */
  @NonNull
  EmojiCategory[] getCategories();
}
