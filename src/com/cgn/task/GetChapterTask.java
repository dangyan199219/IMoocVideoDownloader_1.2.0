package com.cgn.task;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cgn.adapter.ChapterAdapter;
import com.cgn.imoocvediodownloader.ChapterActivity;
import com.cgn.model.Chapter;
import com.cgn.util.CommonTool;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

@SuppressWarnings("deprecation")
public class GetChapterTask extends AsyncTask<String, Void, List<Chapter>> {

	private ChapterAdapter chapterAdapter;

	public GetChapterTask(Context context) {
		super();
		// TODO Auto-generated constructor stub
		chapterAdapter = new ChapterAdapter(context);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		ChapterActivity.progressDialog.show();
	}

	@Override
	protected List<Chapter> doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.v("doInBackground", "LogOn");
		List<Chapter> result = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(params[0]);
			HttpResponse response = client.execute(get);
			// 判断是否连接成功
			if (response.getStatusLine().getStatusCode() == 200) {
				String resultString = EntityUtils.toString(
						response.getEntity(), "utf-8");
				Log.v("result", resultString);
				result = CommonTool.chapterReader(resultString);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	protected void onPostExecute(List<Chapter> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		chapterAdapter.setData(result);
		ChapterActivity.chapterListView.setAdapter(chapterAdapter);
		chapterAdapter.notifyDataSetChanged();
		ChapterActivity.progressDialog.dismiss();
	}
}
