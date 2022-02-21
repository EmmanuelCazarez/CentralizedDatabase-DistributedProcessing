package bancodb;

public class main {

	public static void main(String[] args) {
		Cheques cheq = new Cheques();
		
		cheq.depositar("CHEQ001", Float.valueOf(100));
		cheq.imprimir();
	}
}
