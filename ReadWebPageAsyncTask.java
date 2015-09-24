package com.example.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReadWebPageAsyncTask extends Activity {
    private TextView textView;
    Button button;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                 
                        // TODO Auto-generated method stub
                        Intent i = new Intent(ReadWebPageAsyncTask.this,MainActivity.class);
                        startActivity(i);
                    }
    });
     
        
       
	}
   
    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            for (String url : urls) {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
        	
              
                try {
                	
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    int t=0;
                    while ((s = buffer.readLine()) != null) {
                    	//response+=s;
                    	
                    	
                    	int j=s.indexOf("/problems/");
                		//Toast.makeText(getApplicationContext(), "pojhvu", Toast.LENGTH_SHORT).show();

                    	if(j!=-1)
                    	{
                    		t++;
                    		if(t>=10){
                    		int idx = j;
                    		//Toast.makeText(getApplicationContext(), "hjfdu", Toast.LENGTH_SHORT).show();
                    		j++;
                    		while(s.charAt(j)!='/') j++;
                    		j++;
                    		while(s.charAt(j)!='\"' && j-idx <= 20 )
                    			response += s.charAt(j++);
                    			response+='\n';
                    	}
                    	}
                    	
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
           // textView.setText(Html.fromHtml(result));
        	Toast.makeText(getApplicationContext(), Environment.getExternalStorageDirectory().toString(), Toast.LENGTH_LONG).show();
        	String fileName="aan.txt"; 
        	FileOutputStream stream = null;
			try {
				
				stream = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/"+ fileName));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		try {
    			stream.write(result.getBytes());
    		} catch (Exception e) {
    		} finally {
    			try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
        }
        
    }

    public void readWebpage(View view) {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[] { "http://www.codechef.com/problems/easy" });

    }
}