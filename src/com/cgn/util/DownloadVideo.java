package com.cgn.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

/**
 * ����ͨ��������Ƶ�����Ƽ����ص�ַ��ģ����Ƶ����
 * 
 * @author yan
 * 
 */
public class DownloadVideo {
	private String videoDownloadLink;
	private String videoName;
	private String videoSaveName;

	public DownloadVideo(String videoDownloadLink, String videoName,
			String videoSaveName) {
		super();
		this.videoDownloadLink = videoDownloadLink;
		this.videoName = videoName;
		this.videoSaveName = videoSaveName;
		Log.v("hahahah",
				(this.videoName + this.videoSaveName + this.videoDownloadLink));
	}

	public void download() {
		Log.v("File", "�����ļ�");
		File filePath = null;
		FileWriter fw = null;
		FileWriter fn = null;
		// ���SDCard״̬�Ƿ�����
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// ����
			Log.v("SD Card", "SD Card״̬����");
			try {
				filePath = Environment.getExternalStorageDirectory();
				fw = new FileWriter(filePath + "/" + videoSaveName
						+ "-videoName.txt", true);
				// fw.write((CommonTool.handleVideoName(videoName) + "\r\n"
				// + videoDownloadLink + "\r\n"));
				fw.write((CommonTool.handleVideoName(videoName) + "\r\n"));
				fw.flush();

				fn = new FileWriter(filePath + "/" + videoSaveName
						+ "-BFDW.txt", true);
				fn.write(videoDownloadLink + "\r\n");
				fn.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fw.close();
					fn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			// ����������ʾ�û�
			Log.v("SD Card", "SD Card״̬������");
		}
	}

	public String getVideoPath() {
		return videoDownloadLink;
	}

	public void setVideoPath(String videoDownloadLink) {
		this.videoDownloadLink = videoDownloadLink;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
}
