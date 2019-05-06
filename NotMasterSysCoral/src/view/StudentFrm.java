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
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
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

	private int SearchMode;
	private JTextField StudentField;
	private JTextField TelephoneField;
	private JTextField EmailField;
	private JTextField PhoneField;
	private JTextField AdressField;
	private JTextField AdressComplementField;
	private JTextField NeighbhField;
	private JTextField CepField;
	private JTextField AdressNumField;
	@SuppressWarnings("rawtypes")
	private JComboBox SexCmb;
	@SuppressWarnings("rawtypes")
	private JComboBox CityCmb;
	@SuppressWarnings("rawtypes")
	private JComboBox CountryCmb;
	@SuppressWarnings("rawtypes")
	private JComboBox StateCmb;
	private JTextArea ObservationField;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private JFormattedTextField BirthdateField;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnUpdate;

	Aluno model = new Aluno();

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public StudentFrm() throws SQLException {

		setClosable(true);
		setTitle("Cadastro de Alunos");
		setBounds(100, 100, 470, 510);
		getContentPane().setLayout(null);

		btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/localizar.png")));
		btnSearch.setBounds(10, 11, 100, 36);
		getContentPane().add(btnSearch);

		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/adicionar.png")));
		btnAdd.setBounds(109, 11, 115, 36);
		getContentPane().add(btnAdd);

		btnRemove = new JButton("Remover");
		btnRemove.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/remover.png")));
		btnRemove.setBounds(223, 11, 115, 36);
		getContentPane().add(btnRemove);

		btnUpdate = new JButton("Salvar");
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

		SexCmb = new JComboBox();
		SexCmb.setModel(new DefaultComboBoxModel(new String[] { "", "Masculino", "Feminino" }));
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

		ObservationField = new JTextArea();
		ObservationField.setTabSize(5);
		ObservationField.setLineWrap(true);
		ObservationField.setRows(5);
		ObservationField.setBounds(10, 201, 434, 90);
		getContentPane().add(ObservationField);

		JPanel EnderecoPanel = new JPanel();
		EnderecoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Endere\u00E7o",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		lblCep.setBounds(242, 111, 94, 14);
		EnderecoPanel.add(lblCep);

		JLabel lblCep_1 = new JLabel("CEP:");
		lblCep_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCep_1.setBounds(242, 136, 94, 14);
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

		CepField = new JTextField();
		CepField.setBounds(299, 135, 118, 20);
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
		lblCidade.setBounds(10, 136, 94, 14);
		EnderecoPanel.add(lblCidade);

		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPais.setBounds(10, 111, 94, 14);
		EnderecoPanel.add(lblPais);

		AdressNumField = new JTextField();
		AdressNumField.setBounds(298, 35, 119, 20);
		EnderecoPanel.add(AdressNumField);
		AdressNumField.setColumns(10);

		StateCmb = new JComboBox(new DefaultComboBoxModel(loadStatesComboBox()));
		StateCmb.addActionListener(StateCmb);
		StateCmb.setBounds(299, 110, 118, 20);
		EnderecoPanel.add(StateCmb);
		
		CityCmb = new JComboBox(new DefaultComboBoxModel(new String[] { ""}));
		CityCmb.setBounds(114, 135, 118, 20);
		EnderecoPanel.add(CityCmb);

		CountryCmb = new JComboBox(new DefaultComboBoxModel(new String[] { "", "Brasil" }));
		CountryCmb.setBounds(114, 110, 118, 20);
		EnderecoPanel.add(CountryCmb);

		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		BirthdateField = new JFormattedTextField(df);
		BirthdateField.setBounds(138, 101, 115, 20);
		getContentPane().add(BirthdateField);
		maskData.install(BirthdateField);

		cleanFields();

		// COISAS IMPORTANTES: BOTOES E CONEXAO

		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (btnAdd.isEnabled() == false) {

					if (StudentField.getText().trim().equals("") || BirthdateField.getText().trim().equals("")
							|| TelephoneField.getText().trim().equals("") || PhoneField.getText().trim().equals("")
							|| EmailField.getText().trim().equals("") || ObservationField.getText().trim().equals("")
							|| AdressField.getText().trim().equals("") || AdressNumField.getText().trim().equals("")
							|| AdressComplementField.getText().trim().equals("") || SexCmb.getSelectedIndex() == -1
							|| NeighbhField.getText().trim().equals("") || CityCmb.getSelectedIndex() == -1
							|| StateCmb.getSelectedIndex() == -1 || StateCmb.getSelectedIndex() == -1
							|| CepField.getText().trim().equals("")) {

						JOptionPane.showMessageDialog(getContentPane(), "Campos em branco!", "Erro!",
								JOptionPane.INFORMATION_MESSAGE);

					} else {

						try {
							
							conn.setAutoCommit(false);
							AlunoDAO dao = new AlunoDAO(conn);

							Date data = new Date(df.parse(BirthdateField.getText()).getTime());
							// CHECANDO SE SEXO ESTÁ NULO
							if (SexCmb.getSelectedItem() == "Masculino")
								model.setSexo('M');
							else if (SexCmb.getSelectedItem() == "Feminino")
								model.setSexo('F');
							else
								JOptionPane.showMessageDialog(null, "Erro: Sexo em branco");
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
							model.setCidade((String) CityCmb.getSelectedItem());
							model.setEstado((String) StateCmb.getSelectedItem());
							model.setPais((String) CountryCmb.getSelectedItem());
							model.setCep(CepField.getText());

							if (dao.Select(model) == null) {

								dao.Insert(model);
								JOptionPane.showMessageDialog(null, "Sucesso! Aluno Cadastrado");

								cleanFields();

							}

							else
								JOptionPane.showMessageDialog(null, "Erro no Cadastro! Nome de aluno já existente!");

							cleanFields();

						} catch (SQLException | ParseException e1) {

							e1.printStackTrace();

						}

					}

				} else {

					if (StudentField.getText().trim().equals("") || BirthdateField.getText().trim().equals("")
							|| TelephoneField.getText().trim().equals("") || PhoneField.getText().trim().equals("")
							|| EmailField.getText().trim().equals("") || ObservationField.getText().trim().equals("")
							|| AdressField.getText().trim().equals("") || AdressNumField.getText().trim().equals("")
							|| AdressComplementField.getText().trim().equals("") || SexCmb.getSelectedIndex() == -1
							|| NeighbhField.getText().trim().equals("") || CityCmb.getSelectedIndex() == -1
							|| StateCmb.getSelectedIndex() == -1 || StateCmb.getSelectedIndex() == -1
							|| CepField.getText().trim().equals("")) {

						JOptionPane.showMessageDialog(getContentPane(), "Campos em branco!", "Erro!",
								JOptionPane.INFORMATION_MESSAGE);

					} else {

						try {

							conn.setAutoCommit(false);

							AlunoDAO dao = new AlunoDAO(conn);

							Aluno tempModel = dao.Select(model);

							Date data = new Date(df.parse(BirthdateField.getText()).getTime());
							tempModel.setAluno(StudentField.getText());
							tempModel.setData_nascimento(data);
							tempModel.setTelefone(TelephoneField.getText());
							tempModel.setCelular(PhoneField.getText());
							tempModel.setEmail(EmailField.getText());
							tempModel.setObservacao(ObservationField.getText());
							tempModel.setEndereco(AdressField.getText());
							tempModel.setNumero(AdressNumField.getText());
							tempModel.setComplemento(AdressComplementField.getText());
							tempModel.setBairro(NeighbhField.getText());
							tempModel.setCidade((String) CityCmb.getSelectedItem());
							tempModel.setEstado((String) StateCmb.getSelectedItem());
							tempModel.setPais((String) CountryCmb.getSelectedItem());
							tempModel.setCep(CepField.getText());

							dao.Update(tempModel);
							JOptionPane.showMessageDialog(getContentPane(), "Alterações Salvas.");

							cleanFields();

						} catch (SQLException | ParseException e1) {

							e1.printStackTrace();

						}

					}

				}

			}
		});

		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (StudentField.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(getContentPane(), "Você deve procurar um aluno primeiro!.", "Erro!",
							JOptionPane.INFORMATION_MESSAGE);

				}

				else {

					try {

						conn.setAutoCommit(false);
						AlunoDAO dao = new AlunoDAO(conn);

						if (JOptionPane.showConfirmDialog(getContentPane(), "Confirmar exclusão?", "Aviso:",
								JOptionPane.YES_NO_OPTION) == 0) {

							if (dao.Delete(model) == 1) {

								JOptionPane.showMessageDialog(getContentPane(), "Cadastro deletado.", "Sucesso!",
										JOptionPane.INFORMATION_MESSAGE);

								cleanFields();

							}

							else {

								JOptionPane.showMessageDialog(getContentPane(), null, "Erro: Aluno não encontrado!",
										JOptionPane.INFORMATION_MESSAGE);
								cleanFields();

							}

						}

					} catch (SQLException e1) {

						e1.printStackTrace();

					}

				}

			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				enableSearchingFields();

				StudentSearchFrm searchForm = new StudentSearchFrm(StudentFrm.this);
				searchForm.setVisible(true);

			}

		});

		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (SearchMode != 0) {
				
					enableAddingFields();

				}

			}

		});

	}

    public void actionPerformed(ActionEvent e)
    {
    	
        try {
			CityCmb.setModel(new DefaultComboBoxModel(loadCitiesComboBox(StateCmb.getSelectedItem().toString())));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
    }
	
	void updateFields(Aluno aluno) {

		model = aluno;

		StudentField.setText(aluno.getAluno());
		BirthdateField.setText(df.format(aluno.getData_nascimento()));
		TelephoneField.setText(aluno.getTelefone());

		if (aluno.getSexo() == 'M')
			SexCmb.setSelectedIndex(1);
		else
			SexCmb.setSelectedIndex(2);

		PhoneField.setText(aluno.getCelular());
		EmailField.setText(aluno.getEmail());
		ObservationField.setText(aluno.getObservacao());
		AdressField.setText(aluno.getEndereco());
		AdressNumField.setText(aluno.getNumero());
		AdressComplementField.setText(aluno.getComplemento());
		NeighbhField.setText(aluno.getBairro());

		CityCmb.setSelectedItem(aluno.getCidade());
		StateCmb.setSelectedItem(aluno.getEstado());
		CountryCmb.setSelectedItem(aluno.getPais());

		CepField.setText(aluno.getCep());

	}

	void cleanFields() {
		
		SearchMode = 2;
		
		btnSearch.setEnabled(true);
		btnRemove.setEnabled(false);
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(false);

		StudentField.setText(null);
		BirthdateField.setText(null);
		TelephoneField.setText(null);
		SexCmb.setSelectedIndex(0);
		PhoneField.setText(null);
		EmailField.setText(null);
		ObservationField.setText(null);
		AdressField.setText(null);
		AdressNumField.setText(null);
		AdressComplementField.setText(null);
		NeighbhField.setText(null);
		CepField.setText(null);

		model = new Aluno();

	}

	void enableSearchingFields() {

		cleanFields();
		
		SearchMode = 1;

		btnAdd.setEnabled(true);
		btnRemove.setEnabled(true);
		btnUpdate.setEnabled(true);
		btnSearch.setEnabled(true);

		StudentField.setText(null);
		BirthdateField.setText(null);
		TelephoneField.setText(null);
		SexCmb.setSelectedIndex(0);
		PhoneField.setText(null);
		EmailField.setText(null);
		ObservationField.setText(null);
		AdressField.setText(null);
		AdressNumField.setText(null);
		AdressComplementField.setText(null);
		NeighbhField.setText(null);
		CityCmb.setSelectedIndex(0);
		StateCmb.setSelectedIndex(0);
		CountryCmb.setSelectedIndex(0);
		CepField.setText(null);

	}

	void enableAddingFields() {

		cleanFields();
		
		SearchMode = 0;

		btnAdd.setEnabled(false);
		btnRemove.setEnabled(false);
		btnUpdate.setEnabled(true);
		btnSearch.setEnabled(true);

		StudentField.setText(null);
		BirthdateField.setText(null);
		TelephoneField.setText(null);
		SexCmb.setSelectedIndex(0);
		PhoneField.setText(null);
		EmailField.setText(null);
		ObservationField.setText(null);
		AdressField.setText(null);
		AdressNumField.setText(null);
		AdressComplementField.setText(null);
		NeighbhField.setText(null);
		CityCmb.setSelectedIndex(0);
		StateCmb.setSelectedIndex(0);
		CountryCmb.setSelectedIndex(0);
		CepField.setText(null);
	}

	String[] loadCitiesComboBox(String estado) throws SQLException {

		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

		AlunoDAO dao = new AlunoDAO(conn);
		String[] Cities = dao.selectAllCities(estado);

		return Cities;

	}

	String[] loadStatesComboBox() throws SQLException {

		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

		AlunoDAO dao = new AlunoDAO(conn);
		String[] States = dao.selectAllStates();

		return States;

	}
}