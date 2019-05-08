package table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Graduacoes;
import model.Matricula_Modalidade;

public class ModalidadeMatriculaTableModel extends AbstractTableModel {
	
	private List<Matricula_Modalidade> matricula_modalidade;
	
	private String[] colunas = new String[] {"Modalidade", "Graduação", "Plano", "Data Início", "Data Fim"};
	
	public ModalidadeMatriculaTableModel(List<Matricula_Modalidade> matricula_modalidade) {
		this.matricula_modalidade = matricula_modalidade;
	}

	public ModalidadeMatriculaTableModel() {
		this.matricula_modalidade = new ArrayList<Matricula_Modalidade>();
	}

	public int getRowCount() {
		
		int result = 0;

		if (matricula_modalidade != null) {
			result = matricula_modalidade.size();
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
		return String.class;	}
	
	public void setValueAt(Matricula_Modalidade aValue, int rowIndex) {
		Matricula_Modalidade modality = matricula_modalidade.get(rowIndex);

		modality.setModalidade(aValue.getModalidade());
		modality.setGraduacao(aValue.getGraduacao());
		modality.setPlano(aValue.getPlano());
		modality.setData_inicio(aValue.getData_inicio());
		modality.setData_fim(aValue.getData_fim());
				
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
		fireTableCellUpdated(rowIndex, 4);
		
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Matricula_Modalidade matricula_modalidadeSelecionada = matricula_modalidade.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = String.valueOf(matricula_modalidadeSelecionada.getModalidade());
			break;
		case 1:
			valueObject = String.valueOf(matricula_modalidadeSelecionada.getGraduacao());
			break;
		case 2:
			valueObject = String.valueOf(matricula_modalidadeSelecionada.getPlano());
			break;
		case 3:
			if(String.valueOf(matricula_modalidadeSelecionada.getData_inicio()) == "null")
				valueObject = null;
			else
				valueObject = String.valueOf(matricula_modalidadeSelecionada.getData_inicio());				
			break;
		case 4:
			if(String.valueOf(matricula_modalidadeSelecionada.getData_fim()) == "null")
				valueObject = null;
			else
				valueObject = String.valueOf(matricula_modalidadeSelecionada.getData_fim());				
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Plano.class");
		}

		return valueObject;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Matricula_Modalidade getMatricula_Modalidade(int indiceLinha) {
		return matricula_modalidade.get(indiceLinha);
	}
	
	public void addMatricula_Modalidade(Matricula_Modalidade u) {
		matricula_modalidade.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeGraduacao(int indiceLinha) {
		matricula_modalidade.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	
	public void addListaDeMatricula_Modalidade(List<Matricula_Modalidade> novasMatricula_Modalidade) {

		int tamanhoAntigo = getRowCount();		
		matricula_modalidade = novasMatricula_Modalidade;
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		matricula_modalidade.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return matricula_modalidade.isEmpty();
	}


}
