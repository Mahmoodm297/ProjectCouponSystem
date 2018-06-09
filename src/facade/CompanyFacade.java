package facade;

import java.util.Collection;
import java.util.Date;
import dao.CompanyDaoImpl;
import dao.CouponDaoImpl;
import ifcs.CompanyDao;
import ifcs.CouponClientFacade;
import ifcs.CouponDao;
import model.Company;
import model.Coupon;
import model.CouponType;

public class CompanyFacade implements CouponClientFacade{
	static CompanyFacade   instance = null;
	private CompanyDao companyDao = null;
	private CouponDao couponDao = null ;
	private boolean login = false;
	private Company company = null;
	
 	
	private CompanyFacade() {
		super();

	}

	public static  CompanyFacade getInstance () {
		if ( instance == null )
			instance = new CompanyFacade();
		
		return instance;
	}
	
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		this.companyDao = new CompanyDaoImpl();
		company = companyDao.login(name, password);
		instance = null;
		if ( company instanceof Company && clientType == ClientType.COMPANY ) {
			//this.couponDao = new CouponDaoImpl();
			this.login=true;
			instance =  this;
		}
		return instance ;
	}
	
	public void createCoupon(Coupon coupon) {
		if(this.login) {
			if (this.getAllCoupon().contains(coupon)) {
				System.out.println("This coupon already exists!");
				return;
			}
			
			this.companyDao.createCoupon(coupon, company);
		}
	}
	
	public void removeCoupon(Coupon coupon) {
		if(this.login)
			this.companyDao.removeCoupon(coupon, company);
	}
	
	public void updateCoupon(Coupon coupon) {
		if(this.login)
			this.companyDao.updateCoupon(coupon, company);
	}
	
	public Coupon getCoupon(long id) {
		if(this.login)
			return this.couponDao.getCoupon(id);
		return null;
	}
	
	public Collection<Coupon> getAllCoupon(){
		if(this.login)
			return this.companyDao.getCoupons(company);
		return null;
	}
	
	public Collection<Coupon> getCouponByType(CouponType type){
		if(this.login)
			return this.companyDao.getCouponsBytype(company, type);
		return null;
	}
	
	public Collection<Coupon> getCouponByPrice(double price){
		if(this.login)
			return this.companyDao.getCouponsByPrice(company, price);
		return null;
	}
	
	public Collection<Coupon> getCouponByDate(Date endDate){
		if(this.login)
			return this.companyDao.getCouponsByDate(company, endDate);
		return null;
	}
}
