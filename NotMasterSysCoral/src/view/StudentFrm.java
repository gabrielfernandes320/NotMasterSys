package view;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.sun.jndi.cosnaming.IiopUrl.Address;
import com.sun.org.apache.bcel.internal.generic.CPInstruction;

import database.AlunoDAO;
import database.ConnectionFactory;
import database.UsuarioDAO;
import model.Aluno;
import model.Usuario;

import java.awt.Color;
import javax.swing.JFormattedTextField;

public class StudentFrm extends JInternalFrame {

	private JTextField StudentField;
	private JTextField TelephoneField;
	private JTextField EmailField;
	private JTextField PhoneField;
	private JTextField AdressField;
	private JTextField AdressComplementField;
	private JTextField NeighbhField;
	private JTextField StateField;
	private JTextField CepField;
	private JTextField AdressNumField;
	private JTextField CityField;
	private JTextField CountryField;
	private JTextField IdField;

	/**
	 * Create the frame.
	 */
	public StudentFrm() {
		
		setClosable(true);
		setTitle("Cadastro de Alunos");
		setBounds(100, 100, 470, 510);
		getContentPane().setLayout(null);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/localizar.png")));
		btnSearch.setBounds(10, 11, 100, 36);
		getContentPane().add(btnSearch);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/adicionar.png")));
		btnAdd.setBounds(109, 11, 115, 36);
		getContentPane().add(btnAdd);
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/remover.png")));
		btnRemove.setBounds(223, 11, 115, 36);
		getContentPane().add(btnRemove);
		
		JButton btnUpdate = new JButton("Salvar");
		btnUpdate.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/salvar.png")));
		btnUpdate.setBounds(337, 11, 100, 36);
		getContentPane().add(btnUpdate);
		
		JLabel lblNewLabel_1 = new JLabel("Aluno:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 77, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Data de Nascimento:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 102, 128, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 127, 100, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-Mail:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 152, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		StudentField = new JTextField();
		StudentField.setBounds(138, 76, 150, 20);
		getContentPane().add(StudentField);
		StudentField.setColumns(10);
		
		TelephoneField = new JTextField();
		TelephoneField.setBounds(138, 126, 115, 20);
		getContentPane().add(TelephoneField);
		TelephoneField.setColumns(10);
		
		EmailField = new JTextField();
		EmailField.setBounds(138, 151, 299, 20);
		getContentPane().add(EmailField);
		EmailField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(263, 104, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JComboBox SexCmb = new JComboBox();
		SexCmb.setModel(new DefaultComboBoxModel(new String[] {"", "Masculino", "Feminino"}));
		SexCmb.setBounds(319, 101, 118, 20);
		getContentPane().add(SexCmb);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCelular.setBounds(263, 129, 46, 14);
		getContentPane().add(lblCelular);
		
		PhoneField = new JTextField();
		PhoneField.setBounds(319, 126, 118, 20);
		getContentPane().add(PhoneField);
		PhoneField.setColumns(10);
		
		JLabel lblObservaoes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservaoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblObservaoes.setBounds(10, 177, 100, 14);
		getContentPane().add(lblObservaoes);
		
		JTextArea ObservationField = new JTextArea();
		ObservationField.setTabSize(5);
		ObservationField.setLineWrap(true);
		ObservationField.setRows(5);
		ObservationField.setBounds(10, 201, 434, 90);
		getContentPane().add(ObservationField);
		
		JPanel EnderecoPanel = new JPanel();
		EnderecoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		EnderecoPanel.setBounds(10, 302, 427, 170);
		getContentPane().add(EnderecoPanel);
		EnderecoPanel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Endere\u00E7o:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 36, 94, 14);
		EnderecoPanel.add(lblNewLabel_6);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBairro.setBounds(10, 86, 94, 14);
		EnderecoPanel.add(lblBairro);
		
		JLabel lblCep = new JLabel("Estado:");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCep.setBounds(10, 111, 94, 14);
		EnderecoPanel.add(lblCep);
		
		JLabel lblCep_1 = new JLabel("CEP:");
		lblCep_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCep_1.setBounds(10, 136, 94, 14);
		EnderecoPanel.add(lblCep_1);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComplemento.setBounds(10, 61, 94, 14);
		EnderecoPanel.add(lblComplemento);
		
		AdressField = new JTextField();
		AdressField.setBounds(114, 35, 118, 20);
		EnderecoPanel.add(AdressField);
		AdressField.setColumns(10);
		
		AdressComplementField = new JTextField();
		AdressComplementField.setBounds(114, 60, 303, 20);
		EnderecoPanel.add(AdressComplementField);
		AdressComplementField.setColumns(10);
		
		NeighbhField = new JTextField();
		NeighbhField.setBounds(114, 85, 118, 20);
		EnderecoPanel.add(NeighbhField);
		NeighbhField.setColumns(10);
		
		StateField = new JTextField();
		StateField.setBounds(114, 110, 118, 20);
		EnderecoPanel.add(StateField);
		StateField.setColumns(10);
		
		CepField = new JTextField();
		CepField.setBounds(114, 135, 118, 20);
		EnderecoPanel.add(CepField);
		CepField.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBounds(242, 36, 46, 14);
		EnderecoPanel.add(label);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmero.setBounds(242, 36, 94, 14);
		EnderecoPanel.add(lblNmero);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidade.setBounds(242, 86, 94, 14);
		EnderecoPanel.add(lblCidade);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPais.setBounds(242, 111, 94, 14);
		EnderecoPanel.add(lblPais);
		
		AdressNumField = new JTextField();
		AdressNumField.setBounds(298, 35, 119, 20);
		EnderecoPanel.add(AdressNumField);
		AdressNumField.setColumns(10);
		
		CityField = new JTextField();
		CityField.setBounds(298, 85, 119, 20);
		EnderecoPanel.add(CityField);
		CityField.setColumns(10);
		
		CountryField = new JTextField();
		CountryField.setBounds(298, 110, 119, 20);
		EnderecoPanel.add(CountryField);
		CountryField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Cod:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(292, 79, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		IdField = new JTextField();
		IdField.setBounds(351, 76, 86, 20);
		getContentPane().add(IdField);
		IdField.setColumns(10);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField BirthdateField = new JFormattedTextField(df);
		BirthdateField.setBounds(138, 101, 115, 20);
		getContentPane().add(BirthdateField);
		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		maskData.install(BirthdateField);


		// COISAS IMPORTANTES: BOTOES E CONEXAO
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		
		btnSearch.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				try {
					conn.setAutoCommit(false);
				
					AlunoDAO dao = new AlunoDAO(conn);
					Aluno model = new Aluno();
						
					model.setAluno(StudentField.getText());
					model.setEmail(EmailField.getText());
					
						try {
							dao.Delete(model);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
						}
						
					});
		
		btnAdd.addActionListener(new ActionListener() {	
			
			public void actionPerformed(ActionEvent e) {
				try {
					conn.setAutoCommit(false);
				
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					java.sql.Date data = new java.sql.Date(format.parse(BirthdateField.getText()).getTime());
					AlunoDAO dao = new AlunoDAO(conn);
					Aluno model = new Aluno();
						
					model.setCodigo_aluno(IdField.getText());
					model.setAluno(StudentField.getText());
					model.setData_nascimento(data);
					model.setTelefone(TelephoneField.getText());
					model.setCelular(PhoneField.getText());
					model.setEmail(EmailField.getText());
					model.setObservacao(ObservationField.getText());
					model.setEndereco(AdressField.getText());
					model.setNumero(AdressNumField.getText());
					model.setComplemento(AdressComplementField.getText());
					model.setBairro(NeighbhField.getText());
					model.setCidade(CityField.getText());
					model.setEstado(StateField.getText());
					model.setPais(CountryField.getText());
					model.setCep(CepField.getText());
					
					
					//CHECANDO SE SEXO ESTÁ NULO
					if(SexCmb.getSelectedItem()=="Masculino") {
						
						model.setSexo('M');
						
					}
					else if (SexCmb.getSelectedItem()=="Feminino") {
						
						model.setSexo('F');
						
					}
					else
						JOptionPane.showMessageDialog(null, "Erro: Sexo em branco");
										
					
						try {
							dao.Insert(model);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				}
						}
						
					});
		
	}
}

