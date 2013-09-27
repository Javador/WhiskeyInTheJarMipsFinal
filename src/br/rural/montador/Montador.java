package br.rural.montador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import br.rural.memoria.Memoria;
import br.rural.ucp.Conversor;

public class Montador {

	private int labelPc = 0;
	private ArrayList<String> instrucoes = new ArrayList<String>();
	private HashMap<String, Integer> labels = new HashMap<String,Integer>();
	private HashMap<String, String> registers = new HashMap<String, String>();
	private StringTokenizer tokenizer;
	private StringBuilder buffer = new StringBuilder();
	private Memoria memoria ;

	public Montador(String assembly) {		
		
		assembly = assembly.replaceAll("( )*+,( )*+", ",");

		tokenizer = new StringTokenizer(assembly);			
		String token = tokenizer.nextToken();	

		while (!token.equals("halt")) {
			
			if (token.contains(":")) {

				token = token.replace(":","");
				labels.put(token,labelPc);
				token = tokenizer.nextToken();
				if(token.equals("halt")) {
					
					break;
				}
			}			

			buffer.append(token + "_" + tokenizer.nextToken());
			instrucoes.add(buffer.toString());
			buffer = new StringBuilder();
			labelPc += 32;
			token = tokenizer.nextToken();
		}

		montaRegs();
	}

	private void montaRegs() {

		registers.put("$zero","00000");
		registers.put("$v0", "00010");
		registers.put("$v1", "00011");
		registers.put("$a0", "00100");
		registers.put("$a1", "00101");
		registers.put("$a2", "00110");
		registers.put("$a3", "00111");
		registers.put("$t0", "01000");
		registers.put("$t1", "01001");
		registers.put("$t2", "01010");
		registers.put("$t3", "01011");
		registers.put("$t4", "01100");
		registers.put("$t5", "01101");
		registers.put("$t6", "01110");
		registers.put("$t7", "01111");
		registers.put("$s0", "10000");
		registers.put("$s2", "10010");
		registers.put("$s3", "10011");
		registers.put("$s4", "10100");
		registers.put("$s5", "10101");
		registers.put("$s6", "10110");
		registers.put("$s7", "10111");
		registers.put("$t8", "11000");
		registers.put("$t9", "11001");
		registers.put("$sp", "11101");
		registers.put("$ra", "11111");
		registers.put("$fp", "11110");
		registers.put("$gp", "11100");
	}

	public void montaMem() {
		
		memoria = Memoria.getInstance();

		int i = 0;
		
		while (i < instrucoes.size()) {

			memoria.add(reconheceInst(instrucoes.get(i)));
			
			i++;
		}
		memoria.add("11111111111111111111111111111111");
	}

	public String calcBinNumeros(String numero) {

		int i = Integer.parseInt(numero);
		String ret = Integer.toBinaryString(i);
		
		ret = Conversor.completaBits(ret, 16);

		if (ret.length() > 16) {

			return ret.substring(16,32);
		}

		return ret;
	}

	public String reconheceRegs(String reg) {

		return registers.get(reg);
	}

	public String reconheceInst(String comando) {

		String a[] = comando.split("_");
		String ret;
		String regs[];

		if (a[0].equals("jal")) {

			String aux = Integer.toBinaryString(labels.get(a[1]));

			while(aux.length() < 26 ) { aux = "0" + aux ;}

			ret ="000011"+aux ; 
			return ret;
		}
		if (a[0].equals("jr")) {

			String aux = reconheceRegs(a[1]);

			ret ="000000"+aux+"000000000000000001000" ; 
			
			return ret;
		}
		if (a[0].equals("add")) {

			regs = a[1].split(",");

			ret = "000000" + reconheceRegs(regs[1]) + reconheceRegs(regs[2])
					+ reconheceRegs(regs[0]) + "00000" + "100000";

			return ret;
		}
		if (a[0].equals("beq")) {

			regs = a[1].split(",");

			ret = "000100" + reconheceRegs(regs[0]) + reconheceRegs(regs[1])
					+ calcBinNumeros(""+labels.get(regs[2]));

			return ret;
		}
		if (a[0].equals("j")) {

			String aux = Integer.toBinaryString(labels.get(a[1]));

			while(aux.length() < 26 ){ aux = "0" + aux ;}

			ret = "000010" + aux;

			return ret;
		}
		if (a[0].equals("bne")) {

			regs = a[1].split(",");

			ret = "000101" + reconheceRegs(regs[0]) + reconheceRegs(regs[1])
					+ calcBinNumeros(""+labels.get(regs[2]));

			return ret;
		}
		if (a[0].equals("addi")) {

			regs = a[1].split(",");

			ret = "001000" + reconheceRegs(regs[0]) + reconheceRegs(regs[1])
					+ calcBinNumeros(regs[2]);

			return ret;
		}
		if (a[0].equals("sub")) {

			regs = a[1].split(",");

			ret = "000000" + reconheceRegs(regs[1]) + reconheceRegs(regs[2])
					+ reconheceRegs(regs[0]) + "00000" + "100010";

			return ret;
		}
		if (a[0].equals("sw")) {

			regs = a[1].split(",");
			String shift[] = regs[1].split("\\(");
			shift[1] = shift[1].replaceAll("\\)","");

			ret = "101011" + reconheceRegs(regs[0]) + reconheceRegs(shift[1])
					+ calcBinNumeros(shift[0]);

			return ret;
		}
		if (a[0].equals("lw")) {

			regs = a[1].split(",");
			String shift[] = regs[1].split("\\(");
			shift[1] = shift[1].replaceAll("\\)","");

			ret = "100011" + reconheceRegs(regs[0]) + reconheceRegs(shift[1])
					+ calcBinNumeros(shift[0]);

			return ret;
		}
		if (a[0].equals("and")) {

			regs = a[1].split(",");

			ret = "000000" + reconheceRegs(regs[1]) + reconheceRegs(regs[2])
					+ reconheceRegs(regs[0]) + "00000" + "100100";

			return ret;
		}
		if (a[0].equals("or")) {

			regs = a[1].split(",");

			ret = "000000" + reconheceRegs(regs[1]) + reconheceRegs(regs[2])
					+ reconheceRegs(regs[0]) + "00000" + "100101";

			return ret;
		}
		if (a[0].equals("sll")) {

			regs = a[1].split(",");

			ret = "000000" + "00000" + reconheceRegs(regs[1])
					+ reconheceRegs(regs[0]) + calcBinNumeros(regs[2]).substring(11, 16)
					+ "000000";

			return ret;
		}
		if (a[0].equals("srl")) {

			regs = a[1].split(",");

			ret = "000000" + "00000" + reconheceRegs(regs[1])
					+ reconheceRegs(regs[0]) + calcBinNumeros(regs[2]).substring(11, 16)
					+ "000010";

			return ret;
		}
		if (a[0].equals("andi")) {

			regs = a[1].split(",");

			ret = "001100" + reconheceRegs(regs[1]) + reconheceRegs(regs[0])
					+ calcBinNumeros(regs[2]);

			return ret;
		}
		if (a[0].equals("ori")) {

			regs = a[1].split(",");

			ret = "001101" + reconheceRegs(regs[1]) + reconheceRegs(regs[0])
					+ calcBinNumeros(regs[2]);

			return ret;
		}
		if (a[0].equals("mul")) {

			regs = a[1].split(",");

			ret = "000000" + reconheceRegs(regs[1]) + reconheceRegs(regs[2])
					+ reconheceRegs(regs[0]) + "00000" + "011000";

			return ret;
		}
		if (a[0].equals("div")) {

			regs = a[1].split(",");

			ret = "000000" + reconheceRegs(regs[1]) + reconheceRegs(regs[2])
					+ reconheceRegs(regs[0]) + "00000" + "011001";

			return ret;
		}
		if (a[0].equals("rest")) {

			regs = a[1].split(",");

			ret = "000000" + reconheceRegs(regs[1]) + reconheceRegs(regs[2])
					+ reconheceRegs(regs[0]) + "00000" + "011010";

			return ret;
		}
		if (a[0].equals("move")) {

			regs = a[1].split(",");

			ret = "000000" + reconheceRegs(regs[0]) + reconheceRegs(regs[1])
					+ "00000" + "00000" + "011111";

			return ret;
		}
		if (a[0].equals("slt")) {
			
			regs =a[1].split(",");			
			ret ="000000"+reconheceRegs(regs[1])+reconheceRegs(regs[2])
					+ reconheceRegs(regs[0]) + "00000" + "101010";		
			return ret;
		}

		return null;
	}
}