package com.vanniktech.emoji.googlecompat.category;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import com.vanniktech.emoji.googlecompat.GoogleCompatEmoji;
import com.vanniktech.emoji.emoji.Emoji;
import com.vanniktech.emoji.emoji.EmojiCategory;
import com.vanniktech.emoji.googlecompat.R;

@SuppressWarnings("PMD.MethodReturnsInternalArray") public final class FlagsCategory implements EmojiCategory {
  private static final Emoji[] DATA = new GoogleCompatEmoji[] {
    new GoogleCompatEmoji(0x1f3f3),
    new GoogleCompatEmoji(0x1f3f4),
    new GoogleCompatEmoji(0x1f3c1),
    new GoogleCompatEmoji(0x1f6a9),
    new GoogleCompatEmoji(new int[] { 0x1f3f3, 0xfe0f, 0x200d, 0x1f308 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1eb }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1fd }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1f1 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e9, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1f8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1e9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1f6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1f8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1ed }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1e9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1e7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1fe }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1ef }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1fb, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1eb }),
    new GoogleCompatEmoji(new int[] { 0x1f1e7, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1ed }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1e8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1fb }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1fe }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1eb }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1e9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1f1 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1fd }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1e8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1e9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1f0 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1ed, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1fa }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1fe }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1e9, 0x1f1f0 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e9, 0x1f1ef }),
    new GoogleCompatEmoji(new int[] { 0x1f1e9, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e9, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ea, 0x1f1e8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ea, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1fb }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1f6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ea, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ea, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1ea, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ea, 0x1f1fa }),
    new GoogleCompatEmoji(new int[] { 0x1f1eb, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1eb, 0x1f1ef }),
    new GoogleCompatEmoji(new int[] { 0x1f1eb, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1eb, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1eb }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1e9, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1ed }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1f1 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1e9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1fa }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1fe }),
    new GoogleCompatEmoji(new int[] { 0x1f1ed, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ed, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ed, 0x1f1f0 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ed, 0x1f1fa }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1f8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1e9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1f6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1f1 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ee, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ef, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ef, 0x1f1f5 }),
    new GoogleCompatEmoji(0x1f38c),
    new GoogleCompatEmoji(new int[] { 0x1f1ef, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1ef, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1fb }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1e7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1f8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1fe }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1fa }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1f0 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1fe }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1fb }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1f1 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1ed }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1fa }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1fd }),
    new GoogleCompatEmoji(new int[] { 0x1f1eb, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1e9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1e8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1f8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1f5 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1f1 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1fa }),
    new GoogleCompatEmoji(new int[] { 0x1f1f3, 0x1f1eb }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1f5 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f2, 0x1f1f5 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f4, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1f0 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1f8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1fe }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1ed }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1f1 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f5, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f6, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f7, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f7, 0x1f1fa }),
    new GoogleCompatEmoji(new int[] { 0x1f1f7, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1fc, 0x1f1f8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f7, 0x1f1f8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1e8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1f1 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1fd }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1f0 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1e7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ff, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1f8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1f0 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1ed }),
    new GoogleCompatEmoji(new int[] { 0x1f1f0, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f1, 0x1f1e8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1fb, 0x1f1e8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1e9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1e8, 0x1f1ed }),
    new GoogleCompatEmoji(new int[] { 0x1f1f8, 0x1f1fe }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1ef }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1ed }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1f1 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1f0 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1f4 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1f9 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1f7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1e8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1f9, 0x1f1fb }),
    new GoogleCompatEmoji(new int[] { 0x1f1fb, 0x1f1ee }),
    new GoogleCompatEmoji(new int[] { 0x1f1fa, 0x1f1ec }),
    new GoogleCompatEmoji(new int[] { 0x1f1fa, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1ec, 0x1f1e7 }),
    new GoogleCompatEmoji(new int[] { 0x1f1fa, 0x1f1fe }),
    new GoogleCompatEmoji(new int[] { 0x1f1fa, 0x1f1ff }),
    new GoogleCompatEmoji(new int[] { 0x1f1fb, 0x1f1fa }),
    new GoogleCompatEmoji(new int[] { 0x1f1fb, 0x1f1e6 }),
    new GoogleCompatEmoji(new int[] { 0x1f1fb, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1fb, 0x1f1f3 }),
    new GoogleCompatEmoji(new int[] { 0x1f1fe, 0x1f1ea }),
    new GoogleCompatEmoji(new int[] { 0x1f1ff, 0x1f1f2 }),
    new GoogleCompatEmoji(new int[] { 0x1f1ff, 0x1f1fc }),
    new GoogleCompatEmoji(new int[] { 0x1f1e6, 0x1f1e8 }),
    new GoogleCompatEmoji(new int[] { 0x1f1fa, 0x1f1f3 }),
  };

  @Override @NonNull public Emoji[] getEmojis() {
    return DATA;
  }

  @Override @DrawableRes public int getIcon() {
    return R.drawable.emoji_compat_category_flags;
  }
}
