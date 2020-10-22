package com.ohm.missingpeople.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.ohm.missingpeople.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void openNewScreen(Activity screenName) {
        Intent openIntent = new Intent(this, screenName.getClass());
        openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openIntent);
    }
}