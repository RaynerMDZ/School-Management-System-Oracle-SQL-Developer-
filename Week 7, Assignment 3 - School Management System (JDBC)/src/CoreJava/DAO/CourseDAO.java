package CoreJava.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Course;
import CoreJava.systemsInterfaces.CourseDAOI;

public class CourseDAO implements CourseDAOI {

	/**
	 * This method takes no parameter and returns every Course in the database.
	 * @author RaynerMDZ.
	 */
	@Override
	public List<Course> getAllCourses() {

		OracleConnection oracleConnection = new OracleConnection();
		
		List<Course> courseList = new ArrayList<>();
		
		try {
			
			Connection connection = oracleConnection.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(queries.SELECT_QUERY.getQuery());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Course course = new Course();
				
				course.setCourse_id(resultSet.getInt(1));
				course.setCourse_name(resultSet.getString(2));
				course.setMinimum_gpa(resultSet.getDouble(3));
				
				courseList.add(course);
	
			}
			
			return courseList;
		
		} catch (Exception e) {
			System.out.println("Student not found!");
			return null;
		}
	}

	/**
	 * This method takes an int as a parameter and queries the database 
	 * for all the courses one instructor is assigned to.
	 * @param instructor_id.
	 * @author RaynerMDZ.
	 */
	@Override
	public List<Course> getCourseByInstructor(int instructor_id) {
		
		OracleConnection oracleConnection = new OracleConnection();
		
		List<Course> courseList = new ArrayList<>();
		
		try {
			
			Connection connection = oracleConnection.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(queries.SELECT_QUERY_BY_ID.getQuery());
			preparedStatement.setInt(1, instructor_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Course course = new Course();
				
				course.setCourse_id(resultSet.getInt(1));
				course.setCourse_name(resultSet.getString(2));
				course.setMinimum_gpa(resultSet.getDouble(3));
				
				courseList.add(course);
			}
			
			return courseList;
		
		} catch (Exception e) {
			System.out.println("Student not found!");
			return null;
		}
	}

}
