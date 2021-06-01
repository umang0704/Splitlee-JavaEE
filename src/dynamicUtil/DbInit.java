package dynamicUtil;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.*;
import javax.servlet.http.*;

public class DbInit implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext sc = sce.getServletContext();
			String dbdriver = "com.mysql.cj.jdbc.Driver";
			String dbpath = "jdbc:mysql://localhost:3306/yourdocplus_db?useSSL=false&allowPublicKeyRetrieval=true";
			String dbid = "root";
			String dbpass = "Umang@123";
			Class.forName(dbdriver);
			Connection connection = DriverManager.getConnection(dbpath, dbid, dbpass);
			sc.setAttribute("connection", connection);
			System.out.println("Connection Connected");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ServletContext sc = sce.getServletContext();
			Connection connection = (Connection) sc.getAttribute("connection");
			connection.close();
			System.out.println("Connection Closed");
		} catch (Exception e) {

		}
	}
}