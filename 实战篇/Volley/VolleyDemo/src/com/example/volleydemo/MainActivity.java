package com.example.volleydemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView lv_show;
	private List<String> list;   //�������ͼƬ��ַ
	private static ImageLoader imageLoader;//ͼƬ������
	
	private MyAdapter myAdapter;  //listView������
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RequestQueue queue = Volley.newRequestQueue(this); //�������
		imageLoader = new ImageLoader(queue, new VolleyBitmapLruCache(this)); 
		//����ͼƬ��������ŵ��ļ���
		File f=new File("sdcard/abc");
		if(!f.exists()){
			f.mkdirs();
		}
		lv_show=(ListView) findViewById(R.id.lv_show);
		list=new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("http://image3.uuu9.com/war3/dota2/UploadFiles/201303/_Z201303251348195461.jpg");//�������ͼƬ��ַ
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/klw.gif");
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/bingv.jpg");
			list.add("http://image3.uuu9.com/war3/dota2/UploadFiles/201303/_Z201303251348291251.jpg");
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/dfss.jpg");
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/hycm.jpg");
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/FW.gif");
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		if(myAdapter==null){
			myAdapter=new MyAdapter();
		}
		else{
			myAdapter.notifyDataSetChanged();
		}
		lv_show.setAdapter(myAdapter);
	}
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			Holder holder=null;
			if(convertView==null){
				holder=new Holder();
				convertView=getLayoutInflater().inflate(R.layout.lv_item,null);  //���Ӳ��ּ����ڴ�
				holder.iv_image=(NetworkImageView) convertView.findViewById(R.id.iv_image);  //�ҵ�ͼƬ
				convertView.setTag(holder);//���ؼ����뻺��
			}
			else{
				holder=(Holder) convertView.getTag();
			}
			holder.iv_image.setImageUrl(list.get(position), imageLoader);  //ֱ������ͼƬ��ַ��NetworkImage,����ͼƬ���뻺��
			holder.iv_image.setDefaultImageResId(R.drawable.ic_launcher);  //��ͼƬ��û���س���ʱ��ʾ��ͼƬ
			return convertView;
		}
		
	}
	class Holder {
		NetworkImageView iv_image;
	}
	

}
