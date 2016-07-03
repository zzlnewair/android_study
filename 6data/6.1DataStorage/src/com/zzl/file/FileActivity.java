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

	/** File�д������ݵ�·�� **/
	public final static String DATA_URL = "/data/data/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.file);
		/** ��ȡ���� **/
		String content = loadFile();
		if (content == null) {
			content = "�ϴ�û����������������";
		}
		String str = "�ϴ����뱣��ġ� " + content + "��";
		final EditText editContent = ((EditText) findViewById(R.id.file_et0));
		editContent.setHint(str);
		Button button0 = (Button) findViewById(R.id.file_button0);

		/** ������ť����󱣴��û�������Ϣ��file�� **/
		button0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/** �õ��û��������Ϣ **/
				String content = editContent.getText().toString();
				/** ��ʼ������file **/
				saveFile(content);
				ShowDialog("����File�ļ��ɹ�");
			}
		});

		Button button1 = (Button) findViewById(R.id.file_button1);
		/** ������ť��������file������ **/
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cleanFile();
				ShowDialog("���File�ļ��ɹ�");
			}
		});

		Button button2 = (Button) findViewById(R.id.file_button2);

		/** ������ť�����ɾ��file�ļ� **/
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// File file = new File(DATA_URL + getPackageName().toString() +
				// "/files", FILE_NAME);
				File file = new File(getFilesDir(), FILE_NAME);

				if (file.exists()) {
					file.delete();
				}
				ShowDialog("ɾ��file�ļ��ɹ�");
			}
		});

		super.onCreate(savedInstanceState);
	}

	/**
	 * ��������
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
	 * ��Ϊjavaɾ���ļ�����ֻ��һ��ʵ�ַ��������ǰ������ļ���д��ֻ�ǰ���Ҫɾ������һ����¼ȥ����
	 */
	public void cleanFile() {
		// ���ֻ��Ҫɾ���ļ��е�һ������������Ҫ��������ַ�����һЩ����
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
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
			}
		});
		builder.show();
	}
}
