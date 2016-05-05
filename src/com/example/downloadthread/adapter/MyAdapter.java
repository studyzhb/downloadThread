package com.example.downloadthread.adapter;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.downloadthread.R;
import com.example.downloadthread.bean.ListCount;
import com.example.downloadthread.utils.CommonAdapter;
import com.example.downloadthread.utils.ViewHolder;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyAdapter extends CommonAdapter<ListCount> {
	private String name;
	private String pName;
	private OnclickButtonListener listener;
	public interface OnclickButtonListener{
		public void clickStart(String fileName,String pName);
		public void clickStop(String fileName,String pName);
	}
	public void setOnclickBtn(OnclickButtonListener listener){
		this.listener=listener;
	}
	public MyAdapter(Context context, int layoutId, List<ListCount> listdata) {
		super(context, layoutId, listdata);
		
	}

	@Override
	public void convert(final ViewHolder holder, final ListCount t) {
		holder.setText(R.id.title_path, t.getTitle());
		holder.setText(R.id.title_file, t.getFileName());
//		final Button btn1=holder.getView(R.id.start_down);
//		final Button btn2=holder.getView(R.id.pause_down);
//		List<Integer> mpos=new ArrayList<Integer>();
//		btn1.setClickable(false);
//		if(mpos.contains(holder.getPosition())){
//			btn1.setClickable(true);
//		}
//		btn1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				t.setIsclicked(btn1.isClickable());
//				btn1.setVisibility(View.GONE);
//				btn2.setVisibility(View.VISIBLE);
//				listener.clickStart(name,pName);
//				holder.getPosition();
//			}
//		});
//		
//		btn2.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				btn1.setVisibility(View.VISIBLE);
//				btn2.setVisibility(View.GONE);
//				listener.clickStop(name,pName);
//			}
//		});
	}


}
