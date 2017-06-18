package com.zzl.toast;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastTestActivity extends Activity implements Runnable {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/**
	 * Ĭ�ϵ�Toast��ʾ��ʽ
	 * 
	 * @param view
	 */
	public void showDefault(View view) {
		// ��һ����������ǰ�������Ļ���������getApplicationContext()��this
		// �ڶ���������Ҫ��ʾ���ַ�����Ҳ����R.string���ַ���ID
		// ��������������ʾ��ʱ�䳤�̡�ToastĬ�ϵ�������LENGTH_LONG(��)��LENGTH_SHORT(��)��Ҳ����ʹ�ú�����2000ms
		Toast toast = Toast.makeText(getApplicationContext(), "Ĭ�ϵ�Toast",
				Toast.LENGTH_SHORT);
		// ��ʾtoast��Ϣ
		toast.show();
	}

	/**
	 * �Զ���λ�õ�Toast
	 * 
	 * @param view
	 */
	public void showCustom(View view) {
		Toast toast = Toast.makeText(getApplicationContext(), "�Զ�����ʾλ�õ�Toast",
				Toast.LENGTH_SHORT);
		// ��һ������������toast����Ļ����ʾ��λ�á������ڵ������Ǿ��п���
		// �ڶ�������������ڵ�һ����������toastλ�õĺ���X���ƫ��������������ƫ�ƣ���������ƫ��
		// ������������ͬ�ĵڶ�����������һ��
		// ��������õ�ƫ������������Ļ�ķ�Χ��toast������Ļ�ڿ����������Ǹ��߽���ʾ
		toast.setGravity(Gravity.TOP | Gravity.CENTER, -50, 100);
		// ��Ļ������ʾ��X���Y��ƫ��������0
		// toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * ��ʾ��ͼƬ��toast
	 * 
	 * @param view
	 */
	public void showImg(View view) {
		Toast toast = Toast.makeText(getApplicationContext(), "��ʾ��ͼƬ��toast",
				3000);
		toast.setGravity(Gravity.CENTER, 0, 0);
		// ����ͼƬ��ͼ����
		ImageView imageView = new ImageView(getApplicationContext());
		// ����ͼƬ
		imageView.setImageResource(R.drawable.ic_launcher);
		// ���toast�Ĳ���
		LinearLayout toastView = (LinearLayout) toast.getView();
		// ���ô˲���Ϊ�����
		toastView.setOrientation(LinearLayout.HORIZONTAL);
		// ��ImageView�ڼ��뵽�˲����еĵ�һ��λ��
		toastView.addView(imageView, 0);
		toast.show();
	}

	// button��ȫ�Զ�����ʾ

	public void showCustom2(View view) {
		// Inflater��˼�ǳ���
		// LayoutInflater���������ʵ����XML�ļ�������Ӧ����ͼ����Ĳ���
		LayoutInflater inflater = getLayoutInflater();
		// ͨ���ƶ�XML�ļ�������ID�����һ����ͼ����
		View layout = inflater.inflate(R.layout.custom2,
				(ViewGroup) findViewById(R.id.llToast));

		ImageView image = (ImageView) layout.findViewById(R.id.tvImageToast);
		// ���ò�����ͼƬ��ͼ��ͼƬ
		image.setImageResource(R.drawable.ic_launcher);

		TextView title = (TextView) layout.findViewById(R.id.tvTitleToast);
		// ���ñ���
		title.setText("������");

		TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
		// ��������
		text.setText("��ȫ�Զ���Toast");

		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}

	/**
	 * �������̵߳���
	 * 
	 * @param view
	 */
	public void showInThread(View view) {
		// ���÷���1
		// Thread th=new Thread(this);
		// th.start();
		// ���÷���2
		handler.post(new Runnable() {
			@Override
			public void run() {
				showToast();
			}
		});
	}

	public void showToast() {
		Toast toast = Toast.makeText(getApplicationContext(),
				"Toast�������߳��е�����ʾ", Toast.LENGTH_SHORT);
		toast.show();
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int what = msg.what;
			switch (what) {
			case 1:
				showToast();
				break;
			default:
				break;
			}

			super.handleMessage(msg);
		}
	};

	@Override
	public void run() {
		handler.sendEmptyMessage(1);
	}

}