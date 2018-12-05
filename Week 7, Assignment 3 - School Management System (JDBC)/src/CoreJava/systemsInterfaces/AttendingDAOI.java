package CoreJava.systemsInterfaces;

import java.util.List;

import CoreJava.Models.Attending;
import CoreJava.Models.Course;
import CoreJava.Models.Student;

public interface AttendingDAOI {
	
	enum queries {
		
		CHECK_IF_ATTENDING("SELECT * FROM attending WHERE course_id = ? AND student_id = ?"),
        ADD_STUDENT_TO_ATTENDING("INSERT INTO attending (course_id, student_id)"
                + "VALUES (?, ?)"),
        GET_ATTENDING_ID("SELECT MAX(attending_id) FROM attending"),
        GET_STUDENT_COURSES("SELECT course.course_name, instructor.full_name, instructor.email FROM course \r\n" +
                "JOIN teaching ON course.course_id = teaching.course_id\r\n" +
                "JOIN attending ON attending.course_id =  course.course_id\r\n" +
                "JOIN instructor ON teaching.instructor_id = instructor.instructor_id WHERE student_id = ?");
		
		private final String query;

		private queries(String query) {
			this.query = query;
		}

		public String getQuery() {
			return this.query;
		}

	}
	
	public int registerStudentToCourse(Student student, Course course) throws Exception;
	
	public List<Attending> getStudentCourse(int student_id);
}
