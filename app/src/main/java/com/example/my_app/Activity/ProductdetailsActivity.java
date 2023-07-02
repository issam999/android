package com.example.my_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_app.Activity.Admin_Panel.Admin_Panel;
import com.example.my_app.R;
import com.example.my_app.model.Card;
import com.example.my_app.sql.DBHelper;

public class ProductdetailsActivity extends AppCompatActivity {
    TextView description;
    int count = 0;
    Button add_card;
    TextView qtetxt, pricee, name;
    ImageView image;
    DBHelper myDB;
    Card card;
    String qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        description = findViewById(R.id.prd_description);
        qtetxt = findViewById(R.id.prd_qty);
        pricee = findViewById(R.id.prd_price);
        name = findViewById(R.id.prd_name);
        image = findViewById(R.id.prd_image);
        String Name = getIntent().getStringExtra("name");
        qty = getIntent().getStringExtra("qty");
        String prd_price = getIntent().getStringExtra("price");
        String image2 = getIntent().getStringExtra("image");
        //id = getIntent().getStringExtra("id");
        name.setText(Name);
        pricee.setText(prd_price);
        int resID = getResources().getIdentifier(image2, "drawable", getPackageName());
        image.setImageResource(resID);
        myDB = new DBHelper(ProductdetailsActivity.this);

        card = new Card();
        add_card = findViewById(R.id.add_cart);
        add_card.setOnClickListener(view -> {
            card.setCard_Prod_Name(Name);
            card.setCard_Qty(qtetxt.getText().toString());
            boolean Price_isNumeric = true;
            for (int i = 0; i < prd_price.length(); i++) {
                if (!Character.isDigit(prd_price.charAt(i))) {
                    Price_isNumeric = false;
                }
            }
            boolean qty_isNumeric = true;
            for (int i = 0; i < qtetxt.length(); i++) {
                if (!Character.isDigit(qtetxt.getText().toString().charAt(i))) {
                    qty_isNumeric = false;
                }
            }
            if ((Price_isNumeric) && (qty_isNumeric)) {
                int p = Integer.parseInt(prd_price);
                int q = Integer.parseInt(qtetxt.getText().toString());
                card.setSelected_qty(qtetxt.getText().toString());
                card.setCard_Final_Price(String.valueOf(p * q));
            } else {
                card.setCard_Final_Price(prd_price);
            }
            myDB.Add_Data_To_Card(card);
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        }); }
    public void to_home_page(View view) {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }
    public void increment(View v) {
        try {
            int max = Integer.parseInt(qty);
            count++;
            if (count <= max) {
                qtetxt.setText("" + count);
            }
        } catch (Exception e) {
            Toast.makeText(ProductdetailsActivity.this, "Quantity is not a number ", Toast.LENGTH_SHORT).show();
        }
    }

    public void decrement(View v) {
        try {
            if (count <= 0) count = 1;
            else {
                count = 1;
            }
            qtetxt.setText("" + count);
        } catch (Exception e) {
            Toast.makeText(ProductdetailsActivity.this, "Quantity is not a number ", Toast.LENGTH_SHORT).show();
        } }}