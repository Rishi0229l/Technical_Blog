package com.techblog.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.techblog.dao.PostDao;
import com.techblog.entities.Post;
import com.techblog.entities.user;
import com.techblog.helper.ConnectionProvider;
import com.techblog.helper.Helper;



@WebServlet("/AddPostServlet")
@MultipartConfig
public class AddPostServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=res.getWriter()){
			
			// fetch all data
			int cid=Integer.parseInt(req.getParameter("cid"));
			
			String pTitle=req.getParameter("pTitle");
			String pContent=req.getParameter("pContent");
			String pCode=req.getParameter("pCode");
			Part part=req.getPart("pic");
			String img=part.getSubmittedFileName();
			// Getting user id
			HttpSession session= req.getSession();
			user u=(user)session.getAttribute("currentUser");
			int userid=u.getId();
			
			Post po=new Post(pTitle, pContent, pCode, img, null, cid, userid);
			PostDao dao=new PostDao(ConnectionProvider.getConnection());
			if(dao.savePost(po)) {
				String path=req.getRealPath("/")+"blog_pics"+File.separator+part.getSubmittedFileName();
				Helper.saveFile(part.getInputStream(), path);
				out.println("done");
			}else {
				out.println("Failed...");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
