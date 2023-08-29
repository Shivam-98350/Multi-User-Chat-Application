package com.shivam.chatApp.DAO;

import java.sql.Connection;

//Throw early and catch later -> we start throwing the exceptions from deep classes and the main calling method will catch 
import java.sql.DriverManager;
import java.sql.SQLException;
import com.shivam.chatApp.utils.ConfigReader;

public interface CommonDAO {
	//static methods are allowed
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		//Step 1: Load a Driver 
		Class.forName(ConfigReader.getValue("DRIVER")); //it is RuntimeException as thise will 
		//work in real time and we could have written the wrong name/path of the driver
		
		//Step 2: Making a Connection
		final String CONNECTION_STRING = ConfigReader.getValue("CONNECTION");
		final String USER_ID = ConfigReader.getValue("USERID");
		final String PASSWORD = ConfigReader.getValue("PASSWORD");
		Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_ID, PASSWORD);
		if(con != null) {
			System.out.println("Connection Created...");
		}
		return con;
	}
	

}

