package com.zzl.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class IntentActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void openActivity(View v){
    	/**
    	 * (û�����ݲ����������)ֻҪIntent�е�Action��Category��������Intent-Filter�У�������֮ƥ�䣬����ƥ��ʧ��
    	 */
    	Intent intent = new Intent();//��ʽ��ͼ����Activity
    	intent.setAction("com.zzl.zhangxx");
    	intent.addCategory("com.zzl.category.java");   
    	intent.setDataAndType(Uri.parse("baidu://www.baidu.com"), "image/jpeg");
    	
    	startActivity(intent);//�����ڲ�ΪIntent�����android.intent.category.DEFAULT���
    }
}