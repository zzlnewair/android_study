package com.zzl.control;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.textview);
	
	LinearLayout ll = (LinearLayout) findViewById(R.id.textviewll);
	TextView textView = new TextView(this);
	//������ʾ����
	textView.setText("�Ӵ��������һ��TextView");
	//������ʾ��ɫ
	textView.setTextColor(Color.WHITE);
	//������ʾ�����С
	textView.setTextSize(18);
	//������ʾ������ɫ
	textView.setBackgroundColor(Color.BLUE);
	//����ê��λ��
	textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
	//�����view���뵽���ֵ���
	ll.addView(textView);
	
	super.onCreate(savedInstanceState);
    }
}
