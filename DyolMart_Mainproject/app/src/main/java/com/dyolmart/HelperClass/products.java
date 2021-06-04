package com.dyolmart.HelperClass;

public class products {

    private String Commonid,Img_url1,Img_url2,Img_url3,Img_url4,Brand_Name,Product_Desc,
            Product_feature,Size_1,Size_1_Price,Size_2,Size_2_Price,Size_3,Size_3_Price,
            Size_4,Size_4_Price,Size_5,Size_5_Price,Size_6,Size_6_Price,Size_7,Size_7_Price,
            Size_8,Size_8_Price,Size_9,Size_9_Price,Size_10,Size_10_Price,Shop_ID,common_price,Rating_1,Rating_2,Rating_3,Rating_4,Rating_5,Tot_Rating,Average;

    public products() {

    }


    public products(String commonid, String img_url1, String img_url2, String img_url3, String img_url4,
                    String brand_Name, String product_Desc, String product_feature, String size_1,
                    String size_1_Price, String size_2, String size_2_Price, String size_3, String size_3_Price,
                    String size_4, String size_4_Price, String size_5, String size_5_Price, String size_6, String size_6_Price,
                    String size_7, String size_7_Price, String size_8, String size_8_Price, String size_9, String size_9_Price,
                    String size_10, String size_10_Price, String shop_ID, String common_price) {
        Img_url1 = img_url1;
        Img_url2 = img_url2;
        Img_url3 = img_url3;
        Img_url4 = img_url4;
        Brand_Name = brand_Name;
        Product_Desc = product_Desc;
        Product_feature = product_feature;
        Size_1 = size_1;
        Size_1_Price = size_1_Price;
        Size_2 = size_2;
        Size_2_Price = size_2_Price;
        Size_3 = size_3;
        Size_3_Price = size_3_Price;
        Size_4 = size_4;
        Size_4_Price = size_4_Price;
        Size_5 = size_5;
        Size_5_Price = size_5_Price;
        Size_6 = size_6;
        Size_6_Price = size_6_Price;
        Size_7 = size_7;
        Size_7_Price = size_7_Price;
        Size_8 = size_8;
        Size_8_Price = size_8_Price;
        Size_9 = size_9;
        Size_9_Price = size_9_Price;
        Size_10 = size_10;
        Size_10_Price = size_10_Price;
        Shop_ID = shop_ID;
        Commonid = commonid;
        this.common_price = common_price;

    }

    public products(String tot_Rating, String average) {
        Tot_Rating = tot_Rating;
        Average = average;
    }

    public products(String rating_1, String rating_2, String rating_3, String rating_4, String rating_5) {
        Rating_1 = rating_1;
        Rating_2 = rating_2;
        Rating_3 = rating_3;
        Rating_4 = rating_4;
        Rating_5 = rating_5;
    }

    public String getCommon_price() {
        return common_price;
    }

    public void setCommon_price(String common_price) {
        this.common_price = common_price;
    }

    public String getBrand_Name() {
        return Brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        Brand_Name = brand_Name;
    }

    public String getProduct_Desc() {
        return Product_Desc;
    }

    public void setProduct_Desc(String product_Desc) {
        Product_Desc = product_Desc;
    }

    public String getProduct_feature() {
        return Product_feature;
    }

    public void setProduct_feature(String product_feature) {
        Product_feature = product_feature;
    }

    public String getSize_1() {
        return Size_1;
    }

    public void setSize_1(String size_1) {
        Size_1 = size_1;
    }

    public String getSize_1_Price() {
        return Size_1_Price;
    }

    public void setSize_1_Price(String size_1_Price) {
        Size_1_Price = size_1_Price;
    }

    public String getSize_2() {
        return Size_2;
    }

    public void setSize_2(String size_2) {
        Size_2 = size_2;
    }

    public String getSize_2_Price() {
        return Size_2_Price;
    }

    public void setSize_2_Price(String size_2_Price) {
        Size_2_Price = size_2_Price;
    }

    public String getSize_3() {
        return Size_3;
    }

    public void setSize_3(String size_3) {
        Size_3 = size_3;
    }

    public String getSize_3_Price() {
        return Size_3_Price;
    }

    public void setSize_3_Price(String size_3_Price) {
        Size_3_Price = size_3_Price;
    }

    public String getSize_4() {
        return Size_4;
    }

    public void setSize_4(String size_4) {
        Size_4 = size_4;
    }

    public String getSize_4_Price() {
        return Size_4_Price;
    }

    public void setSize_4_Price(String size_4_Price) {
        Size_4_Price = size_4_Price;
    }

    public String getSize_5() {
        return Size_5;
    }

    public void setSize_5(String size_5) {
        Size_5 = size_5;
    }

    public String getSize_5_Price() {
        return Size_5_Price;
    }

    public void setSize_5_Price(String size_5_Price) {
        Size_5_Price = size_5_Price;
    }

    public String getSize_6() {
        return Size_6;
    }

    public void setSize_6(String size_6) {
        Size_6 = size_6;
    }

    public String getSize_6_Price() {
        return Size_6_Price;
    }

    public void setSize_6_Price(String size_6_Price) {
        Size_6_Price = size_6_Price;
    }

    public String getSize_7() {
        return Size_7;
    }

    public void setSize_7(String size_7) {
        Size_7 = size_7;
    }

    public String getSize_7_Price() {
        return Size_7_Price;
    }

    public void setSize_7_Price(String size_7_Price) {
        Size_7_Price = size_7_Price;
    }

    public String getSize_8() {
        return Size_8;
    }

    public void setSize_8(String size_8) {
        Size_8 = size_8;
    }

    public String getSize_8_Price() {
        return Size_8_Price;
    }

    public void setSize_8_Price(String size_8_Price) {
        Size_8_Price = size_8_Price;
    }

    public String getSize_9() {
        return Size_9;
    }

    public void setSize_9(String size_9) {
        Size_9 = size_9;
    }

    public String getSize_9_Price() {
        return Size_9_Price;
    }

    public void setSize_9_Price(String size_9_Price) {
        Size_9_Price = size_9_Price;
    }

    public String getSize_10() {
        return Size_10;
    }

    public void setSize_10(String size_10) {
        Size_10 = size_10;
    }

    public String getSize_10_Price() {
        return Size_10_Price;
    }

    public String getImg_url1() {
        return Img_url1;
    }

    public void setImg_url1(String img_url1) {
        Img_url1 = img_url1;
    }

    public void setSize_10_Price(String size_10_Price) {
        Size_10_Price = size_10_Price;
    }

    public String getImg_url2() {
        return Img_url2;
    }

    public void setImg_url2(String img_url2) {
        Img_url2 = img_url2;
    }

    public String getImg_url3() {
        return Img_url3;
    }

    public void setImg_url3(String img_url3) {
        Img_url3 = img_url3;
    }

    public String getImg_url4() {
        return Img_url4;
    }

    public void setImg_url4(String img_url4) {
        Img_url4 = img_url4;
    }

    public String getShop_ID() {
        return Shop_ID;
    }

    public String getCommonid() {
        return Commonid;
    }

    public void setCommonid(String commonid) {
        Commonid = commonid;
    }

    public void setShop_ID(String shop_ID) {
        Shop_ID = shop_ID;
    }

    public String getRating_1() {
        return Rating_1;
    }

    public void setRating_1(String rating_1) {
        Rating_1 = rating_1;
    }

    public String getRating_2() {
        return Rating_2;
    }

    public void setRating_2(String rating_2) {
        Rating_2 = rating_2;
    }

    public String getRating_3() {
        return Rating_3;
    }

    public void setRating_3(String rating_3) {
        Rating_3 = rating_3;
    }

    public String getRating_4() {
        return Rating_4;
    }

    public void setRating_4(String rating_4) {
        Rating_4 = rating_4;
    }

    public String getRating_5() {
        return Rating_5;
    }

    public void setRating_5(String rating_5) {
        Rating_5 = rating_5;
    }

    public String getTot_Rating() {
        return Tot_Rating;
    }

    public void setTot_Rating(String tot_Rating) {
        Tot_Rating = tot_Rating;
    }

    public String getAverage() {
        return Average;
    }

    public void setAverage(String average) {
        Average = average;
    }
}

