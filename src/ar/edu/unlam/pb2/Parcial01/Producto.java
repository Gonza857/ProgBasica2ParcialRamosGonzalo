package ar.edu.unlam.pb2.Parcial01;

import java.util.Comparator;

public class Producto extends Item implements Vendible, Comparable<Producto> {

	private Integer puntoDeReposicion;

	public Producto(Integer codigo, String nombre, Double precio, Integer puntoDeReposicion) {
		super(codigo, nombre, precio);
		this.puntoDeReposicion = puntoDeReposicion;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Producto o) {
		return this.getCodigo().compareTo(o.getCodigo());
	}
	
	public Integer getPuntoDeReposicion () {
		return this.puntoDeReposicion;
	}

	// TODO: Completar con los getters y setters necesarios

}
