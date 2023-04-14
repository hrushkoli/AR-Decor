package com.example.ardecor.Classes;

import android.app.Activity;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ardecor.Activities.CartActivity;
import com.example.ardecor.R;
import com.marozzi.roundbutton.RoundButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.ardecor.Classes.cartListModel.setCartModels;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    static ArrayList<cartListModel> cartListModels;

    public cartAdapter(Context context,ArrayList<cartListModel> cartListModels,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        this.cartListModels = cartListModels;
    }

    @NonNull
    @NotNull
    @Override
    public cartAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_list_layout,parent,false);
        return new MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull cartAdapter.MyViewHolder holder, int position) {
        holder.cartListQuantity.setText(String.valueOf(cartListModels.get(position).getItem_quantity()));
        holder.cartListPrice.setText(cartListModels.get(position).getItem_price());
        holder.cartListName.setText(cartListModels.get(position).getItem_name());
        holder.cartListBrand.setText(cartListModels.get(position).getItem_brand());
        Glide.with(context)
                .load(cartListModels.get(position).imagelink1)
                .into(holder.cartListImage);
    }

    @Override
    public int getItemCount() {
        return cartListModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cartListName,cartListBrand,cartListPrice,cartListQuantity;
        ImageView cartListImage;

        public MyViewHolder(@NonNull @NotNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            cartListImage = itemView.findViewById(R.id.productImageView);
            cartListName = itemView.findViewById(R.id.productName);
            cartListBrand = itemView.findViewById(R.id.productBrandText);
            cartListPrice = itemView.findViewById(R.id.productPrice);
            cartListQuantity = itemView.findViewById(R.id.productQuantity);
            TextView increaseQuantity = (TextView) itemView.findViewById(R.id.increaseQuantity);
            TextView decreaseQuantity = (TextView) itemView.findViewById(R.id.decreaseQuantity);
            RoundButton deleteButton = (RoundButton) itemView.findViewById(R.id.deleteFromCartBtn);

            increaseQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    StringRequest stringRequest =  new StringRequest(Request.Method.GET, "https://ardecor.000webhostapp.com/apiUpdateCart.php?"
                            +"username="+staticData.getUsername(v.getContext())+"&item_name="+cartListModels.get(getAdapterPosition()).getItem_name()
                            +"&update=increase",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        setCartModels(v.getContext().getApplicationContext());
                                        staticData.makeToast(v.getContext().getApplicationContext(), object.getString("success"));
                                        SwipeRefreshLayout mSwipeRefreshLayout = itemView.findViewById(R.id.swipeRefreshLayout);
                                        Intent intent = new Intent(itemView.getContext(),CartActivity.class);
                                        itemView.getContext().startActivity(intent);
                                        ((Activity)itemView.getContext()).finish();
                                    } catch (JSONException jsonException) {
                                        staticData.makeToast(v.getContext().getApplicationContext(), jsonException.getMessage());
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    staticData.makeToast(v.getContext().getApplicationContext(), error.getMessage());
                                }
                            }
                    );
                    Volley.newRequestQueue(v.getContext().getApplicationContext()).add(stringRequest);
                }
            });


            decreaseQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    StringRequest stringRequest =  new StringRequest(Request.Method.GET, "https://ardecor.000webhostapp.com/apiUpdateCart.php?"
                            +"username="+staticData.getUsername(v.getContext())+"&item_name="+cartListModels.get(getAdapterPosition()).getItem_name()
                            +"&update=increase",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        setCartModels(v.getContext().getApplicationContext());
                                        staticData.makeToast(v.getContext().getApplicationContext(), object.getString("success"));
                                    } catch (JSONException jsonException) {
                                        staticData.makeToast(v.getContext().getApplicationContext(), jsonException.getMessage());
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    staticData.makeToast(v.getContext().getApplicationContext(), error.getMessage());
                                }
                            }
                    );
                    Volley.newRequestQueue(v.getContext().getApplicationContext()).add(stringRequest);
                }
            });


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    StringRequest stringRequest =  new StringRequest(Request.Method.GET, "https://ardecor.000webhostapp.com/apiUpdateCart.php?"
                            +"username="+staticData.getUsername(v.getContext())+"&item_name="+cartListModels.get(getAdapterPosition()).getItem_name()
                            +"&update=delete",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        setCartModels(v.getContext().getApplicationContext());
                                        staticData.makeToast(v.getContext().getApplicationContext(), object.getString("success"));
                                    } catch (JSONException jsonException) {
                                        staticData.makeToast(v.getContext().getApplicationContext(), jsonException.getMessage());
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    staticData.makeToast(v.getContext().getApplicationContext(), error.getMessage());
                                }
                            }
                    );
                    Volley.newRequestQueue(v.getContext().getApplicationContext()).add(stringRequest);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
