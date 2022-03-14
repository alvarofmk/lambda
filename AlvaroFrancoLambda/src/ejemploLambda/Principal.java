package ejemploLambda;

import java.util.function.Function;

public class Principal {
	
	/*
	 * En este programa vamos a explicar qué son y cómo funcionan las funciones Lambda.
	 * 
	 * Las expresiones lambda no son más que funciones o métodos que están escritos con
	 * una sintaxis distinta de la que estamos acostumbrados, como ejemplo, este método:
	 */
	
	static int sumarUno (int a) {
		return a+1;
	}
	
	/* 
	 * Hace exactamente lo mismo que esta función, como puedes ver se ahorra código (aunque
	 * en este ejemplo sea muy poco). La verdadera utilidad de estas expresiones reside en que
	 * generalmente se utilizan para hacer métodos o funciones anonimas, es decir, que no
	 *  necesitan una clase  
	 *  */
	
	Function<Integer, Integer> addOne = (a) -> a+1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

}
