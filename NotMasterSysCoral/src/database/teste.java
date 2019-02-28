package database;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.core.ConnectionFactory;

public class teste {
		public static void main(String[] args) {
			
			Connection conn = ConnectionPackage.getConnection("master","admin","admin");
			
			try {
				conn.setAutoCommit(false);
				System.out.println("Conectado com sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
}
