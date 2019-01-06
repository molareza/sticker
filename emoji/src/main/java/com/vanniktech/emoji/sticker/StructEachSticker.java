package com.vanniktech.emoji.sticker;

import java.util.ArrayList;

public class StructEachSticker {

    private int idSticker;
    private String url;

    public StructEachSticker(int idSticker, String url ) {
        this.idSticker = idSticker;
        this.url = url;
        this.idSticker = idSticker;
    }

    public int getIdSticker() {
        return idSticker;
    }

    public void setIdSticker(int idSticker) {
        this.idSticker = idSticker;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
