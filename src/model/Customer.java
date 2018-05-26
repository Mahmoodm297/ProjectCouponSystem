package model;

import java.util.List;

public class Customer {
	private long id;
	private String name;
	private String password;
	private List<Coupon> coupones;
	
	public Customer(String name, String password) {
		super();
		//this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public Customer() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coupon> getCoupones() {
		return coupones;
	}

	public void setCoupones(List<Coupon> coupones) {
		this.coupones = coupones;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + ", coupones=" + coupones + "]";
	}
	
	
	

}

