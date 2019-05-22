package com.vanniktech.emoji.sticker.struct;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StructGroupSticker implements Serializable {

    @SerializedName("groupId")
    @Expose
    private String groupId;
    @SerializedName("groupName")
    @Expose
    private String groupName;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("stickers")
    @Expose
    private List<StructItemSticker> stickerList;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<StructItemSticker> getStickerList() {
        return stickerList;
    }

    public void setStickerList(List<StructItemSticker> stickerList) {
        this.stickerList = stickerList;
    }
}