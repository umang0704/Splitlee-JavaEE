<%
String name = (String)session.getAttribute("u_name");
String email = (String)session.getAttribute("u_email");
String phone = (String)session.getAttribute("u_phone");
if(name!=null){
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Splitlee | <%=name %></title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.min.css">
    <link rel="icon" href="images/logo2.png"> 
</head>
<body>
	<jsp:include page="header1.jsp"></jsp:include>
	<!-- Nav -->
	<div>
		<a class='btn btn-primary' href='usershome.jsp'>Dashboard</a>
		<a class='btn btn-danger'  href="logout">Log Out</a>
	</div>
	<!-- Editable Profile Details -->
	
	<div style='border-radius:50px; background:#e6ffff;padding:80px; margin-left: 25%;margin-right: 25%;margin-top:20px;margin-bottom:20px'>
		<form action="changeAccountDetail" method="post">
		<%
		String msg_detchange = (String)application.getAttribute("msg_detchange");
		//String msg_detchange = "Msg";
		if(msg_detchange != null){
			%>
	 	<h2 style='border-radius:10px;color:white;padding:5px;margin-right:10%;margin-left:10%;margin-top:20px;margin-bottom:20px;background-image: linear-gradient(to bottom right,#ffbfc1,#ff6368);'><%= msg_detchange %> </h2>
	 	
			<% 
			application.setAttribute("msg_detchange", null);
		}
	%>
		<h3>Sign Up</h3>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Email Id</label>
			<input type="email" name='email' placeholder=<%=email %> class="form-control" id="exampleFormControlInput1"required readonly="readonly">  
		</div> 
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Name</label>
			<input type="text" name='name' placeholder=<%=name %> value=<%=name %> class="form-control" id="exampleFormControlInput1"required>  
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Phone Number</label>
			<input type="text" name='phone' placeholder=<%=phone %>  value=<%=phone %> class="form-control" id="exampleFormControlInput1" required>  
		</div>
		<div>
			<input type="submit" class='btn btn-success' value="Update">
			<input type="reset" class='btn btn-danger' value="Reset">
		</div> 		
		</form>
		
		<br> <br> 
		
		
	<%
		String msg_passchange = (String)application.getAttribute("msg_passchange");
		//String msg_passchange = "Msg";
		if(msg_passchange != null){
			%>
	 	<h2 style='border-radius:10px;color:white;padding:5px;margin-right:10%;margin-left:10%;margin-top:20px;margin-bottom:20px;background-image: linear-gradient(to bottom right,#ffbfc1,#ff6368);'><%= msg_passchange %> </h2>
	 	
			<% 
			application.setAttribute("msg_passchange", null);
		}
	%>
		
		<form action="changePassword" method="post">
		<h3>Change Password</h3>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Old Password</label>
			<input type="password" name='oldpassword' placeholder="Enter your old password" class="form-control" id="exampleFormControlInput1"required>  
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">New Password</label>
			<input type="password" name='newpassword' placeholder="New Password" class="form-control" id="exampleFormControlInput1"required>  
		</div> 
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Re-Enter New Password</label>
			<input type="password" name='confnewpassword' placeholder="Re-Enter New Password" class="form-control" id="exampleFormControlInput1" required>  
		</div>
		<div>
			<input type="submit" class='btn btn-success' value="Update">
			<input type="reset" class='btn btn-danger' value="Reset">
		</div> 
		</form> 
	</div>
	
	<jsp:include page="footer1.jsp"></jsp:include>
</body>
</html>
<%	
}else{
	application.setAttribute("msg_login", "Please LogIn..!");
	response.sendRedirect("login.jsp");
}
%>