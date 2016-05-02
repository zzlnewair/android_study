package com.zzl.control;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTextActivity extends Activity {

    Context mContext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.editview);
	mContext = this;
	//帐号
	final EditText editText0 = (EditText)findViewById(R.id.editview0);
	//密码
	final EditText editText1 = (EditText)findViewById(R.id.editview1);
	
	//确认按钮
	Button button = (Button)findViewById(R.id.editbutton0);
	
	button.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		String username = editText0.getText().toString();
		String password = editText1.getText().toString();
		Toast.makeText(EditTextActivity.this, "用户名："+username +"密码："+ password, Toast.LENGTH_LONG).show();
	    }
	});
	super.onCreate(savedInstanceState);
    }
}
