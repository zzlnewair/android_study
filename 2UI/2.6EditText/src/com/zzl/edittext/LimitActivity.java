package com.zzl.edittext;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LimitActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.limit);

	final EditText editText0 = (EditText)findViewById(R.id.sample_limit_text0);
	Button button0 = (Button)findViewById(R.id.limit_button0);
	button0.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		String str = editText0.getText().toString();
		Toast.makeText(LimitActivity.this,str, Toast.LENGTH_LONG).show();
	    }
	});
	
	
	super.onCreate(savedInstanceState);
    }
}
