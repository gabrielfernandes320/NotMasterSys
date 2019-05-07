package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.prism.paint.Color;

import database.ConnectionFactory;
import database.InvoiceDAO;
import database.UsuarioDAO;
import model.Invoice;
import model.Usuario;
import table.model.InvoicesCheckTableModel;
import table.model.InvoicesCheckTableModel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class InvoiceCheckFrm extends JInternalFrame {
	private JFormattedTextField tbInitialDate;
	private JFormattedTextField tbFinalDate;
	private InvoicesCheckTableModel model;

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
		
		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("##-##-####");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		MaskFormatter maskData2 = null;
		try {
			maskData2 = new MaskFormatter("##-##-####");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		tbInitialDate = new JFormattedTextField();
		tbInitialDate.setBounds(51, 11, 102, 20);
		getContentPane().add(tbInitialDate);
		maskData2.install(tbInitialDate);
		getContentPane().add(tbInitialDate);
		tbInitialDate.setColumns(10);
		
		JLabel lblAt = new JLabel("At\u00E9:");
		lblAt.setFont(new Font("Arial", Font.BOLD, 12));
		lblAt.setBounds(165, 13, 28, 14);
		getContentPane().add(lblAt);	
		
		tbFinalDate = new JFormattedTextField();
		tbFinalDate.setBounds(197, 11, 115, 20);
		getContentPane().add(tbFinalDate);
		maskData.install(tbFinalDate);
		getContentPane().add(tbFinalDate);

		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setFont(new Font("Arial", Font.BOLD, 12));
		lblSituao.setBounds(326, 13, 66, 14);
		getContentPane().add(lblSituao);
		
		JComboBox cbSituation = new JComboBox();
		cbSituation.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Em Aberto", "Pagas", "Canceladas"}));
		cbSituation.setBounds(402, 11, 115, 20);
		getContentPane().add(cbSituation);
		
		model = new InvoicesCheckTableModel();
		JTable tabela = new JTable(model);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setSize(0, 0);
		scrollPane.setLocation(0, 0);
		scrollPane.setBounds(10, 42, 721, 307);
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
					invoice.setInitial_date(tbInitialDate.getText());
					invoice.setFinal_date(tbFinalDate.getText());
					
					List<Invoice> invoicesList = new ArrayList<Invoice>();
					switch (cbSituation.getSelectedIndex()) {
					case 0:
						invoicesList = (List<Invoice>)(List<?>) new InvoiceDAO(conn).SelectAll(invoice);
						
						break;
					case 1:
						invoicesList = (List<Invoice>)(List<?>) new InvoiceDAO(conn).SelectPendigInvoices(invoice);
						break;
					case 2:
						invoicesList = (List<Invoice>)(List<?>) new InvoiceDAO(conn).SelectPayedInvoices(invoice);
						break;
					case 3:
						invoicesList = (List<Invoice>)(List<?>) new InvoiceDAO(conn).SelectCanceledInvoices(invoice);
						break;
					default:
						break;
					}
					
					model.addListaDeInvoice(invoicesList);
				
					
					
					for (int i = 0; i < model.getRowCount(); i++) {
						if(model.getValueAt(i, 3) != null) {
							model.setRowColour(1, Color.RED);
							
						}
					}
				
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
	
	
	private void checkRows(InvoicesCheckTableModel table) {
		for (int i = 0; i < table.getRowCount(); i++) {
			if(table.getValueAt(i, 3) != null) {
				table.setRowColour(1, Color.RED);
				
			}
		}
		
	}
}
