package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Graduacoes;
import model.Modalidades;

public class GraduacoesDAO extends MasterDAO{
		private String is_delete = "delete from graduacoes where graduacao = ?";
		private String is_selectAll = "select * from graduacoes order by graduacoes";
		private String is_select = "select * from graduacoes where graduacao = ? and modalidade = ? order by graduacoes";
		private String is_insert = "INSERT INTO graduacoes		"
									+"	(			"
									+"			modalidade,		"
									+"			graguacoes		"
									+"	)						"
									+"	VALUES					"	
									+"	(						"
									+"				?,			"	
									+"				?"	
									+"	)";
		private String is_update = "UPDATE graduacoes"
								 + " SET graduacao = ? where graduacoes = ?";
		
		private PreparedStatement pst_selectAll;
		private PreparedStatement pst_select;
		private PreparedStatement pst_insert;
		private PreparedStatement pst_update;
		private PreparedStatement pst_delete;
		
		Connection io_connection;
		
	public GraduacoesDAO(Connection connection) throws SQLException {
		
		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_select = connection.prepareStatement(is_select);
		pst_insert = connection.prepareStatement(is_insert);	
		pst_delete = connection.prepareStatement(is_delete);
		pst_update = connection.prepareStatement(is_update);
		
	}
		
	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> arlgraduacoes = new ArrayList<Object>();
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			Graduacoes model = new Graduacoes();
			model.setId_modality("modalidade");
			model.setGraduations("graduacao");
			arlgraduacoes.add(model);
		}
		return arlgraduacoes;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		Graduacoes graduacoes = null;
		
		Set(pst_select, 1,((Graduacoes)parameter).getId_modality());
		Set(pst_select, 2, ((Graduacoes)parameter).getGraduations());
		
		ResultSet rst = pst_select.executeQuery();
		
		if(rst.next()) {
			Graduacoes model = new Graduacoes();
			model.setId_modality(rst.getString("modalidade"));
			model.setGraduations(rst.getString("graduacao"));
		}
		return graduacoes;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		pst_update.clearParameters();
		
		Graduacoes lo_graduacoes = (Graduacoes)parameter;
		
		Set(pst_update, 1, lo_graduacoes.getId_modality());
		Set(pst_update, 2, lo_graduacoes.getGraduations());
		pst_update.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
				
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Graduacoes lo_graduacoes = (Graduacoes)parameter;
		
		Set(pst_insert, 1, lo_graduacoes.getId_modality());
		Set(pst_insert, 2, lo_graduacoes.getGraduations());
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
	}
	

	@Override
	public int Delete(Object parameter) throws SQLException {
		// TO DO
		return 0;
	}
	
	
}
