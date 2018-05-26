package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Coupon  implements Serializable, Comparable<Coupon> {


	private static SimpleDateFormat dateFromat = new SimpleDateFormat("yyyy-MM-dd");

	private long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;

	/**
	 * An empty constructor
	 */
	public Coupon() {
	}

	/**
	 * A constructor using all the fields
	 */
	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType type, String message,
			double price, String image)	{
		this.title = title;
		this.startDate =   startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}
	// get the Coupon ID
	public long getId() {
		return id;
	}
	// get the Coupon Title
	public String getTitle() {
		return title;
	}
	// get the Coupon Start Date
	public Date getStartDate() {
		return startDate;
	}
	// get the Coupon End Date
	public Date getEndDate() {
		return endDate;
	}
	// get the Coupon Amount
	public int getAmount() {
		return amount;
	}
	// get the Coupon Type
	public CouponType getType() {
		return type;
	}
	// get the Coupon Message
	public String getMessage() {
		return message;
	}
	// get the Coupon Price
	public double getPrice() {
		return price;
	}
	// get the Coupon image
	public String getImage() {
		return image;
	}


	// set the Coupon ID
	public void setId(long id) {
		this.id = id;
	}
	// set the Coupon Title
	public void setTitle(String title) {
		this.title = title;
	}
	// set the Coupon Start Date
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	// set the Coupon End Date
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	// set the Coupon Amount
	public void setAmount(int amount) {
		this.amount = amount;
	}
	// set the Coupon Type
	public void setType(CouponType type) {
		this.type = type;
	}
	// set the Coupon Message
	public void setMessage(String message) {
		this.message = message;
	}
	// get the Coupon Price
	public void setPrice(double price) {
		this.price = price;
	}
	// set the Coupon image
	public void setImage(String image) {
		this.image = image;
	}
	
	
	// toSring
		@Override
		public String toString() {
			return "Coupon [id=" + id + ", title=" + title + ", startDate=" + dateFromat.format(startDate) + ", endDate=" + dateFromat.format(endDate)
					+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
					+ image + "]";
		}

		@Override
		public int compareTo(Coupon o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
}
