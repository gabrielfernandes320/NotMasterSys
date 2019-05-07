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
		private String is_selectAll = "SELECT fm.codigo_matricula,a.aluno ,fm.data_vencimento, fm.valor, fm.data_pagamento, fm.data_cancelamento\r\n" + 
				"  FROM public.faturas_matriculas fm\r\n" + 
				"  INNER JOIN alunos a ON fm.codigo_matricula = a.codigo_aluno\r\n" + 
				"\r\n" + 
				" WHERE data_vencimento BETWEEN '1?' AND '2?'";
		
		private String is_select = "SELECT codigo_matricula, data_vencimento, valor, data_pagamento, data_cancelamento\r\n" + 
				"  FROM public.faturas_matriculas";
		
		private String is_select_pendigns_invocies = "SELECT fm.codigo_matricula,a.aluno ,fm.data_vencimento, fm.valor, fm.data_pagamento, fm.data_cancelamento\\r\\n\" + \r\n" + 
				"				\"  FROM public.faturas_matriculas fm\\r\\n\" + \r\n" + 
				"				\"  INNER JOIN alunos a ON fm.codigo_matricula = a.codigo_aluno\\r\\n\" + \r\n" + 
				"				\"\\r\\n WHERE data_pagamento is null AND data_vencimento BETWEEN '1?' AND '2?'";
		
		private String is_select_payeid_invocies = "SELECT fm.codigo_matricula,a.aluno ,fm.data_vencimento, fm.valor, fm.data_pagamento, fm.data_cancelamento\\r\\n\" + \r\n" + 
				"				\"  FROM public.faturas_matriculas fm\\r\\n\" + \r\n" + 
				"				\"  INNER JOIN alunos a ON fm.codigo_matricula = a.codigo_aluno\\r\\n\" + \r\n" + 
				"				\"\\r\\n WHERE data_pagamento is not null AND data_vencimento BETWEEN '1?' AND '2?'";
		
		private String is_select_canceled_invocies = "SELECT fm.codigo_matricula,a.aluno ,fm.data_vencimento, fm.valor, fm.data_pagamento, fm.data_cancelamento\\r\\n\" + \r\n" + 
				"				\"  FROM public.faturas_matriculas fm\\r\\n\" + \r\n" + 
				"				\"  INNER JOIN alunos a ON fm.codigo_matricula = a.codigo_aluno\\r\\n\" + \r\n" + 
				"				\"\\r\\n WHERE data_cancelamento is not null AND data_vencimento BETWEEN '1?' AND '2?'";
		
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
			//pst_selectAll = connection.prepareStatement(is_selectAll);
			pst_selectCanceled = connection.prepareStatement(is_select_canceled_invocies);
			pst_selectPayed = connection.prepareStatement(is_select_payeid_invocies);
			pst_selectPendings = connection.prepareStatement(is_select_pendigns_invocies);
			pst_insert = connection.prepareStatement(is_insert);	
			pst_delete = connection.prepareStatement(is_delete);
			pst_update = connection.prepareStatement(is_update);
			
			
		}

	@Override
	public List<Object> SelectAll(Object parameter) throws SQLException {
 		List<Object> arInvoice = new ArrayList<Object>();
		Invoice lo_invoice = (Invoice)parameter;
		
		is_selectAll = is_selectAll.replace("1?",lo_invoice.getInitial_date());
		is_selectAll = is_selectAll.replace("2?",lo_invoice.getFinal_date());
		pst_selectAll = io_connection.prepareStatement(is_selectAll);
		ResultSet rst = pst_selectAll.executeQuery();;
		
		while (rst.next()) {
			
			Invoice model = new Invoice();
			model.setCodigo_matricula(rst.getInt("codigo_matricula"));
			model.setNome_aluno(rst.getString("aluno"));
			model.setData_vencimento(rst.getDate("data_vencimento"));
			model.setData_cancelamento(rst.getDate("data_cancelamento"));
			model.setValor(rst.getDouble("valor"));
			model.setData_pagamento(rst.getDate("data_pagamento"));
			arInvoice.add(model);
			
		}
		
		return arInvoice;
	}
	
	public List<Object> SelectPayedInvoices(Object parameter) throws SQLException {
		List<Object> arInvoice = new ArrayList<Object>();
		Invoice lo_invoice = (Invoice)parameter;
		
		is_select_payeid_invocies = is_select_payeid_invocies.replace("1?",lo_invoice.getInitial_date());
		is_select_payeid_invocies = is_select_payeid_invocies.replace("2?",lo_invoice.getFinal_date());
		pst_selectPayed = io_connection.prepareStatement(is_select_payeid_invocies);
		ResultSet rst = pst_selectPayed.executeQuery();;
		
		while (rst.next()) {
			
			Invoice model = new Invoice();
			model.setCodigo_matricula(rst.getInt("codigo_matricula"));
			model.setNome_aluno(rst.getString("aluno"));
			model.setData_vencimento(rst.getDate("data_vencimento"));
			model.setData_cancelamento(rst.getDate("data_cancelamento"));
			model.setValor(rst.getDouble("valor"));
			model.setData_pagamento(rst.getDate("data_pagamento"));
			arInvoice.add(model);
			
		}
		
		return arInvoice;
	}
	
	public List<Object> SelectCanceledInvoices(Object parameter) throws SQLException {
		List<Object> arInvoice = new ArrayList<Object>();
		Invoice lo_invoice = (Invoice)parameter;
		
		is_select_canceled_invocies = is_select_canceled_invocies.replace("1?",lo_invoice.getInitial_date());
		is_select_canceled_invocies = is_select_canceled_invocies.replace("2?",lo_invoice.getFinal_date());
		pst_selectCanceled = io_connection.prepareStatement(is_select_canceled_invocies);
		ResultSet rst = pst_selectCanceled.executeQuery();;
		
		while (rst.next()) {
			
			Invoice model = new Invoice();
			model.setCodigo_matricula(rst.getInt("codigo_matricula"));
			model.setNome_aluno(rst.getString("aluno"));
			model.setData_vencimento(rst.getDate("data_vencimento"));
			model.setData_cancelamento(rst.getDate("data_cancelamento"));
			model.setValor(rst.getDouble("valor"));
			model.setData_pagamento(rst.getDate("data_pagamento"));
			arInvoice.add(model);
			
		}
		
		return arInvoice;
	}
	
	public List<Object> SelectPendigInvoices(Object parameter) throws SQLException {
		List<Object> arInvoice = new ArrayList<Object>();
		Invoice lo_invoice = (Invoice)parameter;
		
		is_select_pendigns_invocies = is_select_pendigns_invocies.replace("1?",lo_invoice.getInitial_date());
		is_select_pendigns_invocies = is_select_pendigns_invocies.replace("2?",lo_invoice.getFinal_date());
		pst_selectPendings = io_connection.prepareStatement(is_select_pendigns_invocies);
		ResultSet rst = pst_selectPendings.executeQuery();;
		
		while (rst.next()) {
			
			Invoice model = new Invoice();
			model.setCodigo_matricula(rst.getInt("codigo_matricula"));
			model.setNome_aluno(rst.getString("aluno"));
			model.setData_vencimento(rst.getDate("data_vencimento"));
			model.setData_cancelamento(rst.getDate("data_cancelamento"));
			model.setValor(rst.getDouble("valor"));
			model.setData_pagamento(rst.getDate("data_pagamento"));
			arInvoice.add(model);
			
		}
		
		return arInvoice;
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

	@Override
	public List<Object> SelectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
