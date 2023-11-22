package com.example.customlistview_1;

public class Legend {
    int ImageAvatar;
    int ImageFlag;
    String Name;
    String Desc;

    public Legend(int imageAvatar, int imageFlag, String name, String desc) {
        ImageAvatar = imageAvatar;
        ImageFlag = imageFlag;
        Name = name;
        Desc = desc;
    }

    public int getImageAvatar() {
        return ImageAvatar;
    }

    public void setImageAvatar(int imageAvatar) {
        ImageAvatar = imageAvatar;
    }

    public int getImageFlag() {
        return ImageFlag;
    }

    public void setImageFlag(int imageFlag) {
        ImageFlag = imageFlag;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
