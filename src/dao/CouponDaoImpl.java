package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import configuration.DataSource;
import ifcs.CouponDao;
import model.Coupon;
import model.CouponType;


public class CouponDaoImpl implements CouponDao {

	DataSource dataSource = new DataSource();
	Connection con = null;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public void createCoupon(Coupon coupon) {
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
		ResultSet rs = null;

		try
		{
			String query2 = "SELECT * FROM coupon WHERE title='" + coupon.getTitle()+"'";
			String query = "INSERT INTO coupon(title,start_date,end_date,amount,type,message,price,image) VALUES ('"+ coupon.getTitle() + "', '" + dateFormat.format(coupon.getStartDate()) +
					"', '" +dateFormat.format(coupon.getEndDate()) + "', '" + coupon.getAmount() + "', '" + coupon.getType().name() + "', '" + coupon.getMessage() + "', '"+ 
					coupon.getPrice() + "', '" + coupon.getImage() +"')";
			//System.out.println(query);
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(query2);
			if (rs.next())
				return;
			
			int numOfIds = stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			if (numOfIds > 0){

				ResultSet results = stmt.getGeneratedKeys();

				if (results.next()){
					System.out.println("IDDDD   "+results.getInt(1));
					coupon.setId(results.getInt(1));
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
	public void removeCoupon(Coupon coupon) {
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
			//delete the coupon from the coupon table and the mapping tables			
			String query1 = "DELETE FROM company_coupon WHERE coupon_id='"  + coupon.getId()+"'";
			String query2 = "DELETE FROM customer_coupon WHERE coupon_id='" + coupon.getId()+"'";
			String query3 = "DELETE FROM coupon WHERE id='" + coupon.getId()+"'";

			System.out.println(query1);
			System.out.println(query2);
			System.out.println(query3);
			con.createStatement().executeUpdate(query1);
			con.createStatement().executeUpdate(query2);
			con.createStatement().executeUpdate(query3);

		}catch (SQLException e){
			e.printStackTrace();
		}

		finally {
			// always returning the connection to the Connection Pool 
			dataSource.returnConnection(con);
		}

	}
	@Override
	public void updateCoupon(Coupon coupon) {
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

			long id = coupon.getId();
			String newTitle = coupon.getTitle();
			Date newStartDate = coupon.getStartDate();
			Date newEndDate = coupon.getEndDate();
			int newAmount = coupon.getAmount();
			CouponType newType = coupon.getType();
			String newMessage = coupon.getMessage();
			double newPrice = coupon.getPrice();
			String newImage = coupon.getImage();

			// updating all the Coupon fields
			String query = "UPDATE coupon SET `title`='" + newTitle + "', "
					+ "`start_date`='" + dateFormat.format(newStartDate) + "', " + "`end_date`='" + dateFormat.format(newEndDate) + "', "
					+ "`amount`=" + newAmount + ", " + "`type`='" + newType.name() + "', "
					+ "`message`='" + newMessage + "', " + "`price`=" + newPrice + ", "
					+ "`image`='" + newImage + "' WHERE id='" + id+"'";

			con.createStatement().execute(query);
		}

		catch (SQLException e){

			e.printStackTrace();
		}
		finally {

			dataSource.returnConnection(con);
		}
	}

	@Override
	public Coupon getCoupon(long id) {
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
		
		Coupon coupon = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			String query = "SELECT * FROM coupon WHERE id='" + id+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
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
			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return coupon;
	}



	@Override
	public List<Coupon> getAllCoupons() {
		
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
		ResultSet rs = null;
		List<Coupon> allCoupons = new ArrayList<Coupon>();
		Coupon coupon = null;

		try {

			String query = "SELECT * FROM coupon";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){

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
	public Coupon getCouponByTitle(String title) {
		Coupon coupon = null;
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

			String query = "SELECT * FROM coupon WHERE TITLE='" + title+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
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
			}

		}

		catch (SQLException e){
			e.printStackTrace();
		}

		finally {

			dataSource.returnConnection(con);
		}
		return coupon;
	}

}
