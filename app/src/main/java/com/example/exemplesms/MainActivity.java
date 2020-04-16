package com.example.exemplesms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> _permissions;
    ArrayList<String> _permissionsRequest;
    Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _context = this;

        //Telephone superieur à android 5
        if(Build.VERSION.SDK_INT >= 23){

            _permissions = new ArrayList<>();
            _permissions.add(Manifest.permission.SEND_SMS);
            _permissions.add(Manifest.permission.RECEIVE_SMS);

            callPermissions();

        } else{
            redirectionActivite(_context, SmsActivity.class);
        }
    }

    private void callPermissions(){
        _permissionsRequest = new ArrayList<>();
        for(int i = 0; i < _permissions.size(); i++){
            String permission = _permissions.get(i);

            if(ContextCompat.checkSelfPermission(_context,permission) != PackageManager.PERMISSION_GRANTED){
                _permissionsRequest.add(permission);
            }
        }

        if(_permissionsRequest.isEmpty()){
            redirectionActivite(_context,SmsActivity.class);
        }else{
            String[] request = new String[_permissionsRequest.size()];
            request = _permissionsRequest.toArray(request);

            ActivityCompat.requestPermissions(
                    this,
                    request,
                    100
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        redirectionActivite(_context, SmsActivity.class);
    }

    public void redirectionActivite(Context context, Class classe){
        Intent intent = new Intent(context, classe);
        startActivity(intent);
    }
}
