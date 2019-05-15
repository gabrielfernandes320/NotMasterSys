package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import database.AlunoDAO;
import database.ConnectionFactory;
import database.MatriculaDAO;
import database.Matricula_ModalidadeDAO;
import model.Aluno;
import model.Matricula;
import model.Matricula_Modalidade;
import table.model.PlansTableModel;
import table.model.ModalidadeMatriculaTableModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.util.List;
import java.util.Locale;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTable;
import javax.swing.Action;

public class MatriculaFrm extends JInternalFrame{
	private JTextField txtMatricula;
	private JTextField txtAluno;
	private JTextField txtCodAluno;
	private JTextField txtVencimento;

	private JTable modalityTable;
	
	/**
	 * Create the frame.
	 */
	public MatriculaFrm() {
	
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");	
		Matricula model = new Matricula();
		
		setClosable(true);
		setTitle("Matricular aluno");
		setBounds(100, 100, 574, 450);
		getContentPane().setLayout(null);
		
		JButton btnPesquisar = new JButton("Buscar");
		btnPesquisar.setIcon(new ImageIcon(MatriculaFrm.class.getResource("/view/images/localizar.png")));
		btnPesquisar.setBounds(10, 11, 100, 35);
		getContentPane().add(btnPesquisar);
		btnPesquisar.setBackground(SystemColor.menu);
		
		JButton btnAtualizar = new JButton("Atualizar");
		//btnAtualizar.setIcon(new ImageIcon(MatriculaFrm.class.getResource("/view/images/atualizar.png")));
		btnAtualizar.setBounds(444, 176, 104, 23);
		getContentPane().add(btnAtualizar);
		btnAtualizar.setBackground(SystemColor.menu);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(MatriculaFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setBounds(120, 11, 124, 35);
		getContentPane().add(btnAdicionar);
		btnAdicionar.setBackground(SystemColor.menu);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);
		btnRemover.setIcon(new ImageIcon(MatriculaFrm.class.getResource("/view/images/remover.png")));
		btnRemover.setBounds(300, 11, 124, 35);
		getContentPane().add(btnRemover);
		btnRemover.setEnabled(false);
		btnRemover.setBackground(SystemColor.menu);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setIcon(new ImageIcon(MatriculaFrm.class.getResource("/view/images/salvar.png")));
		btnSalvar.setBounds(440, 11, 100, 35);
		getContentPane().add(btnSalvar);
		btnSalvar.setEnabled(false);
		btnSalvar.setBackground(SystemColor.menu);
		
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
		btnAdicionarModalidade.setBackground(SystemColor.menu);
	
		JScrollPane mScrollPane = new JScrollPane();
		mScrollPane.setBounds(10, 204, 544, 200);
		getContentPane().add(mScrollPane);
		
		ModalidadeMatriculaTableModel mTableModel = new ModalidadeMatriculaTableModel();
		JTable modalityTable = new JTable(mTableModel);
		mScrollPane.setViewportView(modalityTable);
		
		txtAluno.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					

					try {
						
						conn.setAutoCommit(false);
						
						MatriculaDAO dao = new MatriculaDAO(conn);
						Matricula_ModalidadeDAO matDao = new Matricula_ModalidadeDAO(conn);
						Matricula model = new Matricula();
						Matricula_Modalidade matModel = new Matricula_Modalidade();
						
						model.setAluno(txtAluno.getText());
			
						model = dao.SelectAluno(model);

						txtCodAluno.setText(Integer.toString(model.getCodigo_aluno()));
						txtMatricula.setText(Integer.toString(dao.NextCodigoMatricula(model)));
						txtAluno.setText(model.getAluno());
						
						if (model.getCodigo_aluno() != 0) {			
							txtAluno.setEnabled(true);
							txtVencimento.setEnabled(true);
							//btnSalvar.setEnabled(true);
							dataMatriculaField.setEnabled(true);
							btnAdicionarModalidade.setEnabled(true);
							
							
							matModel.setCodigo_matricula(Integer.parseInt(txtMatricula.getText()));
							
							List<Matricula_Modalidade> matModList = matDao.selectPorCodigoAluno(model); 
							
							mTableModel.addListaDeMatricula_Modalidade(matModList);
							mScrollPane.setViewportView(modalityTable);
							
							modalityTable.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									if (e.getClickCount() == 1) {

										btnRemover.setEnabled(true);							
									}
								}
							});
							
						} else {
							JOptionPane.showMessageDialog(getContentPane(), "Erro: Aluno não encontrado!");
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
		
		btnAtualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					MatriculaDAO dao = new MatriculaDAO(conn);
					Matricula_ModalidadeDAO matDao = new Matricula_ModalidadeDAO(conn);
					Matricula model = new Matricula();
					Matricula_Modalidade matModel = new Matricula_Modalidade();
				
					model.setAluno(txtAluno.getText());
					
					model = dao.SelectAluno(model);
					
					matModel.setCodigo_matricula(model.getCodigo_aluno());
					
					List<Matricula_Modalidade> matModList = matDao.selectPorCodigoAluno(model); 
					
					mTableModel.addListaDeMatricula_Modalidade(matModList);
					mScrollPane.setViewportView(modalityTable);
					
					if (modalityTable.getRowCount() > 0) {
						btnRemover.setEnabled(true);
					}
						
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}		
			}
		});
		
		btnAdicionar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Adicionar Aluno");
				
				try {
					
					conn.setAutoCommit(false);
					MatriculaDAO dao = new MatriculaDAO(conn);
					
					txtAluno.setEnabled(true);
					txtVencimento.setEnabled(true);
					txtCodAluno.setEnabled(true);
					btnAdicionarModalidade.setEnabled(true);
					btnSalvar.setEnabled(true);
					dataMatriculaField.setEnabled(true);
					
					txtCodAluno.setText(Integer.toString(dao.NextCodigoAluno(model)));
					txtMatricula.setText(Integer.toString(dao.NextCodigoMatricula(model)));	
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Erro: Aluno não cadastrado!");
					e1.printStackTrace();
				}
				
			}
		}); //btn adicionar
		
		btnRemover.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Matricula_Modalidade model = new Matricula_Modalidade();
					Matricula_ModalidadeDAO dao = new Matricula_ModalidadeDAO(conn);
					Matricula mat = new Matricula();
					MatriculaDAO matDao = new MatriculaDAO(conn);
				
					mat.setCodigo_aluno(Integer.parseInt(txtCodAluno.getText()));
					
					List<Matricula_Modalidade> matModList = dao.selectPorCodigoAluno(mat); 
					
					model = (Matricula_Modalidade) matModList.get(modalityTable.getSelectedRow());
					System.out.println("Element "+model.getCodigo_matricula());

					mat.setCodigo_matricula(model.getCodigo_matricula());
					
					dao.Delete(model);
					
					matModList = dao.selectPorCodigoAluno(mat); 
					Matricula_Modalidade matModel = new Matricula_Modalidade();
					matModel.setCodigo_matricula(Integer.parseInt(txtMatricula.getText()));
					
					mTableModel.addListaDeMatricula_Modalidade(matModList);
					mScrollPane.setViewportView(modalityTable);
						
					if (modalityTable.getRowCount() == 0) {
						int opt = JOptionPane.showConfirmDialog(null, "Todas as modalidades foram encerradas. \nDeseja encerrar a matrícula?", "Aviso", JOptionPane.YES_NO_OPTION);
						
						if (opt == 0) {
							System.out.println("Encerrando matrícula...");
							matDao.DeleteMatricula(mat);
						} else {
							System.out.println("Matricula continua cadastrada.");
						}
					}
					
					btnRemover.setEnabled(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		}); //btn remover
		
		btnAdicionarModalidade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					conn.setAutoCommit(false);
			
					Date data_matricula = new Date(df.parse(dataMatriculaField.getText()).getTime());
					
					model.setCodigo_matricula(Integer.parseInt(txtMatricula.getText()));
					model.setCodigo_aluno(Integer.parseInt(txtCodAluno.getText()));
					model.setData_matricula(data_matricula);
					model.setDia_vencimento(Integer.parseInt(txtVencimento.getText()));
					model.setAluno(txtAluno.getText());
					
					if (model.getDia_vencimento() >= 1 && model.getDia_vencimento() <= 28) {		
						AddModalidadeFrm addMod = new AddModalidadeFrm(MatriculaFrm.this, model);			
						addMod.setVisible(true);
						
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Erro: Dia do vencimento inválido!");
					}
					
								
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Erro: Aluno não cadastrado!");
					e1.printStackTrace();
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Erro: Insira data de matrícula!");
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Erro: Insira um dia de vencimento!");
				}
			}
		}); //btnAdicionarModalidade
			
		btnSalvar.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
									
				try {
					
					conn.setAutoCommit(false);
					MatriculaDAO dao = new MatriculaDAO(conn);
					Matricula model = new Matricula();
					
					Date data_matricula = new Date(df.parse(dataMatriculaField.getText()).getTime());
					
					model.setCodigo_matricula(Integer.parseInt(txtMatricula.getText()));
					model.setCodigo_aluno(Integer.parseInt(txtCodAluno.getText()));
					model.setData_matricula(data_matricula);
					model.setDia_vencimento(Integer.parseInt(txtVencimento.getText()));
					model.setAluno(txtAluno.getText());
					
					dao.Insert(model);
					
			
				
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Erro: Aluno não cadastrado!");
					e1.printStackTrace();
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Erro: Insira data de matrícula!");
					e1.printStackTrace();
				}	catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Erro: Insira um dia de vencimento!");
				}				
			}
		}); //btnSalvar
		
		btnPesquisar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Adicionar Aluno");
				
				StudentSearchFrm searchForm = new StudentSearchFrm(MatriculaFrm.this);
				searchForm.setVisible(true);
				
			}
		}); //btn pesquisar
		
		

	}
	
	void updateTable () {
		
	}
	
	void updateFields(Matricula matricula) throws SQLException {
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		Matricula_ModalidadeDAO dao = new Matricula_ModalidadeDAO(conn);
		Matricula_Modalidade mat = new Matricula_Modalidade();
		
		System.out.println(matricula.getAluno());
		txtCodAluno.setText(String.valueOf(matricula.getCodigo_aluno()));
		txtAluno.setText(matricula.getAluno());
		
	}

}
