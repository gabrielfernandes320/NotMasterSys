package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class InvoiceCheckFrm extends JInternalFrame {
	private JTextField tbInitialDate;
	private JTextField tbFinalDate;

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
		
		JButton button = new JButton("Buscar");
		button.setIcon(new ImageIcon(InvoiceCheckFrm.class.getResource("/view/images/localizar.png")));
		button.setPreferredSize(new Dimension(40, 25));
		button.setBackground(SystemColor.menu);
		button.setBounds(580, 6, 137, 31);
		getContentPane().add(button);
		
		
		

	}
}
