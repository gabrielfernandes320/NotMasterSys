package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Invoice;
import model.Usuario;

public class InvoiceDAO extends MasterDAO{
	
	 
		private String is_delete = "";
		private String is_selectAll = "SELECT codigo_matricula, data_vencimento, valor, data_pagamento, data_cancelamento\r\n" + 
				"  FROM public.faturas_matriculas";
		private String is_select = "SELECT codigo_matricula, data_vencimento, valor, data_pagamento, data_cancelamento\r\n" + 
				"  FROM public.faturas_matriculas";
		private String is_select_pendigns_invocies = "SELECT codigo_matricula, data_vencimento, valor, data_pagamento, data_cancelamento\r\n" + 
				"  FROM public.faturas_matriculas WHERE data_pagamento is null";
		private String is_select_payeid_invocies = "SELECT codigo_matricula, data_vencimento, valor, data_pagamento, data_cancelamento\r\n" + 
				"  FROM public.faturas_matriculas WHERE data_pagamento is not null";
		private String is_select_canceled_invocies = "SELECT codigo_matricula, data_vencimento, valor, data_pagamento, data_cancelamento\r\n" + 
				"  FROM public.faturas_matriculas WHERE data_cancelamento is not null;\r\n";
		private String is_insert = "";
		private String is_update = "";
		
		private PreparedStatement pst_selectAll;
		private PreparedStatement pst_select;
		private PreparedStatement pst_selectPendings;
		private PreparedStatement pst_selectCanceled;
		private PreparedStatement pst_selectPayed;
		private PreparedStatement pst_insert;
		private PreparedStatement pst_update;
		private PreparedStatement pst_delete;
		
		Connection io_connection;
		
		public InvoiceDAO(Connection connection) throws SQLException {
			
			io_connection = connection;
			pst_selectAll = connection.prepareStatement(is_selectAll);
			pst_selectCanceled = connection.prepareStatement(is_select_canceled_invocies);
			pst_selectPayed = connection.prepareStatement(is_select_payeid_invocies);
			pst_selectPendings = connection.prepareStatement(is_select_pendigns_invocies);
			pst_insert = connection.prepareStatement(is_insert);	
			pst_delete = connection.prepareStatement(is_delete);
			pst_update = connection.prepareStatement(is_update);
			
			
		}

	@Override
	public List<Object> SelectAll() throws SQLException {
		List<Object> arInvoice = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			
			Invoice model = new Invoice();
			model.setCodigo_matricula(rst.getInt("")("usuario"));
			model.setUsuario(rst.getString("perfil"));
			arlUsuario.add(model);
			
		}
		
		return arlUsuario;
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
	public void Insert(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int Delete(Object parameter) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	

}