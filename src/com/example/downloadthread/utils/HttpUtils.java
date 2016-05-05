package com.example.downloadthread.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.downloadthread.db.DownloadDataDB;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class HttpUtils {
	private static DownloadDataDB downDB;
	private static boolean PAUSE = false;

	public static void pauseQuest() {
		if (!PAUSE) {
			PAUSE = true;
		}
	}

	public static void requestDownloadMessage(final int threadId, final String urlpath, final int startSize,
			final int endSize, final String localFile, final OndownloadCallBack callback) {

//		final Handler handler = new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				switch (msg.what) {
//				case 1:
//					callback.getResponse(urlpath, msg.arg2, msg.arg1, threadId);
//					break;
//
//				default:
//					break;
//				}
//			}
//		};
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpURLConnection httpconn = null;
				try {
					
					URL url = new URL(urlpath);
					httpconn = (HttpURLConnection) url.openConnection();
					httpconn.setRequestMethod("GET");
					httpconn.setConnectTimeout(8000);
					httpconn.setReadTimeout(8000);
					if (localFile == null) {
						Log.d("threadid", Thread.currentThread().getName());
						InputStream is = httpconn.getInputStream();
						// 字节流字符流转换
						BufferedInputStream bis=new BufferedInputStream(is);
						ByteArrayOutputStream byteos=new ByteArrayOutputStream();
						byte[] buffer=new byte[1024];
						int len=0;
						while(-1!=(len=bis.read(buffer))){
							byteos.write(buffer, 0, len);
						}
						byteos.close();
						bis.close();
						is.close();
						callback.getString(new String(byteos.toByteArray(),"utf-8"));
						return;
					}
					File file = new File(Environment.getExternalStorageDirectory(),localFile);
					if (!file.exists()) {
						file.createNewFile();
					}
					// 类似IO操作，可以读写
					RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
					if (endSize != 0) {
						downDB = DownloadDataDB.getInstance();
						httpconn.setRequestProperty("Range", "bytes=" + startSize + "-" + endSize);
						InputStream is = httpconn.getInputStream();
						// 标记写入的起始位置
						randomAccessFile.seek(startSize);
						byte[] buffer = new byte[2048];
						int len = 0;
						int compledSize = 0;
						while (-1 != (len = is.read(buffer))) {
							randomAccessFile.write(buffer, 0, len);
							compledSize += len;
//							Message message = Message.obtain();
//							message.what = 1;
//							message.arg1 = len;
//							message.arg2 = compledSize;
//							message.obj = urlpath;
							downDB.updateInfo(threadId, compledSize, urlpath);
//							handler.sendMessage(message);
							if (PAUSE) {
								return;
							}
						}

					} else {
						int fileSize = httpconn.getContentLength();
						randomAccessFile.setLength(fileSize);
						randomAccessFile.close();
						httpconn.disconnect();
						callback.getFileSize(fileSize);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (httpconn != null) {
						httpconn.disconnect();
					}
				}

			}
		}).start();
	}

	public interface OndownloadCallBack {
		public void getFileSize(int fileSize);

		public void getResponse(String url, int completedSize, int len, int threadid);

		public void getString(String str);

	}
}
