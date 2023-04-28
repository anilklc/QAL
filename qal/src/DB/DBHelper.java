package DB;
import java.sql.*;
public class DBHelper {
	private String url="jdbc:mysql://localhost:3306/qal";
	private String userName="root";
	private String password="12345";
	public Connection connection=null;
	public Connection connectOpen() {
		try {
			connection=DriverManager.getConnection(url,userName,password);
		}catch(Exception expection){
			
				System.out.println(expection.getMessage());
		}
		return connection;
	}
	public void connectClose() {
		
        try {
        	connection.close();
			
		}catch(Exception expection){
			
				System.out.println(expection.getMessage());
		}
	}
	
}
