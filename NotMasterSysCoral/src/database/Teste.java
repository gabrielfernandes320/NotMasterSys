package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		
		try {
			conn.setAutoCommit(false);
			
			UsuarioDAO dao = new UsuarioDAO(conn);
			List<Object> lst = dao.SelectAll();
			
			for (int i = 0; i < lst.size(); i++) {				
				Usuario usuario = (Usuario)lst.get(i);
				System.out.println(usuario.getPerfil());
			}		
			
			
					
			/*AlunoModel retorno = (AlunoModel)dao.Select(model);
			System.out.println(retorno.getCidade());*/
		} catch(SQLException e) {
e.printStackTrace();
		}
	}
}
