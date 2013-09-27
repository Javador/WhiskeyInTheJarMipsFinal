package br.rural.memoria;

public class Memoria {

	private int[] memoria ;
	private int index =0;
	private static Memoria instance;

	private Memoria() {

		memoria = new int[4000];		
	}
	
	public static Memoria getInstance(){
		
		if(instance == null){
			
			instance = new Memoria();
			
		}
		
		return instance;
	}

	public void add(String e) {		

		for(int i =0; i < e.length(); i++) {

			memoria[index] = Integer.parseInt(""+e.charAt(i));			
			index++;			
		}
	}

	public void add( int shift,String elem ) {

		for(int j =0; j < elem.length(); j++) {

			memoria[shift+j]= Integer.parseInt(""+elem.charAt(j));
		}
	}
	
	public void reset() {
		
		index = 0;
		memoria = new int[4000];
		System.gc();		
	}

	public int  getMem(int indice) {

		return memoria[indice]; 
	}

	public int getSize(){

		return memoria.length;
	}
}