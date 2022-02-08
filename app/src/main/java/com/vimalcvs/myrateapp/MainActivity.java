package com.vimalcvs.myrateapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.vimalcvs.materialrating.MaterialFeedbackApp;
import com.vimalcvs.materialrating.MaterialRatingApp;
/**
 * Created by VimalCvs on 09/02/2022.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        Button button = findViewById(R.id.rate_ok);
        button.setOnClickListener(v -> {
            MaterialRatingApp materialRatingApp = new MaterialRatingApp (this);
            materialRatingApp.showNow(getSupportFragmentManager(), "");
        });

        Button feedback = findViewById(R.id.feedback_ok);
        feedback.setOnClickListener(v -> {
            MaterialFeedbackApp bottomSheetDialog = new MaterialFeedbackApp ("youremailid@gmail.com");
            bottomSheetDialog.showNow(getSupportFragmentManager(), "");
        });
    }
}
