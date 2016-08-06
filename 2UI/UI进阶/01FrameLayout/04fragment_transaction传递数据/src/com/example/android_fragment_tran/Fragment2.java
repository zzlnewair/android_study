package com.example.android_fragment_tran;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment2 extends Fragment {
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
		View view = inflater.inflate(R.layout.two, null);
		button = (Button) view.findViewById(R.id.button2);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Fragment1 fragment1 = (Fragment1) getFragmentManager()
						.findFragmentByTag("fragment1");//获取Fragment1
				EditText editText = (EditText) fragment1.getView()
						.findViewById(R.id.editText1);
				Toast.makeText(getActivity(),
						"--one->>" + editText.getText().toString(), 1).show();
			}
		});
		return view;
	}
}
