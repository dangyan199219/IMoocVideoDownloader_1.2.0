package com.cgn.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

/**
 * 本类通过保存视频的名称及下载地址来模拟视频下载
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
		Log.v("File", "下载文件");
		File filePath = null;
		FileWriter fw = null;
		FileWriter fn = null;
		// 检测SDCard状态是否正常
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// 正常
			Log.v("SD Card", "SD Card状态正常");
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
			// 不正常，提示用户
			Log.v("SD Card", "SD Card状态不正常");
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
