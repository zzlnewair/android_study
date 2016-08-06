package com.example.android_listfragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends Activity {
	private FragmentManager manager;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		LeftFragment leftFragment = new LeftFragment();
		transaction.add(R.id.left, leftFragment, "leftFragment");
		// 不能在MainActivity直接加载右边的Fragment
		// RightFragment rightFragment = new RightFragment();
		// transaction.add(R.id.right, rightFragment, "rightFragment");
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
