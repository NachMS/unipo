package models;

public class Order {
private int orderID;
private String studentID;
private int orderDatetime;
private int receiveDatetime;
private int totalAmount;
//private ArrayList<Textbook> Textbook;

public int getOrderID() {
	return orderID;
}
public void setOrderID(int orderID) {
	this.orderID = orderID;
}
public String getStudentID() {
	return studentID;
}
public void setStudentID(String studentID) {
	this.studentID = studentID;
}


public int getOrderDatetime() {
	return orderDatetime;
}
public void setOrderDatetime(int orderDatetime) {
	this.orderDatetime = orderDatetime;
}
public int getReceiveDatetime() {
	return receiveDatetime;
}
public void setReceiveDatetime(int receiveDatetime) {
	this.receiveDatetime = receiveDatetime;
}
public int getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(int totalAmount) {
	this.totalAmount = totalAmount;
}


}
