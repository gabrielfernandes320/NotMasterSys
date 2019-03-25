package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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

	JLabel lbUsuarioHora;

	public PrincipalWindow() {
		// Define o tamanho da janela.
		setSize(800, 600);
		setMinimumSize(new Dimension(800, 600));
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// Define o titulo da janela.
		setTitle("Menu Principal");

		// Seta o layout a ser utilizado (NULL significa que não irá utilizar nenhum).
		setLayout(null);

		// Define que não poderá ser alterado as dimensões da tela.
		setResizable(true);

		// Define o método de fechamento da janela.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.black);

		setLocationRelativeTo(null);

		criarComponentes();
	}

	private void criarComponentes() {

//		lbUsuarioHora = new JLabel("asd", JLabel.RIGHT);
//		lbUsuarioHora.addHierarchyBoundsListener(new HierarchyBoundsListener() {
//
//			@Override
//			public void ancestorResized(HierarchyEvent e) {
//				Dimension d = getSize();
//				lbUsuarioHora.setBounds(((int) d.getWidth()) - 370, ((int) d.getHeight()) - 85, 350, 20);
//			}
//
//			@Override
//			public void ancestorMoved(HierarchyEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});

		// Configuração do DesktopPane
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
		menuRelatorios = new JMenu("Relatórios");
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

		//Ações para os item abaixo
		//Sistema
		itemUsuariosSist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UsersFrm us = new UsersFrm();
				us.setLocation(1, 1);
				desktopPane.add(us);
				us.setVisible(true);
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
		
		//Cadastro
//		
		itemModalidadesCad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ModalityFrm us = new ModalityFrm();
				us.setLocation(2, 2);
				desktopPane.add(us);
				us.setVisible(true);
			}
				});	
		itemPlanosCad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PlanosFrm us = new PlanosFrm();
				us.setLocation(1, 1);
				desktopPane.add(us);
				us.setVisible(true);
			}
		});

		//Processos
		
		
		

		setJMenuBar(menuBar);
		add(desktopPane);

	}

	public static void main(String[] args) {

		new PrincipalWindow().setVisible(true);

	}
}
