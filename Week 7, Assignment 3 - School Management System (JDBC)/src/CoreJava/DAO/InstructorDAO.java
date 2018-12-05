package CoreJava.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Instructor;
import CoreJava.systemsInterfaces.InstructorDAOI;

public class InstructorDAO implements InstructorDAOI {

	/**
	 * This method takes no parameter and returns every Instructor in the database.
	 * @author RaynerMDZ.
	 */
	@Override
	public List<Instructor> getAllInstructors() {
		
		OracleConnection oracleConnection = new OracleConnection();
		
		List<Instructor> instructorList = new ArrayList<>();
		
		try {
			
			Connection connection = oracleConnection.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(queries.SELECT_QUERY.getQuery());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Instructor instructor = new Instructor();
				
				instructor.setInstructor_id(resultSet.getInt(1));
				instructor.setFull_name(resultSet.getString(2));
				instructor.setEmail(resultSet.getString(3));
				instructor.setSpeciality(resultSet.getString(4));
				instructor.setAdmin_role(resultSet.getInt(5));
				instructor.setPass(resultSet.getString(6));
	
				instructorList.add(instructor);
			}
			
			return instructorList;
		
		} catch (Exception e) {
			System.out.println("Student not found!");
			return null;
		}
	}

	/**
	 * This method takes a String as a parameter and queries the database for an 
	 * Instructor with such an email and returns an Instructor Object.
	 * @author RaynerMDZ.
	 */
	@Override
	public Instructor getInstructorByGmail(String email) {
		
		OracleConnection oracleConnection = new OracleConnection();
		
		Instructor instructor;
		
		try {
			
			Connection connection = oracleConnection.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(queries.SELECT_QUERY_BY_EMAIl.getQuery());
			preparedStatement.setString(1, email);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				instructor = new Instructor();
				
				instructor.setInstructor_id(resultSet.getInt(1));
				instructor.setFull_name(resultSet.getString(2));
				instructor.setEmail(resultSet.getString(3));
				instructor.setSpeciality(resultSet.getString(4));
				instructor.setAdmin_role(resultSet.getInt(5));
				instructor.setPass(resultSet.getString(6));
	
				return instructor;
			}
			
		} catch (Exception e) {
			System.out.println("Instructor not found!");
		}
		
		return null;
		
	}
	
	/**
	 * This method takes two arguments: an instructor object with all its 
	 * information and a String which represent the password entered by the 
	 * user trying to login as an instructor. This returns “Wrong Credentials”, “Admin” 
	 * or “Instructor”.
	 * @param instructor
	 * @param comparablePass
	 * @return
	 * @author RaynerMDZ.
	 */
	public String validateUser(Instructor instructor, String comparablePass) {
		
		if (instructor.getPass().equals(comparablePass) && instructor.getAdmin_role() == 1) {
			return "Admin";	
		} 
		
		if (instructor.getPass().equals(comparablePass)) {
			return "Instructor";
			
		} 

		return "Wrong Credentials";	
	}

}
