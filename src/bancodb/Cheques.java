package bancodb;

import java.sql.*;
import static bancodb.DataBase.*;

public class Cheques extends DBConnection{
	Statement st;
	ResultSet rs;
	
	//Imprimiendo todos los datos de la tabla cheques
	public void imprimirCheques() {	
		try {
			st = connection.createStatement();
			//por defecto se hace autocommit por cada statement sql(para utilizar for update o for share, el autocommit necesita estar desactivado)
			connection.setAutoCommit(false);
			//for update bloquea otras transacciones hasta que termine la lectura
			//for share espera hasta que la transaccion termine para usar los ultimos valores
			rs = st.executeQuery("select * from "+TCHEQUES+" for share;");
			//nowait nunca espera a una tupla bloqueada, resultando en un error
			//skip locked nunca espera a una tupla bloqueada, removiendo las tuplas bloqueadas del result set
			while (rs.next()) {
				System.out.println(	rs.getString(TNOCUENTA)+" "+
									Float.valueOf(rs.getString(TIMPORTE))+" "+
									rs.getString(TESTTUS).charAt(0));
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
	public void consultarSaldo(String NOCUENTA) {
		try {
			connection.setAutoCommit(false);
			st = connection.createStatement();
			rs = st.executeQuery("select "+TIMPORTE+" from "+TCHEQUES+" where "+TNOCUENTA+" = '"+NOCUENTA+"' for share;");
			rs.next();
			connection.commit();
			System.out.println("Saldo: "+Float.valueOf(rs.getString(TIMPORTE)));
		} catch(SQLException e) {
			try {
				connection.rollback();
				System.out.println("La consulta ha fallado.");
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public void depositar(String NOCUENTA, Float DEPOSITO) {
		
		String query = "update "+TCHEQUES+" set "+TIMPORTE+"="+TIMPORTE+"+ ? where "+TNOCUENTA+"= ? ;";
		
		try (PreparedStatement pst = connection.prepareStatement(query)) {
			
			connection.setAutoCommit(false);
			pst.setFloat(1, DEPOSITO);
			pst.setString(2, NOCUENTA);
			pst.executeUpdate();
			System.out.println("El depósito fue exitoso.");
			System.out.println("Saldo: "+Float.valueOf(rs.getString(TIMPORTE)));
			
		} catch(SQLException e) {	
			try {
				connection.rollback();
				System.out.println("El depósito ha fallado.");
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
	}
	public void retirar(String NOCUENTA, Float RETIRO) {
		
		String query = "update "+TCHEQUES+" set "+TIMPORTE+"="+TIMPORTE+"- ? where "+TNOCUENTA+"= ? ;";

		try (PreparedStatement pst = connection.prepareStatement(query)) {
			
			connection.setAutoCommit(false);
			st = connection.createStatement();
			rs = st.executeQuery("select "+TIMPORTE+" from "+TCHEQUES+" where "+TNOCUENTA+" = '"+NOCUENTA+"' for share;");
			rs.next();
			if(RETIRO<Float.valueOf(rs.getString(TIMPORTE))) {
				
				pst.setFloat(1, RETIRO);
				pst.setString(2, NOCUENTA);
				pst.executeUpdate();
				connection.commit();			
				System.out.println("El retiro fue exitoso.");
			} else {			
				connection.rollback();			
				System.out.println("No cuenta con el suficiente saldo :(");			
			}	
		} catch(SQLException e) {
			try {
				connection.rollback();
				System.out.println("El retiro ha fallado");
			} catch(SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
