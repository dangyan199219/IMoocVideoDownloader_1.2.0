package com.cgn.model;

public class Course {
	private String courseName;
	private String courseTime;
	private String courseLink;
	private String courseImagePath;
	private String courseStatus;
	private String courseSummary;

	public Course() {
		super();
	}

	public Course(String courseName, String courseLink) {
		super();
		this.courseName = courseName;
		this.courseLink = courseLink;
	}

	public Course(String courseName, String courseTime, String courseLink,
			String courseImagePath, String courseStatus) {
		super();
		this.courseName = courseName;
		this.courseTime = courseTime;
		this.courseLink = courseLink;
		this.courseImagePath = courseImagePath;
		this.courseStatus = courseStatus;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getCourseLink() {
		return courseLink;
	}

	public void setCourseLink(String courseLink) {
		this.courseLink = courseLink;
	}

	public String getCourseImagePath() {
		return courseImagePath;
	}

	public void setCourseImagePath(String courseImagePath) {
		this.courseImagePath = courseImagePath;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getCourseSummary() {
		return courseSummary;
	}

	public void setCourseSummary(String courseSummary) {
		this.courseSummary = courseSummary;
	}

}
