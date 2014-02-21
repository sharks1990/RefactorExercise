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
	
	public int setId() {// Added setId method
	return id;
	}	

	public Course getCourse() {
		return course;
	}
	
	public Course setCourse() {// Added setCourse method
		return course;
	}

	public String getDaysTimes() {
		return daysTimes;
	}
	
	public String setDaysTimes() {// Added setDaysTimes method
		return daysTimes;
	}

	public String toString() {
		return "Offering " + getId() + ": " + getCourse() + " meeting " + getDaysTimes();
	}
}