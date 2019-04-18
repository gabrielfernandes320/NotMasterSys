package table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Graduacoes;
import model.Plano;
import model.Usuario;

public class GraduacoesTableModel extends AbstractTableModel {
	
	private List<Graduacoes> graduacoes;
	private String[] colunas = new String[] {"Graduacoes"};
	
	public List<Graduacoes> getGraduacoes() {
		return graduacoes;
	}


	public GraduacoesTableModel(List<Graduacoes> graduation) {
		this.graduacoes = graduation;
	}

	public GraduacoesTableModel() {
		this.graduacoes = new ArrayList<Graduacoes>();
	}

	public int getRowCount() {
		return graduacoes.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}
	
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;	}
	
	public void setValueAt(Graduacoes aValue, int rowIndex) {
		Graduacoes graduacao = graduacoes.get(rowIndex);

		graduacao.setId_modality(aValue.getId_modality());
		graduacao.setGraduations(aValue.getGraduations());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
	}
	
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Graduacoes graduacao = graduacoes.get(rowIndex);

		switch (columnIndex) {
		case 0:
			graduacao.setId_modality(aValue.toString());
		case 1:
			graduacao.setGraduations(aValue.toString());
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}


	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Graduacoes graduacaoSelecionada = graduacoes.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = graduacaoSelecionada.getGraduations();
			break;
		case 1:
			valueObject = graduacaoSelecionada.getGraduations();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Plano.class");
		}

		return valueObject;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Graduacoes getGraduacao(int indiceLinha) {
		return graduacoes.get(indiceLinha);
	}
	
	public void addGraduacao(Graduacoes u) {
		graduacoes.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeGraduacao(int indiceLinha) {
		graduacoes.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	
	public void addListaDeGraduacoes(List<Graduacoes> novasGraduacoes) {

		int tamanhoAntigo = getRowCount();		
		graduacoes = novasGraduacoes;
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		graduacoes.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return graduacoes.isEmpty();
	}

	
	
	
	
	
}
