package com.example.ardecor.Activities;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ardecor.Classes.productListAdapter;
import com.example.ardecor.Classes.productListModel;
import com.example.ardecor.Classes.RecyclerViewInterface;
import com.example.ardecor.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity implements RecyclerViewInterface {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ArrayList<productListModel> productModels = new ArrayList<>();
    String ProductURL = "https://ardecor.000webhostapp.com/api.php/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.productRecyclerView);
//        getSupportActionBar().hide();
        getSupportActionBar().setTitle("Browse Products");
//        Toast.makeText(ProductListActivity.this,"SetupFinished",Toast.LENGTH_LONG).show();
//        productListAdapter adapter = new productListAdapter(this,productModels,this);
//        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
//                RearrangeItems(adapter);
            }
        });
    }

    public void RearrangeItems(productListAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    private void setProductModels(){
//            productModels.add(new productListModel("productName",String.valueOf(i),"lorem ipsum description"));
            StringRequest stringRequest =  new StringRequest(Request.Method.GET, ProductURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray products = new JSONArray(response);
                                for (int i =0;i<products.length();i++){
                                    JSONObject productsObject = products.getJSONObject(i);
                                    String id = productsObject.getString("id");
                                    Toast.makeText(ProductListActivity.this,"ID Added",Toast.LENGTH_LONG).show();
                                    String item_name = productsObject.getString("item_name");
                                    Toast.makeText(ProductListActivity.this,"item_name",Toast.LENGTH_LONG).show();
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
                                    productListModel product = new productListModel(id,item_name,item_brand,item_category,item_price,item_rating,item_description,imagelink1,imagelink2,imagelink3,imagelink4,
                                            item_dimensions1,item_dimensions2,item_dimensions3,item_dimensions4,item_dimensions5,item_dimensions6);
                                    Toast.makeText(ProductListActivity.this,"productModelCreated",Toast.LENGTH_LONG).show();
                                    productModels.add(product);
                                    Toast.makeText(ProductListActivity.this,"productModels.add()",Toast.LENGTH_LONG).show();
                                    Toast.makeText(ProductListActivity.this,"Added Product", Toast.LENGTH_SHORT);
                                }

                            }
                            catch (JSONException e) {
                                Toast.makeText(ProductListActivity.this,"onResponseError"+e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                        }
                    },
                            new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ProductListActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                );
            Volley.newRequestQueue(this).add(stringRequest);
            Toast.makeText(ProductListActivity.this,"Volly Request Sent",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ProductListActivity.this, ProductActivity.class);
        intent.putExtra("ID",productModels.get(position).getId());
        intent.putExtra("POSITION",position);
        intent.putParcelableArrayListExtra("productModelsArray",productModels);
        startActivity(intent);
    }
}