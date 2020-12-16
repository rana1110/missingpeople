package com.ohm.missingpeople.fcm;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.activity.authentication.LoginActivity;


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class PushNotificationReceiveService extends FirebaseMessagingService {
    private static final int NOTIF_ID = 1234;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;
    private RemoteViews mRemoteViews;
    private Notification mNotification;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
       /* Log.e("Test123", "Notification Title: " + remoteMessage.getNotification().getTitle());
        Log.e("Test123", "Notification Body: " + remoteMessage.getNotification().getBody());
*/
//        Log.e("Test123", "Data: " + remoteMessage.getData());
        Log.e("Test123", "Name: " + remoteMessage.getData().get("name"));
        Log.e("Test123", "postId: " + remoteMessage.getData().get("postid"));
        Log.e("Test123", "ImageURL: " + remoteMessage.getData().get("imageurl"));
        Log.e("Test123", "Missing From: " + remoteMessage.getData().get("missingfrom"));


        CustomNotificationHelper notificationHelper = new CustomNotificationHelper(getApplicationContext());
        notificationHelper.sendNotification(
                remoteMessage.getData().get("postid"),
                remoteMessage.getData().get("name"),
                remoteMessage.getData().get("imageurl"),
                remoteMessage.getData().get("missingfrom"));
    }
}


