package com.zzl.edittext;

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
        /**简单的EditText输入框**/
        Button botton0 = (Button)findViewById(R.id.button0);
        botton0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,SampleActivity.class); 
		 startActivity(intent);
	    }
	}); 
        /**限制EditText输入框内容**/
        Button botton1 = (Button)findViewById(R.id.button1);
        botton1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,LimitActivity.class); 
		 startActivity(intent);
	    }
	}); 
       
        /**编辑框中显示图片**/
        Button botton2 = (Button)findViewById(R.id.button2);
        botton2.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,ImageActivity.class); 
		 startActivity(intent);
	    }
	}); 
        /**设置软键盘的Enter键**/
        Button botton3 = (Button)findViewById(R.id.button3);
        botton3.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,KeyBoardActivity.class); 
		 startActivity(intent);
	    }
	});   
        
        
        /**软键盘按钮的点击事件**/
        Button botton4 = (Button)findViewById(R.id.button4);
        botton4.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		 Intent intent = new Intent(mContext,MonitorKeyActivity.class); 
		 startActivity(intent);
	    }
	}); 
    }
}