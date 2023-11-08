package ar.edu.unlam.pb2.Parcial01;

import java.util.HashMap;
import java.util.Map;

public class Venta {

	private String codigo;
	private Cliente cliente;
	private Vendedor vendedor;
	private Map<Vendible, Integer> renglones;

	public Venta(String codigo, Cliente cliente, Vendedor vendedor) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.renglones = new HashMap<>();
		// TODO: Completar el constructor para el correcto funcionamiento del software
	}
	
	// TODO: Completar con los getters y setters necesarios
	
	public void agregarRenglon(Vendible vendible) {
		// TODO: Agregar el vendible a la venta 
		System.out.println("Se agrego un servicio al renglon");
		this.renglones.put(vendible, 1);
	}

	public void agregarRenglon(Vendible vendible, Integer cantidad) {
		// TODO: Agregar el vendible a la venta 
		this.renglones.put(vendible, cantidad);
	}
	
	public Map<Vendible, Integer> getRenglones () {
		return this.renglones;
	}
	
	@Override
	public String toString() {

		return "Venta: " + codigo + "\n" + "Cliente" + cliente + "\n" + "Vendedor" + vendedor + "\n";
	}
	
	public Vendedor getVendedor () {
		return this.vendedor;
	}
	
	public String getCodigo () {
		return this.codigo;
	}

}
