package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import database.AssiduidadeDAO;
import database.ConnectionFactory;
import database.InvoiceDAO;
import database.MatriculaDAO;
import database.PlanosDAO;
import lib.MasterMonthChooser;
import model.Assiduidade;
import model.Invoice;
import model.Matricula;
import table.model.AssiduidadeTableModel;
import table.model.InvoicesCheck2TableModel;
import table.model.PlansTableModel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

public class StudentControlFrm extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GregorianCalendar teste;
	private AssiduidadeTableModel assModel;
	private InvoicesCheck2TableModel invModel;
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
		
		
		
		
					StudentControlFrm frame = new StudentControlFrm();
					frame.setVisible(true);
	
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public StudentControlFrm() throws SQLException {
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
	            		invModel.limpar();
						
								try {
									conn.setAutoCommit(false);
								} catch (SQLException e3) {
									// TODO Auto-generated catch block
									e3.printStackTrace();
								}

								
							AssiduidadeDAO ass = null;

								try {
									ass = new AssiduidadeDAO(conn);
								} catch (SQLException e3) {
									// TODO Auto-generated catch block
									e3.printStackTrace();
								}

							Assiduidade model = null;
							Timestamp ts;

								model = new Assiduidade();

								Date date = new Date();
								long time = date.getTime();
								ts = new Timestamp(time);

								model.setCodigo_matricula(Integer.parseInt(aux));
								model.setData_entrada(ts);
							

								try {
									ass.Insert(model);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								//add a tabela
								try {
									assModel.addListaDeAssiduidades(
											new AssiduidadeDAO(conn).SelectAllP(Integer.parseInt(aux)));
								} catch (NumberFormatException | SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}

						
						//Fatura Matricula
						
						
						
						invModel = new InvoicesCheck2TableModel();
						

							try {
								invModel.addListaDeInvoice(new InvoiceDAO(conn).SelectAllP(Integer.parseInt(aux)));
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
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
		
		//tabela consulta 
		
		pnlConsulta = new JPanel();
		invModel = new InvoicesCheck2TableModel();
		pnlConsulta.setLayout(null);

		tblConsulta = new JTable(invModel);
		tblConsulta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpConsulta = new JScrollPane(tblConsulta);
		scpConsulta.setBounds(0, 0, 431, 220);
		pnlConsulta.add(scpConsulta);
		pnlConsulta.setBounds(230, 277, 431, 220);
		getContentPane().add(pnlConsulta);
		
		
		//Tabela ConsultaAL
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
