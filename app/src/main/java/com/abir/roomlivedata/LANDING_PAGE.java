package com.abir.roomlivedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LANDING_PAGE extends AppCompatActivity {

    Button livedata, roomdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing__page);
        livedata = findViewById(R.id.live_data);
        roomdata = findViewById(R.id.room_data);
        livedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LANDING_PAGE.this, MainActivity.class));
            }
        });
        roomdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LANDING_PAGE.this, ROOM_ACTIVITY.class));
            }
        });
    }
}
