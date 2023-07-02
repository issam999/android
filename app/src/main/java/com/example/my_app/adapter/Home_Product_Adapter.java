package com.example.my_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_app.Activity.ProductdetailsActivity;
import com.example.my_app.R;
import com.example.my_app.model.Products;

import java.util.List;

public class Home_Product_Adapter extends RecyclerView.Adapter<Home_Product_Adapter.ProductViewHolder> {

    Context context;
    List<Products> productsList;



    public Home_Product_Adapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.products_row_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {

        Resources res = holder.itemView.getContext().getResources();

        String img = productsList.get(position).getImageUrl();
        int resID = res.getIdentifier(img, "drawable", holder.itemView.getContext().getPackageName());
        holder.prodImage.setImageResource(resID);
        holder.prodName.setText(productsList.get(position).getProductName());
        holder.prodQty.setText(productsList.get(position).getProductQty());
        holder.prodPrice.setText(productsList.get(position).getProductPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {//hedhy actionlistener lel item mta3 product recycle view fel home
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, ProductdetailsActivity.class);



                i.putExtra("name", productsList.get(position).getProductName());
                i.putExtra("qty", productsList.get(position).getProductQty());
                i.putExtra("price", productsList.get(position).getProductPrice());
                i.putExtra("image", productsList.get(position).getImageUrl());
                context.startActivity(i/*, options.toBundle()*/);



            }

        }

        );




    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView prodImage;
        TextView prodName, prodQty, prodPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            prodImage = itemView.findViewById(R.id.prod_image);
            prodName = itemView.findViewById(R.id.prod_name);
            prodPrice = itemView.findViewById(R.id.prod_price);
            prodQty = itemView.findViewById(R.id.prod_qty);


        }
    }

}
