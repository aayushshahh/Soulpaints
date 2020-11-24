package com.aayushh.profilepage;

public class Model {
    private String title;
    private String artist;
    private String desc;
    private String price;

    public Model(String title, String artist, String desc, String price) {
        this.title = title;
        this.artist = artist;
        this.desc = desc;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
