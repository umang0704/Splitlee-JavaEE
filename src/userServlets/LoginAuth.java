package userServlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginAuth
 */
@WebServlet("/loginAuth")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		HttpSession session = request.getSession();
		ServletContext application = getServletContext();
		try {
			HashMap<String, String> auth = dao.UserUtil.loginAuth(email, pass);
			if (!auth.isEmpty()) {
				// go to user-home page
				session.setAttribute("u_name", (String) auth.get("name"));
				session.setAttribute("u_email", (String) auth.get("email"));
				session.setAttribute("u_phone", (String) auth.get("phone"));

				response.sendRedirect("usershome.jsp");
			} else {
				// auth error, go to login page
				application.setAttribute("msg_login", "Invalid Credentials..!");
				response.sendRedirect("login.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
