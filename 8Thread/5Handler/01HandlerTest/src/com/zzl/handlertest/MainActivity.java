package com.zzl.handlertest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	private Button btnEnd;
	private TextView labelTimer;
	private Thread clockThread;
	private boolean isRunning = true;
	private int timer = 0;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnEnd = (Button) findViewById(R.id.btnEnd);
		btnEnd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isRunning = false;
			}
		});

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {

				case 0:
					labelTimer.setText("逝去了 " + msg.obj + " 秒");
				}
			}

		};

		labelTimer = (TextView) findViewById(R.id.labelTimer);

		clockThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int timer = 0;

				while (isRunning) {
					try {
						Thread.currentThread().sleep(1000);
						timer++;
						// labelTimer.setText("逝去了 " + timer + " 秒");
						Message msg = new Message();
						msg.what = 0;
						msg.obj = timer;
						handler.sendMessage(msg);
						Log.d(TAG, "lost  time " + timer);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		});

		clockThread.start(); /* 启动线程 */
	}

}
