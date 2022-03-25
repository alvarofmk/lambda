package ejemplosnolambda;

import ejemploLambda.Cliente;

public class ComprobadorTerceraEdad implements Icomprobador {

	@Override
	public boolean comprobar(Cliente c) {
		boolean resultado = false;
		
		 if(c.getEdad() > 30 || c.getNombre().equalsIgnoreCase("Durban")) {
			 resultado = true;
		 }
		 
		 return resultado;
	}

}
