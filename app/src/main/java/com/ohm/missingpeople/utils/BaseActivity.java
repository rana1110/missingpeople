package com.ohm.missingpeople.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.ohm.missingpeople.R;

import java.util.regex.Pattern;

public class BaseActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mProgressDialog = new ProgressDialog(this, R.style.myAlertDialogStyle);
        setUpProgressDialog();

    }

    public void setUpProgressDialog() {
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    public void showDialog() {

        if (mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void hideDialog() {

        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showError(String errorMsg) {
        Snackbar snackbar = Snackbar.make((ViewGroup) findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) layout.getLayoutParams();
        params.gravity = Gravity.TOP;
        layout.setLayoutParams(params);
        View snackView = mInflater.inflate(R.layout.snackbar_layout, null);
        TextView textViewTop = (TextView) snackView.findViewById(R.id.textview_snackbar_text);
        textViewTop.setText(errorMsg);
        layout.setPadding(0, 0, 0, 0);
        layout.addView(snackView, 0);
        snackbar.show();
    }

    public void showErrorForDialogLayout(String errorMsg) {
        Snackbar snackbar = Snackbar.make((ViewGroup) findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) layout.getLayoutParams();
        params.gravity = Gravity.TOP;
        layout.setLayoutParams(params);
        View snackView = mInflater.inflate(R.layout.snackbar_layout, null);
        TextView textViewTop = (TextView) snackView.findViewById(R.id.textview_snackbar_text);
        textViewTop.setText(errorMsg);
        layout.setPadding(0, 0, 0, 0);
        layout.addView(snackView, 0);
        snackbar.show();
    }

    public void openURl(Context context, String url) {
        Intent openUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        openUrl.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openUrl);
    }

    public void showToast(String errorMsg) {
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    public void openNewScreen(Activity screenName) {
        Intent openIntent = new Intent(this, screenName.getClass());
        openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openIntent);
    }

    public void makeScreenHolds(int timeInSec) {
        try {
            Thread.sleep(timeInSec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean isValidEmailAddress(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    @Override
    public void onBackPressed() {
        //Execute your code here
        finish();
    }

}