package com.cgn.imoocvediodownloader;

import java.util.List;

import com.cgn.model.Chapter;
import com.cgn.task.GetChapterTask;
import com.cgn.task.GetSectionTask;
import com.cgn.util.CommonTool;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ChapterActivity extends Activity {
	public static String videoSaveName;
	public static ProgressDialog progressDialog;
	public static ListView chapterListView;

	public List<Chapter> chapterList = null;
	public static Chapter chapter;
	public static String videoName;
	public static String videoDownloadLink;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chapter);
		chapterListView = (ListView) findViewById(R.id.chapter_list);
		chapterListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				chapter = new Chapter();
				chapter = (Chapter) parent.getAdapter().getItem(position);
				Log.v("ListOnClick",
						chapter.getChapterName() + " "
								+ chapter.getSectionLink());
				String sectionDownloadLink = CommonTool
						.getJsonURLFromSectionLink(chapter.getSectionLink());
				GetSectionTask getSectionTask = new GetSectionTask(
						getApplicationContext());
				getSectionTask.execute(sectionDownloadLink);
			}
		});
		// 新页面接收数据
		Bundle bundle = this.getIntent().getExtras();
		// 接收name值
		String courseLink = bundle.getString("courseLink");
		setTitle(bundle.getString("courseTittle"));
		videoSaveName = bundle.getString("courseTittle");
		Log.v("获取到的courseLink值为", courseLink);

		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("Tips");
		progressDialog.setMessage("正在下载");

		new GetChapterTask(this).execute(courseLink);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
