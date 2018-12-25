package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.RecentEmoji;
import com.vanniktech.emoji.RecentEmojiManager;
import com.vanniktech.emoji.emoji.Emoji;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public final class RecentStickeriManager implements RecentSticker {
    private static final String PREFERENCE_NAME = "sticker-recent-manager";
    private static final String TIME_DELIMITER = ";";
    private static final String EMOJI_DELIMITER = "~";
    private static final String RECENT_STICKER = "recent-sticker";
    static final int EMOJI_GUESS_SIZE = 5;
    static final int MAX_RECENTS = 40;
    @NonNull
    private final Context context;
    @NonNull
    private EmojiList stickeriList = new EmojiList(0);

    public RecentStickeriManager(@NonNull final Context context) {
        this.context = context.getApplicationContext();
    }


    @SuppressWarnings({"PMD.AvoidDeeplyNestedIfStmts", "checkstyle:nestedifdepth"})
    @NonNull
    @Override
    public Collection<String> getRecentSticker() {
        if (stickeriList.size() == 0) {
            final String savedRecentEmojis = getPreferences().getString(RECENT_STICKER, "");

            if (savedRecentEmojis.length() > 0) {
                final StringTokenizer stringTokenizer = new StringTokenizer(savedRecentEmojis, EMOJI_DELIMITER);
                stickeriList = new EmojiList(stringTokenizer.countTokens());

                while (stringTokenizer.hasMoreTokens()) {
                    final String token = stringTokenizer.nextToken();

                    final String[] parts = token.split(TIME_DELIMITER);

                    if (parts.length == 2) {
                        final String sticker = parts[0];

                        if (sticker != null && sticker.length() == parts[0].length()) {
                            final long timestamp = Long.parseLong(parts[1]);

                            stickeriList.add(sticker, timestamp);
                        }
                    }
                }
            } else {
                stickeriList = new EmojiList(0);
            }
        }

        return stickeriList.getEmojis();
    }


    @Override
    public void addSticker(String stickerPath) {
        stickeriList.add(stickerPath);
    }

    @Override
    public void persist() {

        if (stickeriList.size() > 0) {
            final StringBuilder stringBuilder = new StringBuilder(stickeriList.size() * EMOJI_GUESS_SIZE);

            for (int i = 0; i < stickeriList.size(); i++) {
                final Data data = stickeriList.get(i);
                stringBuilder.append(data.sticker)
                        .append(TIME_DELIMITER)
                        .append(data.timestamp)
                        .append(EMOJI_DELIMITER);
            }

            stringBuilder.setLength(stringBuilder.length() - EMOJI_DELIMITER.length());

            getPreferences().edit().putString(RECENT_STICKER, stringBuilder.toString()).apply();
        }
    }

    private SharedPreferences getPreferences() {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    static class EmojiList {
        static final Comparator<Data> COMPARATOR = new Comparator<Data>() {
            @Override
            public int compare(final Data lhs, final Data rhs) {
                return Long.valueOf(rhs.timestamp).compareTo(lhs.timestamp);
            }
        };

        @NonNull
        final List<Data> stickers;

        EmojiList(final int size) {
            stickers = new ArrayList<>(size);
        }

        void add(final String stricker) {
            add(stricker, System.currentTimeMillis());
        }

        void add(final String sticker, final long timestamp) {
            final Iterator<Data> iterator = stickers.iterator();

            final String emojiBase = sticker;

            while (iterator.hasNext()) {
                final Data data = iterator.next();
                if (data.sticker.equals(emojiBase)) { // Do the comparison by base so that skin tones are only saved once.
                    iterator.remove();
                }
            }

            stickers.add(0, new Data(sticker, timestamp));

            if (stickers.size() > MAX_RECENTS) {
                stickers.remove(MAX_RECENTS);
            }
        }

        Collection<String> getEmojis() {
            Collections.sort(stickers, COMPARATOR);

            final Collection<String> sortedEmojis = new ArrayList<>(stickers.size());

            for (final Data data : stickers) {
                sortedEmojis.add(data.sticker);
            }

            return sortedEmojis;
        }

        int size() {
            return stickers.size();
        }

        Data get(final int index) {
            return stickers.get(index);
        }
    }

    static class Data {
        final String sticker;
        final long timestamp;

        Data(final String emoji, final long timestamp) {
            this.sticker = emoji;
            this.timestamp = timestamp;
        }
    }
}
