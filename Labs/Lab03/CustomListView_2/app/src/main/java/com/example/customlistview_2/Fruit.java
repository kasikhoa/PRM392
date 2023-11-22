package com.example.customlistview_2;

public class Fruit {
    int Image;
    String Name;
    String Desc;

    public Fruit(int image, String name, String desc) {
        Image = image;
        Name = name;
        Desc = desc;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
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
