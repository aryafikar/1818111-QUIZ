package com.abir.roomlivedata;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.abir.roomlivedata.DATABASE.CART_MODEL;
import com.google.android.material.textfield.TextInputEditText;

public class ROOM_ACTIVITY extends AppCompatActivity {

    Context context;
    TextInputEditText inputEditText;
    TextView result;
    Button save, edit, delete, deleteall;
    CARTDATABASE_VIEWMODEL cartdatabaseViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room__activity);
        context = ROOM_ACTIVITY.this;
        cartdatabaseViewmodel = ViewModelProviders.of(ROOM_ACTIVITY.this).get(CARTDATABASE_VIEWMODEL.class);
        inputEditText = findViewById(R.id.room_input);
        result = findViewById(R.id.room_result);
        save = findViewById(R.id.room_save);
        edit = findViewById(R.id.room_edit);
        delete = findViewById(R.id.room_delete);
        deleteall = findViewById(R.id.room_delete_all);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(inputEditText.getEditableText().toString().trim())) {
                    CART_MODEL save = new CART_MODEL(inputEditText.getEditableText().toString().trim());
                    cartdatabaseViewmodel.insert(save);
                    //Toast.makeText(ROOM_ACTIVITY.this, "INPUT IS NOT EMPTY", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ROOM_ACTIVITY.this, "INPUT IS EMPTY", Toast.LENGTH_SHORT).show();
                }

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(inputEditText.getEditableText().toString().trim())) {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("Find name", result.getText().toString().trim());
                                    final CART_MODEL c = cartdatabaseViewmodel.find_cart(new CART_MODEL(result.getText().toString().trim()));
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            if (c != null && c.getcartcount() != null) {
                                                //c.setCart_Number("DONE");
                                                cartdatabaseViewmodel.update_cart(c, c.getCart_Number(), inputEditText.getEditableText().toString().trim());
                                                result.setText(c.getcartcount());
                                                Log.d("Find", c.getcartcount());
                                            } else {
                                                Toast.makeText(ROOM_ACTIVITY.this, "NOTHING FOUND", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(ROOM_ACTIVITY.this, "INPUT IS EMPTY", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(inputEditText.getEditableText().toString().trim())) {
                    //cartdatabaseViewmodel.Delete(new CART_MODEL("Abir"));
                    cartdatabaseViewmodel.Deletesingle(new CART_MODEL("Abir"), inputEditText.getEditableText().toString().trim());
                } else {
                    Toast.makeText(ROOM_ACTIVITY.this, "INPUT IS EMPTY", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartdatabaseViewmodel.DeleteALL(new CART_MODEL("Abir"));
            }
        });
        cartdatabaseViewmodel.getAllWords().observe(ROOM_ACTIVITY.this, new Observer<CART_MODEL>() {
            @Override
            public void onChanged(@Nullable final CART_MODEL words) {
                // Update the cached copy of the words in the adapter.
                if (words != null && words.getcartcount() != null) {
                    result.setText(words.getcartcount());
                }
            }
        });
    }
}
