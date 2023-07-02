package com.example.my_app.model;

public class Product_Category {

    private int category_id ;
    private String category_name ;
    private String prod_name ;
    private int category_price ;


    public Product_Category(int category_id, String category_name, String prod_name, int category_price) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.prod_name = prod_name;
        this.category_price = category_price;
    }


    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public void setCategory_price(int category_price) {
        this.category_price = category_price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getProd_name() {
        return prod_name;
    }

    public int getCategory_price() {
        return category_price;
    }
}
