package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
		private String is_update = "UPDATE public.graduacoes\r\n"
								 + " SET modalidade = ?, graduacao = ?";
		
		private PreparedStatement pst_selectAll;
		private PreparedStatement pst_select;
		private PreparedStatement pst_insert;
		private PreparedStatement pst_update;
		private PreparedStatement pst_delete;
		
		
		
		
		
	@Override
	public List<Object> SelectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
	public int Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
