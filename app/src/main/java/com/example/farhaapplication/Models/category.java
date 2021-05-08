package com.example.farhaapplication.Models;

public class category {

    private String name_cat , image_name ;
    private int num_ofProducts ;

    public category() { }

    public category(String name_cat, String image_name, int num_ofProducts) {
        this.name_cat = name_cat;
        this.image_name = image_name;
        this.num_ofProducts = num_ofProducts;
    }

    public String getName_cat() {
        return name_cat;
    }

    public void setName_cat(String name_cat) {
        this.name_cat = name_cat;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getNum_ofProducts() {
        return num_ofProducts;
    }

    public void setNum_ofProducts(int num_ofProducts) {
        this.num_ofProducts = num_ofProducts;
    }

    @Override
    public String toString() {
        return "category{" +
                "name_cat='" + name_cat + '\'' +
                ", image_name='" + image_name + '\'' +
                ", num_ofProducts=" + num_ofProducts +
                '}';
    }
}
