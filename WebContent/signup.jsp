<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Splitlee|Signup</title>
 <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.min.css">
    <link rel="icon" href="images/logo2.png">
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
	<%
		String msg_signup = (String)application.getAttribute("msg_signup");
		//String msg_signup = "Msg";
		if(msg_signup != null){
			%>
	 	<h2 style='border-radius:10px;color:white;padding:5px;margin-right:10%;margin-left:10%;margin-top:20px;margin-bottom:20px;background-image: linear-gradient(to bottom right,#ffbfc1,#ff6368);'><%= msg_signup %> </h2>
	 	
			<% 
			application.setAttribute("msg_signup", null);
		}
	%>
	<div style='border-radius:50px; background:#e6ffff;padding:80px; margin-left: 25%;margin-right: 25%;margin-top:20px;margin-bottom:20px'>
		<form action="createAccount" method="post">
		<h3>Sign Up</h3>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Name</label>
			<input type="text" name='name' placeholder="Enter your Name" class="form-control" id="exampleFormControlInput1"required>  
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Email Id</label>
			<input type="email" name='email' placeholder="Enter your Email" class="form-control" id="exampleFormControlInput1"required>  
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Password</label>
			<input type="password" name='pass' placeholder="Enter your Password" class="form-control" id="exampleFormControlInput1"required>  
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Confirm-Password</label>
			<input type="password" name='confpass' placeholder="Re-Enter your Password" class="form-control" id="exampleFormControlInput1" required>  
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Phone Number</label>
			<input type="text" name='phone' placeholder="Enter your Phone Number" class="form-control" id="exampleFormControlInput1" required>  
		</div>
		<div>
			<input type="submit" class='btn btn-success' value="Register">
			<input type="reset" class='btn btn-danger' value="Reset">
		</div>
		
	</form>
	</div>
	
	<!--Footer Content-->
	<jsp:include page="footer1.jsp"></jsp:include>
</body>
</html>