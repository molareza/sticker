package com.vanniktech.emoji.sticker;

import java.io.File;
import java.util.ArrayList;

public class StructSticker {

    private String category;
    private String count;
    private File folderSticker;
    private ArrayList<String> path = new ArrayList<>();

    public StructSticker(String category , String count,  File folderSticker,ArrayList<String> path) {
        this.category = category;
        this.path = path;
        this.count = count;
        this.folderSticker = folderSticker;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public void setPath(ArrayList<String> path) {
        this.path = path;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public File getFolderSticker() {
        return folderSticker;
    }

    public void setFolderSticker(File folderSticker) {
        this.folderSticker = folderSticker;
    }
}
