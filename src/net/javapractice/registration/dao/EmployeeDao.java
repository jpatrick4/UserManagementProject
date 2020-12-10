package net.javapractice.registration.dao;

// dao
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.javapractice.registration.model.Employee;

public class EmployeeDao {

	public int registerEmployee(Employee employee) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO employee" +
			" (id, first_name, last_name, username, password, address, contact) VALUES " +
				" (?, ?, ?, ?, ?, ?, ?);";
		
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?useSSL=false", "root", "dolphin");
				
		// 2 - Create statement using connection object we created.		
		PreparedStatement preparedStmt = conn.prepareStatement(INSERT_USERS_SQL)) {
			preparedStmt.setInt(1, 1);
			preparedStmt.setString(2, employee.getFirstName());
			preparedStmt.setString(3, employee.getLastName());
			preparedStmt.setString(4, employee.getUserName());
			preparedStmt.setString(5, employee.getPassword());
			preparedStmt.setString(6, employee.getAddress());
			preparedStmt.setString(7, employee.getContact());
			
			System.out.println(preparedStmt);
			// 3 - Execute or update query. Store in result as 1 if a row was changed. Otherwise store as 0.
			result = preparedStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// print int 1 or 0 depending if a row was changed or not.
		return result;
	}
}
