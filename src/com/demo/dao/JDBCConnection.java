package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
   
	public Connection getConnection(){
		
		Connection connection = null;
		String connectionURL = "jdbc:oracle:thin:@localhost:1521:XE";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection= DriverManager.getConnection(connectionURL, "AngularDB", "dost1234");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
}
