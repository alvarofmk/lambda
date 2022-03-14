package ejemploLambda;

import java.util.function.Predicate;

public class ControllerCliente {
	
	public void mostrarUnaPrestacion (Predicate <Cliente> tester, Cliente c) {
		if(testearCliente(tester, c)) {
			System.out.println("El cliente " +c.getNombre()+ " tiene derecho a la subvenci�n.");
		}else {
			System.out.println("El cliente " +c.getNombre()+ " no es apto para la subvenci�n.");
		}
	}
	
	public boolean testearCliente (Predicate <Cliente> tester, Cliente c) {
		return tester.test(c);
	}

}
