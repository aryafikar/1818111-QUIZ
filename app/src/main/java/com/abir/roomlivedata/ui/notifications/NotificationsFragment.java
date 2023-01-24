package com.abir.roomlivedata.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.abir.roomlivedata.CARTDATABASE_VIEWMODEL;
import com.abir.roomlivedata.DATABASE.CART_MODEL;
import com.abir.roomlivedata.R;

public class NotificationsFragment extends Fragment {

    TextView textView;
    private NotificationsViewModel notificationsViewModel;
    CARTDATABASE_VIEWMODEL cartdatabaseViewmodel;
    CART_MODEL carts;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        cartdatabaseViewmodel = ViewModelProviders.of(getActivity()).get(CARTDATABASE_VIEWMODEL.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        if (carts != null) {
            CART_MODEL current = carts;
            textView.setText(current.getcartcount());
        } else {
            // Covers the case of data not being ready yet.
            textView.setText("No Word");
        }
        cartdatabaseViewmodel.getAllWords().observe(this, new Observer<CART_MODEL>() {
            @Override
            public void onChanged(@Nullable final CART_MODEL words) {
                // Update the cached copy of the words in the adapter.
                if (words != null && words.getcartcount() != null) {
                    textView.setText(words.getcartcount());
                }
            }
        });
        return root;
    }

    void setWords(CART_MODEL words) {
        carts = words;
        //notifyDataSetChanged();
    }
}