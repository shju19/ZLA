package com.zla.android;

//ListView의 각 행(row)에 설정할 데이터를 관리하는 클래스
public class ListActivity_CustomDTO {

    int imgIcon;
    String txtBicycle;
    String txtBrand;
    int year;
    String txtFrame;
    String txtGroupset;
    String txtPrice;

    public ListActivity_CustomDTO(int imgIcon, String txtBicycle, String txtBrand, int year,
                                  String txtFrame, String txtGroupset, String txtPrice) {

        this.imgIcon	= imgIcon;
        this.txtBicycle	= txtBicycle;
        this.txtBrand	= txtBrand;
        this.year = year;
        this.txtFrame	= txtFrame;
        this.txtGroupset	= txtGroupset;
        this.txtPrice	= txtPrice;
    }

    public int getImgIcon() {
        return imgIcon;
    }
    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }
    public String getBicycle() {
        return txtBicycle;
    }
    public void setBicycle(String txtBicycle) {
        this.txtBicycle = txtBicycle;
    }
    public String getBrand() {
        return txtBrand;
    }
    public void setBrand(String txtBrand) {
        this.txtBrand = txtBrand;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getFrame() {
        return txtFrame;
    }
    public void setFrame(String txtFrame) {
        this.txtFrame = txtFrame;
    }
    public String getGroupset() {
        return txtGroupset;
    }
    public void setGroupset(String txtGroupset) {
        this.txtGroupset = txtGroupset;
    }
    public String getPrice() {
        return txtPrice;
    }
    public void setPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }
}
