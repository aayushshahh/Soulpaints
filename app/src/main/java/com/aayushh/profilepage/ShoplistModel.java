package com.aayushh.profilepage;

public class ShoplistModel {
    private int imgsrc;
    private String paintingName;
    private String description;
    private String price;
    public ShoplistModel(int img, String name, String description, String price) {
        this.imgsrc = img;
        this.paintingName = name;
        this.description = description;
        this.price = price;
    }
    public String getPaintingName() {return paintingName;}
    public void setPaintingName(String name) {this.paintingName = name;}
    public int getImgsrc() {return imgsrc;};
    public void setImgsrc(int img) {this.imgsrc = img;}
    public String getDescription() {return description;}
    public void setDescription(String desc) {this.description = desc;}
    public String getPrice() {return price;}
    public void setPrice(String pr) {this.price = pr;}
}
