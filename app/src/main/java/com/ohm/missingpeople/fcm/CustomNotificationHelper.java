package com.ohm.missingpeople.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.ohm.missingpeople.R;
import com.ohm.missingpeople.activity.authentication.LoginActivity;

import java.io.IOException;
import java.net.URL;

import static com.ohm.missingpeople.R.drawable.sport_icon;


public class CustomNotificationHelper {

    private Context mContext;
    private String NOTIFICATION_TITLE = "Notification Sample App";
    private String CONTENT_TEXT = "Expand me to see a detailed message!";
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    public CustomNotificationHelper(Context context) {
        mContext = context;
    }

    public void sendNotification(String postId, String name, String imageUrl, String missingfrom) {


        Intent resultIntent = new Intent(mContext, LoginActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews collapsedView = new RemoteViews(mContext.getPackageName(), R.layout.custom_notification_layout);
        //notificationIconFromUrl
        collapsedView.setTextViewText(R.id.content_title, name);
        collapsedView.setTextViewText(R.id.content_text, missingfrom);
        collapsedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(mContext, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));

        try {
            URL url = new URL(imageUrl);
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            collapsedView.setImageViewBitmap(R.id.notificationIconFromUrl, image);
        } catch (IOException e) {

        }
        

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.drawable.business_icon)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(CONTENT_TEXT)
                .setAutoCancel(true)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setCustomContentView(collapsedView);

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert notificationManager != null;
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        assert notificationManager != null;
        notificationManager.notify(0, builder.build());
    }
}
