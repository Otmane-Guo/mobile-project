package com.example.codemagictest;

public class Category {
    private int id=0;
    private String name;
    private String image;

    public Category(String name, String image){
        this.name = name;
        this.image = image;
    }

    public Category(int id, String name, String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
