<%@page import="com.techblog.entities.Post"%>
<%@page import="com.techblog.helper.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.*"%>
<%@page import="com.techblog.dao.PostDao"%>

<div class="row">
<%
    PostDao d=new PostDao (ConnectionProvider.getConnection());
    int cid=Integer.parseInt(request.getParameter("cid") );
    List<Post> posts=null;
    if(cid==0){
       posts=d.getAllPosts();
    }
    else{
       posts=d.getPostByCatId(cid);
    }
    
    if(posts.size()==0){
    	out.println("No Posts in this Category...");
    	return;
    }
    
    for(Post p:posts){
%>

   <!-- Creating card inside loop to be displayed -->
   <Div class="col-md-6 mt-2">
       <Div class="card">
           <img class="card-img-top" src="blog_pics/<%= p.getpPic() %>" alt="Card image cap">
           <Div class="card-body">
               <B><%= p.getpTitle() %></B>
               <P><%= p.getpContent() %></P>
              
           </Div>
           
           <Div class="card-footer text-center">
               <a href="#!" class="btn btn-outline-primary btn-sm"><i class="fa  fa-thumbs-o-up"></i> <span></span> </a>
               <a href="#!" class="btn btn-outline-primary btn-sm"><i class="fa  fa-commenting-o"></i> <span>20</span> </a>
               <a href="show_blog_page.jsp?post_id=<%= p.getPid() %>" class="btn btn-outline-primary btn-sm">Read more...</a>
           </Div>
           
       </Div>
   </Div>



<% 
    }
%>
</div>