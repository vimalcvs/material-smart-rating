package com.vimalcvs.ratingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.vimalcvs.materialrating.MaterialFeedbackApp;
import com.vimalcvs.materialrating.MaterialRatingApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.rate_ok);
        button.setOnClickListener(v -> {
            //Rating
            MaterialRatingApp materialRatingApp = new MaterialRatingApp (this);
            materialRatingApp.showNow(getSupportFragmentManager(), "");
        });

        Button feedback_ok = findViewById(R.id.feedback_ok);
        feedback_ok.setOnClickListener(v -> {
            //Feedback
            MaterialFeedbackApp bottomSheetDialog = new MaterialFeedbackApp ("youremailid@gmail.com");
            bottomSheetDialog.showNow(getSupportFragmentManager(), "");
        });
    }
}