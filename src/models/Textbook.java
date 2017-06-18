package models;

import java.util.Date;

public class Textbook {

	private int textbookID;
	private String name;
	private String reading;
	private int courseID;
	private int price;
	private int stock;
	private int likes;
	private int dislikes;
	private Date regDate;

	public Textbook(int textbookID, String name, String reading, int courseID, int price, int stock, int likes,
			int dislikes, Date regDate) {
		super();
		this.textbookID = textbookID;
		this.name = name;
		this.reading = reading;
		this.courseID = courseID;
		this.price = price;
		this.stock = stock;
		this.likes = likes;
		this.dislikes = dislikes;
		this.regDate = regDate;
	}

	public String toString() {
		return "Textbook [textbookID=" + textbookID + ", name=" + name + ", reading=" + reading + ", courseID="
				+ courseID + ", price=" + price + ", stock=" + stock + ", likes=" + likes + ", dislikes=" + dislikes
				+ ", regDate=" + regDate + "]";
	}

	public int getTextbookID() {
		return textbookID;
	}

	public void setTextbookID(int textbookID) {
		this.textbookID = textbookID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
