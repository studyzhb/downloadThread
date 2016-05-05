package com.example.downloadthread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.downloadthread.adapter.MyAdapter;
import com.example.downloadthread.adapter.MyAdapter.OnclickButtonListener;
import com.example.downloadthread.bean.ListCount;
import com.example.downloadthread.download.Downloader;
import com.example.downloadthread.download.LoadDownloaderInfo;
import com.example.downloadthread.utils.BaseActivity;
import com.example.downloadthread.utils.Costant;
import com.example.downloadthread.utils.HandleUtisl;
import com.example.downloadthread.utils.HttpUtils;
import com.example.downloadthread.utils.HttpUtils.OndownloadCallBack;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnclickButtonListener{
	private ListView listView;
	private MyAdapter adapter;
	private String resName;
	private String fileName;
	private String pName;
	private List<ListCount> listdata=new ArrayList<ListCount>();
	 private static final String SD_PATH = Environment.getExternalStorageDirectory()+"/down";
	 private Map<String,LoadDownloaderInfo> downloaders=new HashMap<String, LoadDownloaderInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initData();
	}

	private void initData() {
		HttpUtils.requestDownloadMessage(0, Costant.URL_PATH, 0, 0, null, new OndownloadCallBack() {
			
			@Override
			public void getString(String str) {
				List<Map<String,Object>> list=HandleUtisl.changeString(str);
				for(Map<String,Object> ll:list){
					listdata.add(new ListCount((String)ll.get("image"),(String)ll.get("title")));
				}
				adapter.notifyDataSetChanged();
			}
			
			@Override
			public void getResponse(String url, int completedSize, int len, int threadid) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void getFileSize(int fileSize) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	private void init() {
		listView=(ListView) findViewById(R.id.listView);
		adapter=new MyAdapter(this, R.layout.item, listdata);
		listView.setAdapter(adapter);
		adapter.setOnclickBtn(this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				resName=listdata.get(position).getFileName()+".jpg";
				String url=Costant.URL_PICTURE+listdata.get(position).getTitle();
				
				new MyTask().execute(url,resName);
				
			}
		});
				
	}

	@Override
	public void clickStart(String fileName,String pName) {
		Toast.makeText(this, fileName+"start", Toast.LENGTH_SHORT).show();
		this.fileName=fileName;
		this.pName=pName;
	}

	@Override
	public void clickStop(String fileName,String pName) {
		Toast.makeText(this, fileName+"pause", Toast.LENGTH_SHORT).show();
		
	}

	public class MyTask extends AsyncTask<String, Integer, LoadDownloaderInfo>{
		Downloader downloader=null;
		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
		}
		@Override
		protected LoadDownloaderInfo doInBackground(String... params) {
			String key=params[0];
			String path=params[1];//文件存放路径
			downloader=new Downloader(key, path);
			LoadDownloaderInfo ldli=downloader.getDownloadInfo();
			downloaders.put(params[0],ldli);
			
			return ldli;
		}
		@Override
		protected void onPostExecute(LoadDownloaderInfo result) {			
			if(result!=null){
				downloader.download();
			}
		}
	}

	
}
