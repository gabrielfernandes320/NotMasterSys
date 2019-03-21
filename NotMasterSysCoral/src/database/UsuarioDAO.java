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
		
		private String is_update = "UPDATE usuarios " 
				+"   SET usuario= ?, perfil= ?"
				+" WHERE usuario = ?";
		
		private String is_create_role = "create	role ?1"
				+"	with login"
				+"	encrypted password	'?2'"
				+"	in role	admin";
		
		private String is_alter_role = "alter	role		?1"
				+"	with login"
				+"	encrypted password'?2'";
		
		private String is_drop_role = "drop	role		?1";
					
		
		private PreparedStatement pst_selectAll;
		private PreparedStatement pst_select;
		private PreparedStatement pst_insert;
		private PreparedStatement pst_update;
		private PreparedStatement pst_create_role;
		private PreparedStatement pst_alter_role;
		private PreparedStatement pst_drop_role;
		
		
		Connection io_connection;
			
		public UsuarioDAO(Connection connection) 
				throws SQLException 
		{
			io_connection = connection;
			pst_selectAll = connection.prepareStatement(is_selectAll);
			pst_select = connection.prepareStatement(is_select);
			pst_insert = connection.prepareStatement(is_insert);
			pst_update = connection.prepareStatement(is_update);
			pst_create_role = connection.prepareStatement(is_create_role);
			pst_alter_role = connection.prepareStatement(is_alter_role);
			pst_drop_role = connection.prepareStatement(is_drop_role);
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
		// Cria o objeto aluno.
				Usuario usuario = null;
				
				// Seta os parametros.
				Set(pst_select, 1, ((Usuario)parameter).getUsuario());
				Set(pst_select, 2, ((Usuario)parameter).getPerfil());
				
				ResultSet rst = pst_select.executeQuery();
				
				if (rst.next()) {
					usuario = new Usuario();
					usuario.setUsuario(rst.getString("usuario"));
					usuario.setPerfil(rst.getString("perfil"));
				}
				
				return usuario;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		
		pst_update.clearParameters();
		
		Usuario lo_aluno = (Usuario)parameter;
		
		Set(pst_update, 1, lo_aluno.getUsuario());
		Set(pst_update, 2, lo_aluno.getPerfil());
		
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		
		pst_insert.clearParameters();
		
		Usuario lo_aluno = (Usuario)parameter;
		
		Set(pst_insert, 1, lo_aluno.getUsuario());
		Set(pst_insert, 2, lo_aluno.getPerfil());
		
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
	}
	
	public void CreateRole(Object parameter) throws SQLException {
		
		pst_create_role.clearParameters();
		
		Usuario lo_aluno = (Usuario)parameter;
		
		Set(pst_insert, 1, lo_aluno.getUsuario());
		Set(pst_insert, 2, lo_aluno.getPerfil());
		
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
	}

	@Override
	public int Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	return (Integer) null;
		
	}

}
