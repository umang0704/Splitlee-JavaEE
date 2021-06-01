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
 * Servlet implementation class DeleteExpense
 */
@WebServlet("/deleteExpense")
public class DeleteExpense extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		String i = request.getParameter("expenseId");
		String gotoPage = request.getParameter("goto");
		ServletContext application = getServletContext();
		HttpSession session = request.getSession(false);
		int id = Integer.parseInt(i);
		try {
			boolean deleted = dao.UserUtil.deleteExpense(id);
			if(deleted)
				application.setAttribute("msg_delExpense", "Expense Deleted Successfully..!");
			else
				application.setAttribute("msg_delExpense", "DB Error..!");
		}catch(Exception ex) {
			ex.printStackTrace();
			application.setAttribute("msg_delExpense", "Something went wrong..!");
		} 
		response.sendRedirect(gotoPage);
	}

}
