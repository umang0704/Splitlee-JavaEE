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
 * Servlet implementation class SetBudget
 */
@WebServlet("/setBudget")
public class SetBudget extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String date = request.getParameter("date");
		java.util.HashMap<Character, Integer> dates = dynamicUtil.DateTime.getIntDate(date);
		ServletContext application = getServletContext();
		HttpSession session = request.getSession(false);
		String u_email = (String) session.getAttribute("u_email");
		String b = request.getParameter("budget");
		Double budget = Double.parseDouble(b);
		System.out.println(budget);
		Integer month = dates.get('m');
		try {
			boolean setBudget = dao.UserUtil.setBudget(u_email, month, budget);
			if (setBudget) {
				application.setAttribute("msg_setbudget", "Budget set to " + budget + " for "+month+"-"+dates.get('y')+"..!");
			} else {
				application.setAttribute("msg_setbudget", "Error:Something went wrong..DB issue will resolve sooner!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			application.setAttribute("msg_setbudget", "Something went wrong..!");
		}
		response.sendRedirect("usershome.jsp?#setbudget");
	}

}
