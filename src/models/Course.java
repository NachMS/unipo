package models;

import java.util.Date;

public class Course {
	private int courseID;
	private String name;
	private String teacher;
	private String department;
	private int grade;
	private int semester;
	private int dayOfWeek;
	private int hour;
	private int likes;
	private int dislikes;
	private Date regDate;

	public Course(int courseID, String name, String teacher, String department, int grade, int semester, int dayOfWeek,
			int hour, int likes, int dislikes, Date regDate) {
		super();
		this.courseID = courseID;
		this.name = name;
		this.teacher = teacher;
		this.department = department;
		this.grade = grade;
		this.semester = semester;
		this.dayOfWeek = dayOfWeek;
		this.hour = hour;
		this.likes = likes;
		this.dislikes = dislikes;
		this.regDate = regDate;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDay(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

}
