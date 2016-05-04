package com.example.downloadthread.utils;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 关于布局中按钮之类的控件抢占焦点可以通过以下方式解决：
 * 第一种：可以在控件上添加android:focusable="false"
 * 第2种：在布局文件最外层添加 android:descendantFocusability="blocksDescendants"不让焦点下传
 * 在Listview中造成内容复用（checkbox）解决：
 * 第一种：可以在Bean中定义一个成员变量记录选中状态，
 * 在选中的时候将状态设置给bean中的变量
 * 第2种：在adapter中定义一个集合存储选中的状态的position
 * 要进行判断，选中添加，取消就删除
 * @author Administrator
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	private Context context;
	private List<T> list;
	private int layoutId;
	public CommonAdapter(Context context,int layoutId,List<T> listdata) {
		this.context=context;
		this.list=listdata;
		this.layoutId=layoutId;
	}

	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=ViewHolder.getInstance(context, convertView, layoutId, position, parent);
		convert(holder,getItem(position));
		return holder.getmConvertView();
	}
	public abstract void convert(ViewHolder holder,T t);
}
