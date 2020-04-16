package com.example.exemplesms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.exemplesms.Utilities.Constantes;

public class SmsActivity extends AppCompatActivity {

    Context _context;
    Button btnEnvoyer;
    EditText txtMessage;
    EditText txtDestinataire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        _context = this;

        btnEnvoyer = findViewById(R.id.btnEnvoyer);
        txtMessage = findViewById(R.id.txtMessge);
        txtDestinataire = findViewById(R.id.txtDestinataire);

        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                String smsMessage = txtMessage.getText().toString().trim();
                String destinataire = txtDestinataire.getText().toString().trim();

                Intent sendIntent = new Intent(Constantes.SMS_SEND);
                Intent deliveredIntent = new Intent(Constantes.SMS_DELIVERED);

                PendingIntent piSend = PendingIntent.getBroadcast(_context,0,sendIntent,0);
                PendingIntent piDelivered = PendingIntent.getBroadcast(_context,0,deliveredIntent,0);

                if(!smsMessage.isEmpty() && !destinataire.isEmpty()){
                    try {
                        smsManager.sendTextMessage(destinataire,null,smsMessage,piSend,piDelivered);
                    }
                    catch (Exception ex){
                        Log.e("SmsAct SendSms ", ex.getMessage());
                    }

                }
            }
        });
    }
}
