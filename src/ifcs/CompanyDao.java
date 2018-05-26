package ifcs;

import java.util.Collection;
import java.util.Date;

import model.Company;
import model.Coupon;
import model.CouponType;

public interface CompanyDao {

	/**
	 * Create a new Company in the Company table in the Database
	 */
	public void createCompany(Company company);
	/**
	 * Remove a Company from Database, including all the company's coupons
	 */
	public void removeCompany(Company company);
	/**
	 * Update a Company in the Company table in the Database
	 */
	public void updateCompany(Company company);
	/**
	 * Get a Company from the Company table in the Database BY ID
	 */
	public Company getCompany(long id);
	
	public Company getCompanyByName(String name);
	
	/**
	 * Get all the Companies from the Company table in the Database 
	 */
	public Collection<Company> getAllCompanies();
	/**
	 * Get ALL the given Company's COUPONS from the company_coupon join table in the Database 
	 */
	public Collection<Coupon> getCoupons(Company company);
	
	public void createCoupon(Coupon coupon, Company company);
	
	public void removeCoupon(Coupon coupon, Company company);
	
	public void updateCoupon(Coupon coupon, Company company);
	
	public Collection<Coupon> getCouponsBytype(Company company, CouponType type);
	
	public Collection<Coupon> getCouponsByPrice(Company company, double price);
	
	public Collection<Coupon> getCouponsByDate(Company company, Date endDate);
	
	/**
	 * Login: check the name and the password of the company in conjunction with the company table in the Database 
	 */
	public Company login(String compName, String password);


}
