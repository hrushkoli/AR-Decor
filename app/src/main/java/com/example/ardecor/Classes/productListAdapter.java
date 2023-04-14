package com.example.ardecor.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ardecor.Activities.ProductActivity;
import com.example.ardecor.R;

import java.util.ArrayList;

public class productListAdapter extends RecyclerView.Adapter<productListAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<productList> productListModels;

    public productListAdapter(Context context, ArrayList<productList> productListModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.productListModels = productListModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public productListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Toast.makeText(context,"onCreateViewHolder",Toast.LENGTH_LONG).show();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_list_layout,parent,false);
        return new productListAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull productListAdapter.MyViewHolder holder, int position) {
        holder.productListName.setText(productListModels.get(position).item_name);
        holder.productListPrice.setText("₹"+productListModels.get(position).item_price);
        holder.productListDescription.setText(productListModels.get(position).getItem_brand());
        Glide.with(context)
                .load(productListModels.get(position).imagelink1)
        .into(holder.productListImage);
//        holder.productListImage.setImageResource(R.drawable.ardecorcondensed);
    }

    @Override
    public int getItemCount() {
        return productListModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView productListName,productListPrice,productListDescription;
        ImageView productListImage;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            productListName = itemView.findViewById(R.id.productListName);
            productListPrice = itemView.findViewById(R.id.productListPrice);
            productListDescription = itemView.findViewById(R.id.productListDescription);
            productListImage = itemView.findViewById(R.id.productListImage);





            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    if(recyclerViewInterface!= null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
