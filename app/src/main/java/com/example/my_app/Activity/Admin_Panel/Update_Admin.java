package com.example.my_app.Activity.Admin_Panel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.my_app.Activity.LoginActivity;
import com.example.my_app.R;
import com.example.my_app.model.Products;
import com.example.my_app.sql.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class Update_Admin extends AppCompatActivity {
    private TextInputEditText textInputEditTextname,textInputEditTextqty,textInputEditTextprice,textInputEditTextfilter,
            textInputEditTextimage;
    CircularProgressButton update_button , delete_btn ;
    Products prod ;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);


        textInputEditTextname = (TextInputEditText) findViewById (R.id.Name);
        textInputEditTextqty = (TextInputEditText) findViewById (R.id.Quantity);
        textInputEditTextprice = (TextInputEditText) findViewById (R.id.Price);
        textInputEditTextimage=(TextInputEditText) findViewById(R.id.ImageUrl);
        textInputEditTextfilter=(TextInputEditText) findViewById(R.id.text_filter_name);

        String Name = getIntent().getStringExtra("name");
        String qty = getIntent().getStringExtra("qty");
        String price = getIntent().getStringExtra("price");
        String image = getIntent().getStringExtra("image");
        String filter_name = getIntent().getStringExtra("filter");
         //id = getIntent().getStringExtra("id");
        textInputEditTextname.setText(Name);
        textInputEditTextqty.setText(qty);
        textInputEditTextprice.setText(price);
        textInputEditTextimage.setText(image);
        textInputEditTextfilter.setText(filter_name);
        //Toast.makeText(Update_Admin.this, id, Toast.LENGTH_SHORT).show();


        update_button =(CircularProgressButton) findViewById(R.id.UpdateButton) ;

        mydb = new DBHelper(Update_Admin.this);
        prod = new Products();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //prod.setProductid(id);
                prod.setProductName(textInputEditTextname.getText().toString());
                prod.setProductQty(textInputEditTextqty.getText().toString());
                prod.setProductPrice(textInputEditTextprice.getText().toString());
                prod.setImageUrl(textInputEditTextimage.getText().toString());
                prod.setFilter_Name(textInputEditTextfilter.getText().toString());

              // Toast.makeText(Update_Admin.this,prod.getProductName(), Toast.LENGTH_SHORT).show();

                //Toast.makeText(Update_Admin.this, prod.getProductPrice(), Toast.LENGTH_SHORT).show();

                mydb.updateProduct(prod,Name);
                startActivity(new Intent(Update_Admin.this, Admin_Panel.class));
                overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
                finish();
            }
        });

        delete_btn = (CircularProgressButton) findViewById(R.id.DeleteButton) ;
        delete_btn.setOnClickListener(view -> {
            prod.setProductName(textInputEditTextname.getText().toString());
            //prod.setProductid(id);
            mydb.deleteProduct(prod);
            startActivity(new Intent(this, Admin_Panel.class));
            overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
            finish();



        });
    }


    public void toAdmin_Panel (View view){
        startActivity(new Intent(this, Admin_Panel.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }


}