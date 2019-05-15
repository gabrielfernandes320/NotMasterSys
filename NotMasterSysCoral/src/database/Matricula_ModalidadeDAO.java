package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Graduacoes;
import model.Invoice;
import model.Matricula;
import model.Matricula_Modalidade;
import model.Plano;

public class Matricula_ModalidadeDAO extends MasterDAO{
	
	private String is_selectAll = "select * from matriculas order by codigo_aluno";
	private String is_select = "SELECT * from matriculas_modalidades where codigo_matricula = ?";
	private String is_insert = "INSERT INTO matriculas_modalidades"
			+ " ( codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim)"
			+ " VALUES ( ?, ?, ?, ?, ?, ? )";
	private String is_selectPorCodigoAluno = "select * "
			+ "from matriculas_modalidades join matriculas on matriculas_modalidades.codigo_matricula "
			+ "= matriculas.codigo_matricula where matriculas.codigo_aluno = ?";
	private String is_delete = "update matriculas set data_encerramento = current_date where codigo_matricula = ?;"
			+ "delete from matriculas_modalidades where codigo_matricula= ?";
	
	private PreparedStatement pst_insert;
	private PreparedStatement pst_select;
	private PreparedStatement pst_selectPorCodigoAluno;
	private PreparedStatement pst_delete;
	
	Connection io_connection;
	
	public Matricula_ModalidadeDAO (Connection connection) throws SQLException{
		
		io_connection = connection;
		
		pst_insert = connection.prepareStatement(is_insert);
		pst_select = connection.prepareStatement(is_select);
		pst_selectPorCodigoAluno = connection.prepareStatement(is_selectPorCodigoAluno);
		pst_delete = connection.prepareStatement(is_delete);
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


	public List<Matricula_Modalidade> Select(Matricula parameter) throws SQLException {
		
		List<Matricula_Modalidade> arlMatricModalidade = new ArrayList<Matricula_Modalidade>();
		
		
		Set(pst_select, 1, parameter.getCodigo_aluno());
		
		ResultSet rst = pst_select.executeQuery();
		 
		while(rst.next()){
			Matricula_Modalidade model = new Matricula_Modalidade();
			
			int x = 0;
			
			model.setCodigo_matricula(rst.getInt("codigo_matricula"));;
			model.setModalidade(rst.getString("modalidade"));
			model.setGraduacao(rst.getString("graduacao"));
			model.setPlano(rst.getString("plano"));
			model.setData_inicio(rst.getDate("data_inicio"));
			model.setData_fim(rst.getDate("data_fim"));
			arlMatricModalidade.add(model);
			 
			System.out.println(x);
		 }
		
		return arlMatricModalidade;
	}
	
	public List<Matricula_Modalidade> selectPorCodigoAluno(Object parameter) throws SQLException {
		
		pst_selectPorCodigoAluno.clearParameters();
		
		List<Matricula_Modalidade> arlMat = new ArrayList<Matricula_Modalidade>();
		Matricula lo_mat = (Matricula) parameter;
		
		int cod = lo_mat.getCodigo_aluno();
		Set(pst_selectPorCodigoAluno, 1, cod);
		
		ResultSet rst = pst_selectPorCodigoAluno.executeQuery();

		while(rst.next()){
			 Matricula_Modalidade model = new Matricula_Modalidade();
			 
			 model.setCodigo_matricula(rst.getInt("codigo_matricula"));
			 model.setModalidade(rst.getString("modalidade"));
			 model.setData_fim(rst.getDate("data_fim"));
			 model.setData_inicio(rst.getDate("data_inicio"));
			 model.setGraduacao(rst.getString("graduacao"));
			 model.setPlano(rst.getString("plano"));
			 
			 arlMat.add(model);
		 }
		//String[] returno = new String[arlMat.size()];
		return arlMat;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public int Delete(Object parameter) throws SQLException {
		pst_delete.clearParameters();
		
		Matricula_Modalidade lo_matricula = (Matricula_Modalidade)parameter;
		
		Set(pst_delete, 1, lo_matricula.getCodigo_matricula());
		Set(pst_delete, 2, lo_matricula.getCodigo_matricula());
		
		System.out.println("DELETE: " + lo_matricula.getCodigo_matricula());
		
		pst_delete.execute();
		
		if (pst_delete.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
		return 0;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
