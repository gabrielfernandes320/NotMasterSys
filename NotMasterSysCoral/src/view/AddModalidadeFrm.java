package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import database.ConnectionFactory;
import database.GraduacoesDAO;
import database.MatriculaDAO;
import database.Matricula_ModalidadeDAO;
import database.ModalidadesDAO;
import database.PlanosDAO;
import model.Graduacoes;
import model.Matricula;
import model.Matricula_Modalidade;
import model.Plano;
import table.model.StudentsTableModel;

import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AddModalidadeFrm extends JDialog {

	private static MatriculaFrm window;
	private String[] modalidades = null;
	//private List<Graduacoes> graduacoes = new ArrayList<Graduacoes>();
	private String[] graduacoes = null;
	private String[] planos = null;

	Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

	/**
	 * Create the frame.
	 */
	public AddModalidadeFrm(final MatriculaFrm window, final Matricula matricula) {
		setBounds(100, 100, 258, 387);
		//setClosable(true);
		getContentPane().setLayout(null);
		
		ModalidadesDAO mod;
		GraduacoesDAO grad;
		PlanosDAO plan;
		
		Matricula_Modalidade model = new Matricula_Modalidade();
		
		try {
			mod = new ModalidadesDAO(conn);
			grad = new GraduacoesDAO(conn);
			plan = new PlanosDAO(conn);
			
			modalidades = mod.SelectAllModalidade();
			JComboBox comboBoxModalidade = new JComboBox(modalidades);
			comboBoxModalidade.setBounds(10, 37, 222, 22);
			getContentPane().add(comboBoxModalidade);	
			
			JLabel lblModalidade = new JLabel("Modalidade:");
			lblModalidade.setBounds(10, 12, 222, 14);
			getContentPane().add(lblModalidade);
			
			graduacoes = grad.SelectPorModalidade(String.valueOf(comboBoxModalidade.getSelectedItem()));
			JComboBox comboBoxGraduacao = new JComboBox(graduacoes);
			comboBoxGraduacao.setBounds(10, 95, 222, 22);
			getContentPane().add(comboBoxGraduacao);
				
			JLabel labelGraduacao = new JLabel("Gradua\u00E7\u00E3o:");
			labelGraduacao.setBounds(10, 70, 222, 14);
			getContentPane().add(labelGraduacao);
			
			planos = plan.SelectPorModalidade(String.valueOf(comboBoxModalidade.getSelectedItem()));
			JComboBox comboBoxPlano = new JComboBox(planos);
			comboBoxPlano.setBounds(10, 153, 222, 22);
			getContentPane().add(comboBoxPlano);
			//planos = plan.SelectAllModalidade();
			
			JLabel labelPlano = new JLabel("Plano:");
			labelPlano.setBounds(10, 128, 222, 14);
			getContentPane().add(labelPlano);
			
			JLabel labelDataInicio = new JLabel("Data inicio:");
			labelDataInicio.setBounds(10, 186, 222, 14);
			getContentPane().add(labelDataInicio);
			
			JLabel labelDataFim = new JLabel("Data fim:");
			labelDataFim.setBounds(10, 244, 222, 14);
			getContentPane().add(labelDataFim);
			
			MaskFormatter maskData = null;
			MaskFormatter maskData1 = null;
			try {
				maskData = new MaskFormatter("##/##/####");
				maskData1 = new MaskFormatter("##/##/####");
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			JFormattedTextField txtDataInicio = new JFormattedTextField(df);
			txtDataInicio.setBounds(10, 213, 222, 20);
			getContentPane().add(txtDataInicio);
			maskData.install(txtDataInicio);
			
			JFormattedTextField txtDataFim = new JFormattedTextField(df);
			txtDataFim.setBounds(10, 269, 222, 20);
			getContentPane().add(txtDataFim);
			maskData1.install(txtDataFim);
			
			JButton btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setBounds(10, 314, 89, 23);
			getContentPane().add(btnConfirmar);
			btnConfirmar.setBackground(SystemColor.menu);
			
			comboBoxModalidade.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						graduacoes = grad.SelectPorModalidade(String.valueOf(comboBoxModalidade.getSelectedItem()));
						comboBoxGraduacao.setModel(new DefaultComboBoxModel(graduacoes));
						
						planos = plan.SelectPorModalidade(String.valueOf(comboBoxModalidade.getSelectedItem()));
						comboBoxPlano.setModel(new DefaultComboBoxModel(planos));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
			});
			
			btnConfirmar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						conn.setAutoCommit(false);
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					
					
					Matricula_ModalidadeDAO dao;
					
					try {
						dao = new Matricula_ModalidadeDAO(conn);
						MatriculaDAO matDao = new MatriculaDAO(conn);
						
						Date data_inicio = null;
						Date data_fim = null;
						
						try {
							data_inicio = new Date(df.parse(txtDataInicio.getText()).getTime());
							data_fim = new Date(df.parse(txtDataFim.getText()).getTime());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						model.setCodigo_matricula(matricula.getCodigo_matricula());
						model.setModalidade(String.valueOf(comboBoxModalidade.getSelectedItem()));
						model.setGraduacao(String.valueOf(comboBoxGraduacao.getSelectedItem()));
						model.setPlano(String.valueOf(comboBoxPlano.getSelectedItem()));
						model.setData_inicio(data_inicio);
						model.setData_fim(data_fim);
						
						if (model.getData_inicio() != null) {
							System.out.println("certo");
							
							matDao.Insert(matricula);
							dao.Insert(model);
							
							dispose();
						} else {
							JOptionPane.showMessageDialog(getContentPane(), "Erro: Insira data de início!");
						}
						
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(getContentPane(), "Erro: Aluno não está cadastrado!");
						e2.printStackTrace();
					} 
					
					
				}
			});
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		

		
	}
}
