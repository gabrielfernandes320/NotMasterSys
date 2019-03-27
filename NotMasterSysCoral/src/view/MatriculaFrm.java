package view;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MatriculaFrm extends JInternalFrame {

	private JTextField txtNome;
	private JTextField txtNascimento;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtCelular;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtObservacao;
	private JTextField txtPais;
	private JTextField txtCep;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatriculaFrm frame = new MatriculaFrm();
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
	public MatriculaFrm() {
		setClosable(true);
		setTitle("Matricular Aluno");
		setResizable(true);
		setBounds(100, 100, 450, 300);
	
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 12, 66, 15);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(222, 39, 66, 15);

		JLabel lblNascimento = new JLabel("Nascimento:");
		lblNascimento.setBounds(12, 39, 120, 15);

		JComboBox boxSexo = new JComboBox();
		boxSexo.setBounds(388, 7, 50, 24);
		boxSexo.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(342, 12, 66, 15);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(12, 66, 66, 15);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(222, 66, 66, 15);

		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(12, 93, 97, 15);

		JLabel lblNumero = new JLabel("Nº:");
		lblNumero.setBounds(330, 118, 66, 15);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 120, 66, 15);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(12, 147, 66, 15);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(330, 147, 66, 15);

		JComboBox boxEstado = new JComboBox();
		boxEstado.setBounds(388, 142, 50, 24);

		JLabel lblObservacao = new JLabel("Observação:");
		lblObservacao.setBounds(12, 176, 97, 15);

		JLabel lblPais = new JLabel("País:");
		lblPais.setBounds(12, 201, 66, 15);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(262, 201, 66, 15);

		txtNome = new JTextField();
		txtNome.setBounds(70, 10, 257, 19);
		txtNome.setColumns(10);

		txtNascimento = new JTextField();
		txtNascimento.setBounds(103, 37, 113, 19);
		txtNascimento.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(281, 37, 157, 19);
		txtEmail.setColumns(10);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(92, 66, 124, 19);
		txtTelefone.setColumns(10);

		txtCelular = new JTextField();
		txtCelular.setBounds(291, 64, 147, 19);
		txtCelular.setColumns(10);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(92, 91, 346, 19);
		txtEndereco.setColumns(10);

		txtNumero = new JTextField();
		txtNumero.setBounds(356, 116, 82, 19);
		txtNumero.setColumns(10);

		txtBairro = new JTextField();
		txtBairro.setBounds(70, 118, 257, 19);
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(70, 145, 257, 19);
		txtCidade.setColumns(10);

		txtObservacao = new JTextField();
		txtObservacao.setBounds(103, 176, 335, 19);
		txtObservacao.setColumns(10);

		txtPais = new JTextField();
		txtPais.setBounds(59, 199, 157, 19);
		txtPais.setColumns(10);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(30, 231, 114, 25);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(302, 232, 114, 25);
		getContentPane().setLayout(null);
		
		txtCep = new JTextField();
		txtCep.setBounds(302, 199, 136, 19);
		txtCep.setColumns(10);
		getContentPane().add(txtCep);
		getContentPane().add(lblNome);
		getContentPane().add(txtNome);
		getContentPane().add(lblSexo);
		getContentPane().add(boxSexo);
		getContentPane().add(txtNascimento);
		getContentPane().add(lblNascimento);
		getContentPane().add(lblEmail);
		getContentPane().add(txtEmail);
		getContentPane().add(lblTelefone);
		getContentPane().add(txtTelefone);
		getContentPane().add(lblCelular);
		getContentPane().add(txtCelular);
		getContentPane().add(lblEndereco);
		getContentPane().add(txtEndereco);
		getContentPane().add(txtBairro);
		getContentPane().add(lblBairro);
		getContentPane().add(lblNumero);
		getContentPane().add(lblCidade);
		getContentPane().add(txtCidade);
		getContentPane().add(lblEstado);
		getContentPane().add(txtObservacao);
		getContentPane().add(lblPais);
		getContentPane().add(txtPais);
		getContentPane().add(lblCep);
		getContentPane().add(btnConcluir);
		getContentPane().add(btnCancelar);
		getContentPane().add(lblObservacao);
		getContentPane().add(boxEstado);
		getContentPane().add(txtNumero);
	}
}
