package com.example.project;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private NotificationManager notificationManager;

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.i(TAG, s);

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            /*String s = data.get("key");
            if(s=="Messages"){
                intent=new Intent(getApplicationContext(),Messages.class);
                startActivity(intent);
            }
            else {
                intent=new Intent(this,Notifications.class);
                startActivity(intent);
            }

             */
            Log.d(TAG, data.toString());
            //

        }
        if (remoteMessage.getNotification() != null) {
            String s = remoteMessage.getNotification().getTitle();
            Log.d(TAG, "title" + s);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                sendOreoNotification(remoteMessage);
            } else {
                sendNotification(remoteMessage);
            }
        } else {
            Log.d(TAG, remoteMessage.getData().toString());

        }

    }

    /* public void sendNotification(RemoteMessage remoteMessage) {
         NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

         NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
         channel.enableLights(false);
         channel.enableVibration(true);
         channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
         getManager().createNotificationChannel(channel);

         NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
         builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
         Intent intent = new Intent(this,Messages.class);
         PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
         builder.setContentIntent(pendingIntent);
         builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
         builder.setContentTitle(remoteMessage.getNotification().getTitle());
         builder.setContentText(remoteMessage.getNotification().getBody());
         //builder.setSubText("Tap to view the website.");


         // Will display the notification in the notification bar
         notificationManager.notify(1, builder.build());
     }
     public NotificationManager getManager(){
         if(notificationManager==null){
             notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

         }
         return  notificationManager;
     }

     */
    //
    private void sendOreoNotification(RemoteMessage remoteMessage) {


        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Intent intent;
        String s = notification.getTitle();
        if (s == "Messages") {
            intent = new Intent(this, Messages.class);
        } else {
            intent = new Intent(this, Notifications.class);
        }
        //int j= Integer.parseInt(user.replaceAll("[\\D]",""));


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "Channel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(defaultsound)
                .setContentIntent(pendingIntent);
    }

    private void sendNotification(RemoteMessage remoteMessage) {


        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Intent intent;
        String s1 = notification.getTitle();
        if (s1 == "Messages") {
            intent = new Intent(getApplicationContext(), Messages.class);
        } else {
            intent = new Intent(getApplicationContext(), Notifications.class);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(defaultsound)
                .setContentIntent(pendingIntent);
        NotificationManager notification1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        notification1.notify(11, builder.build());
    }

}
