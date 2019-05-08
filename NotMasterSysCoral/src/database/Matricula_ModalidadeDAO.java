package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Matricula;
import model.Matricula_Modalidade;

public class Matricula_ModalidadeDAO extends MasterDAO{
	
	private String is_selectAll = "select * from matriculas order by codigo_aluno";
	
	private String is_insert = "INSERT INTO matriculas_modalidades"
			+ " ( codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim)"
			+ " VALUES ( ?, ?, ?, ?, ?, ? )";
	
	private PreparedStatement pst_insert;

	Connection io_connection;
	
	public Matricula_ModalidadeDAO (Connection connection) throws SQLException{
		
		io_connection = connection;
		
		pst_insert = connection.prepareStatement(is_insert);
		
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
	public int Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}
