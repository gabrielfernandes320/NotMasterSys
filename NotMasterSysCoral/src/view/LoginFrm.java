package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import database.ConnectionFactory;
import database.UsuarioDAO;
import model.Usuario;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField tbUser;
	private JTextField tbPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
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
	public LoginFrm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 327, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(193, 110, 108, 45);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario();
				user.setUsuario(tbUser.getText());
				Connection conn = ConnectionFactory.getConnection("master", tbUser.getText().toString(), tbPassword.getText());
				System.out.println("Deu certo");
				try {
					UsuarioDAO dao = new UsuarioDAO(conn);
					user = (Usuario) dao.Select(user);
					System.out.println(user.getUsuario() + user.getPerfil());
					new PrincipalWindow(user).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		tbUser = new JTextField();
		tbUser.setBounds(33, 135, 150, 20);
		contentPane.add(tbUser);
		tbUser.setColumns(10);
		
		tbPassword = new JTextField();
		tbPassword.setBounds(33, 110, 149, 20);
		contentPane.add(tbPassword);
		tbPassword.setColumns(10);
	}
}
