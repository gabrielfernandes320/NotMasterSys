package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.ConnectionFactory;
import database.ModalidadesDAO;
import database.PlanosDAO;
import model.Modalidades;
import model.Plano;
import table.model.ModalidadeTableModel;
import table.model.PlansTableModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class ModalitySearchFrm extends JDialog {
	private JTable table;
	private ModalidadeTableModel model;
	private String idSelecionado;
	Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
	private static ModalityFrm window;
	private JTextField txfSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModalitySearchFrm dialog = new ModalitySearchFrm(window);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModalitySearchFrm(final ModalityFrm window) {
		setBounds(100, 100, 471, 330);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 248, 434, 33);
			getContentPane().add(buttonPane);
			{
				buttonPane.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Duplo clique na linha para selecionar a modalidade desejada");
				lblNewLabel.setBounds(0, 9, 434, 14);
				buttonPane.add(lblNewLabel);
			}
		}
		
		txfSearch = new JTextField();
		txfSearch.setBounds(89, 11, 220, 20);
		getContentPane().add(txfSearch);
		txfSearch.setColumns(10);
		
		JLabel lblModalidade = new JLabel("Modalidade:");
		lblModalidade.setBounds(10, 13, 75, 17);
		getContentPane().add(lblModalidade);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBackground(SystemColor.menu);
				btnAtualizar.setBounds(312, 10, 89, 23);
		getContentPane().add(btnAtualizar);
		
		JPanel painelFundo;

		painelFundo = new JPanel();
		model = new ModalidadeTableModel();
		painelFundo.setLayout(null);
		painelFundo.setBounds(10, 42, 434, 195);
		getContentPane().add(painelFundo);
		
				JTable tabela = new JTable(model);
				tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				JScrollPane barraRolagem = new JScrollPane(tabela);
				barraRolagem.setBounds(0, 0, 434, 195);
				painelFundo.add(barraRolagem);

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
		            
		            Modalidades p = new Modalidades();
		            p.setModalidade(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
		            
		            window.Update(p);
		           	dispose();
		           	
		           	
		           	
		            
		            }
		         }
		        } );
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zerarTodos();
				txfSearch.setText("");
				try {
					//// ??????????

					model.addListaDeModalidades(new ModalidadesDAO(conn).SelectAll(txfSearch.getText().toString()));
				
				} catch (Exception e1) {
					System.err.printf("Erro: %s.\n", e1.getMessage());
				}

				
				
				
			}
		});

	}
	public void mostrarTodos() {
			}

	public void zerarTodos() {
		model.limpar();
	}

}
