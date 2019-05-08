package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Graduacoes;
import model.Matricula;
import model.Matricula_Modalidade;

public class Matricula_ModalidadeDAO extends MasterDAO{
	
	private String is_selectAll = "select * from matriculas order by codigo_aluno";
	private String is_select = "SELECT * from matriculas_modalidades where codigo_matricula = ?";
	private String is_insert = "INSERT INTO matriculas_modalidades"
			+ " ( codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim)"
			+ " VALUES ( ?, ?, ?, ?, ?, ? )";
	
	private PreparedStatement pst_insert;
	private PreparedStatement pst_select;
	
	Connection io_connection;
	
	public Matricula_ModalidadeDAO (Connection connection) throws SQLException{
		
		io_connection = connection;
		
		pst_insert = connection.prepareStatement(is_insert);
		pst_select = connection.prepareStatement(is_select);
	}
	
	public void Insert(Object parameter) throws SQLException {
		
		pst_insert.clearParameters();
		
		Matricula_Modalidade lo_matMod = (Matricula_Modalidade)parameter;
		
		Set(pst_insert, 1, lo_matMod.getCodigo_matricula());
		Set(pst_insert, 2, lo_matMod.getModalidade());
		Set(pst_insert, 3, lo_matMod.getGraduacao());
		Set(pst_insert, 4, lo_matMod.getPlano());
		Set(pst_insert, 5, lo_matMod.getData_inicio());
		Set(pst_insert, 6, lo_matMod.getData_fim());
		
		
		//Set(pst_insert, 1, lo_matricula.getCodigo_aluno());
		//Set(pst_insert, 2, lo_matricula.getData_matricula());
		//Set(pst_insert, 3, lo_matricula.getDia_vencimento());
		
		//if (lo_matricula.getData_encerramento() != null) {
			//Set(pst_insert, 4, lo_matricula.getData_encerramento());
		//} else {
			//Set(pst_insert, 4, null);
		//}
		
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
		
		
	}

	@Override
	public List<Object> SelectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Matricula_Modalidade> Select(int matricula) throws SQLException {
		
		List<Matricula_Modalidade> arlMatricModalidade = new ArrayList<Matricula_Modalidade>();
		
		pst_select.setInt(1, matricula);
		
		ResultSet rst = pst_select.executeQuery();
		 
		while(rst.next()){
			
			Matricula_Modalidade model = new Matricula_Modalidade();
			 
			model.setCodigo_matricula(rst.getInt("codigo_matricula"));;
			model.setModalidade(rst.getString("modalidade"));
			model.setGraduacao(rst.getString("graduacao"));
			model.setPlano(rst.getString("plano"));
			model.setData_inicio(rst.getDate("data_inicio"));
			model.setData_fim(rst.getDate("data_fim"));
			arlMatricModalidade.add(model);
			 
		 }
				
		return arlMatricModalidade;
	}
	

	@Override
	public void Update(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
