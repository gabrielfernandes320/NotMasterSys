package view;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.ConnectionFactory;
import database.PlanosDAO;
import table.model.PlansTableModel;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Searsh extends JInternalFrame {
	private JTextField txfPesquisa;
	private JTable table;
	private PlansTableModel model;
	private String idSelecionado;
	Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Searsh frame = new Searsh();
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
	public Searsh() {
		
		setBounds(100, 100, 569, 458);
		getContentPane().setLayout(null);
		
		txfPesquisa = new JTextField();
		txfPesquisa.setBounds(173, 27, 167, 20);
		getContentPane().add(txfPesquisa);
		txfPesquisa.setColumns(10);
		
		
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(434, 26, 79, 23);
		btnAtualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {

				zerarTodos();

				mostrarTodos();

				txfPesquisa.setText("");

			}
		});
		getContentPane().add(btnAtualizar);
		

		
		JLabel lblPesquisaPor = new JLabel("Pesquisa por:");
		lblPesquisaPor.setBounds(10, 30, 89, 14);
		getContentPane().add(lblPesquisaPor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Plano", "Modalidade"}));
		comboBox.setBounds(84, 27, 79, 20);
		getContentPane().add(comboBox);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(350, 26, 79, 23);
		btnPesquisar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {

				zerarTodos();

				if (comboBox.getSelectedItem().toString().equals("Plano") && !txfPesquisa.getText().equals("")) {
					try {
						Integer.parseInt(txfPesquisa.getText());
					} catch (Exception e) {
						return;
					}
					try {
						model.addListaDePlanos((List<Object>) new PlanosDAO(conn).Select(txfPesquisa.getText()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e);
					}

				} 
//				else {
//
//					if (comboBox.getSelectedItem().toString().equals("Nome") && !txfPesquisa.getText().equals("")) {
//
//						model.addListaDePlanos(new PlanosDAO().consultar(txfPesquisa.getText()));
//
//					} else {
//						mostrarTodos();
//					}
//				}
			}
		});
		getContentPane().add(btnPesquisar);
		
		
		JPanel painelFundo;

		painelFundo = new JPanel();
		model = new PlansTableModel();
		painelFundo.setLayout(null);

		JTable tabela = new JTable(model);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane barraRolagem = new JScrollPane(tabela);
		barraRolagem.setBounds(0, 0, 489, 330);
		painelFundo.add(barraRolagem);
		painelFundo.setBounds(25, 60, 489, 330);
		getContentPane().add(painelFundo);

		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				if (tabela.getSelectedRow() != -1) {
					idSelecionado = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
				}
			}
		});
		
		tabela.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		         if (e.getClickCount() == 2){
		            System.out.println(" double click" );
		           	PlansFrm usu = new PlansFrm();
		            
//		            usu.consultar();
//		            usu.getTxfCode().setText(idSelecionado);
//		            usu.consultar();
//		            
		            }
		         }
		        } );

		mostrarTodos();

	}


	public void mostrarTodos() {
		try {
			//// ??????????

			model.addListaDePlanos(new PlanosDAO(conn).SelectAll());
		} catch (Exception e) {
			System.err.printf("Erro: %s.\n", e.getMessage());
		}
	}

	public void zerarTodos() {
		model.limpar();
	}
}
