import java.sql.*;
//Just left with getters and setters
//Cut and paste create/find/update methods into OfferingPersistence
public class Offering {
	private int id;
	private Course course;
	private String daysTimes;
	
	public Offering(int id, Course course, String daysTimesCsv) {
		this.id = id;
		this.course = course;
		this.daysTimes = daysTimesCsv;
	}

	public int getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public String getDaysTimes() {
		return daysTimes;
	}

	public String toString() {
		return "Offering " + getId() + ": " + getCourse() + " meeting " + getDaysTimes();
	}
}