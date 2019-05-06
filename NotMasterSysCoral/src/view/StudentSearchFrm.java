package view;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import database.AlunoDAO;
import database.ConnectionFactory;
import model.Aluno;
import table.model.StudentsTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class StudentSearchFrm extends JDialog {
	private StudentsTableModel model;

	Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
	private JTextField txfSearch;


	public StudentSearchFrm(final StudentFrm window) {
				
		setBounds(100, 100, 471, 330);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 248, 434, 33);
			getContentPane().add(buttonPane);
			{
				buttonPane.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Duplo clique na linha para selecionar o aluno desejado");
				lblNewLabel.setBounds(0, 9, 434, 14);
				buttonPane.add(lblNewLabel);
			}
		}
		
		txfSearch = new JTextField();
		txfSearch.setBounds(89, 11, 220, 20);
		getContentPane().add(txfSearch);
		txfSearch.setColumns(10);
		
		JLabel lblStudent = new JLabel("Aluno:");
		lblStudent.setBounds(10, 13, 75, 17);
		getContentPane().add(lblStudent);
		
		JButton btnSearch = new JButton("Pesquisar");
		btnSearch.setBackground(SystemColor.menu);
				btnSearch.setBounds(312, 10, 89, 23);
		getContentPane().add(btnSearch);
		txfSearch.setText("");
		JPanel painelFundo;

		painelFundo = new JPanel();
		model = new StudentsTableModel();
		painelFundo.setLayout(null);
		painelFundo.setBounds(10, 42, 434, 195);
		getContentPane().add(painelFundo);
		
				JTable tabela = new JTable(model);
				tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				JScrollPane barraRolagem = new JScrollPane(tabela);
				barraRolagem.setBounds(0, 0, 434, 195);
				painelFundo.add(barraRolagem);
		
		tabela.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		         if (e.getClickCount() == 2){
		        	 
		            System.out.println(" double click" );
		            
		            Aluno p = new Aluno();		          
		            p.setCodigo_aluno(Integer.parseInt((String) tabela.getValueAt(tabela.getSelectedRow(), 0)));
		            try {
						AlunoDAO AlunoDAO = new AlunoDAO(conn);
						p = AlunoDAO.Select(p);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
		            try {
						window.updateFields(p);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		           	dispose();
		           	
		            }
		         }
		        } );
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zerarTodos();
				try {
					
					model.addListaDealunos(new AlunoDAO(conn).SelectAll(txfSearch.getText().toString()));
				
				} catch (Exception e1) {
					System.err.printf("Erro: %s.\n", e1.getMessage());
				}

				
				
				
			}
		});

	}
	public void zerarTodos() {
		model.limpar();
	}

}
