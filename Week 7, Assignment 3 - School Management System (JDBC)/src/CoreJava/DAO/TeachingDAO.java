package CoreJava.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Teaching;
import CoreJava.systemsInterfaces.TeachingDAOI;

public class TeachingDAO implements TeachingDAOI {

	/**
	 * This method takes as a parameter a course_id and a 
	 * instructor_id int and perform an INSERT query into the 
	 * TEACHING table to assign an instructor to a course.
	 * @param course_id.
	 * @param instructor_id.
	 * @author RaynerMDZ.
	 */
	@Override
	public int assignInstructorToCourse (int course_id, int instructor_id) {
		
		OracleConnection oracleConnection = new OracleConnection();
		
		try {
			
			// Connecting to the data base.
			Connection connection = oracleConnection.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(queries.CHECK_IF_TEACHING.getQuery());
			preparedStatement.setInt(1, course_id);
			preparedStatement.setInt(2, instructor_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				System.out.println("Instructor already teaching this course");
				return resultSet.getInt(1);
				
			} else {
				
				preparedStatement = connection.prepareStatement(queries.ASSIGN_TO_TEACHING.getQuery());
				preparedStatement.setInt(1, course_id);
				preparedStatement.setInt(2, instructor_id);
				resultSet = preparedStatement.executeQuery();
				
                Statement statement = connection.createStatement();
                resultSet = ((java.sql.Statement) statement).executeQuery(queries.GET_TEACHING_ID.getQuery());
                resultSet.next();
                return resultSet.getInt(1);
				
			}
			
		} catch (Exception e) {
			System.out.println("Cannot add.");
		}
		
		return -1;	
	}
	

	/**
	 * This method takes no parameters and queries the database 
	 * for every instructor assigned to a course.
	 * @author RaynerMDZ.
	 */
	@Override
	public List<Teaching> getInstructorsCourses() {
		
		List<Teaching> teachings = new ArrayList<>();
		
		OracleConnection oracleConnection = new OracleConnection();
		Connection connection;
		
		try {	
			connection = oracleConnection.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(queries.GET_INSTRUCTRORS.getQuery());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				teachings.add(new Teaching(resultSet.getString(1), resultSet.getDouble(2), resultSet.getString(3), resultSet.getString(4)));
			}
			return teachings;
			
		} catch (Exception e) {
			System.out.println("Instructors not found!");
		}
		
		return teachings;
	}

}
