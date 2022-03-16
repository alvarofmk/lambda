package ejemploLambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import utilidad.Leer;

public class Principal {
	
	/*
	 * En este programa vamos a explicar qué son y cómo funcionan las funciones Lambda.
	 * 
	 * Las expresiones lambda no son más que funciones o métodos que están escritos con
	 * una sintaxis distinta de la que estamos acostumbrados, como ejemplo, este método:
	 */
	
	public static int sumarUno (int a) {
		return a+1;
	}
	
	/* 
	 * Hace exactamente lo mismo que esta función:
	 *  */
	
	Function<Integer, Integer> addOne = (a) -> a+1;
	
	/*
	 * Como puedes ver se ahorra código (aunque en este ejemplo sea muy poco). La 
	 * verdadera utilidad de estas expresiones reside en que generalmente se utilizan 
	 * para hacer métodos o funciones anonimas, es decir, que no necesitan una clase.
	 * 
	 * En este ejemplo vamos a suponer que queremos gestionar solicitudes para distintos
	 * préstamos o subvenciones, para ello, queremos comprobar que los solicitantes cumplen
	 * con algún requisito para acceder a una prestacion.
	 */

	public static void main(String[] args) {

		/*
		 * Lo primero que vamos a hacer es crear las clases que nos hacen falta para el ejemplo.
		 * Una vez hecho eso, creamos unos cuantos clientes y los añadimos a la lista para tener
		 * con lo que probar los métodos que vamos a crear con las expresiones lambda.
		 */
		
		List <Cliente> miembros = new ArrayList <Cliente> ();
		miembros.add(new Cliente("Francisco", "665 564 398", 66, 2, true, 7, 1100));
		miembros.add(new Cliente("Pablo", "665 482 622", 27, 2, false, 0, 1000));
		miembros.add(new Cliente("Andrea", "688 945 378", 34, 1, true, 1, 1700));
		miembros.add(new Cliente("Yolanda", "744 372 987", 22, 6, false, 0, 750));
		miembros.add(new Cliente("Jesús", "656 472 657", 30, 0, false, 0, 900));
		miembros.add(new Cliente("Putin", "888 888 888", 46, 0, false, 0, 40000000));
		miembros.add(new Cliente("Alvaro", "622 438 699", 26, 2, true, 2, 600));
		miembros.add(new Cliente("Alejandro", "632 829 115", 31, 1, true, 3, 1000));
		
		CRUDcliente sepe = new CRUDcliente(miembros);
		
		/*
		 * Ahora si, tenemos todos nuestros clientes con sus caracteristicas, y queremos
		 * en nuestro programa querer comprobar si cualifican para recibir distintas prestaciones.
		 * Pero imagina que cada prestacion tiene requisitos distintos. Vamos a poner los 
		 * siguientes como ejemplo(aunque no sean realistas):
		 * 
		 * Subvencion del paro: Estar en paro durante más de un año.
		 * Subvencion familiar: Tener dos hermanos o más y que la ultima nomina haya sido como máximo de 1000€.
		 * Subvención dinero para los ricos: No estar en paro y cobrar más de 1200€ al mes.
		 * Subvencion tercera edad: Tener más de 30 años o alternativamente llamarse Durbán.
		 * 
		 * Con lo que conocemos hasta ahora, la mejor forma de hacer estas comprobaciones sería,
		 * en una nueva clase, hacer cuatro métodos como este, uno para cada subvención:
		 */
		
		/*
		 *	public boolean aptoParaFamiliar (Cliente c) {
		 *		boolean resultado = false;
		 *	
		 *		if(c.getNumeroHermanos() >= 2 && c.getUltimaNomina() <=1000) {
		 *			resultado = true;
		 *		}
		 *	
		 *		return resultado;
		 *	}
		 */
		
		/*
		 * Sin embargo, esta clase se podría hacer gigantesca en cuanto se empezara a alargar la lista
		 * de subvenciones, así que vamos a darle solución con las expresiones Lambda.
		 * 
		 * Lo primero que se debe saber, es que estas expresiones funcionan con la ayuda de una serie
		 * de interfaces, las interfaces funcionales, que YA se encuentran dentro del API de java, por lo 
		 * que solo vamos a tener que importar la que necesitemos, dependiendo de lo que la función vaya a 
		 * recibir y devolver. Os dejo aqui el enlace a la pagina correspondiente del API, pero esta 
		 * parte viene explicada en profundidad en el trabajo de Durbán.
		 */
		
		// https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/package-summary.html
		
		/*
		 * Lo básico que necesitais saber de estas interfaces, es que solo tienen UN METODO ABSTRACTO, entonces
		 * lo que vamos a hacer con las lambda es crear un objeto del tipo de la interfaz, y al mismo tiempo
		 * darle el código a ese único método abstracto.
		 * 
		 * En nuestro caso, vamos a trabajar con la interfaz Predicate, que acepta un objeto como argumento, 
		 * y devuelve un booleano. Si os fijais esta interfaz esta importada al inicio de la aplicacion.
		 */
		
		/*
		 * En esta linea vamos a declarar la primera implementacion de la interfaz, para la primera de las 
		 * subvenciones:
		 */
		
		Predicate <Cliente> testerSubvencionParo = (c) -> c.isEnParo() && c.getAniosEnParo() > 1;
		
		/*
		 * Lo primero que se pone es la interfaz que hemos escogido, es decir, el tipo (Predicate)
		 * 
		 * Entre los <> escribimos el argumento que va a recibir, en la API podeis ver que la interfaz Predicate
		 * indica <T>. T es un objeto cualquiera, y lo que estamos diciendo es que en vez de un objeto cualquiera,
		 * el objeto "testerSubvencionParo", va a recibir un Cliente.
		 * 
		 * Después, el nombre, como hemos dicho: testerSubvencionParo.
		 */
		
		/*
		 * Predicate <Cliente> testerSubvencionParo
		 * 
		 * Hasta aqui, lo que hemos hecho ha sido declarar un nuevo objeto de la interfaz Predicate, al igual que si
		 * hicieramos:
		 * 
		 * List <Cliente> listaClientes
		 * 
		 * La diferencia viene en lo que escribimos despues del igual, ya que esta interfaz solo tiene un metodo, 
		 * esta expresion nos permite darle el codigo a ese metodo en la misma linea, en vez de escribir el clasico
		 * new Predicate etc etc.
		 * 
		 * Es decir, estamos resumiento el: 
		 * 
		 * - Crear una interfaz con un metodo abstracto
		 * - Crear una clase
		 * - Implementar la interfaz y darle cuerpo al método
		 * 
		 * En una sola linea.
		 */
		
		/*
		 * (c) -> c.getAniosEnParo() > 1;
		 * 
		 * Como hemos dicho, lo que viene despues del igual, en vez de ser new lo que sea, es el cuerpo del método:
		 * 
		 * Ya que habiamos escrito <Cliente>, ya hemos dicho que el metodo va a recibir un objeto Cliente, con (c)
		 * le estamos dando nombre al cliente que recibe el metodo. Al igual que en un metodo escribieramos
		 * boolean testParo (Cliente c).
		 * 
		 * A continuación viene esta flechita (->). Esto es en si la expresion lambda. Lo que hace es separar los argumentos
		 * que recibe a la izquierda, es decir (c), y lo que devuelve a la derecha.
		 * 
		 * Finalmente tenemos la devolucion del método (c.getAniosEnParo() > 1). Habiamos dicho que la interfaz Predicate
		 * devuelve un booleano, por lo que, lo que escribamos aqui solo puede tener dos resultados. Podemos usar >=, =
		 * <, y cualquier otra expresion que se nos ocurra siempre que de como resultado un booleano.
		 * 
		 *  En este caso, saldrá true si el cliente c lleva más de un año en paro. Y false si no.
		 */
		
		/*
		 * Ahora escribimos el codigo para las otras subvenciones:
		 */
		
		// Podemos usar && para añadir más condiciones, al igual que en los parentesis de los ifs, por ejemplo.
		Predicate <Cliente> testerSubvencionFamiliar = (c) -> c.getNumeroHermanos() >= 2 && c.getUltimaNomina() < 1000;
		Predicate <Cliente> testerSubvencionRicos = (c) -> !c.isEnParo() && c.getUltimaNomina() > 1200;
		
		// También podemos usar || para decir que se cumpla una u otra condicion para que sea verdadero.
		Predicate <Cliente> testerSubvencionViejos = (c) -> c.getEdad() > 30 || c.getNombre().equalsIgnoreCase("Durban");
		
		// Vamos a hacer ahora el resto del programa
		
		int menu = 0, edad, hermanos, aux, aniosEnParo, menuSubvenciones;
		String nombre, telefono;
		double nomina;
		boolean enParo;
		Cliente clienteAuxiliar = null;
		ControllerCliente control = new ControllerCliente();
		
		do {
			
			System.out.println("\nBienvenido al programa de gestion de subvenciones del SEPE");
			System.out.println("1. Agregar nuevo cliente");
			System.out.println("2. Comprobar requisitos de un cliente para una subvencion");
			System.out.println("3. Mostrar qué clientes tienen derecho a una subvención");
			System.out.println("4. Mostrar todos los clientes");
			menu=Leer.datoInt();
			switch (menu) {
				case 1:
					System.out.println("Introduzca el nombre del cliente:");
					nombre = Leer.dato();
					System.out.println("Introduzca el telefono del cliente:");
					telefono = Leer.dato();
					System.out.println("Introduzca la edad del cliente:");
					edad = Leer.datoInt();
					System.out.println("Introduzca el numero de hermanos que tien el cliente:");
					hermanos = Leer.datoInt();
					System.out.println("Pulse 1 si está en el paro. 0 si no.");
					aux = Leer.datoInt();
					enParo = intToBoolean(aux);
					if(enParo) {
						System.out.println("¿Cuántos años lleva en paro?");
						aniosEnParo = Leer.datoInt();
					}else {
						aniosEnParo = 0;
					}
					System.out.println("Especifique la última nómina cobrada por el cliente:");
					nomina = Leer.datoDouble();
					
					sepe.agregarCliente(new Cliente(nombre, telefono, edad, hermanos, enParo, aniosEnParo, nomina));
					break;
				case 2:
					/*
					 * En este caso del switch vamos a utilizar por primera vez los distintos Predicate que hemos
					 * creado. Para ello, aqui le preguntamos el nombre del cliente que queremos comprobar al usuario
					 * para poder buscarlo en la lista.
					 */
					System.out.println("Indique el nombre del cliente");
					nombre = Leer.dato();
					clienteAuxiliar = sepe.encontrarPorNombre(nombre);
					
					if(clienteAuxiliar!=null) {
						/*
						 * Y despues dejamos que elija que subvención quiere comprobar con un pequeño menú, segun lo que
						 * pulse, dentro del switch llamaremos al mismo método (se encuentra en controller), al que le pasamos
						 * un tester y un cliente.
						 * 
						 * Puedes encontrar la explicación del método en controller.
						 */
						menuSubvenciones();
						menuSubvenciones = Leer.datoInt();
						switch (menuSubvenciones) {
							case 1:
								control.mostrarUnaPrestacion(testerSubvencionParo, clienteAuxiliar);
								break;
							case 2:
								control.mostrarUnaPrestacion(testerSubvencionFamiliar, clienteAuxiliar);
								break;
							case 3:
								control.mostrarUnaPrestacion(testerSubvencionRicos, clienteAuxiliar);
								break;
							case 4:
								control.mostrarUnaPrestacion(testerSubvencionViejos, clienteAuxiliar);
								break;
							default:
								System.out.println("Opcion inválida.");
								break;
						}
					}else {
						System.out.println("No se ha encontrado al cliente");
					}
					
					break;
				case 3:
					menuSubvenciones();
					menuSubvenciones = Leer.datoInt();
					switch (menuSubvenciones) {
						case 1:
							control.mostrarClientesAptos(control.comprobarClientesAptos(testerSubvencionParo, sepe));
							break;
						case 2:
							control.mostrarClientesAptos(control.comprobarClientesAptos(testerSubvencionFamiliar, sepe));
							break;
						case 3:
							control.mostrarClientesAptos(control.comprobarClientesAptos(testerSubvencionRicos, sepe));
							break;
						case 4:
							control.mostrarClientesAptos(control.comprobarClientesAptos(testerSubvencionViejos, sepe));
							break;
						default:
							System.out.println("Opcion inválida.");
							break;
					}
					break;
				case 4:
					control.mostrarClientes(sepe);
					break;
				default:
					System.out.println("Opcion inválida.");
					break;
			}
			
		}while(menu!=0);
		
		
		

	}
	
	public static boolean intToBoolean (int a) {
		boolean resultado = false;
		if(a==1) {
			resultado = true;
		} 
		return resultado;
	}
	
	public static void menuSubvenciones () {
		
		System.out.println("Indique la subvención a comprobar");
		System.out.println("1. Subvención por desempleo");
		System.out.println("2. Subvención familiar");
		System.out.println("3. Subvención para adinerados");
		System.out.println("4. Subvención por vejez");
	}

}
