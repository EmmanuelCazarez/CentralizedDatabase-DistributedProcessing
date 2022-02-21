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
			//por defecto se hace autocommit por cada statement sql(para utilizar for update o for share, el autocommit necesita estar desactivado)
			connection.setAutoCommit(false);
			//for update bloquea otras transacciones hasta que termine la lectura
			//for share espera hasta que la transaccion termine para usar los ultimos valores
			rs = st.executeQuery("select * from "+TCHEQUES+" for share;");
			//nowait nunca espera a una tupla bloqueada, resultando en un error
			//skip locked nunca espera a una tupla bloqueada, removiendo las tuplas bloqueadas del result set
			while (rs.next()) {
				System.out.println
				(
					rs.getString(TNOCUENTA)+" "+
					Float.valueOf(rs.getString(TIMPORTE))+" "+
					rs.getString(TESTTUS).charAt(0)
				);
			}
			connection.commit();

		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
