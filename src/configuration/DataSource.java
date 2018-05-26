package configuration;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

	static JDBCConnectionPool pool = new JDBCConnectionPool();
	  
	 public static Connection getConnection() throws ClassNotFoundException, SQLException, InterruptedException{
	  Connection connection = pool.getConnectionFromPool();
	  return connection;
	 }
	  
	 public void returnConnection(Connection connection) {
	  pool.returnConnectionToPool(connection);
	 }
	 
}
