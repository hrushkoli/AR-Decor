package com.example.ardecor.Activities;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ardecor.Classes.UserModel;
import com.example.ardecor.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.ardecor.Classes.md5.getMd5;

public class LoginActivity extends AppCompatActivity {
    String URL = "https://ardecor.000webhostapp.com/apiLogin.php";
    UserModel user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Button loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText)findViewById(R.id.usernameView)).getText().toString().trim();
                String unhashedPassword = ((EditText)findViewById(R.id.passwordView)).getText().toString().trim();
                String password = getMd5(unhashedPassword);
                tryLogin(username,password);
            }
        });
    }

    private void tryLogin(String username,String password){
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL+"?username="+username+"&password="+password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray users = new JSONArray();
                            users.put(object);
                            for (int i =0;i<users.length();i++){
                                JSONObject userObject = users.getJSONObject(i);
                                String successBool = userObject.getString("success");
                                String username = userObject.getString("username");
                                if(successBool.equals("true")){
                                    Toast.makeText(LoginActivity.this,"Successfully Logged In",Toast.LENGTH_LONG).show();
                                    user = new UserModel(username);
                                    SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor=saved_values.edit();
                                    editor.putString("username", username);
                                    editor.commit();
                                    if (saved_values.getString("username","null").equals(username)){
                                        finish();
                                    }else{
                                        saved_values.getString("username","null");
                                    }
                                }
                                else{
                                    Toast.makeText(LoginActivity.this,"Unable to Log In, Check your Credentials",Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                        catch (JSONException e) {
                            Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            TextView txtView = (TextView) findViewById(R.id.errorView);
                            txtView.setText(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        )
//        {@Override
//        public Map<String, String> getParams(){
//            Map<String, String> params = new HashMap<>();
//            params.put("username", "hrushkoli1");
//            params.put("password", "TestHash");
//            return mParams;
//        }
        ;
        Volley.newRequestQueue(this).add(stringRequest);
//        Toast.makeText(LoginActivity.this,"Volley Request Sent",Toast.LENGTH_LONG).show();

    }



}