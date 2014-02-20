import java.util.*;
import java.sql.*;

public class Schedule {

	String name;
	
	int credits = 0;
	static final int minCredits = 12;
	static final int maxCredits = 18;
	boolean additionalCreditsPermission = false;// Changed name

	ArrayList<Offering> offerings = new ArrayList<Offering>(); 
   // named the array list to offering
	

	public Schedule(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;// Added getName method
	}

	public void addOffering(Offering offering) {
		credits += offering.getCourse().getCredits();
		offerings.add(offering);
	}

	public void permitExtraCredits(boolean allow) {
		additionalCreditsPermission = allow;
	}

	public List<String> analysis() {// Changed to list.... Items can be added and removed
		ArrayList<String> result = new ArrayList<String>();
		if (credits < minCredits)
			result.add("Too few credits");
		if (credits > maxCredits && !additionalCreditsPermission)
			result.add("Too many credits");
		checkDuplicateCourses(result);
		checkOverlap(result);
		return result;
	}

	public void checkDuplicateCourses(ArrayList<String> analysis) {
		List<Course> courses = new ArrayList<Course>();//changed to arraylist
		for (Offering offering : offerings) {
			Course course = offering.getCourse();
			if (courses.contains(course))
				analysis.add("Same course twice - " + course.getName());
			courses.add(course);
		}
	}

	public void checkOverlap(ArrayList<String> analysis) {
		List<String> times = new ArrayList<String>();
		for (Offering offering : offerings) {
			StringTokenizer tokens = new StringTokenizer(offering.getDaysTimes(), ",");
			
			while (tokens.hasMoreTokens()) {
				String dayTime = tokens.nextToken();
				if (times.contains(dayTime))
					analysis.add("Course overlap - " + dayTime);
				times.add(dayTime);
			}
		}
	}

	public String toString() {
		return "Schedule " + name + ": " + offerings;
	}
}