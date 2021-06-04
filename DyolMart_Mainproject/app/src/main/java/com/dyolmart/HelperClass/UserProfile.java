package com.dyolmart.HelperClass;



public class UserProfile {
    public String userPhone;
    public String userEmail;
    public String userName;
    public String userPassword;
    public int image;
    public int btimages;
    public String htname;

    public UserProfile(){
    }

    public UserProfile(String userPhone, String userEmail, String userName, String userPassword) {
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserPhone () {
        return userPhone;
    }

    public void setUserPhone (String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail () {
        return userEmail;
    }

    public void setUserEmail (String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getUserPassword () {
        return userPassword;
    }

    public void setUserPassword (String userPassword) {
        this.userPassword = userPassword;
    }

    public int getImage () {
        return image;
    }

    public void setImage (int image) {
        this.image = image;
    }

    public String getHtname () {
        return htname;
    }

    public void setHtname (String htname) {
        this.htname = htname;
    }

    public int getBtimages () {
        return btimages;
    }

    public void setBtimages (int btimages) {
        this.btimages = btimages;
    }
}
