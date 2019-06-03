package com.zla.android;

//ListView의 각 행(row)에 설정할 데이터를 관리하는 클래스
public class ConfirmInventoryActivity_CustomDTO {

    String store;
    String location;
    String color1, color2, color3, color4, color5;
    String size1, size2, size3, size4, size5, size6, size7, size8;
    int color1_size1, color1_size2, color1_size3, color1_size4, color1_size5, color1_size6, color1_size7, color1_size8;
    int color2_size1, color2_size2, color2_size3, color2_size4, color2_size5, color2_size6, color2_size7, color2_size8;
    int color3_size1, color3_size2, color3_size3, color3_size4, color3_size5, color3_size6, color3_size7, color3_size8;
    int color4_size1, color4_size2, color4_size3, color4_size4, color4_size5, color4_size6, color4_size7, color4_size8;
    int color5_size1, color5_size2, color5_size3, color5_size4, color5_size5, color5_size6, color5_size7, color5_size8;

    public ConfirmInventoryActivity_CustomDTO(String store, String location,
                                              String color1, String color2, String color3, String color4, String color5,
                                              String size1, String size2, String size3, String size4,
                                              String size5, String size6, String size7, String size8,
                                              int color1_size1, int color1_size2, int color1_size3, int color1_size4,
                                              int color1_size5, int color1_size6, int color1_size7, int color1_size8,
                                              int color2_size1, int color2_size2, int color2_size3, int color2_size4,
                                              int color2_size5, int color2_size6, int color2_size7, int color2_size8,
                                              int color3_size1, int color3_size2, int color3_size3, int color3_size4,
                                              int color3_size5, int color3_size6, int color3_size7, int color3_size8,
                                              int color4_size1, int color4_size2, int color4_size3, int color4_size4,
                                              int color4_size5, int color4_size6, int color4_size7, int color4_size8,
                                              int color5_size1, int color5_size2, int color5_size3, int color5_size4,
                                              int color5_size5, int color5_size6, int color5_size7, int color5_size8) {

        this.store = store;
        this.location = location;
        this.color1 = color1; this.color2 = color2; this.color3 = color3; this.color4 = color4; this.color5 = color5;
        this.size1 = size1; this.size2 = size2; this.size3 = size3; this.size4 = size4; this.size5 = size5; this.size6 = size6; this.size7 = size7; this.size8 = size8;
        this.color1_size1 = color1_size1; this.color1_size2 = color1_size2; this.color1_size3 = color1_size3; this.color1_size4 = color1_size4;
        this.color1_size5 = color1_size5; this.color1_size6 = color1_size6; this.color1_size7 = color1_size7; this.color1_size8 = color1_size8;
        this.color2_size1 = color2_size1; this.color2_size2 = color2_size2; this.color2_size3 = color2_size3; this.color2_size4 = color2_size4;
        this.color2_size5 = color2_size5; this.color2_size6 = color2_size6; this.color2_size7 = color2_size7; this.color2_size8 = color2_size8;
        this.color3_size1 = color3_size1; this.color3_size2 = color3_size2; this.color3_size3 = color3_size3; this.color3_size4 = color3_size4;
        this.color3_size5 = color3_size5; this.color3_size6 = color3_size6; this.color3_size7 = color3_size7; this.color3_size8 = color3_size8;
        this.color4_size1 = color4_size1; this.color4_size2 = color4_size2; this.color4_size3 = color4_size3; this.color4_size4 = color4_size4;
        this.color5_size5 = color4_size5; this.color4_size6 = color4_size6; this.color4_size7 = color4_size7; this.color4_size8 = color4_size8;
        this.color5_size1 = color5_size1; this.color5_size2 = color5_size2; this.color5_size3 = color5_size3; this.color5_size4 = color5_size4;
        this.color5_size5 = color5_size5; this.color5_size6 = color5_size6; this.color5_size7 = color5_size7; this.color5_size8 = color5_size8;
    }

    //기본값
    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    //색상
    public String getColor1() { return color1; }
    public void setColor1(String color1) { this.color1 = color1; }
    public String getColor2() { return color2; }
    public void setColor2(String color2) { this.color2 = color2; }
    public String getColor3() { return color3; }
    public void setColor3(String color3) { this.color3 = color3; }
    public String getColor4() { return color4; }
    public void setColor4(String color4) { this.color4 = color4; }
    public String getColor5() { return color5; }
    public void setColor5(String color5) { this.color5 = color5; }
    //사이즈
    public String getSize1() { return size1; }
    public void setSize1(String size1) { this.size1 = size1; }
    public String getSize2() { return size2; }
    public void setSize2(String size2) { this.size2 = size2; }
    public String getSize3() { return size3; }
    public void setSize3(String size3) { this.size3 = size3; }
    public String getSize4() { return size4; }
    public void setSize4(String size4) { this.size4 = size4; }
    public String getSize5() { return size5; }
    public void setSize5(String size5) { this.size5 = size5; }
    public String getSize6() { return size6; }
    public void setSize6(String size6) { this.size6 = size6; }
    public String getSize7() { return size7; }
    public void setSize7(String size7) { this.size7 = size7; }
    public String getSize8() { return size8; }
    public void setSize8(String size8) { this.size8 = size8; }
    //색상1 재고
    public int getColor1_size1() { return color1_size1; }
    public void setColor1_size1(int color1_size1) { this.color1_size1 = color1_size1; }
    public int getColor1_size2() { return color1_size2; }
    public void setColor1_size2(int color1_size2) { this.color1_size2 = color1_size2; }
    public int getColor1_size3() { return color1_size3; }
    public void setColor1_size3(int color1_size3) { this.color1_size3 = color1_size3; }
    public int getColor1_size4() { return color1_size4; }
    public void setColor1_size4(int color1_size4) { this.color1_size4 = color1_size4; }
    public int getColor1_size5() { return color1_size5; }
    public void setColor1_size5(int color1_size5) { this.color1_size5 = color1_size5; }
    public int getColor1_size6() { return color1_size6; }
    public void setColor1_size6(int color1_size6) { this.color1_size6 = color1_size6; }
    public int getColor1_size7() { return color1_size7; }
    public void setColor1_size7(int color1_size7) { this.color1_size7 = color1_size7; }
    public int getColor1_size8() { return color1_size8; }
    public void setColor1_size8(int color1_size8) { this.color1_size8 = color1_size8; }
    //색상2 재고
    public int getColor2_size1() { return color2_size1; }
    public void setColor2_size1(int color2_size1) { this.color2_size1 = color2_size1; }
    public int getColor2_size2() { return color2_size2; }
    public void setColor2_size2(int color2_size2) { this.color2_size2 = color2_size2; }
    public int getColor2_size3() { return color2_size3; }
    public void setColor2_size3(int color2_size3) { this.color2_size3 = color2_size3; }
    public int getColor2_size4() { return color2_size4; }
    public void setColor2_size4(int color2_size4) { this.color2_size4 = color2_size4; }
    public int getColor2_size5() { return color2_size5; }
    public void setColor2_size5(int color2_size5) { this.color2_size5 = color2_size5; }
    public int getColor2_size6() { return color2_size6; }
    public void setColor2_size6(int color2_size6) { this.color2_size6 = color2_size6; }
    public int getColor2_size7() { return color2_size7; }
    public void setColor2_size7(int color2_size7) { this.color2_size7 = color2_size7; }
    public int getColor2_size8() { return color2_size8; }
    public void setColor2_size8(int color2_size8) { this.color2_size8 = color2_size8; }
    //색상3 재고
    public int getColor3_size1() { return color3_size1; }
    public void setColor3_size1(int color3_size1) { this.color3_size1 = color3_size1; }
    public int getColor3_size2() { return color3_size2; }
    public void setColor3_size2(int color3_size2) { this.color3_size2 = color3_size2; }
    public int getColor3_size3() { return color3_size3; }
    public void setColor3_size3(int color3_size3) { this.color3_size3 = color3_size3; }
    public int getColor3_size4() { return color3_size4; }
    public void setColor3_size4(int color3_size4) { this.color3_size4 = color3_size4; }
    public int getColor3_size5() { return color3_size5; }
    public void setColor3_size5(int color3_size5) { this.color3_size5 = color3_size5; }
    public int getColor3_size6() { return color3_size6; }
    public void setColor3_size6(int color3_size6) { this.color3_size6 = color3_size6; }
    public int getColor3_size7() { return color3_size7; }
    public void setColor3_size7(int color3_size7) { this.color3_size7 = color3_size7; }
    public int getColor3_size8() { return color3_size8; }
    public void setColor3_size8(int color3_size8) { this.color3_size8 = color3_size8; }
    //색상4 재고
    public int getColor4_size1() { return color4_size1; }
    public void setColor4_size1(int color4_size1) { this.color4_size1 = color4_size1; }
    public int getColor4_size2() { return color4_size2; }
    public void setColor4_size2(int color4_size2) { this.color4_size2 = color4_size2; }
    public int getColor4_size3() { return color4_size3; }
    public void setColor4_size3(int color4_size3) { this.color4_size3 = color4_size3; }
    public int getColor4_size4() { return color4_size4; }
    public void setColor4_size4(int color4_size4) { this.color4_size4 = color4_size4; }
    public int getColor4_size5() { return color4_size5; }
    public void setColor4_size5(int color4_size5) { this.color4_size5 = color4_size5; }
    public int getColor4_size6() { return color4_size6; }
    public void setColor4_size6(int color4_size6) { this.color4_size6 = color4_size6; }
    public int getColor4_size7() { return color4_size7; }
    public void setColor4_size7(int color4_size7) { this.color4_size7 = color4_size7; }
    public int getColor4_size8() { return color4_size8; }
    public void setColor4_size8(int color4_size8) { this.color4_size8 = color4_size8; }
    //색상5 재고
    public int getColor5_size1() { return color5_size1; }
    public void setColor5_size1(int color5_size1) { this.color5_size1 = color5_size1; }
    public int getColor5_size2() { return color5_size2; }
    public void setColor5_size2(int color5_size2) { this.color5_size2 = color5_size2; }
    public int getColor5_size3() { return color5_size3; }
    public void setColor5_size3(int color5_size3) { this.color5_size3 = color5_size3; }
    public int getColor5_size4() { return color5_size4; }
    public void setColor5_size4(int color5_size4) { this.color5_size4 = color5_size4; }
    public int getColor5_size5() { return color5_size5; }
    public void setColor5_size5(int color5_size5) { this.color5_size5 = color5_size5; }
    public int getColor5_size6() { return color5_size6; }
    public void setColor5_size6(int color5_size6) { this.color5_size6 = color5_size6; }
    public int getColor5_size7() { return color5_size7; }
    public void setColor5_size7(int color5_size7) { this.color5_size7 = color5_size7; }
    public int getColor5_size8() { return color5_size8; }
    public void setColor5_size8(int color5_size8) { this.color5_size8 = color5_size8; }
}
