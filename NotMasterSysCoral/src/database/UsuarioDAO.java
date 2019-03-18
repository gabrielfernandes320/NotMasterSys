package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Usuario;

public class UsuarioDAO extends MasterDAO {
	
	// Cria as variaveis contendo o select a ser feito.
		private String is_selectAll = "select * from usuarios order by usuario";
		private String is_select = "select * from usuarios where usuario = ? and perfil = ? order by usuario";
		private String is_insert = "INSERT INTO usuarios			"
									+"	(						" 
									+"		usuario, 		"
									+"		perfil, 				"
									+"	)						"  
									+"  VALUES 					"
									+"	(						"
									+"		DEFAULT, 			"
									+"		?, 					"
									+"						   )";
		
		private PreparedStatement pst_selectAll;
		private PreparedStatement pst_select;
		private PreparedStatement pst_insert;
		
		Connection io_connection;
			
		public UsuarioDAO(Connection connection) 
				throws SQLException 
		{
			io_connection = connection;
			pst_selectAll = connection.prepareStatement(is_selectAll);
			pst_select = connection.prepareStatement(is_select);
			pst_insert = connection.prepareStatement(is_insert);
		}

	@Override
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlUsuario = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			
			Usuario model = new Usuario();
			model.setPerfil(rst.getString("usuario"));
			model.setUsuario(rst.getString("perfil"));
			arlUsuario.add(model);
			
		}
		
		return arlUsuario;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
