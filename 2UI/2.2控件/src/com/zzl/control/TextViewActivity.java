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
	//设置显示文字
	textView.setText("从代码中添加一个TextView");
	//设置显示颜色
	textView.setTextColor(Color.WHITE);
	//设置显示字体大小
	textView.setTextSize(18);
	//设置显示背景颜色
	textView.setBackgroundColor(Color.BLUE);
	//设置锚点位置
	textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
	//把这个view加入到布局当中
	ll.addView(textView);
	
	super.onCreate(savedInstanceState);
    }
}
