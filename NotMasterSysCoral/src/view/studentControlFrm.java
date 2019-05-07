package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Date;
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
import database.PlanosDAO;
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

	Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

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
		txfNumMatricula.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	            	String aux = txfNumMatricula.getText();
	            	
	            	
	            	
	            		assModel.limpar();
						try {
							try {
								conn.setAutoCommit(false);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							AssiduidadeDAO ass = null;
							try {
								ass = new AssiduidadeDAO(conn);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Assiduidade model = null;
							Timestamp ts;
							try {
								model = new Assiduidade();

								Date date = new Date();
								long time = date.getTime();
								ts = new Timestamp(time);

								model.setCodigo_matricula(Integer.parseInt(aux));
								model.setData_entrada(ts);
							} catch (NumberFormatException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							zerarTodos();
							try {
								ass.Insert(model);
								assModel.addListaDeAssiduidades(
										new AssiduidadeDAO(conn).SelectAllP(Integer.parseInt(aux)));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								System.out.println(e1);
							}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
	        			
	        			
	        			
	                
	            }
	        

	    });
		
		
		
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
		
		
	}
	public void zerarTodos() {
		assModel.limpar();
	}
}
