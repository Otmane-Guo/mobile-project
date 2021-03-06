package com.example.codemagictest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.codemagictest.Model.Product;
import com.example.codemagictest.ProductInfoActivity;
import com.example.codemagictest.R;

import java.util.ArrayList;
import java.util.Locale;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {
    ArrayList<Product> productList;
    ArrayList<Product> arrayList = new ArrayList<Product>();
    private Context mContext;

    public ProductListAdapter(Context context, ArrayList<Product> productList)
    {
        this.productList = productList;
        mContext = context;
        this.arrayList.addAll(productList);
    }

    @NonNull
    @Override
    public ProductListAdapter.ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_card, parent, false);
        return new ProductListAdapter.ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductListViewHolder holder, int position) {
        Log.d("ProductAdapter", "onBindViewHolder: called.");

        Product product = productList.get(position);
        Glide.with(mContext)
                .asBitmap()
                .load(product.getImages()[0])
                .into(holder.productImage);

        //holder.productImage.setImageResource(product.getId());
        holder.name.setText(product.getName());
        holder.desc.setText(product.getDescription());
        holder.price.setText(product.getPrice()+"");
        holder.rating.setRating(product.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ProductListAdapter", "onClick: clicked on product: " + holder.name.getText());
                Toast.makeText(mContext, holder.name.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ProductInfoActivity.class);
                Product product = productList.get(position);
                intent.putExtra("productSelected", product);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductListViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView name, desc, price;
        RatingBar rating;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            desc = itemView.findViewById(R.id.product_desc);
            price = itemView.findViewById(R.id.product_price);
            rating = itemView.findViewById(R.id.product_rating);
        }
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        productList.clear();
        if(charText.length() == 0){
            productList.addAll(arrayList);
        }
        else {
            for(Product product : arrayList){
                if(product.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    Log.d("found", product.getName()+" found u hehe!");
                    productList.add(product);
                }
                /*if(restaurant.getStatus().toLowerCase(Locale.getDefault()).contains(charText)){
                    trestaurants.add(restaurant);
                }*/
            }
        }
        notifyDataSetChanged();
    }
}
