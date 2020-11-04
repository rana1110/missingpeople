package com.ohm.missingpeople.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ohm.missingpeople.R;
import com.ohm.missingpeople.utils.BaseActivity;
import com.ohm.missingpeople.utils.Constants;

import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_AGE;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_CITY;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_CONTACT_MOBILE;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_EYE_COLOR;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_FATHER_NAME;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_GENDER;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_HAIR_COLOR;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_HEIGHT;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_IDENTITY_MARK;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_MISSING_FROM;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_MISSING_SINCE;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_MOTHER_NAME;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_NAME;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_POSTED_BY;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_STATE;
import static com.ohm.missingpeople.utils.Constants.SIGLE_PERSON_WEIGHT;

public class SinglePersonDetailView extends BaseActivity {
    Intent b = null;
    TextView name, alertText, fatherName, motherName, gender, age, height, weight, eyeColor, hairColor, idMark, postedBy, contactDetail, cityAndState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_person_detail_view);
        b = getIntent();
        loadData();
    }

    private void loadData() {
        name = findViewById(R.id.single_person_name);
        alertText = findViewById(R.id.single_person_alert_text);
        fatherName = findViewById(R.id.single_person_father_name);
        motherName = findViewById(R.id.single_person_mother_name);
        gender = findViewById(R.id.single_person_gender);
        age = findViewById(R.id.single_person_age);
        height = findViewById(R.id.single_person_height);
        weight = findViewById(R.id.single_person_weight);
        eyeColor = findViewById(R.id.single_person_eye_color);
        hairColor = findViewById(R.id.single_person_hair_color);
        idMark = findViewById(R.id.single_person_identity_mark);
        postedBy = findViewById(R.id.single_person_posted_by);
        contactDetail = findViewById(R.id.single_person_contact_detail);
        cityAndState = findViewById(R.id.single_person_city_state);


        name.setText(b.getStringExtra(SIGLE_PERSON_NAME));

        alertText.setText(b.getStringExtra(SIGLE_PERSON_NAME)+" is missing from "
                +b.getStringExtra(SIGLE_PERSON_MISSING_FROM)+" on "+b.getStringExtra(SIGLE_PERSON_MISSING_SINCE));
        fatherName.setText(b.getStringExtra(SIGLE_PERSON_FATHER_NAME));
        motherName.setText(b.getStringExtra(SIGLE_PERSON_MOTHER_NAME));
        gender.setText(b.getStringExtra(SIGLE_PERSON_GENDER));
        age.setText(b.getStringExtra(SIGLE_PERSON_AGE));
        height.setText(b.getStringExtra(SIGLE_PERSON_HEIGHT));
        weight.setText(b.getStringExtra(SIGLE_PERSON_WEIGHT));
        eyeColor.setText(b.getStringExtra(SIGLE_PERSON_EYE_COLOR));
        hairColor.setText(b.getStringExtra(SIGLE_PERSON_HAIR_COLOR));
        idMark.setText(b.getStringExtra(SIGLE_PERSON_IDENTITY_MARK));
        postedBy.setText(b.getStringExtra(SIGLE_PERSON_POSTED_BY));
        contactDetail.setText(b.getStringExtra(SIGLE_PERSON_CONTACT_MOBILE));
        cityAndState.setText(b.getStringExtra(SIGLE_PERSON_CITY)+" , "+b.getStringExtra(SIGLE_PERSON_STATE));

    }
}
