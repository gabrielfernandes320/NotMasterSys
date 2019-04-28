package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;

public class studentControlFrm extends JInternalFrame {
	private JTextField txfNumMatricula;
	private JTextField txfNomeAluno;
	private JTable tblConsulta;
	private JTextField txfSituacaoColsulta;
	private JTable tblCosultaAl;
	private JTable tblAssiduidade;
	private JPanel pnlData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentControlFrm frame = new studentControlFrm();
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
	public studentControlFrm() {
		setIconifiable(true);
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
		
		tblCosultaAl = new JTable();
		tblCosultaAl.setBounds(230, 53, 431, 102);
		getContentPane().add(tblCosultaAl);
		
		tblAssiduidade = new JTable();
		tblAssiduidade.setBounds(22, 277, 181, 220);
		getContentPane().add(tblAssiduidade);
		
		pnlData = new JPanel();
		pnlData.setBounds(22, 236, 181, 30);
		getContentPane().add(pnlData);

	}
}
