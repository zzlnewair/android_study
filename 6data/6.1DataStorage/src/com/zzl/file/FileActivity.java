package com.zzl.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.zzl.file.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FileActivity extends Activity {
	public final static String FILE_NAME = "a.txt";

	/** File中储存数据的路径 **/
	public final static String DATA_URL = "/data/data/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.file);
		/** 读取内容 **/
		String content = loadFile();
		if (content == null) {
			content = "上次没有输入内容请输入";
		}
		String str = "上次输入保存的【 " + content + "】";
		final EditText editContent = ((EditText) findViewById(R.id.file_et0));
		editContent.setHint(str);
		Button button0 = (Button) findViewById(R.id.file_button0);

		/** 监听按钮点击后保存用户输入信息到file中 **/
		button0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/** 拿到用户输入的信息 **/
				String content = editContent.getText().toString();
				/** 开始保存入file **/
				saveFile(content);
				ShowDialog("保存File文件成功");
			}
		});

		Button button1 = (Button) findViewById(R.id.file_button1);
		/** 监听按钮点击后清空file中内容 **/
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cleanFile();
				ShowDialog("清空File文件成功");
			}
		});

		Button button2 = (Button) findViewById(R.id.file_button2);

		/** 监听按钮点击后删除file文件 **/
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// File file = new File(DATA_URL + getPackageName().toString() +
				// "/files", FILE_NAME);
				File file = new File(getFilesDir(), FILE_NAME);

				if (file.exists()) {
					file.delete();
				}
				ShowDialog("删除file文件成功");
			}
		});

		super.onCreate(savedInstanceState);
	}

	/**
	 * 保存内容
	 * 
	 * @param str
	 */
	public void saveFile(String str) {
		try {
			FileOutputStream outStream = this.openFileOutput(FILE_NAME,
					Context.MODE_WORLD_READABLE);
			outStream.write(str.getBytes());
			outStream.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * 因为java删除文件内容只有一种实现方法，就是把整个文件重写，只是把须要删除的那一条记录去除掉
	 */
	public void cleanFile() {
		// 如果只须要删除文件中的一部分内容则须要在这里对字符串做一些操作
		String cleanStr = "";
		try {
			FileOutputStream outStream = this.openFileOutput(FILE_NAME,
					Context.MODE_WORLD_READABLE);
			outStream.write(cleanStr.getBytes());
			outStream.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}

	public String loadFile() {
		try {
			FileInputStream inStream = this.openFileInput(FILE_NAME);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				stream.write(buffer, 0, length);
			}
			stream.close();
			inStream.close();
			return stream.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
		return null;
	}

	public void ShowDialog(String str) {
		AlertDialog.Builder builder = new AlertDialog.Builder(FileActivity.this);
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
