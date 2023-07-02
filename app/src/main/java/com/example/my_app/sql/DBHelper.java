package com.example.my_app.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ViewParent;
import android.widget.Toast;

import com.example.my_app.model.Card;
import com.example.my_app.model.Product_Filter;
import com.example.my_app.model.Products;
import com.example.my_app.model.User;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper( Context context) {
        super(context, "shop_app.db", null, 1);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table user(user_id TEXT primary key, user_name TEXT, user_email TEXT,user_password TEXT )");
        db.execSQL("create Table card(card_id TEXT primary key, prod_name TEXT, qantity TEXT,final_price TEXT,selected_quantity Text )");
       // db.execSQL("create Table category(category_id TEXT primary key, category_name TEXT, prod_name TEXT,category_price TEXT , CONSTRAINT FK_prod_name FOREIGN KEY (prod_name) REFERENCES product(product_Name))");
        db.execSQL("create Table product(product_id TEXT primary key, product_Name TEXT , product_Qty TEXT ,  product_Price TEXT , image_Url TEXT , filter_name TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists user");
        db.execSQL("drop Table if exists product");
        db.execSQL("drop Table if exists card");
        onCreate(db);
    }

    //<------------ SUM all price of table card ---------->
    public int Sum_all_price_card () {
        SQLiteDatabase db = this.getReadableDatabase();
        int sum =-1;
        Cursor cursor = db.rawQuery("Select SUM(final_price) from card ",null);

        if(cursor.moveToFirst())
        { sum = cursor.getInt(0);}
        cursor.close();
        return sum;
    }

    // <<------------------------- get product by name return one product --------------->>
    @SuppressLint("Range")
    public Products get_product_By_Name (String name_prod) {
        SQLiteDatabase db = this.getReadableDatabase();
        Products product = new Products();
        Cursor cursor = db.rawQuery("Select * from product where product_Name=?", new String[]{name_prod});
        if (cursor.moveToFirst()) {
            do {
                product.setProductid(cursor.getString(cursor.getColumnIndex("product_id")));
                product.setProductName(cursor.getString(cursor.getColumnIndex("product_Name")));
                product.setProductQty(cursor.getString(cursor.getColumnIndex("product_Qty")));
                product.setProductPrice(cursor.getString(cursor.getColumnIndex("product_Price")));
                product.setImageUrl(cursor.getString(cursor.getColumnIndex("image_Url")));
                product.setFilter_Name(cursor.getString(cursor.getColumnIndex("filter_name")));

                // Adding user record to list

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return product; }

    // <<------------------------- update card by product name  --------------->>
    public void update_card(Card card,String name_pd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("prod_name", card.getCard_Prod_Name());
        values.put("qantity", card.getCard_Qty());
        values.put("final_price", card.getCard_Final_Price());
        values.put("selected_quantity", card.getSelected_qty());
        long result = db.update("card", values, "prod_name=?", new String[]{name_pd});
        db.close();
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Card Successfully!", Toast.LENGTH_SHORT).show();
        } }

        // <<------------------------- get all card  return list card --------------->>
    @SuppressLint("Range")
    public List<Card> getAllCard() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Card> cardList = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from card", null);
        if (cursor.moveToFirst()) {
            do {
                Card card = new Card();
                //card.getCard_id(cursor.getString(cursor.getColumnIndex("card_id")));
                card.setCard_Prod_Name(cursor.getString(cursor.getColumnIndex("prod_name")));
                card.setCard_Qty(cursor.getString(cursor.getColumnIndex("qantity")));
                card.setCard_Final_Price(cursor.getString(cursor.getColumnIndex("final_price")));
                card.setSelected_qty(cursor.getString(cursor.getColumnIndex("selected_quantity")));
                // Adding user record to list
                cardList.add(card);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cardList;
    }

    // <<------------------------- add data to card table   --------------->>
    public void Add_Data_To_Card(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("prod_name", card.getCard_Prod_Name());
        values.put("qantity", card.getCard_Qty());
        values.put("final_price", card.getCard_Final_Price());
        values.put("selected_quantity", card.getSelected_qty());
        // Inserting Row
        Long res = db.insert("card", null, values);
        if (res==-1){
            Toast.makeText(context, "add failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Add Successfuly", Toast.LENGTH_SHORT).show();
        } }

    // <<-------------------- add data to  product table  --------------->>
    public void addProduct(Products product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_Name", product.getProductName());
        values.put("product_Qty", product.getProductQty());
        values.put("product_Price", product.getProductPrice());
        values.put("image_Url", product.getImageUrl());
        values.put("filter_name", product.getFilter_Name());
        // Inserting Row
        Long res = db.insert("product", null, values);
        if (res==-1){
            Toast.makeText(context, "add failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Add Successfuly", Toast.LENGTH_SHORT).show();
        } }

    // <<------------------- add data to user table   --------------->>
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", user.getName());
        values.put("user_email", user.getEmail());
        values.put("user_password", user.getPassword());
        // Inserting Row
        db.insert("user", null, values);
        db.close();
    }

    // <<------------------- update table product by name of the product    --------------->>
    public void updateProduct(Products product,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_Name", product.getProductName());
        values.put("product_Qty", product.getProductQty());
        values.put("product_Price", product.getProductPrice());
        values.put("image_Url", product.getImageUrl());
        values.put("filter_name", product.getFilter_Name());
        long result = db.update("product", values, "product_Name=?", new String[]{name});
        db.close();
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        } }

   // <<------------------- get all data  of User table    --------------->>
    @SuppressLint("Range")
    public List<User> getAllUser(){

        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user",null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))));
                user.setName(cursor.getString(cursor.getColumnIndex("user_name")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("user_email")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("user_password")));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }

    // <<------------------- get all data  of product table    --------------->>
    @SuppressLint("Range")
    public List<Products> getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Products> prodlist = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from product", null);
        if (cursor.moveToFirst()) {
            do {
                Products product = new Products();
                product.setProductid(cursor.getString(cursor.getColumnIndex("product_id")));
                product.setProductName(cursor.getString(cursor.getColumnIndex("product_Name")));
                product.setProductQty(cursor.getString(cursor.getColumnIndex("product_Qty")));
                product.setProductPrice(cursor.getString(cursor.getColumnIndex("product_Price")));
                product.setImageUrl(cursor.getString(cursor.getColumnIndex("image_Url")));
                product.setFilter_Name(cursor.getString(cursor.getColumnIndex("filter_name")));

                // Adding user record to list
                prodlist.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return prodlist;
    }




//<------------ return Array list of distinct filter name of product -------->
    @SuppressLint("Range")
    public List<String> get_distinct_filter_name() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> filterlist = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select DISTINCT filter_name  from product ", null);
        if (cursor.moveToFirst()) {
            do {
                // Adding user record to list
                filterlist.add(cursor.getString(cursor.getColumnIndex("filter_name")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return filterlist;
    }

    // <<------------------- get list data  of product table by name_filter and return list of products    --------------->>
    @SuppressLint("Range")
    public List<Products> get_filtred_product(String name_filter) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Products> prodlist = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from product where filter_name=?", new String[]{name_filter});
        if (cursor.moveToFirst()) {
            do {
                Products product = new Products();
                product.setProductid(cursor.getString(cursor.getColumnIndex("product_id")));
                product.setProductName(cursor.getString(cursor.getColumnIndex("product_Name")));
                product.setProductQty(cursor.getString(cursor.getColumnIndex("product_Qty")));
                product.setProductPrice(cursor.getString(cursor.getColumnIndex("product_Price")));
                product.setImageUrl(cursor.getString(cursor.getColumnIndex("image_Url")));
                product.setFilter_Name(cursor.getString(cursor.getColumnIndex("filter_name")));

                // Adding user record to list
                prodlist.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return prodlist;
    }

    // <<------------------- delete one data  of User table by id    --------------->>
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.execSQL("DELETE FROM user WHERE user_id=?" ,new String[]{String.valueOf(user.getId())} );
        db.close();
    }

    // <<------------------- delete one data  of product table by name    --------------->>
    public void deleteProduct(Products product) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        long result = db.delete("product","product_Name=?" ,new String[]{String.valueOf(product.getProductName())} );
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show();

        }
        db.close();
    }



    // <<------------------- check user if existe or no by his email   --------------->>
    // <<------------------- if this function c return true hi should change his email     --------------->>
    public boolean checkUser(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where user_email=?",new String[]{email});
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    // <<------------------- make sure  if the email and pass is correct   --------------->>
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where user_email=? and user_password=?",new String[]{email,password});
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    // <<-------------------  return name of user    --------------->>
    @SuppressLint("Range")
   public String  checkUserID(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery("select * from user where user_email=? and user_password=?",new String[]{email,password});
       cursor.moveToFirst();
       String name_user= cursor.getString(cursor.getColumnIndex("user_name"));
       cursor.close();
       db.close();
       // return user
       return name_user;
    }
}
