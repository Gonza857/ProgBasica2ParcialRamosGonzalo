package ar.edu.unlam.pb2.Parcial01;

import java.util.Comparator;

public class Servicio extends Item implements Vendible, Comparable<Servicio> {

	private String fechaDeInicio;
	private String fechaDeFinalizacion;

	public Servicio(Integer codigo, String nombre, Double precio, String fechaDeInicio, String fechaDeFinalizacion) {
		super(codigo, nombre, precio);
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeFinalizacion = fechaDeFinalizacion;
	}

	@Override
	public Integer getCodigo() {
		return super.getCodigo();
	}

	@Override
	public String getNombre() {
		return super.getNombre();
	}

	@Override
	public Double getPrecio() {
		return super.getPrecio();
	}


	@Override
	public int compareTo(Servicio o) {
		// TODO Auto-generated method stub
		return 0;
	}

	// TODO: Completar con los getters y setters necesarios

}
