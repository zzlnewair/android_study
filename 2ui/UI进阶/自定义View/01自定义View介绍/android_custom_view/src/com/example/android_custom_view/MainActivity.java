package com.example.android_custom_view;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		setContentView(new MyView(this));
	}

	class MyView extends View {
		Paint mPaint = null;

		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			mPaint = new Paint();
		}

		// 在自定义的View上画图
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			// 花文字
			mPaint.setColor(Color.BLACK);
			mPaint.setTextSize(18);// 18dp
			mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);// 消除锯齿
			canvas.drawText("hello world", 0, 120, mPaint);
			
			//设置画布的背景颜色
			canvas.drawColor(Color.WHITE);
			//画一条直线
			mPaint.setColor(Color.BLACK);
			mPaint.setStrokeWidth(4);
			canvas.drawLine(0, 0, 100, 100, mPaint);
			
			//绘制一个矩形
			mPaint.setColor(Color.BLUE);
			canvas.drawRect(0, 120, 100, 200, mPaint);
			
			//绘制椭圆
			mPaint.setColor(Color.CYAN);
			canvas.drawOval(new RectF(300, 370, 120, 100), mPaint);
			
			//绘制一个多边形
			mPaint.setColor(Color.RED);
			Path path = new Path();
			path.moveTo(150+5, 400-50);
			path.lineTo(150+45, 400-50);
			path.lineTo(150+30, 460-50);
			path.lineTo(150+20, 460-50);
			path.close();
			canvas.drawPath(path,mPaint);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
