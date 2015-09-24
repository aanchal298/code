package com.example.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
 
public class AfterNotification extends Activity {
	Button b;
	String fileName="aan.txt"; 
	static String text="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afternot);
        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	if(isChecked){
            	     Toast.makeText(getApplicationContext(), "Your question alarm is set for editorial notification!", Toast.LENGTH_LONG).show();
            	     TimePicker t = (TimePicker) findViewById(R.id.timePicker1);
                     // datePicker = (DatePicker) findViewById(R.id.datePicker);                   
       
                      //---use the AlarmManager to trigger an alarm---
                      AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);                 
       
                      //---get current date and time---
                      Calendar calendar = Calendar.getInstance();       
       
                      //---sets the time for the alarm to trigger---
                      //calendar.set(Calendar.YEAR, datePicker.getYear());
                      //calendar.set(Calendar.MONTH, datePicker.getMonth());
                      //calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());                 
                      calendar.set(Calendar.HOUR_OF_DAY, t.getCurrentHour());
                      calendar.set(Calendar.MINUTE, t.getCurrentMinute());
                      calendar.set(Calendar.SECOND, 0);
       
                      //---PendingIntent to launch activity when the alarm triggers---                    
                      Intent i = new Intent(AfterNotification.this, DisplayNotification2.class);                   
       
                      //---assign an ID of 1---
                      i.putExtra("NotifID", 1);                                
       
                      PendingIntent displayIntent = PendingIntent.getActivity(getBaseContext(), 0, i, 0);               
       
                      //---sets the alarm to trigger---
                      alarmManager.set(AlarmManager.RTC_WAKEUP, 
                          calendar.getTimeInMillis(), displayIntent);
                    
            	    }else{
            	    	Toast.makeText(getApplicationContext(), "You will not get the editorial..set alarm!", Toast.LENGTH_LONG).show();
            	    }
            }
        });
        b=(Button)findViewById(R.id.button1);
		
        //TextView txt=new TextView(this);
        //txt.setText("Activity after click on notification");
        //setContentView(txt);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File sdcard = Environment.getExternalStorageDirectory();

				//Get the text file
				File file = new File(sdcard,"aan.txt");
				
				//Read text from file
				

				try {
				    BufferedReader br = new BufferedReader(new FileReader(file));
				    String line,line2;
				    
				    while ((line = br.readLine()) != null) {
				    {
				    	//text.append(line);
				    	File file2 = new File(sdcard,"billu.txt");
				    	String text2;
				    	BufferedReader br2 = new BufferedReader(new FileReader(file2));
				    	int yy=0;
				    	while( (line2 = br2.readLine()) != null)
				    	{
				    		if(line2.equals(line))
				    		{
					    		Toast.makeText(getApplicationContext(), "MIL GYa", Toast.LENGTH_LONG).show();

				    			yy=1;
				    			break;
				    		}
				    	}
				    	if(yy==0)
				    	{
				    		text  = "www.codechef.com/problems/"+ line;
				    		//Toast.makeText(getApplicationContext(), line.charAt(0)+"XX", Toast.LENGTH_LONG).show();
				    		text += "\n";
				    		//Toast.makeText(getApplicationContext(), Environment.getExternalStorageDirectory().toString(), Toast.LENGTH_LONG).show();
				        	String fileName="billu.txt"; 
				        	FileOutputStream str = null;
							
				    		try {
				    			BufferedReader br3 = new BufferedReader(new FileReader(new File(sdcard,"billu.txt")));
				    			String fullFile = line;
				    			String line1="";
				    			while((line1 = br3.readLine())!=null)
				    			{
				    				//Toast.makeText(getApplicationContext(),"FILE = "+ fullFile+line1, Toast.LENGTH_SHORT).show();
				    				fullFile =fullFile+ "\n"+line1;
				    			}
				    			
								str = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/"+ fileName));

				    			str.write(fullFile.getBytes());
				    			//Toast.makeText(getApplicationContext(),"FILE = "+ fullFile, Toast.LENGTH_LONG).show();
				    		} catch (Exception e) {
				    		} finally {
				    			try {
									str.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				    		}
				    		break;
				    	}
				    }
				    }
				    br.close();
				}
				catch (IOException e) {
				    //You'll need to add proper error handling here
				}

				
				//Find the view by its id
				TextView tv = (TextView)findViewById(R.id.textView1);

				//Set the text
				tv.setText(text);
				Toast.makeText(getApplicationContext(), text,Toast.LENGTH_LONG ).show();
				Intent i = new Intent(AfterNotification.this,QuestionPage.class);
                startActivity(i);
			}
		});
    }
    
  
}
