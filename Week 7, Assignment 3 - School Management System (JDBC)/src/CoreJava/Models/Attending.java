package CoreJava.Models;

public class Attending {
	
	// Class fields.
	private String course_name, full_name, email;
	
	// Default constructor.
	public Attending () {
		
		this.course_name = "";
		this.full_name = "";
		this.email = "";
	}
	
	// Constructor.
	public Attending (String course_name, String full_name, String email) {
		
		this.course_name = course_name;
		this.full_name = full_name;
		this.email = email;
	}
	
	// Getters and Setters.
	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
