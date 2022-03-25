package ejemploLambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ControllerCliente {
	
	/*
	 * Este método es el más importante de esta clase, y el que los demás métodos tienen
	 * como base. Si recuerdas la explicación, dijimos que una interfaz funcional solo tiene
	 * un método abstracto. En el api de java podemos comprobar que el método de la interfaz
	 * Predicate <T> es .test().
	 * 
	 * Ese método es al que le hemos dado cuerpo al escribir la expresion lambda en el 
	 * principal, por lo tanto, para tener todo resumido en un unico método, lo que hacemos es
	 * pasarle a este metodo testearCliente, un cliente al que vamos a testear, y una instancia
	 * de Predicate. Como hemos reescrito el metodo test de cada una de las instancia que hemos
	 * creado, este método devolvera true o false, dependiendo del tester que le pasemos.
	 * 
	 * 
	 * 
	 * Por ejemplo, asumimos que le pasamos el testerSubvencionParo, recordamos esta linea:
	 * 
	 * Predicate <Cliente> testerSubvencionParo = (c) -> c.isEnParo() && c.getAniosEnParo() > 1;
	 * 
	 * Y el cliente:
	 * 
	 * Cliente [nombre=Alejandro, telefono=632 829 115, edad=31, numeroHermanos=1, 
	 * enParo=true, aniosEnParo=3, ultimaNomina=1000.0]
	 * 
	 * 
	 * 
	 * El código que se ejecutaría sería el de la interfaz que le pasamos:
	 * 
	 * c.isEnParo() && c.getAniosEnParo() > 1.
	 * 
	 * Y como Alejandro cumple con estas caracteristicas:
	 * 
	 * enParo=true, aniosEnParo=3
	 * 
	 * El resultado sería verdadero.
	 * 
	 */
	public boolean testearCliente (Predicate <Cliente> tester, Cliente c) {
		return tester.test(c);
	}
	
	public void mostrarUnaPrestacion (Predicate <Cliente> tester, Cliente c) {
		if(testearCliente(tester, c)) {
			System.out.println("El cliente " +c.getNombre()+ " tiene derecho a la subvención.");
		}else {
			System.out.println("El cliente " +c.getNombre()+ " no es apto para la subvención.");
		}
	}
	
	public List <Cliente> comprobarClientesAptos (Predicate <Cliente> tester, CRUDcliente control){
		
		List <Cliente> aptos = new ArrayList <Cliente> ();
		
		for (Cliente clienteTesteando : control.getMiembros()) {
			if(testearCliente(tester, clienteTesteando)) {
				aptos.add(clienteTesteando);
			}
		}
		
		return aptos;
	}
	
	public void mostrarClientesAptos (List <Cliente> aptos) {
		int contador = 1;
		for (Cliente cliente : aptos) {
			System.out.println(contador+ " - " +cliente.getNombre()+ ", " +cliente.getEdad()+ " años.");
			contador++;
		}
	}
	
	public void mostrarClientes (CRUDcliente control) {
		for (Cliente cliente : control.getMiembros()) {
			System.out.println(cliente);
		}
	}
	
	public void precargarClientes(CRUDcliente control) {
		control.getMiembros().add(new Cliente("Francisco", "665 564 398", 66, 2, true, 7, 1100));
		control.getMiembros().add(new Cliente("Pablo", "665 482 622", 27, 2, false, 0, 1000));
		control.getMiembros().add(new Cliente("Andrea", "688 945 378", 34, 1, true, 1, 1700));
		control.getMiembros().add(new Cliente("Yolanda", "744 372 987", 22, 6, false, 0, 750));
		control.getMiembros().add(new Cliente("Jesús", "656 472 657", 30, 0, false, 0, 900));
		control.getMiembros().add(new Cliente("Putin", "888 888 888", 46, 0, false, 0, 40000000));
		control.getMiembros().add(new Cliente("Alvaro", "622 438 699", 26, 2, true, 2, 600));
		control.getMiembros().add(new Cliente("Alejandro", "632 829 115", 31, 1, true, 3, 1000));
	}

}
