package com.shivam.chatApp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shivam.chatApp.DAO.CommonDAO;
import com.shivam.chatApp.DTO.UserDTO;
import com.shivam.chatApp.utils.Encryption;
import com.shivam.chatApp.utils.UserInfo;

//USER CRUD Operations
public class UserDAO {
	
	public boolean isLogin(UserDTO userData) throws SQLException, ClassNotFoundException, Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL = "Select userid from users where userid=? and password=?";
		try {
			con = CommonDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userData.getUserid());
			String encryptedPwd = Encryption.passwordEncrypt(new String(userData.getPassword()));
			pstmt.setString(2, encryptedPwd);
			rs = pstmt.executeQuery();
			
//			if(rs.next()) { //means that row is there for the given userid & password
//				return true;
//			}else {
//				return false;
//			}
			return rs.next();
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
	
	
	public int add(UserDTO userData) throws ClassNotFoundException, SQLException, Exception{ //it acts like a handbag. put everything inside it and send it once together
		//System.out.println("Record:"+userData.getUserid()+" "+userData.getPassword());
		
		//now put the records into the database
		
		//create connection
		Connection connection = null;
		
		//to write queries
		Statement stmt = null;
		try {
			//now we dont want to make objects of CommonDao, so we made it interface
			connection = CommonDAO.createConnection(); //STEP 1: connection created
			
			stmt = connection.createStatement(); //STEP 2: write a query
			//new String() creates a new string, otherwise it would have given hashcode.
			int record = stmt.executeUpdate("insert into users (userid, password) values (\"" + userData.getUserid()+"\",\""+Encryption.passwordEncrypt(new String(userData.getPassword()))+"\");"); //Insert, delete, update
			return record; //1 -> added, 0->error
		}
		finally { //Always executes except System.exit(0); and always used with try{}
			if(stmt != null) {
				stmt.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
	}
	
	 public static void storeChatLog(String sender, String message) {
	       
	        	Connection connection = null;
	        	PreparedStatement statement=null;
	        	
	        		try {
						connection=	CommonDAO.createConnection();
						String sql = "INSERT INTO chatlogs (sender, message) VALUES (?, ?)";
			            statement = connection.prepareStatement(sql);
			            statement.setString(1, sender);
			            statement.setString(2, message);

			            // Execute the statement
			            statement.executeUpdate();

			            // Close the resources
			            statement.close();
			            connection.close();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		finally {
	        			try {//Always executes except System.exit(0); and always used with try{}
	        			if(statement != null) {
	        				
								statement.close();
							
	        			}
	        			if(connection != null) {
	        				connection.close();
	        			}
	        			} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	        		}
	 }
	
}

