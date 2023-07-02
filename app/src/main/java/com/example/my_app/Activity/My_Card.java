package com.example.my_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_app.Activity.Admin_Panel.Admin_Panel;
import com.example.my_app.R;
import com.example.my_app.adapter.AdminAdapter;
import com.example.my_app.adapter.Card_Adapter;
import com.example.my_app.sql.DBHelper;

public class My_Card extends AppCompatActivity {
RecyclerView recycle ;
DBHelper myDB ;
Card_Adapter cardadapter;
RadioGroup radio_grp;
RadioButton btn1 ,btn2;
int costs =15 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);

        recycle= (RecyclerView) findViewById(R.id.card_recycle_view);
        myDB = new DBHelper(this);


        cardadapter = new Card_Adapter(My_Card.this,myDB.getAllCard());
        recycle.setAdapter(cardadapter);
        recycle.setLayoutManager(new LinearLayoutManager(My_Card.this));
        radio_grp = findViewById(R.id.radioGroup);
        btn1 = findViewById(R.id.radioButton);
        btn2=findViewById(R.id.radioButton2);

        int sum =  myDB.Sum_all_price_card();
        TextView total_price =  findViewById(R.id.Total_card_price);
        //Toast.makeText(My_Card.this,String.valueOf(costs+sum), Toast.LENGTH_SHORT).show();
        total_price.setText(String.valueOf(sum+costs));
        radio_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton:
                        costs=15;
                        total_price.setText(String.valueOf(sum+costs));
                         break;
                    case R.id.radioButton2:
                        costs=7;
                        total_price.setText(String.valueOf(sum+costs));
                        break;

                }
            }
        });

        //radio_grp.clearCheck();




    }

    public void to_Main_Activty (View view){
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }


}