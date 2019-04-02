package com.vanniktech.emoji.<%= package %>;


import com.vanniktech.emoji.EmojiProvider;
import com.vanniktech.emoji.emoji.EmojiCategory;
<%= imports %>

public final class <%= name %>Provider implements EmojiProvider {
  @Override @NonNull public EmojiCategory[] getCategories() {
    return new EmojiCategory[] {
      <%= categories %>
    };
  }
}
