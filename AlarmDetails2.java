package com.example.code;


import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
 
public class AlarmDetails2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmdetails2);
 
        //---look up the notification manager service---
        NotificationManager nm = (NotificationManager) 
            getSystemService(NOTIFICATION_SERVICE);
 
        //---cancel the notification---
        nm.cancel(getIntent().getExtras().getInt("NotifID"));        
    }
}
