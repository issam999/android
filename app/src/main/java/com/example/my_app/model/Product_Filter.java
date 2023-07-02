package com.example.my_app.model;

public class Product_Filter {
    Integer productId;
    String productName;

    public Product_Filter(Integer productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public Product_Filter() {
    }

    public Integer get_Filter_Id() {
        return productId;
    }

    public void set_Filter_Id(Integer productId) {
        this.productId = productId;
    }

    public String get_Filter_Name() {
        return productName;
    }

    public void set_Filter_Name(String productName) {
        this.productName = productName;
    }
}
