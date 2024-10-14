package com.techblog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techblog.dao.userDao;
import com.techblog.entities.user;
import com.techblog.helper.ConnectionProvider;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		// Fetch all form data
		String check=req.getParameter("check");
		if(check==null) {
			out.println("Box not checked");
		}else {
			// fetch data from form
			String name=req.getParameter("user_name");
			String email=req.getParameter("user_email");
			String password=req.getParameter("user_password");
			String gender=req.getParameter("gender");
			String about=req.getParameter("about");
			
			// create user object and set all data to that object
			user user=new user(name,email,password,gender,about);
			
			// Create a user dao object
			userDao dao=new userDao(ConnectionProvider.getConnection());
			if(dao.saveUser(user)) {
//				out.println("Registration Successful...");
				resp.sendRedirect("login_page.jsp");
			}else {
//				out.println ("Registration Failed...");
				resp.sendRedirect("registration_page.jsp");
			}
			
		}
	}
}
