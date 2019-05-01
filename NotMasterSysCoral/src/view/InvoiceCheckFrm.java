package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import database.ConnectionFactory;
import database.InvoiceDAO;
import database.UsuarioDAO;
import model.Invoice;
import model.Usuario;
import table.model.InvoiceCheckTableModel;
import table.model.UsuariosTableModel;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class InvoiceCheckFrm extends JInternalFrame {
	private JTextField tbInitialDate;
	private JTextField tbFinalDate;
	private InvoiceCheckTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoiceCheckFrm frame = new InvoiceCheckFrm();
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
	public InvoiceCheckFrm() {
		setClosable(true);
		setBounds(100, 100, 757, 390);
		getContentPane().setLayout(null);
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setFont(new Font("Arial", Font.BOLD, 12));
		lblDe.setBounds(27, 13, 28, 14);
		getContentPane().add(lblDe);
		
		tbInitialDate = new JTextField();
		tbInitialDate.setBounds(51, 11, 102, 20);
		getContentPane().add(tbInitialDate);
		tbInitialDate.setColumns(10);
		
		JLabel lblAt = new JLabel("At\u00E9:");
		lblAt.setFont(new Font("Arial", Font.BOLD, 12));
		lblAt.setBounds(165, 13, 28, 14);
		getContentPane().add(lblAt);
		
		tbFinalDate = new JTextField();
		tbFinalDate.setColumns(10);
		tbFinalDate.setBounds(197, 11, 115, 20);
		getContentPane().add(tbFinalDate);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setFont(new Font("Arial", Font.BOLD, 12));
		lblSituao.setBounds(326, 13, 66, 14);
		getContentPane().add(lblSituao);
		
		JComboBox cbSituation = new JComboBox();
		cbSituation.setBounds(402, 11, 115, 20);
		getContentPane().add(cbSituation);
		
		model = new InvoiceCheckTableModel();
		JTable tabela = new JTable(model);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		//scrollPane.setBounds(10, 173, 427, 155);
		getContentPane().add(scrollPane);
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					conn.setAutoCommit(false);
					System.out.println("Conectado com sucesso!");

					InvoiceDAO dao = new InvoiceDAO(conn);
					Invoice invoice = new Invoice();
					List<Invoice> invoicesList = new ArrayList<Invoice>();
					invoicesList = (List<Invoice>)(List<?>) new InvoiceDAO(conn).SelectAll();
					model.addListaDeInvoices(invoicesList);
				
				  } catch (SQLException e1) {
					 e1.printStackTrace();
			}
			}
		});
		button.setIcon(new ImageIcon(InvoiceCheckFrm.class.getResource("/view/images/localizar.png")));
		button.setPreferredSize(new Dimension(40, 25));
		button.setBackground(SystemColor.menu);
		button.setBounds(580, 6, 137, 31);
		getContentPane().add(button);
		
		
		

	}
}
