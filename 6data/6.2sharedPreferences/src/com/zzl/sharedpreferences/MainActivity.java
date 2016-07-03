package com.zzl.sharedpreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zzl.util.PreferencesUtils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ȡֻ�ܱ���Ӧ�ó������д��SharedPreferences����
		preferences = getSharedPreferences("mysp", MODE_WORLD_READABLE);//ata/data/package name/
		editor = preferences.edit();
		Button read = (Button) findViewById(R.id.read);
		Button write = (Button) findViewById(R.id.write);
		read.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				//��ȡ�ַ�������
				String time = preferences.getString("time", null);
				//��ȡint���͵�����
				int randNum = preferences.getInt("random", 0);
				String result = time == null ? "����ʱ��δд������"
					: "д��ʱ��Ϊ��" + time 
					+ "\n�ϴ����ɵ������Ϊ��" + randNum;
				//ʹ��Toast��ʾ��Ϣ
				Toast.makeText(MainActivity.this , 
					result , 5000)
					.show();
			}
		});
		write.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� "
					+ "hh:mm:ss");
				// ���뵱ǰʱ��
				editor.putString("time", sdf.format(new Date()));
				// ����һ�������
				editor.putInt("random", (int) (Math.random() * 100));
				// �ύ���д��������
				editor.commit();
				
				//PreferencesUtils
				PreferencesUtils.PREFERENCE_NAME="mysp";
				int num=PreferencesUtils.getInt(getApplicationContext(), "random");
				
				Log.d("PreferencesUtils","num==="+num);
			}
		});
		
	
		
	}
}
