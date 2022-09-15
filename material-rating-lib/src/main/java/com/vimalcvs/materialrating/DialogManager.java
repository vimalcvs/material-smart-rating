package com.vimalcvs.materialrating;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

/**
 * Created by VimalCvs on 02/11/2020.
 */

public class DialogManager {

    public static void showMaterialFeedback(Context context, float rating, String email) {
        FragmentManager fragmentManager = getFragManager(context);
        MaterialFeedback materialFeedback = new MaterialFeedback(email);
        materialFeedback.setRating(rating);
        materialFeedback.show(fragmentManager, MaterialRating.KEY);
    }

    private static FragmentManager getFragManager(Context context){
        AppCompatActivity activity = (AppCompatActivity) context;
        return activity.getSupportFragmentManager();
    }
}