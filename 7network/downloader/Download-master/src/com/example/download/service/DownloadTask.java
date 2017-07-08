package com.example.download.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.example.download.db.ThreadDAO;
import com.example.download.db.ThreadDAOImple;
import com.example.download.entitis.FileInfo;
import com.example.download.entitis.ThreadInfo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DownloadTask {
	private Context mComtext = null;
	private FileInfo mFileInfo = null;
	private ThreadDAO mDao = null;
	private int mFinished = 0;
	public boolean mIsPause = false;

	public DownloadTask(Context comtext, FileInfo fileInfo) {
		super();
		this.mComtext = comtext;
		this.mFileInfo = fileInfo;
		this.mDao = new ThreadDAOImple(mComtext);
	}
	
	public void download(){
		// �Ĕ������Ы@ȡ�����d����Ϣ
		List<ThreadInfo> list = mDao.queryThreads(mFileInfo.getUrl());
		ThreadInfo info = null;
		if (list.size() == 0) {
			info = new ThreadInfo(0, mFileInfo.getUrl(), 0, mFileInfo.getLength(), 0);
		}else{
			info= list.get(0);
			 
		}
		new DownloadThread(info).start();
	}

	class DownloadThread extends Thread {
		private ThreadInfo threadInfo = null;

		public DownloadThread(ThreadInfo threadInfo) {
			this.threadInfo = threadInfo;
		}

		@Override
		public void run() {
			// ��������첻�������d��Ϣ��������d��Ϣ
			if (!mDao.isExists(threadInfo.getUrl(), threadInfo.getId())) {
				mDao.insertThread(threadInfo);
			}
			HttpURLConnection conn = null;
			RandomAccessFile raf = null;
			InputStream is = null;
			try {
				URL url = new URL(mFileInfo.getUrl());
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");

				int start = threadInfo.getStart() + threadInfo.getFinished();
				// �O�����d�ļ��_ʼ���Y����λ��
				conn.setRequestProperty("Range", "bytes=" + start + "-" + threadInfo.getEnd());
				File file = new File(DownloadService.DownloadPath, mFileInfo.getFileName());
				raf = new RandomAccessFile(file, "rwd");
				raf.seek(start);
				mFinished += threadInfo.getFinished();
				
				
				int code = conn.getResponseCode();
				if (code == HttpURLConnection.HTTP_PARTIAL) {
					is = conn.getInputStream();
					byte[] bt = new byte[1024];
					int len = -1;
					// ����UIˢ��ʱ��
					long time = System.currentTimeMillis();
					while ((len = is.read(bt)) != -1) {
						raf.write(bt, 0, len);
						mFinished += len;
						// �O�à�500���׸���һ��
						if (System.currentTimeMillis() - time > 500) {
							time = System.currentTimeMillis();
							
							Intent intent = new Intent(DownloadService.ACTION_UPDATE);
							intent.putExtra("finished", mFinished * 100 / mFileInfo.getLength());
							Log.i("test", mFinished * 100 / mFileInfo.getLength() + "");
							// �l�͏V���oActivity
							mComtext.sendBroadcast(intent);
						}
						if (mIsPause) {
							mDao.updateThread(threadInfo.getUrl(), threadInfo.getId(), mFinished);
							return;
						}
					}
				}
				// ���d��ɺ󣬄h����������Ϣ
				mDao.deleteThread(threadInfo.getUrl(), threadInfo.getId());
				Log.i("DownloadTask", "�������");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
				try {
					if (is != null) {
						is.close();
					}
					if (raf != null) {
						raf.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			super.run();
		}
	}
}
