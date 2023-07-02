package com.example.my_app.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_app.R;
import com.example.my_app.model.Card;
import com.example.my_app.model.Products;
import com.example.my_app.sql.DBHelper;

import java.util.List;

public class Card_Adapter extends RecyclerView.Adapter<Card_Adapter.CardViewHolder> {

    Context context;
    List<Card> cardlist;
    DBHelper myDB;
    Products prod;


    public Card_Adapter(Context context, List<Card> x) {
        this.context = context;
        this.cardlist = x;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_card_item, parent, false);
        // lets create a recyclerview row item layout file
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        //<<------ set every text view with his new value ------>>
        holder.name.setText(cardlist.get(position).getCard_Prod_Name());
        holder.totale.setText(cardlist.get(position).getCard_Final_Price());
        holder.qty.setText(cardlist.get(position).getSelected_qty());

         myDB = new DBHelper(context);
        //<------ get product by name to take picture name and price of product
         prod = myDB.get_product_By_Name(cardlist.get(position).getCard_Prod_Name());
        holder.price.setText(prod.getProductPrice());

        //<----- set the picture with drawble/name_of_picture------>>
        Resources resources = holder.itemView.getContext().getResources();

        String img = prod.getImageUrl();
        int resID = resources.getIdentifier(img, "drawable", holder.itemView.getContext().getPackageName());
        holder.image_prd.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return cardlist.size();
    }


    public static final class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView image_prd;
        TextView qty, totale, name, price;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.namee);
            qty = itemView.findViewById(R.id.qty_choisi);
            totale = itemView.findViewById(R.id.total);
            price = itemView.findViewById(R.id.pricee);
            image_prd = itemView.findViewById(R.id.imagee);
        }
    }
}

