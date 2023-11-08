package ar.edu.unlam.pb2.Parcial01;

public class Vendedor implements Comparable<Vendedor>{

	private String dni;
	private String nombre;
	private Boolean deLicencia;
	
	public Vendedor (String dni, String nombre) {
		// TODO: Completar el constructor para el correcto funcionamiento del software
		// Por defecto, los vendedores no estan de licencia
		this.dni = dni;
		this.nombre = nombre;
		this.deLicencia = false;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getDeLicencia() {
		return deLicencia;
	}

	public void setDeLicencia(Boolean deLicencia) {
		this.deLicencia = deLicencia;
	}

	@Override
	public int compareTo(Vendedor o) {
		return this.dni.compareTo(o.getDni());
	}
	
	

	// TODO: Completar con los getters y setters necesarios
}
