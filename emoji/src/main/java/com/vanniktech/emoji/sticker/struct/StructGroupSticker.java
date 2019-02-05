package com.vanniktech.emoji.sticker.struct;



import java.io.Serializable;
import java.util.List;

public class StructGroupSticker implements Serializable {

    private Integer createdAt;
    private String id;
    private Integer refId;
    private String name;
    private String avatarToken;
    private String uri;
    private Integer price;
    private Boolean isVip;
    private Integer sort;
    private Integer createdBy;
    private List<StructItemSticker> stickers = null;

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarToken() {
        return avatarToken;
    }

    public void setAvatarToken(String avatarToken) {
        this.avatarToken = avatarToken;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getIsVip() {
        return isVip;
    }

    public void setIsVip(Boolean isVip) {
        this.isVip = isVip;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public List<StructItemSticker> getStickers() {
        return stickers;
    }

    public void setStickers(List<StructItemSticker> stickers) {
        this.stickers = stickers;
    }

}