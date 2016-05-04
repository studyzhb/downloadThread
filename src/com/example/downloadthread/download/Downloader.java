package com.example.downloadthread.download;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

/**
 * 下载器类
 * 变量包括文件大小，开启线程数，下载地址，保存路径，保存下载信息的信息类和保存下载器信息的类
 * @author Administrator
 *
 */
public class Downloader {
	/**
	 * 定义下载地址，文件总大小，线程数（可以进行分组按比例开启），存储地址，
	 * 还要有一个context用来向数据库存储数据信息时用到，可以使用Application，不用传
	 */
	private String urlstr;
	//文件大小
	private int filesize;
	//本地存放路径
	private String localfile;
	private int threadcount;
	private Handler handler;
	//每个线程下载器所下载的信息集合
	private List<DownloadInfo> dinfos=new ArrayList<DownloadInfo>();
	//定义下载状态 init(初始状态),downloading(正在下载),pause(暂停);
	private static final int INIT_STATE=0;	
	private static final int DOWNLOADING_STATE=1;
	private static final int PAUSE_STATE=2;
	private int state=INIT_STATE;
	public Downloader(String urlstr, int filesize, String localfile, int threadcount, Handler handler) {
		this.urlstr = urlstr;
		this.filesize = filesize;
		this.localfile = localfile;
		this.threadcount = threadcount;
		this.handler = handler;
	}
	
	/**
	 * 从数据库读取是否为空判断是否是第一次
	 * @return
	 */
	private boolean isFirstDownload(){
		return true;
	}
	
	/**
	 * 获取下载的信息并将信息保存（包括线程）
	 * 首先判断是不是第一次下载，不是的话先从数据库读取数据
	 */
	public DownloadInfo getDownloadInfo(){
		if(isFirstDownload()){
			
		}else{
			//暂无
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return null;
	}

}
