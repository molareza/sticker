package com.vanniktech.emoji;

import android.support.annotation.NonNull;
import com.vanniktech.emoji.emoji.EmojiCategory;

/**
 * Interface for custom implementation of an emoji providing class.
 */
public interface EmojiProvider {
    /**
     * Returns a collection of categories.
     *
     * @return The collection of categories.
     */
    @NonNull
    Iterable<EmojiCategory> getCategories();
}
