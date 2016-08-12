package com.zzl.image;

import java.io.File;
import java.io.ObjectInputStream.GetField;

import com.zzl.service.ImageService;
import com.zzl.utils.ImageFactory;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText pathText;
    private ImageView imageView;
    private  String path;
    private  String storePath;
    private byte[] data ;
    private LinearLayout mLinearLayout;
    
    Bitmap bitmap;
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		pathText = (EditText) this.findViewById(R.id.imagepath);
		imageView = (ImageView) this.findViewById(R.id.imageView);
		Button button = (Button) this.findViewById(R.id.btndisplay);
		button.setOnClickListener(new ButtonClickListener());
		
		Button button2 = (Button) this.findViewById(R.id.btn2);//缩小
		button2.setOnClickListener(new ButtonClickListener());
		

		Button button3 = (Button) this.findViewById(R.id.btn3);//放大
		button3.setOnClickListener(new ButtonClickListener());
		
		mLinearLayout=(LinearLayout)this.findViewById(R.id.ll);

		path = pathText.getText().toString();
		storePath=getFilesDir().getAbsolutePath()+"/test.jpg";
		try {
			data = ImageService.getImage(path);

			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

			ImageFactory.storeImage(bitmap, storePath);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    private final class ButtonClickListener implements View.OnClickListener{

		public void onClick(View v) {
			
			switch(v.getId()){
			
			case R.id.btndisplay:
				imageView.setImageBitmap(bitmap);
				
				break;
			case R.id.btn2://缩小
				imageView.setImageBitmap(ImageFactory.ratio(storePath,320,240 ));
				break;
			
				
			case R.id.btn3:	//放大
				//int weight=mLinearLayout.getWidth();
				//int height=mLinearLayout.getHeight();
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);//android:scaleType="fitXY" 全屏填充
				imageView.setImageBitmap(bitmap);
				break;
				
			}
			
		}
    }
}