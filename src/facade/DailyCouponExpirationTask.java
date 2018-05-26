package facade;

import java.util.Date;
import java.util.List;

import dao.CouponDaoImpl;
import model.Coupon;

public class DailyCouponExpirationTask implements Runnable{
	private boolean quit;
	private CouponDaoImpl couponDao;
	private List<Coupon>  coupons;
	@Override
	public void run() {
		coupons = couponDao.getAllCoupons();
		// TODO Auto-generated method stub
		while ( ! this.quit ) {
		
				for ( Coupon coupon:coupons) {
					Date nowDate = new Date(System.currentTimeMillis()) ; 
					if ( (coupon.getEndDate().getTime() - nowDate.getTime()) <= 0 ) {
						couponDao.removeCoupon(coupon);
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//Need Sleep here for reducing the loop overload 
		}
	}
	
	public DailyCouponExpirationTask() {
		super();
		quit = false;
		couponDao = new CouponDaoImpl();
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}
	
	

}
