package com.example.downloadthread.db;

import java.util.ArrayList;
import java.util.List;

import com.example.downloadthread.bean.DownloadInfo;
import com.example.downloadthread.utils.MyApplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DownloadDataDB {
	//数据库名字
	public static final String DB_NAME="down_data";
	//数据库版本
	public static final int VERSION=1;
	private static DownloadDataDB downloadDB;
	private SQLiteDatabase db;
	/**
	 * 私有化构造方法
	 */
	private DownloadDataDB(){
		DownloadOpenHelper dbHelper=new DownloadOpenHelper(MyApplication.getContext(), DB_NAME, null, VERSION);
		db=dbHelper.getWritableDatabase();
	}
	/**
	 * 获取实例对象
	 */
	public synchronized static DownloadDataDB getInstance(){
		if(downloadDB==null){
			downloadDB=new DownloadDataDB();
		}
		return downloadDB;
	}
	/**
	 * 存储读取数据进度信息
	 */
	
	public void saveData(List<DownloadInfo> infos){
		if(infos!=null){
			for(DownloadInfo downinfo:infos){
				String sql="insert into downloadData(thread_id,start_pos,end_pos,completed_size,url) values(?,?,?,?,?)";
				Object[] values={downinfo.getThreadinfo(),downinfo.getStartpos(),downinfo.getEndpos(),downinfo.getCompletedSize(),downinfo.getUrl()};
				db.execSQL(sql, values);
			}
		}
	}
	/**
	 * 根据名字获取相应的下载进度信息
	 * @param url
	 * @return
	 */
	public List<DownloadInfo> getInfo(String url){
		List<DownloadInfo> list=new ArrayList<DownloadInfo>();
		String sql="select * from downloadData where url=?";
		Cursor cursor=db.rawQuery(sql, new String[]{url});
		if(cursor.moveToFirst()){
			do{
				DownloadInfo downinfo=new DownloadInfo(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5));
				list.add(downinfo);
			}while(cursor.moveToNext());
		}
		if(cursor!=null){
			cursor.close();
		}
		return list;
	}
	/**
	 * 更新数据
	 * @param threadid
	 * @param completedSize
	 * @param url
	 */
	public void updateInfo(int threadid,int completedSize,String url){
		String sql="upadate downloadData set completed_size=? where thread_id=? and url=?";
		Object[] values={threadid,completedSize,url};
		db.execSQL(sql, values);
	}
	
	/**
	 * 删除数据
	 */
	public void deleteInfo(String url){
		db.delete("downloadData", "url=?", new String[]{url});
	}
	
	
}
