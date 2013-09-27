package br.rural.graficos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.rural.memoria.Memoria;
import br.rural.montador.MontadorInstrucoes;
import br.rural.ucp.Conversor;
import br.rural.ucp.Registers;
import br.rural.ucp.Ucp;

public class Instrucoes extends JPanel{

	JTable tabela = new JTable();
	ScrollPane scrool = new ScrollPane();
	private Memoria memoria = Memoria.getInstance();
	private Registers regs;
	private int n = 32;
	private int auxpc = 0;
	private int PC = 0;
	private int aux;
	private StringBuffer buffer;
	private JTextArea area;
	private MontadorInstrucoes montador;
	private HashMap<Integer, Integer> instrucoesPc;
	private HashMap<Integer,String> instrucoesTabela;
	private static Instrucoes instance;

	Instrucoes(){

		tabela = new JTable();
		tabela.setModel(new DefaultTableModel(
				new Object[][] {
						{"Address", "Code", "Source",},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""},
						{"", "", ""}},
						new String[] {						
						"New column", "New column", "New column"}
				));

		tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {  
			public Component getTableCellRendererComponent(JTable table, Object value,  
					boolean isSelected, boolean hasFocus, int row, int column) {  
				super.getTableCellRendererComponent(table, value, isSelected,  
						hasFocus, row, column);  
				if (row == 0) {						
					setBackground(Color.LIGHT_GRAY);
				} else {  
					setBackground(null); 							            	
				}  
				return this; 
			}  
		});	 
		
		scrool.setPreferredSize(new Dimension(800,600));
		scrool.add(tabela);
		add(scrool);

		setPreferredSize(new Dimension(480, 500));
		setVisible(true);
	}

	private void pegaInstrucao(int inte){

		buffer = null;
		buffer = new StringBuffer();
		int i =0;

		for (; auxpc < n; auxpc++) {

			buffer.append(memoria.getMem(auxpc));
			String aux = buffer.toString();
			tabela.setValueAt("0x"+Conversor.bintoHex(aux), inte+1, 1);
			tabela.setValueAt("0x"+Conversor.bintoHex(Conversor.completaBits(Integer.toBinaryString(inte*4),  32  )), inte+1, 0);
		}

		auxpc = n;
		n += 32;
	}

	public void montar(MontadorInstrucoes mount ) {
		this.instrucoesPc = mount.getInstrucoesPc();
		this.instrucoesTabela = mount.getInstrucoestabela();

		for (Integer inte: mount.getInstrucoestabela().keySet()) {

			pegaInstrucao(inte);
			tabela.setValueAt(mount.getInstrucoestabela().get(inte), inte+1, 2);
			aux++;
		}
	}

	public void reset(){
          
		if(aux >30){
			
			aux = 30;
			
		}
		for(int i =1 ; i < aux ; i++){
			
			tabela.setValueAt(null, i, 1);
			tabela.setValueAt(null, i, 0);
			tabela.setValueAt(null, i, 2);
			
		}
		
	      	 n = 32;
			 auxpc = 0;
			 PC = 0;
		
	}

	public static Instrucoes getInstance() {

		if(instance == null) {

			instance = new Instrucoes();
		}
		return instance;
	}


	public void step() {

		final int i;

		if(instrucoesPc.containsKey(Conversor.converte( Registers.getInstance()
				.getRegister("pc").getValue()))) {

			i = Conversor.converte( Registers.getInstance().getRegister("pc").getValue());
			tabela.getSelectedRow();
			tabela.setRowSelectionInterval(instrucoesPc.get(i),
					instrucoesPc.get(i));
			tabela.setDefaultRenderer(Object.class,new DefaultTableCellRenderer() {

				public Component getTableCellRendererComponent(JTable tableReg, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {

					super.getTableCellRendererComponent(tableReg, value,
							isSelected, hasFocus, row-1, column);

					if (row == instrucoesPc.get(i)) {

						setBackground(Color.yellow);

					} else {

						setBackground(null);
					}
					return this;				
				}
			});
		}	
	}
	
}