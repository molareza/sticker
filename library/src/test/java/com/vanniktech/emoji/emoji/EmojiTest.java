package com.vanniktech.emoji.emoji;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

@SuppressWarnings("checkstyle:magicnumber")
public class EmojiTest {
    @Test
    public void fromCodePoint() {
        final Emoji emoji = Emoji.fromCodePoint(0x1f600);
        assertThat(emoji.getEmoji()).isEqualTo("😀");
    }

    @Test
    public void fromChar() {
        final Emoji emoji = Emoji.fromChar((char) 0x2708);
        assertThat(emoji.getEmoji()).isEqualTo("✈");
    }

    @Test
    public void getEmoji() {
        final Emoji emoji = new Emoji("nik");
        assertThat(emoji.getEmoji()).isEqualTo("nik");
    }

    @Test
    public void hash() {
        final Emoji nik = new Emoji("nik");
        final Emoji test = new Emoji("test");

        assertThat(nik.hashCode()).isEqualTo(new Emoji("nik").hashCode());
        assertThat(test.hashCode()).isEqualTo(new Emoji("test").hashCode());
        assertThat(nik.hashCode()).isNotEqualTo(test.hashCode());
    }

    @Test
    public void equality() {
        final Emoji nik = new Emoji("nik");
        final Emoji test = new Emoji("test");

        assertThat(nik).isEqualTo(new Emoji("nik"));
        assertThat(test).isEqualTo(new Emoji("test"));
        assertThat(nik).isNotEqualTo(test);
    }
}
