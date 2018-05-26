package ifcs;

import java.util.Collection;

import model.Company;
import model.Coupon;
import model.CouponType;
import model.Customer;

public interface CustomerDao {

	/**
	 * Create a new customer in the Company table in the Database
	 */
	public void createCustomer(Customer customer);
	/**
	 * Remove a Customer from Database, including all the company's coupons
	 */
	public void removeCustomer(Customer customer);
	/**
	 * Update a Customer in the Company table in the Database
	 */
	public void updateCustomer(Customer customer);
	/**
	 * Get a Customer from the Company table in the Database BY ID
	 */
	public Customer getCustomer(long id);
	
	
	/**
	 * Get a Customer from the Company table in the Database BY Name
	 */
	public Customer getCustomerByName(String name);
	
	/**
	 * Get all the Customers from the Company table in the Database 
	 */
	public Collection<Customer> getAllCustomers();
	/**
	 * Get ALL the given Customer's COUPONS from the Customer_coupon join table in the Database 
	 */
	public Collection<Coupon> getCoupons(Customer customer);
	
	public void purchaseCoupon(Coupon coupon, Customer customer);
	
	public Collection<Coupon> getCouponsByType(Customer customer, CouponType type);
	
	public Collection<Coupon> getCouponsByPrice(Customer customer, double price);
	
	/**
	 * Login: check the name and the password of the Customer in conjunction with the Customer table in the Database 
	 */
	public Customer login(String custName, String password);


}
