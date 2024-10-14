package com.techblog.dao;
import java.sql.*;
import java.util.ArrayList;

import com.techblog.entities.Category;
public class PostDao {
    
	Connection con;

	public PostDao(Connection con) {
		this.con = con;
	}
	
	public ArrayList<Category> getAllCategories(){
		ArrayList<Category> list=new ArrayList();
		
		try{
			String sql="select * from categories";
			Statement st=this.con.createStatement();
			ResultSet set=st.executeQuery(sql);
			while(set.next()) {
				int cid=set.getInt("cid");
				String name=set.getString("name");
				String description=set.getString("description");
				Category c=new Category(cid,name,description);
				list.add(c);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
