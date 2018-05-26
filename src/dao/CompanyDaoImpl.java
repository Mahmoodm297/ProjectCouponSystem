package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import configuration.DataSource;
import ifcs.CompanyDao;
import ifcs.CouponDao;
import model.Company;
import model.Coupon;
import model.CouponType;

public class CompanyDaoImpl implements CompanyDao {
	DataSource dataSource = new DataSource();
	Connection con = null;
	
	@Override
	public void createCompany(Company company) {

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
			String query = "SELECT * FROM company where comp_name='"+company.getCompanyName()+"'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				System.out.println("Create log Company"+company.getCompanyName()+"Exist");
				
				return;
			}
			
			 query = "INSERT INTO company(`COMP_NAME`,`PASSWORD`,`EMAIL`)" + 
					"VALUES ('" + company.getCompanyName() + "','" + company.getPassword() + "','"+company.getEmail()+"')";

			stmt = con.createStatement();
			int numOfIds = stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			if (numOfIds > 0){

				ResultSet results = stmt.getGeneratedKeys();

				if (results.next()){
					company.setId(results.getInt(1));
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

		// TODO Auto-generated method stub

	}

	@Override
	public void removeCompany(Company company) {
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
			//delete the company from the company table and the mapping tables			
			String query1 = "DELETE FROM company_coupon WHERE COMP_ID=" + company.getId();
			String query2 = "DELETE FROM company WHERE id=" + company.getId();

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
	public void updateCompany(Company company) {
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
			String query = "UPDATE company SET `comp_name`='" + company.getCompanyName() + "', "
				+ "`password`='" + company.getPassword() + "', " + "`email`='" + company.getEmail() + "' WHERE id='"+company.getId()+"'";

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
	public Company getCompany(long id) {
		Company company = null;
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

			String query = "SELECT * FROM company WHERE id='" + id+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
				// creating new Coupon object
				company = new Company();

				// setting the object's fields
				company.setId(rs.getLong("id"));
				company.setCompanyName(rs.getString("comp_name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
				
			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return company;
	}

	@Override
	public Collection<Company> getAllCompanies() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Company> allCompanies = new ArrayList<Company>();
		Company company = null;
		
		
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

			String query = "SELECT * FROM company ";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()){
				// creating new Coupon object
				company = new Company();

				// setting the object's fields
				company.setId(rs.getLong("id"));
				company.setCompanyName(rs.getString("comp_name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
				
				//adding company to the list
				allCompanies.add(company);

			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return allCompanies;
		
	}

	@Override
	public Collection<Coupon> getCoupons(Company company) {
		String query = "SELECT c.* from coupon c join company_coupon cc ON c.id=cc.coupon_id where comp_id='"+company.getId()+"'";
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
	public Company login(String compName, String password) {
		Company company = null;
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

			String query = "SELECT * FROM company WHERE comp_name='" + compName+"' and password='"+password+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
				// creating new Coupon object
				company = new Company();

				// setting the object's fields
				company.setId(rs.getLong("id"));
				company.setCompanyName(rs.getString("comp_name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
				
			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return company;
	}

	@Override
	public Company getCompanyByName(String name) {
		Company company = null;
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

			String query = "SELECT * FROM company WHERE COMP_NAME='" + name+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
				// creating new Coupon object
				company = new Company();

				// setting the object's fields
				company.setId(rs.getLong("id"));
				company.setCompanyName(rs.getString("comp_name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
				
			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return company;
	}

	@Override
	public void createCoupon(Coupon coupon, Company company) {
		CouponDao newCoupon = new CouponDaoImpl();
		Collection<Coupon> coupons;
		Boolean flag = false;
		coupons = this.getCoupons(company);
		for ( Coupon cop: coupons) {
			if ( cop.getTitle().equals(coupon.getTitle())) {
				coupon = cop;
				flag = true;
			}
		}
		if(flag == true) {
			System.out.println("Exception: Coupon " + coupon.getTitle()+ " exists!");
			return;
		} else {
		
			newCoupon.createCoupon(coupon);
			Coupon couponTemp = null;
			String query = null;

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
				System.out.println(coupon);
				couponTemp = newCoupon.getCouponByTitle(coupon.getTitle());
				System.out.println(couponTemp);
				//company.getCoupons().add(couponTemp);

				query = "INSERT INTO company_coupon (COMP_ID,COUPON_ID) VALUES ('" + company.getId() + "','"
						+ couponTemp.getId() + "')";
				
				System.out.println(query);
				stmt = con.createStatement();
				stmt.execute(query);
				

			} catch (SQLException e) {
				e.printStackTrace();
			}

			finally {

				dataSource.returnConnection(con);
			}
		}	
	}
	
	@Override
	public void removeCoupon(Coupon coupon, Company company) {
		System.out.println(coupon);
		System.out.println(this.getCoupons(company));
		Collection<Coupon> list = this.getCoupons(company);
		Boolean flag = false;
		for ( Coupon cop: list) {
			if ( cop.getTitle().equals(coupon.getTitle())) {
				coupon = cop;
				flag=true;
			}
		}
		if( flag == true ) {
			CouponDaoImpl couponHandler = new CouponDaoImpl();
			couponHandler.removeCoupon(coupon);
			//company.getCoupons().remove(coupon);
			
		} else {
			System.out.println("Exception: Coupon " + coupon.getTitle()+ " does not exist!");
		}
		
	}

	@Override
	public void updateCoupon(Coupon coupon, Company company) {
		if(company.getCoupons().contains(coupon)) {
			CouponDaoImpl couponHandler = new CouponDaoImpl();
			couponHandler.updateCoupon(coupon);
			company.getCoupons().remove(coupon);
			company.getCoupons().add(coupon);
			
		} else {
			System.out.println("Exception: Coupon " + coupon.getTitle()+ " does not exist!");
		}
		
	}

	@Override
	public Collection<Coupon> getCouponsBytype(Company company, CouponType type) {
		String query = "SELECT c.* from coupon c join company_coupon cc ON c.id=cc.coupon_id where comp_id='"+company.getId()+"' AND c.type='"+type+"'";
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
	public Collection<Coupon> getCouponsByPrice(Company company, double price) {
		String query = "SELECT c.* from coupon c join company_coupon cc ON c.id=cc.coupon_id where comp_id='"+company.getId()+"' AND c.price='"+price+"'";
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
	public Collection<Coupon> getCouponsByDate(Company company, Date endDate) {
		String query = "SELECT c.* from coupon c join company_coupon cc ON c.id=cc.coupon_id where comp_id='"+company.getId()+"' AND c.end_date='"+endDate+"'";
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
