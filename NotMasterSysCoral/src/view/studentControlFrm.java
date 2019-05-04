package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import database.AssiduidadeDAO;
import database.ConnectionFactory;
import lib.MasterMonthChooser;
import model.Assiduidade;
import table.model.AssiduidadeTableModel;
import table.model.PlansTableModel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

public class studentControlFrm extends JDialog {
	private GregorianCalendar teste;
	private AssiduidadeTableModel assModel;
	private JTextField txfNumMatricula;
	private JTextField txfNomeAluno;
	private JTextField txfSituacaoColsulta;
	private JTable tblConsulta;
	private JTable tblCosultaAl;
	private JTable tblAssiduidade;
	private JPanel pnlConsulta;
	private JPanel pnlCosultaAl;
	private JPanel pnlAssiduidade;
	private JScrollPane scpConsulta;
	private JScrollPane scpCosultaAl;
	private JScrollPane scpAssiduidade;
	private MasterMonthChooser mmcData;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		
		
		
					studentControlFrm frame = new studentControlFrm();
					frame.setVisible(true);
	
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public studentControlFrm() throws SQLException {
	//	setIconifiable(true);
		setTitle("Controle de Alunos");
		setBounds(100, 100, 696, 538);
		getContentPane().setLayout(null);
		
		JPanel pnlFoto = new JPanel();
		pnlFoto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, new Color(0, 0, 0)));
		pnlFoto.setBounds(22, 22, 181, 203);
		getContentPane().add(pnlFoto);
		
		txfNumMatricula = new JTextField();
		txfNumMatricula.setBounds(230, 22, 100, 20);
		getContentPane().add(txfNumMatricula);
		txfNumMatricula.setColumns(10);
		
		txfNomeAluno = new JTextField();
		txfNomeAluno.setEditable(false);
		txfNomeAluno.setBounds(343, 22, 318, 20);
		getContentPane().add(txfNomeAluno);
		txfNomeAluno.setColumns(10);
		
		tblConsulta = new JTable();
		tblConsulta.setBounds(230, 277, 431, 220);
		getContentPane().add(tblConsulta);
		
		txfSituacaoColsulta = new JTextField();
		txfSituacaoColsulta.setEditable(false);
		txfSituacaoColsulta.setColumns(10);
		txfSituacaoColsulta.setBounds(230, 166, 431, 54);
		getContentPane().add(txfSituacaoColsulta);
		
		JButton btnAcsAluno = new JButton("Acessar dados do Aluno");
		btnAcsAluno.setBounds(230, 236, 210, 30);
		getContentPane().add(btnAcsAluno);
		
		JButton btnAcsMatricula = new JButton("Acessar dados da Matr\u00EDcula");
		btnAcsMatricula.setBounds(450, 236, 210, 30);
		getContentPane().add(btnAcsMatricula);
		
		//Tabela Consulta
		tblCosultaAl = new JTable();
		tblCosultaAl.setBounds(230, 53, 431, 102);
		getContentPane().add(tblCosultaAl);
		
		
		//Tabela Assiduidade
		pnlAssiduidade = new JPanel();
		assModel = new AssiduidadeTableModel();
		pnlAssiduidade.setLayout(null);

		tblAssiduidade = new JTable(assModel);
		tblAssiduidade.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpAssiduidade = new JScrollPane(tblAssiduidade);
		scpAssiduidade.setBounds(0, 0, 181, 220);
		pnlAssiduidade.add(scpAssiduidade);
		pnlAssiduidade.setBounds(22, 277, 181, 220);
		getContentPane().add(pnlAssiduidade);
		
		mmcData = new MasterMonthChooser();
		mmcData.setBounds(22, 236, 181, 30);
		getContentPane().add(mmcData);
		
//		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
//		conn.setAutoCommit(false);
//			AssiduidadeDAO ass = new AssiduidadeDAO(conn);
//			Assiduidade model = new Assiduidade();
//			
//			model.setCodigo_matricula(100);
//			teste = new GregorianCalendar();
//			model.setData_entrada((Date) teste.getTime());
//			
//			ass.Insert(model);
		
		
		
		
	}
}
