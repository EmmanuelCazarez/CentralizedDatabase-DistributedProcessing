package bancodb;

import static bancodb.DataBase.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	Connection connection;
	//Estableciendo conexi�n
	public DBConnection() {	
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Los datos de acceso est�n en la clase DataBase
			connection = DriverManager.getConnection(URL+DB, USER, PASSWORD);
			if(connection != null) {
				System.out.println("Se estableci� la conexi�n :)");
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
