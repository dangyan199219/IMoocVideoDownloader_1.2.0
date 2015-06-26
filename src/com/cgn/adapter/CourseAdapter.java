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

	private List<Course> list = null;// �������ݸ�ʽ

	public CourseAdapter(Context context) {
		// ��ȡLayoutInflaterʵ�������ڶ�̬���ز���
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
			// ע�������null����ʱ������null֮����������ʾ��Ҫ��Ϊthis���˴���������this������
			// ��̬���ز����ļ�item_content_listview.xml
			view = layoutInflater.inflate(
					com.cgn.imoocvediodownloader.R.layout.course_content, null);
		} else {
			view = convertView;
		}
		// ע��������view.findViewById(R.id.tittle_edit),��дview����ֿ�ָ���쳣
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
		// ��μ���ͼƬ
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
		// ���ּ���ͼƬ�ķ�ʽ
		// mAppIconImageView.setImageDrawable(drawable);
		// mAppIconImageView.setImageBitmap(bm);
		return view;
	}

}
