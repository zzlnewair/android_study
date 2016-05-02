package com.zzl.edittext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

@SuppressLint("NewApi")
public class KeyBoardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.keyboard);

	EditText editText0 = (EditText)findViewById(R.id.txtTest0);
	
	editText0.setOnEditorActionListener(new OnEditorActionListener() {

	    @Override
	    public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
		if (arg1 == EditorInfo.IME_ACTION_GO) {
		    Toast.makeText(KeyBoardActivity.this, "你点了软键盘'去往'按钮",
			    Toast.LENGTH_SHORT).show();
		}
		return false;
	    }
	});
	EditText editText1 = (EditText)findViewById(R.id.txtTest1);
	
	editText1.setOnEditorActionListener(new OnEditorActionListener() {

	    @Override
	    public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
		if (arg1 == EditorInfo.IME_ACTION_SEARCH) {
		    Toast.makeText(KeyBoardActivity.this, "你点了软键盘'搜索'按钮",
			    Toast.LENGTH_SHORT).show();
		}
		return false;
	    }
	});
	EditText editText2 = (EditText)findViewById(R.id.txtTest2);
	
	editText2.setOnEditorActionListener(new OnEditorActionListener() {

	    @Override
	    public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
		if (arg1 == EditorInfo.IME_ACTION_SEND) {
		    Toast.makeText(KeyBoardActivity.this, "你点了软键盘'发送'按钮",
			    Toast.LENGTH_SHORT).show();
		}
		return false;
	    }
	});
	EditText editText3 = (EditText)findViewById(R.id.txtTest3);
	
	editText3.setOnEditorActionListener(new OnEditorActionListener() {

	    @Override
	    public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
		if (arg1 == EditorInfo.IME_ACTION_NEXT) {
		    Toast.makeText(KeyBoardActivity.this, "你点了软键盘'下一个'按钮",
			    Toast.LENGTH_SHORT).show();
		}
		return false;
	    }
	});
	EditText editText4 = (EditText)findViewById(R.id.txtTest4);
	
	editText4.setOnEditorActionListener(new OnEditorActionListener() {

	    @Override
	    public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
		if (arg1 == EditorInfo.IME_ACTION_DONE) {
		    Toast.makeText(KeyBoardActivity.this, "你点了软键盘'完成'按钮",
			    Toast.LENGTH_SHORT).show();
		}
		return false;
	    }
	});
	EditText editText5 = (EditText)findViewById(R.id.txtTest5);
	
	editText5.setOnEditorActionListener(new OnEditorActionListener() {

	    @Override
	    public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
		if (arg1 == EditorInfo.IME_ACTION_UNSPECIFIED) {
		    Toast.makeText(KeyBoardActivity.this, "你点了软键盘'未指定'按钮",
			    Toast.LENGTH_SHORT).show();
		}
		return false;
	    }
	});
	super.onCreate(savedInstanceState);
    }
}
