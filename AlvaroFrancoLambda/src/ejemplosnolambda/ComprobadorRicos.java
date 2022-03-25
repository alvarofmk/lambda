package ejemplosnolambda;

import ejemploLambda.Cliente;

public class ComprobadorRicos implements Icomprobador {

	@Override
	public boolean comprobar(Cliente c) {
		boolean resultado = false;
		
		 if(!c.isEnParo() && c.getUltimaNomina() > 1200) {
			 resultado = true;
		 }
		 
		 return resultado;
	}

}
