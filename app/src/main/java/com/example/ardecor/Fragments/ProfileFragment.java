package com.example.ardecor.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ardecor.Activities.CartActivity;
import com.example.ardecor.Activities.LoginActivity;
import com.example.ardecor.Activities.SignupActivity;
import com.example.ardecor.R;
import com.marozzi.roundbutton.RoundButton;

import org.w3c.dom.Text;

import static com.example.ardecor.Classes.cartListModel.setCartModels;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor=saved_values.edit();
        NavController navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
        View profileView = inflater.inflate(R.layout.fragment_profile, container, false);
        View profileEmptyView = inflater.inflate(R.layout.fragment_profile_empty, container, false);
        if (!saved_values.getString("username","null").equals("null")){
                TextView logoutBtn = profileView.findViewById(R.id.logoutBtn);
                logoutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.remove("username");
                        editor.commit();
                        navController.popBackStack();
                        navController.navigate(R.id.profile_fragment);
                    }
                });

                TextView cartBtn = profileView.findViewById(R.id.viewCart);
                cartBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getActivity(), CartActivity.class);
                        startActivity(intent);
                    }
                });
            TextView nameText = profileView.findViewById(R.id.nameText);
            nameText.setText(saved_values.getString("username","null"));
            return profileView;
            }
        RoundButton loginBtn = profileEmptyView.findViewById(R.id.loginButton);
        RoundButton signupBtn = profileEmptyView.findViewById(R.id.signupBtn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                navController.popBackStack();
//                navController.navigate(R.id.home_fragment);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), SignupActivity.class);
                startActivity(intent);
            }
        });
        return profileEmptyView;
    }
}