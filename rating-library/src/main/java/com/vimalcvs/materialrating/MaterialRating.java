package com.vimalcvs.materialrating;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.vimalcvs.materialratinglib.R;

/**
 * Created by VimalCvs on 02/11/2020.
 */

public class MaterialRating extends DialogFragment implements RatingBar.OnRatingBarChangeListener {

    public static final String KEY = "fragment_rate";
    private View theDialogView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity());
        theDialogView = onCreateView(LayoutInflater.from(requireActivity()), null, savedInstanceState);
        builder.setCancelable(false);
        builder.setView(theDialogView);
        return builder.create();
    }

    @Override
    public View getView() {
        return theDialogView;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lib_material_fragment_rating, container);

        Button bt_maybeLater = view.findViewById(R.id.bt_maybeLater);
        bt_maybeLater.setOnClickListener(cancelButton -> dismiss());

        Button bt_ratingSend = view.findViewById(R.id.bt_ratingSend);
        bt_ratingSend.setOnClickListener(send -> Toast.makeText( getActivity(), "Please select 5 star rating!", Toast.LENGTH_SHORT).show());

        RatingBar bt_ratingBar = view.findViewById(R.id.bt_ratingBar);
        bt_ratingBar.setOnRatingBarChangeListener(this);

        return view;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        if (v >= 5) {
            String packageName = requireActivity().getPackageName();
            Intent intent;
            try {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
                startActivity(intent);
            } catch (ActivityNotFoundException activityNotFoundException) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
                startActivity(intent);
            }
            dismiss();
        } else if (v > 0) {
            DialogManager.showMaterialFeedback(getActivity(), v,"null");
            dismiss();
        }
    }
}
