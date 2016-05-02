package com.zzl.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ButtonActivity extends Activity {

    Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
	setContentView(R.layout.buttonview);
	mContext = this;

	//普通按钮
	Button button0 = (Button)findViewById(R.id.buttonview0);
	
	//设置按钮文字颜色
	button0.setTextColor(Color.BLUE);
	//设置按钮文字大小
	button0.setTextSize(30);
	
	//设置按钮监听 点击事件
	button0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		Toast.makeText(ButtonActivity.this, "您点击了‘这是一个按钮’", Toast.LENGTH_LONG).show();
		
	    }
	});
	
	//带图片的按钮
	ImageButton button1 = (ImageButton)findViewById(R.id.buttonview1);
	//设置按钮监听 点击事件
	button1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		Toast.makeText(ButtonActivity.this, "您点击了一个带图片的按钮", Toast.LENGTH_LONG).show();
		
	    }
	});

    }
}
