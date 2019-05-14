package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import lib.MasterMonthChooser;
import model.Invoice;
import model.Matricula;
import model.Matricula_Modalidade;
import model.Plano;

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
import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

import database.ConnectionFactory;
import database.InvoiceDAO;
import database.MatriculaDAO;
import database.Matricula_ModalidadeDAO;
import database.PlanosDAO;

public class GenerateInvoicesFrm extends JInternalFrame {
	Connection conn =  ConnectionFactory.getConnection("master", "admin", "admin");
	MasterMonthChooser invoiceMonth = new MasterMonthChooser();
	
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

	public GenerateInvoicesFrm() {
		setTitle("Gerar Faturas");
		setClosable(true);
		setBounds(100, 100, 343, 122);
		getContentPane().setLayout(null);
		
		JButton btnGenerateInvoices = new JButton("Gerar Faturas");
		btnGenerateInvoices.setBackground(SystemColor.menu);
		btnGenerateInvoices.setBounds(127, 46, 184, 31);
		getContentPane().add(btnGenerateInvoices);
		

		invoiceMonth.setBounds(127, 11, 184, 24);
		getContentPane().add(invoiceMonth);
		
		JLabel lblNewLabel = new JLabel("Data da Fatura:");
		lblNewLabel.setBounds(21, 11, 96, 24);
		getContentPane().add(lblNewLabel);
		
		btnGenerateInvoices.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
						conn.setAutoCommit(false);
						MatriculaDAO enrollmentDAO = new MatriculaDAO(conn);
						Matricula_ModalidadeDAO enrollmentModalityDAO = new Matricula_ModalidadeDAO(conn);
						InvoiceDAO invDAO = new InvoiceDAO(conn);
						PlanosDAO plansDAO =  new PlanosDAO(conn);
						
						Matricula model = new Matricula();
						
						List<Object> allEnrollment = new ArrayList<Object>();
						allEnrollment = enrollmentDAO.SelectAll();
						
							for(int i=0; i < allEnrollment.size(); i++) {
								double value = 0;
								model = (Matricula)allEnrollment.get(i);
								
								if((model.getData_encerramento()) == null) {
									
									List<Matricula_Modalidade> modalityList = new ArrayList<Matricula_Modalidade>();							
									modalityList = enrollmentModalityDAO.Select(model.getCodigo_matricula());
									
										for(int j = 0; j < modalityList.size(); j++) {
											Plano plan = new Plano();
											plan = (Plano) plansDAO.SelecT(modalityList.get(j));
											value += plan.getValor();
										}
									
									java.util.Date selectedDate = invoiceMonth.getDate();
									Date dueDate = new Date(selectedDate.getYear(),  selectedDate.getMonth(), model.getDia_vencimento());
										
									Invoice invoice = new Invoice();
									invoice.setCodigo_matricula(model.getCodigo_matricula());
									invoice.setData_vencimento(dueDate);
									invoice.setValor(value);
									invoice.setData_pagamento(null);
									invoice.setData_cancelamento(null);
									invDAO.Insert(invoice);
								}
							} 
					
						JOptionPane.showMessageDialog(null,"Faturas geradas com sucesso!");
					
				}catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,"Faturas ja existentes para esta data" );
					
					}  
				
			}
		});
	}
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) /2);
		}
}
