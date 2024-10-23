package com.techblog.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.techblog.entities.Category;
import com.techblog.entities.Post;
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
	
	public boolean savePost(Post po) {
		boolean flag=false;
		try {
			String sql="insert into posts(pTitle,pContent,pCode,pPic,catId,userId) values(?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, po.getpTitle());
			ps.setString(2, po.getpContent());
			ps.setString(3, po.getpCode());
			ps.setString(4, po.getpPic());
			ps.setInt(5, po.getCatId());
			ps.setInt(6, po.getUserid());
			ps.executeUpdate();
			flag=true;	
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return flag;
	}
	
	
	
	// Get all posts
	public ArrayList<Post> getAllPosts(){
		ArrayList<Post> list=new ArrayList<Post>();
		// fetch all posts
		try {
			PreparedStatement ps=con.prepareStatement("select * from posts order by pid desc");
			ResultSet set=ps.executeQuery();
			
			while(set.next()) {
				int pid = set.getInt("pid");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date = set.getTimestamp("pDate");
                int catId = set.getInt("catId");
                int userId = set.getInt("userId");
                Post post = new Post(pid, pTitle, pContent, pCode, pPic, date, catId, userId);
                
                list.add(post);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	// Get Posts by CatId
	public ArrayList<Post> getPostByCatId(int catId){
		ArrayList<Post> list=new ArrayList<Post>();
		// fetch posts
		try {
            PreparedStatement p = con.prepareStatement("select * from posts where catId=?");
            p.setInt(1, catId);
            ResultSet set = p.executeQuery();

            while (set.next()) {

                int pid = set.getInt("pid");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date = set.getTimestamp("pDate");

                int userId = set.getInt("userId");
                Post post = new Post(pid, pTitle, pContent, pCode, pPic, date, catId, userId);

                list.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return list;
	}
	
	
	
}
