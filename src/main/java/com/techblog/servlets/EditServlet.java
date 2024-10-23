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

import com.techblog.dao.userDao;
import com.techblog.entities.message;
import com.techblog.entities.user;
import com.techblog.helper.ConnectionProvider;
import com.techblog.helper.Helper;

@WebServlet("/EditServlet")
@MultipartConfig
public class EditServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		
		
		// fetch all data
		String userEmail=req.getParameter("user_email");
		String userName=req.getParameter("user_name");
		String userPassword=req.getParameter("user_password");
		String userAbout=req.getParameter("user_about");
		Part part=req.getPart("image");
		String imgName=part.getSubmittedFileName();
		
		// get the user from the session
		HttpSession s=req.getSession();
		user us=(user)s.getAttribute("currentUser");
		us.setEmail(userEmail);
		us.setName(userName);
		us.setPassword(userPassword);
		us.setAbout(userAbout);
		String oldFile=us.getProfile();
		us.setProfile(imgName);
		
		// update in database
		userDao ud=new userDao(ConnectionProvider.getConnection());
		boolean check=ud.updateUser(us);
		if(check==true) {
			String path=req.getRealPath("/")+"pics"+File.separator+us.getProfile();
			String pathOldFile=req.getRealPath("/")+"pics"+File.separator+oldFile;
			
			if(!oldFile.equals("default.png"))
			    Helper.deleteFile(pathOldFile);
			if( Helper.saveFile(part.getInputStream(), path) ) {
			    out.println("Profile Updated ... ");
			    message sms=new message("Profile details Updated...","success","alert-success");
	    		s.setAttribute("sms", sms);
			}
		}
		else{ 
			out.println("Profile Update Failed ... ");
			message sms=new message("Profile Update Failed...","error","alert-danger");
    		s.setAttribute("sms", sms);
		}
		resp.sendRedirect("profile.jsp");
	}

}
