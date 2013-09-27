package br.rural.graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Help extends JMenu {

	private JMenuItem instrucoes = new JMenuItem("Instrucoes");

	Help(JTextArea area) {

		super("Help");
		eventos();
		add(instrucoes);
	}

	public void eventos() {

		instrucoes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JanelaHelp janela = new JanelaHelp();
			}
		});
	}
}