package br.rural.ucp;

import java.util.Collection;
import java.util.HashMap;

public class Registers {
	
	private  HashMap<String,Word> registers = new HashMap<String,Word>(); 
	private static Registers instance;

	private Registers() {
		
		registers.put("00000",new Word());
		registers.get("00000").setPosicao(0);
		registers.put("00010",new Word());
		registers.get("00010").setPosicao(2);
		registers.put("00011",new Word());
		registers.get("00011").setPosicao(3);
		registers.put("00100",new Word());
		registers.get("00100").setPosicao(4);
		registers.put("00101",new Word());
		registers.get("00101").setPosicao(5);
		registers.put("00110",new Word());
		registers.get("00110").setPosicao(6);
		registers.put("00111",new Word());
		registers.get("00111").setPosicao(7);
		registers.put("01000",new Word());
		registers.get("01000").setPosicao(8);
		registers.put("01001",new Word());
		registers.get("01001").setPosicao(9);
		registers.put("01010",new Word());
		registers.get("01010").setPosicao(10);
		registers.put("01011",new Word());
		registers.get("01011").setPosicao(11);
		registers.put("01100",new Word());
		registers.get("01100").setPosicao(12);
		registers.put("01101",new Word());
		registers.get("01101").setPosicao(13);
		registers.put("01110",new Word());
		registers.get("01110").setPosicao(14);
		registers.put("01111",new Word());
		registers.get("01111").setPosicao(15);
		registers.put("10000",new Word());
		registers.get("10000").setPosicao(16);
		registers.put("10001",new Word());
		registers.get("10001").setPosicao(17);
		registers.put("10010",new Word());
		registers.get("10010").setPosicao(18);
		registers.put("10011",new Word());
		registers.get("10011").setPosicao(19);
		registers.put("10100",new Word());
		registers.get("10100").setPosicao(20);
		registers.put("10101",new Word());
		registers.get("10101").setPosicao(21);
		registers.put("10110",new Word());
		registers.get("10110").setPosicao(22);
		registers.put("10111",new Word());
		registers.get("10111").setPosicao(23);
		registers.put("11000",new Word());
		registers.get("11000").setPosicao(24);
		registers.put("11001",new Word());
		registers.get("11001").setPosicao(25);
		registers.put("11111",new Word());
		registers.get("11111").setPosicao(31);
		registers.put("11110",new Word());
		registers.get("11110").setPosicao(30);
		registers.put("11100",new Word());
		registers.get("11100").setPosicao(28);
		registers.put("11101", new Word());
		registers.get("11101").setPosicao(29);
		registers.put("pc",new Word());
		registers.get("pc").setPosicao(32);	
	}
	
	public static Registers getInstance(){
		
		if(instance == null){
			
			instance = new Registers();			
		}		
		return instance;
	}
	
	public HashMap<String, Word> getRegisters() {
		
		return (HashMap<String,Word>) registers.clone();
	}

	public void setRegisters(HashMap<String, Word> registers) {
		
		this.registers = registers;
	}
	
	public void setRegister(String reg,Word value) {	
		
		Word elem = registers.get(reg);
		String aux =value.getValue();
		elem.setValue(aux);		
	}
	
	public Word getRegister(String reg) {
		
		return registers.get(reg);		
	}
	
	public Collection<Word> getLista() {
		
		return registers.values();		
	}
}