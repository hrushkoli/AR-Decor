package com.example.ardecor.Classes;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class cartListModel {
    String id, item_name,item_price,imagelink1,item_brand,username;
    int item_quantity;
    public static ArrayList<cartListModel> cartModels = new ArrayList<>();

    public cartListModel(String id, String item_name, String item_brand,String item_price, int item_quantity,String imagelink1,String username) {
        this.id = id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.imagelink1 = imagelink1;
        this.item_brand = item_brand;
        this.item_quantity = item_quantity;
        this.username = username;
    }



    public static void setCartModels(Context ctx){
        cartModels.clear();
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, "https://ardecor.000webhostapp.com/apiFetchCart.php/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);
                            for (int i =0;i<products.length();i++){
                                JSONObject productsObject = products.getJSONObject(i);
                                String id = productsObject.getString("id");
                                String item_name = productsObject.getString("item_name");
                                String item_brand = productsObject.getString("item_brand");
                                String item_price = productsObject.getString("item_price");
                                String imagelink1 = productsObject.getString("imagelink1");
                                String username = productsObject.getString("username");
                                int item_quantity = productsObject.getInt("item_quantity");
                                cartListModel product = new cartListModel(id,item_name,item_brand,item_price,item_quantity,imagelink1, username);
                                cartModels.add(product);
                                }
                        }
                        catch (JSONException e) {
                            Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        Volley.newRequestQueue(ctx).add(stringRequest);
    }

    public String getId() {
        return id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public String getImagelink1() {
        return imagelink1;
    }

    public String getItem_brand() {
        return item_brand;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public String getUsername() {
        return username;
    }

    public static ArrayList<cartListModel> getCartModels(String username) {
        ArrayList<cartListModel> tempModel = new ArrayList<>();
        for (int i=0;i<cartModels.size();i++){
            if (cartModels.get(i).getUsername().equals(username)){
                tempModel.add(cartModels.get(i));
            }
        }
        return tempModel;
    }

}


