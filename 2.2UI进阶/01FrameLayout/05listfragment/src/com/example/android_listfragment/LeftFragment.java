package com.example.android_listfragment;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LeftFragment extends ListFragment {
	private ArrayAdapter<String> adapter;
	private FragmentManager manager;
	private FragmentTransaction transaction;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, getData());
		manager = getFragmentManager();
	}

	public List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			list.add("Left" + i);
		}
		return list;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.left, null);
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
		return view;
	}

	// 完成用户的点击行为
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String item = adapter.getItem(position);
		//
		RightFragment fragment = new RightFragment();
		transaction = manager.beginTransaction();
		transaction.replace(R.id.right, fragment, "fragment");
		transaction.addToBackStack("fragment");
		Bundle bundle = new Bundle();
		bundle.putString("item", item);
		fragment.setArguments(bundle);
		transaction.commit();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
