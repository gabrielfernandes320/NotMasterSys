package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class GenerateInvoicesFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateInvoicesFrm frame = new GenerateInvoicesFrm();
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
	public GenerateInvoicesFrm() {
		setTitle("Gerar Faturas");
		setClosable(true);
		setBounds(100, 100, 390, 152);
		getContentPane().setLayout(null);
		
		JButton btnGenerateInvoices = new JButton("Gerar Faturas");
		btnGenerateInvoices.setBackground(SystemColor.menu);
		btnGenerateInvoices.setBounds(90, 76, 191, 36);
		getContentPane().add(btnGenerateInvoices);
		
		
		btnGenerateInvoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) /2);
		}
}
