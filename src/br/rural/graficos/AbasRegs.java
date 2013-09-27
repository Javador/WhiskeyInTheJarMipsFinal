package br.rural.graficos;

import javax.swing.JTabbedPane;

public class AbasRegs extends JTabbedPane {

	AbasRegs(RegistradoresTab tab) {

		add("Registradores",tab);
		setSize(900, 500);
	}
}