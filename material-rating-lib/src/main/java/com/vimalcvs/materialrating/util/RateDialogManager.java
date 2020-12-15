package com.vimalcvs.materialrating.util;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
/**
 * Created by VimalCvs on 02/11/2020.
 */

public class RateDialogManager {

    public static void showRateDialog(Context context, Bundle savedInstanceState) {

         RateSPManager.updateLaunchTimes( context, savedInstanceState );

        FragmentManager fm = getFragManager( context );
        if( RateSPManager.canShowDialog( context ) && fm.findFragmentByTag(RateDialogFrag.KEY) == null ){

            RateDialogFrag dialog = new RateDialogFrag();
            dialog.setCancelable(false);
            dialog.show( fm, RateDialogFrag.KEY );
        }
    }


    public static void showRateDialogFeedback( Context context, float rating ) {
        FragmentManager fm = getFragManager( context );
        RateDialogFeedbackFrag dialog = new RateDialogFeedbackFrag();
        dialog.setRating( rating );
        dialog.setCancelable(false);
        dialog.show( fm, RateDialogFrag.KEY );
    }

    private static FragmentManager getFragManager( Context context ){
        AppCompatActivity activity = (AppCompatActivity) context;
        return activity.getSupportFragmentManager();
    }
}
