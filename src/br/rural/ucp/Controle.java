package br.rural.ucp;

import br.rural.memoria.Memoria;

public class Controle {

	private int n = 32;
	private int auxpc = 0;
	private int PC = 0;
	private StringBuffer buffer;
    private static Controle instance ;
    
	public StringBuffer getBuffer() {

		return buffer;
	}

	private Ula ula;
	private Memoria mem;
	private Registers reg;

	private Controle() {

		reg = Registers.getInstance();
		ula = Ula.getInstance();
		this.mem =Memoria.getInstance();
		
		reg.getRegister("11101").setValue("" + Integer.toBinaryString((4000)));		
	}
	
	public void reset(){
		n = 32;
		auxpc = 0;
		PC =0;
	}

	public static Controle getInstance(){
		if(instance == null){
			
			instance = new Controle();
			
		}
		return instance;
	}
	public void execute() {

		seekInstruction();
		decode();
		
		while (!buffer.toString().equals("11111111111111111111111111111111")) {
			seekInstruction();
			decode();
		}
	}

	public boolean seekInstruction() {

		buffer = null;
		buffer = new StringBuffer();

		for (; auxpc < n; auxpc++) {

			buffer.append(mem.getMem(auxpc));
		}

		PC += 32;
		reg.getRegister("pc").setValue(Integer.toBinaryString(PC));
		auxpc = n;
		n += 32;

		if(buffer.toString().equals("11111111111111111111111111111111")) {

			return false;
		}
		
		return true;
	}

	//public void seekInstructionb() {

		//buffer = null;
	//	buffer = new StringBuffer();

		//if(auxpc >0) {
			
		//	auxpc = auxpc-32;
		//	n = n-32;
	//	}

		//for (int i = auxpc ;i < n;i++) {

		//	buffer.append(mem.getMem(i));
	//	}

	//	reg.getRegister("pc").setValue(Integer.toBinaryString(PC));
	//}

	public int decode() {

		String n = buffer.toString();
		String opcode = n.substring(0, 6);

		if (opcode.equals("100011")) {

			int shift = Conversor.converte(n.substring(16, 32)) * 8;
			int regOper = Conversor.converte
					(reg.getRegister(n.substring(11, 16)).getValue());

			StringBuffer buff = new StringBuffer();
			Word registrador = reg.getRegister(n.substring(6, 11));

			int shiftValue = shift + regOper;
			int fimIntervalo = shiftValue + 32;

			for (int i = shiftValue; i < fimIntervalo; i++) {

				buff.append(mem.getMem(i));
			}

			String valor = buff.toString();
			registrador.setValue(valor);
			
			return registrador.getPosicao();
		}

		if (opcode.equals("101011")) {

			int shift = Conversor.converte(n.substring(16, 32)) * 8;
			int regOper = Conversor.converte
					(reg.getRegister(n.substring(11, 16)).getValue());

			String valor = reg.getRegister(n.substring(6, 11)).getValue();
			int shiftValue = shift + regOper;

			mem.add(shiftValue, valor);
		}

		if (opcode.equals("000010")) {

			String pcString = reg.getRegister("pc").getValue();
			String aux = pcString.substring(0, 4) + "00" + n.substring(16, 32);

			auxpc = Conversor.converte(aux);
			this.n = auxpc + 32;
			reg.getRegister("pc").setValue(Integer.toBinaryString(auxpc));
			PC = auxpc;
		}

		if (opcode.equals("000011")) {

			Word w = reg.getRegister("11111");
			w.setValue(Integer.toBinaryString(PC));

			auxpc = Conversor.converte(n.substring(6, 32));
			this.n = auxpc + 32;
			reg.getRegister("pc").setValue(Integer.toBinaryString(auxpc));
			PC = auxpc;

			return w.getPosicao();
		}

		if (opcode.equals("000000")) {

			return decodeFunc(n);
		}

		if (opcode.equals("001000")) {

			ula.iAdder(n.substring(6, 11), n.substring(11, 16),
					n.substring(16, 32));

			return reg.getRegister(n.substring(6, 11)).getPosicao();
		}

		if (opcode.equals("000100")) {

			if (ula.RegsEquals(n.substring(6, 11), n.substring(11, 16))) {

				int endereco = (Conversor.converte(n.substring(16, 32))-(PC))/32;
				auxpc = PC + endereco*(32);
				this.n = auxpc + 32;
				PC = auxpc;
			}
		}
		
		if (opcode.equals("000101")) {

			if (!ula.RegsEquals(n.substring(6, 11), n.substring(11, 16))) {

				int endereco = (Conversor.converte(n.substring(16, 32))-(PC))/32;
				auxpc = PC + endereco*(32);
				this.n = auxpc + 32;
				PC = auxpc;
			}
		}
		
		if (opcode.equals("001100")) {

			ula.iAndi(n.substring(11, 16), n.substring(6, 11),
					n.substring(16, 32));

			return reg.getRegister(n.substring(11, 16)).getPosicao();
		}
		
		if (opcode.equals("001101")) {

			ula.iOri(n.substring(11, 16), n.substring(6, 11),
					n.substring(16, 32));

			return reg.getRegister(n.substring(11, 16)).getPosicao();
		}

		return 0;
	}

	public int decodeFunc(String inst) {

		String function = inst.substring(26, 32);

		if (function.equals("100000")) {

			ula.rAdder(inst.substring(16, 21), inst.substring(6, 11),
					inst.substring(11, 16));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();

		}
		
		if (function.equals("100100")) {

			ula.rAnd(inst.substring(16, 21), inst.substring(6, 11),
					inst.substring(11, 16));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();

		}
		
		if (function.equals("100101")) {

			ula.rOr(inst.substring(16, 21), inst.substring(6, 11),
					inst.substring(11, 16));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();

		}
		
		if (function.equals("000000")) {

			ula.rSll(inst.substring(16, 21), inst.substring(11, 16),
					inst.substring(21, 26));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();

		}
		
		if (function.equals("000010")) {

			ula.rSrl(inst.substring(16, 21), inst.substring(11, 16),
					inst.substring(21, 26));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();

		}

		if (function.equals("100010")) {

			ula.rSub(inst.substring(16, 21), inst.substring(6, 11),
					inst.substring(11, 16));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();

		}

		if (function.equals("001000")) {

			Word w = reg.getRegister(inst.substring(6, 11));
			auxpc = Conversor.converte(w.getValue());
			this.n = auxpc + 32;

			reg.getRegister("pc").setValue(Integer.toBinaryString(auxpc));

			PC = auxpc;

			return reg.getRegister(inst.substring(6, 11)).getPosicao();
		}

		if (function.equals("011000")) {

			ula.rMul(inst.substring(16, 21), inst.substring(6, 11),
					inst.substring(11, 16));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();

		}
		
		if (function.equals("011001")) {

			ula.rDiv(inst.substring(16, 21), inst.substring(6, 11),
					inst.substring(11, 16));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();

		}
		
		if (function.equals("011010")) {

			ula.rRest(inst.substring(16, 21), inst.substring(6, 11),
					inst.substring(11, 16));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();

		}
		
		if (function.equals("011111")) {

			ula.rMove(inst.substring(6, 11), inst.substring(11, 16));

			return reg.getRegister(inst.substring(6, 11)).getPosicao();

		}
		
		if (function.equals("101010")) {
			
			ula.rSlt(inst.substring(16, 21), inst.substring(6, 11),
					inst.substring(11, 16));

			return reg.getRegister(inst.substring(16, 21)).getPosicao();
		}
		
		return 0;
	}
}