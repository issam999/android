package com.example.my_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.my_app.Activity.Admin_Panel.Add_Product;
import com.example.my_app.Activity.Admin_Panel.Admin_Panel;
import com.example.my_app.R;
import com.example.my_app.adapter.Home_Product_Adapter;
import com.example.my_app.adapter.Product_Filter_Adapter;
import com.example.my_app.model.Product_Filter;
import com.example.my_app.model.Products;
import com.example.my_app.sql.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Product_Filter_Adapter productFilterAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    Home_Product_Adapter homeProductAdapter;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView Text_Name= (TextView) findViewById(R.id.NameUser);
        String Name = getIntent().getStringExtra("name");
        Text_Name.setText(Name);


        TextView logout = findViewById(R.id.logout);
        logout.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
            finish();

        });



       // <--- here we get all product than we put product filter name in list --->
        List<Product_Filter> productFilterList = new ArrayList<>();
        myDB = new DBHelper(this);

        for (String p : myDB.get_distinct_filter_name()) {
            Product_Filter prd = new Product_Filter();
            prd.set_Filter_Name(p);
            productFilterList.add(prd);

        }


        setProductRecycler(productFilterList);



        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        homeProductAdapter = new Home_Product_Adapter(this, myDB.getAllProducts());
        prodItemRecycler.setAdapter(homeProductAdapter);


        Button my_card = (Button) findViewById(R.id.my_card_btn);
        my_card.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, My_Card.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,android.R.anim.slide_out_right);



        });


    }

    private void setProductRecycler(List<Product_Filter> productFilterList) {

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productFilterAdapter = new Product_Filter_Adapter(this, productFilterList);
        productCatRecycler.setAdapter(productFilterAdapter);

    }

    private void Logout(View v ){



    }


}