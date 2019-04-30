package database;

import model.Invoice;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Teste2 {

	public static void main(String[] args) {

		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		try {
			//conn.setAutoCommit(false);
			System.out.println("Conectado com sucesso!");

			Usuario model = new Usuario();

			//Insert OK
			model.setPerfil("Cadastral");
			model.setUsuario("testeagora");
			model.setPassword("testeagora4");
			
			List<Invoice> in = new ArrayList<Invoice>();
			in = (List<Invoice>)(List<?>) new InvoiceDAO(conn).SelectAll();
			
			//dao.Insert(model);y
			//InsertRole
			//dao.Insert(model);
			//dao.CreateRole(model);

			//SelectAll OK
				/*List<Object> lst = dao.SelectAll();
			
			for (int i = 0; i < lst.size(); i++) {				
				Usuario usuario = (Usuario)lst.get(i);
				System.out.println(usuario.getPerfil());
			}*/
			
			
			//Select OK
			//model.setUsuario("teste")
			System.out.println("ok");
			System.out.println(in.get(0).getData_vencimento() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}










