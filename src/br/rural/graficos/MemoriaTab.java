package br.rural.graficos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.ScrollPane;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.rural.memoria.Memoria;
import br.rural.ucp.Conversor;

public class MemoriaTab extends JPanel{

	private JTable tabela = new JTable();
	private Memoria memoria = Memoria.getInstance();
	private ScrollPane scrool = new ScrollPane();
	private int n = 32;
	private int auxpc = 0;
	private int PC = 0;
	private StringBuffer buffer;
	
	public MemoriaTab() {

		tabela = new JTable();  
		tabela.setModel(new DefaultTableModel(
				new Object[][] {
						{"Address", "Value (+0)", "Value (+4)", "Value (+8)", "Value (+c)", "Value (+10)", "Value (+14)", "Value (+18)", "Value (+1c)"},
						{"0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x00000020", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},	     	
						{"0x00000040", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x00000060", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x00000080", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x000000a0", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x000000c0", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x000000e0", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x00000100", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x00000120", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x00000140", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x00000160", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x00000180", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x000001a0", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x000001c0", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},
						{"0x000001e0", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000", "0x00000000"},

				},
				new String[] {
						"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"}
				));		

		tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {  
		public Component getTableCellRendererComponent(JTable table, Object value,  
					boolean isSelected, boolean hasFocus, int row, int column) {  
				super.getTableCellRendererComponent(table, value, isSelected,  
						hasFocus, row, column);  
				if (row != 0 && (column != 0)) {

					setBackground(null); 
				} else {  

					setBackground(Color.LIGHT_GRAY);	            	
				}  
				return this;  
			}  
		});
		
		tabela.setRowHeight(0, 30);
		scrool.setPreferredSize(new Dimension(800,290));
		scrool.add(tabela);
		add(scrool);

		setPreferredSize(new Dimension(480, 500));
		setVisible(true);
	}	

	public void tableRefresh(){		

		for(int i =1 ; i<16 ;i++) {

			for(int j =1 ; j<9;j++) {			

				tabela.setValueAt("0x"+Conversor.bintoHex(buffMemoria()).toUpperCase(), i , j);
			}
		}	
		n = 32;
		auxpc = 0;
		PC = 0;
	}

	private String buffMemoria(){

		if(PC == memoria.getSize()){

			return  "00000000000000000000000000000000";
		}
		
		buffer = null;
		buffer = new StringBuffer();

		for (; auxpc < n; auxpc++) {

			buffer.append(memoria.getMem(auxpc));
		}

		PC += 32;
		auxpc = n;
		n += 32;

		return buffer.toString();
	}
}