package com.ohm.missingpeople.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputEditText;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.activity.authentication.LoginActivity;
import com.ohm.missingpeople.networkoperation.model.GeneralModel;
import com.ohm.missingpeople.networkoperation.restclient.ApiClient;
import com.ohm.missingpeople.networkoperation.restclient.ApiInterface;
import com.ohm.missingpeople.networkoperation.restclient.NetworkOperationConstants;
import com.ohm.missingpeople.utils.BaseActivity;
import com.ohm.missingpeople.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ohm.missingpeople.utils.Constants.*;

public class SinglePersonDetailView extends BaseActivity {
    String LOG = "test123";
    Intent b = null;
    TextView name, alertText, fatherName, motherName, gender, age, height, weight, eyeColor, hairColor, idMark, postedBy, contactDetail, cityAndState;
    ImageView postImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_person_detail_view);
        b = getIntent();
        loadData();
    }

    private void loadData() {
        postImage = findViewById(R.id.single_person_postImageView);
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


        Log.e(LOG, b.getStringExtra(SIGLE_PERSON_PICTURE_NAME));
        Glide.with(this).load(b.getStringExtra(SIGLE_PERSON_PICTURE_NAME))
                .placeholder(R.mipmap.image_not_found)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(postImage);
        name.setText(b.getStringExtra(SIGLE_PERSON_NAME));
        alertText.setText(b.getStringExtra(SIGLE_PERSON_NAME) + " is missing from "
                + b.getStringExtra(SIGLE_PERSON_MISSING_FROM) + " on " + b.getStringExtra(SIGLE_PERSON_MISSING_SINCE));
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
        cityAndState.setText(b.getStringExtra(SIGLE_PERSON_CITY) + " , " + b.getStringExtra(SIGLE_PERSON_STATE));

    }

    public void onAddButtonClick(View v) {
        TextInputEditText addCommentText;
        ImageView cancelButton;
        Button addCommentBtn;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.user_comment_layout, viewGroup, false);
        addCommentText = dialogView.findViewById(R.id.add_comment_text);
        addCommentBtn = dialogView.findViewById(R.id.add_comment_button);
        cancelButton = dialogView.findViewById(R.id.cancel_btn);
        addCommentText.requestFocus();
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        addCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  apiInterface = ApiClient.getClient().create(ApiInterface.class);
                forgotPasswordCall = apiInterface.forgotPasswordEmailSend(forgotPasswordDialog.getText().toString());
                forgotPasswordCall.enqueue(new Callback<GeneralModel>() {
                    @Override
                    public void onResponse(Call<GeneralModel> call, Response<GeneralModel> response) {

                        if (response.body().getMessage().equals(NetworkOperationConstants.FORGOT_PASSWORD_EMAIL_SEND_SUCCESS)) {
                            showErrorForDialogLayout("Password Link Send to Your Email");
                            alertDialog.dismiss();
                        } else {
                            showErrorForDialogLayout("Your Email is not register");
                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralModel> call, Throwable t) {
                        showErrorForDialogLayout("Something went Wrong Try later");
                    }
                });*/


            }
        });
    }

    public void cancelButtonClick(View v) {
        onBackPressed();

    }
}
