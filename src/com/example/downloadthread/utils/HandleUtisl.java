package com.example.downloadthread.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.downloadthread.db.DownloadDataDB;

public class HandleUtisl {

	public synchronized static void handleReadLen(DownloadDataDB downDB){
		
	}
	
	public static List<Map<String,Object>> changeString(String str) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		try {
			JSONObject json=new JSONObject(str);
//			int count=json.getInt("total");
//			map.put("count", count);
//			list.add(map);
			JSONArray tngoes=json.getJSONArray("tngou");
			for(int i=0;i<tngoes.length();i++){
				Map<String,Object> map=new HashMap<String, Object>();
				JSONObject single=tngoes.getJSONObject(i);
				String image=single.getString("img");
				String title=single.getString("title");
				map.put("image", image);
				map.put("title", title);		
				list.add(map);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
