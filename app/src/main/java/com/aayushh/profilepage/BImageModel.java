package com.aayushh.profilepage;

public class BImageModel {
    private String title;
    private int imgsrc;

    public BImageModel(String title, int imgsrc) {
        this.title = title;
        this.imgsrc = imgsrc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public int getImgsrc(){return imgsrc;}
    public void setImgsrc(int imgsrc) {this.imgsrc = imgsrc;}
}
