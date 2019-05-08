package view;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;
import com.sun.xml.internal.ws.api.Component;
import database.ConnectionFactory;
import database.InvoiceDAO;
import database.MatriculaDAO;
import model.Invoice;
import model.Matricula;
import table.model.InvoicesCheckTableModel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.color.*;
import java.lang.String;

public class InvoiceCheckFrm extends JInternalFrame {
	private JFormattedTextField tbInitialDate;
	private JFormattedTextField tbFinalDate;
	private InvoicesCheckTableModel model;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

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
		setTitle("Consulta de Faturas");
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

		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setFont(new Font("Arial", Font.BOLD, 12));
		lblSituao.setBounds(326, 13, 66, 14);
		getContentPane().add(lblSituao);

		JComboBox cbSituation = new JComboBox();
		cbSituation.setModel(new DefaultComboBoxModel(new String[] { "Todas", "Em Aberto", "Pagas", "Canceladas" }));
		cbSituation.setBounds(402, 11, 115, 20);
		getContentPane().add(cbSituation);

		model = new InvoicesCheckTableModel();
		JTable tabela = new JTable(model);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(getNewRenderedTable(tabela));
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

					LocalDate data = LocalDate.parse("2000-01-01");
					Date dataInicial = Date.valueOf(data);
					data = LocalDate.parse("2999-01-01");
					Date dataFinal = Date.valueOf(data);

					if (tbInitialDate.getText().replace(" ", "").length() >= 8) {

						dataInicial = new Date(df.parse(tbInitialDate.getText()).getTime());

					}
					if (tbFinalDate.getText().replace(" ", "").length() >= 8) {

						dataFinal = new Date(df.parse(tbFinalDate.getText()).getTime());

					}

					InvoiceDAO dao = new InvoiceDAO(conn);
					Invoice invoice = new Invoice();
					invoice.setInitial_date(dataInicial);
					invoice.setFinal_date(dataFinal);

					List<Invoice> invoicesList = new ArrayList<Invoice>();
					switch (cbSituation.getSelectedIndex()) {
					case 0:
						invoicesList = (List<Invoice>) (List<?>) new InvoiceDAO(conn).SelectAll(invoice);
						break;
					case 1:
						invoicesList = (List<Invoice>) (List<?>) new InvoiceDAO(conn).SelectPendigInvoices(invoice);
						break;
					case 2:
						invoicesList = (List<Invoice>) (List<?>) new InvoiceDAO(conn).SelectPayedInvoices(invoice);
						break;
					case 3:
						invoicesList = (List<Invoice>) (List<?>) new InvoiceDAO(conn).SelectCanceledInvoices(invoice);
						break;
					default:
						break;
					}

					model.addListaDeInvoice(invoicesList);
					tabela.repaint();

				} catch (SQLException | ParseException e1) {
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

	private JTable getNewRenderedTable(final JTable tabela) {

		tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String payed = String.valueOf(table.getValueAt(row, 4));
				String canceled = String.valueOf(table.getValueAt(row, 5));
				Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
				Matricula matri = new Matricula();
				try {
					MatriculaDAO md = new MatriculaDAO(conn);
					
					matri.setCodigo_aluno(Integer.parseInt(String.valueOf(table.getValueAt(row, 0))));
					matri = md.Select(matri);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				if (payed == "null" && canceled == "null") {
					setBackground(Color.WHITE);
					setForeground(Color.BLACK);
					table.setValueAt("asdsad", row , 4);
					
				} else if(payed != "null"){
					setBackground(Color.GREEN);
					setForeground(Color.BLACK);
				
				} else if(String.valueOf(matri.getCodigo_matricula()) == null){
					setBackground(Color.RED);
					setForeground(Color.BLACK);
						
				} else {
					setBackground(Color.YELLOW);
					setForeground(Color.BLACK);
				}

				return this;
			}
		});

		return tabela;

	};

}
