package com.example.code;


import android.app.Activity;
import android.os.Bundle;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
 
public class MainActivity extends Activity {    
    TimePicker timePicker;
  //  DatePicker datePicker;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetime);
        	
        //---Button view---
      
       
        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked){
            	     Toast.makeText(getApplicationContext(), "Your question alarm is set babe!", Toast.LENGTH_LONG).show();
            	     timePicker = (TimePicker) findViewById(R.id.timePicker1);
                     // datePicker = (DatePicker) findViewById(R.id.datePicker);                   
       
                      //---use the AlarmManager to trigger an alarm---
                      AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);                 
       
                      //---get current date and time---
                      Calendar calendar = Calendar.getInstance();       
       
                      //---sets the time for the alarm to trigger---
                      //calendar.set(Calendar.YEAR, datePicker.getYear());
                      //calendar.set(Calendar.MONTH, datePicker.getMonth());
                      //calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());                 
                      calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                      calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                      calendar.set(Calendar.SECOND, 0);
       
                      //---PendingIntent to launch activity when the alarm triggers---                    
                      Intent i = new Intent(MainActivity.this, DisplayNotification.class);                   
       
                      //---assign an ID of 1---
                      i.putExtra("NotifID", 1);                                
       
                      PendingIntent displayIntent = PendingIntent.getActivity(getBaseContext(), 0, i, 0);               
       
                      //---sets the alarm to trigger---
                      alarmManager.set(AlarmManager.RTC_WAKEUP, 
                          calendar.getTimeInMillis(), displayIntent);
                    
            	    }else{
            	    	Toast.makeText(getApplicationContext(), "You will not get a question..set alarm!", Toast.LENGTH_LONG).show();
            	    }
            }
        });
        Button bt = (Button)findViewById(R.id.button1);
        bt.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                 
                        // TODO Auto-generated method stub
                        Intent i = new Intent(MainActivity.this,AfterNotification.class);
                        startActivity(i);
                    }
    });
        
   }
}