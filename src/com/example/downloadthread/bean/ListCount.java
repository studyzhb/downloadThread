package com.example.downloadthread.bean;


public class ListCount {

	private String title;
	private String fileName;
	private boolean isclicked;
	
	

	public boolean isIsclicked() {
		return isclicked;
	}

	public void setIsclicked(boolean isclicked) {
		this.isclicked = isclicked;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}
	


	public ListCount(String title, String fileName) {
		super();
		this.title = title;
		this.fileName = fileName;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
