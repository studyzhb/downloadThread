package com.example.downloadthread.download;

/**
 * 用来存储下载器详细信息的类
 * @author studyzhb
 *
 */
public class LoadDownloaderInfo {

	/**
	 * 管理下载器，文件大小，完成度
	 */
	private String loaderTag;
	private int fileSize;
	private int completedSize;
	public LoadDownloaderInfo(String loaderTag, int fileSize, int completedSize) {
		super();
		this.loaderTag = loaderTag;
		this.fileSize = fileSize;
		this.completedSize = completedSize;
	}
	public String getLoaderTag() {
		return loaderTag;
	}
	public void setLoaderTag(String loaderTag) {
		this.loaderTag = loaderTag;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public int getCompletedSize() {
		return completedSize;
	}
	public void setCompletedSize(int completedSize) {
		this.completedSize = completedSize;
	}
	@Override
	public String toString() {
		return "LoadDownloaderInfo [loaderTag=" + loaderTag + ", fileSize=" + fileSize + ", completedSize="
				+ completedSize + "]";
	}
	
	
	

}
