package br.rural.montador;

import java.util.HashMap;
import java.util.StringTokenizer;

public class MontadorInstrucoes {

	private HashMap<Integer, Integer> instrucoesPc = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> instrucoestabela = new HashMap<Integer, String>();
	private StringTokenizer tokenizer;
	private int Pc = 0;
	private int tabela = 0;
	private StringBuilder buffer = new StringBuilder();
	private StringBuilder buffer2 = new StringBuilder();
	public MontadorInstrucoes(String assembly) {

		assembly = assembly.replaceAll("( )*+,( )*+", ",");

		tokenizer = new StringTokenizer(assembly);
		String token = tokenizer.nextToken();

		while (!token.equals("halt")) {			

			if (token.contains(":")) {
				
				buffer2.append(token + " " +tokenizer.nextToken());
				token = buffer2.toString();
				if(token.contains("halt"))
				{
					break;
				}
			}
			
			buffer.append(token + " " +tokenizer.nextToken());			
			instrucoesPc.put(Pc, tabela+1);
			instrucoestabela.put(tabela, buffer.toString());
			buffer = new StringBuilder();
			buffer2 = new StringBuilder();
			Pc += 32;
			tabela++;			
			token = tokenizer.nextToken();	
			
			if(token.equals("halt")) {
				break;
			}
		}
	}

	public HashMap<Integer, Integer> getInstrucoesPc() {
		return instrucoesPc;
	}

	public void setInstrucoesPc(HashMap<Integer, Integer> instrucoesPc) {
		this.instrucoesPc = instrucoesPc;
	}

	public HashMap<Integer, String> getInstrucoestabela() {
		return instrucoestabela;
	}

	public void setInstrucoestabela(HashMap<Integer, String> instrucoestabela) {
		this.instrucoestabela = instrucoestabela;
	}
}