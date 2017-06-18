package com.zzl.control;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RadioActivity extends Activity {

    Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.radioview);
	mContext = this;
	//��ѡ��(ֻ����һ�����еİ�ť���Ե�ѡ)
	RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radion0);
	
	//��ѡ��ť����һ�飩
	final RadioButton radioButton0 = (RadioButton)findViewById(R.id.radionButton0);
	final RadioButton radioButton1 = (RadioButton)findViewById(R.id.radionButton1);
	final RadioButton radioButton2 = (RadioButton)findViewById(R.id.radionButton2);
	
	radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    
	    @Override
	    public void onCheckedChanged(RadioGroup arg0, int checkID) {
		if(radioButton0.getId() == checkID) {
		    Toast.makeText(RadioActivity.this, "��ѡ���˵�һ��" + radioButton0.getText(), Toast.LENGTH_LONG).show();
		}else if(radioButton1.getId() == checkID) {
		    Toast.makeText(RadioActivity.this, "��ѡ���˵�һ��" + radioButton1.getText(), Toast.LENGTH_LONG).show();
		}else if(radioButton2.getId() == checkID) {
		    Toast.makeText(RadioActivity.this, "��ѡ���˵�һ��" + radioButton2.getText(), Toast.LENGTH_LONG).show();
		}
	    }
	});
	
	RadioGroup radioGroup0 = (RadioGroup)findViewById(R.id.radion1);
	
	//��ѡ��ť(�ڶ���)
	final RadioButton radioButton3 = (RadioButton)findViewById(R.id.radionButton3);
	final RadioButton radioButton4 = (RadioButton)findViewById(R.id.radionButton4);
	final RadioButton radioButton5 = (RadioButton)findViewById(R.id.radionButton5);
	
	radioGroup0.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    
	    @Override
	    public void onCheckedChanged(RadioGroup arg0, int checkID) {
		if(radioButton3.getId() == checkID) {
		    Toast.makeText(RadioActivity.this, "��ѡ���˵ڶ���" + radioButton3.getText(), Toast.LENGTH_LONG).show();
		}else if(radioButton4.getId() == checkID) {
		    Toast.makeText(RadioActivity.this, "��ѡ���˵ڶ���" + radioButton4.getText(), Toast.LENGTH_LONG).show();
		}else if(radioButton5.getId() == checkID) {
		    Toast.makeText(RadioActivity.this, "��ѡ���˵ڶ���" + radioButton5.getText(), Toast.LENGTH_LONG).show();
		}
	    }
	});
	super.onCreate(savedInstanceState);
    }
}
