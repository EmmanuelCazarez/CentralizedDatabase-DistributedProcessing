package bancodb;

public class main {

	public static void main(String[] args) {
		Cheques cheq = new Cheques();
		
		cheq.retirar("CHEQ001", Float.valueOf(300));
		cheq.consultarSaldo("CHEQ001");
		//cheq.imprimirCheques();
	}
}
