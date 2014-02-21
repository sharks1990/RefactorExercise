import java.sql.*;
//Just left with getters and setters
//Cut and paste create/find/update methods into CoursePersistence
public class Course {
	private String name;
	private int credits;

	public Course(String name, int credits) {
		this.name = name;
		this.credits = credits;
	}

	public int getCredits() {
		return credits;
	}

	public String getName() {
		return name;
	}
	
	public int setCredits() {// Added setCredits method
		return credits;
	}

	public String setName() {// Added setName method
		return name;
	}
}