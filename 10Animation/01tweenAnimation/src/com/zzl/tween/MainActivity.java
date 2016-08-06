package com.zzl.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);//ʹ��alpha.xml���ɶ���Ч������
      
        //Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);//����һ��ˮƽ����ֱ��λ��
       // Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);//���ŵ����ĵ�
        //Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);//����ת����
        
       /* Animation animation = new RotateAnimation(0, 360, 
        		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(5000);*/
        
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.frame);//Ч�����ӵ�
        
        animation.setFillAfter(true);
        ImageView imageView = (ImageView) this.findViewById(R.id.imageView);
        imageView.startAnimation(animation);
    }
}