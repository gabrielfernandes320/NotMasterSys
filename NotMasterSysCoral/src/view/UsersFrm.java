package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import database.ConnectionFactory;
import database.PlanosDAO;
import database.UsuarioDAO;
import model.Usuario;
import table.model.PlansTableModel;
import table.model.UsuariosTableModel;

import javax.swing.JButton;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import java.awt.Component;

public class UsersFrm extends JInternalFrame {
	private JTextField tbUser;
	private JPasswordField tbPassword;
	private JPasswordField tbConfirmPassword;
	private UsuariosTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersFrm frame = new UsersFrm();
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
	public UsersFrm() {
		setClosable(true);
		setBounds(100, 100, 463, 369);
		getContentPane().setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		
		btnAdicionar.setIcon(new ImageIcon(UsersFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(119, 11, 104, 31);
		getContentPane().add(btnAdicionar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		
		btnSalvar.setIcon(new ImageIcon(UsersFrm.class.getResource("/view/images/salvar.png")));
		btnSalvar.setPreferredSize(new Dimension(40, 25));
		btnSalvar.setBackground(SystemColor.menu);
		btnSalvar.setBounds(324, 11, 104, 31);
		getContentPane().add(btnSalvar);
		
		JButton btnRemover = new JButton("Remover");
		
		
		btnRemover.setIcon(new ImageIcon(UsersFrm.class.getResource("/view/images/remover.png")));
		btnRemover.setPreferredSize(new Dimension(40, 25));
		btnRemover.setBackground(SystemColor.menu);
		btnRemover.setBounds(222, 11, 104, 31);
		getContentPane().add(btnRemover);
		
		tbUser = new JTextField();
		tbUser.setEnabled(false);
		tbUser.setBounds(135, 53, 271, 20);
		getContentPane().add(tbUser);
		tbUser.setColumns(10);
		
		tbPassword = new JPasswordField();
		tbPassword.setEnabled(false);
		tbPassword.setBounds(135, 84, 270, 20);
		getContentPane().add(tbPassword);
		
		tbConfirmPassword = new JPasswordField();
		tbConfirmPassword.setEnabled(false);
		tbConfirmPassword.setBounds(135, 115, 270, 20);
		getContentPane().add(tbConfirmPassword);
		
		JComboBox cbProfile = new JComboBox();
		cbProfile.setEnabled(false);
		cbProfile.setModel(new DefaultComboBoxModel(new String[] {"Cadastral", "Matricular", "Financeiro", "Completo"}));
		cbProfile.setBounds(135, 146, 270, 20);
		getContentPane().add(cbProfile);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 53, 86, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 83, 68, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirmar senha:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 114, 115, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Perfil:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 142, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(UsersFrm.class.getResource("/view/images/localizar.png")));
		btnBuscar.setPreferredSize(new Dimension(40, 25));
		btnBuscar.setBackground(SystemColor.menu);
		btnBuscar.setBounds(10, 11, 110, 31);
		getContentPane().add(btnBuscar);

		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
				
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn.setAutoCommit(false);
					System.out.println("Conectado com sucesso!");

					UsuarioDAO dao = new UsuarioDAO(conn);
					Usuario usuario = new Usuario();
				
					usuario.setPerfil(cbProfile.getSelectedItem().toString());
					usuario.setUsuario(tbUser.getText());
					usuario.setPassword(tbPassword.getText());
				try {
					dao.CreateRole(usuario);
					JOptionPane.showMessageDialog(btnAdicionar, "Adicionado com Sucesso!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				  }
				
				    } catch (SQLException e1) {
					 e1.printStackTrace();
				      } 
			}
		});
					
		btnAdicionar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				btnSalvar.setEnabled(true);
				tbUser.setEnabled(true);
				tbPassword.setEnabled(true);
				tbConfirmPassword.setEnabled(true);
				cbProfile.setEnabled(true);		
			}
		});
		
		model = new UsuariosTableModel();
		JTable tabela = new JTable(model);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 173, 427, 155);
		getContentPane().add(scrollPane);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						conn.setAutoCommit(false);
						System.out.println("Conectado com sucesso!");

						UsuarioDAO dao = new UsuarioDAO(conn);
						Usuario usuario = new Usuario();
						List<Usuario> usuariosList = new ArrayList<Usuario>();
						usuariosList = (List<Usuario>)(List<?>) new UsuarioDAO(conn).SelectAll();
						model.addListaDeUsuarios(usuariosList);
						usuario.setPerfil(cbProfile.getSelectedItem().toString());
						usuario.setUsuario(tbUser.getText());
						usuario.setPassword(tbPassword.getText());
					
					  } catch (SQLException e1) {
						 e1.printStackTrace();
				}
				}
							
							
			});
		
		
		 tabela.setCellSelectionEnabled(true);
		    ListSelectionModel cellSelectionModel = tabela.getSelectionModel();
		    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    
		   
		    
						
				

				
					
		btnRemover.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				try {
					conn.setAutoCommit(false);
				
					UsuarioDAO dao = new UsuarioDAO(conn);
					System.out.println("aqui foi");
					Usuario usuario = new Usuario();
					System.out.println("aqui tmb");
				
						usuario.setPerfil(cbProfile.getSelectedItem().toString());
						usuario.setUsuario(tbUser.getText());
							try {
								dao.Delete(usuario);
								dao.DropRole(usuario);
								JOptionPane.showMessageDialog(btnRemover, "Removido com Sucesso!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
						}
						
					});
						
				
			}
	
	
	}

