package ejemplosnolambda;

import ejemploLambda.Cliente;

public class ComprobadorFamiliar implements Icomprobador {

	@Override
	public boolean comprobar(Cliente c) {
		boolean resultado = false;
		
		 if(c.getNumeroHermanos() >= 2 && c.getUltimaNomina() <=1000) {
			 resultado = true;
		 }
		 
		 return resultado;
	}

}
