package com.example.exemplesms.BroadcastReveivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.exemplesms.Utilities.Constantes;

public class SendBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals(Constantes.SMS_SEND)){

            int resultCode = getResultCode();

            switch (resultCode){

                case Activity.RESULT_OK:
                    Toast.makeText(context, "SMS Envoyé", Toast.LENGTH_SHORT).show();
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
