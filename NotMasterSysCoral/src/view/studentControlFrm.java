package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import lib.MasterMonthChooser;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;
import javax.swing.DropMode;
import java.awt.Component;
import java.awt.Point;
import java.awt.Insets;

public class studentControlFrm extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1826784769218740550L;
	private JTextField txfNumMatricula;
	private JTextField txfNomeAluno;
	private JTable tblConsulta;
	private JTextField txfSituacaoColsulta;
	private JTable tblCosultaAl;
	private JTable tblAssiduidade;
	private JPanel pnlData;
	private MasterMonthChooser mmcData;
	private JTextField txfSituaçãoRegular;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

					studentControlFrm frame = new studentControlFrm();
					frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public studentControlFrm() {
//		setIconifiable(true);
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
		txfSituacaoColsulta.setVisible(false);
		txfSituacaoColsulta.setMargin(new Insets(2, 110, 2, 2));
		txfSituacaoColsulta.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 19));
		txfSituacaoColsulta.setText("Aguardando Consulta ...");
		txfSituacaoColsulta.setEditable(false);
		txfSituacaoColsulta.setColumns(10);
		txfSituacaoColsulta.setBounds(230, 166, 431, 54);
//		getContentPane().add(txfSituacaoColsulta);
		
		txfSituaçãoRegular = new JTextField();
		txfSituaçãoRegular.setText("Situação Regular!!");
		txfSituaçãoRegular.setMargin(new Insets(2, 110, 2, 2));
		txfSituaçãoRegular.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 19));
		txfSituaçãoRegular.setEditable(false);
		txfSituaçãoRegular.setColumns(10);
		txfSituaçãoRegular.setBounds(230, 166, 431, 54);
		getContentPane().add(txfSituaçãoRegular);
		
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
		
		mmcData = new MasterMonthChooser();
		mmcData.setBounds(22, 236, 181, 30);
		getContentPane().add(mmcData);

	}
}
