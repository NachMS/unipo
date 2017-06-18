package models;

import java.util.Date;
import java.util.List;

public class Order {
	private int orderID;
	private String studentID;
	private Date orderDate;
	private Date receiveDate;
	private int totalAmount;
	private boolean completeFlag;
	private boolean cancelFlag;
	private List<Textbook> textbooks;

	public Order(int orderID, String studentID, Date orderDate, Date receiveDate, int totalAmount, boolean completeFlag,
			boolean cancelFlag, List<Textbook> textbooks) {
		super();
		this.orderID = orderID;
		this.studentID = studentID;
		this.orderDate = orderDate;
		this.receiveDate = receiveDate;
		this.totalAmount = totalAmount;
		this.completeFlag = completeFlag;
		this.cancelFlag = cancelFlag;
		this.textbooks = textbooks;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", studentID=" + studentID + ", orderDate=" + orderDate + ", receiveDate="
				+ receiveDate + ", totalAmount=" + totalAmount + ", completeFlag=" + completeFlag + ", cancelFlag="
				+ cancelFlag + ", textbooks=" + textbooks + "]";
	}

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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean isCompleteFlag() {
		return completeFlag;
	}

	public void setCompleteFlag(boolean completeFlag) {
		this.completeFlag = completeFlag;
	}

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public List<Textbook> getTextbooks() {
		return textbooks;
	}

	public void setTextbooks(List<Textbook> textbooks) {
		this.textbooks = textbooks;
	}
}
