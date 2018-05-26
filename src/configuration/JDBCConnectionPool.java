package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class JDBCConnectionPool {

	Set<Connection> availableConnections = new HashSet <Connection>();


	//initialize the connection pool in the constructor.
	public JDBCConnectionPool() {
		initializeConnectionPool();
	}


	//Get the database configuration from Configuration.java and Create a new Connection 
	private Connection createNewConnectionForPool() 
	{
		Connection connection = null;
		// get the configuraiton object to get the database configuration
		Configuration config = Configuration.getInstance();
		try {
			//load the Database driver using Class.forName
			Class.forName(config.DB_DRIVER);
			// Create connection by using DriverManager
			connection = (Connection) DriverManager.getConnection(config.DB_URL, config.DB_USER_NAME, config.DB_PASSWORD);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection ;
	}



	//Initialize the connection pool and check the connection pool is full and if connection pool is empty then add new connection 

	private void initializeConnectionPool()
	{
		while(!checkIfConnectionPoolIsFull())
		{
			availableConnections.add(createNewConnectionForPool());
		}
	} 


	private synchronized boolean checkIfConnectionPoolIsFull() {
		final int MAX_POOL_SIZE = Configuration.getInstance().DB_MAX_CONNECTIONS;
		// check the connections size in the available connections
		if(availableConnections.size() < MAX_POOL_SIZE)	
		{
			return false;
		}
		return true;
	}

	//Get the connection from connection pool 
	public synchronized Connection getConnectionFromPool() throws InterruptedException {
		Connection connection = null;
		boolean flag = true;
		while (flag) {
			if (availableConnections.size() > 0) {
				// connection = (Connection) availableConnections.get(0);
				connection = (Connection) availableConnections.iterator().next();
				availableConnections.remove(connection);
				// availableConnections.remove(0);
				flag = false;
			} else {
				wait();
			}
		}
		return connection;
	}


	//Return the connection to connection pool 
	public synchronized void returnConnectionToPool(Connection connection)
	{
		availableConnections.add(connection);
		notifyAll();
	}
}



