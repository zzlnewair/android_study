package com.example.android_fragment_manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button button1;
	private Button button2;
	private Button button3;
	private FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) this.findViewById(R.id.button1);
		button2 = (Button) this.findViewById(R.id.button2);
		button3 = (Button) this.findViewById(R.id.button3);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		manager = getFragmentManager();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FragmentTransaction transaction = manager.beginTransaction();
		switch (v.getId()) {
		case R.id.button1:

			Fragment1 fragment1 = new Fragment1();
			// 加入Fragment回退栈的标记
			transaction.replace(R.id.main, fragment1, "fragment1");
			transaction.addToBackStack("fragment1");
			//FragmentTransaction 中提供了一个 addToBackStack()方法，可以用于将一个事务添加到返回栈中
			break;

		case R.id.button2:
			Fragment2 fragment2 = new Fragment2();
			transaction.replace(R.id.main, fragment2, "fragment2");
			transaction.addToBackStack("fragment2");

			break;
		case R.id.button3:
			Fragment3 fragment3 = new Fragment3();
			transaction.replace(R.id.main, fragment3, "fragment3");
			transaction.addToBackStack("fragment3");
			break;
		}
		transaction.commit();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage("再按一次就退出系统");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							finish();
							// System.exit(code);
						}
					});
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub

						}
					});
			builder.show();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
