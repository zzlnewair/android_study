package com.anoic.AccoutBook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import android.widget.AdapterView.OnItemClickListener;

public class MonthGridActivity extends Activity implements AbstractActivity {
    /** Called when the activity is first created. */
	
	GridView mGridView = null;
	ProgressDialog mProgressDialog;
	GridViewAdapter mAdapter;
    int year;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.monthgrid);
        
        year = Integer.valueOf(getIntent().getExtras().getString("year"));
        setTitle(year+"年清单");
        loadProgress();
		
    }
    
    //加载进度条
    public void loadProgress(){
		mProgressDialog = new ProgressDialog(MonthGridActivity.this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setMessage("正在加载"+year+"年清单,请稍后...");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.show();
		new Thread(new LoadThread()).start();
    }
    @Override
    protected void onRestart() {
    	// TODO Auto-generated method stub
    	super.onRestart();
    	 loadProgress();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
    
    //监听器
    class MgridViewListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MonthGridActivity.this,ListViewActivity.class);
			Bundle b = new Bundle();
			b.putInt("mIndex",position+1);
			b.putInt("year",year);
			intent.putExtras(b);
			startActivity(intent);
		}    	
    }
    
    
	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what == 1){
				//setContentView(R.layout.main);
				mGridView = (GridView)findViewById(R.id.mgridview);
		        mGridView.setAdapter(mAdapter);
		        mGridView.setOnItemClickListener(new MgridViewListener());		       
				mProgressDialog.cancel();
			}
		}
	};
    
	
	//加载线程类
    class LoadThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub				
			try {
				Thread.sleep(1000);
				Message message = new Message();
		        mAdapter = new GridViewAdapter(MonthGridActivity.this,year,months);        
				message.what=1;
				mHandler.sendMessage(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
    	
    }
    
    
    //更新adatper
    public void updateView(){
    	mAdapter = new GridViewAdapter(MonthGridActivity.this,year,months); 
    	mGridView.setAdapter(mAdapter);
    }
}