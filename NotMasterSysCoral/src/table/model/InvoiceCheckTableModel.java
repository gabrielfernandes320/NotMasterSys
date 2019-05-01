package table.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Invoice;

public class InvoiceCheckTableModel extends AbstractTableModel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Invoice> invoices;
	private String[] colunas = new String[] { "Matricula", "Aluno" , "Vencimento", "Valor", "Pagamento", "Cancelamento" };
	
	public InvoiceCheckTableModel(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	public InvoiceCheckTableModel() {
		this.invoices = new ArrayList<Invoice>();
	}
	
	public int getRowCount() {
		return invoices.size();
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
		invoice.setData_cancelamento(aValue.getData_cancelamento());
		invoice.setData_pagamento(aValue.getData_pagamento());
		invoice.setData_vencimento(aValue.getData_vencimento());
		invoice.setValor(aValue.getValor());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
		fireTableCellUpdated(rowIndex, 4);
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Invoice invoice = invoices.get(rowIndex);

		switch (columnIndex) {
		case 0:
			invoice.setCodigo_matricula((int) aValue);
		case 1:
			invoice.setData_cancelamento((Date) aValue);
		case 2:
			invoice.setData_pagamento((Date) aValue);
		case 3:
			invoice.setData_vencimento((Date) aValue);
		case 4:
			invoice.setValor((Double) aValue);
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	/*public Object getValueAt(int rowIndex, int columnIndex) {
		Invoice invoiceSelecionado = invoices.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = invoiceSelecionado.getInvoice();
			break;
		case 1:
			valueObject = invoiceSelecionado.getPerfil();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Invoice.class");
		}

		return valueObject;
	}*/

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Invoice getInvoice(int indiceLinha) {
		return invoices.get(indiceLinha);
	}

	public void addInvoice(Invoice u) {
		invoices.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeInvoice(int indiceLinha) {
		invoices.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeInvoices(List<Invoice> novosInvoices) {

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

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}


}
