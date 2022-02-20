package bancodb;

import static bancodb.DataBase.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	Connection connection;
	//Estableciendo conexión
	public DBConnection() {	
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Los datos de acceso están en la clase DataBase
			connection = DriverManager.getConnection(URL+DB, USER, PASSWORD);
			if(connection != null) {
				System.out.println("Se estableció la conexión :)");
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
