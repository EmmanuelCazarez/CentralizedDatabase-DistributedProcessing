package bancodb;

import java.sql.*;
import static bancodb.DataBase.*;

public class Cheques extends DBConnection{
	DBConnection con = new DBConnection();
	Statement st;
	ResultSet rs;
	
	//Imprimiendo los datos de la tabla cheques
	public void imprimir() {	
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
			e.printStackTrace();
		}
	}
	public void depositar(String NOCUENTA, Float IMPORTE) {
		
		String query = "update "+TCHEQUES+" set "+TIMPORTE+"="+TIMPORTE+"+ ? where "+TNOCUENTA+"= ? ;";
		
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			
			pst.setFloat(1, IMPORTE);
			pst.setString(2, NOCUENTA);
			pst.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void retirar(String NOCUENTA, Float IMPORTE) {
		
		String query = "update "+TCHEQUES+" set "+TIMPORTE+"="+TIMPORTE+"- ? where "+TNOCUENTA+"= ? ;";
		
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			
			pst.setFloat(1, IMPORTE);
			pst.setString(2, NOCUENTA);
			pst.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
