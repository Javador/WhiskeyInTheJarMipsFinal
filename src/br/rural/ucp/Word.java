package br.rural.ucp;

public class Word {
	
	private String value;
	
	public int getPosicao() {
		
		return posicao;
	}

	public void setPosicao(int posicao) {
		
		this.posicao = posicao;
	}

	private int posicao;
	
	Word() {
		
		value = "00000000000000000000000000000000";
	}

	public String getValue() {
		
		return value;
	}

	public void setValue(String value) {
		
		value = Conversor.completaBits(value, 32);		
		this.value = value;
	}
}