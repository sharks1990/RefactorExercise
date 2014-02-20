import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


public class SchedulePersistence {
	
	static String url = "jdbc:mysql://localhost:3306/refactoring";
	static { 
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
			}
		catch (Exception ignored) {} 
	}

	public static void deleteAll() throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM schedule;");
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public static Schedule create(String name) throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM schedule WHERE name = '" + name + "';");
			return new Schedule(name);
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public static Schedule find(String name) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM schedule WHERE Name= '" + name + "';");
			Schedule schedule = new Schedule(name);
			while (result.next()) {
				int offeringId = result.getInt("OfferingId");
				Offering offering = OfferingPersistence.find(offeringId);
				schedule.add(offering);
			}
			return schedule;
		} 
		catch (Exception ex) {
			return null;
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public static Collection<Schedule> all() throws Exception {
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery("SELECT DISTINCT Name FROM schedule;");
			while (results.next())
			result.add(Schedule.find(results.getString("Name")));
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
		return result;
	}

	public void update() throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM schedule WHERE name = '" + schedule.getName + "';");
			for (int i = 0; i < schedule.size(); i++) {
				Offering offering = (Offering) schedule.get(i);
				statement.executeUpdate("INSERT INTO schedule (name, offeringId) VALUES('" + name + "','" + offering.getId() + "');");
			}
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

}
