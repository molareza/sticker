package com.vanniktech.emoji.listeners;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vanniktech.emoji.R;
import com.vanniktech.emoji.sticker.struct.StructGroupSticker;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    public SettingFragment() {
        // Required empty public constructor
    }

    private ArrayList<StructGroupSticker> allList;

    public static SettingFragment newInstance(ArrayList<StructGroupSticker> allList) {
        SettingFragment settingFragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putSerializable("ALL", allList);
        settingFragment.setArguments(args);
        return settingFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.emoji_fragment_setting, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allList = (ArrayList<StructGroupSticker>) getArguments().getSerializable("ALL");

        Log.i("CCCCC", "onViewCreated: " + allList.size());
        RecyclerView rcvSettingPage = view.findViewById(R.id.rcvSettingPage);
        AdapterSettingPage adapterSettingPage = new AdapterSettingPage(this, allList);
        rcvSettingPage.setAdapter(adapterSettingPage);
        rcvSettingPage.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvSettingPage.setHasFixedSize(true);
    }

    public class AdapterSettingPage extends RecyclerView.Adapter<AdapterSettingPage.ViewHolder> {
        private ArrayList<StructGroupSticker> mData;
        private SettingFragment context;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        AdapterSettingPage(SettingFragment context, ArrayList<StructGroupSticker> data) {
            this.mData = data;
            this.context = context;
            this.mInflater = LayoutInflater.from(context.getContext());
        }

        // inflates the row layout from xml when needed
        @Override
        public AdapterSettingPage.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.emoji_adapter_setting_page, parent, false);
            return new AdapterSettingPage.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(AdapterSettingPage.ViewHolder holder, int position) {
            StructGroupSticker item = mData.get(position);

            Glide.with(context)
                    .load(new File(item.getUri())) // Uri of the picture
                    .into(holder.imgSticker);
            holder.txtName.setText(item.getName());
            holder.txtCount.setText(item.getStickers().size() + " " + "Stickers");
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
                            builder = new AlertDialog.Builder(context.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(context.getContext());
                        }
                        builder.setTitle("Delete entry")
                                .setMessage("Are you sure you want to delete this entry?")
                                .setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (new File(mData.get(getAdapterPosition()).getName()).exists()) {



                                            mData.remove(getAdapterPosition());
                                            notifyDataSetChanged();
                                            dialog.dismiss();
                                        }
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
