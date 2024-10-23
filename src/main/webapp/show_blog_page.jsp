<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<% 
    int postId=Integer.parseInt(request.getParameter("post_id") );
    
%>
    
    
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
     <H1>Hello viewer</H1>
     <H1>Post Id: <%= postId %></H1>
</body>
</html>