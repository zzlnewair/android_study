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
	 * 默认的Toast显示方式
	 * 
	 * @param view
	 */
	public void showDefault(View view) {
		// 第一个参数：当前的上下文环境。可用getApplicationContext()或this
		// 第二个参数：要显示的字符串。也可是R.string中字符串ID
		// 第三个参数：显示的时间长短。Toast默认的有两个LENGTH_LONG(长)和LENGTH_SHORT(短)，也可以使用毫秒如2000ms
		Toast toast = Toast.makeText(getApplicationContext(), "默认的Toast",
				Toast.LENGTH_SHORT);
		// 显示toast信息
		toast.show();
	}

	/**
	 * 自定义位置的Toast
	 * 
	 * @param view
	 */
	public void showCustom(View view) {
		Toast toast = Toast.makeText(getApplicationContext(), "自定义显示位置的Toast",
				Toast.LENGTH_SHORT);
		// 第一个参数：设置toast在屏幕中显示的位置。我现在的设置是居中靠顶
		// 第二个参数：相对于第一个参数设置toast位置的横向X轴的偏移量，正数向右偏移，负数向左偏移
		// 第三个参数：同的第二个参数道理一样
		// 如果你设置的偏移量超过了屏幕的范围，toast将在屏幕内靠近超出的那个边界显示
		toast.setGravity(Gravity.TOP | Gravity.CENTER, -50, 100);
		// 屏幕居中显示，X轴和Y轴偏移量都是0
		// toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * 显示带图片的toast
	 * 
	 * @param view
	 */
	public void showImg(View view) {
		Toast toast = Toast.makeText(getApplicationContext(), "显示带图片的toast",
				3000);
		toast.setGravity(Gravity.CENTER, 0, 0);
		// 创建图片视图对象
		ImageView imageView = new ImageView(getApplicationContext());
		// 设置图片
		imageView.setImageResource(R.drawable.ic_launcher);
		// 获得toast的布局
		LinearLayout toastView = (LinearLayout) toast.getView();
		// 设置此布局为横向的
		toastView.setOrientation(LinearLayout.HORIZONTAL);
		// 将ImageView在加入到此布局中的第一个位置
		toastView.addView(imageView, 0);
		toast.show();
	}

	// button完全自定义显示

	public void showCustom2(View view) {
		// Inflater意思是充气
		// LayoutInflater这个类用来实例化XML文件到其相应的视图对象的布局
		LayoutInflater inflater = getLayoutInflater();
		// 通过制定XML文件及布局ID来填充一个视图对象
		View layout = inflater.inflate(R.layout.custom2,
				(ViewGroup) findViewById(R.id.llToast));

		ImageView image = (ImageView) layout.findViewById(R.id.tvImageToast);
		// 设置布局中图片视图中图片
		image.setImageResource(R.drawable.ic_launcher);

		TextView title = (TextView) layout.findViewById(R.id.tvTitleToast);
		// 设置标题
		title.setText("标题栏");

		TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
		// 设置内容
		text.setText("完全自定义Toast");

		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}

	/**
	 * 在其他线程调用
	 * 
	 * @param view
	 */
	public void showInThread(View view) {
		// 调用方法1
		// Thread th=new Thread(this);
		// th.start();
		// 调用方法2
		handler.post(new Runnable() {
			@Override
			public void run() {
				showToast();
			}
		});
	}

	public void showToast() {
		Toast toast = Toast.makeText(getApplicationContext(),
				"Toast在其他线程中调用显示", Toast.LENGTH_SHORT);
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