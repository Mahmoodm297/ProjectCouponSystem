package facade;

import java.util.Collection;

import dao.CompanyDaoImpl;
import dao.CouponDaoImpl;
import dao.CustomerDaoImpl;
import ifcs.CouponClientFacade;
import model.Coupon;
import model.CouponType;
import model.Customer;

public class CustomerFacade implements CouponClientFacade{

	private CustomerDaoImpl customerDao;
	private Customer customer;
	//private ClientType type;
	static CustomerFacade   instance = null;
	private boolean login = false;
 	
	private CustomerFacade() {
		super();

	}

	public static  CustomerFacade getInstance () {
		if ( instance == null )
			instance = new CustomerFacade();
		
		return instance;
	}
	
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		this.customerDao = new CustomerDaoImpl();
		instance = null;
		customer = customerDao.login(name, password);//     getCustomerByName(name);
				//companyDao.getCompanyByName(name);
		if ( customer instanceof Customer  && clientType == ClientType.CUSTOMER ) {
			this.login=true;
			instance = this;
		}
		return instance;
	}
	
	public void purchaseCoupon(Coupon coupon) {
		Boolean flag = false;
		if(this.login) {
			Collection<Coupon> coupons = customerDao.getCoupons(customer);      //customer.getCoupones();
			for ( Coupon cop:coupons) {
				if ( cop.getTitle().equals(coupon.getTitle()))
					flag=true;
				
			}
			System.out.println(customer.getCoupones());
			if( flag == true ) {
				System.out.println("This customer already purchased this coupon!");
				return;
			}
			System.out.println("Purchess coupon");
			this.customerDao.purchaseCoupon(coupon, customer);
		}
	}
	
	public Collection<Coupon> getAllPurchasedCoupons() {
		if(this.login) {
			return this.customerDao.getCoupons(customer);
		}
		return null;
	}

	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type){
		if(this.login) {
			return this.customerDao.getCouponsByType(customer, type);
		}
		return null;
	}
	
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price){
		if(this.login) {
			return this.customerDao.getCouponsByPrice(customer, price);
		}
		return null;
	}
	
}
