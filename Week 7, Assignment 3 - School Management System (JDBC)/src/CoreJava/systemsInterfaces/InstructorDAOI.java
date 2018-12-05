package CoreJava.systemsInterfaces;

import CoreJava.Models.Instructor;
import java.util.List;

public interface InstructorDAOI {
	
	enum queries {
		
		SELECT_QUERY("SELECT * FROM instructor"),
		SELECT_QUERY_BY_EMAIl("SELECT * FROM instructor WHERE email = ?");
		
		private final String query;

		private queries(String query) {
			this.query = query;
		}

		public String getQuery() {
			return this.query;
		}

	}

	public List<Instructor> getAllInstructors();
	
	public Instructor getInstructorByGmail(String email);
}
