package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;

public class AlunoDAO extends MasterDAO {
	
	// Cria as variaveis contendo o select a ser feito.
	private String is_selectAll = "select * from alunos order by aluno";
	private String is_select = "select * from alunos where aluno = ? and email = ? order by aluno";
	private String is_insert = "INSERT INTO alunos			"
								+"	(						" 
								+"		codigo_aluno, 		"
								+"		aluno, 				"
								+"		data_nascimento, 	"
								+"		sexo, 				"
								+"		telefone, 			"
								+"		celular,			" 
								+"      email, 				"
								+"		observacao, 		"
								+"		endereco, 			"
								+"		numero, 			"
								+"		complemento, 		"
								+"		bairro, 			"
								+"		cidade, 			" 
								+"      estado, 			"
								+"		pais, 				"
								+"		cep					"
								+"	)						"  
								+"  VALUES 					"
								+"	(						"
								+"		DEFAULT, 			"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					" 
								+"      ?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					"
								+"		?, 					" 
								+"      ?, 					"
								+"		?, 					"
								+"		?"
								+"	)";
	
	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	
	Connection io_connection;
		
	public AlunoDAO(Connection connection) 
			throws SQLException 
	{
		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_select = connection.prepareStatement(is_select);
		pst_insert = connection.prepareStatement(is_insert);
	}

	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> arlAluno = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			
			Aluno model = new Aluno();
			model.setCodigo_aluno(rst.getString("aluno"));
			model.setData_nascimento(rst.getDate("data_nascimento"));
			model.setSexo(rst.getString("sexo").charAt(0));
			model.setTelefone(rst.getString("telefone"));
			model.setCelular(rst.getString("celular"));
			model.setEmail(rst.getString("email"));
			model.setObservacao(rst.getString("observacao"));
			model.setEndereco(rst.getString("endereco"));
			model.setNumero(rst.getString("numero"));
			model.setComplemento(rst.getString("complemento"));
			model.setBairro(rst.getString("bairro"));
			model.setCidade(rst.getString("cidade"));
			model.setPais(rst.getString("pais"));
			model.setCep(rst.getString("cep"));
			
			arlAluno.add(model);
			
		}
		
		return arlAluno;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		// Cria o objeto aluno.
		AlunoModel aluno = null;
		
		// Seta os parametros.
		Set(pst_select, 1, ((AlunoModel)parameter).getAluno());
		Set(pst_select, 2, ((AlunoModel)parameter).getEmail());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			aluno = new AlunoModel();
			aluno.setAluno(rst.getString("aluno"));
			aluno.setData_nascimento(rst.getDate("data_nascimento"));
			aluno.setSexo(rst.getString("sexo").charAt(0));
			aluno.setTelefone(rst.getString("telefone"));
			aluno.setCelular(rst.getString("celular"));
			aluno.setEmail(rst.getString("email"));
			aluno.setObservacao(rst.getString("observacao"));
			aluno.setEndereco(rst.getString("endereco"));
			aluno.setNumero(rst.getString("numero"));
			aluno.setComplemento(rst.getString("complemento"));
			aluno.setBairro(rst.getString("bairro"));
			aluno.setCidade(rst.getString("cidade"));
			aluno.setPais(rst.getString("pais"));
			aluno.setCep(rst.getString("cep"));
		}
		
		return aluno;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
				
		
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		
		pst_insert.clearParameters();
		
		AlunoModel lo_aluno = (AlunoModel)parameter;
		
		Set(pst_insert, 1, lo_aluno.getAluno());
		Set(pst_insert, 2, lo_aluno.getData_nascimento());
		Set(pst_insert, 3, lo_aluno.getSexo());
		
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
	}

	@Override
	public void Delete(Object parameter) throws SQLException {
				
	}

}










