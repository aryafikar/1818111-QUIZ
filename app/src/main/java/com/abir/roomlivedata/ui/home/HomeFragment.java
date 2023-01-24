package com.abir.roomlivedata.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.abir.roomlivedata.CARTDATABASE_VIEWMODEL;
import com.abir.roomlivedata.CartViewModel;
import com.abir.roomlivedata.DATABASE.CART_MODEL;
import com.abir.roomlivedata.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    CartViewModel cartViewModel;
    Button cart_add;
    TextView textView, Normal, Dynamic, Set;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        cartViewModel = ViewModelProviders.of(getActivity()).get(CartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        textView = root.findViewById(R.id.text_home);
        Normal = root.findViewById(R.id.home_normal);
        Dynamic = root.findViewById(R.id.home_dynamic);
        Set = root.findViewById(R.id.home_set);
        cart_add = root.findViewById(R.id.cart_add);
        cart_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Change", "Click Data");
                //cartViewModel.setData("2");
                String c = "2";
                cartViewModel.getText().setValue(c);
                cartViewModel.getTextDynamic().setValue(c);
                cartViewModel.setDatacart(c);
                //homeViewModel.getText().postValue("abir");
            }
        });
        homeViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        cartViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Normal.setText(context.getResources().getString(R.string.title_normal).concat(" " + s));
            }
        });
        cartViewModel.getTextDynamic().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Dynamic.setText(context.getResources().getString(R.string.title_dynamic).concat(" " + s));
            }
        });
        cartViewModel.cart_number.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Set.setText(context.getResources().getString(R.string.title_set).concat(" " + s));
            }
        });
        return root;
    }
}