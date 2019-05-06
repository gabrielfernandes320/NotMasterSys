package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import lib.MasterMonthChooser;
import model.Matricula;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.xml.crypto.Data;

import database.ConnectionFactory;
import database.MatriculaDAO;

public class GenerateInvoicesFrm extends JInternalFrame {
	Connection conn =  ConnectionFactory.getConnection("master", "admin", "admin");
	MasterMonthChooser invoiceMonth;
	
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
		setBounds(100, 100, 343, 122);
		getContentPane().setLayout(null);
		
		JButton btnGenerateInvoices = new JButton("Gerar Faturas");
		btnGenerateInvoices.setBackground(SystemColor.menu);
		btnGenerateInvoices.setBounds(127, 46, 184, 31);
		getContentPane().add(btnGenerateInvoices);
		
		invoiceMonth = new MasterMonthChooser();
		invoiceMonth.setBounds(127, 11, 184, 24);
		getContentPane().add(invoiceMonth);
		
		JLabel lblNewLabel = new JLabel("Data da Fatura:");
		lblNewLabel.setBounds(21, 11, 96, 24);
		getContentPane().add(lblNewLabel);
		
		btnGenerateInvoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Matricula model = new Matricula();
					MatriculaDAO enrollmentDAO = new MatriculaDAO(conn);
					List<Object> allEnrollment = new ArrayList<Object>();
					allEnrollment = enrollmentDAO.SelectAll();
					
					for(int i=0; i < allEnrollment.size(); i++) {
						model = (Matricula)allEnrollment.get(i);
						
					
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  
				
				
				
				
				
				
			}
		});
	}
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) /2);
		}
}
