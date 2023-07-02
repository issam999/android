package com.example.my_app.model;

public class Card {

    String card_id;
    String card_Prod_Name;
    String card_Qty;
    String card_Final_Price;
    String selected_qty;

    public Card(String card_id, String card_Prod_Name, String card_Qty, String card_Final_Price,String selected_qty) {
        this.card_id = card_id;
        this.card_Prod_Name = card_Prod_Name;
        this.card_Qty = card_Qty;
        this.card_Final_Price = card_Final_Price;
        this. selected_qty=selected_qty;
    }

    public Card() {

    }

    public void setSelected_qty(String selected_qty) {
        this.selected_qty = selected_qty;
    }

    public String getSelected_qty() {
        return selected_qty;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public void setCard_Prod_Name(String card_Prod_Name) {
        this.card_Prod_Name = card_Prod_Name;
    }

    public void setCard_Qty(String card_Qty) {
        this.card_Qty = card_Qty;
    }

    public void setCard_Final_Price(String card_Final_Price) {
        this.card_Final_Price = card_Final_Price;
    }

    public String getCard_id() {
        return card_id;
    }

    public String getCard_Prod_Name() {
        return card_Prod_Name;
    }

    public String getCard_Qty() {
        return card_Qty;
    }

    public String getCard_Final_Price() {
        return card_Final_Price;
    }
}
