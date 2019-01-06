package com.vanniktech.emoji.sticker;

import java.io.File;
import java.util.ArrayList;

public class StructCategory {

    private int idCategory;
    private String nameCategory;
    private int count;
    private String url;

    public StructCategory( int idCategory , String nameCategory , int count ,String url) {

        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.count = count;
        this.url = url;
    }


    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
