package ejemploLambda;

import java.util.function.Function;

public class Principal {
	
	/*
	 * En este programa vamos a explicar qu� son y c�mo funcionan las funciones Lambda.
	 * 
	 * Las expresiones lambda no son m�s que funciones o m�todos que est�n escritos con
	 * una sintaxis distinta de la que estamos acostumbrados, como ejemplo, este m�todo:
	 */
	
	static int sumarUno (int a) {
		return a+1;
	}
	
	/* 
	 * Hace exactamente lo mismo que esta funci�n, como puedes ver se ahorra c�digo (aunque
	 * en este ejemplo sea muy poco). La verdadera utilidad de estas expresiones reside en que
	 * generalmente se utilizan para hacer m�todos o funciones anonimas, es decir, que no
	 *  necesitan una clase  
	 *  */
	
	Function<Integer, Integer> addOne = (a) -> a+1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

}
