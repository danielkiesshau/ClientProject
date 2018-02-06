package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionDB {
	private static Connection connection;
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String USER = "sa";
	private static String PASSWORD = "root";
	private static String URL = "jdbc:sqlserver://localhost;"+
	"databaseName=intern_valemobi;"+"user="+USER+";password="+PASSWORD+";";
	
	
	public ConnectionDB() {}
	
	public static synchronized Connection getConnection() {
		if(connection == null) {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL);
				
			//First catch to check if the driver load correctly	
			}catch(ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Could not load driver\n"+e);
            //Second driver to see if it could establish connection with the database
            }catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "Error when trying to establish connection with the database\n"+e);
            }
		}
	
		return connection;
	}
	
}
