package com.example.mainthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "MainThread";
	private Handler mMainHandler, mChildHandler;
    private TextView info;
    private Button msgBtn;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 info = (TextView) findViewById(R.id.info);
	     msgBtn = (Button) findViewById(R.id.msgBtn);
	     mMainHandler =new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				 Log.i(TAG, "Got an incoming message from the child thread - "
	                        + (String) msg.obj);
	                
	                info.setText((String) msg.obj);
	                
			}
	     };
	     
	     new ChildThread().start();
	     
	     msgBtn.setOnClickListener(new OnClickListener() {
	    	 
	            @Override
	            public void onClick(View v) {
	                 
	                if (mChildHandler != null) {
	                     
	               
	                    Message childMsg = mChildHandler.obtainMessage();
	                    childMsg.obj = mMainHandler.getLooper().getThread().getName() + "says Hello";
	                    mChildHandler.sendMessage(childMsg);
	                     
	                    Log.i(TAG, "Send a message to the child thread - " + (String)childMsg.obj);
	 
	 
	                }
	            }

				
	        });
	 
	    }
	 
	    public void onDestroy() {
	    		super.onDestroy();
	        Log.i(TAG, "Stop looping the child thread's message queue");
	 
	        mChildHandler.getLooper().quit();
	    }



	
	 class ChildThread extends Thread {
		 
	        private static final String CHILD_TAG = "ChildThread";
	 
	        public void run() {
	            this.setName("ChildThread");
	 
	           
	            Looper.prepare();
	 
	            mChildHandler = new Handler() {
	                @Override
	                public void handleMessage(Message msg) {
	                     Log.i(CHILD_TAG, "Got an incoming message from the main thread - " + (String)msg.obj);
	 
	 
	                    try {
	 
	                        sleep(100);
	 
	                        Message toMain = mMainHandler.obtainMessage();
	                        toMain.obj = "This is " + this.getLooper().getThread().getName() +
	                                    ".  Did you send me \"" + (String)msg.obj + "\"?";
	 
	                        mMainHandler.sendMessage(toMain);
	 
	                        Log.i(CHILD_TAG, "Send a message to the main thread - " + (String)toMain.obj);
	 
	                    } catch (InterruptedException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	                }
	 
	            };
	 
	            Log.d(CHILD_TAG, "Child handler is bound to - "+ mChildHandler.getLooper().getThread().getName());
	 
	           
	            Looper.loop();
	        }
	    }
	
}
