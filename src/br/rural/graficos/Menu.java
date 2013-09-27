package br.rural.graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Menu extends JMenu {

	private JFileChooser arquivo = new JFileChooser();
	private File file;
	private JMenuItem abrir = new JMenuItem("Abrir");
	private JMenuItem salvar = new JMenuItem("Salvar");
	private JTextArea area;

	Menu(JTextArea area) {

		super("Arquivo");
		this.area = area;
		eventos();
		add(abrir);
		add(salvar);
	}

	public void eventos() {

		abrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				arquivo.showOpenDialog(null);
				file = arquivo.getSelectedFile();
				
				area.setText(null);

				try {

					BufferedReader bf = new BufferedReader(new FileReader(file));

					while(bf.ready()) {

						area.setText(area.getText()+bf.readLine() +"\n");
					}
					bf.close();
					
				} catch(Exception ex) {
				}
			}
		});

		salvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				arquivo.showSaveDialog(null);
				file = arquivo.getSelectedFile();

				try {

					BufferedWriter bw = new BufferedWriter(new FileWriter(file));
					bw.write(area.getText());
					bw.close();

				} catch (IOException e1) {
				}
			}
		});
	}
}