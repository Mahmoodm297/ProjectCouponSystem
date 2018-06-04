package facade;

import java.util.Collection;

import dao.CompanyDaoImpl;
import dao.CustomerDaoImpl;
import ifcs.CouponClientFacade;
import model.Company;
import model.Customer;

public class AdminFacade implements CouponClientFacade{
	private CompanyDaoImpl companyDao;
	private CustomerDaoImpl customerDao;
	//private ClientType type;
	static AdminFacade   instance = null;
	private boolean login = false;
 	
	private AdminFacade() {
		super();

	}

	public static  AdminFacade getInstance () {
		if ( instance == null )
			instance = new AdminFacade();
		
		return instance;
	}
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		
		//instance = null;
		if(name.equals("admin") && password.equals("1234") && clientType == ClientType.ADMIN && this.login == false) {
			this.companyDao = new CompanyDaoImpl();
			this.customerDao = new CustomerDaoImpl();
			this.login = true;
			instance =  this;
		}
		
		return instance ;
	}
	
	public void createCompany(Company company) {
		if(this.login) {
			if (this.getAllCompanies().contains(company)) {
				System.out.println("This company already exists!");
				return;
			}
			
			this.companyDao.createCompany(company);
		}
	}
	
	public void removeCompany(Company company) {
		if(this.login)
			this.companyDao.removeCompany(company);
	}
	
	public void updateCompany(Company company) {
		if(this.login)
			this.companyDao.updateCompany(company);
	}
	
	public Company getCompany(long id) {
		if(this.login)
			return this.companyDao.getCompany(id);
		return null;
	}
	
	public Collection<Company> getAllCompanies(){
		if(this.login)
			return this.companyDao.getAllCompanies();
		return null;
	}
	
	public void createCustomer(Customer customer) {
		if(this.login)
			this.customerDao.createCustomer(customer);
	}
	
	public void removeCustomer(Customer customer) {
		if(this.login)
			this.customerDao.removeCustomer(customer);
	}
	
	public void updateCustomer(Customer customer) {
		if(this.login)
			this.customerDao.updateCustomer(customer);
	}
	
	public Customer getCustomer(long id) {
		if(this.login)
			return this.customerDao.getCustomer(id);
		return null;
	}
	
	public Collection<Customer> getAllCustomers(){
		if(this.login)
			return this.customerDao.getAllCustomers();
		return null;
	}

}
