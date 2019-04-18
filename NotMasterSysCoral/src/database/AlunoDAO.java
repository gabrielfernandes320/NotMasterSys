package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.CASTORE;
import com.sun.xml.internal.bind.v2.model.core.ID;

import model.Aluno;

public class AlunoDAO extends MasterDAO {
	
	// Cria as variaveis contendo o select a ser feito.
	private String is_update = "UPDATE alunos\r\n" + 
			"   SET data_nascimento=?, sexo=?, telefone=?, \r\n" + 
			"       celular=?, email=?, observacao=?, endereco=?, numero=?, complemento=?, \r\n" + 
			"       bairro=?, cidade=?, estado=?, pais=?, cep=?\r\n" + 
			" WHERE aluno=?";
	private String is_selectAll = "select * from alunos order by aluno";
	private String is_delete = "delete from alunos where aluno =? and email=? ";
	private String is_select = "select * from alunos where aluno = ? order by aluno";
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
	private PreparedStatement pst_delete;
	private PreparedStatement pst_update;
	
	Connection io_connection;
		
	public AlunoDAO(Connection connection) 
			throws SQLException 
	{
		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_select = connection.prepareStatement(is_select);
		pst_insert = connection.prepareStatement(is_insert);
		pst_delete = connection.prepareStatement(is_delete);
		pst_update = connection.prepareStatement(is_update);
		
	}

	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> arlAluno = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			
			Aluno model = new Aluno();
			model.setCodigo_aluno(rst.getInt("codigo_aluno"));
			model.setAluno(rst.getString("aluno"));
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
	public Aluno Select(Object parameter) throws SQLException {

		Aluno aluno = null;
		
		Aluno lo_aluno = (Aluno)parameter;
		
		Set(pst_select, 1, lo_aluno.getAluno());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			aluno = new Aluno();
			aluno.setCodigo_aluno(rst.getInt("codigo_aluno"));
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
			aluno.setEstado(rst.getString("estado"));
			aluno.setPais(rst.getString("pais"));
			aluno.setCep(rst.getString("cep"));
			
			return aluno;
			
		}
		
		else
			return null;

	}

	@Override
	public void Update(Object parameter) throws SQLException {
				
		pst_update.clearParameters();
		
		Aluno lo_aluno = (Aluno)parameter;
		
		Set(pst_update, 1, lo_aluno.getData_nascimento());
		Set(pst_update, 2, lo_aluno.getSexo());
		Set(pst_update, 3, lo_aluno.getTelefone());
		Set(pst_update, 4, lo_aluno.getCelular());
		Set(pst_update, 5, lo_aluno.getEmail());
		Set(pst_update, 6, lo_aluno.getObservacao());
		Set(pst_update, 7, lo_aluno.getEndereco());
		Set(pst_update, 8, lo_aluno.getNumero());
		Set(pst_update, 9, lo_aluno.getComplemento());
		Set(pst_update, 10, lo_aluno.getBairro());
		Set(pst_update, 11, lo_aluno.getCidade());
		Set(pst_update, 12, lo_aluno.getEstado());
		Set(pst_update, 13, lo_aluno.getPais());
		Set(pst_update, 14, lo_aluno.getCep());
		Set(pst_update, 15, lo_aluno.getAluno());

		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
	}
		
	@Override
	public void Insert(Object parameter) throws SQLException {
		
		pst_insert.clearParameters();
		
		Aluno lo_aluno = (Aluno)parameter;
		
		Set(pst_insert, 1, lo_aluno.getAluno());
		Set(pst_insert, 2, lo_aluno.getData_nascimento());
		Set(pst_insert, 3, lo_aluno.getSexo());
		Set(pst_insert, 4, lo_aluno.getTelefone());
		Set(pst_insert, 5, lo_aluno.getCelular());
		Set(pst_insert, 6, lo_aluno.getEmail());
		Set(pst_insert, 7, lo_aluno.getObservacao());
		Set(pst_insert, 8, lo_aluno.getEndereco());
		Set(pst_insert, 9, lo_aluno.getNumero());
		Set(pst_insert, 10, lo_aluno.getComplemento());
		Set(pst_insert, 11, lo_aluno.getBairro());
		Set(pst_insert, 12, lo_aluno.getCidade());
		Set(pst_insert, 13, lo_aluno.getEstado());
		Set(pst_insert, 14, lo_aluno.getPais());
		Set(pst_insert, 15, lo_aluno.getCep());
		
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
		
	}

	@Override
    public int Delete(Object parameter) throws SQLException{

        int affectedrows = 0;
        
        Aluno lo_aluno = (Aluno)parameter;

        Set(pst_delete, 1, lo_aluno.getAluno());
        Set(pst_delete, 2, lo_aluno.getEmail());

        affectedrows = pst_delete.executeUpdate();

        return affectedrows;

    }
	
}
