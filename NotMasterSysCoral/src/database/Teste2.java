/*package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.dao.AlunoDAO;
import database.model.AlunoModel;
import image.MasterImage;

public class Teste {

	public static void main(String[] args) {
		
		Connection conn = ConnectionFactory.getConnection
							(
								"master", 
								"admin", 
								"admin"
							);
		try {
			conn.setAutoCommit(false);
			System.out.println("Conectado com sucesso!");
			
			AlunoDAO dao = new AlunoDAO(conn);
			
			AlunoModel model = new AlunoModel();
			model.setAluno("Matheus Leandro Ferreira");
			model.setEmail("mlf@unesc.net");
			model.setData_nascimento(new Date("03/06/2019"));
			
			dao.Insert(model);			
		
			/*List<Object> lst = dao.SelectAll();
			
			for (int i = 0; i < lst.size(); i++) {				
				AlunoModel aluno = (AlunoModel)lst.get(i);
				System.out.println(aluno.getAluno());
			}*/			
			
			
					
			/*AlunoModel retorno = (AlunoModel)dao.Select(model);
			System.out.println(retorno.getCidade());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}*/










