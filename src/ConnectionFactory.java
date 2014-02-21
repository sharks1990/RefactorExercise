//Ronan Sharkey C10303937 DT354 
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	String driverName = "com.mysql.jdbc.Driver";
	String conUrl = "jdbc:mysql://localhost:3306/refactoring";
	String dbUser = "root";//username
	String dbPwd = "root";//password
	

	
	ConnectionFactory() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection()  {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(conUrl, dbUser , dbPwd);
			
			} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/*public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}*/
}