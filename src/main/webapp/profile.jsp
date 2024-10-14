<%@page import="com.techblog.entities.*"%>
<%@page import="com.techblog.entities.user"%>
<%@page import="com.techblog.dao.PostDao"%>
<%@page import="com.techblog.helper.ConnectionProvider"%>
<%@page errorPage="error_page.jsp" %>
<%@page import="java.util.*"%>


<% 
    user us=(user)session.getAttribute("currentUser");
    if(us==null){
    	response.sendRedirect("login_page.jsp");
    }
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<!-- CSS -->
<link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"crossorigin="anonymous">
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="CSS/mystyle.css">
<style>
.banner-background {
	clip-path: polygon(30% 0%, 100% 0, 100% 93%, 79% 81%, 51% 100%, 23% 91%, 0 95%, 0 0);
}
</style>
</head>
<body>
    <!-- navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark primary-background">
		<a class="navbar-brand" href="index.jsp"> <span
			class="fa fa-mortar-board"></span> Tech Blog
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#"> <span
						class="	fa fa-bell-o"></span> LearnCode with Rishi <span
						class="sr-only">(current)</span></a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <span class="	fa fa-check-square-o"></span>
						Categories
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Programming Language</a> <a
							class="dropdown-item" href="#">Project Implementation</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Data Structure</a>
					</div></li>

				<li class="nav-item"><a class="nav-link" href="#"> <span
						class="	fa fa-address-card-o"></span> Contact
				</a></li>
				
				<li class="nav-item"><a class="nav-link" href="#" data-toggle="modal" data-target="#add-post-Modal"> <span
						class="	fa fa-asterisk"></span> Do Post
				</a></li>
				

			</ul>
			<ul class="navbar-nav mr-right" >
			    <a class="nav-link" href="#!" data-toggle="modal" data-target="#profile_modal"> <span class="fa fa-user-circle"></span> <%= us.getName() %></a>
			</ul>
			<ul class="navbar-nav mr-right" >
			    <a class="nav-link" href="LogoutServlet"> <span class="fa fa-power-off"></span> Logout</a>
			</ul>
		</div>
	</nav>
	<!--end of navbar-->
	<%
	message m = (message) session.getAttribute("sms");
	if (m != null) {
	%>
	<div class="alert <%=m.getCssClass()%>" role="alert">
		<%=m.getContent()%>
	</div>
	<%
	}
	session.removeAttribute("sms");
	%>




	<!-- Profile Model -->

	<!-- Modal -->
	<div class="modal fade" id="profile_modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header primary-background">
					<h5 class="modal-title" id="exampleModalLabel">TechBlog</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
				<div class="container text-center">
				    <img src="pics/<%=us.getProfile()%>" class="img-fluid" style="border-radius:50%;width:150px">
				    <h5 class="modal-title" id="exampleModalLabel"><%= us.getName() %></h5>
				    
				    <!-- details of user -->
				    <div id="profile-details">
						<table class="table">
							<tbody>
								<tr>
									<th scope="row">ID :</th>
									<td><%=us.getId() %></td>
								</tr>
								<tr>
									<th scope="row">Email :</th>
									<td><%= us.getEmail() %></td>
								</tr>
								<tr>
									<th scope="row">Gender :</th>
									<td><%=us.getGender() %></td>
								</tr>
								<tr>
									<th scope="row">Status :</th>
									<td><%=us.getAbout() %></td>
								</tr>
								<tr>
									<th scope="row">Registerd on :</th>
									<td><%=us.getDateTime() %></td>
								</tr>
							</tbody>
						</table>
						</div>
						
						<!-- profile edit -->
						<div id="profile-edit" style="display: none;">
						    <h3 class="mt-2">Edit Here...</h2>
						    <form action="EditServlet" method="post" enctype="multipart/form-data">
						        <table class="table">
						             <tr>
									     <td >ID :</td>
									     <td><%=us.getId() %></td>
								    </tr>
									<tr>
									     <td >Email :</td>
									     <td><input type="email" class="form-control" name="user_email" value="<%= us.getEmail() %>" ></td>
								    </tr>
									<tr>
									     <td >Name :</td>
									     <td><input type="text" class="form-control" name="user_name" value="<%= us.getName() %>" ></td>
								    </tr>
								    <tr>
									     <td >Password :</td>
									     <td><input type="password" class="form-control" name="user_password" value="<%= us.getPassword() %>" ></td>
								    </tr>
								    <tr>
									     <td >Gender :</td>
                                         <td><%=us.getGender().toUpperCase() %></td>
   								    </tr>
   								    <tr>
									     <td >About :</td>
                                         <td>
                                             <textarea  class="form-control" name="user_about"><%=us.getAbout() %>
                                             </textarea>
                                         </td>
   								    </tr>
   								    <tr>
									     <td>Update Profile Pic :</td>
                                         <td>
                                             <input type="file" name="image" class="form-control"></input>
                                         </td>
   								    </tr>
						        </table>
						        <div class="container">
						            <button type="submit" class="bnt bnt-outline-primary">Save</button>
						        </div>
						    </form>
						</div>
						
						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button id="edit-profile-button" type="button" class="btn btn-primary">Edit</button>
				</div>
			</div>
		</div>
	</div>
   <!-- end of profile modal -->

   <!-- Add Post modal -->
	<div class="modal fade" id="add-post-Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Provide the post details...</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      
	        <form id="add-post-form" action="AddPostServlet" method="post">
	            <div class="form-group">
	                <select class="form-control" name="cid">
		                <option selected disabled>---Select Category--- </option>
		                <%
		                    PostDao postd=new PostDao(ConnectionProvider.getConnection());
		                    ArrayList<Category> a=postd.getAllCategories();
		                    for(Category c:a){
		                %>
		                <option value="<%= c.getCid() %>"><%= c.getName() %></option>
		                <% } %>
		            </select>
	            </div>
	           
	            <div class="form-group">
	                <input type="text" name="pTitle" placeholder="Enter post Title" class="form-control">
	            </div>
	            
	            <div class="form-group">
	                <textarea class="form-control" name="pContent" style="height: 200px" placeholder="Enter your Content" ></textarea>
	            </div>
	            
	            <div class="form-group">
	                <textarea class="form-control" name="pCode" style="height: 200px" placeholder="Enter your Program if any" ></textarea>
	            </div>
	            
	            <div class="form-group">
	            <level>Select your pic</level><br>
	                <input type="file" name="pic">
	            </div>
	            
	            <div class="container text-center">
	                <button type="submit" class="btn btn-outline-primary">Post</button>
	            </div>
	        
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
    <!-- End Post modal -->







	<!-- Script -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"crossorigin="anonymous"></script>
    <script src="js/myjs.js" type="text/javascript"></script>
    <script>
	    $(document).ready(function(){
	    	let editStatus=false;
	    	$("#edit-profile-button").click(function(){
	    		if(editStatus==false){
		    		$("#profile-details").hide()
		    		$("#profile-edit").show();
		    		editStatus=true;
		    		$(this).text("Back");
	    		}else{
	    			$("#profile-details").show()
		    		$("#profile-edit").hide();
	    			editStatus=false;
	    			$(this).text("Edit")
	    		}
	    	})
	    });
    </script>
    
    <!-- Now add Post JS -->
    <script>
        $(document).ready(fuction (e){
        	$("#add-post-form").on("submit", function(event){
        		// This code gets called when form is submited
        		event.preventDefault();
        		console.log("you have clicked on submit...")
        		
        		let form-new FormData(this);
        		// Now requesting to server
        		$.ajax({
        			url: "AddPostServlet",
        			type: 'POST',
        			data: form,
        			success: function(data,textStatus, jqXHR){
        				//success
        			},
        			error: function(jqXHR, textStatus, errorThrown){
        				
        			},
        			processData: false,
        			contentType: false
        		})
        	})
        })
    
    </script>
    
    
    
    
</body>
</html>