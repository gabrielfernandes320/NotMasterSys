package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPackage {
	public static Connection getConnection(final String nomeBanco,final String usuario, final String senha)
	{
		return getConnection("localhost","5432",nomeBanco,usuario,senha );
		
	}
	
	public static Connection getConnection(final String ipBanco, final String portaBanco, final String nomeBanco, final String usuario, final String senha) {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://"+ipBanco+":"+portaBanco+"/"+nomeBanco,usuario, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
	
}
