package com.vanniktech.emoji.sticker.struct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StructSticker {


    @SerializedName("ok")
    @Expose
    private boolean ok;
    @SerializedName("data")
    @Expose
    private List<StructItemSticker> data = null;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<StructItemSticker> getData() {
        return data;
    }

    public void setData(List<StructItemSticker> data) {
        this.data = data;
    }

}
