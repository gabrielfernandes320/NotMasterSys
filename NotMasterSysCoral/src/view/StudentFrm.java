package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DropMode;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class StudentFrm extends JInternalFrame {
	private JTextField StudentField;
	private JTextField TelephoneField;
	private JTextField CellphoneField_1;
	private JTextField EmailField_2;
	private JTextField AdressField_3;
	private JTextField ComplementField_4;
	private JTextField NgbhField_5;
	private JTextField StateField_6;
	private JTextField CepField_7;
	private JTextField AdressnumField_8;
	private JTextField CityField_9;
	private JTextField CountryField_10;
	private final Action action = new SwingAction();

	/**
	 * Create the frame.
	 */
	public StudentFrm() {
		setBorder(null);
		setTitle("Cadastro de Alunos");
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 517, 543);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(94dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(84dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(16dlu;default):grow"),
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/localizar.png")));
		getContentPane().add(btnSearch, "2, 2, fill, fill");
		
		JButton btnCreate = new JButton("Adicionar");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCreate.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/adicionar.png")));
		getContentPane().add(btnCreate, "4, 2, fill, fill");
		
		JButton btnDelete = new JButton("Remover");
		btnDelete.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/remover.png")));
		getContentPane().add(btnDelete, "6, 2, fill, fill");
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(StudentFrm.class.getResource("/view/images/salvar.png")));
		getContentPane().add(btnSave, "8, 2, fill, fill");
		
		JLabel lblAluno = new JLabel("Aluno:");
		lblAluno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblAluno, "2, 6, left, default");
		
		StudentField = new JTextField();
		getContentPane().add(StudentField, "4, 6, 5, 1, fill, default");
		StudentField.setColumns(10);
		
		JLabel lblData = new JLabel("Data de Nascimento:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblData, "2, 8, left, default");
		
		JFormattedTextField BirthdateField = new JFormattedTextField();
		BirthdateField.setDropMode(DropMode.ON);
		BirthdateField.setHorizontalAlignment(SwingConstants.CENTER);
		BirthdateField.setFocusLostBehavior(JFormattedTextField.REVERT);
		BirthdateField.setColumns(3);
		getContentPane().add(BirthdateField, "4, 8, fill, default");
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblSexo, "6, 8, left, default");
		
		JComboBox SexComboBox = new JComboBox();
		SexComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Masculino", "Feminino"}));
		getContentPane().add(SexComboBox, "8, 8, fill, default");
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblTelefone, "2, 10, left, default");
		
		TelephoneField = new JTextField();
		getContentPane().add(TelephoneField, "4, 10, fill, default");
		TelephoneField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Celular:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblNewLabel, "6, 10, left, default");
		
		CellphoneField_1 = new JTextField();
		getContentPane().add(CellphoneField_1, "8, 10, fill, default");
		CellphoneField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblNewLabel_1, "2, 12, left, default");
		
		EmailField_2 = new JTextField();
		getContentPane().add(EmailField_2, "4, 12, 5, 1, fill, default");
		EmailField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Observa\u00E7\u00F5es:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblNewLabel_2, "2, 14");
		
		JTextArea ObservationArea = new JTextArea();
		ObservationArea.setTabSize(4);
		ObservationArea.setLineWrap(true);
		ObservationArea.setRows(5);
		getContentPane().add(ObservationArea, "2, 16, 7, 7");
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(layeredPane, "2, 24, 7, 15, fill, fill");
		layeredPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(80dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(108dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),}));
		
		JLabel lblNewLabel_3 = new JLabel("Endere\u00E7o:");
		layeredPane.add(lblNewLabel_3, "2, 2, left, default");
		
		AdressField_3 = new JTextField();
		layeredPane.add(AdressField_3, "4, 2, 3, 1");
		AdressField_3.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		layeredPane.add(lblNmero, "10, 2, left, default");
		
		AdressnumField_8 = new JTextField();
		layeredPane.add(AdressnumField_8, "12, 2, fill, default");
		AdressnumField_8.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Complemento:");
		layeredPane.add(lblNewLabel_4, "2, 4, left, default");
		
		ComplementField_4 = new JTextField();
		layeredPane.add(ComplementField_4, "4, 4, 9, 1, fill, default");
		ComplementField_4.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		layeredPane.add(lblBairro, "2, 6, left, default");
		
		NgbhField_5 = new JTextField();
		layeredPane.add(NgbhField_5, "4, 6, 3, 1, fill, default");
		NgbhField_5.setColumns(10);
		
		JLabel lblCdade = new JLabel("Cidade:");
		layeredPane.add(lblCdade, "10, 6, left, default");
		
		CityField_9 = new JTextField();
		layeredPane.add(CityField_9, "12, 6, fill, default");
		CityField_9.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Estado:");
		layeredPane.add(lblNewLabel_5, "2, 8, left, default");
		
		StateField_6 = new JTextField();
		layeredPane.add(StateField_6, "4, 8, 3, 1, fill, default");
		StateField_6.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Pa\u00EDs:");
		layeredPane.add(lblNewLabel_7, "10, 8, left, default");
		
		CountryField_10 = new JTextField();
		layeredPane.add(CountryField_10, "12, 8, fill, default");
		CountryField_10.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("CEP:");
		layeredPane.add(lblNewLabel_6, "2, 10, left, default");
		
		CepField_7 = new JTextField();
		layeredPane.add(CepField_7, "4, 10, 3, 1, fill, default");
		CepField_7.setColumns(10);

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFrm frame = new StudentFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
