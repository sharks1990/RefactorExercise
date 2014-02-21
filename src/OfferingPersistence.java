//Ronan Sharkey C10303937 DT354 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class OfferingPersistence {
	private static ConnectionFactory connectionFactory = new ConnectionFactory();
	
	public static Offering create(Course course, String daysTimesCsv) throws Exception {
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT MAX(ID) FROM offering;");
			result.next();
			int newId = 1 + result.getInt(1);
			statement.executeUpdate("INSERT INTO offering VALUES ('"+ newId + "','" + course.getName() + "','" + daysTimesCsv + "');");
			return new Offering(newId, course, daysTimesCsv);
		} 
		finally {
			try { 
				connection.close(); 
				} 
			catch (Exception ignored) {}
		}
	}

	public static Offering find(int id) {
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM offering WHERE id =" + id + ";");
			if (result.next() == false)
				return null;
			Course course = CoursePersistence.find(result.getString("name"));	//Changed to name and changed course to CoursePersistence
			String dateTime = result.getString("DaysTimes");
			connection.close();
			return new Offering(id, course, dateTime);
		} 
		catch (Exception ex) {
			try { 
				connection.close(); 
			} catch (Exception ignored) {}
			return null;
		}
	}

	public static void update(Offering offering) throws Exception {	
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM Offering WHERE ID=" + offering.getId() + ";");// changed it to offering.getId() method
			statement.executeUpdate("INSERT INTO Offering VALUES('" + offering.getId() + "','" + offering.getCourse().getName() + "','" + offering.getDaysTimes() + "');");// changed it to offering.getname() and getDaysTimes() method
		} 
		finally {
			try { 
				connection.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

}
