<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Splitlee|LogIn</title>
 <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" href="images/logo2.png">
</head>
<body>
	<jsp:include page="header1.jsp"></jsp:include>
	<div style="padding:5px;margin:2px 2px 2px 2px">
		<a class='btn btn-primary' href="index.jsp">Home</a>
	</div>
	
	<hr>
	
	<div style='border-radius:50px; background:#e6ffff;padding:80px; margin-left: 25%;margin-right: 25%;margin-top:5%;margin-bottom:5%'>
		<form action="loginAuth" method='post'> 
			<%
		String msg_login = (String)application.getAttribute("msg_login");
		if(msg_login != null){
			%> 
<h2 style='border-radius:10px;color:white;padding:5px;margin-right:10%;margin-left:10%;margin-top:20px;margin-bottom:20px;background-image: linear-gradient(to bottom right,#ffbfc1,#ff6368);'><%= msg_login %> </h2>
	 		
			<% 
			application.setAttribute("msg_login", null);
		}
	%>
			<h3>Log In</h3>
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Email address</label>
			  <input type="email" class="form-control" name='email' id="exampleFormControlInput1" placeholder="name@example.com">
			</div> 
			<div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Password</label>
			  <input type="password" class="form-control" name='pass' id="exampleFormControlInput1" placeholder="name@example.com">
			</div>  
			<div>
				<input type="submit" class='btn btn-success' value="Log In">
				<input type="reset" class='btn btn-danger' value="Reset">
			</div>
			
		</form> 
	</div>
	
	<!--Footer Content-->
	<jsp:include page="footer1.jsp"></jsp:include>
</body>
</html>