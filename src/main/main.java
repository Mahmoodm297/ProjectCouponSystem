package main;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

//import javax.websocket.ClientEndpointConfig;

import dao.CompanyDaoImpl;
import dao.CouponDaoImpl;
import dao.CustomerDaoImpl;
import facade.AdminFacade;
import facade.ClientType;
import facade.CompanyFacade;
import facade.CouponSystem;
import facade.CustomerFacade;
import ifcs.CompanyDao;
import ifcs.CouponClientFacade;
import ifcs.CouponDao;
import ifcs.CustomerDao;
import model.Company;
import model.Coupon;
import model.CouponType;
import model.Customer;

public class main {

	public static void main(String[] args) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// TODO Auto-generated method stub

		//
		/*
		CouponDao dao = new CouponDaoImpl();
		Coupon coupon = new Coupon("Rosh Hashanah",new Date(2015, 04, 3),new Date(2015, 05, 11),20,CouponType.ELECTRICITY,"Rosh Hashanah",2000,"Images2015");
		dao.createCoupon(coupon);
		
		coupon = new Coupon("Aladha",new Date(2015, 04, 3),new Date(2015, 05, 11),20,CouponType.ELECTRICITY,"Aladha",2000,"Images2015");
		dao.createCoupon(coupon);
		coupon = new Coupon("Alfeter",new Date(2015, 04, 3),new Date(2015, 05, 11),20,CouponType.ELECTRICITY,"Alfeter",2000,"Images2015");
		dao.createCoupon(coupon);
		*/
		
		/*
		Company company = new Company("Nice", "Nice", "mahmoodm@cadence.com");
		CompanyDao compDao = new CompanyDaoImpl();
		
		compDao.createCompany(company);
		company = new Company("Intel", "Intel", "mahmoodm@cadence.com");
		compDao.createCompany(company);
		Collection<Company> companies = compDao.getAllCompanies();
		System.out.println(companies);
		company = companies.iterator().next();
		//company.setCompanyName("Mahmood test123");
		//compDao.updateCompany(company  );
		//company=compDao.getCompanyByName("Mahmood test123");
		//System.out.println(company);
		
		//company=compDao.getCompany(12540);
		//System.out.println(company);
		*/
		
		
		/*
		Customer customer = new Customer("Hashem", "Test1234");
		CustomerDao custDao = new CustomerDaoImpl();
		custDao.createCustomer(customer);
		customer = new Customer("Adham", "Test1234");
		custDao.createCustomer(customer);

		System.out.println( customer);
		Collection<Customer> customers = custDao.getAllCustomers();
		customer = customers.iterator().next();
		customer.setName("Mahmood Majadly");
		custDao.updateCustomer(customer);
		System.out.println(customers);
		*/
		
		Company company1 = new Company("Cadence", "Cadence", "mahmoodm@cadence.com");
		Company company2 = new Company("Amdocs", "Amdocs", "mahmoodm@cadence.com");
		Company company3 = new Company("Intel", "Intel", "mahmoodm@cadence.com");
		Company company4 = new Company("Apple", "Apple", "mahmoodm@cadence.com");
		
		Customer customer1 = new Customer("Mahmood", "Test1234");
		Customer customer2 = new Customer("Dawood", "Test1234");
		Customer customer3 = new Customer("Hashem", "Test1234");
		
		Coupon coupon1  = new Coupon("Rosh Hashanah",new Date(2018-1900, 04, 3),new Date(2018-1900, 05, 11),20,CouponType.ELECTRICITY,"Rosh Hashanah",2000,"Images2015");
		Coupon coupon2 = new Coupon("Aladha",new Date(2018-1900, 04, 3),new Date(2018-1900, 05, 11),20,CouponType.ELECTRICITY,"Aladha",2000,"Images2015");
		Coupon coupon3 = new Coupon("Alfeter",new Date(2018-1900, 04, 3),new Date(2018-1900, 05, 11),20,CouponType.ELECTRICITY,"Alfeter",2000,"Images2015");
		//System.out.println( new Date(2015, 04, 3) );
		//new Date ()
		
		CouponSystem system = CouponSystem.getInstance();
		CouponClientFacade admin = system.login("admin", "1234", ClientType.ADMIN);
	    
		if ( admin instanceof AdminFacade) {
			//System.out.println("Creating Company");
			//((AdminFacade)admin).createCompany(company1);
			//((AdminFacade)admin).createCustomer(customer1);
			//System.out.println(((AdminFacade)admin).getAllCustomers());
			//System.out.println(((AdminFacade)admin).getCustomer(12));
			//((AdminFacade)admin).removeCustomer(customer3);
			//((AdminFacade)admin).
			
		//if ( system.getInstance().getClass().isInstance(AdminFacade) ) {
			Company company =   ((AdminFacade)admin).getCompany(12542);
			String res = "{ 'id' : '" + company.getId() + "' , 'name' : '"
					+ company.getCompanyName() + "' , 'email' : '" + company.getEmail() + "'}";
			System.out.println(res);
			
		}
		
		
		
		//system = CouponSystem.getInstance();
		/*
		CouponClientFacade company = system.login("Cadence", "Cadence", ClientType.COMPANY);
		if ( company  instanceof CompanyFacade ) {
			((CompanyFacade)company).createCoupon(coupon1);
		}
		
		*/
		/*
		CouponClientFacade ccf = CompanyFacade.getInstance().login("Cadence", "Cadence",ClientType.COMPANY);
		if ( ccf instanceof CompanyFacade ) {
			System.out.println("Login Done");
			((CompanyFacade)ccf).createCoupon(coupon1);
			((CompanyFacade)ccf).createCoupon(coupon1);
			((CompanyFacade)ccf).createCoupon(coupon2);
			((CompanyFacade)ccf).createCoupon(coupon3);
			
			//((CompanyFacade)ccf).removeCoupon(coupon1);
			//((CompanyFacade)ccf).removeCoupon(coupon2);
			//((CompanyFacade)ccf).removeCoupon(coupon3);
		}
		else
		{
			System.exit(1);
		}
		*/
		
		/*
		CouponClientFacade ccf =  CustomerFacade.getInstance().login("Dawood", "Test1234", ClientType.CUSTOMER);
		if ( ccf instanceof CustomerFacade ) {
			System.out.println("Login Done");
			System.out.println( ((CustomerFacade)ccf).getAllPurchasedCoupons());
			//((CompanyFacade)ccf).removeCoupon(coupon1);;
		}
		else
		{
			System.exit(1);
		}
		*/
		
		
		system.shutdown();
	}

	
}
