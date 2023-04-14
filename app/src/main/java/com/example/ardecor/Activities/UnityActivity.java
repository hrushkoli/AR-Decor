package com.example.ardecor.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.player.UnityPlayerActivity;

public class UnityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ardecor.R.layout.activity_unity);
        Intent intent = new Intent(this, UnityPlayerActivity.class);
        startActivity(intent);
    }
}