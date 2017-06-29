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
	private Date regDate;

	public Course(int courseID, String name, String teacher, String department, int grade, int semester, int dayOfWeek,
			int hour, Date regDate) {
		super();
		this.courseID = courseID;
		this.name = name;
		this.teacher = teacher;
		this.department = department;
		this.grade = grade;
		this.semester = semester;
		this.dayOfWeek = dayOfWeek;
		this.hour = hour;
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

	public String toString() {
		return "Course [courseID=" + courseID + ", name=" + name + ", teacher=" + teacher + ", department=" + department
				+ ", grade=" + grade + ", semester=" + semester + ", dayOfWeek=" + dayOfWeek + ", hour=" + hour
				+ ", regDate=" + regDate + "]";
	}

	/**
	 * @return 科目の開講曜日の漢字一文字をString型で得る (月曜日:"月"～金曜日:"金")
	 */
	public String getDayOfWeekKanji() {
		String[] dowKanjis = { "月", "火", "水", "木", "金" };
		int i = getDayOfWeek() - 1;
		return dowKanjis[i];
	}

}
