package br.rural.graficos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import br.rural.montador.Montador;

public class Janela extends JFrame {

	private JTextArea area = new JTextArea();
	private Montador montador;
	private Menu  menu = new Menu(area);
	private Help  help = new Help(area);
	private RegistradoresTab tabelaregs = new RegistradoresTab();
	private MemoriaTab tabMemoria = new MemoriaTab();
	
	private AbasRegs abasreg = new AbasRegs(tabelaregs);
	private JMenuBar barra = new JMenuBar();
	private  JTabbedPane abas = new JTabbedPane();
	private MenuBotoes barrabotoes = new MenuBotoes(area,tabelaregs,tabMemoria,abas);
	private URL icon = getClass().getClassLoader().getResource("br/rural/graficos/imagens/cafe.png");


	public Janela() {
		
        setIconImage(new ImageIcon(icon).getImage());
		inicializar();		
	}

	public void inicializar()  {

		JPanel painel = new JPanel();
		painel.setLayout(new BorderLayout());

		setTitle("WhiskeyInTheJar Mips");

		painel.add(barrabotoes,BorderLayout.NORTH);
		barra.add(menu);
		barra.add(help);
		setJMenuBar(barra);
		add(painel);

		area.setPreferredSize(new Dimension(700, 2070));

		ScrollPane scroll = new ScrollPane();
		scroll.setPreferredSize(new Dimension(800, 500));
		scroll.add(area);
		
		abas.add("Codigo",scroll);
		abas.add("Execucao",Instrucoes.getInstance());
		abas.add("Memoria",tabMemoria);
		abas.setEnabledAt(1, false);
		abas.setEnabledAt(2, false);
		

		painel.add(abas,BorderLayout.WEST);
		painel.add(abasreg,BorderLayout.EAST);

		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.LEADING));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1050, 705);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}