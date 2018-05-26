package webServices;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import facade.AdminFacade;
import facade.ClientType;
import facade.CouponSystem;
import ifcs.CouponClientFacade;
import model.Company;

@Path("/adminMain/")
public class adminRS {
	
	//AdminFacade adminFacade = (AdminFacade) AdminFacade.getInstance().login("admin","1234",ClientType.ADMIN);
	AdminFacade adminFacade = null;
	CouponSystem system = CouponSystem.getInstance().getInstance();
	CouponClientFacade admin = system.login("admin", "1234", ClientType.ADMIN);
	
	@Path("/createComp")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createCompany(@FormParam("companyName") String companyName,
			@FormParam("password") String password, @FormParam("email") String email) {
		Company company = new Company(companyName, password, email);
		adminFacade.createCompany(company);
	}
	
	@Path("/removeComp")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void removeCompany(@FormParam("companyID") long id) {
		Company company = adminFacade.getCompany(id);
		adminFacade.removeCompany(company);
	}
	
	@Path("/updateComp")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateCompany(@FormParam("companyName") String companyName,
			@FormParam("password") String password, @FormParam("email") String email) {
		Company company = new Company(companyName, password, email);
		adminFacade.updateCompany(company);
	}
	
	@Path("/getComp/{companyID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public String getCompany( @PathParam("companyID")  long id) {
		Company company =  ((AdminFacade)admin).getCompany(id);
		String res = "{ 'id' : '" + company.getId() + "' , 'name' : '"
		+ company.getCompanyName() + "' , 'email' : '" + company.getEmail()
		+ "' , 'password' : '" + company.getPassword() + "'}";

		return res;
	}

}
