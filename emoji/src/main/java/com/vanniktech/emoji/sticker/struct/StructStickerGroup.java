package com.vanniktech.emoji.sticker.struct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StructStickerGroup {


    @SerializedName("ok")
    @Expose
    private boolean ok;
    @SerializedName("data")
    @Expose
    private List<StructStickerItemGroup> data = null;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<StructStickerItemGroup> getData() {
        return data;
    }

    public void setData(List<StructStickerItemGroup> data) {
        this.data = data;
    }

}
