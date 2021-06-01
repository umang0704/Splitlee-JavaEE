<%@ page import="java.util.*,dao.UserUtil" %>
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
	
	<div style='padding:5px;border:5px;background-image: linear-gradient(to bottom right,#ffffff,#cbf7cc)' id='expenses'>
		<h3>My All Expenses </h3>
		
		<table class="table table-striped">
		  <%
				String msg_delAllExpense = (String)application.getAttribute("msg_delAllExpense");
				//String msg_delAllExpense = "Msg";
				if(msg_delAllExpense != null){
					%>
			 	<h2 style='border-radius:10px;color:white;padding:5px;margin-right:10%;margin-left:10%;margin-top:20px;margin-bottom:20px;background-image: linear-gradient(to bottom right,#ffbfc1,#ff6368);'><%= msg_delAllExpense %> </h2>
			 	
					<% 
					application.setAttribute("msg_delAllExpense", null);
				}
			%>	
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Date</th>
		      <th scope="col">Description</th>
		      <th scope="col">Amount</th>
		    </tr>
		  </thead>
		  <tbody>
		<%
		ArrayList<ArrayList<Object>> details = UserUtil.getAllExpenses(email);
		int i = 0;
		for(ArrayList<Object> itr : details){
			%>
			<tr>
		      <th scope="row"><%= ++i %></th>
		      <td><%= itr.get(0) %></td>
		      <td><%= itr.get(1) %></td>
		      <td><%= itr.get(2) %></td>
		      <td> 
		      	<form action="deleteExpense" method='post'>
		      	
		      		<input type="hidden" name='expenseId' value=<%= itr.get(3) %>>
		      		<input type="hidden" name='goto' value='showAllExpense.jsp'>
		      		<button type="submit" class='btn btn-danger' )>Delete</button>	
		      	</form>
		      </td>
		    </tr>
			<%
		}
		%> 
		    
		 
		  </tbody>
		</table>
		
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