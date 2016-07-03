package com.zzl.file;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zzl.file.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class loadSDActivity extends Activity {
    public final static String FILE_NAME = "b.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.sdfile);
	/**读取内容**/
	String content = loadFile();
	if(content == null) {
	    content ="上次没有输入内容请输入"; 
	}
	
	final EditText editContent = (EditText)findViewById(R.id.sdfile_et0);
	editContent.setHint("上次输入SD卡的内容的为【 " +content + "】");
	Button button0 = (Button)findViewById(R.id.sdfile_button0);
	
	/**监听按钮点击后保存用户输入信息到SD卡中**/
	button0.setOnClickListener(new  OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		/**拿到用户输入的信息**/
		String content = editContent.getText().toString();
		/**开始保存入SD卡**/
		saveFile(content);
		ShowDialog("保存SD卡文件成功");
	    }
	});
	Button button1 = (Button)findViewById(R.id.sdfile_button1);
	
	/**去清除SD卡保存的内容**/
	button1.setOnClickListener(new  OnClickListener() {
	    @Override
	    public void onClick(View arg0) {
		cleanFile();
		ShowDialog("清除SD卡文件中的内容成功");
	    }
	});	
	Button button2 = (Button)findViewById(R.id.sdfile_button2);
	
	/**删除SD卡保存的文件**/
	button2.setOnClickListener(new  OnClickListener() {
	    @Override
	    public void onClick(View arg0) {
		DeleteSDFile();
	    }
	});
	
	super.onCreate(savedInstanceState);
    }
    
    /**
     * 保存入SD卡中
     * @param str
     */
    public void saveFile(String str) {
	FileOutputStream fileOutputStream = null;

	File file = new File(Environment.getExternalStorageDirectory(),
		FILE_NAME);
	try {
	    fileOutputStream = new FileOutputStream(file);
	    fileOutputStream.write(str.getBytes());
	    fileOutputStream.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}catch (IOException e) {
	    e.printStackTrace();
	}
    }
   
    
    /**
     * 读取SD卡的内容
     * @return
     */
    public String loadFile() {
	String path = Environment.getExternalStorageDirectory() +"/" + FILE_NAME;
	try {

	    FileInputStream fi = new FileInputStream(path);
	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    fi));
	    String readString = new String();
	    while ((readString = br.readLine()) != null) {
		//数据多的话须要在这里处理 readString
		return readString;
	    }
	    fi.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }
    
    /**
     * 删除SD卡
     */
    public void DeleteSDFile() {
	String path = Environment.getExternalStorageDirectory() + "/"
		+ FILE_NAME;
	File file1 = new File(path);
	boolean isdelte = file1.delete();
	if(isdelte) {
	    ShowDialog("删除SD卡成功");
	}else {
	    finish();
	}
    }
    
    /**
     * 因为java删除文件内容只有一种实现方法，就是把整个文件重写，只是把须要删除的那一条记录去除掉 
     */
    public void cleanFile() {
	//如果只须要删除文件中的一部分内容则须要在这里对字符串做一些操作
	String cleanStr = "";
	FileOutputStream fileOutputStream = null;

	File file = new File(Environment.getExternalStorageDirectory(),
		FILE_NAME);
	try {
	    fileOutputStream = new FileOutputStream(file);
	    fileOutputStream.write(cleanStr.getBytes());
	    fileOutputStream.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}catch (IOException e) {
	    e.printStackTrace();
	}
    }
    public void ShowDialog(String str) {
	AlertDialog.Builder builder = new AlertDialog.Builder(loadSDActivity.this);
	builder.setIcon(R.drawable.icon);
	builder.setTitle(str);
	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int whichButton) {
		finish();
	    }
	});
	builder.show();
    }
}

