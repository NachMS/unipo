package models;

import java.io.Serializable;

public class Student implements Serializable {
	private String name;
	private String pass;

	public Student() {
		name = "";
		pass = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
