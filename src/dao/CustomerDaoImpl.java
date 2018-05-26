package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import configuration.DataSource;
import facade.AdminFacade;
import ifcs.CouponDao;
import ifcs.CustomerDao;
import model.Coupon;
import model.CouponType;
import model.Customer;

public class CustomerDaoImpl implements CustomerDao {
	DataSource dataSource = new DataSource();
	Connection con = null;
	
	@Override
	public void createCustomer(Customer customer) {
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stmt = null;

		try
		{
			
			String query = "SELECT * FROM customer where CUST_NAME='"+customer.getName()+"'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				System.out.println("Customer already exist");
				return;
			}
					
			query = "INSERT INTO customer(`CUST_NAME`,`PASSWORD`)" + 
					"VALUES ('" + customer.getName() + "','" + customer.getPassword() +"')";

			stmt = con.createStatement();
			int numOfIds = stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			if (numOfIds > 0){

				ResultSet results = stmt.getGeneratedKeys();

				if (results.next()){
					customer.setId(results.getInt(1));
				}
			}
		}catch (SQLException e){
			e.printStackTrace();
			//throw new Exception("CouponDBDAO: An Error occured while trying to INSERT A NEW COUPON into the DB table: " + e.getMessage());
		}

		finally {
			// always returning the connection to the Connection Pool 
			dataSource.returnConnection(con);
		}

		
	}

	@Override
	public void removeCustomer(Customer customer) {
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		try
		{
			Customer tmp = this.getCustomerByName(customer.getName());
			if ( tmp  == null ) {
				System.out.println("Customer "+customer.getName()+" is not exist in the system");
				return;
			}
			//delete the company from the company table and the mapping tables			
			String query1 = "DELETE FROM customer_coupon WHERE CUST_ID='" + tmp.getId()+"'";
			String query2 = "DELETE FROM customer WHERE id='" + tmp.getId()+"'";
			System.out.println(query1);
			System.out.println(query1);
			con.createStatement().executeUpdate(query1);
			con.createStatement().executeUpdate(query2);
		}catch (SQLException e){
			e.printStackTrace();
		}

		finally {
			// always returning the connection to the Connection Pool 
			dataSource.returnConnection(con);
		}
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {

			// updating all the company fields
			String query = "UPDATE customer SET `cust_name`='" + customer.getName() + "', "
				+ "`password`='" + customer.getPassword() + "' WHERE id='"+customer.getId()+"'";

			con.createStatement().execute(query);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		finally {
			// always returning the connection to the Connection Pool 
			dataSource.returnConnection(con);
		}
		
	}

	@Override
	public Customer getCustomer(long id) {
		Customer customer = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {

			String query = "SELECT * FROM customer WHERE id='" + id+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
				// creating new Coupon object
				customer = new Customer();

				// setting the object's fields
				customer.setId(rs.getLong("id"));
				customer.setName(rs.getString("cust_name"));
				customer.setPassword(rs.getString("password"));
				
			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return customer;
	}

	
	
	@Override
	public Customer getCustomerByName(String name) {
		Customer customer = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {

			String query = "SELECT * FROM customer WHERE cust_name='" + name+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
				// creating new Coupon object
				customer = new Customer();

				// setting the object's fields
				customer.setId(rs.getLong("id"));
				customer.setName(rs.getString("cust_name"));
				customer.setPassword(rs.getString("password"));
				
			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return customer;
		
		
	}

	@Override
	public Collection<Customer> getAllCustomers() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Customer> allCustomers = new ArrayList<Customer>();
		Customer customer = null;
		
		
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {

			String query = "SELECT * FROM customer ";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()){
				// creating new Coupon object
				customer = new Customer();

				// setting the object's fields
				customer.setId(rs.getLong("id"));
				customer.setName(rs.getString("cust_name"));
				customer.setPassword(rs.getString("password"));
				
				//adding company to the list
				allCustomers.add(customer);

			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return allCustomers;
		
	}

	@Override
	public Collection<Coupon> getCoupons(Customer customer) {
		String query = "SELECT c.* from coupon c join customer_coupon cc ON c.id=cc.coupon_id where cust_id='"+customer.getId()+"'";
		Statement stmt = null;
		Coupon coupon = null;
		ResultSet rs = null;
		List<Coupon> allCoupons = new ArrayList<Coupon>();
		//Company company = null;
		
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()){
				// creating new Coupon object
				coupon = new Coupon();
				// setting the object's fields
				coupon.setId(rs.getLong("id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setStartDate(rs.getDate("start_date"));
				coupon.setEndDate(rs.getDate("end_date"));
				coupon.setAmount(rs.getInt("amount"));
				CouponType couponType = CouponType.valueOf(rs.getString("type"));
				coupon.setType(couponType);
				coupon.setMessage(rs.getString("message"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));

				// adding the object to the Coupons Collection
				allCoupons.add(coupon);

			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return allCoupons;
	}

	@Override
	public Customer login(String custName, String password) {
		Customer customer = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {

			String query = "SELECT * FROM customer WHERE cust_name='" + custName+"' and password='"+password+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
				// creating new Coupon object
				customer = new Customer();

				// setting the object's fields
				customer.setId(rs.getLong("id"));
				customer.setName(rs.getString("cust_name"));
				customer.setPassword(rs.getString("password"));
				
			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return customer;
	}

	@Override
	public void purchaseCoupon(Coupon coupon, Customer customer) {
		//Customer customer = null;
		//Coupon coupon;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			CouponDao dao = new CouponDaoImpl();
			Coupon tmp = dao.getCouponByTitle(coupon.getTitle());
			if ( tmp instanceof Coupon)
				coupon = tmp;
			else {
				System.out.println("Unable to retrieve Coupone from DB "+coupon.getTitle());
				return;
			}
			String query = "SELECT * FROM customer_coupon WHERE  CUST_ID='"+customer.getId()+"' AND COUPON_ID='"+coupon.getId()+"'";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
				// creating new Coupon object
				System.out.println("Coupon already purchased!");
				
			} else {
				query = "INSERT INTO customer_coupon (CUST_ID , COUPON_ID) VALUES ('" + customer.getId()+"', '"+coupon.getId()+"')";
				System.out.println(query);
				stmt = con.createStatement();
				stmt.execute(query);
			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}		
	}

	@Override
	public Collection<Coupon> getCouponsByType(Customer customer, CouponType type) {
		String query = "SELECT c.* from coupon c join customer_coupon cc ON c.id=cc.coupon_id where cust_id='"+customer.getId()+"' AND c.TYPE='"+ type+"'";
		Statement stmt = null;
		Coupon coupon = null;
		ResultSet rs = null;
		List<Coupon> allCoupons = new ArrayList<Coupon>();
		//Company company = null;
		
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()){
				// creating new Coupon object
				coupon = new Coupon();
				// setting the object's fields
				coupon.setId(rs.getLong("id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setStartDate(rs.getDate("start_date"));
				coupon.setEndDate(rs.getDate("end_date"));
				coupon.setAmount(rs.getInt("amount"));
				CouponType couponType = CouponType.valueOf(rs.getString("type"));
				coupon.setType(couponType);
				coupon.setMessage(rs.getString("message"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));

				// adding the object to the Coupons Collection
				allCoupons.add(coupon);

			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return allCoupons;
	}

	@Override
	public Collection<Coupon> getCouponsByPrice(Customer customer, double price) {
		String query = "SELECT c.* from coupon c join customer_coupon cc ON c.id=cc.coupon_id where cust_id='"+customer.getId()+"' AND c.PRICE='"+ price+"'";
		Statement stmt = null;
		Coupon coupon = null;
		ResultSet rs = null;
		List<Coupon> allCoupons = new ArrayList<Coupon>();
		//Company company = null;
		
		try {
			con = DataSource.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()){
				// creating new Coupon object
				coupon = new Coupon();
				// setting the object's fields
				coupon.setId(rs.getLong("id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setStartDate(rs.getDate("start_date"));
				coupon.setEndDate(rs.getDate("end_date"));
				coupon.setAmount(rs.getInt("amount"));
				CouponType couponType = CouponType.valueOf(rs.getString("type"));
				coupon.setType(couponType);
				coupon.setMessage(rs.getString("message"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));

				// adding the object to the Coupons Collection
				allCoupons.add(coupon);

			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return allCoupons;
	}

	

	
	

}
