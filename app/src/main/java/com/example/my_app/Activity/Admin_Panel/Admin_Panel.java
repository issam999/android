package com.example.my_app.Activity.Admin_Panel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_app.Activity.LoginActivity;
import com.example.my_app.R;
import com.example.my_app.adapter.AdminAdapter;
import com.example.my_app.model.Products;
import com.example.my_app.sql.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Admin_Panel extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    AdminAdapter adminAdapter;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        add_button =(FloatingActionButton) findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data =  findViewById(R.id.no_data);

        FloatingActionButton logout = findViewById(R.id.logout);
        logout.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
            finish();

        });

       add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Panel.this, Add_Product.class);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,android.R.anim.slide_out_right);
                finish();

            }
        });
        myDB = new DBHelper(this);
 adminAdapter = new AdminAdapter(Admin_Panel.this,myDB.getAllProducts());
        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Admin_Panel.this));
        if (myDB.getAllProducts().size()==0){
        empty_imageview.setVisibility(View.VISIBLE);
        no_data.setVisibility(View.VISIBLE);}
        else {
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);}}}



























