package br.rural.ucp;

import java.util.HashMap;

public class Conversor {

	private static HashMap<String, String> mapa = new HashMap<String, String>();

	public static String bintoHex(String bin) {
		
		montar();
		
		if(bin == null || bin.length()<32){
			
			return " ";
			
		}

		return  mapa.get(bin.substring(0, 4))
				+ mapa.get(bin.substring(4, 8)) + mapa.get(bin.substring(8, 12))
				+ mapa.get(bin.substring(12, 16)) + mapa.get(bin.substring(16, 20))
				+ mapa.get(bin.substring(20, 24)) + mapa.get(bin.substring(24, 28))
				+ mapa.get(bin.substring(28, 32));
	}

	private static void montar() {

		mapa.put("0000", "0");
		mapa.put("0001", "1");
		mapa.put("0010", "2");
		mapa.put("0011", "3");
		mapa.put("0100", "4");
		mapa.put("0101", "5");
		mapa.put("0110", "6");
		mapa.put("0111", "7");
		mapa.put("1000", "8");
		mapa.put("1001", "9");
		mapa.put("1010", "A");
		mapa.put("1011", "B");
		mapa.put("1100", "C");
		mapa.put("1101", "D");
		mapa.put("1110", "E");
		mapa.put("1111", "F");
	}

	public static int converte(String n) {

		int value;

		if (n.substring(0, 1).equals("1")) {

			value = desconverteComplemento(n);
		} else {

			value = Integer.parseInt(n, 2);
		}

		return value;
	}

	private static int desconverteComplemento(String numero) {

		char[] array = numero.toCharArray();

		for (int i = 0; i < array.length; i++) {

			if (array[i] == '0') {

				array[i] = '1';
			} else {

				array[i] = '0';
			}
		}

		String numeroBin = String.copyValueOf(array);

		int numeroDec = Integer.parseInt(numeroBin, 2);

		numeroDec = (numeroDec + 1) * (-1);

		return numeroDec;
	}

	public static String completaBits(String numero, int i) {

		while (numero.length() < i) {

			numero = "0" + numero;
		}

		return numero;
	}
}