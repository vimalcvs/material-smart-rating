package com.vimalcvs.materialrating;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class RatingDialog extends DialogFragment implements RatingBar.OnRatingBarChangeListener {


    public static final String PREF_KEY_SHOW_AGAIN = "show_rate_dialog_again";
    public static final String PREF_KEY_NEVER_SHOW = "never_show_rate_dialog";
    private static final String PREF_KEY_APP_LAUNCH_COUNT = "app_launch_count";
    public static String EMAIL_FEEDBACK = "";

    public static void showRateAppDialog(FragmentManager fragmentManager, Context context, String email) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            boolean showAgain = prefs.getBoolean(PREF_KEY_SHOW_AGAIN, true);
            boolean neverShow = prefs.getBoolean(PREF_KEY_NEVER_SHOW, false);
            if (neverShow || !showAgain) {
                return;
            }
            EMAIL_FEEDBACK = email;
            RatingDialog.showRateAppDialogNormal(fragmentManager, context, email);
        } catch (Exception e) {
            Utils.getErrors(e);
        }
    }

    public static void showRateAppDialogNormal(FragmentManager fragmentManager, Context context, String email) {
        try {
            RatingDialog ratingDialog = new RatingDialog();
            ratingDialog.show(fragmentManager, "RatingDialog");
            EMAIL_FEEDBACK = email;
        } catch (Exception e) {
            Utils.getErrors(e);
        }
    }

    public static void showRateAppDialogAuto(FragmentManager fragmentManager, Context context, int time, String email) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            int appLaunchCount = prefs.getInt(PREF_KEY_APP_LAUNCH_COUNT, 0);
            if (appLaunchCount >= time) {
                boolean hasRatedApp = prefs.getBoolean(RatingDialog.PREF_KEY_SHOW_AGAIN, false);
                if (!hasRatedApp) {
                    RatingDialog.showRateAppDialog(fragmentManager, context, email);
                }
            } else {
                appLaunchCount++;
                prefs.edit().putInt(PREF_KEY_APP_LAUNCH_COUNT, appLaunchCount).apply();
            }
        } catch (Exception e) {
            Utils.getErrors(e);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity());
        View dialogView = onCreateView(getLayoutInflater(), null, savedInstanceState);
        builder.setCancelable(false).setView(dialogView).setBackground(new ColorDrawable(Color.TRANSPARENT));
        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lib_material_rate_dialog, container, false);


        TextView neverButton = view.findViewById(R.id.bt_maybeLater);
        neverButton.setOnClickListener(v -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
            prefs.edit().putBoolean(PREF_KEY_NEVER_SHOW, true).apply();
            dismiss();
        });

        TextView rateButton = view.findViewById(R.id.bt_ratingSend);
        rateButton.setOnClickListener(v -> Toast.makeText(getActivity(), R.string.select_5_star_rating, Toast.LENGTH_SHORT).show());

        String title = String.format(view.getContext().getString(R.string.dialog_five_star_title), view.getContext().getString(R.string.app_name));
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(title);


        RatingBar ratingBar = view.findViewById(R.id.bt_ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);

        return view;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        try {
            if (rating >= 5) {
                openPlayStoreForRating();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
                prefs.edit().putBoolean(PREF_KEY_SHOW_AGAIN, false).apply();
                Toast.makeText(getActivity(), R.string.select_5_star_rating, Toast.LENGTH_SHORT).show();
                dismiss();
            } else if (rating > 0) {
                sendEmailFeedback();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
                prefs.edit().putBoolean(PREF_KEY_SHOW_AGAIN, false).apply();
                Toast.makeText(getActivity(), R.string.select_5_star_rating, Toast.LENGTH_SHORT).show();
                dismiss();
            }
            dismiss();
        } catch (Exception e) {
            Utils.getErrors(e);
        }
    }

    private void openPlayStoreForRating() {
        try {
            String packageName = requireActivity().getPackageName();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
                startActivity(intent);
            }
        } catch (Exception e) {
            Utils.getErrors(e);
        }
    }

    private void sendEmailFeedback() {
        try {
            String subject = String.format(getResources().getString(R.string.subject_email), getResources().getString(R.string.app_name));
            Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
            selectorIntent.setData(Uri.parse("mailto:"));
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL_FEEDBACK});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.setSelector(selectorIntent);
            startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.send_mail)));
        } catch (Exception e) {
            Utils.getErrors(e);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getDialog() != null) {
            getDialog().setDismissMessage(null);
        }
    }
}
