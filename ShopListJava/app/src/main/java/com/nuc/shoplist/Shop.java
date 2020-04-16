package com.nuc.shoplist;

/**
 * 商店物品类
 * */
public class Shop {
    private int imgResId;
    private String title;
    private int likes;
    private int comments;

    public Shop(int imgResId, String title, int likes, int comments) {
        this.imgResId = imgResId;
        this.title = title;
        this.likes = likes;
        this.comments = comments;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
