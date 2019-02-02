package com.vanniktech.emoji.sticker;

public class StructRecentSticker {

    private String idSticker;
    private String idCategory;
    private String category;
    private String token;
    private String url;

    public StructRecentSticker(String idSticker, String idCategory, String category, String token, String url) {
        this.idSticker = idSticker;
        this.idCategory = idCategory;
        this.category = category;
        this.token = token;
        this.url = url;
    }

    public String getIdSticker() {
        return idSticker;
    }

    public void setIdSticker(String idSticker) {
        this.idSticker = idSticker;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

