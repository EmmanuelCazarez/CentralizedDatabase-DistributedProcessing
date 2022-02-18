package bancodb;

import java.sql.*;
import static bancodb.DataBase.*;

public class Cheques extends DBConnection{
	DBConnection con = new DBConnection();
	Statement st;
	ResultSet rs;
	
	//Leyendo todos los datos de la tabla cheques
	public void read() {	
		try {
			st = con.connection.createStatement();
			rs = st.executeQuery("select * from "+TCHEQUES);
			while (rs.next()) {
				System.out.println
				(
					rs.getString(TNOCUENTA)+" "+
					Float.valueOf(rs.getString(TIMPORTE))+" "+
					rs.getString(TESTTUS).charAt(0)
				);
			}
		} catch(SQLException e) {
			
		}
	}
}
