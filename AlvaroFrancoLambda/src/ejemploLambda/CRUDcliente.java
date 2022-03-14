package ejemploLambda;

import java.util.Iterator;
import java.util.List;

public class CRUDcliente {
	
	/*
	 * De igual forma hacemos un CRUD normal y corriente para el programa.
	 */
	
	private List <Cliente> miembros;

	public CRUDcliente(List<Cliente> miembros) {
		super();
		this.miembros = miembros;
	}

	public List<Cliente> getMiembros() {
		return miembros;
	}

	public void setMiembros (List<Cliente> miembros) {
		this.miembros = miembros;
	}

	@Override
	public String toString () {
		return "CRUDcliente [miembros=" + miembros + "]";
	}
	
	public void agregarCliente (Cliente nuevo) {
		miembros.add(nuevo);
	}
	
	public Cliente encontrarPorNombre (String nombre) {
		Iterator <Cliente> it = miembros.iterator();
		boolean encontrado = false;
		Cliente aux = null;
		while(it.hasNext() && !encontrado) {
			aux = it.next();
			if(aux.getNombre().equalsIgnoreCase(nombre)) {
				encontrado = true;
			}
		}
		
		if(!encontrado) {
			aux = null;
		}
		return aux;
	}
	
	

}
