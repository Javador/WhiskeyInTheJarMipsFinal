package br.rural.ucp;

public class Ucp {	
	
	private Controle control ;

	private int RegAtual ,reganterior;
    private static Ucp instance ;
    
    
	private Ucp() {

		control = Controle.getInstance();
	}
	
	public void reset() {
		
		control.reset();		
	}
	
	public static Ucp getInstance(){
		
		if(instance == null) {
			instance = new Ucp();
		}		
		return instance;		
	}

	public boolean step() {

		boolean retorno = true;
		retorno = control.seekInstruction();
		RegAtual = control.decode();

		if(RegAtual == 0) {

			RegAtual = reganterior;			
		}

		reganterior = RegAtual;
		
		return retorno;
	}	

	public int getRegAtual() {

		return RegAtual;
	}

	public void execute() {

		control.execute();
	}
}