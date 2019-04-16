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


	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Graduacoes graduacaoSelecionada = graduacoes.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = graduacaoSelecionada.getId_modality();
			break;
		case 1:
			valueObject = graduacaoSelecionada.getGraduations();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Plano.class");
		}

		return valueObject;
	}
	
}
