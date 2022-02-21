package bancodb;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		int num;
		boolean i = false;
		String nocuenta;
		Float importe;
		
		Cheques cheq = new Cheques();
		Scanner sc = new Scanner(System.in);
			
		do {
			System.out.println("Introduzca su numero de cuenta: ");
			nocuenta = sc.nextLine();
			System.out.println("Introduzca 1 para depositar --- Introduzca 2 para retirar --- Introduzca 3 para consultar su cuenta --- Introduzca 4 para finalizar");
			num = Integer.valueOf(sc.nextLine());
			switch(num) {
				case 1:{
					System.out.println("Introduzca la cantidad a depositar deseada: ");
					importe = Float.valueOf(sc.nextLine());
					cheq.depositar(nocuenta, importe);
					cheq.consultarSaldo(nocuenta);
					break;
				}
				case 2:{
					System.out.println("Introduzca la cantidad a retirar deseada: ");
					importe = Float.valueOf(sc.nextLine());
					cheq.retirar(nocuenta, importe);
					cheq.consultarSaldo(nocuenta);
					break;
				}
				case 3:{
					cheq.consultarCuenta(nocuenta);
					break;
				}
				case 4:{
					i = false;
					System.out.println("Adiós");
					break;
				}
				default:{
					i = true;
					System.out.println("Favor de seleccionar una de las opciones");
				}
			}
		} while(i);
	}
}
