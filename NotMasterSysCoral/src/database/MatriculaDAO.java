package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Matricula;
import model.Usuario;

public class MatriculaDAO extends MasterDAO{

	private String is_selectAll = "select * from matriculas order by codigo_aluno";
	
	private String is_insert = "INSERT INTO Matriculas "
			+ "(codigo_matricula, codigo_aluno, data_matricula, dia_vencimento, data_encerramento)"
			+ "VALUES (DEFAULT,?,?,?,?);";
	
	private String is_select = "select * from matriculas where codigo_aluno =? ";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_select;
	
	Connection io_connection;
	
	public MatriculaDAO (Connection connection) throws SQLException{
		
		io_connection = connection;
		
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_insert = connection.prepareStatement(is_insert);
		
	}
	
	@Override
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlMatricula = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			
			Matricula model = new Matricula();
			arlMatricula.add(model);
			
		}
		
		return arlMatricula;
	}
	
public void Insert(Object parameter) throws SQLException {
		
		pst_insert.clearParameters();
		
		Matricula lo_matricula = (Matricula)parameter;
		
		//Set(pst_insert, 1, lo_matricula.getCodigo_matricula());
		Set(pst_insert, 1, lo_matricula.getCodigo_aluno());
		Set(pst_insert, 2, lo_matricula.getData_matricula());
		Set(pst_insert, 3, lo_matricula.getDia_vencimento());
		Set(pst_insert, 4, lo_matricula.getData_encerramento());

		
		System.out.println(lo_matricula.getCodigo_aluno());
		
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
	}


@Override
public Object Select(Object parameter) throws SQLException {
	// TODO Auto-generated method stub
	Matricula matricula = null;
	Matricula lo_matricula = (Matricula)parameter;	
	
	Set(pst_select, 1, lo_matricula.getCodigo_aluno());	
	ResultSet rst = pst_select.executeQuery();
	
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
