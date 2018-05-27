package webServices;

import java.util.ArrayList;
import java.util.Collection;
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

//import org.json.JSONException;
import org.json.simple.JSONObject;
//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import facade.AdminFacade;
import facade.ClientType;
import model.Company;
import model.Customer;


@Path("/adminServices")
public class AdminService {
	AdminFacade admin = (AdminFacade) AdminFacade.getInstance().login("admin", "1234", ClientType.ADMIN);
	
	@GET 
	@Path("/getcompany")
	@Produces("application/json")
	public String getComp(@QueryParam("compId")int id)
	{
		Company comp = new Company();
		comp=admin.getCompany(id);
		return ("{'name':'"+comp.getCompanyName()+"','compemail':'"+comp.getEmail()+"'}");
	}
	
	
	@DELETE
	@Path("/dellCompany")
	//public void deleteCompanyById(@QueryParam("compid")int id){
	public void deleteCompanyById(@QueryParam("compId")int id)	{
		Company comp = new Company();
		//adminFacade admin= new adminFacade();
		comp=admin.getCompany(id);
		admin.removeCompany(comp);
	}
	
	 @POST
	 @Path("/createcompany")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void createCompany(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("email")String email,@QueryParam("pass")String password) {
		 System.out.println(id);
		 System.out.println("server side");
		 Company comp = new Company();
		 //adminFacade admin= new adminFacade();
		 comp.setCompanyName(name);
		 comp.setId(id);
		 comp.setEmail(email);
		 comp.setPassword(password);
		 admin.createCompany(comp);		 
	    }
	
	@PUT
	@Path("/updatecompany")
	@Consumes(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.APPLICATION_JSON)
	//public void updateCompanyById(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("email")String email,@QueryParam("pass")String password){
	public void 	updateCompanyById ( String requestr) throws ParseException {
		Company comp = new Company();
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(requestr);
		int id =   Integer.parseInt((String) jsonObj.get("id"));
		comp=admin.getCompany(id);
		comp.setCompanyName (jsonObj.get("name").toString());
		comp.setEmail(jsonObj.get("email").toString());
		comp.setPassword(jsonObj.get("pass").toString());
		admin.updateCompany(comp);
		
	}
	
	@GET 
	@Path("/getAllCompanies")
	@Produces("application/json")
	public JSONObject getAllComp()
	{
		System.out.println("server side");
		JSONObject obj=new JSONObject();
		List<Company> companyList= new ArrayList<Company>();
		//adminFacade admin= new adminFacade();
		companyList=(List<Company>) admin.getAllCompanies();
		obj=(JSONObject) companyList;
		return obj;
	}
	

//	@GET 
//	@Path("/getcustomer")
//	@Produces("application/json")
//	public String getCustomer(@QueryParam("customerId")int id)
//	{
//		Customer customer = new Customer();
//		adminFacade admin= new adminFacade();
//		customer=admin.getCustomer(id);
//		return ("{'name':'"+customer.getCompName()+"','password':'"+customer.getPassword()+"'}");
//	}
	

/*
	@DELETE
	@Path("/{customerid}")
	public void deleteCompanyById(@PathParam("customerid")int id){
		
		Customer customer = new Customer();
		//adminFacade admin= new adminFacade();
		customer=admin.getCustomer(id);
		admin.removeCustomer(customer);
	}
	
	 @POST
	 @Path("/createcustomer")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void createCompany(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("email")String email,@QueryParam("pass")String password) {
		 System.out.println(id);
		 System.out.println("server side");
		 Customer customer = new Customer();
		 adminFacade admin= new adminFacade();
		 customer.setCompName(name);
		 customer.setId(id);
		 customer.setEmail(email);
		 customer.setPassword(password);
		 admin.createCustomer(customer);;		 
	    }
*/	
	@PUT
	@Path("/updatecustomer")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updateCompanyById(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("pass")String password){
		
		Customer customer = new Customer();
		//adminFacade admin= new adminFacade();
		customer=admin.getCustomer(id);
		customer.setName(name);
		customer.setPassword(password);
		admin.updateCustomer(customer);
	}
/*	
	@GET 
	@Path("/getAllCustomers")
	@Produces("application/json")
	public JSONObject getAllComp()
	{
		System.out.println("server side");
		JSONObject obj=new JSONObject();
		List<Customer> customerList= new ArrayList<Customer>();
		//adminFacade admin= new adminFacade();
		customerList=(List<Customer>)admin.getAllCustomer();
		obj=(JSONObject) customerList;
		return obj;
	}
*/	
}