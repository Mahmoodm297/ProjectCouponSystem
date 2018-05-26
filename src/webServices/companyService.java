package webServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import cop.Company;
import couponClient.adminFacade;
import facade.CompanyFacade;
import model.Coupon;
import model.CouponType;


@Path("/companyService")
public class companyService {
	

	@GET 
	@Path("/getCoupon")
	@Produces("application/json")
	public String getComp(@QueryParam("coouponId")int id)
	{
		Coupon coupon = new Coupon();
		CompanyFacade comp = new CompanyFacade();
		coupon =comp.getCoupon(id);
		return ("{'title':'" + coupon.getTitle() +"','startDate':'"+coupon.getStartDate().toString()+
				"','endDate':'"+coupon.getEndDate().toString()+"','amount':'"+coupon.getAmount()+
				"','type':'"+coupon.getType().toString()+"','message':'"+coupon.getMessage()+
				"','price':'"+coupon.getPrice()+"','image':'"+coupon.getImage()+"'}");
	}
	
	
	@DELETE
	@Path("/{couponid}")
	public void deleteCouponById(@PathParam("couponid")int id){
		
		Coupon coupon = new Coupon();
		CompanyFacade comp = new CompanyFacade();
		coupon =comp.getCoupon(id);
		comp.removeCoupon(coupon);;
	}
	
	 @POST
	 @Path("/createcoupon")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void createCoupon(@QueryParam("id")int id,@QueryParam("title")String title,
			 @QueryParam("startDate")Date startDate, @QueryParam("endDate")Date endDate,
			 @QueryParam("amount")int amount, @QueryParam("type")CouponType type,
			 @QueryParam("message")String message, @QueryParam("price")double price,
			 @QueryParam("image")String image) {
		 /*System.out.println(id);
		 System.out.println("server side");*/
		 Coupon coupon = new Coupon();
		 CompanyFacade comp = new CompanyFacade();
		 coupon.setId(id);
		 coupon.setTitle(title);
		 coupon.setStartDate(startDate);
		 coupon.setEndDate(endDate);
		 coupon.setAmount(amount);
		 coupon.setType(type);
		 coupon.setMessage(message);
		 coupon.setPrice(price);
		 coupon.setImage(image);
		 comp.createCoupon(coupon);		 
	    }
	
	@PUT
	@Path("/updatecoupon")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updateCompanyById(@QueryParam("id")int id,@QueryParam("title")String title,
			 @QueryParam("startdate")Date startDate, @QueryParam("enddate")Date endDate,
			 @QueryParam("amount")int amount, @QueryParam("type")CouponType type,
			 @QueryParam("message")String message, @QueryParam("price")double price,
			 @QueryParam("image")String image) {
		
		Coupon coupon = new Coupon();
		CompanyFacade comp = new CompanyFacade();
		coupon = comp.getCoupon(id);
		coupon.setTitle(title);
		coupon.setStartDate(startDate);
		coupon.setEndDate(endDate);
		coupon.setAmount(amount);
		coupon.setType(type);
		coupon.setMessage(message);
		coupon.setPrice(price);
		coupon.setImage(image);
		comp.updateCoupon(coupon);
	}
	
	@GET 
	@Path("/getallcoupons")
	@Produces("application/json")
	public JSONObject getAllCoupons()
	{
		System.out.println("server side");
		JSONObject obj=new JSONObject();
		List<Coupon> couponList = new ArrayList<Coupon>();
		CompanyFacade comp = new CompanyFacade();
		couponList = (List<Coupon>) comp.getAllCoupon();
		obj=(JSONObject) couponList;
		return obj;
	}
	
	@GET 
	@Path("/getcouponbytype")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/json")
	public JSONObject getCouponByType(@QueryParam("type")CouponType type)
	{
		System.out.println("server side");
		JSONObject obj=new JSONObject();
		List<Coupon> couponList = new ArrayList<Coupon>();
		CompanyFacade comp = new CompanyFacade();
		couponList = (List<Coupon>) comp.getCouponByType(type);
		obj=(JSONObject) couponList;
		return obj;
	}
	
	@GET 
	@Path("/getcouponbyprice")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/json")
	public JSONObject getCouponByType(@QueryParam("price")double price)
	{
		System.out.println("server side");
		JSONObject obj=new JSONObject();
		List<Coupon> couponList = new ArrayList<Coupon>();
		CompanyFacade comp = new CompanyFacade();
		couponList = (List<Coupon>) comp.getCouponByPrice(price);
		obj=(JSONObject) couponList;
		return obj;
	}
	
	@GET 
	@Path("/getcouponbyenddate")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/json")
	public JSONObject getCouponByType(@QueryParam("enddate")Date endDate)
	{
		System.out.println("server side");
		JSONObject obj=new JSONObject();
		List<Coupon> couponList = new ArrayList<Coupon>();
		CompanyFacade comp = new CompanyFacade();
		couponList = (List<Coupon>) comp.getCouponByEndDate(endDate);
		obj=(JSONObject) couponList;
		return obj;
	}
}