package com.zzl.control;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CheckboxActivity extends Activity {

    //用来储存选中的内容
    ArrayList <String>item = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.checkboxview);
	
	CheckBox checkbox0 = (CheckBox)findViewById(R.id.checkboxview0); 
	CheckBox checkbox1 = (CheckBox)findViewById(R.id.checkboxview1); 
	CheckBox checkbox2 = (CheckBox)findViewById(R.id.checkboxview2); 
	CheckBox checkbox3 = (CheckBox)findViewById(R.id.checkboxview3); 
	Button button = (Button)findViewById(R.id.checkboxbutton); 
	//对checkbox进行监听
	checkbox0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	    @Override
	    public void onCheckedChanged(CompoundButton button, boolean arg1) {
		String str = button.getText().toString();
		if (button.isChecked()) {
		    item.add(str);
		} else {
		    item.remove(str);
		}

	    }
	});
	
	checkbox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	    @Override
	    public void onCheckedChanged(CompoundButton button, boolean arg1) {
		String str = button.getText().toString();
		if (button.isChecked()) {
		    item.add(str);
		} else {
		    item.remove(str);
		}

	    }
	});	
	checkbox2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	    @Override
	    public void onCheckedChanged(CompoundButton button, boolean arg1) {
		String str = button.getText().toString();
		if (button.isChecked()) {
		    item.add(str);
		} else {
		    item.remove(str);
		}

	    }
	});
	checkbox3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	    @Override
	    public void onCheckedChanged(CompoundButton button, boolean arg1) {
		String str = button.getText().toString();
		if (button.isChecked()) {
		    item.add(str);
		} else {
		    item.remove(str);
		}

	    }
	});
	
	button.setOnClickListener(new  OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		String str = item.toString();
		 Toast.makeText(CheckboxActivity.this, "您选中了" + str, Toast.LENGTH_LONG).show();
		
	    }
	});
	super.onCreate(savedInstanceState);
    }
}
