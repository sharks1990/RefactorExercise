import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class OfferingPersistence {
	
	static String url = "jdbc:mysql://localhost:3306/refactoring";//Changed the localhost address
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
			Course course = CoursePersistence.find(result.getString("Course"));	
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

	public static void update(Offering offering) throws Exception {	
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");	
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM Offering WHERE ID=" + offering.getId() + ";");// changed it to offering.getId() method
			statement.executeUpdate("INSERT INTO Offering VALUES('" + offering.getId() + "','" + offering.getCourse().getName() + "','" + offering.getDaysTimes() + "');");// changed it to offering.getname() and getDaysTimes() method
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

}
