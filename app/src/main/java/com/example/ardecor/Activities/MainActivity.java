package com.example.ardecor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.ardecor.Classes.productList;
import com.example.ardecor.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Collections;

import me.ibrahimsn.lib.OnItemReselectedListener;
import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {
    SmoothBottomBar bottomBar;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//
//        try {
//            productList.setProductModels(this);
////            Toast.makeText(getContext(),"on next line",Toast.LENGTH_LONG).show();
//        }catch (Exception e){
//            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
//        }
        try {
//            SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//            SharedPreferences.Editor editor=saved_values.edit();
//            editor.putString("username", "hrishikesh");
//            editor.remove("username");
//            editor.commit();
        }catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bottomBar = findViewById(R.id.bottomBar);
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController);
        setupSmoothBottomBar();
        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String username = saved_values.getString("username", "null");
//        Toast.makeText(this,username,Toast.LENGTH_LONG).show();

    }

    private void setupSmoothBottomBar() {
        PopupMenu popupMenu = new PopupMenu(this, null);
        popupMenu.inflate(R.menu.menu);
        Menu menu  = popupMenu.getMenu();
        bottomBar.setupWithNavController(menu, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}