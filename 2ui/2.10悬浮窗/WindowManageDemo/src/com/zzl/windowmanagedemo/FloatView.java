package com.zzl.windowmanagedemo;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class FloatView extends View {
	private float mTouchStartX;
	private float mTouchStartY;
	private float x;
	private float y;

	private WindowManager wm = (WindowManager) getContext()
			.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	private WindowManager.LayoutParams wmParams = ((MyApplication) getContext()
			.getApplicationContext()).getMywmParams();
	private OnClickListener mClickListener;

	public FloatView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// 获取相对屏幕的坐标，即以屏幕左上角为原点
		x = event.getRawX();
		y = event.getRawY() - 25; // 25是系统状态栏的高度
		Log.i("currP", "currX" + x + "====currY" + y);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 获取相对View的坐标，即以此View左上角为原点
			mTouchStartX = event.getX();
			mTouchStartY = event.getY();

			Log.i("startP", "startX" + mTouchStartX + "====startY"
					+ mTouchStartY);

			break;
		case MotionEvent.ACTION_MOVE:
			updateViewPosition();
			break;

		case MotionEvent.ACTION_UP:
			updateViewPosition();
			mTouchStartX = mTouchStartY = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		
		event.getKeyCode();
		Log.d( "dispatchKeyEvent--",""+event.getKeyCode());
		return super.dispatchKeyEvent(event);
	}

	private void updateViewPosition() {
		// 更新浮动窗口位置参数
		wmParams.x = (int) (x - mTouchStartX);
		wmParams.y = (int) (y - mTouchStartY);
		wm.updateViewLayout(this, wmParams);

	}

	@Override
	public void setOnClickListener(OnClickListener l) {
		this.mClickListener = l;
	}

}
