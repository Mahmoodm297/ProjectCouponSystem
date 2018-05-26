package configuration;

public class Configuration {
	//Make the configuration class as singleton
	 private static Configuration configuration = new Configuration();
	  
	 public static Configuration getInstance(){ 
	  return configuration;
	 }
	 
	 //Create initialize method that sets the database configuration properties 
	 public String DB_USER_NAME ;
	 public String DB_PASSWORD ;
	 public String DB_URL;
	 public String DB_DRIVER;
	 public Integer DB_MAX_CONNECTIONS;
	 
	 
	 public Configuration(){
		 conInit();
		 }
	 
	 
	 private void conInit()
	 {
		 DB_USER_NAME = "coupon";
		  DB_PASSWORD = "coupon123456";
		  DB_URL = "jdbc:mysql://localhost:3306/coupon?useSSL=false";
		  DB_DRIVER = "com.mysql.jdbc.Driver";
		  DB_MAX_CONNECTIONS = 15;
	 }
}
