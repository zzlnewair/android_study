package com.zzl.reclerviewpractice;

import java.util.ArrayList;
import java.util.List;

import com.zzl.reclerviewpractice.adapter.MyRecyclerAdapter;
import com.zzl.reclerviewpractice.adapter.MyRecyclerAdapter.OnItemClickListener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private RecyclerView recyclerView;
	private List<String> mDatas;
	private MyRecyclerAdapter recycleAdapter;
	
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		
		initData();
		recycleAdapter=new MyRecyclerAdapter(MainActivity.this, mDatas);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		//设置布局管理器
		recyclerView.setLayoutManager(layoutManager);
		//设置为垂直布局，这也是默认的
		layoutManager.setOrientation(OrientationHelper.VERTICAL);
		//设置Adapter
		recyclerView.setAdapter(recycleAdapter);
		recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
		//设置增加或删除条目的动画
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		recycleAdapter.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onLongClick(int position) {
				Toast.makeText(MainActivity.this,"onLongClick事件       您点击了第："+position+"个Item",0).show();
			}
			
			@Override
			public void onClick(int position) {
				Toast.makeText(MainActivity.this,"onClick事件       您点击了第："+position+"个Item",0).show();
			}
		});
		
	}

	private void initData() {
		mDatas = new ArrayList<String>();
		for (int i=0; i < 40; i++) {
			mDatas.add("item"+i);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.id_action_add:
			recycleAdapter.addData(1);
			break;
			
		case R.id.id_action_delete:
			recycleAdapter.removeData(1);
			break;
			
		case R.id.id_action_gridview:
			recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
			break;
			
		case R.id.id_action_listview:
			recyclerView.setLayoutManager(new LinearLayoutManager(this));
			break;
			
		case R.id.id_action_listview2:
			//recyclerView.setLayoutManager(new LinearLayoutManager(this));
			LinearLayoutManager layoutManager = new LinearLayoutManager(this);
			layoutManager.setOrientation(OrientationHelper.HORIZONTAL);//水平布局
			recyclerView.setLayoutManager(layoutManager);
			
			
			break;
			
		case R.id.id_action_horizontalGridView:
			recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
					StaggeredGridLayoutManager.HORIZONTAL));
			break;
			
		}
		return true;
	}
	
	
}
