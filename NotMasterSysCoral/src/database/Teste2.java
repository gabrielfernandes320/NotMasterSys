package database;

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
			conn.setAutoCommit(false);
			System.out.println("Conectado com sucesso!");
			
			UsuarioDAO dao = new UsuarioDAO(conn);
			
			Usuario model = new Usuario();
			model.setPerfil("Administradorh");
			model.setUsuario("testessgs");
			model.setSenha("testessdd");

			//dao.Insert(model);
			dao.CreateRole(model);
			/*List<Object> lst = dao.SelectAll();
			
			for (int i = 0; i < lst.size(); i++) {				
				AlunoModel aluno = (AlunoModel)lst.get(i);
				System.out.println(aluno.getAluno());
			}*/			
			
			
					
			/*AlunoModel retorno = (AlunoModel)dao.Select(model);
			System.out.println(retorno.getCidade());*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}










