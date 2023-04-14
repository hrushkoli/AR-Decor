package com.example.ardecor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ardecor.Classes.cartListModel;
import com.example.ardecor.Classes.productList;
import com.example.ardecor.Classes.productListModel;
import com.example.ardecor.Classes.staticData;
import com.example.ardecor.R;
import com.marozzi.roundbutton.RoundButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    String URI = "https://ardecor.000webhostapp.com/apiSetCart.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ardecor.R.layout.activity_product);
        getSupportActionBar().hide();
        SharedPreferences saved_vales = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = saved_vales.getString("username","null");
        int position = getIntent().getIntExtra("POSITION",0);
        String productIntentProdName = getIntent().getStringExtra("PRODUCT");
        ArrayList<productList> productModels = productList.getProductModels();
        for (int i =0;i<=productModels.size();i++){
            if (productModels.get(position).getItem_name().equals(productIntentProdName)){
                position = i;
            }
        }
        TextView productBrand = findViewById(R.id.productBrand);
        TextView productName = findViewById(R.id.productName);
        TextView price = findViewById(R.id.price);
        TextView productDescription = findViewById(R.id.productDescription);
        TextView item_dimensions1 = findViewById(R.id.item_dimensions1);
        TextView item_dimensions2 = findViewById(R.id.item_dimensions2);
        TextView item_dimensions3 = findViewById(R.id.item_dimensions3);
        TextView item_dimensions4 = findViewById(R.id.item_dimensions4);
        TextView item_dimensions5 = findViewById(R.id.item_dimensions5);
        TextView item_dimensions6 = findViewById(R.id.item_dimensions6);
        ImageView productImage = findViewById(R.id.product_image);
        productBrand.setText(productModels.get(position).getItem_brand());
        productName.setText(productModels.get(position).getItem_name());
        price.setText("₹ "+productModels.get(position).getItem_price());
        productDescription.setText(productModels.get(position).getItem_description());
        item_dimensions1.setText(productModels.get(position).getItem_dimensions1());
        if(!productModels.get(position).getItem_dimensions2().equals("null")){
            item_dimensions2.setText(productModels.get(position).getItem_dimensions2());
        }
        if(!productModels.get(position).getItem_dimensions3().equals("null")){
            item_dimensions3.setText(productModels.get(position).getItem_dimensions3());
        }
        if(!productModels.get(position).getItem_dimensions4().equals("null")){
            item_dimensions4.setText(productModels.get(position).getItem_dimensions4());
        }
        if(!productModels.get(position).getItem_dimensions5().equals("null")){
            item_dimensions5.setText(productModels.get(position).getItem_dimensions5());
        }
        if(!productModels.get(position).getItem_dimensions6().equals("null")){
            item_dimensions6.setText(productModels.get(position).getItem_dimensions6());
        }
        Glide.with(this)
                .load(productModels.get(position).getImagelink1())
                .into(productImage);
        RoundButton arViewButton = findViewById(R.id.arViewButton);
        RoundButton addToCartButton = findViewById(R.id.addToCartButton);
        arViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),UnityActivity.class);
                startActivity(intent);
            }
        });
        int finalPosition = position;
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saved_vales.getString("username","null").equals("null")){
                    Toast.makeText(getApplicationContext(),"You need to be Logged In to Add Product to Cart!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    String URL = URI+"?username="+saved_vales.getString("username","null")+
                            "&item_name="+productModels.get(finalPosition).getItem_name()+
                            "&item_price="+productModels.get(finalPosition).getItem_price()+
                            "&imagelink1="+productModels.get(finalPosition).getImagelink1()+
                            "&item_brand="+productModels.get(finalPosition).getItem_brand();
                    addToCart(URL);
                }
            }
        });

    }

    public void addToCart(String URL){
        Toast.makeText(ProductActivity.this,"Processing",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray users = new JSONArray();
                            users.put(object);
//                            Toast.makeText(SignupActivity.this,"arrayCreated",Toast.LENGTH_LONG).show();
                            for (int i =0;i<users.length();i++){
                                JSONObject userObject = users.getJSONObject(i);
                                String successBool = userObject.getString("success");
                                String username = userObject.getString("username");
                                cartListModel.setCartModels(getApplicationContext());
                                Toast.makeText(ProductActivity.this,successBool,Toast.LENGTH_SHORT).show();
                                }
                            }
                        catch (JSONException e) {
                            TextView txtView = (TextView) findViewById(R.id.errorView);
                            txtView.setText("Please fill all Fields");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        Volley.newRequestQueue(this).add(stringRequest);
    }
}