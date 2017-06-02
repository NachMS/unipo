package models;

public class Textbook {

	private int textbookID;
	private String name;
	private String reading;
	private String courseID;
	private int price;
	private int stock;


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
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
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


}
