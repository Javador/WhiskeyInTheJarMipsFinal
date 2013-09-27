package br.rural.graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JanelaHelp extends JFrame {

	private JTextArea area = new JTextArea();
	private URL icon = getClass().getClassLoader().getResource("br/rural/graficos/imagens/Help22.png");
	private JTable tabela = new JTable();

	public JanelaHelp() {
		
        setIconImage(new ImageIcon(icon).getImage());
		inicializar();		
	}

	public void inicializar() {

		JPanel painel = new JPanel();
		painel.setLayout(new BorderLayout());
		setTitle("Instrucoes");
		add(painel);		

		tabela = new JTable();  
		tabela.setModel(new DefaultTableModel(
				new Object[][] {
						{"Instrução", "Exemplo", "Significado"},
						{"Soma", "add $t0,$t1,$t2", "$t0 = $t1+$t2"},
						{"Subtração", "sub $s1, $s2,$s3", "$s1 = $s2 - $s3"},
						{"Soma imediato", "addi $s1,$s2, 100", "$s1 = $s2 + 100"},
						{"Multiplicação", "mul $v0,$a1,$v0", "$v0 = $a1 * $v0"},
						{"Divisão",	"div $t0,$a1,$a0", "$t0 = $a1 / $s0"},
						{"Resto", "rest $t1,$a1,$a0"	, "$t1 = $a1 % $a0"},
						{"AND", "and $s1, $s2,$s3", "$s1 = $s2 & $s3"},
						{"AND imediato", "andi $s1,$s2,100", "$s1 = $s2 & 100"},
						{"OR", "or $s1,$s2,$s3", "$s1 = $s2 ou $s3"},
						{"OR imediato",	"ori $s1,$s2,100", "$s1 = $s2 ou 100"},
						{"SHIFT para a Esquerda", "sll $s1,$s2,10", "$s1 = $s2 << 10 (Shifta os bits de $s2 10 posições para a esquerda)"},
						{"SHIFT para a Direita", "srl $s1,$s2,10", "$s1 = $s2 >> 10 (Shifta os bits de $s2 10 posições para a direita)"},
						{"Leitura do Array", "lw $s1,100($s2)", "$s1 = Memória ($s2 + 100)"},
						{"Escrita no Array", "sw $s1,100($s2)", "Memória ($s2 + 100) = $s1"},
						{"Verificar Igualdade", "beq $s1,$s2,label", "if ( $s1 == $s2 ) {vai para label}"},
						{"Verificar Desigualdade", "bne $s1,$s2,label", "if ( $s1 != $s2 ) {vai para label}"},
						{"Verificar se for menor", "slt $s1,$s2,$s3", "if ( $s2 < $s3 ) {$s1 = 1} else {$s1 = 0}"},
						{"Salto", "j label", "Vá para label"},
						{"Salto para um registrador", "jr $ra", "Vá para $ra"},
						{"Salto para um ‘link’", "jal Func", "$ra = PC + 4; Vá para Func"},
						{"Mover valor", "move $v0,$a0", "Move o $a0 para $v0"}},
				new String[] {"New column", "New column", "New column"}
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
		
		painel.add(tabela);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(0);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(250);
		tabela.setRowHeight(0, 30); 

		area.setPreferredSize(new Dimension(500, 500));
		ScrollPane scroll = new ScrollPane();
		scroll.setPreferredSize(new Dimension(800,280));
		scroll.add(area);
		
		setResizable(false);
		setSize(700, 395);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}