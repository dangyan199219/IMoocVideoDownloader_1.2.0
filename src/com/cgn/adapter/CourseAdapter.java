package com.cgn.adapter;

import java.util.List;

import com.cgn.model.Course;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class CourseAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater = null;

	private List<Course> list = null;// 定义数据格式

	public CourseAdapter(Context context) {
		// 获取LayoutInflater实例，用于动态加载布局
		layoutInflater = LayoutInflater.from(context);
	}

	public void setData(List<Course> list) {
		// TODO Auto-generated method stub
		this.list = list;
	}

	public List<Course> getList() {
		return list;
	}

	public void setList(List<Course> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
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
		// TODO Auto-generated method stub
		View view = null;
		if (convertView == null) {
			// 注意这里的null，有时候用来null之后不能正常显示，要改为this，此处不可以用this，报错
			// 动态加载布局文件item_content_listview.xml
			view = layoutInflater.inflate(
					com.cgn.imoocvediodownloader.R.layout.course_content, null);
		} else {
			view = convertView;
		}
		// 注意这里是view.findViewById(R.id.tittle_edit),不写view会出现空指针异常
		TextView mCourseName = (TextView) view
				.findViewById(com.cgn.imoocvediodownloader.R.id.tv_course_name);
		TextView mCourseSummary = (TextView) view
				.findViewById(com.cgn.imoocvediodownloader.R.id.tv_course_summary);
		TextView mCourseStatus = (TextView) view
				.findViewById(com.cgn.imoocvediodownloader.R.id.tv_course_status);

		mCourseName.setText(list.get(position).getCourseName());
		mCourseSummary.setText(list.get(position).getCourseSummary());
		mCourseStatus.setText(list.get(position).getCourseStatus());

		Log.v("mCourseName", list.get(position).getCourseName().toString());
		Log.v("mCourseSummary", list.get(position).getCourseSummary()
				.toString());
		Log.v("mCourseStatus", list.get(position).getCourseStatus().toString());
		Log.v("mCourseImagePath", list.get(position).getCourseImagePath()
				.toString());
		// 如何加载图片
		final ImageView mAppIconImageView = (ImageView) view
				.findViewById(com.cgn.imoocvediodownloader.R.id.course_image);
		com.cgn.util.DownloadImage downloadImage = new com.cgn.util.DownloadImage(
				list.get(position).getCourseImagePath().toString());
		downloadImage.loadImage(new com.cgn.util.DownloadImage.ImageCallback() {

			@Override
			public void getDrawable(Drawable drawable) {
				// TODO Auto-generated method stub
				mAppIconImageView.setImageDrawable(drawable);
			}
		});
		// 两种加载图片的方式
		// mAppIconImageView.setImageDrawable(drawable);
		// mAppIconImageView.setImageBitmap(bm);
		return view;
	}

}
