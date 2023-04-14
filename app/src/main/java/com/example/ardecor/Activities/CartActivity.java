package com.example.ardecor.Activities;

import com.example.ardecor.Classes.RecyclerViewInterface;
import com.example.ardecor.Classes.cartAdapter;
import com.example.ardecor.Classes.cartListModel;
import com.example.ardecor.Classes.productListAdapter;
import com.example.ardecor.Classes.productList;
import com.example.ardecor.Classes.staticData;
import com.example.ardecor.R;
import com.marozzi.roundbutton.RoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.ardecor.Classes.cartListModel.setCartModels;

public class CartActivity extends AppCompatActivity implements RecyclerViewInterface {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ArrayList<cartListModel> cartModels;
    String username;
    String subtotalText = "Your Subtotal is : ";
    TextView subtotalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().hide();
        username = staticData.getUsername(this);
        cartModels = cartListModel.getCartModels(username);
        recyclerView = (RecyclerView) findViewById(R.id.cartRecyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.cartRefreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter adapter = new cartAdapter(this,cartModels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                adapter.notifyItemRangeInserted(0, cartModels.size());
//                finish();
//                overridePendingTransition(0, 0);
//                startActivity(getIntent());
//                overridePendingTransition(0, 0);
            }
        });
        RoundButton checkoutBtn = findViewById(R.id.checkoutBtn);

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
        intent.putExtra("ID",cartModels.get(position).getId());
        intent.putExtra("POSITION",cartModels.get(position).getId());
        //intent.putExtra("PRODUCT",cartModels.get(position).getItem_name());
        startActivity(intent);
    }
}