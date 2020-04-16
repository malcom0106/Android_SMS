package com.example.exemplesms.Utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Fonctions {

    public static void getNotification(Context context, Class maclasse, String titre, String message){
        //Redirection vers ma classe lors du click dans la notif
        Intent intent = new Intent(context, maclasse);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification
                .Builder(context)
                .setContentTitle(titre)
                .setContentText(message)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0,notification);

    }
}
