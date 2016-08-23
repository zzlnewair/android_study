package com.zzl.recycleviewexample;

import com.zzl.recycleviewexample.RecyclerAdapter.OnItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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
	
		
	
		mAdapter.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onClick(int position) {
				// TODO Auto-generated method stub
				
				Toast.makeText(MainActivity.this,"onLongClick事件       您点击了第："+position+"个Item",0).show();
			}

			@Override
			public void onLongClick(int position) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,"onClick事件       您点击了第："+position+"个Item",0).show();
			}
			
			
		});
		
		mRecyclerView.setAdapter(mAdapter);
		//设置Item增加、移除动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		
		
		
		}
		
	

	
}
