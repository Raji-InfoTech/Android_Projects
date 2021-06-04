package com.dyolmart.HelperClass;

public class Helper {
    private String Brand_Name,Img_url1;
    private String Img1,Img2,Img3,Img4;



    public Helper() {
    }

    public Helper(String brand_Name, String img_url1, String img1, String img2, String img3, String img4) {
        Brand_Name = brand_Name;
        Img_url1 = img_url1;
        Img1 = img1;
        Img2 = img2;
        Img3 = img3;
        Img4 = img4;
    }


    public String getBrand_Name() {
        return Brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        Brand_Name = brand_Name;
    }

    public String getImg_url1() {
        return Img_url1;
    }

    public void setImg_url1(String img_url1) {
        Img_url1 = img_url1;
    }

    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }

    public String getImg2() {
        return Img2;
    }

    public void setImg2(String img2) {
        Img2 = img2;
    }

    public String getImg3() {
        return Img3;
    }

    public void setImg3(String img3) {
        Img3 = img3;
    }

    public String getImg4() {
        return Img4;
    }

    public void setImg4(String img4) {
        Img4 = img4;
    }
}
