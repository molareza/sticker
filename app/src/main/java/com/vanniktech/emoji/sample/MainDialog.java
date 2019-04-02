package com.vanniktech.emoji.sample;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiPopup;

// We don't care about duplicated code in the sample.
@SuppressWarnings("CPD-START") public class MainDialog extends DialogFragment {
  static final String FRAGMENT_MANAGER_TAG = "dialog_main";
  static final String TAG = "MainDialog";

  ChatAdapter chatAdapter;
  EmojiPopup emojiPopup;

  EmojiEditText editText;
  ViewGroup rootView;
  ImageView emojiButton;

  public static void show(@NonNull final AppCompatActivity activity) {
    new MainDialog().show(activity.getSupportFragmentManager(), FRAGMENT_MANAGER_TAG);
  }

  @Override public void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    chatAdapter = new ChatAdapter();
  }

  @Override public void onStop() {
    if (emojiPopup != null) {
      emojiPopup.dismiss();
    }

    super.onStop();
  }

  @Override @NonNull public Dialog onCreateDialog(final Bundle savedInstanceState) {
    return new AlertDialog.Builder(getContext())
            .setView(buildView())
            .create();
  }

  private View buildView() {
    final View result = View.inflate(getContext(), R.layout.dialog_main, null);

    editText = result.findViewById(R.id.main_dialog_chat_bottom_message_edittext);
    rootView = result.findViewById(R.id.main_dialog_root_view);
    emojiButton = result.findViewById(R.id.main_dialog_emoji);
    final ImageView sendButton = result.findViewById(R.id.main_dialog_send);

    emojiButton.setColorFilter(ContextCompat.getColor(getContext(), R.color.emoji_icons), PorterDuff.Mode.SRC_IN);
    sendButton.setColorFilter(ContextCompat.getColor(getContext(), R.color.emoji_icons), PorterDuff.Mode.SRC_IN);

    emojiButton.setOnClickListener(ignore -> emojiPopup.toggle());
    sendButton.setOnClickListener(ignore -> {
      final String text = editText.getText().toString().trim();

      if (text.length() > 0) {
        chatAdapter.add(text);

        editText.setText("");
      }
    });

    final RecyclerView recyclerView = result.findViewById(R.id.main_dialog_recycler_view);
    recyclerView.setAdapter(chatAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

    setUpEmojiPopup();

    return rootView;
  }

  private void setUpEmojiPopup() {
    emojiPopup = EmojiPopup.Builder.fromRootView(rootView)
            .setOnEmojiBackspaceClickListener(ignore -> Log.d(TAG, "Clicked on Backspace"))
            .setOnEmojiClickListener((ignore, ignore2) -> Log.d(TAG, "Clicked on emoji"))
            .setOnEmojiPopupShownListener(() -> emojiButton.setImageResource(R.drawable.ic_keyboard))
            .setOnSoftKeyboardOpenListener(ignore -> Log.d(TAG, "Opened soft keyboard"))
            .setOnEmojiPopupDismissListener(() -> emojiButton.setImageResource(R.drawable.emoji_ios_category_smileysandpeople))
            .setOnSoftKeyboardCloseListener(() -> Log.d(TAG, "Closed soft keyboard"))
            .build(editText);
  }
}
