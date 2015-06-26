package com.cgn.imoocvediodownloader;

import com.cgn.model.Course;
import com.cgn.task.GetCourseTask;
import com.cgn.util.CommonURL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnRefreshListener {

	public static ProgressDialog progressDialog;
	public static ListView courseListView;
	private SwipeRefreshLayout swipeRefreshLayout;
	public Course course;
	private int i = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		courseListView = (ListView) findViewById(R.id.course_list);
		courseListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				course = new Course();
				course = (Course) parent.getAdapter().getItem(position);
				Log.v("ListOnClick",
						course.getCourseName() + " " + course.getCourseLink());
				Intent intent = new Intent(MainActivity.this,
						ChapterActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("courseLink", course.getCourseLink());
				bundle.putString("courseTittle", course.getCourseName());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
		swipeRefreshLayout.setOnRefreshListener(this);
		// setColorScheme()已经弃用，使用setColorSchemeResources()来设置颜色
		swipeRefreshLayout.setColorSchemeResources(
				android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);

		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("提示");
		progressDialog.setMessage("正在下载");

		new GetCourseTask(this).execute(CommonURL.COURSE_LIST_URL + i);
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

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new GetCourseTask(this).execute(CommonURL.COURSE_LIST_URL + (++i));
	}
}
