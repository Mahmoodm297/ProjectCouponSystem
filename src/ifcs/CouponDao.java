package ifcs;

import java.util.Collection;

import model.Coupon;


public interface CouponDao {
	
	/**
	 * Create a new Coupon in the Coupon table in the Database
	 */
	public void createCoupon(Coupon coupon);
	/**
	 * Remove a Coupon from Database
	 */
	public void removeCoupon(Coupon coupon);
	/**
	 * Update a Coupon in the Coupon table in the Database
	 */
	public void updateCoupon(Coupon coupon);
	/**
	 * Get a coupon from the Coupon table in the Database BY ID
	 */
	public Coupon getCoupon(long id);
	
	public Coupon getCouponByTitle(String title);
	
	/**
	 * Get all the Coupons from the Coupon table in the Database  
	 */
	public Collection<Coupon> getAllCoupons();
	/**
	 * Get all the Coupons of the given TYPE from the Coupon table in the Database
	 */
	//public Collection<Coupon> getCouponsByType(CouponType couponType);

}
