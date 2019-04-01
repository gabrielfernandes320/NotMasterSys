package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import database.ConnectionFactory;
import database.PlanosDAO;
import model.Plano;

public class PlansFrm extends JInternalFrame {
	private JTextField txfPlano;
	private JTextField txfValor;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlansFrm frame = new PlansFrm();
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
	public PlansFrm() {
		setBounds(100, 100, 480, 210);
		getContentPane().setLayout(null);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 11, 96, 31);
		btnBuscar.setPreferredSize(new Dimension(40, 25));
		btnBuscar.setBackground(new Color(240, 240, 240));
		btnBuscar.setIcon(new ImageIcon(PlansFrm.class.getResource("/view/images/localizar.png")));
		getContentPane().add(btnBuscar);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(PlansFrm.class.getResource("/view/images/adicionar.png")));
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(104, 11, 114, 31);
		getContentPane().add(btnAdicionar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(PlansFrm.class.getResource("/view/images/salvar.png")));
		btnSalvar.setPreferredSize(new Dimension(40, 25));
		btnSalvar.setBackground(SystemColor.menu);
		btnSalvar.setBounds(328, 11, 114, 31);
		getContentPane().add(btnSalvar);

		JButton btnRemover = new JButton("Remover");
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

		JComboBox cbxModalidade = new JComboBox(new String[] { "Boxe", "Capoeira", "Karate", "Taekwondo" });
		cbxModalidade.setBounds(113, 78, 328, 20);
		getContentPane().add(cbxModalidade);

		txfPlano = new JTextField();
		txfPlano.setBounds(113, 108, 324, 20);
		getContentPane().add(txfPlano);
		txfPlano.setColumns(10);

		txfValor = new JTextField();
		txfValor.setBounds(113, 138, 168, 20);
		getContentPane().add(txfValor);
		txfValor.setColumns(10);

		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

		btnSalvar.addActionListener(new ActionListener() {
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
						dao.Insert(model);
						JOptionPane.showMessageDialog(btnAdicionar, "Adicionado com Sucesso!");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn.setAutoCommit(false);

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
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});

	}
}
