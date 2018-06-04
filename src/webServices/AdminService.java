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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
	
	//public void createCompany(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("email")String email,@QueryParam("pass")String password) {
	 @PUT
	 @Path("/createcompany")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void createCompany(String requestr) {
		 	System.out.println("server side");
			Company comp = new Company();
			Gson gson = new Gson();
			JsonElement element = gson.fromJson(requestr, JsonElement.class);
			JsonObject jsonObject = element.getAsJsonObject();
			comp.setCompanyName (jsonObject.get("name").getAsString());
			comp.setEmail(jsonObject.get("email").getAsString());
			comp.setPassword(jsonObject.get("pass").getAsString());
			admin.createCompany(comp);
			System.out.println("Company was created");
			//System.out.println(id);
		 
			 		 
	    }
	
	@PUT
	@Path("/updatecompany")
	@Consumes(MediaType.TEXT_PLAIN)
	public void 	updateCompanyById ( String requestr) throws ParseException {
		Company comp = new Company();
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(requestr, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();
		int id=jsonObject.get("id").getAsInt();
		comp=admin.getCompany(id);
		comp.setCompanyName (jsonObject.get("name").getAsString());
		comp.setEmail(jsonObject.get("email").getAsString());
		//System.out.println("test"+jsonObject.get("email").getAsString());
		comp.setPassword(jsonObject.get("pass").getAsString());
		/*
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(requestr);
		int id =   Integer.parseInt((String) jsonObj.get("id"));
		comp=admin.getCompany(id);
		comp.setCompanyName (jsonObj.get("name").toString());
		comp.setEmail(jsonObj.get("email").toString());
		comp.setPassword(jsonObj.get("pass").toString());
		*/
		admin.updateCompany(comp);
		
	}
	
	@GET 
	@Path("/getAllCompanies")
	//@Produces("application/json")
	@Produces(MediaType.TEXT_PLAIN)
	
	public String getAllComp()
	{
		System.out.println("server side");
		Gson gson = new Gson();
		String retString = null;
		
		//JSONObject obj=new JSONObject();
		List<Company> companyList= new ArrayList<Company>();
		//adminFacade admin= new adminFacade();
		companyList=(List<Company>) admin.getAllCompanies();
		//obj=(JSONObject) companyList;
		retString = gson.toJson(companyList);
		System.out.println(retString);
		return retString;
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
	


	@DELETE
	@Path("/{customerid}")
	public void deleteCCustomerById(@PathParam("customerid")int id){
		
		Customer customer = new Customer();
		//adminFacade admin= new adminFacade();
		customer=admin.getCustomer(id);
		admin.removeCustomer(customer);
	}
	

	@PUT
	@Path("/createcustomer")
	@Consumes(MediaType.TEXT_PLAIN)
	// public void createCustomer(@QueryParam("id")int id,@QueryParam("name")String
	// name,@QueryParam("email")String email,@QueryParam("pass")String password) {
	public void createCustomer(String requestr) {
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(requestr, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();
		String name = jsonObject.get("name").getAsString();
		String pass = jsonObject.get("pass").getAsString();

		System.out.println("server side");
		Customer customer = new Customer();
		// adminFacade admin= new adminFacade();
		customer.setName(name);
		customer.setPassword(pass);
		admin.createCustomer(customer);
	}

	@PUT
	@Path("/updatecustomer")
	@Consumes(MediaType.TEXT_PLAIN)
	//public void updateCustomer(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("pass")String password){
		
	public void updateCustomer(String requestr) {
		Customer customer = new Customer();
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(requestr, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();
		int id = jsonObject.get("id").getAsInt();
		String name = jsonObject.get("name").getAsString();
		String pass = jsonObject.get("pass").getAsString();
		//adminFacade admin= new adminFacade();
		customer=admin.getCustomer(id);
		customer.setName(name);
		customer.setPassword(pass);
		admin.updateCustomer(customer);
	}
	

	@GET 
	@Path("/getAllCustomers")
	@Produces(MediaType.TEXT_PLAIN)
	
	public String getAllCustomers()
	{
		System.out.println("server side");
		Gson gson = new Gson();
		String retString = null;
		List<Customer> customerlist= new ArrayList<Customer>();
		//adminFacade admin= new adminFacade();
		customerlist=(List<Customer>) admin.getAllCustomers();
		//obj=(JSONObject) companyList;
		retString = gson.toJson(customerlist);
		System.out.println(retString);
		return retString;
	}

}
