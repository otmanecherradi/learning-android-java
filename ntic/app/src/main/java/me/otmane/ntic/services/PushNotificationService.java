package me.otmane.ntic.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import me.otmane.ntic.DataStore;

public class PushNotificationService extends FirebaseMessagingService {
    public static final String TAG = "PushNotificationService";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        DataStore.getInstance().setFCMToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "PushNotificationService.onMessageReceived");

        final String CHANNEL_ID = "HEADS_UP_NOTIFICATION";

        String title = remoteMessage.getNotification().getTitle();
        String text = remoteMessage.getNotification().getBody();

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "New schedule",
                NotificationManager.IMPORTANCE_HIGH);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.createNotificationChannel(channel);

        notificationManager.notify(1, notificationBuilder.build());
    }
}