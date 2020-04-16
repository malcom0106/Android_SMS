package com.example.exemplesms.BroadcastReveivers;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.exemplesms.Utilities.Constantes;
import com.example.exemplesms.Utilities.Fonctions;

public class SendBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals(Constantes.SMS_SEND)){

            int resultCode = getResultCode();

            switch (resultCode){

                case Activity.RESULT_OK:
                    Fonctions.getNotification(context,"Envoi SMS", "Message Envoyé");
                    //Toast.makeText(context, "SMS Envoyé", Toast.LENGTH_SHORT).show();
                    break;

                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    Toast.makeText(context, "Erreur d'envoi", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Toast.makeText(context, "Autres erreurs", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

}
