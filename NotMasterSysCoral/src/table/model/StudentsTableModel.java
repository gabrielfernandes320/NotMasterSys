package table.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Aluno;

public class StudentsTableModel extends AbstractTableModel  {

	private List<Aluno> alunos;
	private String[] colunas = new String[] {"Aluno", "Data de Nascimento", "Observação"};
	
	public StudentsTableModel(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public StudentsTableModel() {
		this.alunos = new ArrayList<Aluno>();
	}
	
	public int getRowCount() {
		return alunos.size();
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

	public void setValueAt(Aluno aValue, int rowIndex) {
		
		Aluno Aluno = alunos.get(rowIndex);

		Aluno.setAluno(aValue.getAluno());
		Aluno.setData_nascimento(aValue.getData_nascimento());
		Aluno.setObservacao(aValue.getObservacao());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 3);
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Aluno Aluno = alunos.get(rowIndex);

		switch (columnIndex) {
		case 0:
			Aluno.setAluno(aValue.toString());
			break;
		case 1:
			Aluno.setData_nascimento((Date)aValue);
			break;
		case 2:
			Aluno.setObservacao(aValue.toString());
			break;
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Aluno alunoselecionado = alunos.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = alunoselecionado.getAluno();
			break;
		case 1:
			valueObject = alunoselecionado.getData_nascimento().toString();
			break;
		case 2:
			valueObject = alunoselecionado.getObservacao();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Aluno.class");
		}

		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Aluno getAluno(int indiceLinha) {
		return alunos.get(indiceLinha);
	}

	public void addAluno(Aluno u) {
		alunos.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeAluno(int indiceLinha) {
		alunos.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDealunos(List<Aluno> novosalunos) {

		int tamanhoAntigo = getRowCount();		
		alunos = novosalunos;
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		alunos.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return alunos.isEmpty();
	}


}