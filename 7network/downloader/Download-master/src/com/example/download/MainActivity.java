package com.example.download;

import com.example.download.entitis.FileInfo;
import com.example.download.service.DownloadService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private TextView fileName;
	private Button startButton;
	private Button stopButton;
	private ProgressBar downloadProgress;
	private String urlstr = "http://study.163.com/pub/study-android-official.apk";
	private FileInfo fileInfo;
	private Intent intent;
	private UIRecive mRecive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ʼ���ؼ�
		fileName = (TextView) findViewById(R.id.file_textview);
		downloadProgress = (ProgressBar) findViewById(R.id.progressBar2);
		startButton = (Button) findViewById(R.id.start_button);
		stopButton = (Button) findViewById(R.id.stop_button);
		downloadProgress.setMax(100);
		// ��ʼ���ļ�����
		fileInfo = new FileInfo(0, urlstr, getfileName(urlstr), 0, 0);

		startButton.setOnClickListener(this);
		stopButton.setOnClickListener(this);
		
		intent = new Intent(MainActivity.this, DownloadService.class);
		mRecive = new UIRecive();
		
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(DownloadService.ACTION_UPDATE);
		registerReceiver(mRecive, intentFilter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_button:
			// �_������
			fileName.setText(getfileName(urlstr));
			intent.setAction(DownloadService.ACTION_START);
			intent.putExtra("fileInfo", fileInfo);
			startService(intent);

			break;
		case R.id.stop_button:
			intent.setAction(DownloadService.ACTION_STOP);
			intent.putExtra("fileInfo", fileInfo);
			startService(intent);
			break;
		}

	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(mRecive);
		super.onDestroy();
	}
	// ��URL��ַ�Ы@ȡ�ļ�������/������ַ�
	private String getfileName(String url) {

		return urlstr.substring(urlstr.lastIndexOf("/") + 1);
	}

	// ��DownloadTadk�Ы@ȡ�V����Ϣ�������M�ȗl
	class UIRecive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (DownloadService.ACTION_UPDATE.equals(intent.getAction())) {
				int finished = intent.getIntExtra("finished", 0);
				downloadProgress.setProgress(finished);
				
			}
		}

	}


}
