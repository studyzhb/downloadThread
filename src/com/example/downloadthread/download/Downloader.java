package com.example.downloadthread.download;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.downloadthread.bean.DownloadInfo;
import com.example.downloadthread.db.DownloadDataDB;
import com.example.downloadthread.utils.Costant;
import com.example.downloadthread.utils.HttpUtils;
import com.example.downloadthread.utils.HttpUtils.OndownloadCallBack;

import android.os.Environment;
import android.util.Log;


/**
 * 下载器类
 * 变量包括文件大小，开启线程数，下载地址，保存路径，保存下载信息的信息类和保存下载器信息的类
 * @author Administrator
 *
 */
public class Downloader implements OndownloadCallBack{
	/**
	 * 定义下载地址，文件总大小，线程数（可以进行分组按比例开启），存储地址，
	 * 还要有一个context用来向数据库存储数据信息时用到，可以使用Application，不用传
	 */
	private String urlstr;
	//文件大小
	private static int filesize;
	//本地存放路径
	private String localfile;
	private int threadcount;
	//每个线程下载器所下载的信息集合
	private List<DownloadInfo> dinfos;
	
	private DownloadDataDB downDB;
	
	//定义下载状态 init(初始状态),downloading(正在下载),pause(暂停);
	private static final int INIT_STATE=0;	
	private static final int DOWNLOADING_STATE=1;
	private static final int PAUSE_STATE=2;
	private int state=INIT_STATE;
	public Downloader(String urlstr, String localfile) {
		this.urlstr = urlstr;
		this.localfile = localfile;
		downDB=DownloadDataDB.getInstance();
		
	}
	
	/**
	 * 从数据库读取是否为空判断是否是第一次
	 * @return
	 */
	private boolean isFirstDownload(){
		List<DownloadInfo> list=DownloadDataDB.getInstance().getInfo(urlstr);
		if(list!=null&&!list.isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取下载的信息并将信息保存（包括线程）
	 * 首先判断是不是第一次下载，不是的话先从数据库读取数据
	 */
	public LoadDownloaderInfo getDownloadInfo(){
		if(isFirstDownload()){
			init();
			Log.d("--filesize", filesize+"");
			int result=filesize/Costant.THREAD_READ;
			Log.d("---->result", result+"");
			int re=filesize%Costant.THREAD_READ;
			if(re==0){
				threadcount=4;
			}else{
				threadcount=4;
			}
			int range=filesize/threadcount;
			dinfos=new ArrayList<DownloadInfo>();
			for(int i=0;i<threadcount-1;i++){
				DownloadInfo info=new DownloadInfo(1000+i, i*range, (i+1)*range-1, 0, urlstr);
				dinfos.add(info);
			}
			dinfos.add(new DownloadInfo(1000+(threadcount-1), (threadcount-1)*range, filesize, 0, urlstr));
			downDB.saveData(dinfos);
			//一个文件存储进一个下载管理器来管理包含的所有线程和完成度
			LoadDownloaderInfo loadinfo=new LoadDownloaderInfo(urlstr, filesize, 0);
			return loadinfo;
		}else{
			dinfos=downDB.getInfo(urlstr);
			int size=0;
			int complete=0;
			for(DownloadInfo dinfo:dinfos){
				complete+=dinfo.getCompletedSize();
				size+=dinfo.getEndpos()-dinfo.getStartpos()+1;
			}
			return new LoadDownloaderInfo(urlstr, size, complete);
		}

	}

	private void init() {
		HttpUtils.requestDownloadMessage(0, urlstr, 0, 0, localfile,this);
		
		
		
//		HttpURLConnection httpconn = null;
//		try {
//
//			URL url = new URL(urlstr);
//			httpconn = (HttpURLConnection) url.openConnection();
//			httpconn.setRequestMethod("GET");
//			httpconn.setConnectTimeout(8000);
//			httpconn.setReadTimeout(8000);
//			
//			File file = new File(Environment.getExternalStorageDirectory(),localfile);
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			// 类似IO操作，可以读写
//			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
//			
//
//				filesize = httpconn.getContentLength();
//				randomAccessFile.setLength(filesize);
//				randomAccessFile.close();
//				httpconn.disconnect();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (httpconn != null) {
//				httpconn.disconnect();
//			}
//		}

	}
		
	
	public void download(){
		if(dinfos!=null){
			if(state==DOWNLOADING_STATE){
				return;
			}
			for(DownloadInfo dinfo:dinfos){
				HttpUtils.requestDownloadMessage(dinfo.getThreadinfo(), dinfo.getUrl(), dinfo.getStartpos()+dinfo.getCompletedSize(), dinfo.getEndpos(), localfile, this);
			}
		}
	}

	@Override
	public void getFileSize(int fileSize) {
		this.filesize=fileSize;
		Log.d("--filesize--before", filesize+"");
		
	}

	@Override
	public void getResponse(String url, int completedSize, int len, int threadid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getString(String str) {
		// TODO Auto-generated method stub
		
	}

}
