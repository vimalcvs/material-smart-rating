package com.vimalcvs.myrateapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vimalcvs.materialrating.util.RateDialogManager;
/**
 * Created by VimalCvs on 02/11/2020.
 */
public class MainActivity extends AppCompatActivity {

    Bundle rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        Button button = findViewById(R.id.rate_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Rating Dialog
                RateDialogManager.showRateDialog(MainActivity.this, rating);

            }
        });
    }
}
