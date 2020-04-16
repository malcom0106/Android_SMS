package com.example.exemplesms.BroadcastReveivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.exemplesms.Utilities.Fonctions;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    String messagebody;
    String telephone;

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(action.equals("android.provider.Telephony.SMS_RECEIVED")){

            Bundle bundle = intent.getExtras();
            if(bundle != null){
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messageList = new SmsMessage[pdus.length];
                int pos = 0;
                for (Object msgPdu : pdus) {
                    messageList[pos] = SmsMessage.createFromPdu((byte[]) msgPdu, null);
                    pos++;
                }

                if(messageList.length > 0){
                    for(SmsMessage message : messageList){
                        messagebody = message.getDisplayMessageBody();
                        telephone = message.getDisplayOriginatingAddress();
                    }

                    if(!messagebody.isEmpty() && !telephone.isEmpty()){
                        Fonctions.getNotification(context, "Nouveau SMS de" + telephone, messagebody);
                    }
                }
            }
        }
    }
}
