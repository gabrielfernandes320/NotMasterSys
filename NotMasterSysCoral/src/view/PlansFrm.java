package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;
import com.sun.javafx.geom.transform.BaseTransform;

import database.ConnectionFactory;
import database.ModalidadesDAO;
import database.PlanosDAO;
import model.Plano;
import sun.security.x509.IssuingDistributionPointExtension;

public class PlansFrm extends JInternalFrame {
	private JTextField txfPlano;
	private JTextField txfValor;
	private JComboBox cbxModalidade;
	private String[] modalidades = null;
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnSalvar;
	private JButton btnRemover;
	private Boolean IsUpdate;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */

	public PlansFrm() {
		setBounds(100, 100, 480, 210);
		getContentPane().setLayout(null);
		setResizable(false);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setClosable(true);
		setIconifiable(true);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 11, 96, 31);
		btnBuscar.setPreferredSize(new Dimension(40, 25));
		btnBuscar.setBackground(new Color(240, 240, 240));
		btnBuscar.setIcon(new ImageIcon(PlansFrm.class.getResource("/view/images/localizar.png")));
		getContentPane().add(btnBuscar);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(PlansFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(104, 11, 114, 31);
		getContentPane().add(btnAdicionar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(PlansFrm.class.getResource("/view/images/salvar.png")));
		btnSalvar.setPreferredSize(new Dimension(40, 25));
		btnSalvar.setBackground(SystemColor.menu);
		btnSalvar.setBounds(328, 11, 114, 31);
		getContentPane().add(btnSalvar);

		btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon(PlansFrm.class.getResource("/view/images/remover.png")));
		btnRemover.setPreferredSize(new Dimension(40, 25));
		btnRemover.setBackground(SystemColor.menu);
		btnRemover.setBounds(216, 11, 114, 31);
		getContentPane().add(btnRemover);

		JLabel lblNewLabel = new JLabel("Modalidade:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 79, 86, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Plano:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 109, 68, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Valor:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 139, 46, 14);
		getContentPane().add(lblNewLabel_3);

		// tem que colocar as modalidades dentro do JComboBox
//		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
//		PlanosDAO dao = new PlanosDAO(conn);
//		String modalidade[];
//		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

		// PlanosDAO pla = new PlanosDAO(conn);

		ModalidadesDAO mod;

		try {
			mod = new ModalidadesDAO(conn);
			modalidades = mod.SelectAllModalidade();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		cbxModalidade = new JComboBox(modalidades);
		cbxModalidade.setBounds(113, 78, 328, 20);
		cbxModalidade.setSelectedItem("");
		getContentPane().add(cbxModalidade);

		txfPlano = new JTextField();
		txfPlano.setBounds(113, 108, 324, 20);
		getContentPane().add(txfPlano);
		txfPlano.setColumns(10);

		txfValor = new JTextField();
		txfValor.setBounds(113, 138, 168, 20);
		getContentPane().add(txfValor);
		txfValor.setColumns(10);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxModalidade.getSelectedItem() == null) {

					JOptionPane.showMessageDialog(null, "Nenhuma modalidade cadastrada!");

				} else

				if (txfPlano.getText().isEmpty() || txfValor.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Algum campo de texto pode estar vazio!");

				} else {

					if (IsUpdate) {

						try {
							conn.setAutoCommit(false);
							System.out.println("Conectado com sucesso!");

							PlanosDAO dao = new PlanosDAO(conn);
							Plano model = new Plano();

							model.setPlano(txfPlano.getText());
							model.setModalidade(cbxModalidade.getSelectedItem().toString());
							model.setValor(Double.parseDouble(txfValor.getText()));
							try {
								dao.Update(model);
								JOptionPane.showMessageDialog(null, "Salvo com Sucesso!");
								esvaziarCampos();
								fecharBotoes();
								fecharCampos();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					} else {

						try {
							conn.setAutoCommit(false);
							System.out.println("Conectado com sucesso!");

							PlanosDAO dao = new PlanosDAO(conn);
							Plano model = new Plano();

							model.setPlano(txfPlano.getText());
							model.setModalidade(cbxModalidade.getSelectedItem().toString());
							model.setValor(Double.parseDouble(txfValor.getText()));
							try {
								dao.Insert(model);
								JOptionPane.showMessageDialog(btnAdicionar, "Adicionado com Sucesso!");
								esvaziarCampos();
								fecharBotoes();
								fecharCampos();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IsUpdate = false;
				btnSalvar.setEnabled(true);
				btnRemover.setEnabled(false);
				abrirCampos();
				esvaziarCampos();

			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IsUpdate = true;
				PlansSearch plans = new PlansSearch(PlansFrm.this);
				plans.setVisible(true);

			}
		});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					conn.setAutoCommit(false);
					System.out.println("Conectado com sucesso!");

					PlanosDAO dao = new PlanosDAO(conn);
					Plano model = new Plano();

					model.setPlano(txfPlano.getText());
					model.setModalidade(cbxModalidade.getSelectedItem().toString());
					model.setValor(Double.parseDouble(txfValor.getText()));
					try {
						dao.Delete(model);
						JOptionPane.showMessageDialog(btnRemover, "Removido com Sucesso!");
						esvaziarCampos();
						fecharBotoes();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		fecharCampos();
		fecharBotoes();
		esvaziarCampos();
	}

	public void Update(final Plano p) {

		txfPlano.setText(p.getPlano());
		txfValor.setText("" + p.getValor());
		cbxModalidade.setSelectedItem(p.getModalidade());
		abrirBotoes();
		updateCampos();
		IsUpdate = true;

//	while()
//	cbxModalidade.setSelectedIndex();
//		
	}

	public void abrirCampos() {
		txfPlano.setEnabled(true);
		txfValor.setEnabled(true);
		cbxModalidade.setEnabled(true);
	}

	public void fecharCampos() {
		txfPlano.setEnabled(false);
		txfValor.setEnabled(false);
		cbxModalidade.setEnabled(false);
	}

	public void abrirBotoes() {
		btnRemover.setEnabled(true);
		btnSalvar.setEnabled(true);
	}

	public void fecharBotoes() {
		btnAdicionar.setEnabled(true);
		btnBuscar.setEnabled(true);
		btnRemover.setEnabled(false);
		btnSalvar.setEnabled(false);
	}

	public void esvaziarCampos() {
		txfPlano.setText("");
		txfValor.setText("");
		try {
			cbxModalidade.setSelectedIndex(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateCampos() {
		txfPlano.setEnabled(false);
		cbxModalidade.setEnabled(false);
		txfValor.setEnabled(true);
	}

}
