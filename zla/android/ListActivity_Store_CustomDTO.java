package com.zla.android;

//ListView의 각 행(row)에 설정할 데이터를 관리하는 클래스
public class ListActivity_Store_CustomDTO {

    String storeImage;
    String storeName;
    String address;
    String contact;

    public ListActivity_Store_CustomDTO(String storeImage, String storeName,
                                        String address, String contact) {
        this.storeImage	= storeImage;
        this.storeName	= storeName;
        this.address = address;
        this.contact = contact;
    }

    public String getStoreImage() { return storeImage; }
    public void setStoreImage(String storeImage) { this.storeImage = storeImage; }
    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
