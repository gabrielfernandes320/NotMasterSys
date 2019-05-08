package table.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import com.sun.prism.paint.Color;
import javafx.util.converter.DateTimeStringConverter;
import model.Invoice;

public class InvoicesCheckTableModel extends AbstractTableModel  {

	private List<Invoice> invoices;
	private String[] colunas = new String[] { "Codigo Matricula", "Nome Aluno", "Data de vencimento", "Valor",
			"Data de pagamento", "Data de Cancelamento" };

	public InvoicesCheckTableModel(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public InvoicesCheckTableModel() {
		this.invoices = new ArrayList<Invoice>();
	}

	public int getRowCount() {

		int result = 0;

		if (invoices != null) {
			result = invoices.size();
		}

		return result;

	}

	public int getColumnCount() {
		return colunas.length;
	}

	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public void setValueAt(Invoice aValue, int rowIndex) {
		Invoice invoice = invoices.get(rowIndex);

		invoice.setCodigo_matricula(aValue.getCodigo_matricula());
		invoice.setNome_aluno(aValue.getNome_aluno());
		invoice.setData_vencimento(aValue.getData_vencimento());
		invoice.setValor(aValue.getValor());
		invoice.setData_pagamento(aValue.getData_pagamento());
		invoice.setData_cancelamento(aValue.getData_cancelamento());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
		fireTableCellUpdated(rowIndex, 4);
		fireTableCellUpdated(rowIndex, 5);
	}

	@Override
	/*
	 * public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	 * Invoice invoice = invoices.get(rowIndex); SimpleDateFormat formatter = new
	 * SimpleDateFormat("dd/MM/yyyy"); switch (columnIndex) { case 0:
	 * invoice.setCodigo_matricula(Integer.parseInt(aValue.toString())); break; case
	 * 1: invoice.setNome_aluno(aValue.toString()); break; case 3:
	 * invoice.setValor(Double.parseDouble(aValue.toString())); break; case 5: try {
	 * invoice.setData_cancelamento(formatter.parse(aValue.toString())); } catch
	 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } break; case 4: try {
	 * invoice.setData_pagamento(formatter.parse(aValue.toString())); } catch
	 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } break; case 2: try {
	 * invoice.setData_vencimento(formatter.parse(aValue.toString())); } catch
	 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } break; default: System.err.println("Índice da coluna inválido"); }
	 * fireTableCellUpdated(rowIndex, columnIndex); }
	 */

	public Object getValueAt(int rowIndex, int columnIndex) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Invoice invoiceSelecionado = invoices.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = String.valueOf(invoiceSelecionado.getCodigo_matricula());
			break;
		case 1:
			valueObject = String.valueOf(invoiceSelecionado.getNome_aluno());
			break;
		case 2:
			valueObject = df.format((invoiceSelecionado.getData_vencimento()));
			break;
		case 3:
			valueObject = String.valueOf(invoiceSelecionado.getValor());
			break;
		case 4:
			if(String.valueOf(invoiceSelecionado.getData_pagamento()) == "null")
				valueObject = null;
			else
				valueObject = df.format((invoiceSelecionado.getData_pagamento()));				
			break;
		case 5:
			if(String.valueOf(invoiceSelecionado.getData_cancelamento()) == "null")
				valueObject = null;
			else
				valueObject = df.format((invoiceSelecionado.getData_cancelamento()));	
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Invoice.class");
		}

		return valueObject;
	}

	List<Color> rowColours = Arrays.asList(Color.RED, Color.GREEN);

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Invoice getInvoice(int indiceLinha) {
		return invoices.get(indiceLinha);
	}

	public void addInvoice(Invoice i) {
		invoices.add(i);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeInvoice(int indiceLinha) {
		invoices.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeInvoice(List<Invoice> novosInvoices) {

		int tamanhoAntigo = getRowCount();
		invoices = novosInvoices;
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		invoices.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return invoices.isEmpty();
	}

}
