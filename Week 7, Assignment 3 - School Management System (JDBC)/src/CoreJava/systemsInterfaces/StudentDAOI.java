package CoreJava.systemsInterfaces;

import CoreJava.Models.Student;

public interface StudentDAOI {
	
	enum queries {
		
		SELECT_QUERY_BY_EMAIL("SELECT * FROM student WHERE email = ?");
		
		private final String query;

		private queries(String query) {
			this.query = query;
		}

		public String getQuery() {
			return this.query;
		}

	}
	
	public Student getStudentByGmail(String email);

}
