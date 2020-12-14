package com.vimalcvs.materialrating.util;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import com.vimalcvs.materialratinglib.R;
/**
 * Created by VimalCvs on 02/11/2020.
 */

public class RateDialogFrag extends DialogFragment implements RatingBar.OnRatingBarChangeListener{

    public static final String KEY = "fragment_rate";


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_rate, container);

        RatingBar rbStars = view.findViewById(R.id.rb_stars);
        rbStars.setOnRatingBarChangeListener(this);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        final Button rateUs = view.findViewById(R.id.bt_send);
        final LinearLayoutCompat later = view.findViewById(R.id.bt_no);


        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getActivity(), "Please select 5 star rating!", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if (rating >= 4) {
            String packageName = getActivity().getPackageName();
            Intent it;
            try {
                it = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
                startActivity(it);
            } catch (android.content.ActivityNotFoundException anfe) {
                it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
                startActivity(it);
            }
            dismiss();
        } else if (rating > 0) {
            RateDialogManager.showRateDialogFeedback(getActivity(), rating);
            dismiss();
        }
    }
}