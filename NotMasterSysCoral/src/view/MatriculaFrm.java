package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.text.MaskFormatter;

import database.AlunoDAO;
import database.ConnectionFactory;
import database.MatriculaDAO;
import model.Aluno;
import model.Matricula;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.Action;

public class MatriculaFrm extends JInternalFrame{
	private JTextField txtMatricula;
	private JTextField txtAluno;
	private JTextField txtCodAluno;
	private JTextField txtVencimento;

	
	
	/**
	 * Create the frame.
	 */
	public MatriculaFrm() {
		setClosable(true);
		setTitle("Matricular aluno");
		setBounds(100, 100, 574, 350);
		getContentPane().setLayout(null);
		
		JButton btnPesquisar = new JButton("Buscar");
		btnPesquisar.setIcon(new ImageIcon(MatriculaFrm.class.getResource("/view/images/localizar.png")));
		btnPesquisar.setBounds(10, 11, 89, 35);
		getContentPane().add(btnPesquisar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(MatriculaFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setBounds(109, 11, 124, 35);
		getContentPane().add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		btnRemover.setIcon(new ImageIcon(MatriculaFrm.class.getResource("/view/images/remover.png")));
		btnRemover.setBounds(325, 11, 124, 35);
		getContentPane().add(btnRemover);
		btnRemover.setEnabled(false);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setIcon(new ImageIcon(MatriculaFrm.class.getResource("/view/images/salvar.png")));
		btnSalvar.setBounds(459, 11, 89, 35);
		getContentPane().add(btnSalvar);
		btnSalvar.setEnabled(false);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(10, 69, 79, 14);
		getContentPane().add(lblMatricula);		
		
		txtMatricula = new JTextField();
		txtMatricula.setEnabled(false);
		txtMatricula.setBounds(129, 57, 104, 29);
		getContentPane().add(txtMatricula);
		txtMatricula.setColumns(10);
		txtMatricula.setEnabled(false);
		
		JLabel lblAluno = new JLabel("Aluno:");
		lblAluno.setBounds(10, 103, 46, 14);
		getContentPane().add(lblAluno);
		
		txtAluno = new JTextField();
		txtAluno.setText("");
		txtAluno.setBounds(129, 96, 104, 29);
		getContentPane().add(txtAluno);
		txtAluno.setColumns(10);
	
		txtCodAluno = new JTextField();
		txtCodAluno.setEnabled(false);
		txtCodAluno.setBounds(243, 96, 305, 29);
		getContentPane().add(txtCodAluno);
		txtCodAluno.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Data de Matr\u00EDcula:");
		lblNewLabel.setBounds(10, 141, 159, 14);
		getContentPane().add(lblNewLabel);
		
		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		JFormattedTextField dataMatriculaField = new JFormattedTextField(df);
		dataMatriculaField.setBounds(129, 136, 104, 29);
		getContentPane().add(dataMatriculaField);
		maskData.install(dataMatriculaField);
		dataMatriculaField.setEnabled(false);
		
		JLabel lblNewLabel_1 = new JLabel("Dia do vencimento da fatura:");
		lblNewLabel_1.setBounds(253, 141, 283, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtVencimento = new JTextField();
		txtVencimento.setEnabled(false);
		txtVencimento.setBounds(444, 136, 104, 29);
		getContentPane().add(txtVencimento);
		txtVencimento.setColumns(10);
		
		JButton btnAdicionarModalidade = new JButton("Adicionar Modalidade");
		btnAdicionarModalidade.setBounds(10, 176, 202, 23);
		getContentPane().add(btnAdicionarModalidade);
		btnAdicionarModalidade.setEnabled(false);

		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		
		txtAluno.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					
					System.out.println("Buscando por " + txtAluno.getText() + "...");		
					
					try {
						
						conn.setAutoCommit(false);
						MatriculaDAO dao = new MatriculaDAO(conn);
						Matricula model = new Matricula();
						
						model.setAluno(txtAluno.getText());
						
						model = dao.SelectAluno(model);

						txtCodAluno.setText(Integer.toString(model.getCodigo_aluno()));
						
						if (model.getCodigo_aluno() != 0) {			
							txtAluno.setEnabled(true);
							txtVencimento.setEnabled(true);
							btnSalvar.setEnabled(true);
							dataMatriculaField.setEnabled(true);
							btnAdicionarModalidade.setEnabled(true);
						}
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnAdicionar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Adicionar Aluno");
				
				txtAluno.setEnabled(true);
				txtVencimento.setEnabled(true);
				btnAdicionarModalidade.setEnabled(true);
				btnSalvar.setEnabled(true);
				dataMatriculaField.setEnabled(true);
				
				
			}
		}); //btn adicionar
		
		btnSalvar.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
									
				try {
					
					conn.setAutoCommit(false);
					MatriculaDAO dao = new MatriculaDAO(conn);
					Matricula model = new Matricula();
					
					Date data_matricula = new Date(df.parse(dataMatriculaField.getText()).getTime());
					
					model.setCodigo_aluno(Integer.parseInt(txtCodAluno.getText()));
					model.setData_matricula(data_matricula);
					model.setDia_vencimento(Integer.parseInt(txtVencimento.getText()));
					model.setAluno(txtAluno.getText());
					
					dao.Insert(model);
					JOptionPane.showMessageDialog(getContentPane(), "Sucesso! Aluno Matriculado");
			
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}					
			}
		}); //btnSalvar
		
		btnAdicionarModalidade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				AddModalidadeFrm addMod = new AddModalidadeFrm(MatriculaFrm.this);
				addMod.setVisible(true);
				
			}
		}); //btnAdicionarModalidade
	}
	
	

}
