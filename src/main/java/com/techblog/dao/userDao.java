package com.techblog.dao;

import java.sql.*;

import com.techblog.entities.user;

public class userDao {
	private Connection con;

	public userDao(Connection con) {
		super();
		this.con = con;
	}
	
	//method to insert user to database
	public boolean saveUser(user user) {
		try {
			//user----->database
			String sql="insert into users(user_name,user_email,user_password,gender,about) values(?,?,?,?,?)";
		    PreparedStatement ps= this.con.prepareStatement(sql);
		    
		    ps.setString(1, user.getName());
		    ps.setString(2, user.getEmail());
		    ps.setString(3, user.getPassword());
		    ps.setString(4, user.getGender());
		    ps.setString(5, user.getAbout());
		    
		    int i=ps.executeUpdate();
		    if(i>0) {
		    	return true;
		    }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	// method to get user by user email and password
	public user getUserByEmailAndPassword(String email,String password) {
		user client=null;
		
		try {
			String sql="select * from users where user_email=? and user_password=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				client=new user();
				String name=rs.getString("user_name");
				client.setName(name);
				client.setId(rs.getInt("id"));
				client.setEmail(rs.getString("user_email"));
				client.setPassword(rs.getString("user_password"));
				client.setGender(rs.getString("gender"));
				client.setAbout(rs.getString("about"));
				String s=rs.getTimestamp("rdate").toString(); // type casting the Timestamp into string 
				client.setDateTime(s);
				client.setProfile(rs.getString("profile"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return client;
	}
	
	// Update user
	public boolean updateUser(user us) {
		boolean f=false;
		try{
			String sql="update users set user_name=?,user_email=?,user_password=?,gender=?,about=?,profile=? where id=?";
			PreparedStatement p=con.prepareStatement(sql);
			p.setString(1, us.getName());
			p.setString(2, us.getEmail());
			p.setString(3, us.getPassword());
			p.setString(4, us.getGender());
			p.setString(5, us.getAbout().trim());
			p.setString(6, us.getProfile());
			p.setInt(7, us.getId());
			p.executeUpdate();
			f=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
