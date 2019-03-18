package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class PlanosFrm extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanosFrm frame = new PlanosFrm();
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
	public PlanosFrm() {
		setBounds(100, 100, 451, 210);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(10, 11, 96, 31);
		btnNewButton.setPreferredSize(new Dimension(40, 25));
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\16x16\\localizar.png"));
		getContentPane().add(btnNewButton);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\22x22\\adicionar.png"));
		btnAdicionar.setPreferredSize(new Dimension(40, 25));
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(95, 11, 114, 31);
		getContentPane().add(btnAdicionar);
		
		JButton button = new JButton("Adicionar");
		button.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\22x22\\salvar.png"));
		button.setPreferredSize(new Dimension(40, 25));
		button.setBackground(SystemColor.menu);
		button.setBounds(301, 11, 114, 31);
		getContentPane().add(button);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\22x22\\remover.png"));
		btnRemover.setPreferredSize(new Dimension(40, 25));
		btnRemover.setBackground(SystemColor.menu);
		btnRemover.setBounds(198, 11, 114, 31);
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
		
		JComboBox comboBox = new JComboBox(new String[] {"Boxe", "Capoeira", "Karate", "Taekwondo"});
		comboBox.setBounds(113, 78, 279, 20);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(113, 108, 279, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(113, 138, 140, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

	}
}
