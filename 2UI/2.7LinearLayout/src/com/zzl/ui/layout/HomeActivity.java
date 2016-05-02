package com.zzl.ui.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class HomeActivity extends Activity {

	
    Context mContext = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        /**
         * 
         * LinearLayout 
         * */
        Button botton0 = (Button)findViewById(R.id.button0);
        botton0.setOnClickListener(new OnClickListener() {
	    
	     public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,LinearLayoutActivity.class); 
		 startActivity(intent);
	    }
	}); 
        /**RelativeLayout
         * 
         * **/
        Button botton1 = (Button)findViewById(R.id.button1);
        botton1.setOnClickListener(new OnClickListener() {
	    
	 
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,RelativeLayoutActivity.class); 
		 startActivity(intent);
	    }
	}); 
       
        /**TableLayout**/
        Button botton2 = (Button)findViewById(R.id.button2);
        botton2.setOnClickListener(new OnClickListener() {
	   
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,TableLayoutActivity.class); 
		 startActivity(intent);
	    }
	}); 
        /**GridLayout**/
        Button botton3 = (Button)findViewById(R.id.button3);
        botton3.setOnClickListener(new OnClickListener() {
	    
	    
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,GridLayoutActivity.class); 
		 startActivity(intent);
	    }
	});   
        

    }
}