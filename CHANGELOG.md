# Change Log

Version 0.4.0 *(2017-02-15)*
----------------------------

- Soft keyboard not detected when toggling the emoji-popup [\#60](https://github.com/vanniktech/Emoji/issues/60)
- Can't show keybord [\#58](https://github.com/vanniktech/Emoji/issues/58)
- Opening emoticons, and change the keybord [\#57](https://github.com/vanniktech/Emoji/issues/57)
- On android 6 emoji not averlays keyboard [\#20](https://github.com/vanniktech/Emoji/issues/20)
- Optimize EmojiGridView hierachy [\#39](https://github.com/vanniktech/Emoji/pull/39) ([vanniktech](https://github.com/vanniktech))
- Split v4 [\#49](https://github.com/vanniktech/Emoji/pull/49) ([vanniktech](https://github.com/vanniktech))
- Make colors customizable [\#70](https://github.com/vanniktech/Emoji/pull/70) ([rubengees](https://github.com/rubengees))
- Rewrite for more Emojis, modularity and performance [\#77](https://github.com/vanniktech/Emoji/pull/77) ([rubengees](https://github.com/rubengees))

Huge thanks to [rubengees](https://github.com/rubengees) for making this library able to support multiple Emojis ([iOS](https://github.com/vanniktech/Emoji#ios-emojis) & [Emoji One](https://github.com/vanniktech/Emoji#emojione)) as well as fixing those issues:

- Skin tones support [\#34](https://github.com/vanniktech/Emoji/issues/34)
- Add flags [\#12](https://github.com/vanniktech/Emoji/issues/12)
- Add missing Symbols [\#11](https://github.com/vanniktech/Emoji/issues/11)
- Add missing People emojis [\#10](https://github.com/vanniktech/Emoji/issues/10)

**Note:**

0.4.0 is a breaking change so please consult with the README in order to set it up correctly. If you want to continue using the iOS Emojis change this:

```diff
-compile 'com.vanniktech:emoji:0.4.0'
+compile 'com.vanniktech:emoji-ios:0.4.0'
```

and add `EmojiManager.install(new IosEmojiProvider());` in your Applications `onCreate()` method.

Version 0.3.0 *(2016-05-03)*
----------------------------

- Remove Global Layout listener when dismissing the popup. Fixes \#22 [\#24](https://github.com/vanniktech/Emoji/pull/24) ([vanniktech](https://github.com/vanniktech))
- Show People category first when no recent emojis are present [\#16](https://github.com/vanniktech/Emoji/pull/16) ([vanniktech](https://github.com/vanniktech))

Version 0.2.0 *(2016-03-29)*
----------------------------

- Add Recent Emojis Tab [\#13](https://github.com/vanniktech/Emoji/pull/13) ([vanniktech](https://github.com/vanniktech))
- Adding new emojis [\#9](https://github.com/vanniktech/Emoji/pull/9) ([vanniktech](https://github.com/vanniktech))
- Add Library resource config file. Fixes \#6 [\#7](https://github.com/vanniktech/Emoji/pull/7) ([vanniktech](https://github.com/vanniktech))

Version 0.1.0 *(2016-03-12)*
----------------------------

- Initial release