package com.example.ardecor.Activities;

import com.example.ardecor.Classes.md5;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ardecor.Classes.UserModel;
import com.example.ardecor.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.ardecor.Classes.md5.getMd5;

public class SignupActivity extends AppCompatActivity {
    String URI = "https://ardecor.000webhostapp.com/apiSignup.php";
    String URL;
//    String URL = "https://ardecor.000webhostapp.com/apiSignup.php?firstname=Hrishikesh&lastname=Koli&password=TestHash&username=hrushkoli1&phone=9820703453&city=Mumbai";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        Button signupBtn = findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText)findViewById(R.id.usernameView)).getText().toString().trim();
                String unhashedPassword = ((EditText)findViewById(R.id.passwordView)).getText().toString().trim();
                String password = getMd5(unhashedPassword);
                String firstname = ((EditText)findViewById(R.id.firstnameView)).getText().toString().trim();
                String lastname = ((EditText)findViewById(R.id.lastnameView)).getText().toString().trim();
                String city = ((EditText)findViewById(R.id.cityView)).getText().toString().trim();
                String phone = ((EditText)findViewById(R.id.phoneView)).getText().toString().trim();
                URL = URI+"?firstname="+firstname+"&lastname="+lastname+"&username="+username+"&password="+password+"&phone="+phone+"&city="+city;
                trySignup();
            }
        });
    }



    private void trySignup(){
        Toast.makeText(SignupActivity.this,"Processing",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray users = new JSONArray();
                            users.put(object);
//                            Toast.makeText(SignupActivity.this,"arrayCreated",Toast.LENGTH_LONG).show();
                            for (int i =0;i<users.length();i++){
                                JSONObject userObject = users.getJSONObject(i);
                                String successBool = userObject.getString("success");
                                String username = userObject.getString("username");
                                if (successBool.equals("User Already Exists!")){
                                    Toast.makeText(SignupActivity.this,successBool+" try a different username.",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(SignupActivity.this,successBool,Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        }
                        catch (JSONException e) {
                            TextView txtView = (TextView) findViewById(R.id.errorView);
                            txtView.setText("Please fill all Fields");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        Volley.newRequestQueue(this).add(stringRequest);
//        Toast.makeText(LoginActivity.this,"Volley Request Sent",Toast.LENGTH_LONG).show();

    }



}