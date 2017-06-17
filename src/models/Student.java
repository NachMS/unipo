package models;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
	private String studentID;
	private String name;
	private String faculty;
	private String department;
	private int grade;
	private String password;
	private List<Course> courses;

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
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

	public String getPassword() {
		return password;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", name=" + name + ", faculty=" + faculty + ", department="
				+ department + ", grade=" + grade + ", password=" + password + ", courses=" + courses + "]";
	}

}
