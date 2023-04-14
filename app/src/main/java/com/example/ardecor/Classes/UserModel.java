package com.example.ardecor.Classes;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserModel {
    static String username,password, firstName,lastName,phone,city,pincode,address;
    static String URL = "https://ardecor.000webhostapp.com/apifetchUserData.php?username=";

    public UserModel(String username) {
        this.username = username;
    }

    public static void fetchData(String username,String password){
            StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL+username,
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
//                                        Toast.makeText(LoginActivity.this,"Successfully Logged In",Toast.LENGTH_LONG).show();
//                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    }
                                    else{
//                                        Toast.makeText(LoginActivity.this,"Unable to Log In, Check your Credentials",Toast.LENGTH_LONG).show();
                                    }
                                }

                            }
                            catch (JSONException e) {
//                                Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
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
//            Volley.newRequestQueue().add(stringRequest);
//        Toast.makeText(LoginActivity.this,"Volley Request Sent",Toast.LENGTH_LONG).show();


    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
