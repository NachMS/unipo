package models;

import java.util.Calendar;
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

	/**
	 * @return 受取日時の日付をint型で得る (ex. 1日:1)
	 */
	public int getReceiveDateInt() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(receiveDate);
		return cal.get(Calendar.DATE);
	}

	/**
	 * @return 受取日時の曜日をint型で得る (月曜日:1～日曜日:7)
	 */
	public int getReceiveDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(receiveDate);
		// Calendar.DAY_OF_WEEKは日曜日:1～月曜日:7
		int calDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return (calDayOfWeek == 1) ? 7 : calDayOfWeek - 1;
	}

	/**
	 * @return 受取日時の曜日の漢字一文字をString型で得る (月曜日:"月"～日曜日:"日")
	 */
	public String getReceiveDayOfWeekKanji() {
		String[] dowKanjis = { "月", "火", "水", "木", "金", "土", "日" };
		int i = getReceiveDayOfWeek() - 1;
		return dowKanjis[i];
	}

	/**
	 * @return 受取日時の時間（開始時間）をint型で得る (10~11:10～17~18:17)
	 */
	public int getReceiveHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(receiveDate);
		return cal.get(Calendar.HOUR_OF_DAY);
	}
}
