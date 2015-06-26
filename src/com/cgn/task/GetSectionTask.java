package com.cgn.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cgn.imoocvediodownloader.ChapterActivity;
import com.cgn.imoocvediodownloader.MainActivity;
import com.cgn.model.Section;
import com.cgn.util.CommonTool;
import com.cgn.util.DownloadVideo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class GetSectionTask extends AsyncTask<String, Void, List<Section>> {

	private Context context;

	// public AsyncResponse delegate = null;

	// private String videoName;
	// private String videoDownloadLink;
	//
	// public String getVideoDownloadLink() {
	// if (videoDownloadLink != null) {
	// return videoDownloadLink;
	//
	// } else {
	// return null;
	// }
	// }
	//
	// public void setVideoDownloadLink(String videoDownloadLink) {
	// this.videoDownloadLink = videoDownloadLink;
	// }
	//
	// public String getVideoName() {
	// return videoName;
	// }
	//
	// public void setVideoName(String videoName) {
	// this.videoName = videoName;
	// }

	public GetSectionTask(Context context) {
		super();
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		MainActivity.progressDialog.show();
	}

	@Override
	protected void onPostExecute(List<Section> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		ChapterActivity.progressDialog.dismiss();
		ChapterActivity.videoName = result.get(0).getSectionName();
		ChapterActivity.videoDownloadLink = result.get(0)
				.getSectionDownloadLink();
		Log.v("lak", ChapterActivity.videoDownloadLink + "<--->"
				+ ChapterActivity.videoName);
		DownloadVideo downloadVideo = new DownloadVideo(
				ChapterActivity.videoDownloadLink,
				ChapterActivity.chapter.getSectionName(),
				ChapterActivity.videoSaveName);
		downloadVideo.download();
		// setVideoName(result.get(0).getSectionName());
		// setVideoDownloadLink(result.get(0).getSectionDownloadLink());
		Toast.makeText(
				context,
				ChapterActivity.videoName + "------"
						+ ChapterActivity.videoDownloadLink, Toast.LENGTH_SHORT)
				.show();
		// delegate.processFinish(result);
	}

	@Override
	protected List<Section> doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<Section> resultList = new ArrayList<Section>();
		Section section = new Section();
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(params[0]);
			HttpResponse response = client.execute(get);
			// 判断是否连接成功
			if (response.getStatusLine().getStatusCode() == 200) {
				String resultString = EntityUtils.toString(
						response.getEntity(), "utf-8");
				Log.v("resultString", resultString);
				section = CommonTool.sectionReader(resultString);
				resultList.add(section);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultList;
	}
	//
	// public interface AsyncResponse {
	// void processFinish(List<Section> result);
	// }

}
