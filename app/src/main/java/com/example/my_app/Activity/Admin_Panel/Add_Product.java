package com.example.my_app.Activity.Admin_Panel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.my_app.R;
import com.example.my_app.model.Products;
import com.example.my_app.sql.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class Add_Product extends AppCompatActivity {

    private TextInputEditText textInputEditTextname,textInputEditTextqty,textInputEditTextprice,
            textInputEditTextfilter,textInputEditTextimage;
    String image;
    CircularProgressButton add_button ;
    Products prod ;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        textInputEditTextname = (TextInputEditText) findViewById (R.id.Name);
        textInputEditTextqty = (TextInputEditText) findViewById (R.id.Quantity);
        textInputEditTextprice = (TextInputEditText) findViewById (R.id.Price);
        textInputEditTextimage=(TextInputEditText) findViewById(R.id.ImageUrl);
        textInputEditTextfilter=(TextInputEditText) findViewById(R.id.filter);
        add_button =(CircularProgressButton) findViewById(R.id.AddButton) ;



       mydb = new DBHelper(Add_Product.this);
        prod = new Products();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //image = textInputEditTextimage.getText().toString().trim();
                prod.setProductName(textInputEditTextname.getText().toString());
                prod.setProductQty(textInputEditTextqty.getText().toString());
                prod.setProductPrice(textInputEditTextprice.getText().toString());
                prod.setImageUrl(textInputEditTextimage.getText().toString());
                prod.setFilter_Name(textInputEditTextfilter.getText().toString());

             //   String message1 = prod.getProductName();
               //
                mydb.addProduct(prod);
                startActivity(new Intent(Add_Product.this, Admin_Panel.class));
                overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
                finish();
            }
        });


            }


    public void toAdmin_Panel (View view){
        startActivity(new Intent(this, Admin_Panel.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }
        }