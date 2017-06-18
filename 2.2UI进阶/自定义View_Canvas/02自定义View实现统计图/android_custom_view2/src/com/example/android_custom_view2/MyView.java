package com.example.android_custom_view2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	private Paint myPaint;
	private static final String title = "2006-2011上半年的销售情况";
	private static final String content = "来自公司销售的统计数据";

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		myPaint = new Paint();// 初始化画笔
		myPaint.setColor(Color.BLACK);
		myPaint.setTextSize(18);
		canvas.drawText(title, 20, 20, myPaint);
		// 绘制坐标
		canvas.drawLine(50, 100, 50, 500, myPaint);// 绘制纵坐标
		canvas.drawLine(50, 500, 400, 500, myPaint);// 绘制横坐标
		int[] array = { 0, 50, 100, 150, 200, 250, 300, 350 };
		myPaint.setTextSize(10);// 设置字体大小
		canvas.drawText("单位：万元", 20, 90, myPaint);
		for (int i = 0; i < array.length; i++) {
			canvas.drawLine(50, 500 - array[i], 54, 500 - array[i], myPaint);
			canvas.drawText(array[i] + "", 20, 500 - array[i], myPaint);
		}
		String[] array2 = { "2008年", "2009年", "2010年", "2011上半年" };
		for (int i = 0; i < array2.length; i++) {
			canvas.drawText(array2[i], array[i] + 80, 520, myPaint);
		}
		myPaint.setColor(Color.BLUE);
		myPaint.setStyle(Style.FILL);
		canvas.drawRect(new Rect(90, 500 - 56, 110, 500), myPaint);
		canvas.drawRect(new Rect(140, 500 - 98, 160, 500), myPaint);
		canvas.drawRect(new Rect(190, 500 - 207, 210, 500), myPaint);
		canvas.drawRect(new Rect(240, 500 - 318, 260, 500), myPaint);
		myPaint.setColor(Color.BLACK);
		canvas.drawText("56.32", 88, 500 - 58, myPaint);
		canvas.drawText("90.00", 138, 500 - 100, myPaint);
		canvas.drawText("207.67", 188, 500 - 209, myPaint);
		canvas.drawText("318.56", 238, 500 - 320, myPaint);
		myPaint.setColor(Color.BLACK);
		myPaint.setTextSize(16);
		canvas.drawText(content, 20, 560, myPaint);
	}
}
