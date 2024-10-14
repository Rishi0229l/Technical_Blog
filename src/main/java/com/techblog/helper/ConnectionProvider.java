package com.techblog.helper;

import java.sql.*;
public class ConnectionProvider {
	private static Connection con;
	public static Connection getConnection() {
		try {
			if(con==null ) {
				//Load driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// Create a connection
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techblogdb","root","Admin@5");
			}
		 }catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
}
