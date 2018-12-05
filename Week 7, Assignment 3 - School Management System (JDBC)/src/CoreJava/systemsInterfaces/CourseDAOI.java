package CoreJava.systemsInterfaces;

import java.util.List;

import CoreJava.Models.Course;

public interface CourseDAOI {
	
	enum queries {
		
		SELECT_QUERY("SELECT * FROM course"),
		SELECT_QUERY_BY_ID("SELECT course.COURSE_ID, course.COURSE_NAME, course.MINIMUN_GPA FROM course INNER JOIN teaching ON\r\n" +
                "course.course_id = teaching .course_id WHERE instructor_id = ?");
		
		private final String query;

		private queries(String query) {
			this.query = query;
		}

		public String getQuery() {
			return this.query;
		}

	}
	
	public List<Course> getAllCourses();
	
	public List<Course> getCourseByInstructor(int instructor_id);
}
