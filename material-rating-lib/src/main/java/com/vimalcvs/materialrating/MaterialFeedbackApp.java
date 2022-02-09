package com.vimalcvs.materialrating;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
/**
 * Created by VimalCvs on 09/02/2022.
 */
public class MaterialFeedbackApp extends BottomSheetDialogFragment {

    private final String mText;

    public MaterialFeedbackApp(String email) {
        mText = email;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(STYLE_NORMAL, R.style.lib_rate_round_corner);
        return super.onCreateDialog(savedInstanceState);
    }

    TextView lib_feedback_button;
    EditText et_feedback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lib_rate_dialog_feedback_app, container, false);

        lib_feedback_button = v.findViewById(R.id.lib_feedback_button);
        et_feedback = v.findViewById(R.id.et_feedback);
        onclick();
        return v;
    }

    public void onclick() {
        lib_feedback_button.setOnClickListener(view -> {
            String feedback = et_feedback.getText().toString();
            if( view.getId() == R.id.lib_feedback_button && feedback.length() > 0 ){
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mText});
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name)+" App Rating...!");
                intent.putExtra(Intent.EXTRA_TEXT, "Feedback: "+feedback);
                getActivity().startActivity(Intent.createChooser(intent, "Send email"));
            }
            else if( view.getId() == R.id.lib_feedback_button ){
                Toast.makeText( getActivity(), "Please enter your feedback!", Toast.LENGTH_SHORT).show();
                return;
            }
            dismiss();
        });
    }

}