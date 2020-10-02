package com.ohm.missingpeople.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.networkoperation.model.MissingPeopleDataClass;

import java.util.ArrayList;

public class AllMissingPersonAdapter extends RecyclerView.Adapter<AllMissingPersonAdapter.AllMissingPersonViewHolder> {

    private ArrayList<MissingPeopleDataClass> dataList;
    private Context context;


    public AllMissingPersonAdapter(ArrayList<MissingPeopleDataClass> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public AllMissingPersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.all_missing_people_data_list, parent, false);
        return new AllMissingPersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AllMissingPersonViewHolder holder, final int position) {
        holder.personName.setText(dataList.get(position).getName());
        holder.personMissingFrom.setText("is missing from "+dataList.get(position).getMissingFrom()
                                        +","+dataList.get(position).getCity()+","+dataList.get(position).getState()
        +" on "+dataList.get(position).getMissingSince()+".");
       // holder.personMissingDate.setText("Missing Date - "+dataList.get(position).getMissingSince());
       // holder.personContactDetail.setText(dataList.get(position).getContactdetail());

        Glide.with(context).load("http://missingppl.com/Picture/"+dataList.get(position).getPicture())
                .placeholder(R.drawable.call_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(holder.imageView);


        holder.callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Call Click", Toast.LENGTH_SHORT).show();
            }
        });

        holder.shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Share Click", Toast.LENGTH_SHORT).show();
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
}

