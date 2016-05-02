package com.zzl.control;

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
        /**TextView**/
        Button botton0 = (Button)findViewById(R.id.button0);
        botton0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,TextViewActivity.class); 
		 startActivity(intent);
	    }
	}); 
        /**WebView**/
        Button botton1 = (Button)findViewById(R.id.button1);
        botton1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,WebViewActivity.class); 
		 startActivity(intent);
	    }
	}); 
       
        /**Menu菜单**/
        Button botton2 = (Button)findViewById(R.id.button2);
        botton2.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,MenuActivity.class); 
		 startActivity(intent);
	    }
	}); 
    
        /**Button按钮**/
        Button botton3 = (Button)findViewById(R.id.button3);
        botton3.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,ButtonActivity.class); 
		 startActivity(intent);
	    }
	}); 
        
        /**EditText编辑框**/
        Button botton4 = (Button)findViewById(R.id.button4);
        botton4.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,EditTextActivity.class); 
		 startActivity(intent);
	    }
	}); 
        /**单项选择**/
        Button botton5 = (Button)findViewById(R.id.button5);
        botton5.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,RadioActivity.class); 
		 startActivity(intent);
	    }
	});     
        
        /**多项选择**/
        Button botton6 = (Button)findViewById(R.id.button6);
        botton6.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,CheckboxActivity.class); 
		 startActivity(intent);
	    }
	}); 
    }
}