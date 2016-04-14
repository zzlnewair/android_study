package com.zzl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 创建Activity的要点
 * 1.一个Activity就是一个类，并且这个类要继承Activity
 * 2.需要复写onCreate方法
 * 3.每一个Activity都需要在AndroidManifest.xml文件当中进行配置
 * 4.为Activity添加必要的控件
 * @author mars_chenchuan
 *
 */
public class MyHello extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//调用父类当中的onCreate方法
        super.onCreate(savedInstanceState);
        //设置当前的Activity使用main.xml作为布局文件，其中R.layout.main是main.xml文件在R.java文件当中的ID
        setContentView(R.layout.mainlayout);
        //在main.xml文件当中所定义的控件，都会在R.java文件当中产生相应的ID，本行代码的作用就是在根据这个ID来取得代表该控件的对象
        TextView myTextView = (TextView)findViewById(R.id.myTextView);
        //这一行的作用和上一行类似，只不过这一次取得的是代表按钮的对象
        Button myButton = (Button)findViewById(R.id.myButton);
        //为TextView控件设置String值
        myTextView.setText("我的第一个TextView");
        //为Button控件设置String值
        myButton.setText("名字Button");
        myButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText( MyHello.this, "toast---from myButton", Toast.LENGTH_LONG).show();
			}
		});
    }
}