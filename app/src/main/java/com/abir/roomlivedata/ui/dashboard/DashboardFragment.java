package com.abir.roomlivedata.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.abir.roomlivedata.CartViewModel;
import com.abir.roomlivedata.MainActivity;
import com.abir.roomlivedata.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    CartViewModel cartViewModel;
    TextView textView, Normal, Dynamic, Set;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(getActivity()).get(DashboardViewModel.class);
        cartViewModel = ViewModelProviders.of(getActivity()).get(CartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        context = getActivity();
        textView = root.findViewById(R.id.text_dashboard);
        Normal = root.findViewById(R.id.dashboared_normal);
        Dynamic = root.findViewById(R.id.dashboared_dynamic);
        Set = root.findViewById(R.id.dashboared_set);
        dashboardViewModel.getText().observe(getActivity(), new Observer<String>() {
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