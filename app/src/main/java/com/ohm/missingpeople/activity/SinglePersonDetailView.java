package com.ohm.missingpeople.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ohm.missingpeople.R;
import com.ohm.missingpeople.utils.BaseActivity;
import com.ohm.missingpeople.utils.Constants;

public class SinglePersonDetailView extends BaseActivity {
    Intent b = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_person_detail_view);
        b = getIntent();
        loadData();
    }

    private void loadData() {
        Log.e("test123 - ", "" + b.getIntExtra(Constants.SIGLE_PERSON_ID, 0));
        //b.getStringExtra(Constants.SIGLE_PERSON_KEY);
    }
}
