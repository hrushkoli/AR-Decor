package com.example.ardecor.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ardecor.Classes.*;

import com.example.ardecor.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        try {
            productList.setProductModels(this);
            cartListModel.setCartModels(this);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent is used to switch from one activity to another.
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i); // invoke the SecondActivity.
                finish(); // the current activity will get finished.
            }
        }, 5000);
    }
}