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
import model.Plano;
import table.model.PlansTableModel;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;

public class PlansSearch extends JDialog {
	private JTextField txfPesquisa;
	private JTable table;
	private PlansTableModel model;
	private String idSelecionado;
	Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
	private PlansFrm window;

	/**
	 * Create the frame.
	 */
	public PlansSearch(final PlansFrm window) {
		
		this.window = window;
		
		setModal(true);
		setBounds(100, 100, 602, 458);
		getContentPane().setLayout(null);
		
		txfPesquisa = new JTextField();
		txfPesquisa.setBounds(196, 27, 159, 20);
		getContentPane().add(txfPesquisa);
		txfPesquisa.setColumns(10);
		
		
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(470, 26, 95, 23);
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
		lblPesquisaPor.setBounds(25, 30, 89, 14);
		getContentPane().add(lblPesquisaPor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Plano", "Modalidade"}));
		comboBox.setBounds(107, 27, 79, 20);
		getContentPane().add(comboBox);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(365, 26, 95, 23);
		btnPesquisar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {

				zerarTodos();

				if (comboBox.getSelectedItem().toString().equals("Plano") && !txfPesquisa.getText().equals("")) {
					
					try {
						model.addListaDePlanos(new PlanosDAO(conn).SelectAll(txfPesquisa.getText()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e);
					}

				} 
				else {

					if (comboBox.getSelectedItem().toString().equals("Modalidade") && !txfPesquisa.getText().equals("")) {
						
						try {
							model.addListaDePlanos(new PlanosDAO(conn).SelectAllM(txfPesquisa.getText()));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println(e);
						}

					}
				}
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
		barraRolagem.setBounds(0, 0, 541, 330);
		painelFundo.add(barraRolagem);
		painelFundo.setBounds(25, 60, 541, 330);
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
		            
		            Plano p = new Plano();
		            p.setPlano(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
		            p.setModalidade(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
		            p.setValor(Double.parseDouble( (String) tabela.getValueAt(tabela.getSelectedRow(), 2)));
		            
		            window.Update(p);
		           	dispose();
		           	
		           	
		           	
		            
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

			model.addListaDePlanos(new PlanosDAO(conn).SelectAll(""));
		} catch (Exception e) {
			System.err.printf("Erro: %s.\n", e.getMessage());
		}
	}

	public void zerarTodos() {
		model.limpar();
	}
}
