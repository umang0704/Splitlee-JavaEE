package dao;

import java.sql.*;
import java.util.*;

public class UserUtil {

	/**
	 * Creates the user account
	 * 
	 * @param name
	 * @param password
	 * @param email
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public static boolean createAccount(String name, String password, String email, String phone) throws Exception {
		boolean created = false;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement(
				"INSERT INTO `splitlee_db`.`user` (`email`, `password`, `name`, `phone`) VALUES (?,?,?,?);");
		pst.setString(1, email);
		pst.setString(2, password);
		pst.setString(3, name);
		pst.setString(4, phone);

		int res = pst.executeUpdate();

		if (res == 1)
			created = true;

		return created;
	}

	/**
	 * 
	 * @param email
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, String> loginAuth(String email, String pass) throws Exception {
		HashMap<String, String> ret = null;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM `user` WHERE `email`= ? AND `password`=?;");
		pst.setString(1, email);
		pst.setString(2, pass);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			ret = new HashMap<String, String>();
			ret.put("name", rs.getString("name"));
			ret.put("email", rs.getString("email"));
			ret.put("phone", rs.getString("phone"));
		}
		return ret;
	}

	/**
	 * fetches the password for the email
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public static String getPassword(String email) throws Exception {
		String pass = null;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement("SELECT `password` FROM `user` WHERE `email`= ?;");
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		if (rs.next())
			pass = rs.getString("password");
		return pass;
	}

	/**
	 * Changes the password
	 * 
	 * @param email
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public static boolean changePassword(String email, String pass) throws Exception {
		boolean updated = false;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn
				.prepareStatement("UPDATE `splitlee_db`.`user` SET `password` = ? WHERE (`email` = ?);");
		pst.setString(1, pass);
		pst.setString(2, email);
		int e = pst.executeUpdate();
		if (e == 1)
			updated = true;
		return updated;
	}

	/**
	 * Changes account details using email
	 * 
	 * @param email
	 * @param name
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public static boolean changeDetails(String email, String name, String phone) throws Exception {
		boolean updated = false;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn
				.prepareStatement("UPDATE `splitlee_db`.`user` SET  `name` = ?, `phone` = ? WHERE (`email` = ?);");
		pst.setString(1, name);
		pst.setString(2, phone);
		pst.setString(3, email);
		int e = pst.executeUpdate();
		if (e == 1)
			updated = true;
		return updated;
	}

	/**
	 * gets the budget id for the user using email and month
	 * 
	 * @param email
	 * @param month
	 * @return
	 * @throws SQLException
	 */
	public static Integer budgetExists(String email, int month) throws SQLException {
		Integer budgetId = null;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM `budgets` WHERE `userid`= ? and `month`=?;");
		pst.setString(1, email);
		pst.setInt(2, month);
		ResultSet rs = pst.executeQuery();
		if (rs.next())
			budgetId = rs.getInt("id");
		return budgetId;
	}

	/**
	 * sets the budget for a user
	 * 
	 * @param email
	 * @param month
	 * @param budget
	 * @return
	 */
	public static boolean setBudget(String email, Integer month, Double budget) throws SQLException {
		boolean set = false;
		Connection conn = dao.DbConnect.getConnection();
		Integer budgetId = budgetExists(email, month);
		if (budgetId == null) {
			// insert new budget tuple
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO `splitlee_db`.`budgets` (`userid`, `month`, `budget`,`leftbudget`) VALUES (?, ?, ?,?);");
			pst.setString(1, email);
			pst.setInt(2, month);
			pst.setDouble(3, budget);
			pst.setDouble(4, budget);
			int e = pst.executeUpdate();
			if (e == 1)
				set = true;
		} else {
			// update old budget tuple
			PreparedStatement pst = conn.prepareStatement(
					"UPDATE `splitlee_db`.`budgets` SET `budget` = ? , `leftBudget` = ?  WHERE (`id` = ?);");
			pst.setDouble(1, budget);
			pst.setDouble(2, budget);
			pst.setInt(3, budgetId);
			int e = pst.executeUpdate();
			if (e == 1)
				set = true;
		}
		return set;
	}

	/**
	 * get the budget and left budget from the month using email and month.
	 * 
	 * @param email
	 * @param month
	 * @return
	 * @throws SQLException
	 */
	public static Double[] getBudgetUpdates(String email, int month) throws SQLException {
		Double[] budgets = null;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM `budgets` WHERE `userid`= ? and `month`=?;");
		pst.setString(1, email);
		pst.setInt(2, month);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			budgets = new Double[2];
			// left budget
			budgets[0] = rs.getDouble("leftbudget");
			// set budget
			budgets[1] = rs.getDouble("budget");
		}
		return budgets;
	}

	/**
	 * adds the expense for the user.
	 * 
	 * @param email
	 * @param day
	 * @param month
	 * @param year
	 * @param amount
	 * @return
	 * @throws SQLException
	 */
	public static boolean addExpenses(String email, int day, int month, int year, Double amount, String des)
			throws SQLException {
		boolean added = false;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement(
				"INSERT INTO `splitlee_db`.`expense` (`day`, `month`, `year`, `email`, `amount`,`des`) VALUES (?, ?, ?, ?, ?,?);");
		pst.setInt(1, day);
		pst.setInt(2, month);
		pst.setInt(3, year);
		pst.setString(4, email);
		pst.setDouble(5, amount);
		pst.setString(6, des);
		int e = pst.executeUpdate();
		if (e == 1)
			added = true;
		return added;
	}

	/**
	 * fetches the sum of expenses for the month
	 * 
	 * @param email
	 * @param month
	 * @param year
	 * @return
	 * @throws SQLException
	 */
	public static Double sumForMonth(String email, int month, int year) throws SQLException {
		Double sum = 0.0;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement(
				"SELECT * FROM `splitlee_db`.`expense` WHERE `email`=? and `month`=? and `year` = ?;");
		pst.setString(1, email);
		pst.setInt(2, month);
		pst.setInt(3, year);

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			sum = sum + rs.getDouble("amount");
		}
		return sum;
	}

	/**
	 * fetches data for the month
	 * 
	 * @param email
	 * @param month
	 * @param year
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<ArrayList<Object>> getMonthlyExpense(String email, int month, int year)
			throws SQLException {
		ArrayList<ArrayList<Object>> ret = new ArrayList<ArrayList<Object>>();
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement(
				"SELECT * FROM `splitlee_db`.`expense` WHERE `email`=? and `month`=? and `year` = ? ORDER BY `day` ASC;");
		pst.setString(1, email);
		pst.setInt(2, month);
		pst.setInt(3, year);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			ArrayList<Object> temp = new ArrayList<Object>();
			int d = rs.getInt("day");
			int m = rs.getInt("month");
			int y = rs.getInt("year");
			String date = d + "-" + m + "-" + y;
			temp.add(date);
			temp.add(rs.getString("des"));
			temp.add(rs.getDouble("amount"));
			temp.add(rs.getInt("id"));
			ret.add(temp);
		}

		return ret;
	}

	/**
	 * deletes the expense using id
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static boolean deleteExpense(int id) throws SQLException {
		boolean deleted = false;
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement("DELETE FROM `splitlee_db`.`expense` WHERE (`id` = ?);");
		pst.setInt(1, id);
		int e = pst.executeUpdate();
		if (e == 1)
			deleted = true;
		return deleted;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<ArrayList<Object>> getAllExpenses(String email)
			throws SQLException {
		ArrayList<ArrayList<Object>> ret = new ArrayList<ArrayList<Object>>();
		Connection conn = dao.DbConnect.getConnection();
		PreparedStatement pst = conn.prepareStatement(
				"SELECT * FROM `splitlee_db`.`expense` WHERE `email`=? ORDER BY `day` ASC;");
		pst.setString(1, email); 
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			ArrayList<Object> temp = new ArrayList<Object>();
			int d = rs.getInt("day");
			int m = rs.getInt("month");
			int y = rs.getInt("year");
			String date = d + "-" + m + "-" + y;
			temp.add(date);
			temp.add(rs.getString("des"));
			temp.add(rs.getDouble("amount"));
			temp.add(rs.getInt("id"));
			ret.add(temp);
		}

		return ret;
	}
}
