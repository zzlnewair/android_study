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
	/**��ȡ����**/
	String content = loadFile();
	if(content == null) {
	    content ="�ϴ�û����������������"; 
	}
	
	final EditText editContent = (EditText)findViewById(R.id.sdfile_et0);
	editContent.setHint("�ϴ�����SD�������ݵ�Ϊ�� " +content + "��");
	Button button0 = (Button)findViewById(R.id.sdfile_button0);
	
	/**������ť����󱣴��û�������Ϣ��SD����**/
	button0.setOnClickListener(new  OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		/**�õ��û��������Ϣ**/
		String content = editContent.getText().toString();
		/**��ʼ������SD��**/
		saveFile(content);
		ShowDialog("����SD���ļ��ɹ�");
	    }
	});
	Button button1 = (Button)findViewById(R.id.sdfile_button1);
	
	/**ȥ���SD�����������**/
	button1.setOnClickListener(new  OnClickListener() {
	    @Override
	    public void onClick(View arg0) {
		cleanFile();
		ShowDialog("���SD���ļ��е����ݳɹ�");
	    }
	});	
	Button button2 = (Button)findViewById(R.id.sdfile_button2);
	
	/**ɾ��SD��������ļ�**/
	button2.setOnClickListener(new  OnClickListener() {
	    @Override
	    public void onClick(View arg0) {
		DeleteSDFile();
	    }
	});
	
	super.onCreate(savedInstanceState);
    }
    
    /**
     * ������SD����
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
     * ��ȡSD��������
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
		//���ݶ�Ļ���Ҫ�����ﴦ�� readString
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
     * ɾ��SD��
     */
    public void DeleteSDFile() {
	String path = Environment.getExternalStorageDirectory() + "/"
		+ FILE_NAME;
	File file1 = new File(path);
	boolean isdelte = file1.delete();
	if(isdelte) {
	    ShowDialog("ɾ��SD���ɹ�");
	}else {
	    finish();
	}
    }
    
    /**
     * ��Ϊjavaɾ���ļ�����ֻ��һ��ʵ�ַ��������ǰ������ļ���д��ֻ�ǰ���Ҫɾ������һ����¼ȥ���� 
     */
    public void cleanFile() {
	//���ֻ��Ҫɾ���ļ��е�һ������������Ҫ��������ַ�����һЩ����
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
	builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int whichButton) {
		finish();
	    }
	});
	builder.show();
    }
}

