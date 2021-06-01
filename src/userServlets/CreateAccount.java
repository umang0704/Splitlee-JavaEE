package userServlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UserUtil;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/createAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone"); 
		String pass = request.getParameter("pass");
		String confpass = request.getParameter("confpass");
		ServletContext application = getServletContext();

		if (pass.equals(confpass)) {
			try {
				boolean created = UserUtil.createAccount(name, pass, email, phone);
				if (created) {
					// send to sign up with success message
					String body = "<html>"
							+ "	<head></head>"
							+ "<body>"
							+ "<h1>Welcome to the family,"+name+"</h1>"
							+ "<hr>"
							+ "You have been registered to Splitlee with following details<br><br>"
							+ "<b>Name:</b>"+email+"<br><br>"
							+ "<b>Phone:</b>"+phone
							+ "<hr>"
							+ "</body>"
							+ "</html>";
					dynamicUtil.Send_email.sendEmail(email, "Splitlee | Account Creation", body);
					application.setAttribute("msg_signup", "Account created Successfully..!");
					response.sendRedirect("signup.jsp");
				} else {
					// send to sign up page is error message
					application.setAttribute("msg_signup", "Account was not created..!");
					response.sendRedirect("signup.jsp");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				application.setAttribute("msg_signup", "Something went wrong..!");
				response.sendRedirect("signup.jsp");
			}
		} else {
			// send to signup page withpass error
			application.setAttribute("msg_signup", "Password does not match..!");
			response.sendRedirect("signup.jsp");
		}
	}

}
