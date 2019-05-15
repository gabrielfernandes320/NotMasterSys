package view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;
import com.sun.prism.paint.Color;
import database.ConnectionFactory;
import database.InvoiceDAO;
import model.Invoice;
import table.model.InvoicesPaymentTableModel;
import java.awt.Font;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;

public class InvoicePaymentFrm extends JInternalFrame {
	private JFormattedTextField tbInitialDate;
	private JFormattedTextField tbFinalDate;
	private InvoicesPaymentTableModel model;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");

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

		model = new InvoicesPaymentTableModel();
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

		JMenuItem payInvoice = new JMenuItem("Pagar Fatura");
		JMenuItem cancelInvoice = new JMenuItem("Cancelar Fatura");
		JMenuItem changeInvoice = new JMenuItem("Desconto/Acrescimo");

		tabela.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("pressed");

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				int r = tabela.rowAtPoint(e.getPoint());
				if (r >= 0 && r < tabela.getRowCount()) {
					tabela.setRowSelectionInterval(r, r);
				} else {
					tabela.clearSelection();
				}

				int rowindex = tabela.getSelectedRow();
				if (rowindex < 0)
					return;
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
					JPopupMenu RightClickMenu = new JPopupMenu();
					RightClickMenu.add(payInvoice);
					RightClickMenu.add(cancelInvoice);
					RightClickMenu.add(changeInvoice);
					RightClickMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		// FUNÇOES IMPORTANTES E ETC

		payInvoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(getContentPane(), "Confirmar pagamento?", "Aviso:",
						JOptionPane.YES_NO_OPTION) == 0) {

					Invoice inv = new Invoice();

					inv.setCodigo_matricula(
							Integer.parseInt(String.valueOf((tabela.getValueAt(tabela.getSelectedRow(), 0)))));
					inv.setValor(Double.parseDouble((String) tabela.getValueAt(tabela.getSelectedRow(), 3)));
					inv.setData_vencimento((java.sql.Date) tabela.getValueAt(tabela.getSelectedRow(), 2));

					try {

						InvoiceDAO invDAO = new InvoiceDAO(conn);

						if (invDAO.PayInvoice(inv) == 1) {

							JOptionPane.showMessageDialog(getContentPane(), "Pagamento de fatura realizado.",
									"Sucesso!", JOptionPane.INFORMATION_MESSAGE);

							UpdateTableModel();
							tabela.repaint();
							
							
						} else {

							JOptionPane.showMessageDialog(getContentPane(), "Problema no pagamento!", "Erro!",
									JOptionPane.INFORMATION_MESSAGE);

							UpdateTableModel();
							tabela.repaint();
							
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				else {

				}

			}
		});

		cancelInvoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(getContentPane(), "Confirmar cancelamento?", "Aviso:",
						JOptionPane.YES_NO_OPTION) == 0) {

					Invoice inv = new Invoice();

					inv.setCodigo_matricula(
							Integer.parseInt(String.valueOf((tabela.getValueAt(tabela.getSelectedRow(), 0)))));
					inv.setValor(Double.parseDouble((String) tabela.getValueAt(tabela.getSelectedRow(), 3)));
					inv.setData_vencimento((java.sql.Date) tabela.getValueAt(tabela.getSelectedRow(), 2));

					try {

						InvoiceDAO invDAO = new InvoiceDAO(conn);

						if (invDAO.CancelInvoice(inv) == 1) {

							JOptionPane.showMessageDialog(getContentPane(), "Cancelamento de fatura realizado.",
									"Sucesso!", JOptionPane.INFORMATION_MESSAGE);
							
							UpdateTableModel();
							tabela.repaint();
							

						} else {

							JOptionPane.showMessageDialog(getContentPane(), "Problema no cancelamento!", "Erro!",
									JOptionPane.INFORMATION_MESSAGE);

							UpdateTableModel();
							tabela.repaint();
							
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}

			}
		});

		changeInvoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Invoice inv = new Invoice();

				Double newvalue = Double.valueOf(
						JOptionPane.showInputDialog(tabela, "Novo valor:", "", JOptionPane.INFORMATION_MESSAGE));

				if (JOptionPane.showConfirmDialog(getContentPane(), "Alterar para o valor " + newvalue + "?",
						"Confirmar Alteração", JOptionPane.YES_NO_OPTION) == 0) {

					inv.setCodigo_matricula(
							Integer.parseInt(String.valueOf((tabela.getValueAt(tabela.getSelectedRow(), 0)))));
					inv.setValor(Double.parseDouble((String) tabela.getValueAt(tabela.getSelectedRow(), 3)));

					try {

						InvoiceDAO invDAO = new InvoiceDAO(conn);

						if (invDAO.ChangeInvoicePrice(inv, newvalue) == 1) {

							JOptionPane.showMessageDialog(getContentPane(), "Alteração de fatura realizada.",
									"Sucesso!", JOptionPane.INFORMATION_MESSAGE);
							UpdateTableModel();
							tabela.repaint();


						} else {

							JOptionPane.showMessageDialog(getContentPane(), "Problema na alteração!", "Erro!",
									JOptionPane.INFORMATION_MESSAGE);
							UpdateTableModel();
							tabela.repaint();
							
							
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}

			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UpdateTableModel();
				tabela.repaint();
				
			}
		});

	}

	private void checkRows(InvoicesPaymentTableModel table) {
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 3) != null) {
				table.setRowColour(1, Color.RED);

			}
		}

	}
	
	private List<Invoice> UpdateTableModel(){
		
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
		
		return null;
		
	}
	
}

