import java.sql.Connection;
import java.sql.DriverManager;


public class getConnection {
	
	static String url = "jdbc:mysql://localhost:3306/refactoring";//Changed the localhost address
	static { 
		Connection conn = null;
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "root");			
			}
		catch (Exception ignored) {} 
		
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

}
