package com.example.code;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class QuestionPage extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionpage);
        WebView view = (WebView)findViewById(R.id.webView1);
        String url = "http://" + AfterNotification.text;
       // view.loadUrl(url);
        Toast.makeText(getApplicationContext(), AfterNotification.text, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url)); 
        startActivity(i); 
	}
	
	

}
