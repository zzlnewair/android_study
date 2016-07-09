package com.example.bearpicasso;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends Activity {

	private static final String BASE_URL = "http://img1.3lian.com/img2011/w1/106/85/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<Dish> dishList = new ArrayList<Dish>();

		dishList.add(new Dish(BASE_URL + "42.jpg", "水煮鱼片", "38.00"));
		dishList.add(new Dish(BASE_URL + "34.jpg", "小炒肉", "18.00"));
		dishList.add(new Dish(BASE_URL + "37.jpg", "清炒时蔬", "15.00"));
		dishList.add(new Dish(BASE_URL + "11.jpg", "金牌烤鸭", "36.00"));
		dishList.add(new Dish(BASE_URL + "12.jpg", "粉丝肉煲", "20.00"));

		ListView mListView = (ListView) this.findViewById(R.id.listview);
		MainListViewAdapter adapter = new MainListViewAdapter(dishList);
		mListView.setAdapter(adapter);
	}

	// ListView适配器
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

			//这里就是异步加载网络图片的地方
			Picasso.with(MainActivity.this).load(dish.getImgUrl())
					.into(item.img_iv);

			item.name_textview.setText(dish.getName());
			item.price_textview.setText(dish.getPrice() + "元");

			return convertView;
		}

	}

	// ListView的Item组件类
	private class ListViewItemHolder {
		ImageView img_iv;
		TextView name_textview;
		TextView price_textview;
	}
}
