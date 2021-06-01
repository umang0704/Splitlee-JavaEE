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
 * Servlet implementation class ChangePassword
 */
@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("u_email");
		String oldPass = request.getParameter("oldpassword");
		String oldPassCheck = null;
		ServletContext application = getServletContext();
		try {
			oldPassCheck = dao.UserUtil.getPassword(email);
		} catch (Exception ex) {
			ex.printStackTrace();
			// send to profile.jsp with something wen wrong
			application.setAttribute("msg_passchange", "Something went wrong..!");
			response.sendRedirect("profile.jsp");
		}
		if (oldPass.equals(oldPassCheck)) {
			try {
				// change password and send to profile.jsp with success message
				String newpass = request.getParameter("newpassword");
				String confnewpassword = request.getParameter("confnewpassword");
				if (confnewpassword.equals(newpass)) {
					boolean changed = dao.UserUtil.changePassword(email, newpass);
					if (changed) {
						application.setAttribute("msg_passchange", "Password changed successfully..!");
						response.sendRedirect("profile.jsp");
					} else {
						application.setAttribute("msg_passchange", "Something went wrong..!");
						response.sendRedirect("profile.jsp");
					}
				} else {
					application.setAttribute("msg_passchange", "New password confirmation invalid..!");
					response.sendRedirect("profile.jsp");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				// send to profile.jsp with something wen wrong
				application.setAttribute("msg_passchange", "Something went wrong..!");
				response.sendRedirect("profile.jsp");
			}
		} else {
			// send to profile.jsp with wrong old pas
			application.setAttribute("msg_passchange", "Old Password is wrong..!");
			response.sendRedirect("profile.jsp");
		}
	}

}
