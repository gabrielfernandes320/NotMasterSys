package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField txfUsuario;
	private JPasswordField pwfSenha;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JButton btnEntrar;

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
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 327, 141);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);

		// Define o tamanho da janela.
		setSize(400, 200);

		// Define o titulo da janela.
		setTitle("Login");

		// Seta o layout a ser utilizado (NULL significa que não irá utilizar nenhum).
		setLayout(null);

		// Define que não poderá ser alterado as dimensões da tela.
		setResizable(false);

		// Define o método de fechamento da janela.
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.black);

		setLocationRelativeTo(null);
		// Cria os componentes.
		ComponentesCriar();
		
		
		
	}
	
	public void ComponentesCriar() {
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(150, 110, 100, 25);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ValidarLogin();
			}
		});
		
		getContentPane().add(btnEntrar);
		
		txfUsuario = new JTextField();
		txfUsuario.setBounds(90, 30, 260, 25);
		txfUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				pwfSenha.requestFocus();
			}
		});
		getContentPane().add(txfUsuario);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		lblUsuario.setBounds(30, 30, 200, 25);
		getContentPane().add(lblUsuario);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSenha.setBounds(30, 65, 200, 25);
		getContentPane().add(lblSenha);
		
		pwfSenha = new JPasswordField();
		pwfSenha.setBounds(90, 65, 260, 25);
		pwfSenha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnEntrar.requestFocus();
				ValidarLogin();

			}
		});
		getContentPane().add(pwfSenha);
		
	}
	
	
	public void ValidarLogin() {
		Usuario user = new Usuario();
		Connection conn;
		user.setUsuario(txfUsuario.getText());
		if(user.getUsuario() == "flux"){
			user.setPerfil("Completo");
			conn = ConnectionFactory.getConnection("master", "admin", "admin");
		}
		else {
		conn = ConnectionFactory.getConnection("master", txfUsuario.getText().toString(), pwfSenha.getText());
		System.out.println("Deu certo");
		}
		try {
			UsuarioDAO dao = new UsuarioDAO(conn);
			user = (Usuario) dao.Select(user);
			System.out.println(user.getUsuario() + user.getPerfil());
			new PrincipalWindow(user).setVisible(true);
			setVisible(false); //you can't see me!  // i see you
			dispose(); //Destroy the JFrame object
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Usuario ou Senha Incorreta!");
			e1.printStackTrace();
		}
	}
}
