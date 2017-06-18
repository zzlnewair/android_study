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

	//��ͨ��ť
	Button button0 = (Button)findViewById(R.id.buttonview0);
	
	//���ð�ť������ɫ
	button0.setTextColor(Color.BLUE);
	//���ð�ť���ִ�С
	button0.setTextSize(30);
	
	//���ð�ť���� ����¼�
	button0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		Toast.makeText(ButtonActivity.this, "������ˡ�����һ����ť��", Toast.LENGTH_LONG).show();
		
	    }
	});
	
	//��ͼƬ�İ�ť
	ImageButton button1 = (ImageButton)findViewById(R.id.buttonview1);
	//���ð�ť���� ����¼�
	button1.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		Toast.makeText(ButtonActivity.this, "�������һ����ͼƬ�İ�ť", Toast.LENGTH_LONG).show();
		
	    }
	});

    }
}
