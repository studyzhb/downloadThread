package com.example.downloadthread.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DownloadOpenHelper extends SQLiteOpenHelper {
	/*
	 * 建表语句
	 */
	public static final String CREATE_DOWNLOAD_DATA="create table downloadData(id integer primary key autoincrement,thread_id integer,start_pos integer,end_pos integer,completed_size integer,url text)";
	public DownloadOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_DOWNLOAD_DATA);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
