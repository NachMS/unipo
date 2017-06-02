package models;

import java.io.Serializable;

public class Student implements Serializable {
	private String studentID;
	private String name;
	private String faculty;
	private String department;
	private int grade;
	private String pass;
	//private ArrayList<Course> courses;

	public Student() {
		name = "";
		pass = "";
	}


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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
