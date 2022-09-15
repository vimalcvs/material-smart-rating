package com.vimalcvs.materialrating;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Created by VimalCvs on 02/11/2020.
 */

public class MaterialFeedback extends DialogFragment {

    private View theDialogView;
    private TextInputLayout text_input;
    private static final String RATING_KEY = "rating";
    public float rating;
    private String deviceInfo;
    public String email;

    public MaterialFeedback(String email) {
        this.email = email;
    }

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
        View view = inflater.inflate(R.layout.lib_material_fragment_feedback, container);

        if(savedInstanceState != null){
            rating = savedInstanceState.getFloat(RATING_KEY);
        }

        Button bt_maybeLater = view.findViewById(R.id.bt_maybeLater);
        bt_maybeLater.setOnClickListener(cancelButton -> dismiss());


        deviceInfo = "Device Info:";
        deviceInfo += "\n OS Version: " + System.getProperty("os.version") + "(" + android.os.Build.VERSION.INCREMENTAL + ")";
        deviceInfo += "\n OS API Level: " + android.os.Build.VERSION.SDK_INT;
        deviceInfo += "\n Device: " + android.os.Build.DEVICE;
        deviceInfo += "\n Model (and Product): " + android.os.Build.MODEL + " ("+ android.os.Build.PRODUCT + ")";


        TextInputEditText etFeedback = view.findViewById(R.id.et_feedback);
        Editable feedback = etFeedback.getText();

        Button bt_feedbackSend = view.findViewById(R.id.bt_feedbackSend);
        bt_feedbackSend.setOnClickListener(send -> {
            if(send.getId() == R.id.bt_feedbackSend && (feedback != null ? feedback.length() : 0) > 0 ){
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name)+" App Rating...!");
                intent.putExtra(Intent.EXTRA_TEXT, "Stars: "+rating+"\n\nFeedback: "+feedback+"\n\n"+deviceInfo);
                if (requireActivity().getPackageManager().resolveActivity(intent,0) != null) {
                    startActivity(intent);
                }

            } else if(send.getId() == R.id.bt_feedbackSend){
                text_input = view.findViewById(R.id.text_input);
                text_input.setError("Please enter at least 10 characters.");
                return;
            }
            dismiss();
        });

        return view;
    }
    public void setRating(float rating){
        this.rating = rating;
    }
}
