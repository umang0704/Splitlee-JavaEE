package userServlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		HttpSession session = request.getSession(false);
		ServletContext application = getServletContext();
		if(session!=null) {
			application.setAttribute("msg_login", "Logged out successfully..!");
		}else {

			application.setAttribute("msg_login", "Please LogIn First..!");
		}
		session.invalidate();
		response.sendRedirect("login.jsp");
	}

}
