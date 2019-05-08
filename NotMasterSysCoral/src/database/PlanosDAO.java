package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.encoding.MtomCodec.MtomXMLStreamReaderEx;

import model.Matricula_Modalidade;
import model.Modalidades;
import model.Plano;

public class PlanosDAO extends MasterDAO {
	
	// Cria as variaveis contendo o select a ser feito.
		private String is_delete = "delete from planos where plano = ? ";
		private String is_selectAll = "select * from planos order by plano";
		private String is_selectAllModalidade = "select * from planos order by plano";
		private String is_selectAllPesquisa = "select * from planos where plano like ? order by plano";
		private String is_selectAllPesquisaM = "select * from planos where modalidade like ? order by modalidade";
		private String is_select = "select * from planos where plano = ? order by plano";
		private String is_insert = "INSERT INTO planos	"
									+" (					"
									+"		modalidade,			"
									+"		plano,		"
									+ "		valor_mensal	"
									+"	)					"
									+"	VALUES				"
									+"	(					"
									+"		?,				"
									+"		?,				"
									+ "		?				"
									+"	)";
		private String is_update = "UPDATE planos " + 
								"   SET valor_mensal=? where plano = ?";
		
		private PreparedStatement pst_selectAll;
		private PreparedStatement pst_selectAllModalidade;
		private PreparedStatement pst_selectAllPesquis;
		private PreparedStatement pst_selectAllPesquisM;
		private PreparedStatement pst_select;
		private PreparedStatement pst_insert;
		private PreparedStatement pst_update;
		private PreparedStatement pst_delete;
		
		Connection io_connection;
		
		public PlanosDAO(Connection connection) throws SQLException {
			
			io_connection = connection;
			pst_selectAll = connection.prepareStatement(is_selectAll);
			pst_selectAllModalidade = connection.prepareStatement(is_selectAllModalidade);
			pst_selectAllPesquis = connection.prepareStatement(is_selectAllPesquisa);
			pst_selectAllPesquisM = connection.prepareStatement(is_selectAllPesquisaM);
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
			 

			 model.setModalidade(rst.getString("modalidade"));
			 model.setPlano(rst.getString("plano"));
			 model.setValor(rst.getDouble("valor_mensal"));
			 arlPlano.add(model);
		 }
				
		return arlPlano;
	}

	
	public List<Plano> SelectAll(final String pesquisa) throws SQLException {
		List<Plano> arlPlano = new ArrayList<Plano>();
		
		Set(pst_selectAllPesquis, 1, "%"+pesquisa+"%");
		
		ResultSet rst = pst_selectAllPesquis.executeQuery();
		 
		while(rst.next()){
			 Plano model = new Plano();
			 
			 model.setModalidade(rst.getString("modalidade"));
			 model.setPlano(rst.getString("plano"));
			 model.setValor(rst.getDouble("valor_mensal"));
			 arlPlano.add(model);
		 }
				
		return arlPlano;
	}
	
	public List<Plano> SelectAllM(final String pesquisa) throws SQLException {
		List<Plano> arlPlano = new ArrayList<Plano>();
		
		Set(pst_selectAllPesquis, 1, "%"+pesquisa+"%");
		
		ResultSet rst = pst_selectAllPesquis.executeQuery();
		 
		while(rst.next()){
			 Plano model = new Plano();
			 
			 model.setModalidade(rst.getString("modalidade"));
			 model.setPlano(rst.getString("plano"));
			 model.setValor(rst.getDouble("valor_mensal"));
			 arlPlano.add(model);
		 }
				
		return arlPlano;
	}
	
	public String[] SelectAllModalidade() throws SQLException {
		ArrayList<String> arlPlano = new ArrayList<String>();
		String[] returno = null;
		
		ResultSet rst = pst_selectAllPesquis.executeQuery();
		 
		while(rst.next()){
			 Plano model = new Plano();
			 
			 model.setModalidade(rst.getString("modalidade"));
			 
			 arlPlano.add(model.getModalidade());
		 }
		returno = new String[arlPlano.size()];
		return arlPlano.toArray(returno);
	}
	
	public Object SelecT(Matricula_Modalidade parameter) throws SQLException {
//		cria o objeto modaliidade
		Plano plano = null;
		
		Set(pst_select, 1, parameter.getPlano());
		
		ResultSet rst = pst_select.executeQuery();
		if(rst.next()) {
			plano = new Plano();
			plano.setModalidade(rst.getString("modalidade"));
			plano.setPlano(rst.getString("plano"));
			plano.setValor(rst.getDouble("valor_mensal"));
		}
		return plano;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
//		cria o objeto modaliidade
		Plano plano = null;
		
		Set(pst_select, 1, (Plano)parameter);
		
		ResultSet rst = pst_select.executeQuery();
		if(rst.next()) {
			plano = new Plano();
			plano.setModalidade(rst.getString("modalidade"));
			plano.setPlano(rst.getString("plano"));
			plano.setValor(rst.getDouble("valor"));
		}
		return plano;
	}

	public String[] SelectPorModalidade (String mod) throws SQLException {
		ArrayList<String> arlPlano = new ArrayList<String>();
		String[] returno = null;
		
		Set(pst_selectAllPesquisM, 1, mod);
		ResultSet rst = pst_selectAllPesquisM.executeQuery();
		 
		while(rst.next()){
			 Plano model = new Plano();
			 
			 model.setModalidade(rst.getString("plano"));
			 
			 arlPlano.add(model.getModalidade());
		 }
		returno = new String[arlPlano.size()];
		return arlPlano.toArray(returno);
	}
	
	@Override
	public void Update(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Plano lo_plano = (Plano)parameter;

		
		Set(pst_update, 2, lo_plano.getPlano());
		Set(pst_update, 1, lo_plano.getValor());
		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			io_connection.commit();
		}
	
	}

	@Override
	public void Insert(Object parameter) throws SQLException {
		pst_insert.clearParameters();
		
		Plano lo_plano = (Plano)parameter;
		Set(pst_insert, 1, lo_plano.getModalidade());
		Set(pst_insert, 2, lo_plano.getPlano());
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
		pst_delete.execute();

		if (pst_delete.getUpdateCount() > 0) {
			io_connection.commit();
		}

        return affectedrows;
	}

}
