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
 * Servlet implementation class AddExpense
 */
@WebServlet("/addExpense")
public class AddExpense extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String d = request.getParameter("date");
		String des = request.getParameter("des");
		String a =request.getParameter("amount");
		HashMap<Character,Integer> date = dynamicUtil.DateTime.getIntDate(d);
		Double amount = Double.parseDouble(a);
		HttpSession session = request.getSession();
		ServletContext application =  getServletContext();
		String email = (String)session.getAttribute("u_email");
		try {
			boolean added = dao.UserUtil.addExpenses(email,date.get('d'), date.get('m'),date.get('y'), amount, des);
			if(added)
				application.setAttribute("msg_addexp", des+" added successfully on "+d+" for Amount: "+a+"..!");
			else
				application.setAttribute("msg_addexp", "DB Error..!");
		}catch(Exception ex) {
			application.setAttribute("msg_addexp", "Something went wrong while adding..!");
			ex.printStackTrace();
		}
		response.sendRedirect("usershome.jsp");
	}

}
