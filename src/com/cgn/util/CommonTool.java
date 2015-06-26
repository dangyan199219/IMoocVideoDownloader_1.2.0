package com.cgn.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.cgn.model.Chapter;
import com.cgn.model.Section;
import com.cgn.model.Course;

public class CommonTool {

	/**
	 * 解析JSON数据获取视频名称和下载地址
	 * 
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public static Section sectionReader(String result) throws Exception {

		JSONObject object = new JSONObject(result).getJSONObject("data");
		JSONObject resultData = object.getJSONObject("result");
		JSONArray videoArray = resultData.getJSONArray("mpath");
		String chapterDownloadLink = videoArray.getString(0);
		String chapterName = resultData.getString("name");
		Log.v("Json", chapterDownloadLink);
		Log.v("Json", chapterName);
		return new Section(chapterName, chapterDownloadLink);

	}

	public static List<Chapter> chapterReader(String resultString) {
		// TODO Auto-generated method stub
		List<Chapter> chapterList = new ArrayList<Chapter>();
		Document doc = Jsoup.parse(resultString);
		Elements eChapterLinks = doc.select(".learnchapter").select(
				".learnchapter-active");
		Log.v("eChapterLinks", eChapterLinks.size() + "");
		for (Element e : eChapterLinks) {
			Chapter chapter = new Chapter();
			String chapterName = e.select("h3").get(0).text();
			chapter.setChapterName(chapterName);
			Log.v("chapterName", chapterName);

			Elements eSectionName = e.select(".studyvideo");
			if (eSectionName.size() > 1) {
				for (int i = 0; i < eSectionName.size(); i++) {
					Chapter tmpChapter = new Chapter();
					tmpChapter.setChapterName(chapterName);
					tmpChapter.setSectionName(eSectionName.get(i).text());
					tmpChapter.setSectionLink(eSectionName.get(i)
							.select("a[href]").get(0).attr("href"));
					Log.v("sectionName", eSectionName.get(i).text());
					chapterList.add(tmpChapter);
				}

			} else {
				chapter.setSectionName(eSectionName.get(0).text());
				String sectionLink = e.select("a[href]").get(0).attr("href");
				chapter.setSectionLink(sectionLink);
				Log.v("sectionLink", sectionLink);
				chapterList.add(chapter);
			}

		}
		return chapterList;
	}

	/**
	 * 解析HTML数据获取course
	 * 
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public static List<Course> courseReader(String result) throws Exception {
		List<Course> courseList = new ArrayList<Course>();
		Document doc = Jsoup.parse(result);
		Elements courses = doc.select(".course-one");
		Log.v("courseList", courses.size() + "");
		for (Element e : courses) {
			Course course = new Course();
			String courseName = e.select("h5").first().text();
			String courseSummary = e.select(".text-ellipsis").get(0).text();
			String courseStatus = e.select(".l").get(0).text() + " <-->"
					+ e.select(".l").get(1).text();
			String courseImagePath = e.select("img[src]").get(0).attr("src");

			String courseLink = CommonURL.IMOOC_URL
					+ e.select("a[href]").get(0).attr("href")
							.replaceFirst("view", "learn");

			course.setCourseName(courseName);

			System.out.print(courseName);

			course.setCourseSummary(courseSummary);

			course.setCourseStatus(courseStatus);

			course.setCourseImagePath(courseImagePath);

			course.setCourseLink(courseLink);

			System.out.print(courseLink);

			System.out.print(course);

			courseList.add(course);
		}
		return courseList;
	}

	/**
	 * 将视频的播放地址转换成视频的Json数据地址
	 * 
	 * @param sectionLink
	 * @return
	 */
	public static String getJsonURLFromSectionLink(String sectionLink) {
		String jsonURL = sectionLink.replaceAll("/video/", "");
		jsonURL = CommonURL.JSON_URL.replaceAll("0000", jsonURL);
		return jsonURL;
	}

	public static String handleVideoName(String videoName) {
		String resultVideoName = null;
		resultVideoName = videoName.split("\\s")[0] + " "
				+ videoName.split("\\s")[1] + ".mp4";
		Log.v("handleVideoName", resultVideoName);
		return resultVideoName;

	}

}
