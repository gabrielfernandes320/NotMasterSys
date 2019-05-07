package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import com.sun.prism.paint.Color;

import database.AlunoDAO;
import database.ConnectionFactory;
import database.InvoiceDAO;
import model.Aluno;
import model.Invoice;
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

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class InvoicePaymentFrm extends JInternalFrame {
	private JFormattedTextField tbInitialDate;
	private JFormattedTextField tbFinalDate;
	private InvoicesCheckTableModel model;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public InvoicePaymentFrm() {
		setTitle("Pagamento de Faturas");
		setClosable(true);
		setBounds(100, 100, 757, 390);
		getContentPane().setLayout(null);

		JLabel lblDe = new JLabel("De:");
		lblDe.setFont(new Font("Arial", Font.BOLD, 12));
		lblDe.setBounds(27, 13, 28, 14);
		getContentPane().add(lblDe);

		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		MaskFormatter maskData2 = null;
		try {
			maskData2 = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		tbInitialDate = new JFormattedTextField(df);
		tbInitialDate.setBounds(51, 11, 102, 20);
		getContentPane().add(tbInitialDate);
		maskData.install(tbInitialDate);

		JLabel lblAt = new JLabel("At\u00E9:");
		lblAt.setFont(new Font("Arial", Font.BOLD, 12));
		lblAt.setBounds(165, 13, 28, 14);
		getContentPane().add(lblAt);

		tbFinalDate = new JFormattedTextField(df);
		tbFinalDate.setBounds(197, 11, 115, 20);
		getContentPane().add(tbFinalDate);
		maskData2.install(tbFinalDate);

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

		button.setIcon(new ImageIcon(InvoicePaymentFrm.class.getResource("/view/images/localizar.png")));
		button.setPreferredSize(new Dimension(40, 25));
		button.setBackground(SystemColor.menu);
		button.setBounds(580, 6, 137, 31);
		getContentPane().add(button);

		JPopupMenu RightClickMenu = new JPopupMenu();
		JMenuItem payInvoice = new JMenuItem("Pagar Fatura");
		JMenuItem cancelInvoice = new JMenuItem("Cancelar Fatura");
		JMenuItem changeInvoice = new JMenuItem("Desconto/Acrescimo");

		RightClickMenu.add(payInvoice);
		RightClickMenu.add(cancelInvoice);
		RightClickMenu.add(changeInvoice);

		tabela.setComponentPopupMenu(RightClickMenu);

		// FUNÇOES IMPORTANTES E ETC

		payInvoice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Right-click performed on table and choose DELETE");
            }
        });
		
		cancelInvoice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Right-click performed on table and choose DELETE");
            }
        });
		
		changeInvoice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Right-click performed on table and choose DELETE");
            }
        });
				
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conn.setAutoCommit(false);
					System.out.println("Conectado com sucesso!");

					Date dataInicial = new Date(df.parse(tbInitialDate.getText()).getTime());
					Date dataFinal = new Date(df.parse(tbFinalDate.getText()).getTime());
					
					InvoiceDAO dao = new InvoiceDAO(conn);
					Invoice invoice = new Invoice();
					invoice.setInitial_date(dataInicial);
					invoice.setFinal_date(dataFinal);
					List<Invoice> invoicesList = new ArrayList<Invoice>();
					invoicesList = (List<Invoice>) (List<?>) new InvoiceDAO(conn).SelectPendigInvoices(invoice);
					model.addListaDeInvoice(invoicesList);

					for (int i = 0; i < model.getRowCount(); i++) {
						if (model.getValueAt(i, 3) != null) {
							model.setRowColour(1, Color.RED);

						}
					}

				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	private void checkRows(InvoicesCheckTableModel table) {
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 3) != null) {
				table.setRowColour(1, Color.RED);

			}
		}

	}
}
