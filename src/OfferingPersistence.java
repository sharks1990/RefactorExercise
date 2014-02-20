import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class OfferingPersistence {
	
	static String url = "jdbc:mysql://localhost:3306/refactoring";
	static { 
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
			}
		catch (Exception ignored) {} 
	}

	public static Offering create(Course course, String daysTimesCsv) throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT MAX(ID) FROM offering;");
			result.next();
			int newId = 1 + result.getInt(1);
			statement.executeUpdate("INSERT INTO offering VALUES ('"+ newId + "','" + course.getName() + "','" + daysTimesCsv + "');");
			return new Offering(newId, course, daysTimesCsv);
		} 
		finally {
			try { 
				conn.close(); 
				} 
			catch (Exception ignored) {}
		}
	}

	public static Offering find(int id) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM offering WHERE id =" + id + ";");
			if (result.next() == false)
				return null;
			String courseName = result.getString("name");
			Course course = CoursePersistence.find(courseName);
			String dateTime = result.getString("DaysTimes");
			conn.close();
			return new Offering(id, course, dateTime);
		} 
		catch (Exception ex) {
			try { 
				conn.close(); 
			} catch (Exception ignored) {}
			return null;
		}
	}

	public void update() throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM Offering WHERE ID=" + offering.getId + ";");
			statement.executeUpdate("INSERT INTO Offering VALUES('" + newid + "','" + course.getName() + "','" + daysTimes + "');");
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

}
