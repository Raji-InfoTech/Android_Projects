package com.dyolmart.HelperClass;

public class Userhipper {

    public String password,bname,price,order_number,shop_number,imagelink,altermbl,status;
    private String name,address,email,phoneno,pincode,Payment_method;
    private String state,city,locality,flatno,landmark,generate,for_me,Placed_date,qty,Item_Size;

    public Userhipper() {
    }


    public Userhipper(String password, String bname, String price, String order_number, String shop_number, String imagelink, String altermbl, String status, String name, String address, String email, String phoneno, String pincode, String shop_ID, String payment_method, String state, String city, String locality, String flatno, String landmark, String generate, String for_me, String qty, String item_size) {
        this.password = password;
        this.bname = bname;
        this.price = price;
        this.order_number = order_number;
        this.shop_number = shop_number;
        this.imagelink = imagelink;
        this.altermbl = altermbl;
        this.status = status;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneno = phoneno;
        this.pincode = pincode;
        Payment_method = payment_method;
        this.state = state;
        this.city = city;
        this.locality = locality;
        this.flatno = flatno;
        this.landmark = landmark;
        this.generate = generate;
        this.for_me = for_me;
        this.qty = qty;
        Item_Size = item_size;
      }

    public Userhipper(String Placed_date) {
        this.Placed_date = Placed_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getShop_number() {
        return shop_number;
    }

    public void setShop_number(String shop_number) {
        this.shop_number = shop_number;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getAltermbl() {
        return altermbl;
    }

    public void setAltermbl(String altermbl) {
        this.altermbl = altermbl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPayment_method() {
        return Payment_method;
    }

    public void setPayment_method(String payment_method) {
        Payment_method = payment_method;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getFlatno() {
        return flatno;
    }

    public void setFlatno(String flatno) {
        this.flatno = flatno;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getGenerate() {
        return generate;
    }

    public void setGenerate(String generate) {
        this.generate = generate;
    }

    public String getFor_me() {
        return for_me;
    }

    public void setFor_me(String for_me) {
        this.for_me = for_me;
    }

    public String getDate() {
        return Placed_date;
    }

    public void setDate(String Placed_date) {
        this.Placed_date= Placed_date;
    }
    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getItem_Size() {
        return Item_Size;
    }

    public void setItem_Size(String item_Size) {
        Item_Size = item_Size;
    }
}
