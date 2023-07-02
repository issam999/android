package com.example.my_app.model;

public class Products {
    String productid;
    String productName;
    String productQty;
    String productPrice;
    String imageUrl;
    String filter_Name;

    public Products(String productid, String productName, String productQty, String productPrice, String imageUrl,String filter_Name) {
        this.productid = productid;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
        this.filter_Name =  filter_Name;
    }
    public Products() { }

    public void setFilter_Name(String filter_Name) {
        this.filter_Name = filter_Name;
    }

    public String getFilter_Name() {
        return filter_Name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}
