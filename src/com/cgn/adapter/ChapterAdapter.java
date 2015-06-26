package com.cgn.adapter;

import java.util.List;

import com.cgn.imoocvediodownloader.R;
import com.cgn.model.Chapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class ChapterAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater = null;

	private List<Chapter> list = null;// 定义数据格式

	public ChapterAdapter(Context context) {
		// 获取LayoutInflater实例，用于动态加载布局
		layoutInflater = LayoutInflater.from(context);
	}

	public void setData(List<Chapter> list) {
		// TODO Auto-generated method stub
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
			view = layoutInflater
					.inflate(
							com.cgn.imoocvediodownloader.R.layout.chapter_content,
							null);
		} else {
			view = convertView;
		}
		TextView tvChapterName = (TextView) view
				.findViewById(R.id.chapter_name);
		TextView tvSectionName = (TextView) view
				.findViewById(R.id.section_name);
		// Button btDownload = (Button) view.findViewById(R.id.bt_download);
		tvChapterName.setText(list.get(position).getChapterName());
		tvSectionName.setText(list.get(position).getSectionName());

		return view;
	}

}
