package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectionFactory;
import database.GraduacoesDAO;
import database.ModalidadesDAO;
import model.Graduacoes;
import model.Modalidades;
import model.Plano;
import table.model.GraduacoesTableModel;
import table.model.PlansTableModel;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class ModalityFrm extends JInternalFrame {
	
	private JTextField txfModality;
	private JTextField txfGraduation;
	private JButton btnRemove;
	private JButton btnAdd;
	private JButton btnSearch;
	private JButton btnSave;
	private JButton btnOk;
	private JScrollPane scrollPaneGraduate;
	private JLabel lblModality;
	private JLabel lblGraduate;
	private JTable tableGraduate;
	private String idSelecionado;
	private GraduacoesTableModel model;
	private boolean IsUpdate = false;
	private List<Graduacoes> newGraduations = new ArrayList<Graduacoes>();
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
		btnRemove.setBounds(230, 11, 115, 37);
		getContentPane().add(btnRemove);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(ModalityFrm.class.getResource("/view/images/salvar.png")));
		btnSave.setBackground(SystemColor.menu);
		btnSave.setEnabled(false);
		btnSave.setBounds(345, 11, 104, 37);
		getContentPane().add(btnSave);
		
		JLabel lblModality = new JLabel("Modalidade:");
		lblModality.setEnabled(true);
		lblModality.setBounds(10, 54, 72, 22);
		getContentPane().add(lblModality);
		
		JLabel lblGraduate = new JLabel(" Gradua\u00E7\u00E3o:");
		lblGraduate.setEnabled(true);
		lblGraduate.setBounds(10, 81, 72, 14);
		getContentPane().add(lblGraduate);
		
		txfModality = new JTextField();
		txfModality.setEditable(false);
		txfModality.setBounds(92, 55, 357, 20);
		getContentPane().add(txfModality);
		txfModality.setColumns(10);
		
		txfGraduation = new JTextField();
		txfGraduation.setEditable(false);
		txfGraduation.setBounds(92, 78, 299, 20);
		getContentPane().add(txfGraduation);
		txfGraduation.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(SystemColor.menu);
		btnOk.setEnabled(false);
		btnOk.setBounds(391, 79, 58, 18);
		getContentPane().add(btnOk);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setBackground(SystemColor.menu);
		btnAdd.setIcon(new ImageIcon(ModalityFrm.class.getResource("/view/images/adicionar.png")));
		btnAdd.setBounds(115, 11, 115, 37);
		getContentPane().add(btnAdd);
				
		JLabel lblMessage = new JLabel("Duplo clique na linha da gradua\u00E7\u00E3o para remov\u00EA-la.");
		lblMessage.setBounds(11, 264, 414, 14);
		getContentPane().add(lblMessage);

		JPanel	painelFundo = new JPanel();
		model = new GraduacoesTableModel();
		painelFundo.setLayout(null);
		
		JTable tableGraduation = new JTable(model);
		tableGraduation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane barraRolagem = new JScrollPane(tableGraduation);
		barraRolagem.setBounds(0, 0, 440, 147);
		painelFundo.add(barraRolagem);
		painelFundo.setBounds(10, 106, 439, 147);
		getContentPane().add(painelFundo);
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		
		tableGraduation.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				if (tableGraduation.getSelectedRow() != -1) {
					idSelecionado = tableGraduation.getValueAt(tableGraduation.getSelectedRow(), 0).toString();
				}
			}
		});
		tableGraduation.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		         if (e.getClickCount() == 2){
		            System.out.println(" double click" );
		            
		            
		            try {
		            	GraduacoesDAO graduationDao = new GraduacoesDAO(conn);
		            	Graduacoes g = new Graduacoes();
		            	g.setGraduations(tableGraduation.getValueAt(tableGraduation.getSelectedRow(), 0).toString());
		            	model.removeGraduacao(tableGraduation.getSelectedRow());
		            	graduationDao.Delete(g);
		            	
		            	
		            	} catch (SQLException e1) {
		            		// TODO Auto-generated catch block
		            		e1.printStackTrace();
		            	}
		 
		            }
		        }
		 });
		
		
				
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSearch.setEnabled(true);
				btnOk.setEnabled(true);
				btnSave.setEnabled(true);
				btnAdd.setEnabled(false);
				txfModality.setEditable(true);
				txfGraduation.setEditable(true);
				txfModality.setText("");
				model.limpar();
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModalitySearchFrm modalitySearch = new ModalitySearchFrm(ModalityFrm.this);
				modalitySearch.setVisible(true);
				btnRemove.setEnabled(true);
				btnSave.setEnabled(true);
				btnAdd.setEnabled(true);
				btnOk.setEnabled(true);
				btnSearch.setEnabled(false);
				txfGraduation.setEditable(true);
				}
		});
		
		
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if( (txfModality.getText()).isEmpty() ) {
					JOptionPane.showMessageDialog(null,"O campo da Modalidade deve ser preenchido!");
				}
				else if(model.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Deve ser cadastrada pelos uma graduaçao.");
				}else {
					
					if(IsUpdate) {
						try {
							conn.setAutoCommit(false);
							System.out.println("Conectado com sucesso!");
							
							GraduacoesDAO graduationDao = new GraduacoesDAO(conn);
							
							try {
							
								for(int i = 0 ; i< newGraduations.size() ;i++) {
									
									graduationDao.Insert(newGraduations.get(i));
									}
									newGraduations.clear();
								
							}catch (SQLException e1) {
								e1.printStackTrace();
							}
							
					}catch (SQLException e2) {
						e2.printStackTrace();
					}
						JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
						
					}else {
						try {
							conn.setAutoCommit(false);
							System.out.println("Conectado com sucesso!");
							
							Modalidades modalityModel = new Modalidades();
							ModalidadesDAO modalityDao = new ModalidadesDAO(conn);
							GraduacoesDAO graduationDao = new GraduacoesDAO(conn);
							
							modalityModel.setModalidade(txfModality.getText());
							try {
								modalityDao.Insert(modalityModel);
								for(int i=0; i<model.getRowCount();i++) {
									graduationDao.Insert(model.getGraduacao(i));
								}
								
							}catch (SQLException e1) {
								e1.printStackTrace();
							}
						
							
						}catch (SQLException e2) {
							e2.printStackTrace();
						}
						JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
					  }
					}
				
				btnOk.setEnabled(false);
				btnSave.setEnabled(false);
				btnRemove.setEnabled(false);
				btnSearch.setEnabled(true);
				txfModality.setEditable(false);
				txfGraduation.setEditable(false);
				txfGraduation.setText("");
				txfModality.setText("");
				IsUpdate = false;
				model.limpar();
				
			}});
		
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( (txfModality.getText()).isEmpty() ) {
					JOptionPane.showMessageDialog(null,"Uma modalidade deve ser selecionada!");
				}else {
					Modalidades modalityModel = new Modalidades();
					modalityModel.setModalidade(txfModality.getText().toString());
					
						try {
							ModalidadesDAO modalityDao = new ModalidadesDAO(conn);
							GraduacoesDAO  graduationDao = new GraduacoesDAO(conn);
							
							for(int i = 0; i < model.getRowCount(); i++) {
								graduationDao.Delete(model.getGraduacao(i));
							}
							modalityDao.Delete(modalityModel);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null,"Deletado com sucesso!");
				}
				btnOk.setEnabled(false);
				btnSave.setEnabled(false);
				btnRemove.setEnabled(false);
				btnSearch.setEnabled(true);
				txfModality.setEditable(false);
				txfGraduation.setEditable(false);
				txfGraduation.setText("");
				txfModality.setText("");
				IsUpdate = false;
				model.limpar();
			}});
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Graduacoes newGraduation = new Graduacoes();
				newGraduation.setId_modality(txfModality.getText());
				newGraduation.setGraduations(txfGraduation.getText());
				model.addGraduacao(newGraduation);
				txfGraduation.setText("");
				newGraduations.add(newGraduation);
				
			}
		});
		
		
	}
	
	
	
	public void Update(final Modalidades p, final List<Graduacoes> graduation) {

		txfModality.setText(p.getModalidade());
		model.limpar();
		model.addListaDeGraduacoes(graduation);
		updateCampos();
		IsUpdate = true;

//	while()
//	cbxModalidade.setSelectedIndex();
//		
	}
	
	public void updateCampos() {
		txfModality.setEnabled(true);
		txfGraduation.setEditable(true);
		IsUpdate = true;
	}
	
	public void zerarTable() {
		model.limpar();
	}
	
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) /2);
		}
}
