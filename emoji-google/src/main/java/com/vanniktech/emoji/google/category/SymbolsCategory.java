package com.vanniktech.emoji.google.category;


import com.vanniktech.emoji.emoji.EmojiCategory;
import com.vanniktech.emoji.google.GoogleEmoji;
import com.vanniktech.emoji.google.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

@SuppressWarnings("PMD.MethodReturnsInternalArray") public final class SymbolsCategory implements EmojiCategory {
  private static final GoogleEmoji[] DATA = new GoogleEmoji[] {
    new GoogleEmoji(0x1F3E7, 12, 4, false),
    new GoogleEmoji(0x1F6AE, 35, 19, false),
    new GoogleEmoji(0x1F6B0, 35, 21, false),
    new GoogleEmoji(0x267F, 48, 10, false),
    new GoogleEmoji(0x1F6B9, 36, 29, false),
    new GoogleEmoji(0x1F6BA, 36, 30, false),
    new GoogleEmoji(0x1F6BB, 36, 31, false),
    new GoogleEmoji(0x1F6BC, 36, 32, false),
    new GoogleEmoji(0x1F6BE, 36, 34, false),
    new GoogleEmoji(0x1F6C2, 36, 43, false),
    new GoogleEmoji(0x1F6C3, 36, 44, false),
    new GoogleEmoji(0x1F6C4, 36, 45, false),
    new GoogleEmoji(0x1F6C5, 36, 46, false),
    new GoogleEmoji(new int[] { 0x26A0, 0xFE0F }, 48, 20, false),
    new GoogleEmoji(0x1F6B8, 36, 28, false),
    new GoogleEmoji(0x26D4, 48, 35, false),
    new GoogleEmoji(0x1F6AB, 35, 16, false),
    new GoogleEmoji(0x1F6B3, 35, 24, false),
    new GoogleEmoji(0x1F6AD, 35, 18, false),
    new GoogleEmoji(0x1F6AF, 35, 20, false),
    new GoogleEmoji(0x1F6B1, 35, 22, false),
    new GoogleEmoji(0x1F6B7, 36, 27, false),
    new GoogleEmoji(0x1F4F5, 26, 44, false),
    new GoogleEmoji(0x1F51E, 27, 32, false),
    new GoogleEmoji(new int[] { 0x2622, 0xFE0F }, 47, 33, false),
    new GoogleEmoji(new int[] { 0x2623, 0xFE0F }, 47, 34, false),
    new GoogleEmoji(new int[] { 0x2B06, 0xFE0F }, 50, 18, false),
    new GoogleEmoji(new int[] { 0x2197, 0xFE0F }, 46, 36, false),
    new GoogleEmoji(new int[] { 0x27A1, 0xFE0F }, 50, 12, false),
    new GoogleEmoji(new int[] { 0x2198, 0xFE0F }, 46, 37, false),
    new GoogleEmoji(new int[] { 0x2B07, 0xFE0F }, 50, 19, false),
    new GoogleEmoji(new int[] { 0x2199, 0xFE0F }, 46, 38, false),
    new GoogleEmoji(new int[] { 0x2B05, 0xFE0F }, 50, 17, false),
    new GoogleEmoji(new int[] { 0x2196, 0xFE0F }, 46, 35, false),
    new GoogleEmoji(new int[] { 0x2195, 0xFE0F }, 46, 34, false),
    new GoogleEmoji(new int[] { 0x2194, 0xFE0F }, 46, 33, false),
    new GoogleEmoji(new int[] { 0x21A9, 0xFE0F }, 46, 39, false),
    new GoogleEmoji(new int[] { 0x21AA, 0xFE0F }, 46, 40, false),
    new GoogleEmoji(new int[] { 0x2934, 0xFE0F }, 50, 15, false),
    new GoogleEmoji(new int[] { 0x2935, 0xFE0F }, 50, 16, false),
    new GoogleEmoji(0x1F503, 27, 5, false),
    new GoogleEmoji(0x1F504, 27, 6, false),
    new GoogleEmoji(0x1F519, 27, 27, false),
    new GoogleEmoji(0x1F51A, 27, 28, false),
    new GoogleEmoji(0x1F51B, 27, 29, false),
    new GoogleEmoji(0x1F51C, 27, 30, false),
    new GoogleEmoji(0x1F51D, 27, 31, false),
    new GoogleEmoji(0x1F6D0, 37, 5, false),
    new GoogleEmoji(new int[] { 0x269B, 0xFE0F }, 48, 18, false),
    new GoogleEmoji(new int[] { 0x1F549, 0xFE0F }, 28, 12, false),
    new GoogleEmoji(new int[] { 0x2721, 0xFE0F }, 49, 47, false),
    new GoogleEmoji(new int[] { 0x2638, 0xFE0F }, 47, 39, false),
    new GoogleEmoji(new int[] { 0x262F, 0xFE0F }, 47, 38, false),
    new GoogleEmoji(new int[] { 0x271D, 0xFE0F }, 49, 46, false),
    new GoogleEmoji(new int[] { 0x2626, 0xFE0F }, 47, 35, false),
    new GoogleEmoji(new int[] { 0x262A, 0xFE0F }, 47, 36, false),
    new GoogleEmoji(new int[] { 0x262E, 0xFE0F }, 47, 37, false),
    new GoogleEmoji(0x1F54E, 28, 17, false),
    new GoogleEmoji(0x1F52F, 27, 49, false),
    new GoogleEmoji(0x2648, 47, 44, false),
    new GoogleEmoji(0x2649, 47, 45, false),
    new GoogleEmoji(0x264A, 47, 46, false),
    new GoogleEmoji(0x264B, 47, 47, false),
    new GoogleEmoji(0x264C, 47, 48, false),
    new GoogleEmoji(0x264D, 47, 49, false),
    new GoogleEmoji(0x264E, 47, 50, false),
    new GoogleEmoji(0x264F, 47, 51, false),
    new GoogleEmoji(0x2650, 48, 0, false),
    new GoogleEmoji(0x2651, 48, 1, false),
    new GoogleEmoji(0x2652, 48, 2, false),
    new GoogleEmoji(0x2653, 48, 3, false),
    new GoogleEmoji(0x26CE, 48, 31, false),
    new GoogleEmoji(0x1F500, 27, 2, false),
    new GoogleEmoji(0x1F501, 27, 3, false),
    new GoogleEmoji(0x1F502, 27, 4, false),
    new GoogleEmoji(new int[] { 0x25B6, 0xFE0F }, 47, 10, false),
    new GoogleEmoji(0x23E9, 46, 45, false),
    new GoogleEmoji(new int[] { 0x23ED, 0xFE0F }, 46, 49, false),
    new GoogleEmoji(new int[] { 0x23EF, 0xFE0F }, 46, 51, false),
    new GoogleEmoji(new int[] { 0x25C0, 0xFE0F }, 47, 11, false),
    new GoogleEmoji(0x23EA, 46, 46, false),
    new GoogleEmoji(new int[] { 0x23EE, 0xFE0F }, 46, 50, false),
    new GoogleEmoji(0x1F53C, 28, 10, false),
    new GoogleEmoji(0x23EB, 46, 47, false),
    new GoogleEmoji(0x1F53D, 28, 11, false),
    new GoogleEmoji(0x23EC, 46, 48, false),
    new GoogleEmoji(new int[] { 0x23F8, 0xFE0F }, 47, 4, false),
    new GoogleEmoji(new int[] { 0x23F9, 0xFE0F }, 47, 5, false),
    new GoogleEmoji(new int[] { 0x23FA, 0xFE0F }, 47, 6, false),
    new GoogleEmoji(new int[] { 0x23CF, 0xFE0F }, 46, 44, false),
    new GoogleEmoji(0x1F3A6, 9, 0, false),
    new GoogleEmoji(0x1F505, 27, 7, false),
    new GoogleEmoji(0x1F506, 27, 8, false),
    new GoogleEmoji(0x1F4F6, 26, 45, false),
    new GoogleEmoji(0x1F4F3, 26, 42, false),
    new GoogleEmoji(0x1F4F4, 26, 43, false),
    new GoogleEmoji(new int[] { 0x2640, 0xFE0F }, 47, 42, false),
    new GoogleEmoji(new int[] { 0x2642, 0xFE0F }, 47, 43, false),
    new GoogleEmoji(new int[] { 0x2695, 0xFE0F }, 48, 14, false),
    new GoogleEmoji(new int[] { 0x267B, 0xFE0F }, 48, 9, false),
    new GoogleEmoji(new int[] { 0x269C, 0xFE0F }, 48, 19, false),
    new GoogleEmoji(0x1F531, 27, 51, false),
    new GoogleEmoji(0x1F4DB, 26, 18, false),
    new GoogleEmoji(0x1F530, 27, 50, false),
    new GoogleEmoji(0x2B55, 50, 23, false),
    new GoogleEmoji(0x2705, 49, 15, false),
    new GoogleEmoji(new int[] { 0x2611, 0xFE0F }, 47, 22, false),
    new GoogleEmoji(new int[] { 0x2714, 0xFE0F }, 49, 44, false),
    new GoogleEmoji(new int[] { 0x2716, 0xFE0F }, 49, 45, false),
    new GoogleEmoji(0x274C, 50, 1, false),
    new GoogleEmoji(0x274E, 50, 2, false),
    new GoogleEmoji(0x2795, 50, 9, false),
    new GoogleEmoji(0x2796, 50, 10, false),
    new GoogleEmoji(0x2797, 50, 11, false),
    new GoogleEmoji(0x27B0, 50, 13, false),
    new GoogleEmoji(0x27BF, 50, 14, false),
    new GoogleEmoji(new int[] { 0x303D, 0xFE0F }, 50, 25, false),
    new GoogleEmoji(new int[] { 0x2733, 0xFE0F }, 49, 49, false),
    new GoogleEmoji(new int[] { 0x2734, 0xFE0F }, 49, 50, false),
    new GoogleEmoji(new int[] { 0x2747, 0xFE0F }, 50, 0, false),
    new GoogleEmoji(new int[] { 0x203C, 0xFE0F }, 46, 29, false),
    new GoogleEmoji(new int[] { 0x2049, 0xFE0F }, 46, 30, false),
    new GoogleEmoji(0x2753, 50, 3, false),
    new GoogleEmoji(0x2754, 50, 4, false),
    new GoogleEmoji(0x2755, 50, 5, false),
    new GoogleEmoji(0x2757, 50, 6, false),
    new GoogleEmoji(new int[] { 0x3030, 0xFE0F }, 50, 24, false),
    new GoogleEmoji(new int[] { 0x00A9, 0xFE0F }, 0, 12, false),
    new GoogleEmoji(new int[] { 0x00AE, 0xFE0F }, 0, 13, false),
    new GoogleEmoji(new int[] { 0x2122, 0xFE0F }, 46, 31, false),
    new GoogleEmoji(new int[] { 0x0023, 0xFE0F, 0x20E3 }, 0, 0, false),
    new GoogleEmoji(new int[] { 0x002A, 0xFE0F, 0x20E3 }, 0, 1, false),
    new GoogleEmoji(new int[] { 0x0030, 0xFE0F, 0x20E3 }, 0, 2, false),
    new GoogleEmoji(new int[] { 0x0031, 0xFE0F, 0x20E3 }, 0, 3, false),
    new GoogleEmoji(new int[] { 0x0032, 0xFE0F, 0x20E3 }, 0, 4, false),
    new GoogleEmoji(new int[] { 0x0033, 0xFE0F, 0x20E3 }, 0, 5, false),
    new GoogleEmoji(new int[] { 0x0034, 0xFE0F, 0x20E3 }, 0, 6, false),
    new GoogleEmoji(new int[] { 0x0035, 0xFE0F, 0x20E3 }, 0, 7, false),
    new GoogleEmoji(new int[] { 0x0036, 0xFE0F, 0x20E3 }, 0, 8, false),
    new GoogleEmoji(new int[] { 0x0037, 0xFE0F, 0x20E3 }, 0, 9, false),
    new GoogleEmoji(new int[] { 0x0038, 0xFE0F, 0x20E3 }, 0, 10, false),
    new GoogleEmoji(new int[] { 0x0039, 0xFE0F, 0x20E3 }, 0, 11, false),
    new GoogleEmoji(0x1F51F, 27, 33, false),
    new GoogleEmoji(0x1F4AF, 25, 26, false),
    new GoogleEmoji(0x1F520, 27, 34, false),
    new GoogleEmoji(0x1F521, 27, 35, false),
    new GoogleEmoji(0x1F522, 27, 36, false),
    new GoogleEmoji(0x1F523, 27, 37, false),
    new GoogleEmoji(0x1F524, 27, 38, false),
    new GoogleEmoji(new int[] { 0x1F170, 0xFE0F }, 0, 16, false),
    new GoogleEmoji(0x1F18E, 0, 20, false),
    new GoogleEmoji(new int[] { 0x1F171, 0xFE0F }, 0, 17, false),
    new GoogleEmoji(0x1F191, 0, 21, false),
    new GoogleEmoji(0x1F192, 0, 22, false),
    new GoogleEmoji(0x1F193, 0, 23, false),
    new GoogleEmoji(new int[] { 0x2139, 0xFE0F }, 46, 32, false),
    new GoogleEmoji(0x1F194, 0, 24, false),
    new GoogleEmoji(new int[] { 0x24C2, 0xFE0F }, 47, 7, false),
    new GoogleEmoji(0x1F195, 0, 25, false),
    new GoogleEmoji(0x1F196, 0, 26, false),
    new GoogleEmoji(new int[] { 0x1F17E, 0xFE0F }, 0, 18, false),
    new GoogleEmoji(0x1F197, 0, 27, false),
    new GoogleEmoji(new int[] { 0x1F17F, 0xFE0F }, 0, 19, false),
    new GoogleEmoji(0x1F198, 0, 28, false),
    new GoogleEmoji(0x1F199, 0, 29, false),
    new GoogleEmoji(0x1F19A, 0, 30, false),
    new GoogleEmoji(0x1F201, 5, 29, false),
    new GoogleEmoji(new int[] { 0x1F202, 0xFE0F }, 5, 30, false),
    new GoogleEmoji(new int[] { 0x1F237, 0xFE0F }, 5, 38, false),
    new GoogleEmoji(0x1F236, 5, 37, false),
    new GoogleEmoji(0x1F22F, 5, 32, false),
    new GoogleEmoji(0x1F250, 5, 42, false),
    new GoogleEmoji(0x1F239, 5, 40, false),
    new GoogleEmoji(0x1F21A, 5, 31, false),
    new GoogleEmoji(0x1F232, 5, 33, false),
    new GoogleEmoji(0x1F251, 5, 43, false),
    new GoogleEmoji(0x1F238, 5, 39, false),
    new GoogleEmoji(0x1F234, 5, 35, false),
    new GoogleEmoji(0x1F233, 5, 34, false),
    new GoogleEmoji(new int[] { 0x3297, 0xFE0F }, 50, 26, false),
    new GoogleEmoji(new int[] { 0x3299, 0xFE0F }, 50, 27, false),
    new GoogleEmoji(0x1F23A, 5, 41, false),
    new GoogleEmoji(0x1F235, 5, 36, false),
    new GoogleEmoji(new int[] { 0x25AA, 0xFE0F }, 47, 8, false),
    new GoogleEmoji(new int[] { 0x25AB, 0xFE0F }, 47, 9, false),
    new GoogleEmoji(new int[] { 0x25FB, 0xFE0F }, 47, 12, false),
    new GoogleEmoji(new int[] { 0x25FC, 0xFE0F }, 47, 13, false),
    new GoogleEmoji(0x25FD, 47, 14, false),
    new GoogleEmoji(0x25FE, 47, 15, false),
    new GoogleEmoji(0x2B1B, 50, 20, false),
    new GoogleEmoji(0x2B1C, 50, 21, false),
    new GoogleEmoji(0x1F536, 28, 4, false),
    new GoogleEmoji(0x1F537, 28, 5, false),
    new GoogleEmoji(0x1F538, 28, 6, false),
    new GoogleEmoji(0x1F539, 28, 7, false),
    new GoogleEmoji(0x1F53A, 28, 8, false),
    new GoogleEmoji(0x1F53B, 28, 9, false),
    new GoogleEmoji(0x1F4A0, 25, 6, false),
    new GoogleEmoji(0x1F518, 27, 26, false),
    new GoogleEmoji(0x1F532, 28, 0, false),
    new GoogleEmoji(0x1F533, 28, 1, false),
    new GoogleEmoji(0x26AA, 48, 22, false),
    new GoogleEmoji(0x26AB, 48, 23, false),
    new GoogleEmoji(0x1F534, 28, 2, false),
    new GoogleEmoji(0x1F535, 28, 3, false)
  };

  @Override @NonNull
  public GoogleEmoji[] getEmojis() {
    return DATA;
  }

  @Override @DrawableRes
  public int getIcon() {
    return R.drawable.emoji_google_category_symbols;
  }
}
