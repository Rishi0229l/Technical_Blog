package com.techblog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



@WebServlet("/AddPostServlet")
@MultipartConfig
public class AddPostServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=res.getWriter()){
			out.print("<body>");
			String cid=req.getParameter("cid");
			
			String pTitle=req.getParameter("pTitle");
			String pContent=req.getParameter("pContent");
			String pCode=req.getParameter("pCode");
			Part part=req.getPart("pic");
			String img=part.getSubmittedFileName();
			out.print(pTitle); 
			out.print(img);
			out.print("</body>");
		}
	}

}
