package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Usuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipalWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane;
	JMenuBar menuBar;
	JMenu menuSistema;
	JMenu menuCadastros;
	JMenu menuProcessos;
	JMenu menuRelatorios;
	JMenu menuUtilitarios;
	JMenu menuAjuda;
	JMenuItem itemUsuariosSist;
	JMenuItem itemSairSist;
	JMenuItem itemAlunosCad;
	JMenuItem itemModalidadesCad;
	JMenuItem itemPlanosCad;
	JMenu itemMatricularProc;
	JMenuItem itemAlunoMatricularProc;
	JMenu itemFaturamentoProc;
	JMenuItem itemGFFaturamentoProc;
	JMenuItem itemCFFaturamentoProc;
	JMenuItem itemGPFaturamentoProc;
	UsersFrm us;
	StudentFrm studentFrm;
	ModalityFrm mod;
	InvoiceCheckFrm icf;
	GenerateInvoicesFrm gif;
	JLabel lbUsuarioHora;

	public PrincipalWindow(Usuario user) {
		// Define o tamanho da janela.
		setSize(800, 600);
		setMinimumSize(new Dimension(800, 600));
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		// Define o titulo da janela.
		setTitle("Menu Principal");

		// Seta o layout a ser utilizado (NULL significa que nï¿½o irï¿½ utilizar nenhum).
		getContentPane().setLayout(null);

		// Define que nï¿½o poderï¿½ ser alterado as dimensï¿½es da tela.
		setResizable(true);

		// Define o mï¿½todo de fechamento da janela.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.black);

		setLocationRelativeTo(null);

		criarComponentes();
		AcessController(user);
	}

	private void criarComponentes() {

//		lbUsuarioHora = new JLabel("asd", JLabel.RIGHT);
//		lbUsuarioHora.addHierarchyBoundsListener(new HierarchyBoundsListener() {
//
//			@Override
//			public void ancestorResized(HierarchyEvent e) {
//				Dimension d = getSize();
//				lbUsuarioHora.setBounds(((i/nt) d.getWidth()) - 370, ((int) d.getHeight()) - 85, 350, 20);
//			}
//
//			@Override
//			public void ancestorMoved(HierarchyEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});

		// Configuraï¿½ï¿½o do DesktopPane
		desktopPane = new JDesktopPane();
		// desktopPane.setSize(800,600);
		desktopPane.addHierarchyBoundsListener(new HierarchyBoundsListener() {

			@Override
			public void ancestorResized(HierarchyEvent arg0) {
				Dimension d = getSize();
				d.setSize(d.getWidth() - 20, d.getHeight() - 60);
				desktopPane.setSize(d);
			}

			@Override
			public void ancestorMoved(HierarchyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Comando da Barra de Menu
		menuBar = new JMenuBar();

		menuSistema = new JMenu("Sistema");
		menuCadastros = new JMenu("Cadastros");
		menuProcessos = new JMenu("Processos");
		menuRelatorios = new JMenu("Relatï¿½rios");
		menuUtilitarios = new JMenu("Utilitarios");
		menuAjuda = new JMenu("Ajuda");

		itemUsuariosSist = new JMenuItem("Usuarios");
		itemSairSist = new JMenuItem("Sair");
		itemAlunosCad = new JMenuItem("Alunos");
		itemModalidadesCad = new JMenuItem("Modalidades");
		itemPlanosCad = new JMenuItem("Planos");
		itemMatricularProc = new JMenu("Matricular");
		itemAlunoMatricularProc = new JMenuItem("Aluno");
		itemFaturamentoProc = new JMenu("Faturamento");
		itemGFFaturamentoProc = new JMenuItem("Gerar Faturamento");
		itemCFFaturamentoProc = new JMenuItem("Consultar Faturas");
		itemGPFaturamentoProc = new JMenuItem("Gerar Pagamentos");
		
		//icones dos itens

		itemUsuariosSist.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\16x16\\aplicacao.png"));
		itemSairSist.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\16x16\\sair.png"));
		itemAlunosCad.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\16x16\\aplicacao.png"));
		itemModalidadesCad.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\16x16\\aplicacao.png"));
		itemPlanosCad.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\16x16\\aplicacao.png"));
		itemMatricularProc.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\16x16\\aplicacao.png"));
		itemFaturamentoProc.setIcon(new ImageIcon("C:\\Users\\eduar\\Desktop\\NotMasterSys\\MasterImage\\src\\16x16\\aplicacao.png"));


		menuSistema.add(itemUsuariosSist);
		menuSistema.add(itemSairSist);
		menuCadastros.add(itemAlunosCad);
		menuCadastros.add(itemModalidadesCad);
		menuCadastros.add(itemPlanosCad);
		menuProcessos.add(itemMatricularProc);
		menuProcessos.add(itemFaturamentoProc);

		itemMatricularProc.add(itemAlunoMatricularProc);
		itemFaturamentoProc.add(itemGFFaturamentoProc);
		itemFaturamentoProc.add(itemCFFaturamentoProc);
		itemFaturamentoProc.add(itemGPFaturamentoProc);

		menuBar.add(menuSistema);
		menuBar.add(menuCadastros);
		menuBar.add(menuProcessos);
		menuBar.add(menuUtilitarios);
		menuBar.add(menuAjuda);
		

		//Aï¿½ï¿½es para os item abaixo
		//Sistema
		itemUsuariosSist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JanelaVerificar(UsersFrm.class.getName())) {
					JanelaFocar(us);
				}
				else {
					us = new UsersFrm();
					us.setName(UsersFrm.class.getName());
					us.setLocation(1, 1);
					desktopPane.add(us);
					us.setVisible(true);
				}
				
				
			}

			private void JanelaFocar(UsersFrm us) {
				try
				{
					us.setSelected(true);
				}
				catch	(PropertyVetoException	ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		itemSairSist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Saindo...", dialogButton);
				if(dialogResult == 0) {
				  System.exit(0);
				} 
			}
		});
		
		itemCFFaturamentoProc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JanelaVerificar(UsersFrm.class.getName())) {
					
				}
				else {
					icf = new InvoiceCheckFrm();
					icf.setName(UsersFrm.class.getName());
					icf.setLocation(1, 1);
					desktopPane.add(icf);
					icf.setVisible(true);
				}
			}
		});
		
		itemGFFaturamentoProc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JanelaVerificar(GenerateInvoicesFrm.class.getName())) {
					
				}
				else {
					gif = new GenerateInvoicesFrm();
					gif.setName(GenerateInvoicesFrm.class.getName());
					desktopPane.add(gif);
					gif.setPosicao();
					gif.setVisible(true);
				}
			}
		});

		
		//Cadastro
//		
		
		
		
		itemModalidadesCad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (JanelaVerificar(ModalityFrm.class.getName())) {
					
				}
				else {
					mod = new ModalityFrm();
					desktopPane.add(mod);
					mod.setVisible(true);
					mod.setPosicao();
					mod.setName(ModalityFrm.class.getName());
					
				}
				
				
				
			}
				});	
		itemPlanosCad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PlansFrm us = new PlansFrm();
				us.setLocation(1, 1);
				desktopPane.add(us);
				us.setVisible(true);
				us.setPosicao();
				
			}
		});
		itemAlunosCad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				if (JanelaVerificar(StudentFrm.class.getName())) {
					
				}
				else {
					
					try {
						studentFrm = new StudentFrm();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					desktopPane.add(studentFrm);
					studentFrm.setVisible(true);
					studentFrm.setName(StudentFrm.class.getName());
					
				}
				
				
				
			}			
		});
		
		//MatrÃ­cula
		itemAlunoMatricularProc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MatriculaFrm us = new MatriculaFrm();
				us.setLocation(1, 1);
				desktopPane.add(us);
				us.setVisible(true);
			}
		});
		
		setJMenuBar(menuBar);
		getContentPane().add(desktopPane);

	}
	
	private boolean JanelaVerificar(String ls_nome)
	{
		//
		// Pega todas as janelas existentes no Desktop Pane
		//
		JInternalFrame[] la_janelas = desktopPane.getAllFrames();
		
		//
		// Varre o array das janelas
		//
		for	(JInternalFrame lo_frame : la_janelas) {
			
			//
			// Se encontrou a janela.
			//
			if	(lo_frame.getName().equalsIgnoreCase(ls_nome)) {
				return true;
			}
		}
		
		//
		// Se não encontrou a janela
		//
		return false;
	}
	
	private void AcessController(Usuario user){
		switch (user.getPerfil()) {
		case "Cadastral":
			menuBar.getMenu(0).setEnabled(false);
			menuBar.getMenu(2).setEnabled(false);
			menuBar.getMenu(3).setEnabled(false);
			break;
			
		case "Matricular":
			menuBar.getMenu(0).setEnabled(false);
			menuBar.getMenu(1).setEnabled(false);
			menuBar.getMenu(3).setEnabled(false);	
			menuBar.getMenu(2).getMenuComponent(1).setEnabled(false);
			break;
			
		case "Completo":
			menuBar.getMenu(0).setEnabled(true);
			menuBar.getMenu(1).setEnabled(true);
			menuBar.getMenu(2).setEnabled(true);
			menuBar.getMenu(3).setEnabled(true);
			menuBar.getMenu(4).setEnabled(true);
			break;
			
		case "Financeiro":
			menuBar.getMenu(0).setEnabled(false);
			menuBar.getMenu(1).setEnabled(false);
			menuBar.getMenu(2).getMenuComponent(0).setEnabled(false);
			menuBar.getMenu(3).setEnabled(false);
			break;

		default:
			break;
		}
	}
	

}
