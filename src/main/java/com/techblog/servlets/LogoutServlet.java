package com.techblog.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techblog.entities.message;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession s=req.getSession();
    	s.removeAttribute("currentUser");
    	message m=new message("Logout Successful...","success","alert-success");
        s.setAttribute("sms", m);
    	resp.sendRedirect("login_page.jsp");
    }
}
