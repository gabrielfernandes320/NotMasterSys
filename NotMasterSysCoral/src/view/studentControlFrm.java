package view;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;

import database.AssiduidadeDAO;
import database.ConnectionFactory;
import database.InvoiceDAO;
import database.MatriculaDAO;
import database.ModalidadesDAO;
import lib.MasterMonthChooser;
import model.Invoice;
import model.Matricula;
import table.model.AssiduidadeTableModel;
import table.model.InvoicesCheckTableModel;
import table.model.InvoicesStudentCtrlTableModel;
import table.model.ModalidadeMatriculaTableModel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;

public class studentControlFrm extends JInternalFrame {

	private AssiduidadeTableModel aTableModel;
	private InvoicesStudentCtrlTableModel iTableModel;
	private ModalidadeMatriculaTableModel mTableModel;
	private JTextField txfNumMatricula;
	private JTextField txfNomeAluno;
	private JTable assiduidadeTable;
	private JTable invoicesTable;
	private MasterMonthChooser mmcData;
	

	Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
	private JTable modalityTable;

	public studentControlFrm() {
		setClosable(true);
		// setIconifiable(true);
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

		JButton studentBtn = new JButton("Acessar dados do Aluno");
		studentBtn.setBounds(230, 236, 210, 30);
		getContentPane().add(studentBtn);

		JButton matriculaBtn = new JButton("Acessar dados da Matr\u00EDcula");
		matriculaBtn.setBounds(450, 236, 210, 30);
		getContentPane().add(matriculaBtn);
		
		aTableModel = new AssiduidadeTableModel();
		iTableModel = new InvoicesStudentCtrlTableModel();
		mTableModel = new ModalidadeMatriculaTableModel();
		
		mmcData = new MasterMonthChooser();
		mmcData.setBounds(22, 236, 181, 30);
		getContentPane().add(mmcData);
		
		JScrollPane aScrollPane = new JScrollPane();
		aScrollPane.setBounds(22, 277, 181, 203);
		getContentPane().add(aScrollPane);
		
		assiduidadeTable = new JTable(aTableModel);
		aScrollPane.setViewportView(assiduidadeTable);
		
		JScrollPane iScrollPane = new JScrollPane();
		iScrollPane.setBounds(229, 277, 432, 203);
		getContentPane().add(iScrollPane);
		
		invoicesTable = new JTable(iTableModel);
		iScrollPane.setViewportView(getNewRenderedTable(invoicesTable));
		
		JScrollPane mScrollPane = new JScrollPane();
		mScrollPane.setBounds(230, 54, 431, 101);
		getContentPane().add(mScrollPane);
		
		modalityTable = new JTable(mTableModel);
		mScrollPane.setViewportView(modalityTable);
		
		// COISAS IMPORTANTES
		
		txfNumMatricula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						
						updateFieldsAndTables(txfNumMatricula.getText());
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}	
		});

	}
	
	private void updateFieldsAndTables(String cod_matricula) throws SQLException {
		
		InvoiceDAO iDAO = new InvoiceDAO(conn);
		AssiduidadeDAO aDAO = new AssiduidadeDAO(conn);
		ModalidadesDAO mDAO = new ModalidadesDAO(conn);
		
		//CARREGAR TABLE FATURAS
		List<Invoice> invoicesList = iDAO.SelectAllP(Integer.parseInt(cod_matricula));
		iTableModel.addListaDeInvoice(invoicesList);	
		
		//CARREGAR NOME
		txfNomeAluno.setText(invoicesList.get(0).getNome_aluno());
		
		//CARREGAR ASSIDUIDADE
		//aDAO.SelectByMonth(Integer.parseInt(cod_matricula), mmcData.getDate());
		
	}

	private JTable getNewRenderedTable(final JTable tabela) {

		tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String payed = String.valueOf(table.getValueAt(row, 2));
				String canceled = String.valueOf(table.getValueAt(row, 3));
				Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
				Matricula matri = new Matricula();
				try {
					
					MatriculaDAO md = new MatriculaDAO(conn);					
					matri.setCodigo_matricula(Integer.parseInt(txfNumMatricula.getText()));
					matri = md.checkMatricula(matri);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				if (payed == "null" && canceled == "null" && String.valueOf(matri.getData_encerramento()) == "null") {
					
					setBackground(Color.WHITE);
					setForeground(Color.BLACK);
					
					
				} else if(payed != "null"){
					
					setBackground(Color.GREEN);
					setForeground(Color.BLACK);
					
				
				} else if(payed == "null" && String.valueOf(matri.getData_encerramento()) != "null"){
					
					setBackground(Color.RED);
					setForeground(Color.BLACK);
					
						
				} else {
					
					setBackground(Color.YELLOW);
					setForeground(Color.BLACK);
					
				}

				return this;
				
			}
		});

		return tabela;

	}
}
