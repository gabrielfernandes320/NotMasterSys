package table.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Plano;

public class PlansTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -3586211638575736174L;

	private List<Plano> planos;
	private String[] colunas = new String[] { "Plano", "Modalidade", "Valor Mensal" };

	public PlansTableModel(List<Plano> planos) {
		this.planos = planos;
	}

	public PlansTableModel() {
		this.planos = new ArrayList<Plano>();
	}

	public int getRowCount() {
		return planos.size();
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

	public void setValueAt(Plano aValue, int rowIndex) {
		Plano aluno = planos.get(rowIndex);

		aluno.setPlano(aValue.getPlano());
		aluno.setModalidade(aValue.getModalidade());
		aluno.setValor(aValue.getValor());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Plano aluno = planos.get(rowIndex);

		switch (columnIndex) {
		case 0:
			aluno.setPlano(aValue.toString());
		case 1:
			aluno.setModalidade(aValue.toString());
		case 2:
			aluno.setValor(Double.parseDouble(aValue.toString()));
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Plano planoSelecionado = planos.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = planoSelecionado.getPlano();
			break;
		case 1:
			valueObject = planoSelecionado.getModalidade();
			break;
		case 2:
			valueObject = String.valueOf(planoSelecionado.getValor());
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Plano.class");
		}

		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Plano getPlano(int indiceLinha) {
		return planos.get(indiceLinha);
	}

	public void addPlano(Plano u) {
		planos.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removePlano(int indiceLinha) {
		planos.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDePlanos(List<Object> novosPlanos) {

		int tamanhoAntigo = getRowCount();
		planos.add((Plano) novosPlanos);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		planos.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return planos.isEmpty();
	}

}
