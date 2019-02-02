package com.vanniktech.emoji.sticker.struct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class StructAllSticker implements Serializable {

    private String id;
    private String name;
    private String avatarToken;
    private String url;
    private ArrayList<StructItemSticker> structItemStickers;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarToken() {
        return avatarToken;
    }

    public void setAvatarToken(String avatarToken) {
        this.avatarToken = avatarToken;
    }

    public ArrayList<StructItemSticker> getStructItemStickers() {
        return structItemStickers;
    }

    public void setStructItemStickers(ArrayList<StructItemSticker> structItemStickers) {
        this.structItemStickers = structItemStickers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
