package com.vimalcvs.materialrating.util;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vimalcvs.materialratinglib.R;

/**
 * Created by VimalCvs on 02/11/2020.
 */

public class RateDialogFeedbackFrag extends RateDialogFrag implements View.OnClickListener {

    private static final String RATING_KEY = "rating";
    private EditText etFeedback;
    private float rating;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog( savedInstanceState );
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_feedback, container);

        etFeedback = view.findViewById(R.id.et_feedback);

        View bt = view.findViewById(R.id.bt_no);
        bt.setOnClickListener( this );

        bt = view.findViewById(R.id.bt_send);
        bt.setOnClickListener( this );


        if( savedInstanceState != null ){
            rating = savedInstanceState.getFloat(RATING_KEY);
        }

        return view;
    }

    public void setRating( float rating ){
        this.rating = rating;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putFloat( RATING_KEY, rating );
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        String feedback = etFeedback.getText().toString();
        if( view.getId() == R.id.bt_send && feedback.length() > 0 ){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.email)});
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name)+" App Rating...!");
            intent.putExtra(Intent.EXTRA_TEXT, "Stars:- "+rating+"\n\nFeedback:- "+feedback);
            getActivity().startActivity(Intent.createChooser(intent, "Send email"));
        }
        else if( view.getId() == R.id.bt_send ){
            Toast.makeText( getActivity(), "Please enter your feedback!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        dismiss();
    }
}
