package com.vanniktech.emoji.sticker;

import java.util.ArrayList;

public class StructSticker {

    private int idCategory;
    private String category;
    private int count;
    private ArrayList<StructEachSticker> eachSticker = new ArrayList<>();

    public StructSticker(int idCategory,String nameCategory  , int count,ArrayList<StructEachSticker> eachSticker) {
        this.idCategory = idCategory;
        this.category = nameCategory;
        this.eachSticker = eachSticker;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<StructEachSticker> getEachSticker() {
        return eachSticker;
    }

    public void setEachSticker(ArrayList<StructEachSticker> eachSticker) {
        this.eachSticker = eachSticker;
    }
}
