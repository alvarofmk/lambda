package ejemplosnolambda;

import ejemploLambda.Cliente;

public class ComprobadorParo implements Icomprobador {

	@Override
	public boolean comprobar(Cliente c) {
		boolean resultado = false;
		
		 if(c.isEnParo() && c.getAniosEnParo() > 1) {
			 resultado = true;
		 }
		 
		 return resultado;
	}

}
