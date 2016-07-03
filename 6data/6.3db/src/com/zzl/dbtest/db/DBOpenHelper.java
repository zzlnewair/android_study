package com.zzl.dbtest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	// �������ƣ�
	private static final String DBNAME = "test.db";
	// ���ݿ�汾
	private static final int version = 1;
	
	public DBOpenHelper(Context context) {
		super(context, DBNAME, null, version);//<��>/databases/
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//�������ݿ�ÿһ�α�������ʱ����õ�
		db.execSQL("CREATE TABLE person(personid integer primary key autoincrement, name varchar(20), phone varchar(12), amount integer )");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("ALTER TABLE person ADD amount integer");
	}

}
