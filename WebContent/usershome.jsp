<%@page import="dao.UserUtil,java.time.*,java.util.*" %>
<%
String name = (String)session.getAttribute("u_name");
String email = (String)session.getAttribute("u_email");

if(name!=null){
	LocalDateTime now = LocalDateTime.now();
	int currentMonth  = now.getMonthValue();
	int year = now.getYear();
	double budget = 0;
	double leftbudget = 0;
	try{
		Double expense = UserUtil.sumForMonth(email, currentMonth, year);
		Double[] budgets = UserUtil.getBudgetUpdates(email, currentMonth);
		budget = budgets[1];
		leftbudget = budgets[0] - expense;
	}catch(Exception ex){
		ex.printStackTrace();
	}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Splitlee | <%=name%></title>
 <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.min.css">
    <link rel="icon" href="images/logo2.png"> 
</head>
<body>
	
	<jsp:include page="header1.jsp"></jsp:include>
	<h1>Welcome <%= name %></h1>
	<hr>
	<!-- Menu --> 
	<div style="padding:15px;margin:2px 2px 2px 2px;">
		<form action="showMonthlyExpenses.jsp" style='margin:2px 2px 2px 2px;'>
			<input type="date" name='date'required="required"placeholder="Select month">
			<button class='btn btn-success' type="submit">Show Expense</button> <br>
			<p>*Select any date from the month.</p>
		</form>
		<a class='btn btn-primary' href="profile.jsp">Profile</a>
<!-- 		<a class='btn btn-primary' href=".jsp">Groups</a> -->
<!-- 		<a class='btn btn-primary' href=".jsp">Feedback</a> -->
<!-- 		<a class='btn btn-primary' href=".jsp">About</a>  -->
		<a class='btn btn-success' href="showAllExpense.jsp">My All Expense</a>
	</div> 
	
	<!-- Nav -->
	<div style='border-radius:15px;background-image: linear-gradient(to bottom right,#dbd7d7,#6c89d9)'>
		<!-- name -->
		<label style='margin:5px 50% 5px 2px;'><b><%=name%></b></label>
		<!-- budget left -->
		<label style='margin:10px 3px 10px 3px;background: red;color:white;padding:5px;border-radius:5px'>Budget Left:<b><%= leftbudget %></b> of<label style='margin:10px 3px 10px 3px;background: #55ed58;color:black;padding:5px;border-radius:5px'><b><%= budget %></b></label>
		 </label>
		<!-- logout -->
		<a class='btn btn-danger' style='margin:5px 0 5px 15%' href="logout">Log Out</a>
	</div>
	<!-- Add Personal Expense -->
	 
		<div style='border-radius:50px; background:#e6ffff; padding: 50px; margin-right:25%;margin-left: 30%;margin-top:20px;margin-bottom:20px'>
			<form action="setBudget" style='margin:2px 2px 2px 2px;' id='setbudget' method="post">
			<%
				String msg_setbudget = (String)application.getAttribute("msg_setbudget");
				//String msg_setbudget = "Msg";
				if(msg_setbudget != null){
					%>
			 	<h2 style='border-radius:10px;color:white;padding:5px;margin-right:10%;margin-left:10%;margin-top:20px;margin-bottom:20px;background-image: linear-gradient(to bottom right,#ffbfc1,#ff6368);'><%= msg_setbudget %> </h2>
			 	
					<% 
					application.setAttribute("msg_setbudget", null);
				}
			%>	
				<h3>Set Budget</h3>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">Budget</label>
					<input type="number" name='budget' placeholder="Enter Budget Amount" class="form-control" id="exampleFormControlInput1"required>  
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">Select Month</label>
					<input type="date" name='date'required="required"placeholder="Select month" class="form-control" id="exampleFormControlInput1">
				<p>*Select any date from the month.</p>
			</div> 
				<button class='btn btn-success' type="submit">Set Budget</button>
				<input type="reset" class='btn btn-danger' value="Reset">
			</form>
	
	<br><br>
			
			<form action="addExpense" method="post">
			<%
				String msg_addexp = (String)application.getAttribute("msg_addexp");
				//String msg_addexp = "Msg";
				if(msg_addexp != null){
					%>
			 	<h2 style='border-radius:10px;color:white;padding:5px;margin-right:10%;margin-left:10%;margin-top:20px;margin-bottom:20px;background-image: linear-gradient(to bottom right,#ffbfc1,#ff6368);'><%= msg_addexp %> </h2>
			 	
					<% 
					application.setAttribute("msg_addexp", null);
				}
			%>	
				<h3>Add Expense</h3>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">Amount</label>
					<input type="number" name='amount' placeholder="Enter Amount" class="form-control" id="exampleFormControlInput1"required>  
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">Description</label>
					<input type="text" name='des' placeholder="Enter description" class="form-control" id="exampleFormControlInput1"required>  
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label">Date:</label> 
					 <input type="date" id="birthdaytime" name="date"class="form-control" id="exampleFormControlInput1"required>
				</div>
				<div>
					<input type="submit" class='btn btn-success' value="Update">
					<input type="reset" class='btn btn-danger' value="Reset">
				</div>		
			</form>
</div> 
			
	
	<!-- My Dashboard -->
	<div style='padding:5px;border:5px;background-image: linear-gradient(to bottom right,#ffffff,#cbf7cc)' id='expenses'>
		<h3>My Expenses - <%= currentMonth %>/<%= year %></h3>
		
		<table class="table table-striped">
		  <%
				String msg_delExpense = (String)application.getAttribute("msg_delExpense");
				//String msg_delExpense = "Msg";
				if(msg_delExpense != null){
					%>
			 	<h2 style='border-radius:10px;color:white;padding:5px;margin-right:10%;margin-left:10%;margin-top:20px;margin-bottom:20px;background-image: linear-gradient(to bottom right,#ffbfc1,#ff6368);'><%= msg_delExpense %> </h2>
			 	
					<% 
					application.setAttribute("msg_delExpense", null);
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
		ArrayList<ArrayList<Object>> details = UserUtil.getMonthlyExpense(email, currentMonth, year);
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
		      		<input type="hidden" name='goto' value='usershome.jsp?#expenses'>
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
	<!--Footer Content-->
	<jsp:include page="footer1.jsp"></jsp:include>
</body>
</html>

<%	
}else{
	application.setAttribute("msg_login", "Please LogIn..!");
	response.sendRedirect("login.jsp");
}
%>
