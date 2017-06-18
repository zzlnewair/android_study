package com.zzl.recycleviewexample;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends Activity {
	private RecyclerView mRecyclerView;
	private static final String TAG = MainActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//默认是竖屏

       //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

// 设置布局管理器
		mRecyclerView.setLayoutManager(layoutManager);
		
		String[] dataset = new String[100];

		for (int i = 0; i < dataset.length; i++) {

		dataset[i] = "item" + i;
	
		}
	
		RecyclerAdapter mAdapter = new RecyclerAdapter(dataset);
	
		mRecyclerView.setAdapter(mAdapter);
	
		mAdapter
		
		}
		
	

	
}
