package com.dev.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.dev.myapplication.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {

    ActivityNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNotificationBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        createNotification();
    }

    private void createNotification() {
        final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent monIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, monIntent, 0);

        Notification.Builder builder = new Notification.Builder(this)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("Test Notification")
                .setSmallIcon(android.R.drawable.sym_contact_card)
                .setContentTitle("Test")
                .setContentText("Notification content")
                .setContentIntent(pendingIntent);

        String channelId = "channel_ID1";
        NotificationChannel channel = new NotificationChannel(channelId,
                "test notification channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        mNotification.createNotificationChannel(channel);
        builder.setChannelId(channelId);

        mNotification.notify(1, builder.build());
    }

}
