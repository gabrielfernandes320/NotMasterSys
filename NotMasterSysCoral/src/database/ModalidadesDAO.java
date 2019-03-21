package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Modalidades;

public class ModalidadesDAO extends MasterDAO{
	
	private String is_selectAll = "select * from modalidades order by modalidade";
	private String is_select = "select * from modalidades where modalidade = ? and graduacao = ? order by modalidade";
	private String is_insert = "INSERT INTO modalidades	"
								+" (					"
								+"		modalidade,		"
								+"		graduacao		"
								+"	)					"
								+"	VALUES				"
								+"	(					"
								+"		DEFAULT,		"
								+"		?"
								+"	)";
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	
	Connection io_connection;
	
	public ModalidadesDAO(Connection connection) throws SQLException {
		
		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_select = connection.prepareStatement(is_select);
		pst_insert = connection.prepareStatement(is_insert);	
	}

	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> arlModalidade = new ArrayList<Object>();
		ResultSet rst = pst_selectAll.executeQuery();
		 
		while(rst.next()){
			 Modalidades model = new Modalidades();
			 
			 model.setModalidade(rst.getString("modalidade"));
			 model.setGraduacoes(rst.getString("graduacoes"));
			 arlModalidade.add(model);
		 }
				
		return arlModalidade;;
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
