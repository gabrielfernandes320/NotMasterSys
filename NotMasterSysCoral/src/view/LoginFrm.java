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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField tbUser;
	private JPasswordField tbPassword;

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
		setBounds(100, 100, 327, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setBounds(193, 32, 108, 45);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario();
				user.setUsuario(tbUser.getText());
				Connection conn = ConnectionFactory.getConnection("master", tbUser.getText().toString(), tbPassword.getPassword().toString());
				System.out.println("Deu certo");
				try {
					UsuarioDAO dao = new UsuarioDAO(conn);
					user = (Usuario) dao.Select(user);
					System.out.println(user.getUsuario() + user.getPerfil());
					new PrincipalWindow(user).setVisible(true);
					setVisible(false); //you can't see me!  // i see you
					dispose(); //Destroy the JFrame object
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		tbUser = new JTextField();
		tbUser.setBounds(59, 32, 124, 20);
		contentPane.add(tbUser);
		tbUser.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		lblUsuario.setBounds(10, 32, 54, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSenha.setBounds(10, 59, 54, 14);
		contentPane.add(lblSenha);
		
		tbPassword = new JPasswordField();
		tbPassword.setBounds(59, 57, 124, 20);
		contentPane.add(tbPassword);
	}
}
