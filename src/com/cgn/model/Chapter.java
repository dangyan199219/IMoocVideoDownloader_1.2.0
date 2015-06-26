package com.cgn.model;

public class Chapter {
	private String chapterName;
	private String sectionName;
	private String sectionLink;

	public Chapter(String chapterName, String sectionName,
			String sectionDownloadLink) {
		super();
		this.chapterName = chapterName;
		this.sectionName = sectionName;
		this.sectionLink = sectionDownloadLink;
	}

	public Chapter() {
		// TODO Auto-generated constructor stub
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionLink() {
		return sectionLink;
	}

	public void setSectionLink(String sectionLink) {
		this.sectionLink = sectionLink;
	}

}
