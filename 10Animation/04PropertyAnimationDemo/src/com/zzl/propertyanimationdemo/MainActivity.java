package com.zzl.propertyanimationdemo;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button btn;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv= (TextView) findViewById(R.id.textView1);
		btn =(Button) findViewById(R.id.btn);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("onClick","test");
				//startObjectAnimator();
				//startValueAnimator();
				//ValuesHolderDemo();
				
				KeyframeDemo();
			}
		});
		
		
	}

	void startObjectAnimator(){//extends ValueAnimator
		
		//创建一个水平移动的动画对象，从位置0到300平移
        final ObjectAnimator translation = ObjectAnimator.ofFloat(tv, "translationX", 0f, 300f);
        translation.setDuration(1500);
        translation.setInterpolator(new AccelerateDecelerateInterpolator());//：添加一个插值器等
        translation.start();
        
      
	}
	
	
	void startValueAnimator(){
		
		final ValueAnimator translation = ValueAnimator.ofFloat(0f, 300f);
		translation.setDuration(1500);
		translation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                tv.setTranslationX((float) animation.getAnimatedValue());
            }
        });
		translation.start();
		
		
	}
	//用PropertyValuesHolder控制多个对象属性
	void ValuesHolderDemo() {
		// 把按钮放大1.5倍
		PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f,
				1.5f);
		PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f,
				1.5f);
		final ObjectAnimator scale = ObjectAnimator.ofPropertyValuesHolder(btn,
				pvhX, pvhY);

		final ValueAnimator translation = ValueAnimator.ofFloat(0f, 300f);
		translation.setDuration(1500);
		
		// 执行放大动画
				scale.start();
				translation.start();
		
	}
	
	/*
	//使用Keyframe对象控制多属性动画
	每个keyframe变量第一个参数执行总时间0%~100%,0表示动画未执行，1代码动画执行完毕

    keyframe0代表动画在0%时的位置

    keyframe1代表动画在30%时所处的位置

    keyframe2代表动画在最后一个时间段所处的位置

    keyframe3代表在200个单位距离时动画完成已经停止，加上这一行是为了动画停止后对象不会消失
    */
	void KeyframeDemo(){
		
		    Keyframe keyframe0 = Keyframe.ofFloat(0f, 0);
	        Keyframe keyframe1 = Keyframe.ofFloat(0.3f, 100);
	        Keyframe keyframe2 = Keyframe.ofFloat(0.4f, 200);
	        Keyframe keyframe3 = Keyframe.ofFloat(1f, 200);         
	        PropertyValuesHolder pvhM = PropertyValuesHolder.ofKeyframe("translationX", keyframe0,keyframe1, keyframe2,keyframe3);
	        final ObjectAnimator trans = ObjectAnimator.ofPropertyValuesHolder(tv, pvhM);
	        trans.setDuration(3500);
	        //final ValueAnimator translation = ValueAnimator.ofFloat(0f, 300f);
	        //translation.setDuration(1500);
	        
	       // scale.start();
            trans.start();
		
	}
	
}
