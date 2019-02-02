package com.vanniktech.emoji.sticker;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vanniktech.emoji.R;
import com.vanniktech.emoji.sticker.struct.StructAllSticker;

import java.io.File;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class EmojiSettingPage extends AppCompatActivity {

    public static final String DIR_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String emoji = "/emoji";
    public static String DIR_APP = DIR_SDCARD + emoji;
    private ArrayList<StructAllSticker> stickerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emoji_activity_main);

//        ArrayList<StructAllSticker> stickerList = StickerEmojiView.getStickerDatabase(this).getAllCategory();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            stickerList = (ArrayList<StructAllSticker>) bundle.getSerializable("ALL");
        }

        stickerList.remove(0);
        RecyclerView rcvSettingPage = findViewById(R.id.rcvSettingPage);
        AdapterSettingPage adapterSettingPage = new AdapterSettingPage(this, stickerList);
        rcvSettingPage.setAdapter(adapterSettingPage);
        rcvSettingPage.setLayoutManager(new LinearLayoutManager(this));
        rcvSettingPage.setHasFixedSize(true);
    }

    public class AdapterSettingPage extends RecyclerView.Adapter<AdapterSettingPage.ViewHolder> {
        private ArrayList<StructAllSticker> mData;
        private Context context;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        AdapterSettingPage(Context context, ArrayList<StructAllSticker> data) {
            this.mData = data;
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
        }

        // inflates the row layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.emoji_adapter_setting_page, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            StructAllSticker item = mData.get(position);

            if (item.getUrl() == null) return;

            Glide.with(context)
                    .load(new File(item.getUrl())) // Uri of the picture
                    .into(holder.imgSticker);
            holder.txtName.setText(item.getName());
            holder.txtCount.setText(item.getStructItemStickers().size() + " " + "Stickers");
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }

        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgSticker;
            TextView txtRemove;
            TextView txtName;
            TextView txtCount;

            ViewHolder(View itemView) {
                super(itemView);
                imgSticker = itemView.findViewById(R.id.imgSticker);
                txtRemove = itemView.findViewById(R.id.txtRemoveSticker);
                txtName = itemView.findViewById(R.id.txtName);
                txtCount = itemView.findViewById(R.id.txtCount);

                txtRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(context);
                        }
                        builder.setTitle("Delete entry")
                                .setMessage("Are you sure you want to delete this entry?")
                                .setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//
                                        StickerEmojiView.getStickerDatabase(EmojiSettingPage.this).favoriteDeleteGroup(mData.get(getAdapterPosition()).getId());
                                        StickerEmojiView.getStickerDatabase(EmojiSettingPage.this).favoriteDeleteSticker(mData.get(getAdapterPosition()).getId());
                                        StickerEmojiView.onNotifyList.notifyList(getAdapterPosition());
                                        dialog.dismiss();
//
                                    }
                                })
                                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

                    }
                });
            }
        }
    }
}