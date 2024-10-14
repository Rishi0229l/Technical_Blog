package com.techblog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.protocol.Message;
import com.techblog.dao.userDao;
import com.techblog.entities.message;
import com.techblog.entities.user;
import com.techblog.helper.ConnectionProvider;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	PrintWriter out=resp.getWriter();
    	
    	// Fetch email and password from request object
    	String email=req.getParameter("email");
    	String password=req.getParameter("password");
    	userDao dao=new userDao(ConnectionProvider.getConnection());
    	
    	user user1=dao.getUserByEmailAndPassword(email, password);
    	
    	if(user1==null) {
    		// login error message
    		// out.println("Invalid Details... Try again");
    		message sms=new message("Invalid Details... Try again","error","alert-danger");
    	    
    		HttpSession ss=req.getSession();
    		ss.setAttribute("sms", sms);
    		resp.sendRedirect("login_page.jsp");
    	}
    	else {
    		// login success
    		HttpSession s=req.getSession();
    		s.setAttribute("currentUser", user1);
    		resp.sendRedirect("profile.jsp");
    	}
    }
}
