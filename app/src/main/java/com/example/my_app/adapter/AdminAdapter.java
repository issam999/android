package com.example.my_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_app.Activity.Admin_Panel.Update_Admin;
import com.example.my_app.R;
import com.example.my_app.model.Products;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ProductViewHolder> {
    private Context context;
    List<Products> productsList;

    public AdminAdapter(Context context, List<Products> productsList) {

        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_row, parent, false);
        return new AdminAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Resources res = holder.itemView.getContext().getResources();

        String img = productsList.get(position).getImageUrl();
        int resID = res.getIdentifier(img, "drawable", holder.itemView.getContext().getPackageName());
        holder.prodImage.setImageResource(resID);
       // holder.prodId.setText("id"+String.valueOf(product_id.get(position)));
        holder.prodName.setText(productsList.get(position).getProductName());
        holder.prodQty.setText(productsList.get(position).getProductQty());
        holder.prodPrice.setText(productsList.get(position).getProductPrice());

        holder.filter_name.setText(productsList.get(position).getFilter_Name());
       // Toast.makeText(context,productsList.get(position).getFilter_Name(), Toast.LENGTH_SHORT).show();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_Admin.class);

                //intent.putExtra("id", String.valueOf(book_id.get(position)));
                intent.putExtra("name", productsList.get(position).getProductName());
                intent.putExtra("qty", productsList.get(position).getProductQty());
                intent.putExtra("price", productsList.get(position).getProductPrice());
                intent.putExtra("image", productsList.get(position).getImageUrl());
                intent.putExtra("filter", productsList.get(position).getFilter_Name());

                //intent.putExtra("id", String.valueOf(productsList.get(position).getProductid()));
                context.startActivity(intent);
            }});}
            @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView prodImage;
        TextView prodName, prodQty, prodPrice,filter_name;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            prodImage = itemView.findViewById(R.id.imagee);
            prodName =  itemView.findViewById(R.id.namee);
            prodPrice = itemView.findViewById(R.id.pricee);
            prodQty = itemView.findViewById(R.id.qty_choisi);
            filter_name = itemView.findViewById(R.id.filter_name);

        }
    }

}
