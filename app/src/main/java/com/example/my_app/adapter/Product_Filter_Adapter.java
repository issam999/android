package com.example.my_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_app.R;
import com.example.my_app.model.Product_Filter;
import com.example.my_app.model.Products;
import com.example.my_app.sql.DBHelper;


import java.util.List;

public class Product_Filter_Adapter extends RecyclerView.Adapter<Product_Filter_Adapter.ProductViewHolder> {

    Context context;
    List<Product_Filter> productFilterList;
    DBHelper myDB ;
    //List<Products> prod_list ;
    //String filter_name;

    public Product_Filter_Adapter(Context context, List<Product_Filter> productFilterList) {
        this.context = context;
        this.productFilterList = productFilterList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.filter_row_item, parent, false);
        // lets create a recyclerview row item layout file
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.catagoryName.setText(productFilterList.get(position).get_Filter_Name());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB = new DBHelper(context);
                Toast.makeText(context,productFilterList.get(position).get_Filter_Name(), Toast.LENGTH_SHORT).show();
                //prod_list = new ArrayList<>();
                //prod_list = myDB.get_filtred_product(filter_name);

                StringBuffer buffer = new StringBuffer();
                for (Products p : myDB.get_filtred_product(productFilterList.get(position).get_Filter_Name())) {
                    buffer.append("Name : " + p.getProductName() + "\n");
                    buffer.append("Quantity : " + p.getProductQty() + "\n");
                    buffer.append("Price : " + p.getProductPrice() + "\n");
                    buffer.append("Filter Name : " + productFilterList.get(position).get_Filter_Name() + "\n");
                    buffer.append("\n");

                }


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);

                builder.setTitle(productFilterList.get(position).get_Filter_Name() + " Liste");
                builder.setMessage(buffer.toString());

                builder.show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return productFilterList.size();
    }


    public static final class ProductViewHolder extends RecyclerView.ViewHolder{


        TextView catagoryName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            catagoryName = itemView.findViewById(R.id.cat_name);

        }
    }

}
