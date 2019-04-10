package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectionFactory;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

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
		btnSearch.setBackground(SystemColor.menu);
		btnSearch.setIcon(new ImageIcon(ModalityFrm.class.getResource("/view/images/localizar.png")));
		btnSearch.setEnabled(true);
		btnSearch.setBounds(20, 11, 95, 37);
		getContentPane().add(btnSearch);
				
		JButton btnRemove = new JButton("Remover");
		btnRemove.setBackground(SystemColor.menu);
		btnRemove.setIcon(new ImageIcon(ModalityFrm.class.getResource("/view/images/remover.png")));
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemove.setBounds(230, 11, 115, 37);
		getContentPane().add(btnRemove);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(ModalityFrm.class.getResource("/view/images/salvar.png")));
		btnSave.setBackground(SystemColor.menu);
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(345, 11, 104, 37);
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
		txfModality.setBounds(92, 55, 357, 20);
		getContentPane().add(txfModality);
		txfModality.setColumns(10);
		
		txfGraduate = new JTextField();
		txfGraduate.setEnabled(false);
		txfGraduate.setBounds(92, 78, 299, 20);
		getContentPane().add(txfGraduate);
		txfGraduate.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(SystemColor.menu);
		btnOk.setEnabled(false);
		btnOk.setBounds(391, 79, 58, 18);
		getContentPane().add(btnOk);
		
		JScrollPane scrollPaneGraduate = new JScrollPane();
		scrollPaneGraduate.setEnabled(false);
		scrollPaneGraduate.setToolTipText("");
		scrollPaneGraduate.setBounds(10, 114, 439, 146);
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
		btnAdd.setBackground(SystemColor.menu);
		btnAdd.setIcon(new ImageIcon(ModalityFrm.class.getResource("/view/images/adicionar.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				lblModality.setEnabled(true);
				lblGraduate.setEnabled(true);
				txfModality.setEnabled(true);
				txfGraduate.setEnabled(true);
				btnOk.setEnabled(true);
				scrollPaneGraduate.setEnabled(true);
				btnSave.setEnabled(true);
				btnRemove.setEnabled(true);
				tableGraduate.setEnabled(true);
				btnAdd.setEnabled(false);
			}
		});
		btnAdd.setBounds(115, 11, 115, 37);
		getContentPane().add(btnAdd);

		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		
		JLabel lblMessage = new JLabel("Duplo clique na linha da gradua\u00E7\u00E3o para remov\u00EA-la.");
		lblMessage.setBounds(11, 264, 414, 14);
		getContentPane().add(lblMessage);

	}
}
