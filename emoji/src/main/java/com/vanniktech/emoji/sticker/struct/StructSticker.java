package com.vanniktech.emoji.sticker.struct;



import java.util.List;

public class StructSticker {

    private Boolean ok;
    private List<StructGroupSticker> data = null;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<StructGroupSticker> getData() {
        return data;
    }

    public void setData(List<StructGroupSticker> data) {
        this.data = data;
    }

}