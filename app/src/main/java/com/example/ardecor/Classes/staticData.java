package com.example.ardecor.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.ardecor.Activities.CartActivity;

public class staticData {
    public static String getURI(){
        return "https://ardecor.000webhostapp.com";
    }
    public static String Link = "https://ardecor.000webhostapp.com";
    public static SharedPreferences getSavedValues (Context context){
        SharedPreferences saved_vales = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return saved_vales;
    }
    public static String getUsername (Context context){
        Toast.makeText(context,"getUsername()",Toast.LENGTH_SHORT);
        SharedPreferences saved_vales = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String username = saved_vales.getString("username","null");
        return username;
    }

    public static void makeToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    public static void reloadActivity(Activity activity){
        activity.finish();
        activity.overridePendingTransition(0, 0);
        Intent intent = new Intent(activity, CartActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    public static int getCartTotalAmount(Context context,String username) {
        int tempPrice = 0;
        for (int i = 0;i<cartListModel.cartModels.size();i++){
            if (cartListModel.cartModels.get(i).getUsername().equals(username)){
                tempPrice += Integer.parseInt(cartListModel.cartModels.get(i).getItem_price());
            }
        }
        return tempPrice;
    }
}
