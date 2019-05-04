package table.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Assiduidade;

public class AssiduidadeTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -3586211638575736174L;

	private List<Assiduidade> assiduidade;
	private String[] colunas = new String[] { "Assiduidade" };

	public AssiduidadeTableModel(List<Assiduidade> assiduidade) {
		this.assiduidade = assiduidade;
	}

	public AssiduidadeTableModel() {
		this.assiduidade = new ArrayList<Assiduidade>();
	}

	public int getRowCount() {
		return assiduidade.size();
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

	public void setValueAt(Assiduidade aValue, int rowIndex) {
		Assiduidade aluno = assiduidade.get(rowIndex);

		
		aluno.setData_entrada(aValue.getData_entrada());

		fireTableCellUpdated(rowIndex, 0);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Assiduidade aluno = assiduidade.get(rowIndex);

	switch (columnIndex) {
		case 0:
		aluno.setData_entrada(Timestamp.valueOf(aValue.toString()));
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Assiduidade assiduidadeSelecionado = assiduidade.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = String.valueOf(assiduidadeSelecionado.getData_entrada());
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Assiduidade.class");
		}

		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Assiduidade getAssiduidade(int indiceLinha) {
		return assiduidade.get(indiceLinha);
	}

	public void addAssiduidade(Assiduidade u) {
		assiduidade.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeAssiduidade(int indiceLinha) {
		assiduidade.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeAssiduidades(List<Assiduidade> novosAssiduidade) {

		int tamanhoAntigo = getRowCount();		
		assiduidade = novosAssiduidade;
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		assiduidade.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return assiduidade.isEmpty();
	}

}
