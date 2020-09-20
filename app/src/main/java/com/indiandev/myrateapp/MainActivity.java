package com.indiandev.myrateapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.indiandev.smartrateapp.util.RateDialogManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout linearLayout = findViewById(R.id.rate_ok);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateDialogManager.showRateDialog(MainActivity.this, savedInstanceState);
            }
        });


    }
}