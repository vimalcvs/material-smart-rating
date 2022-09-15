package com.vimalcvs.myapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.vimalcvs.materialrating.MaterialFeedback;
import com.vimalcvs.materialrating.MaterialRating;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rating_app = findViewById(R.id.rating_app);
        rating_app.setOnClickListener(v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MaterialRating feedBackDialog = new MaterialRating();
            feedBackDialog.show(fragmentManager, "rating");
        });


        Button feedback_app = findViewById(R.id.feedback_app);
        feedback_app.setOnClickListener(v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MaterialFeedback materialFeedback = new MaterialFeedback("technovimalin@gmail.com");
            materialFeedback.show(fragmentManager, "feedback");
        });
    }
}