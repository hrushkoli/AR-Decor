package com.example.ardecor.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ardecor.Classes.RecyclerViewInterface;
import com.example.ardecor.Classes.productListAdapter;
import com.example.ardecor.Classes.productListModel;
import com.example.ardecor.Activities.ProductActivity;
import com.example.ardecor.Activities.ProductListActivity;
import com.example.ardecor.R;

import java.util.ArrayList;
import com.example.ardecor.Classes.productList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements RecyclerViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ArrayList<productList> productModels = productList.getProductModels();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        recyclerView = v.findViewById(R.id.productRecyclerView);
//        getSupportActionBar().hide();
        productListAdapter adapter = new productListAdapter(getContext(),productModels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                RearrangeItems(adapter);
            }
        });
        return v;
    }

    public void RearrangeItems(productListAdapter adapter) {
        try {
            if (productModels.isEmpty()){
//                Toast.makeText(getContext(),"is Empty", Toast.LENGTH_LONG).show();
            }
            else {
//                Toast.makeText(getContext(),"Not Empty", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), ProductActivity.class);
        intent.putExtra("ID",productModels.get(position).getId());
        intent.putExtra("POSITION",position);
        startActivity(intent);
    }
}