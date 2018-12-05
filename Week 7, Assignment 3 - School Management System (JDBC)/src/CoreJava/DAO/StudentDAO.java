package CoreJava.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import CoreJava.Models.Student;
import CoreJava.systemsInterfaces.StudentDAOI;

public class StudentDAO implements StudentDAOI {

	/**
	 * This method takes a String as a parameter and queries the database 
	 * for an Student with such an email and returns a Student Object.
	 * @param String email.
	 * @return Student object.
	 * @author RaynerMDZ.
	 */
	@Override
	public Student getStudentByGmail(String email) {
		
		OracleConnection oracleConnection = new OracleConnection();
		Student student;
		Connection connection;
		
		try {
			
			connection = oracleConnection.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(queries.SELECT_QUERY_BY_EMAIL.getQuery());
			preparedStatement.setString(1, email);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				student = new Student();
				
				student.setStudent_id(resultSet.getInt(1));
				student.setFull_name(resultSet.getString(2));
				student.setEmail(resultSet.getString(3));
				student.setGpa(resultSet.getInt(4));
				student.setPass(resultSet.getString(5));
				student.setStudent_role(resultSet.getInt(6));
				
				return student;
			}
			
			return null;
			
		} catch (Exception e) {
			System.out.println("Student not found!");
			return null;	
		}
		
		
	}
	
	/**
	 * This method takes two parameters: the first one is the password 
	 * from the database and the second one is the password from the user input. 
	 * If both passwords are the same return true otherwise return false.
	 * @param passToValidate.
	 * @param comparablePass.
	 * @return boolean.
	 * @author RaynerMDZ.
	 */
	public boolean validateUser(String passToValidate, String comparablePass) {
		
		if (passToValidate.equalsIgnoreCase(comparablePass)) {
			return true;
		}
		return false;
	}
	
}
