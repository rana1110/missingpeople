package com.ohm.missingpeople.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.activity.SinglePersonDetailView;
import com.ohm.missingpeople.networkoperation.model.MissingPeopleDataClass;
import com.ohm.missingpeople.utils.Constants;

import java.util.ArrayList;

public class AllMissingPersonAdapter extends RecyclerView.Adapter<AllMissingPersonAdapter.AllMissingPersonViewHolder> {

    private ArrayList<MissingPeopleDataClass> dataList;
    private Context context;
    private boolean layoutFlag = false;


    public AllMissingPersonAdapter(ArrayList<MissingPeopleDataClass> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public AllMissingPersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.all_missing_people, parent, false);
        return new AllMissingPersonViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final AllMissingPersonViewHolder holder, final int position) {
        holder.personName.setText(dataList.get(position).getName());
        holder.personMissingFrom.setText("Missing from " + dataList.get(position).getMissingFrom()
                + ", " + dataList.get(position).getCity() + ", " + dataList.get(position).getState()
                + " On " + dataList.get(position).getMissingSince() + ". " + position);
        // holder.personMissingDate.setText("Missing Date - "+dataList.get(position).getMissingSince());
        // holder.personContactDetail.setText(dataList.get(position).getContactdetail());

        Glide.with(context).load("http://missingppl.com/Picture/" + dataList.get(position).getPicture())
                .placeholder(R.mipmap.image_not_found)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(holder.imageView);


       /* holder.callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Call Click", Toast.LENGTH_SHORT).show();
            }
        });*/


        holder.allMissingPeopleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent singlePerson = new Intent(context, SinglePersonDetailView.class);
                    singlePerson.putExtra(Constants.SIGLE_PERSON_ID, position);
                    singlePerson.putExtra(Constants.SIGLE_PERSON_NAME, checkForBlank(dataList.get(position).getName()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_FATHER_NAME, checkForBlank(dataList.get(position).getFathername()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_MOTHER_NAME, checkForBlank(dataList.get(position).getMothername()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_GENDER, checkForBlank(dataList.get(position).getGender()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_AGE, checkForBlank(dataList.get(position).getAge()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_HEIGHT, checkForBlank(dataList.get(position).getHeight()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_WEIGHT, checkForBlank(dataList.get(position).getWeight()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_EYE_COLOR, checkForBlank(dataList.get(position).getEyesColor()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_HAIR_COLOR, checkForBlank(dataList.get(position).getHairColor()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_PICTURE_NAME, checkForBlank("http://missingppl.com/Picture/" + dataList.get(position).getPicture()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_LAST_SEEN, checkForBlank(dataList.get(position).getLastseen()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_MISSING_FROM, checkForBlank(dataList.get(position).getMissingFrom()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_MISSING_SINCE, checkForBlank(dataList.get(position).getMissingSince()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_IDENTITY_MARK, checkForBlank(dataList.get(position).getIdentityMark()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_DESCRIPTION, checkForBlank(dataList.get(position).getDescription()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_POSTED_BY, checkForBlank(dataList.get(position).getPostedby()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_CONTACT_MOBILE, checkForBlank(dataList.get(position).getContactdetail()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_CITY, checkForBlank(dataList.get(position).getCity()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_STATE, checkForBlank(dataList.get(position).getState()));
                    singlePerson.putExtra(Constants.SIGLE_PERSON_COUNTRY, checkForBlank(dataList.get(position).getCountry()));


                    singlePerson.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(singlePerson);
                } catch (Exception e) {
                    Log.e("test123", e.getMessage().toString());
                }
            }
        });



       /* holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent newsWebView = new Intent(context, NewsWebViewScreen.class);
                newsWebView.putExtra(Constants.WEB_VIEW_URL_KEY, dataList.get(position).getUrl());
                newsWebView.putExtra(Constants.WEB_VIEW_CATEGORY_KEY, newsCategoty);
                newsWebView.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                WNApplication.getInstance().getApplicationContext().startActivity(newsWebView);


            }
        });*/


    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class AllMissingPersonViewHolder extends RecyclerView.ViewHolder {

        TextView personName, personMissingFrom, personMissingDate, personContactDetail;
        ImageView imageView, callImageView, shareImageView;
        RelativeLayout allMissingPeopleLayout;
        LinearLayout callLayout;
        Button shareButton;

        AllMissingPersonViewHolder(View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.personName);
            personMissingFrom = itemView.findViewById(R.id.personMissingFrom);
            //  personMissingDate = itemView.findViewById(R.id.personMissingDate);
            //    personContactDetail = itemView.findViewById(R.id.personContactDetails);
            imageView = itemView.findViewById(R.id.newsImageView);
            callImageView = itemView.findViewById(R.id.callIconImageButton);
            shareImageView = itemView.findViewById(R.id.shareIconImageButton);
            allMissingPeopleLayout = itemView.findViewById(R.id.all_misssing_people_layout);
            callLayout = itemView.findViewById(R.id.call_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  /*  Toast.makeText(context, "onclick", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, InterstitialActivity.class);
                    context.startActivity(intent);*/
                }
            });

        }
    }

    private String checkForBlank(String tempStr) {
        if (!tempStr.isEmpty())
            return tempStr;
        return "Not Available";
    }
}

