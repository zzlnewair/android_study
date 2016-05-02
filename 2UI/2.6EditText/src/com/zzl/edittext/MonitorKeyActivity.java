package com.zzl.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MonitorKeyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.monitorkey);
	EditText editText = (EditText)findViewById(R.id.monitor_edit_text0);
	final TextView textView0 = (TextView)findViewById(R.id.monitor_text0);
	final TextView textView1 = (TextView)findViewById(R.id.monitor_text1);
	final TextView textView2 = (TextView)findViewById(R.id.monitor_text2);
	
	editText.addTextChangedListener(new TextWatcher() {
	    
	    @Override
	    public void onTextChanged(CharSequence text, int start, int before, int count) {
                //text  ������иı����ַ�����Ϣ
		//start ������иı����ַ�������ʼλ��
		//before ������иı�ǰ���ַ�����λ�� Ĭ��Ϊ0
		//count ������иı���һ�������ַ���������
		textView1.setText("������ַ��� [ " + text.toString() + " ] ��ʼ��� [ " + start + " ] �������� [ " + count+" ]");
		
	    }
	    
	    @Override
	    public void beforeTextChanged(CharSequence text, int start, int count,int after) {
		//text  ������иı�ǰ���ַ�����Ϣ
		//start ������иı�ǰ���ַ�������ʼλ��
		//count ������иı�ǰ����ַ����ı�����һ��Ϊ0
		//after ������иı����ַ�������ʼλ�õ�ƫ����
		System.out.println(text.toString());
		textView0.setText("����ǰ�ַ��� [ " + text.toString() + " ]��ʼ��� [ " + start + " ]����ƫ����  [" + after + " ]");
	    }
	    
	    @Override
	    public void afterTextChanged(Editable edit) {
		//edit  �������������������е���Ϣ
		textView2.setText("��������������Ϊ [" + edit.toString()+" ] ������ʾ����Ļ��");
	    }
	});
	
	super.onCreate(savedInstanceState);
    }
}
