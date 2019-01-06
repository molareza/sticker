package com.vanniktech.emoji.sticker;

public class StructRecentSticker {

    private int idSticker;
    private int idCategory;
    private String category;
    private String path;

    public StructRecentSticker(String category, String path ,int idCategory ,int idSticker ) {
        this.category = category;
        this.idCategory = idCategory;
        this.idSticker = idSticker;
        this.path = path;
    }


    public int getIdSticker() {
        return idSticker;
    }

    public void setIdSticker(int idSticker) {
        this.idSticker = idSticker;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

