package com.cgn.task;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cgn.adapter.CourseAdapter;
import com.cgn.imoocvediodownloader.MainActivity;
import com.cgn.model.Course;
import com.cgn.util.CommonTool;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

@SuppressWarnings("deprecation")
public class GetCourseTask extends AsyncTask<String, Void, List<Course>> {

	private CourseAdapter courseAdapter;

	public GetCourseTask(Context context) {
		super();
		// TODO Auto-generated constructor stub
		courseAdapter = new CourseAdapter(context);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		MainActivity.progressDialog.show();
	}

	@Override
	protected List<Course> doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<Course> result = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(params[0]);
			HttpResponse response = client.execute(get);
			// 判断是否连接成功
			if (response.getStatusLine().getStatusCode() == 200) {
				String resultString = EntityUtils.toString(
						response.getEntity(), "utf-8");
				result = CommonTool.courseReader(resultString);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	protected void onPostExecute(List<Course> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if ((CourseAdapter) (MainActivity.courseListView.getAdapter()) == null) {
			courseAdapter.setData(result);
			MainActivity.courseListView.setAdapter(courseAdapter);
		} else {
			CourseAdapter tmp = (CourseAdapter) (MainActivity.courseListView
					.getAdapter());
			Log.d("h", tmp.getCount() + tmp.getList().toString());
			result.addAll(tmp.getList());
			courseAdapter.setData(result);
			MainActivity.courseListView.setAdapter(courseAdapter);
		}
		courseAdapter.notifyDataSetChanged();
		MainActivity.progressDialog.dismiss();
	}
}
