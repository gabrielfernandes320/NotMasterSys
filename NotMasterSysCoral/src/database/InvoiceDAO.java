package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Assiduidade;
import model.Invoice;
import model.Usuario;

public class InvoiceDAO extends MasterDAO {


	private String is_selectAllP = "SELECT alunos.aluno , faturas_matriculas.data_vencimento, faturas_matriculas.valor, faturas_matriculas.data_pagamento, faturas_matriculas.data_cancelamento \r\n" + 
			"FROM faturas_matriculas \r\n" + 
			"INNER JOIN matriculas ON faturas_matriculas.codigo_matricula = matriculas.codigo_matricula \r\n" + 
			"INNER JOIN alunos ON alunos.codigo_aluno = matriculas.codigo_aluno\r\n" + 
			"WHERE faturas_matriculas.codigo_matricula = ?";

	private String is_selectAll = "SELECT faturas_matriculas.codigo_matricula, alunos.aluno , faturas_matriculas.data_vencimento, faturas_matriculas.valor, faturas_matriculas.data_pagamento, faturas_matriculas.data_cancelamento FROM faturas_matriculas  INNER JOIN matriculas ON faturas_matriculas.codigo_matricula = matriculas.codigo_matricula  INNER JOIN alunos ON alunos.codigo_aluno = matriculas.codigo_aluno WHERE faturas_matriculas.data_vencimento BETWEEN ? AND ?";

	private String is_select_pendigns_invocies = "SELECT faturas_matriculas.codigo_matricula, alunos.aluno , faturas_matriculas.data_vencimento, faturas_matriculas.valor, faturas_matriculas.data_pagamento, faturas_matriculas.data_cancelamento \r\n"
			+ "FROM faturas_matriculas  \r\n"
			+ "INNER JOIN matriculas ON faturas_matriculas.codigo_matricula = matriculas.codigo_matricula \r\n"
			+ "INNER JOIN alunos ON alunos.codigo_aluno = matriculas.codigo_aluno\r\n"
			+ "WHERE faturas_matriculas.data_pagamento is null AND faturas_matriculas.data_cancelamento is null AND faturas_matriculas.data_vencimento BETWEEN ? AND ?\r\n"
			+ "																";
	private String is_select_payeid_invocies = "SELECT faturas_matriculas.codigo_matricula, alunos.aluno , faturas_matriculas.data_vencimento, faturas_matriculas.valor, faturas_matriculas.data_pagamento, faturas_matriculas.data_cancelamento FROM faturas_matriculas  INNER JOIN matriculas ON faturas_matriculas.codigo_matricula = matriculas.codigo_matricula  INNER JOIN alunos ON alunos.codigo_aluno = matriculas.codigo_aluno WHERE faturas_matriculas.data_pagamento is not null AND faturas_matriculas.data_vencimento BETWEEN ? AND ?";

	private String is_select_canceled_invocies = "SELECT faturas_matriculas.codigo_matricula, alunos.aluno , faturas_matriculas.data_vencimento, faturas_matriculas.valor, faturas_matriculas.data_pagamento, faturas_matriculas.data_cancelamento FROM faturas_matriculas  INNER JOIN matriculas ON faturas_matriculas.codigo_matricula = matriculas.codigo_matricula  INNER JOIN alunos ON alunos.codigo_aluno = matriculas.codigo_aluno WHERE faturas_matriculas.data_cancelamento is not null AND faturas_matriculas.data_vencimento BETWEEN ? AND ?";

	private String is_insert = "INSERT INTO faturas_matriculas		" + " (								"
			+ "		codigo_matricula,			" + "		data_vencimento,			"
			+ "		valor,						" + "		data_pagamento,				"
			+ "		data_cancelamento			" + " )								"
			+ "	VALUES							" + " (								"
			+ "			?,						" + "			?,						"
			+ "			?,						" + "			?,						" + "			?" + " )";;

	private String is_payment = "UPDATE public.faturas_matriculas\r\n" + "   SET data_pagamento = CURRENT_TIMESTAMP\r\n"
			+ " WHERE codigo_matricula = ? and valor = ?";

	private String is_canceling = "UPDATE public.faturas_matriculas\r\n"
			+ "   SET data_cancelamento = CURRENT_TIMESTAMP\r\n" + " WHERE codigo_matricula = ? and valor = ?";

	private String is_changevalue = "UPDATE public.faturas_matriculas\r\n" + "   SET valor = ?\r\n"
			+ " WHERE codigo_matricula = ? and valor = ?";
	

	private PreparedStatement pst_selectAll;
	private PreparedStatement pst_selectPendings;
	private PreparedStatement pst_selectCanceled;
	private PreparedStatement pst_selectPayed;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_payment;
	private PreparedStatement pst_cancel;
	private PreparedStatement pst_changevalue;
	private PreparedStatement pst_selectAllPesquisa;

	Connection io_connection;

	public InvoiceDAO(Connection connection) throws SQLException {

		io_connection = connection;
		pst_selectAll = connection.prepareStatement(is_selectAll);
		pst_selectCanceled = connection.prepareStatement(is_select_canceled_invocies);
		pst_selectPayed = connection.prepareStatement(is_select_payeid_invocies);
		pst_selectPendings = connection.prepareStatement(is_select_pendigns_invocies);
		pst_insert = connection.prepareStatement(is_insert);
		pst_payment = connection.prepareStatement(is_payment);
		pst_cancel = connection.prepareStatement(is_canceling);
		pst_changevalue = connection.prepareStatement(is_changevalue);
		pst_selectAllPesquisa = connection.prepareStatement(is_selectAllP);

	}

	public List<Invoice> SelectAllP(final int pesquisa) throws SQLException {
		
		List<Invoice> arlInvoice = new ArrayList<Invoice>();

		Set(pst_selectAllPesquisa, 1, pesquisa);

		ResultSet rst = pst_selectAllPesquisa.executeQuery();

		while (rst.next()) {
			
			Invoice model = new Invoice();

			model.setNome_aluno(rst.getString("aluno"));
			model.setData_vencimento(rst.getDate("data_vencimento"));
			model.setValor(rst.getDouble("valor"));
			model.setData_cancelamento(rst.getDate("data_cancelamento"));
			model.setData_pagamento(rst.getDate("data_pagamento"));
			
			arlInvoice.add(model);
			
		}

		return arlInvoice;
	}

	@Override
	public List<Object> SelectAll(Object parameter) throws SQLException {

		List<Object> arInvoice = new ArrayList<Object>();
		Invoice lo_invoice = (Invoice) parameter;

		pst_selectAll.setDate(1, (Date) lo_invoice.getInitial_date());
		pst_selectAll.setDate(2, (Date) lo_invoice.getFinal_date());
		ResultSet rst = pst_selectAll.executeQuery();
		;

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
		Invoice lo_invoice = (Invoice) parameter;

		pst_selectPayed.setDate(1, (Date) lo_invoice.getInitial_date());
		pst_selectPayed.setDate(2, (Date) lo_invoice.getFinal_date());
		ResultSet rst = pst_selectPayed.executeQuery();
		;

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
		Invoice lo_invoice = (Invoice) parameter;

		pst_selectCanceled.setDate(1, (Date) lo_invoice.getInitial_date());
		pst_selectCanceled.setDate(2, (Date) lo_invoice.getFinal_date());
		ResultSet rst = pst_selectCanceled.executeQuery();
		;

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
		Invoice lo_invoice = (Invoice) parameter;

		pst_selectPendings.setDate(1, (Date) lo_invoice.getInitial_date());
		pst_selectPendings.setDate(2, (Date) lo_invoice.getFinal_date());
		ResultSet rst = pst_selectPendings.executeQuery();
		;

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

	public int PayInvoice(Invoice parameter) throws SQLException {

		int IsPaymentSucessfull = 0;

		Invoice lo_invoice = (Invoice) parameter;

		Set(pst_payment, 1, lo_invoice.getCodigo_matricula());
		Set(pst_payment, 2, lo_invoice.getValor());

		IsPaymentSucessfull = pst_payment.executeUpdate();

		return IsPaymentSucessfull;

	}

	public int CancelInvoice(Invoice parameter) throws SQLException {

		int IsCancellingSucessfull = 0;

		Invoice lo_invoice = (Invoice) parameter;

		Set(pst_cancel, 1, lo_invoice.getCodigo_matricula());
		Set(pst_cancel, 2, lo_invoice.getValor());

		IsCancellingSucessfull = pst_cancel.executeUpdate();

		return IsCancellingSucessfull;

	}

	public int ChangeInvoicePrice(Invoice parameter, Double newvalue) throws SQLException {

		int IsChangeSucessfull = 0;

		Invoice lo_invoice = (Invoice) parameter;

		Set(pst_changevalue, 1, newvalue);
		Set(pst_changevalue, 2, lo_invoice.getCodigo_matricula());
		Set(pst_changevalue, 3, lo_invoice.getValor());

		IsChangeSucessfull = pst_changevalue.executeUpdate();

		return IsChangeSucessfull;

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
		pst_insert.clearParameters();

		Invoice lo_invoice = (Invoice) parameter;

		Set(pst_insert, 1, lo_invoice.getCodigo_matricula());
		Set(pst_insert, 2, lo_invoice.getData_vencimento());
		Set(pst_insert, 3, lo_invoice.getValor());
		Set(pst_insert, 4, lo_invoice.getData_pagamento());
		Set(pst_insert, 5, lo_invoice.getData_cancelamento());
		pst_insert.execute();

		if (pst_insert.getUpdateCount() > 0) {
			io_connection.commit();
		}

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
