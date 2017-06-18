package com.example.android_fragment_tran;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment1 extends Fragment {
	private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.one, null);
		button = (Button) view.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 获得第二个Fragment的输入框的值
				Fragment2 fragment2 = (Fragment2) getFragmentManager()
						.findFragmentByTag("fragment2");//获取Fragment2
				EditText editText = (EditText) fragment2.getView()
						.findViewById(R.id.editText2);
				Toast.makeText(getActivity(),
						"--two->>" + editText.getText().toString(), 1).show();
			}
		});
		return view;
	}
}
