package com.ohm.missingpeople.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.networkoperation.model.MissingPeopleDataClass;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<MissingPeopleDataClass> dataList;
    private Context context;
    private String newsCategoty;


    public NewsAdapter(ArrayList<MissingPeopleDataClass> dataList, Context context, String newsCategoty) {
        this.dataList = dataList;
        this.context = context;
        this.newsCategoty = newsCategoty;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.all_missing_people_data_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        holder.personName.setText(dataList.get(position).getName());
        holder.personMissingFrom.setText(dataList.get(position).getMissingFrom());
        holder.personMissingDate.setText(dataList.get(position).getMissingSince());
        holder.personContactDetail.setText(dataList.get(position).getContactdetail());

        Glide.with(context).load(dataList.get(position).getPicture())
                .placeholder(R.drawable.call_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(holder.imageView);


/*        String countryName = CountryCodeAndCountryName.getCountryName(dataList.get(position).getCountrycode()).toUpperCase();
        String publishedDate = dataList.get(position).getPublishedat();
        holder.txtPublishedat.setText(countryName + "  -  " + publishedDate);*/


       /* holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, dataList.get(position).getUrl() + "\n"
                        + context.getResources().getString(R.string.share_my_app_with_link) + "\n" +
                        context.getResources().getString(R.string.share_my_app));

                Intent startIntent = Intent.createChooser(sharingIntent, context.getResources().getString(R.string.app_name));
                startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startIntent);
            }
        });*/

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

    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView personName, personMissingFrom, personMissingDate, personContactDetail;
        ImageView imageView;
        Button shareButton;

        NewsViewHolder(View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.personName);
            personMissingFrom = itemView.findViewById(R.id.personMissingFrom);
            personMissingDate = itemView.findViewById(R.id.personMissingDate);
            personContactDetail = itemView.findViewById(R.id.personContactDetails);
            imageView = itemView.findViewById(R.id.newsImageView);

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

