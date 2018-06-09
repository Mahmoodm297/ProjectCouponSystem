package webServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import model.Company;
import model.Coupon;
import model.CouponType;

@Path("/companyService")
public class CompanyService {
	CompanyFacade company = (CompanyFacade) CompanyFacade.getInstance().login("test", "test", ClientType.COMPANY);
	
	@GET 
	@Path("/getcoupon")
	@Produces("application/json")
	public String getCoupon(@QueryParam("couponId")int id)
	{
		System.out.println(id);
		//Coupon coupon  = new Coupon("Rosh Hashanah",new Date(2018-1900, 04, 3),new Date(2018-1900, 05, 11),20,CouponType.ELECTRICITY,"Rosh Hashanah",2000,"Images2015");
		//coupon.setId(1);
		
		Coupon coupon = new Coupon();
		coupon = company.getCoupon(id);
		
		return ("{'coupontitle':'"+coupon.getTitle()+"','startdate':'"+coupon.getStartDate()+
				"','enddate':'"+coupon.getEndDate()+"','amount':'"+coupon.getAmount()+
				"','type':'"+coupon.getType()+"','message':'"+coupon.getMessage()+
				"','price':'"+coupon.getPrice()+"','image':'"+coupon.getImage()+"'}");
	}
	
	 @POST
	 @Path("/createcoupon")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void createCoupon(@QueryParam("coupon")int id,@QueryParam("coupontitle")String coupontitle,
			 @QueryParam("startdate")String startdate,@QueryParam("enddate")String enddate,
			 @QueryParam("amount")int amount,@QueryParam("type")CouponType type,
			 @QueryParam("message")String message,@QueryParam("price")double price,@QueryParam("image")String image) {
		 System.out.println(id);
		 System.out.println("server side");
		 Coupon coupon = new Coupon();
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		 Date sDate = null;
		 Date eDate = null;
		try {
			sDate = format.parse(startdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			eDate = format.parse(enddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 coupon.setTitle(coupontitle);
		 coupon.setStartDate(sDate);
		 coupon.setEndDate(eDate);
		 coupon.setAmount(amount);
		 coupon.setType(type);
		 coupon.setMessage(message);
		 coupon.setPrice(price);
		 coupon.setImage(image);
	    }
	 
	 @PUT
	 @Path("/updatecoupon")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void updateCoupon(@QueryParam("coupon")int id,@QueryParam("coupontitle")String coupontitle,
			 @QueryParam("startdate")String startdate,@QueryParam("enddate")String enddate,
			 @QueryParam("amount")int amount,@QueryParam("type")CouponType type,
			 @QueryParam("message")String message,@QueryParam("price")double price,@QueryParam("image")String image) {
		 System.out.println(id);
		 System.out.println("server side");
		 Coupon coupon = new Coupon();
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		 Date sDate = null;
		 Date eDate = null;
		try {
			sDate = format.parse(startdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			eDate = format.parse(enddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 coupon.setTitle(coupontitle);
		 coupon.setStartDate(sDate);
		 coupon.setEndDate(eDate);
		 coupon.setAmount(amount);
		 coupon.setType(type);
		 coupon.setMessage(message);
		 coupon.setPrice(price);
		 coupon.setImage(image);
		 
		 company.updateCoupon(coupon);
	    }
	 
	 
	@DELETE
	@Path("/dellCoupon")
	public void deleteCompanyById(@QueryParam("couponId")int id)	{
		System.out.println("remove " + id);
			Coupon coupon = new Coupon();
			coupon=company.getCoupon(id);
			company.removeCoupon(coupon);
		}
	
	@GET 
	@Path("/getallcoupons")
	@Produces(MediaType.TEXT_PLAIN)
	
	public String getAllCoupon() {
		System.out.println("server side");
		Gson gson = new Gson();
		String retString = null;
		
		List<Coupon> couponList= new ArrayList<Coupon>();
		//adminFacade admin= new adminFacade();
		couponList=(List<Coupon>) company.getAllCoupon();
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
		//adminFacade admin= new adminFacade();
		couponList=(List<Coupon>) company.getCouponByType(type);
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
		//adminFacade admin= new adminFacade();
		couponList=(List<Coupon>) company.getCouponByPrice(price);
		retString = gson.toJson(couponList);
		System.out.println(retString);
		return retString;
	}
	
	@GET 
	@Path("/getcouponbyenddate")
	@Produces(MediaType.TEXT_PLAIN)
	
	public String getCouponByDate(@QueryParam("enddate")String enddate) {
		System.out.println("server side");
		Gson gson = new Gson();
		String retString = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date eDate = null;
		try {
			eDate = format.parse(enddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Coupon> couponList= new ArrayList<Coupon>();
		//adminFacade admin= new adminFacade();
		couponList=(List<Coupon>) company.getCouponByDate(eDate);
		retString = gson.toJson(couponList);
		System.out.println(retString);
		return retString;
	}
}
