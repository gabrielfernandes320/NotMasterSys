package database;

import java.sql.SQLException;
import java.util.List;

public class GraduacoesDAO extends MasterDAO{
		private String is_delete = "delete from graduacoes where graduacao = ?";
		private String is_selectAll = "select * from graduacoes order by graduacao";
		
		
		
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
