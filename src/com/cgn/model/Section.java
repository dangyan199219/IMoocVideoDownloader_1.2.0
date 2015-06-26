package com.cgn.model;

public class Section {
	private String sectionName;
	private String sectionDownloadLink;

	public Section() {
		super();
	}

	public Section(String sectionName, String sectionDownloadLink) {
		super();
		this.sectionName = sectionName;
		this.sectionDownloadLink = sectionDownloadLink;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionDownloadLink() {
		return sectionDownloadLink;
	}

	public void setSectionDownloadLink(String sectionDownloadLink) {
		this.sectionDownloadLink = sectionDownloadLink;
	}

}
