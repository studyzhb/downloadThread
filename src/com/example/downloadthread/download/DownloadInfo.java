package com.example.downloadthread.download;

/**
 * 存储下载信息的类
 * @author Administrator
 *
 */
public class DownloadInfo {
	private int threadinfo;
	private int startpos;
	private int endpos;
	private int completedSize;
	private String url;
	public DownloadInfo(int threadinfo, int startpos, int endpos, int completedSize, String url) {
		super();
		this.threadinfo = threadinfo;
		this.startpos = startpos;
		this.endpos = endpos;
		this.completedSize = completedSize;
		this.url = url;
	}

	public int getThreadinfo() {
		return threadinfo;
	}
	public void setThreadinfo(int threadinfo) {
		this.threadinfo = threadinfo;
	}
	public int getStartpos() {
		return startpos;
	}
	public void setStartpos(int startpos) {
		this.startpos = startpos;
	}
	public int getEndpos() {
		return endpos;
	}
	public void setEndpos(int endpos) {
		this.endpos = endpos;
	}
	public int getCompletedSize() {
		return completedSize;
	}
	public void setCompletedSize(int completedSize) {
		this.completedSize = completedSize;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "DownloadInfo [threadinfo=" + threadinfo + ", startpos=" + startpos + ", endpos=" + endpos
				+ ", completedSize=" + completedSize + ", url=" + url + "]";
	}
	

}
