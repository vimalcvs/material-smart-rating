package com.vimalcvs.myrateapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vimalcvs.materialrating.util.RateDialogManager;
/**
 * Created by VimalCvs on 02/11/2020.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        LinearLayout linearLayout = findViewById(R.id.rate_ok);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///Rate Dialog
                RateDialogManager.showRateDialog(MainActivity.this, savedInstanceState);

            }
        });

    }

}
