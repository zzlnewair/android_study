package com.zzl.file;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.zzl.file.R;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class loadRawActivity extends ListActivity {

    private class MyListAdapter extends BaseAdapter {
	private int[] colors = new int[] { 0xff626569, 0xff4f5257 };

	public MyListAdapter(Context context) {
	    mContext = context;
	}

	public int getCount() {
	    return inpormation.length;
	}

	@Override
	public boolean areAllItemsEnabled() {
	    return false;
	}

	public Object getItem(int position) {
	    return position;
	}

	public long getItemId(int position) {
	    return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	    TextView tv;
	    if (convertView == null) {
		tv = (TextView) LayoutInflater.from(mContext).inflate(
			android.R.layout.simple_list_item_1, parent, false);
	    } else {
		tv = (TextView) convertView;
	    }
	    int colorPos = position % colors.length;
	    tv.setBackgroundColor(colors[colorPos]);
	    tv.setText(String.valueOf(position + 1) + ":"
		    + inpormation[position]);
	    return tv;
	}

	private Context mContext;
    }

    String[] inpormation = null;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	readFile(R.raw.file);
	setListAdapter(new MyListAdapter(this));
	listView = getListView();
	int[] colors = { 0, 0xFF505259, 0 }; 
	listView
		.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
	listView.setDividerHeight(10);
	super.onCreate(savedInstanceState);
    }

    /**
     * 从raw中读取数据
     * @param ID
     */
    public void readFile(int ID) {
	InputStream in = null;
	String temp = "";
	try {
	    in = this.getResources().openRawResource(ID);
	    byte[] buff = new byte[1024];// 缓存
	    int rd = 0;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    while ((rd = in.read(buff)) != -1) {
		baos.write(buff, 0, rd);
		temp = new String(baos.toByteArray(), "UTF-8");
	    }
	    baos.close();
	    in.close();
	    inpormation = temp.split("\r\n");
	} catch (Exception e) {
	    Toast.makeText(this, "文件没有找到", 2000).show();
	}
    }

}
