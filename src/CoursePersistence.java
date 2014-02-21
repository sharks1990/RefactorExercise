 import java.sql.*;
//Created a new class and called it Course Persistence 

public class CoursePersistence { 
	private static String name;
	private static int credits;
	private static ConnectionFactory connectionFactory = new ConnectionFactory();
	
	public static Course create(String name, int credits) throws Exception {
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM course WHERE name = '" + name + "';");
			statement.executeUpdate("INSERT INTO course (name, credits) VALUES ('" + name + "', '" + credits + "');");//Added in values for course of names and credits
			return new Course(name, credits);
		} 
		finally {
			try { 
				connection.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public static Course find(String name) {
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM course WHERE Name = '" + name + "';");
			if (!result.next()) return null;
			int credits = result.getInt("Credits");
			return new Course (name, credits);
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

	public static void update(Course course) throws Exception {
		Connection connection = null;
		try {
			connection = connectionFactory.getConnection();//new way of connection
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM COURSE WHERE name = '" + course.getName() + "';");
			statement.executeUpdate("INSERT INTO course (name, credits) VALUES('" + course.getName() + "','" + course.getCredits() + "');");//getting errors here
			} 
		finally {
			try { 
				connection.close(); 
			} 
			catch (Exception ignored) {}
		}
	}
}