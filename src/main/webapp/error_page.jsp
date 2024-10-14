<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sorry something went wrong!</title>
<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="CSS/mystyle.css">
<style>
    .banner-background{
        clip-path: polygon(30% 0%, 100% 0, 100% 93%, 79% 81%, 51% 100%, 23% 91%, 0 95%, 0 0);
    }
    
</style>
</head>
<body>
    <div id="imgcon" class="container text-center">
        <img src="Img/error.png" class="img-fluid" alt="Responsive image">
        <h3 class="display-4">Sorry ! Something went wrong ...</h3>
        <%= exception %>
        <a href="index.jsp" class="btn primary-background btn-lg text-white mt-2">Home</a>
    </div>
</body>
</html>