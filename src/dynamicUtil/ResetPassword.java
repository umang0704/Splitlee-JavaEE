package dynamicUtil;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/resetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String email = request.getParameter("email");
		try {
//			//boolean emailSent=DbUtilGenric.changePasswordEmail(email, type);
//			if(emailSent) {
//				System.out.println("Email Sent");
//			}else {
//				
//			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
