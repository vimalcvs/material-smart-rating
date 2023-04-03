package com.vimalcvs.myapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.vimalcvs.materialrating.RatingDialog;

public class MainActivity extends AppCompatActivity {
    private final String email = "example@email.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rating_app = findViewById(R.id.rating_app);
        rating_app.setOnClickListener(v -> {
            RatingDialog.showRateAppDialogAuto(getSupportFragmentManager(), this, 1, email);
        });

        Button feedback_app = findViewById(R.id.feedback_app);
        feedback_app.setOnClickListener(v -> {
            RatingDialog.showRateAppDialogNormal(getSupportFragmentManager(), this, email);

        });
    }
}