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
 * Servlet implementation class ChangeAccountDetail
 */
@WebServlet("/changeAccountDetail")
public class ChangeAccountDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ServletContext application = getServletContext();
		String email = (String) session.getAttribute("u_email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		try {
			boolean updated = dao.UserUtil.changeDetails(email, name, phone);
			if (updated) {
				application.setAttribute("msg_detchange", "Details updated successfully..!");
			} else {
				application.setAttribute("msg_detchange", "Something went wrong..!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			application.setAttribute("msg_detchange", "Something went wrong..!");
		}
		response.sendRedirect("profile.jsp");
	}

}
