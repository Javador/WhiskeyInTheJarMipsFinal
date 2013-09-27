package br.rural.graficos;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;

import br.rural.memoria.Memoria;
import br.rural.montador.Montador;
import br.rural.montador.MontadorInstrucoes;
import br.rural.ucp.Conversor;
import br.rural.ucp.Registers;
import br.rural.ucp.Ucp;
import br.rural.ucp.Word;

public class MenuBotoes extends JToolBar {
	
    private URL mountimg = getClass().getClassLoader().getResource("br/rural/graficos/imagens/Assemble16.png");
    private URL stepimg = getClass().getClassLoader().getResource("br/rural/graficos/imagens/Step.png");
    private URL exectimg = getClass().getClassLoader().getResource("br/rural/graficos/imagens/Play22.png");
    private Registers registradores = Registers.getInstance();
    private ImageIcon iconeMount = new ImageIcon(mountimg);
	private JButton mount = new JButton(iconeMount);
	private ImageIcon iconeStep = new ImageIcon(stepimg);
	private JButton step = new JButton(iconeStep);
	private ImageIcon iconeExec = new ImageIcon(exectimg);
	private JButton exec = new JButton(iconeExec);
	private Ucp ucp;
	private Montador montador;
	private JTextArea area;
	private RegistradoresTab tabelaregs;
	private MemoriaTab tab ;
	private JTabbedPane tabs;
	private Instrucoes instrucao;


	MenuBotoes(JTextArea area, RegistradoresTab tabelaregs,MemoriaTab tab,JTabbedPane tabs) {
		
		this.tab=tab;
        this.tabs = tabs;
		this.tabelaregs = tabelaregs;
		this.area = area;
		addEventos();
		add(mount);
		add(step);
		add(exec);
	}
	
	public void addEventos() {

		exec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				execute();
			}
		});

		step.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				step();
			}
		});

		mount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				montar();
			
			}
		});
	}

	public void step() {
		
		boolean flag = ucp.step();
		step.setEnabled(flag);
		exec.setEnabled(flag);
      
		for (Word i : registradores.getLista()) {

			
			String elem = i.getValue();
			int aux = Conversor.converte((elem));
			tabelaregs.setValue(i.getPosicao(), 1, aux);
			
		}

		tabelaregs.getTable().getSelectedRow();
		
		tabelaregs.getTable().setRowSelectionInterval(ucp.getRegAtual(),
				ucp.getRegAtual());

		tabelaregs.getTable().setDefaultRenderer(Object.class,new DefaultTableCellRenderer() {
			
			public Component getTableCellRendererComponent(JTable tableReg, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				super.getTableCellRendererComponent(tableReg, value,
						                                isSelected, hasFocus, row, column);
				
				if (row-1 == ucp.getRegAtual()) {
					
					setBackground(Color.green);
					
				} else {
					
					setBackground(null);					
				}
				
				return this;				
			}
		});
		
		Instrucoes.getInstance().step();	
	}

	public void execute() {

		ucp.execute();
	  
		for (Word i : registradores.getLista()) {

			String elem = i.getValue();
			int aux = Conversor.converte((elem));
			tabelaregs.setValue(i.getPosicao(), 1, aux);
		}
		
		exec.setEnabled(false);
		step.setEnabled(false);
	}

	public void montar() {
		
		exec.setEnabled(true);
		step.setEnabled(true);
		tabs.setEnabledAt(1, true);
		tabs.setEnabledAt(2, true);
	    tabs.setSelectedIndex(1);

		montador = new Montador(area.getText());
		
		Memoria.getInstance().reset();
		Ucp.getInstance().reset();
		
		montador.montaMem();
		tab.tableRefresh();
		ucp = Ucp.getInstance();	
		Instrucoes.getInstance().reset();	
		Instrucoes.getInstance().montar( new MontadorInstrucoes(area.getText()));
			
	}
}