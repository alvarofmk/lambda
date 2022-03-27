package ejemploLambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import ejemplosnolambda.Icomprobador;
import utilidad.Leer;

public class Principal {
	
	/*
	 * En este programa vamos a explicar qu� son y c�mo funcionan las funciones Lambda.
	 * 
	 * Las expresiones lambda no son m�s que funciones o m�todos que est�n escritos con
	 * una sintaxis distinta de la que estamos acostumbrados, como ejemplo, este m�todo:
	 */
	
	public static int sumarUno (int a) {
		return a+1;
	}
	
	/* 
	 * Hace exactamente lo mismo que esta funci�n:
	 *  */
	
	Function<Integer, Integer> addOne = (a) -> a+1;
	
	/*
	 * Como puedes ver se ahorra c�digo (aunque en este ejemplo sea muy poco). La 
	 * verdadera utilidad de estas expresiones reside en que generalmente se utilizan 
	 * para hacer m�todos o funciones anonimas, es decir, que no necesitan una clase.
	 * 
	 * En este ejemplo vamos a suponer que queremos gestionar solicitudes para distintos
	 * pr�stamos o subvenciones, para ello, queremos comprobar que los solicitantes cumplen
	 * con alg�n requisito para acceder a una prestacion.
	 */

	public static void main(String[] args) {

		//  Lo primero que vamos a hacer es crear las clases que nos hacen falta para el ejemplo.
		
		List <Cliente> miembros = new ArrayList <Cliente> ();
		CRUDcliente sepe = new CRUDcliente(miembros);
		ControllerCliente control = new ControllerCliente();
		
		/*
		 * Una vez hecho eso, creamos unos cuantos clientes y los a�adimos a la lista para tener
		 * ejemnplos con los que probar los m�todos que vamos a crear con las expresiones lambda.
		 */
		
		control.precargarClientes(sepe);
		
		/*
		 * Ahora si, tenemos todos nuestros clientes con sus caracteristicas, y queremos
		 * en nuestro programa comprobar si cualifican para recibir distintas prestaciones.
		 * Pero imagina que cada prestacion tiene requisitos distintos. Vamos a poner las 
		 * siguientes como ejemplo (aunque no sean realistas):
		 * 
		 * - Subvencion del paro: Estar en paro durante m�s de un a�o.
		 * - Subvencion familiar: Tener dos hermanos o m�s y que la ultima nomina haya sido como m�ximo de 1000�.
		 * - Subvenci�n para los ricos: No estar en paro y cobrar m�s de 1200� al mes.
		 * - Subvencion tercera edad: Tener m�s de 30 a�os o alternativamente llamarse Durb�n.
		 * 
		 * Con lo que conocemos hasta ahora, la mejor forma de hacer estas comprobaciones ser�a,
		 * al igual que hacemos con los comparator:
		 * 
		 * 1� - Creamos una interfaz IComprobador, con un metodo que sea comprobar
		 * 2� - Como cada subvencion comprueba si el cliente es apto de una forma distinta, para cada
		 * 		subvencion creariamos una clase, y cada una de ellas implementar�a el metodo comprobar
		 * 		de la manera que necesite, como por ejemplo as� para la subvenci�n familiar:
		 */
		
		/*
		 *	public boolean comprobar (Cliente c) {
		 *		boolean resultado = false;
		 *	
		 *		if(c.getNumeroHermanos() >= 2 && c.getUltimaNomina() <=1000) {
		 *			resultado = true;
		 *		}
		 *	
		 *		return resultado;
		 *	}
		 */
		
		// En el paquete "ejemplosnolambda" puedes ver c�mo quedar�a si hicieramos el programa de esta forma.
		
		/*
		 * 3� - Finalmente, en controller (o donde sea que tengamos nuestros m�todos), creariamos un metodo
		 * 		al que le pasar�amos un Icomprobador (es decir, un objeto de una clase que implemente esta
		 * 		interfaz), y un cliente, para que compruebe si el cliente es apto, seg�n los criterios que
		 * 		le pasemos con el Icomprobador.
		 *  
		 * Sin embargo, estar�amos creando una interfaz, adem�s de 4 clases, una para cada subvenci�n.
		 * El programa se engordar�a r�pidamente, e imaginad que en vez de solo 4 subvenciones tuvieramos cientas,
		 * el n�mero de clases ser�a ingestionable.
		 * 
		 * Para evitar esto vamos a darle soluci�n con las expresiones Lambda.
		 * 
		 * El primer paso para solucionarlo, ser�a pasar de usar clases, a clases anonimas, es decir, en vez de
		 * crear una clase para cada subvenci�n en el m�todo que hemos hecho siempre, podemos crear un nuevo
		 * objeto Icomprobador, y darle c�digo aqui mismo, para especificar que este objeto en concreto, va
		 * a implementar el metodo comprobar de una forma distinta, sin necesidad de crear una clase.
		 */
		
		Icomprobador comprobadorFamiliar = new Icomprobador() {
				
			public boolean comprobar(Cliente c) {
				boolean resultado = false;
				 if(c.getNumeroHermanos() >= 2 && c.getUltimaNomina() <=1000) {
				 		resultado = true;
				 }
				 return resultado;
			}
		};
		
		/*
		 * Con esta soluci�n ya nos ahorramos la creaci�n de todas las clases que hab�amos mencionado,
		 * pero aun as� nos quedar�a una gran cantidad de c�digo en el principal, (imagina esta clase anonima de
		 * arrba para cada una de las subvenciones). El siguiente paso es crear una clase anonima, y darle el c�digo
		 * con una funci�n lambda.
		 */
		
		/* 
		 * Lo primero que se debe saber, es que estas expresiones funcionan con la ayuda de interfaces funcionales, 
		 * es decir, interfaces que SOLO TIENEN UN M�TODO ABSTRACTO, como la que hemos creado (Icomprobador), por lo que
		 * al crear un objeto de esta interfaz y darle c�digo con lambda, lo que estamos es dandole el funcionamiento
		 * al �nico m�todo que hay dentro.
		 * 
		 * Podemos crear nosotros una interfaz de este tipo si lo necesitamos, pero con este fin, el API de Java ya
		 * cuenta con una serie de interfaces funcionales que solo var�an en lo que recibe, y devuelve, el m�todo
		 * que tiene dentro, por lo que solo vamos a tener que importar la que necesitemos, dependiendo de lo que la funci�n vaya a 
		 * recibir y devolver. 
		 * 
		 * Os dejo aqui el enlace a la pagina correspondiente del API, pero esta 
		 * parte viene explicada en profundidad en el trabajo de Durb�n.
		 */
		
		// https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/package-summary.html
		
		/*
		 * En nuestro caso, vamos a trabajar con la interfaz Predicate, que acepta un objeto como argumento, 
		 * y devuelve un booleano. Si os fijais esta interfaz esta importada al inicio de la aplicacion.
		 */
		
		/*
		 * En esta linea vamos a declarar la primera implementacion de la interfaz, para la primera de las 
		 * subvenciones:
		 */
		
		Predicate <Cliente> testerSubvencionParo = c -> c.isEnParo() && c.getAniosEnParo() > 1;
		
		
		/*
		 * Lo primero que se pone es la interfaz que hemos escogido, es decir, el tipo (Predicate)
		 * 
		 * Entre los <> escribimos el argumento que va a recibir, en la API podeis ver que la interfaz Predicate
		 * indica <T>. T es un objeto cualquiera, y lo que estamos diciendo es que en vez de un objeto cualquiera,
		 * el objeto "testerSubvencionParo", va a recibir un Cliente.
		 * 
		 * Despu�s, el nombre, como hemos dicho: testerSubvencionParo.
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
		 * - Implementar la interfaz y darle cuerpo al m�todo
		 * 
		 * En una sola linea.
		 */
		
		/*
		 * (c) -> c.getAniosEnParo() > 1;
		 * 
		 * Como hemos dicho, lo que viene despues del igual, en vez de ser new lo que sea, es el cuerpo del m�todo:
		 * 
		 * Ya que habiamos escrito <Cliente>, ya hemos dicho que el metodo va a recibir un objeto Cliente, con (c)
		 * le estamos dando nombre al cliente que recibe el metodo. Al igual que en un metodo escribieramos
		 * boolean testParo (Cliente c).
		 * 
		 * A continuaci�n viene esta flechita (->). Esto es en si la expresion lambda. Lo que hace es separar los argumentos
		 * que recibe a la izquierda, es decir (c), y lo que devuelve a la derecha.
		 * 
		 * Finalmente tenemos la devolucion del m�todo (c.getAniosEnParo() > 1). Habiamos dicho que la interfaz Predicate
		 * devuelve un booleano, por lo que, lo que escribamos aqui solo puede tener dos resultados. Podemos usar >=, =
		 * <, y cualquier otra expresion que se nos ocurra siempre que de como resultado un booleano.
		 * 
		 *  En este caso, saldr� true si el cliente c lleva m�s de un a�o en paro. Y false si no.
		 */
		
		// Ahora escribimos el codigo para las otras subvenciones:
		 
		
		// Podemos usar && para a�adir m�s condiciones, al igual que en los parentesis de los ifs, por ejemplo.
		Predicate <Cliente> testerSubvencionFamiliar = (c) -> c.getNumeroHermanos() >= 2 && c.getUltimaNomina() < 1000;
		Predicate <Cliente> testerSubvencionRicos = (c) -> !c.isEnParo() && c.getUltimaNomina() > 1200;
		
		// Tambi�n podemos usar || para decir que se cumpla una u otra condicion para que sea verdadero.
		Predicate <Cliente> testerSubvencionViejos = (c) -> c.getEdad() > 30 || c.getNombre().equalsIgnoreCase("Durban");
		
		// Vamos a hacer ahora el resto del programa
		
		int menu = 0, edad, hermanos, aux, aniosEnParo, menuSubvenciones;
		String nombre, telefono;
		double nomina;
		boolean enParo;
		Cliente clienteAuxiliar = null;
		
		do {
			
			System.out.println("\nBienvenido al programa de gestion de subvenciones del SEPE");
			System.out.println("1. Agregar nuevo cliente");
			System.out.println("2. Comprobar requisitos de un cliente para una subvencion");
			System.out.println("3. Mostrar qu� clientes tienen derecho a una subvenci�n");
			System.out.println("4. Mostrar todos los clientes");
			
			//Abajo a partir de este caso est�n explicados los m�todos de la interfaz Predicate
			System.out.println("5. Comprobar si un cliente tiene derecho a alguna subvenci�n");
			System.out.println("6. Comprobar si un cliente tiene derecho a todas las subvenciones");
			System.out.println("7. Comprobar si un cliente ha dejado de tener derecho a una subvenci�n");
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
					System.out.println("Pulse 1 si est� en el paro. 0 si no.");
					aux = Leer.datoInt();
					enParo = intToBoolean(aux);
					if(enParo) {
						System.out.println("�Cu�ntos a�os lleva en paro?");
						aniosEnParo = Leer.datoInt();
					}else {
						aniosEnParo = 0;
					}
					System.out.println("Especifique la �ltima n�mina cobrada por el cliente:");
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
						 * Y despues dejamos que elija que subvenci�n quiere comprobar con un peque�o men�, segun lo que
						 * pulse, dentro del switch llamaremos al mismo m�todo (se encuentra en controller), al que le pasamos
						 * un tester y un cliente.
						 * 
						 * Puedes encontrar la explicaci�n del m�todo en controller.
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
								System.out.println("Opcion inv�lida.");
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
							System.out.println("Opcion inv�lida.");
							break;
					}
					break;
				case 4:
					control.mostrarClientes(sepe);
					break;
					
					/*
					 * Como dijimos antes, una interfaz funcional cuenta con un solo m�todo abstracto, que hemos podido
					 * ver, que en el caso de la interfaz Predicate, es .test()
					 * 
					 * Sin embargo, la interfaz puede contar con otros m�todos no abstractos que podemos usar.
					 * En el caso de predicate, contamos con 3 m�todos, bastante sencillos:
					 * 
					 *  - or()
					 *  - and()
					 *  - negate()
					 *  
					 * Est�n explicados en ese orden en los case 5, 6 y 7
					 */

				case 5:
					/*
					 * En este case vamos a comprobar si un cliente tiene derecho a al menos una de las subvenciones, para
					 * ellos, volvemos a buscar el cliente.
					 */
					System.out.println("Indique el nombre del cliente");
					nombre = Leer.dato();
					clienteAuxiliar = sepe.encontrarPorNombre(nombre);
					
					if(clienteAuxiliar!=null) {
						/*
						 * Y ahora, para comprobarlo, vamos a usar el m�todo .or(), a este m�todo hay que pasarle otro
						 * predicado, y lo que hace es combinar ambos de forma que el resultado sea true en cuanto uno
						 * de ellos se cumpla, como si los combinaramos con el operador "||".
						 * 
						 * Como esto realmente devuelve un predicado, podemos volver a llamar a .or() de nuevo, y asi
						 * formamos un Predicate que devuelve true si cualquiera de las subvenciones sale positiva.
						 */
						control.mostrarUnaPrestacion(testerSubvencionFamiliar
								.or(testerSubvencionViejos)
								.or(testerSubvencionRicos)
								.or(testerSubvencionParo), clienteAuxiliar);
					}else {
						System.out.println("No se ha encontrado al cliente");
					}
				case 6:
					/*
					 * En este case vamos a comprobar si un cliente tiene derecho a todas las subvenciones.
					 */
					System.out.println("Indique el nombre del cliente");
					nombre = Leer.dato();
					clienteAuxiliar = sepe.encontrarPorNombre(nombre);
					
					if(clienteAuxiliar!=null) {
						/*
						 * Para comprobarlo, vamos a usar el m�todo .and(), funciona de la misma manera que el m�todo .or(),
						 * pero en este caso devolver� true solo si se cumplen TODAS las condiciones de todos los predicados.
						 * Como si los combinaramos con el operador "||".
						 * 
						 * Tambi�n los podemos encadenar.
						 */
						control.mostrarUnaPrestacion(testerSubvencionFamiliar
								.and(testerSubvencionViejos)
								.and(testerSubvencionRicos)
								.and(testerSubvencionParo), clienteAuxiliar);
					}else {
						System.out.println("No se ha encontrado al cliente");
					}
				case 7:
					/*
					 * En este caso del switch vamos a comprobar si un cliente NO cualifica para una subvenci�n.
					 * Para ello usaremos el m�todo .negate()
					 */
					System.out.println("Indique el nombre del cliente");
					nombre = Leer.dato();
					clienteAuxiliar = sepe.encontrarPorNombre(nombre);
					
					if(clienteAuxiliar!=null) {
						menuSubvenciones();
						menuSubvenciones = Leer.datoInt();
						/*
						 * Despu�s de elegir el cliente y la subvenci�n, tan solo tenemos que llamar al m�todo, este
						 * devuelve un Predicate que niega al que lo ha llamado, como si pusieramos un "!" delante de
						 * una condici�n, por lo que devolver� true si el cliente no cuenta con los requisitos.
						 */
						switch (menuSubvenciones) {
							case 1:
								control.mostrarUnaPrestacion(testerSubvencionParo.negate(), clienteAuxiliar);
								break;
							case 2:
								control.mostrarUnaPrestacion(testerSubvencionFamiliar.negate(), clienteAuxiliar);
								break;
							case 3:
								control.mostrarUnaPrestacion(testerSubvencionRicos.negate(), clienteAuxiliar);
								break;
							case 4:
								control.mostrarUnaPrestacion(testerSubvencionViejos.negate(), clienteAuxiliar);
								break;
							default:
								System.out.println("Opcion inv�lida.");
								break;
						}
					}else {
						System.out.println("No se ha encontrado al cliente");
					}
					
					break;
				default:
					System.out.println("Opcion inv�lida.");
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
		
		System.out.println("Indique la subvenci�n a comprobar");
		System.out.println("1. Subvenci�n por desempleo");
		System.out.println("2. Subvenci�n familiar");
		System.out.println("3. Subvenci�n para adinerados");
		System.out.println("4. Subvenci�n por vejez");
	}

}
