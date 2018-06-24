package webServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import facade.ClientType;
import facade.CompanyFacade;
import facade.CustomerFacade;
import ifcs.CouponDao;
import model.Company;
import model.Coupon;
import model.CouponType;

@Path("/customerService")
public class CustomerService {
	CustomerFacade customer = (CustomerFacade) CustomerFacade.getInstance().login("test", "test", ClientType.CUSTOMER);
	CouponDao couponD;
	
	 @POST
	 @Path("/buycoupon")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void createCoupon(@QueryParam("coupon")String title) {
		 System.out.println(title);
		 System.out.println("server side");
		 Coupon coupon = new Coupon();
		 coupon = couponD.getCouponByTitle(title);
		 
		 if(coupon == null) {
			 System.out.println("There is no coupon with this name!");
		 }
		 else {
			 customer.purchaseCoupon(coupon);
		 }
	}
	
	@GET 
	@Path("/getallcoupons")
	@Produces(MediaType.TEXT_PLAIN)
		
	public String getAllCoupon() {
		System.out.println("server side");
		Gson gson = new Gson();
		String retString = null;
			
		List<Coupon> couponList= new ArrayList<Coupon>();
		couponList=(List<Coupon>) customer.getAllPurchasedCoupons();
		retString = gson.toJson(couponList);
		System.out.println(retString);
		return retString;
	}
	
	@GET 
	@Path("/getcouponbytype")
	@Produces(MediaType.TEXT_PLAIN)
	
	public String getCouponByType(@QueryParam("type")CouponType type) {
		System.out.println("server side");
		Gson gson = new Gson();
		String retString = null;
		
		List<Coupon> couponList= new ArrayList<Coupon>();
		couponList=(List<Coupon>) customer.getAllPurchasedCouponsByType(type);
		retString = gson.toJson(couponList);
		System.out.println(retString);
		return retString;
	}
	
	@GET 
	@Path("/getcouponbyprice")
	@Produces(MediaType.TEXT_PLAIN)
	
	public String getCouponByPrice(@QueryParam("price")double price) {
		System.out.println("server side");
		Gson gson = new Gson();
		String retString = null;
		
		List<Coupon> couponList= new ArrayList<Coupon>();
		couponList=(List<Coupon>) customer.getAllPurchasedCouponsByPrice(price);
		retString = gson.toJson(couponList);
		System.out.println(retString);
		return retString;
	}
}
