package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Modalidades;
import model.Plano;

public class PlanosDAO extends MasterDAO {
	
	// Cria as variaveis contendo o select a ser feito.
		private String is_delete = "delete from planos where plano = ? and modalidade = ?";
		private String is_selectAll = "select * from planos order by plano";
		private String is_select = "select * from planos where plano = ? and modalidade = ? order by plano";
		private String is_insert = "INSERT INTO planos	"
									+" (					"
									+"		plano,			"
									+"		modalidade,		"
									+ "		valor_mensal	"
									+"	)					"
									+"	VALUES				"
									+"	(					"
									+"		?,				"
									+"		?,				"
									+ "		?				"
									+"	)";
		private String is_update = "UPDATE public.planos\r\n" + 
								"   SET plano=?, modalidade=?, valor_mensal";
		
		private PreparedStatement pst_selectAll;
		private PreparedStatement pst_select;
		private PreparedStatement pst_insert;
		private PreparedStatement pst_update;
		private PreparedStatement pst_delete;
		
		Connection io_connection;
		
		public PlanosDAO(Connection connection) throws SQLException {
			
			io_connection = connection;
			pst_selectAll = connection.prepareStatement(is_selectAll);
			pst_select = connection.prepareStatement(is_select);
			pst_insert = connection.prepareStatement(is_insert);	
			pst_delete = connection.prepareStatement(is_delete);
			pst_update = connection.prepareStatement(is_update);
			
		}
	

	@Override
	public List<Object> SelectAll() throws SQLException {
		List<Object> arlPlano = new ArrayList<Object>();
		ResultSet rst = pst_selectAll.executeQuery();
		 
		while(rst.next()){
			 Plano model = new Plano();
			 
			 model.setPlano(rst.getString("plano"));
			 model.setModalidade(rst.getString("modalidade"));
			 model.setValor(rst.getDouble("valor_mensal"));
			 arlPlano.add(model);
		 }
				
		return arlPlano;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
//		cria o objeto modaliidade
		Plano plano = null;
		
		Set(pst_select, 1, ((Plano)parameter).getPlano());
		Set(pst_select, 2, ((Plano)parameter).getModalidade());
		
		ResultSet rst = pst_select.executeQuery();
		if(rst.next()) {
			plano = new Plano();
			plano.setPlano(rst.getString("modalidade"));
			plano.setModalidade(rst.getString("graduacoes"));
			plano.setValor((rst.getDouble("graduacoes")));
		}
		return plano;
	}

	@Override
	public void Update(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Plano lo_plano = (Plano)parameter;
		Set(pst_update, 1, lo_plano.getPlano());
		Set(pst_update, 2, lo_plano.getModalidade());
		Set(pst_update, 3, lo_plano.getValor());
		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			io_connection.commit();
		}
	
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Plano lo_plano = (Plano)parameter;
		Set(pst_insert, 1, lo_plano.getPlano());
		Set(pst_insert, 2, lo_plano.getModalidade());
		Set(pst_insert, 3, lo_plano.getValor());
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}
	
	}

	@Override
	public int Delete(Object parameter) throws SQLException {
		int affectedrows = 0;

		Plano lo_plano = (Plano)parameter;

		Set(pst_delete, 1, lo_plano.getPlano());
		Set(pst_delete, 2, lo_plano.getModalidade());
		Set(pst_delete, 3, lo_plano.getValor());

        affectedrows = pst_delete.executeUpdate();

        return affectedrows;
	}

}
