package com.example.downloadthread.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder {
	/**
	 * SparseArray用来替换key为int类型的Map
	 * ViewHolder用集合来存储布局中的单个view元素
	 */
	private SparseArray<View> views;
	
	private int position;
	//布局位置
	private View mConvertView;
	
	private ViewHolder(Context context,int layoutId,int position,ViewGroup parent) {
		this.views=new SparseArray<View>();
		this.position=position;
		this.mConvertView=LayoutInflater.from(context).inflate(layoutId, parent, false);
		this.mConvertView.setTag(this);
	}
	public static ViewHolder getInstance(Context context,View convertView,int layoutId,int position,ViewGroup parent){
		if(convertView==null){
			return new ViewHolder(context,layoutId,position,parent);
		}else{
			ViewHolder holder=(ViewHolder) convertView.getTag();
			holder.position=position;
			return holder;
		}
	}
	public View getmConvertView() {
		return mConvertView;
	}
	
	public int getPosition() {
		return position;
	}
	/**
	 * 通过控件ID获取View
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId){
		View view=views.get(viewId);
		if(view==null){
			view=mConvertView.findViewById(viewId);
			views.put(viewId, view);
		}
		return (T) view;
	}
	public void setText(int viewId,String text){
		TextView tv=getView(viewId);
		tv.setText(text);
	}
	
}
