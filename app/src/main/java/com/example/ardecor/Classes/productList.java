package com.example.ardecor.Classes;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class productList implements Parcelable {
    //    String productListName,productListPrice,productListDescription,productListImage;
    String id,item_name,item_brand,item_category,item_price,item_rating,item_description;
    String imagelink1,imagelink2,imagelink3,imagelink4;
    String item_dimensions1,item_dimensions2,item_dimensions3,item_dimensions4,item_dimensions5,item_dimensions6;
    public static ArrayList<productList> productModels = new ArrayList<>();

    public static ArrayList<productList> getProductModels() {
        return productModels;
    }

    public static boolean setProductModels(Context ctx){
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, "https://ardecor.000webhostapp.com/api.php/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
//                            Toast.makeText(ctx,"setProductModels Called",Toast.LENGTH_SHORT).show();

                            JSONArray products = new JSONArray(response);
                            for (int i =0;i<products.length();i++){
                                JSONObject productsObject = products.getJSONObject(i);
                                String id = productsObject.getString("id");
                                String item_name = productsObject.getString("item_name");
                                String item_brand = productsObject.getString("item_brand");
                                String item_category = productsObject.getString("item_category");
                                String item_price = productsObject.getString("item_price");
                                String item_rating = productsObject.getString("item_rating");
                                String item_description = productsObject.getString("item_description");
                                String imagelink1 = productsObject.getString("imagelink1");
                                String imagelink2 = productsObject.getString("imagelink2");
                                String imagelink3 = productsObject.getString("imagelink3");
                                String imagelink4 = productsObject.getString("imagelink4");
                                String item_dimensions1 = productsObject.getString("item_dimensions1");
                                String item_dimensions2 = productsObject.getString("item_dimensions2");
                                String item_dimensions3 = productsObject.getString("item_dimensions3");
                                String item_dimensions4 = productsObject.getString("item_dimensions4");
                                String item_dimensions5 = productsObject.getString("item_dimensions5");
                                String item_dimensions6 = productsObject.getString("item_dimensions6");
                                productList product = new productList(id,item_name,item_brand,item_category,item_price,item_rating,item_description,imagelink1,imagelink2,imagelink3,imagelink4,
                                        item_dimensions1,item_dimensions2,item_dimensions3,item_dimensions4,item_dimensions5,item_dimensions6);
                                productModels.add(product);
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
        return true;

    }

    private static DiskBasedCache.FileSupplier getCacheDir() {
        return null;
    }

    public productList(String id, String item_name, String item_brand, String item_category, String item_price, String item_rating, String item_description, String imagelink1, String imagelink2, String imagelink3, String imagelink4, String item_dimensions1, String item_dimensions2, String item_dimensions3, String item_dimensions4, String item_dimensions5, String item_dimensions6) {
        this.id = id;
        this.item_name = item_name;
        this.item_brand = item_brand;
        this.item_category = item_category;
        this.item_price = item_price;
        this.item_rating = item_rating;
        this.item_description = item_description;
        this.imagelink1 = imagelink1;
        this.imagelink2 = imagelink2;
        this.imagelink3 = imagelink3;
        this.imagelink4 = imagelink4;
        this.item_dimensions1 = item_dimensions1;
        this.item_dimensions2 = item_dimensions2;
        this.item_dimensions3 = item_dimensions3;
        this.item_dimensions4 = item_dimensions4;
        this.item_dimensions5 = item_dimensions5;
        this.item_dimensions6 = item_dimensions6;
    }


    protected productList(Parcel in) {
        id = in.readString();
        item_name = in.readString();
        item_brand = in.readString();
        item_category = in.readString();
        item_price = in.readString();
        item_rating = in.readString();
        item_description = in.readString();
        imagelink1 = in.readString();
        imagelink2 = in.readString();
        imagelink3 = in.readString();
        imagelink4 = in.readString();
        item_dimensions1 = in.readString();
        item_dimensions2 = in.readString();
        item_dimensions3 = in.readString();
        item_dimensions4 = in.readString();
        item_dimensions5 = in.readString();
        item_dimensions6 = in.readString();
    }

    public static final Creator<productList> CREATOR = new Creator<productList>() {
        @Override
        public productList createFromParcel(Parcel in) {
            return new productList(in);
        }

        @Override
        public productList[] newArray(int size) {
            return new productList[size];
        }
    };

    public String getProductListName() {
        return item_name;
    }

    public String getProductListPrice() {
        return item_price;
    }

    public String getProductListDescription() {
        return "";
    }

    public String getProductListImage() {return imagelink1;}

    public String getId() {
        return id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_brand() {
        return item_brand;
    }

    public String getItem_category() {
        return item_category;
    }

    public String getItem_price() {
        return item_price;
    }

    public String getItem_rating() {
        return item_rating;
    }

    public String getItem_description() {
        return item_description;
    }

    public String getImagelink1() {
        return imagelink1;
    }

    public String getImagelink2() {
        return imagelink2;
    }

    public String getImagelink3() {
        return imagelink3;
    }

    public String getImagelink4() {
        return imagelink4;
    }

    public String getItem_dimensions1() {
        return item_dimensions1;
    }

    public String getItem_dimensions2() {
        return item_dimensions2;
    }

    public String getItem_dimensions3() {
        return item_dimensions3;
    }

    public String getItem_dimensions4() {
        return item_dimensions4;
    }

    public String getItem_dimensions5() {
        return item_dimensions5;
    }

    public String getItem_dimensions6() {
        return item_dimensions6;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(item_name);
        dest.writeString(item_brand);
        dest.writeString(item_category);
        dest.writeString(item_price);
        dest.writeString(item_rating);
        dest.writeString(item_description);
        dest.writeString(imagelink1);
        dest.writeString(imagelink2);
        dest.writeString(imagelink3);
        dest.writeString(imagelink4);
        dest.writeString(item_dimensions1);
        dest.writeString(item_dimensions2);
        dest.writeString(item_dimensions3);
        dest.writeString(item_dimensions4);
        dest.writeString(item_dimensions5);
        dest.writeString(item_dimensions6);
    }
}
