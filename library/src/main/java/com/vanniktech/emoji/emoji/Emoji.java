package com.vanniktech.emoji.emoji;

import java.io.Serializable;

public final class Emoji implements Serializable {
    private static final long serialVersionUID = 1L;

    public static Emoji fromCodePoint(final int codePoint) {
        return new Emoji(newString(codePoint));
    }

    public static Emoji fromChar(final char ch) {
        return new Emoji(Character.toString(ch));
    }

    private static String newString(final int codePoint) {
        if (Character.charCount(codePoint) == 1) {
            return String.valueOf(codePoint);
        } else {
            return new String(Character.toChars(codePoint));
        }
    }

    private final String emoji;

    private Emoji(final String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
