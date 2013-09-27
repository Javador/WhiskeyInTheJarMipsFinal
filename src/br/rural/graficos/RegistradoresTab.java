package br.rural.graficos;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTable;
import br.rural.ucp.Conversor;

public class RegistradoresTab extends JPanel {

	private JTable table= new JTable();

	public RegistradoresTab() {
		
		Object[][] dados = { { "Registrador", "Valor Dec.", "Valor Hex." },
				{ "$zero","0","0x00000000" }, { "$at","0", "0x00000000" },
				{ "$v0","0","0x00000000" }, { "$v1","0","0x00000000" },
				{ "$a0","0","0x00000000" }, { "$a1","0","0x00000000" },
				{ "$a2","0","0x00000000" }, { "$a3","0","0x00000000" },
				{ "$t0","0","0x00000000" }, { "$t1","0","0x00000000" },
				{ "$t2","0","0x00000000" }, { "$t3","0","0x00000000" },
				{ "$t4","0","0x00000000" }, { "$t5","0","0x00000000" },
				{ "$t6","0","0x00000000" }, { "$t7","0","0x00000000" },
				{ "$s0","0","0x00000000" }, { "$s1","0","0x00000000" },
				{ "$s2","0","0x00000000" }, { "$s3","0","0x00000000" },
				{ "$s4","0","0x00000000" }, { "$s5","0","0x00000000" },
				{ "$s6","0","0x00000000" }, { "$s7","0","0x00000000" },
				{ "$t8","0","0x00000000" }, { "$t9","0","0x00000000" },
				{ "$k0","0","0x00000000" }, { "$k1","0","0x00000000" },
				{ "$gp","0","0x00000000" }, { "$sp","0","0x00000000" },
				{ "$fp","0","0x00000000" }, { "$ra","0","0x00000000" },
				{ "$pc","0","0x00000000" }, { "$hi","0","0x00000000" },
				{ "$lo","0","0x00000000" } };

		table = new JTable(dados, new String[] { "Registradores", "Valores", "Valores" });
		
		table.setRowHeight(0, 20); 
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		table.setMinimumSize(new Dimension(400,600));		
		
		setVisible(true);
		add(table);
	}	

	public JTable getTable(){
		
		return table;
	}

	public void setValue(int row, int colum, int i) {

		if(row==32 || row==29)
		{
			i = i/8;
			String hex = Integer.toHexString(i);
			hex = Conversor.completaBits(hex, 8);
			table.setValueAt("0x"+hex.toUpperCase(), row+1, colum+1);
			table.setValueAt(Integer.toString(i), row+1, colum);
		} else {
			
			String hex = Integer.toHexString(i);
			hex = Conversor.completaBits(hex, 8);
			table.setValueAt("0x"+hex.toUpperCase(), row+1, colum+1);
			table.setValueAt(Integer.toString(i), row+1, colum);
		}
	}
}