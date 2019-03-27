package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import database.ConnectionFactory;
import database.UsuarioDAO;
import model.Usuario;

import javax.swing.JButton;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UsersFrm extends JInternalFrame {
	private JTextField tbUser;
	private JPasswordField tbPassword;
	private JPasswordField tbConfirmPassword;

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
		setBounds(100, 100, 466, 249);
		getContentPane().setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		
		btnAdicionar.setIcon(new ImageIcon(UsersFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(119, 11, 104, 31);
		getContentPane().add(btnAdicionar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		tbUser.setBounds(135, 79, 271, 20);
		getContentPane().add(tbUser);
		tbUser.setColumns(10);
		
		tbPassword = new JPasswordField();
		tbPassword.setBounds(135, 110, 270, 20);
		getContentPane().add(tbPassword);
		
		tbConfirmPassword = new JPasswordField();
		tbConfirmPassword.setBounds(135, 141, 270, 20);
		getContentPane().add(tbConfirmPassword);
		
		JComboBox cbProfile = new JComboBox();
		cbProfile.setModel(new DefaultComboBoxModel(new String[] {"Cadastral", "Matricular", "Financeiro", "Completo"}));
		cbProfile.setBounds(135, 172, 270, 20);
		getContentPane().add(cbProfile);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 79, 86, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 109, 68, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirmar senha:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 140, 115, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Perfil:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 168, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(UsersFrm.class.getResource("/view/images/localizar.png")));
		btnBuscar.setPreferredSize(new Dimension(40, 25));
		btnBuscar.setBackground(SystemColor.menu);
		btnBuscar.setBounds(10, 11, 110, 31);
		getContentPane().add(btnBuscar);
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					conn.setAutoCommit(false);
					System.out.println("Conectado com sucesso!");

					UsuarioDAO dao = new UsuarioDAO(conn);
					Usuario model = new Usuario();
					
					btnAdicionar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							model.setPerfil(cbProfile.getSelectedItem().toString());
							model.setUsuario(tbUser.getText());
							try {
								dao.Insert(model);
								JOptionPane.showMessageDialog(btnAdicionar, "Adicionado com Sucesso!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
						
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
			
			
		});
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn.setAutoCommit(false);
				
					UsuarioDAO dao = new UsuarioDAO(conn);
					Usuario model = new Usuario();
					
					btnAdicionar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							model.setPerfil(cbProfile.getSelectedItem().toString());
							model.setUsuario(tbUser.getText());
							try {
								dao.Delete(model);
								JOptionPane.showMessageDialog(btnRemover, "Removido com Sucesso!");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
						
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
			}
			
		});
		
		
		

	}
}