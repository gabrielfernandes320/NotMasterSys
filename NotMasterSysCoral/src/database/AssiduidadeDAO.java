package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Assiduidade;

public class AssiduidadeDAO extends MasterDAO{
	
	// Cria as variaveis contendo o select a ser feito.
			
			private String is_selectAll = "select * from assiduidade order by data_entrada";
			private String is_selectAllPesquisa = "select * from assiduidade where codigo_matricula like ? order by data_entrada";
			private String is_select = "select * from assiduidade where codigo_matricula = ? order by data_entrada";
			private String is_insert = "INSERT INTO assiduidade	"
										+" (					"
										+"		codigo_matricula,"
										+"		data_entrada	"
										+"	)					"
										+"	VALUES				"
										+"	(					"
										+"		?,				"
										+"		?				"
										+"	)";
			private String is_update = "UPDATE assiduidade " + 
									"   SET codigo_matricula=? where data_entrada = ?";
			
			private PreparedStatement pst_selectAll;
			private PreparedStatement pst_selectAllPesquis;
			private PreparedStatement pst_select;
			private PreparedStatement pst_insert;
			private PreparedStatement pst_update;
			
			
			Connection io_connection;
			
			public AssiduidadeDAO(Connection connection) throws SQLException {
				
				io_connection = connection;
				pst_selectAll = connection.prepareStatement(is_selectAll);
				pst_selectAllPesquis = connection.prepareStatement(is_selectAllPesquisa);
				pst_select = connection.prepareStatement(is_select);
				pst_insert = connection.prepareStatement(is_insert);	
				pst_update = connection.prepareStatement(is_update);
				
			}

	@Override
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlAssiduidade = new ArrayList<Object>();
		ResultSet rst = pst_selectAll.executeQuery();
		 
		while(rst.next()){
			 Assiduidade model = new Assiduidade();
			 

			 model.setCodigo_matricula(rst.getInt("codigo_matricula"));
			 model.setData_entrada(rst.getTimestamp("data_entrada"));
			 arlAssiduidade.add(model);
		 }
				
		return arlAssiduidade;
	}
	
	public List<Assiduidade> SelectAll(final String pesquisa) throws SQLException {
		List<Assiduidade> arlAssiduidade = new ArrayList<Assiduidade>();
		
		Set(pst_selectAllPesquis, 1, "%"+pesquisa+"%");
		
		ResultSet rst = pst_selectAllPesquis.executeQuery();
		 
		while(rst.next()){
			Assiduidade model = new Assiduidade();

			 model.setCodigo_matricula(rst.getInt("codigo_matricula"));
			 model.setData_entrada(rst.getTimestamp("data_entrada"));
			 arlAssiduidade.add(model);
		 }
				
		return arlAssiduidade;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
Assiduidade assiduidade = null;
		
		Set(pst_select, 1, ((Assiduidade)parameter).getCodigo_matricula());
		Set(pst_select, 2, ((Assiduidade)parameter).getData_entrada());
		
		ResultSet rst = pst_select.executeQuery();
		if(rst.next()) {
			assiduidade = new Assiduidade();
			assiduidade.setCodigo_matricula(rst.getInt("codigo_matricula"));
			assiduidade.setData_entrada(rst.getTimestamp("data_entrada"));
		}
		return assiduidade;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Assiduidade lo_plano = (Assiduidade)parameter;
		Set(pst_insert, 1, lo_plano.getCodigo_matricula());
		Set(pst_insert, 2, lo_plano.getData_entrada());
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
	
	}

	@Override
	public int Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}