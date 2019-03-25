package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ModalityFrm extends JInternalFrame {
	private JTextField txfModality;
	private JTextField txfGraduate;
	private JButton btnRemove;
	private JButton btnAdd;
	private JButton btnSearch;
	private JButton btnSave;
	private JButton btnOk;
	private JScrollPane scrollPaneGraduate;
	private JLabel lblModality;
	private JLabel lblGraduate;
	private JTable tableGraduate;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModalityFrm frame = new ModalityFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModalityFrm() {
		setTitle("Modalidades");
		setClosable(true);
		setBounds(100, 100, 475, 318);
		getContentPane().setLayout(null);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setEnabled(false);
		btnSearch.setBounds(20, 11, 88, 37);
		getContentPane().add(btnSearch);
				
		JButton btnRemove = new JButton("Remover");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemove.setBounds(198, 11, 88, 37);
		getContentPane().add(btnRemove);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(288, 11, 88, 37);
		getContentPane().add(btnSave);
		
		JLabel lblModality = new JLabel("Modalidade:");
		lblModality.setEnabled(false);
		lblModality.setBounds(10, 54, 72, 22);
		getContentPane().add(lblModality);
		
		JLabel lblGraduate = new JLabel(" Gradua\u00E7\u00E3o:");
		lblGraduate.setEnabled(false);
		lblGraduate.setBounds(10, 81, 72, 14);
		getContentPane().add(lblGraduate);
		
		txfModality = new JTextField();
		txfModality.setEnabled(false);
		txfModality.setBounds(92, 55, 346, 20);
		getContentPane().add(txfModality);
		txfModality.setColumns(10);
		
		txfGraduate = new JTextField();
		txfGraduate.setEnabled(false);
		txfGraduate.setBounds(92, 78, 286, 20);
		getContentPane().add(txfGraduate);
		txfGraduate.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.setBounds(380, 78, 58, 18);
		getContentPane().add(btnOk);
		
		JScrollPane scrollPaneGraduate = new JScrollPane();
		scrollPaneGraduate.setEnabled(false);
		scrollPaneGraduate.setToolTipText("");
		scrollPaneGraduate.setBounds(10, 114, 428, 146);
		getContentPane().add(scrollPaneGraduate);
		
		scrollPaneGraduate.setViewportView(tableGraduate);
		
		tableGraduate = new JTable();
		tableGraduate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableGraduate.setCellSelectionEnabled(true);
		tableGraduate.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Gradua\u00E7\u00F5es"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableGraduate.getColumnModel().getColumn(0).setResizable(false);
		tableGraduate.setEnabled(false);
		scrollPaneGraduate.setViewportView(tableGraduate);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnSearch.setEnabled(true);
				lblModality.setEnabled(true);
				lblGraduate.setEnabled(true);
				txfModality.setEnabled(true);
				txfGraduate.setEnabled(true);
				btnOk.setEnabled(true);
				scrollPaneGraduate.setEnabled(true);
				btnSave.setEnabled(true);
				btnRemove.setEnabled(true);
				tableGraduate.setEnabled(true);

			}
		});
		btnAdd.setBounds(109, 11, 88, 37);
		getContentPane().add(btnAdd);

		
		JLabel lblMessage = new JLabel("Duplo clique na linha da gradua\u00E7\u00E3o para remov\u00EA-la.");
		lblMessage.setBounds(11, 264, 414, 14);
		getContentPane().add(lblMessage);

	}
}
