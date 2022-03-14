package ejemploLambda;

import java.util.Objects;

public class Cliente {
	
	/*
	 * La clase cliente no tiene nada especial, simplemente hemos introducido
	 * muchos atributos para poder jugar con las posibilidades de las lambda.
	 */
	
	private String nombre;
	private String telefono;
	private int edad;
	private int numeroHermanos;
	private boolean enParo;
	private int aniosEnParo;
	private double ultimaNomina;
	
	public Cliente(String nombre, String telefono, int edad, int numeroHermanos, boolean enParo,
			int aniosEnParo, double ultimaNomina) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
		this.numeroHermanos = numeroHermanos;
		this.enParo = enParo;
		this.aniosEnParo = aniosEnParo;
		this.ultimaNomina = ultimaNomina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getNumeroHermanos() {
		return numeroHermanos;
	}

	public void setNumeroHermanos(int numeroHermanos) {
		this.numeroHermanos = numeroHermanos;
	}

	public boolean isEnParo() {
		return enParo;
	}

	public void setEnParo(boolean enParo) {
		this.enParo = enParo;
	}

	public int getAniosEnParo() {
		return aniosEnParo;
	}

	public void setAniosEnParo(int aniosEnParo) {
		this.aniosEnParo = aniosEnParo;
	}

	public double getUltimaNomina() {
		return ultimaNomina;
	}

	public void setUltimaNomina(double ultimaNomina) {
		this.ultimaNomina = ultimaNomina;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", telefono=" + telefono + ", edad=" + edad + ", numeroHermanos=" + numeroHermanos + ", enParo=" + enParo + ", aniosEnParo="
				+ aniosEnParo + ", ultimaNomina=" + ultimaNomina + "]";
	}	

}
