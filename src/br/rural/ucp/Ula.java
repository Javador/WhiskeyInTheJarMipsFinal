package br.rural.ucp;

public class Ula {

	private Registers reg;
	private static Ula instance;

	private Ula() {

		reg = Registers.getInstance();
	}

	public void rAdder(String destino, String parcela, String parcela2) {

		Word parc = reg.getRegister(parcela);
		Word parc2 = reg.getRegister(parcela2);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue())
				+ Conversor.converte(parc2.getValue());

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);

		dest.setValue(retorno);
	}
	
	public static Ula getInstance(){
		
		if(instance == null){
			
			instance = new Ula();
			
		}
		
		return instance;
	}

	public void rSub(String destino, String parcela, String parcela2) {

		Word parc = reg.getRegister(parcela);
		Word parc2 = reg.getRegister(parcela2);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue())
				- Conversor.converte(parc2.getValue());

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);
		
		dest.setValue(retorno);
	}

	public void iAdder(String destino, String parcela, String numero) {

		Word parc = reg.getRegister(parcela);
		Word dest = reg.getRegister(destino);

		int parcelaI = Conversor.converte(parc.getValue());
		int parcelaII;

		if (destino.equals("11101") || parcela.equals("11101")) {

			parcelaII = Conversor.converte(numero)*8 ;
		} else {
			
			parcelaII = Conversor.converte(numero);
		}

		int value = parcelaI + parcelaII;

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);

		dest.setValue(retorno);
	}

	public boolean RegsEquals(String reg1, String reg2) {

		Word prim = reg.getRegister(reg1);
		Word sec = reg.getRegister(reg2);

		int parcela1 = Integer.parseInt(prim.getValue());
		int parcela2 = Integer.parseInt(sec.getValue());

		if (parcela1 == parcela2) {

			return true;
		}

		return false;
	}

	public void rAnd(String destino, String parcela, String parcela2) {

		Word parc = reg.getRegister(parcela);
		Word parc2 = reg.getRegister(parcela2);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue())
				& Conversor.converte(parc2.getValue());

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);
		
		dest.setValue(retorno);
	}

	public void rOr(String destino, String parcela, String parcela2) {

		Word parc = reg.getRegister(parcela);
		Word parc2 = reg.getRegister(parcela2);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue())
				| Conversor.converte(parc2.getValue());

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);

		dest.setValue(retorno);
	}

	public void rSll(String destino, String parcela, String numero) {

		Word parc = reg.getRegister(parcela);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue()) 
				<< Conversor.converte(numero);

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);

		dest.setValue(retorno);
	}

	public void rSrl(String destino, String parcela, String numero) {

		Word parc = reg.getRegister(parcela);

		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue()) 
				>> Conversor.converte(numero);

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);

		dest.setValue(retorno);
	}

	public void iAndi(String destino, String parcela, String numero) {

		Word parc = reg.getRegister(parcela);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue())
				& Conversor.converte(numero);

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);

		dest.setValue(retorno);
	}

	public void iOri(String destino, String parcela, String numero) {

		Word parc = reg.getRegister(parcela);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue())
				| Conversor.converte(numero);

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);
		
		dest.setValue(retorno);
	}

	public void rMul(String destino, String parcela, String parcela2) {

		Word parc = reg.getRegister(parcela);
		Word parc2 = reg.getRegister(parcela2);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue())
				* Conversor.converte(parc2.getValue());

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);
		
		dest.setValue(retorno);
	}
	
	public void rDiv(String destino, String parcela, String parcela2) {

		Word parc = reg.getRegister(parcela);
		Word parc2 = reg.getRegister(parcela2);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue())
				/ Conversor.converte(parc2.getValue());

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);
		
		dest.setValue(retorno);
	}
	
	public void rRest(String destino, String parcela, String parcela2) {

		Word parc = reg.getRegister(parcela);
		Word parc2 = reg.getRegister(parcela2);
		Word dest = reg.getRegister(destino);

		int value = Conversor.converte(parc.getValue())
				% Conversor.converte(parc2.getValue());

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);
		
		dest.setValue(retorno);
	}

	public void rMove(String destino, String parcela) {

		Word parc = reg.getRegister(parcela);
		Word dest = reg.getRegister(destino);

		String retorno = Integer.toBinaryString(Conversor.converte(parc.getValue()));
		retorno = Conversor.completaBits(retorno, 32);
		
		dest.setValue(retorno);
	}

	public void rSlt(String destino, String parcela, String parcela2) {

		Word parc = reg.getRegister(parcela);
		Word parc2 = reg.getRegister(parcela2);
		Word dest = reg.getRegister(destino);

		int value;
		if (Conversor.converte(parc.getValue()) < Conversor.converte(parc2.getValue())) {
			
			value = 1;
		} else {
			
			value = 0;
		}

		String retorno = Integer.toBinaryString(value);
		retorno = Conversor.completaBits(retorno, 32);
		
		dest.setValue(retorno);
	}
}