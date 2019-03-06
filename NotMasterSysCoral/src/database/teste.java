package database;


import java.sql.Connection;
import java.sql.SQLException;

public class Teste {

	public static void main(String[] args) {
		
		Connection conn = ConnectionFactory.getConnection
							(
								"master", 
								"admin", 
								"admin"
							);
		try {
			conn.setAutoCommit(false);
			System.out.println("Conectado com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
