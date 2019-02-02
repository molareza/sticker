package com.vanniktech.emoji.sticker.struct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StructStickerItemGroup {

    @SerializedName("createdAt")
    @Expose
    private int createdAt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatarToken")
    @Expose
    private String avatarToken;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("isVip")
    @Expose
    private int isVip;
    @SerializedName("sort")
    @Expose
    private int sort;
    @SerializedName("createdBy")
    @Expose
    private long createdBy;

    public StructStickerItemGroup() {
    }

    public StructStickerItemGroup(int createdAt, String id, String name, String avatarToken, int price, int isVip, int sort, long createdBy) {
        this.createdAt = createdAt;
        this.id = id;
        this.name = name;
        this.avatarToken = avatarToken;
        this.price = price;
        this.isVip = isVip;
        this.sort = sort;
        this.createdBy = createdBy;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int isIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }
}
