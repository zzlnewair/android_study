package com.zzl.universalimageloader;

import java.util.ArrayList;

import com.zzl.universalimageloader.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	/**
	 * 如果需要读取本地图片，可以使用
	 * String uri = "file:///" + "本地路径";
	 */
	
	private ImageLoader mImageLoader;
	
	private static final String BASE_URL = "http://img1.3lian.com/img2011/w1/106/85/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//获取到ImageLoader对象
		mImageLoader = ImageLoader.getInstance();
		
		ArrayList<Dish> dishList = new ArrayList<Dish>();
		
		dishList.add(new Dish(BASE_URL+"42.jpg", "水煮鱼片", "38.00"));
		dishList.add(new Dish(BASE_URL+"34.jpg", "小炒肉", "18.00"));
		dishList.add(new Dish(BASE_URL+"37.jpg", "清炒时蔬", "15.00"));
		dishList.add(new Dish(BASE_URL+"11.jpg", "金牌烤鸭", "36.00"));
		dishList.add(new Dish(BASE_URL+"12.jpg", "粉丝肉煲", "20.00"));
		
		ListView mListView = (ListView) this.findViewById(R.id.listview);
		MainListViewAdapter adapter = new MainListViewAdapter(dishList);
		mListView.setAdapter(adapter);
	}

	//网络图片加载监听器对象
	private ImageLoadingListener mImageLoadingListener = new ImageLoadingListener() {

		@Override
		public void onLoadingStarted(String arg0, View arg1) {
			System.out.println("onLoadingStarted...");
		}

		@Override
		public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
			System.out.println("onLoadingFailed...");
		}

		@Override
		public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
			System.out.println("onLoadingComplete...");
		}

		@Override
		public void onLoadingCancelled(String arg0, View arg1) {
			System.out.println("onLoadingCancelled...");
		}
	};

	//ListView适配器
	private class MainListViewAdapter extends BaseAdapter {

		private ArrayList<Dish> dishList;

		public MainListViewAdapter(ArrayList<Dish> list) {
			this.dishList = list;
		}

		@Override
		public int getCount() {
			return dishList.size();
		}

		@Override
		public Object getItem(int position) {
			return dishList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ListViewItemHolder item = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.main_listview_item, null);
				item = new ListViewItemHolder();
				item.img_iv = (ImageView) convertView
						.findViewById(R.id.imageView1);
				item.name_textview = (TextView) convertView
						.findViewById(R.id.textView1);
				item.price_textview = (TextView) convertView
						.findViewById(R.id.textView2);

				convertView.setTag(item);
			} else {
				item = (ListViewItemHolder) convertView.getTag();
			}

			Dish dish = dishList.get(position);
			
			//这里就是加载网络图片，加载的整个过程都会有调用监听器中的回调方法
			mImageLoader.displayImage(dish.getImgUrl(), item.img_iv,
					mImageLoadingListener);
			
			item.name_textview.setText(dish.getName());
			item.price_textview.setText(dish.getPrice()+"元");

			return convertView;
		}

	}

	//ListView的Item组件类
	private class ListViewItemHolder {
		ImageView img_iv;
		TextView name_textview;
		TextView price_textview;
	}
}
