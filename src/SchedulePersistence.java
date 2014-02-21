//Ronan Sharkey C10303937 DT354 
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//Methods from Schedule have being copied into here
public class SchedulePersistence {
		private static ConnectionFactory connectionFactory = new ConnectionFactory();
	
	public static void deleteAll() throws Exception {
		Connection connection = null;
		try {
			
			connection = connectionFactory.getConnection();//new way of connection
			System.out.println(connection.toString());
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM schedule;");
		} 
		finally {
			try { 
				connection.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public static Schedule create(String name) throws Exception {
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM schedule WHERE name = '" + name + "';");
			return new Schedule(name);
		} 
		finally {
			try { 
				connection.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public static Schedule find(String name) {
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM schedule WHERE Name= '" + name + "';");
			Schedule schedule = new Schedule(name);
			while (result.next()) {
				Offering offering = OfferingPersistence.find(result.getInt("OfferingId"));	//Changed name so it looks in OfferingPersistence instead of Offering
				schedule.offerings.add(offering);
			}
			return schedule;
		} 
		catch (Exception ex) {
			return null;
		} 
		finally {
			try { 
				connection.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public static List<Schedule> all() throws Exception {
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT DISTINCT Name FROM schedule;");
			while (results.next())
			result.add(SchedulePersistence.find(results.getString("Name")));//Changed to SchedulePersistence-- New name
			//changed Schedule name
		} 
		finally {
			try { 
				connection.close(); 
			} 
			catch (Exception ignored) {}
		}
		return result;
	}

	public static void update(Schedule schedule) throws Exception { //Change to static
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM schedule WHERE name = '" + schedule.getName() + "';");// Changed to getName()
			for (int i = 0; i < schedule.offerings.size(); i++) {//array list
				Offering offering = (Offering) schedule.offerings.get(i);//array added--offerings
				statement.executeUpdate("INSERT INTO schedule (name, offeringId) VALUES('" + schedule.name + "','" + offering.getId() + "');");
			}
		} 
		finally {
			try { 
				connection.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

}
