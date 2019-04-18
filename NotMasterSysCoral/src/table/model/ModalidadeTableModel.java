package table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Graduacoes;
import model.Modalidades;

public class ModalidadeTableModel extends AbstractTableModel {
	
	private List<Modalidades> modalidade;
	private String[] colunas = new String[] {"Modalidades"};
	
	public ModalidadeTableModel(List<Modalidades> modalidade) {
		this.modalidade = modalidade;
	}

	public ModalidadeTableModel() {
		this.modalidade = new ArrayList<Modalidades>();
	}

	public int getRowCount() {
		return modalidade.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}
	
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;	}
	
	public void setValueAt(Modalidades aValue, int rowIndex) {
		Modalidades modality = modalidade.get(rowIndex);

		modality.setModalidade(aValue.getModalidade());
		
		fireTableCellUpdated(rowIndex, 0);
	}
	
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Modalidades modality = modalidade.get(rowIndex);

		switch (columnIndex) {
		case 0:
			modality.setModalidade(aValue.toString());
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}


	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Modalidades modalidadeSelecionada = modalidade.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = modalidadeSelecionada.getModalidade();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Plano.class");
		}

		return valueObject;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Modalidades getModalidade(int indiceLinha) {
		return modalidade.get(indiceLinha);
	}
	
	public void addModalidade(Modalidades u) {
		modalidade.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeGraduacao(int indiceLinha) {
		modalidade.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	
	public void addListaDeModalidades(List<Modalidades> novasModalidades) {

		int tamanhoAntigo = getRowCount();		
		modalidade = novasModalidades;
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		modalidade.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return modalidade.isEmpty();
	}


}
