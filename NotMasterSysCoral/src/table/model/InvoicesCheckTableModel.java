package table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Usuario;
import model.Usuario;

public class InvoicesCheckTableModel extends AbstractTableModel  {

	private List<Usuario> usuarios;
	private String[] colunas = new String[] { "Codigo Matricula", "Data de vencimento", "Valor", "Data de pagamento", "Data de Cancelamento" };
	
	public InvoicesCheckTableModel(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public InvoicesCheckTableModel() {
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public int getRowCount() {
		return usuarios.size();
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

	public void setValueAt(Usuario aValue, int rowIndex) {
		Usuario usuario = usuarios.get(rowIndex);

		usuario.setUsuario(aValue.getUsuario());
		usuario.setPerfil(aValue.getPerfil());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Usuario usuario = usuarios.get(rowIndex);

		switch (columnIndex) {
		case 0:
			usuario.setUsuario(aValue.toString());
		case 1:
			usuario.setPerfil(aValue.toString());
		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Usuario usuarioSelecionado = usuarios.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = usuarioSelecionado.getUsuario();
			break;
		case 1:
			valueObject = usuarioSelecionado.getPerfil();
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Usuario.class");
		}

		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Usuario getUsuario(int indiceLinha) {
		return usuarios.get(indiceLinha);
	}

	public void addUsuario(Usuario u) {
		usuarios.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeUsuario(int indiceLinha) {
		usuarios.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeUsuarios(List<Usuario> novosUsuarios) {

		int tamanhoAntigo = getRowCount();		
		usuarios = novosUsuarios;
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		usuarios.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return usuarios.isEmpty();
	}


}
