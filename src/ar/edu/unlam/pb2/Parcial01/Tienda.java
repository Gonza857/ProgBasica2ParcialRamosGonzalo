package ar.edu.unlam.pb2.Parcial01;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Tienda {

	/*
	 * productos --> teclados o mouses servicios --> soporte tecnico a domicilio
	 * 
	 * registro de stock de productos clientes que compran P/S (cliente y P/S)
	 * ventas vendedores
	 * 
	 * 
	 */

	/**
	 * En esta ocasion deberemos resolver un producto software que nos permita
	 * administrar la venta de productos o servicios de nuestra tienda. Venderemos
	 * entonces, productos como mouse o teclados y servicios como el soporte tecnico
	 * a domicilio. Sabemos que la tienda cuenta con items Vendibles que pueden ser
	 * del tipo Producto o Servicio. Ademas, podemos registrar el stock de los
	 * productos, los clientes a quienes les vendemos algun producto o servicio, las
	 * ventas y los vendedores de la tienda. Antes de realizar alguna operacion, se
	 * debera obtener el elemento correspondiente de las colecciones. Ejemplo: Si
	 * quisiera realizar alguna operacion con un cliente, el mismo debe obtenerse de
	 * la coleccion de clientes.
	 * 
	 * Cada Venta contiene renglones los cuales representa a los productos o
	 * servicios que se incluyen en la misma. Tambien cuenta con el Cliente y
	 * Vendedor que participan en la Venta. Cuando agregamos un vendible a una
	 * venta, lo haremos con 1 unidad. En una version posterior, admitiremos
	 * cantidades variables.
	 * 
	 * Cada Item debe compararse por nombre y precio, en caso de ser necesario.
	 * Recordar que los items deben ser Vendibles.
	 * 
	 */

	private String cuit;
	private String nombre;
	private Set<Vendible> vendibles;
	private Map<Producto, Integer> stock;
	private List<Cliente> clientes;
	private Set<Venta> ventas;
	private Set<Vendedor> vendedores;

	// 1 renglon en fila de la factura
	// ojo hay un mapa que la key es una clase

	public Tienda(String cuit, String nombre) {
		this.cuit = cuit;
		this.nombre = nombre;
		this.vendibles = new HashSet<>(); // ??
		this.stock = new HashMap<>(); // ??
		this.clientes = new ArrayList<>(); // ??
		this.ventas = new HashSet<>(); // ?
		this.vendedores = new TreeSet<>(); // ??
		// TODO: Completar el constructor para el correcto funcionamiento del software
	}

	// TODO: Completar con los getters y setters necesarios

	public Vendible getVendible(Integer codigo) throws VendibleInexistenteException {
		Vendible buscado = null;
		for (Vendible vendible : vendibles) {
			if (vendible.getCodigo().equals(codigo)) {
				return vendible;
			}
		}
		// TODO: Obtiene un producto o servicio de la coleccion de vendibles utilizando
		// el codigo. En caso de no existir devuelve null.
		throw new VendibleInexistenteException("No existe eso");
	}

	public void agregarProducto(Producto producto) {
		this.agregarProducto(producto, 0);
	}

	public void agregarProducto(Producto producto, Integer stockInicial) {
		// TODO: Agrega un producto a la coleccion de vendibles y pone en la coleccion
		// de stocks al producto con su stock inicial
		this.vendibles.add(producto);
		this.stock.put(producto, stockInicial);
		System.out.println("Producto " + producto.getNombre() + " ahora tiene " + getStock(producto));
	}

	public void agregarServicio(Servicio servicio) {
		// TODO: Agrega un servicio a la coleccion de vendibles
		this.vendibles.add(servicio);
	}

	public Integer getStock(Producto producto) {
		return stock.get(producto);
	}

	public void agregarStock(Producto producto, Integer incremento) {
		// TODO: se debe agregar stock a un producto existente
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if (key.getCodigo().equals(producto.getCodigo())) {
				System.out.println("El producto tenia: " + val);
				entry.setValue(val + incremento);
				System.out.println("El producto ahora tiene: " + val);
			}

		}
	}

	public void agregarCliente(Cliente cliente) {
		if (this.clientes.isEmpty()) {
			this.clientes.add(cliente);
			System.out.println("Cliente agregado");
		} else {
			// no esta vacio
			if (!this.clientes.contains(cliente)) {
				this.clientes.add(cliente);
				System.out.println("Cliente agregado");
			} else {
				// ya existe
			}
		}
	}

	public void agregarVendedor(Vendedor vendedor) {
		vendedores.add(vendedor);
	}

	public void agregarVenta(Venta venta) throws VendedorDeLicenciaException {
		// TODO: Agrega una venta a la coleccion correspondiente. En caso de que el
		// vendedor este de licencia, arroja una
		// VendedorDeLicenciaException
		if (venta.getVendedor().getDeLicencia()) {
			System.out.println("Vendedor de licencia bro");
			throw new VendedorDeLicenciaException("Vendedor de licencia");
		}
		this.ventas.add(venta);

	}

	public Producto obtenerProductoPorCodigo(Integer codigo) {
		// TODO: Obtiene un producto de los posibles por su codigo. En caso de no
		// encontrarlo se debera devolver null
		Producto buscado = null;
		for (Vendible producto : vendibles) {
			if (producto instanceof Producto) {
				if (producto.getCodigo().equals(codigo)) {
					buscado = (Producto) producto;
					break;
				}
			}
		}
		return buscado;
	}

	public void agregarProductoAVenta(String codigoVenta, Producto producto)
			throws VentaInexistenteException, VendibleInexistenteException, StockInsuficienteException {
		Venta v = getVentaPorCodigo(codigoVenta);
		Vendible buscado = getVendible(producto.getCodigo());
		saberSiHayStockDeUnProducto(producto.getCodigo());
		if (buscado == null) {
			throw new VendibleInexistenteException("No existe ese vendible");
		} else {
			v.agregarRenglon(buscado, 1);
			restarStock((Producto) buscado, 1);
		}

		// TODO: Agrega un producto a una venta. Si el vendible no existe (utilizando su
		// codigo), se debe lanzar una VendibleInexistenteException
		// Se debe actualizar el stock en la tienda del producto que se agrega a la
		// venta
	}

	public void agregarServicioAVenta(String codigoVenta, Servicio servicio)
			throws VentaInexistenteException, VendibleInexistenteException {
		// TODO: Agrega un servicio a la venta. Recordar que los productos y servicios
		// se traducen en renglones
		Venta v = getVentaPorCodigo(codigoVenta);
		Vendible buscado = getVendible(servicio.getCodigo());
		if (buscado == null) {
			throw new VendibleInexistenteException("No existe ese vendible");
		} else {
			v.agregarRenglon(buscado);
		}
	}

	public List<Producto> obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		// TODO: Obtiene una lista de productos cuyo stock es menor o igual al punto de
		// reposicion. El punto de reposicion, es un valor que
		// definimos de manera estrategica para que nos indique cuando debemos reponer
		// stock para no quedarnos sin productos
		List<Producto> losPoquitos = new ArrayList<>();
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			System.out.println("Stock actual: " + val);
			if (val <= key.getPuntoDeReposicion()) {
				losPoquitos.add(key);
			}
		}
		return losPoquitos;
	}

	public List<Cliente> obtenerClientesOrdenadosPorRazonSocialDescendente() {
		Comparator<Cliente> orden = new OrdenarPorRazonSocialDescendente();
		Set<Cliente> ordenados = new TreeSet<>(orden);
		List<Cliente> listado = new ArrayList<>();

		for (Cliente cliente : clientes) {
			ordenados.add(cliente);
		}
		listado.addAll(ordenados);
		return listado;
		// TODO: obtiene una lista de clientes ordenados por su razon social de manera
		// descendente
	}

	public Map<Vendedor, Set<Venta>> obtenerVentasPorVendedor() {
		Map<Vendedor, Set<Venta>> ventasVendedor = new HashMap<>();
		for (Venta venta : ventas) {
			ventasVendedor.put(venta.getVendedor(), ventasDeUnVendedor(venta.getVendedor().getDni()));
		}
		// TODO: Obtiene un mapa que contiene las ventas realizadas por cada vendedor.
		return ventasVendedor;
	}

	public Set<Venta> ventasDeUnVendedor(String dni) {
		Set<Venta> ventasDelVendedor = new HashSet<>();
		for (Venta venta : ventas) {
			if (venta.getVendedor().getDni().equals(dni)) {
				ventasDelVendedor.add(venta);
			}
		}
		return ventasDelVendedor;
	}

	public Double obtenerTotalDeVentasDeServicios() {
		// TODO: obtiene el total acumulado de los vendibles que son servicios incluidos
		// en todas las ventas.
		// Si una venta incluye productos y servicios, solo nos interesa saber el total
		// de los servicios
		Double total = 0.0;
		for (Venta venta : ventas) {
			for (Map.Entry<Vendible, Integer> entry : venta.getRenglones().entrySet()) {
				Vendible key = entry.getKey();
				Integer val = entry.getValue();
				if (key instanceof Servicio) {
					System.out.println("Encontre un servicio");
					total += key.getPrecio();
				}
			}
		}
		return total;
	}

	public Integer getCantidadDeVendedoresRegistrados() {
		return this.vendedores.size();
	}

	public Integer getCantidadDeVentas() {
		return this.ventas.size();
	}

	public Integer getCantidadDeClientesRegistrados() {
		return this.clientes.size();
	}

	public Integer getCantidadDeProductosAgregadosAStock() {
		return this.stock.size();
	}

	public Integer getCantidadDeServiciosAgregados() {
		Integer contador = 0;
		for (Vendible vendible : vendibles) {
			if (vendible instanceof Servicio) {
				contador++;
			}
		}
		return contador;
	}

	public Venta getVentaPorCodigo(String codigoVenta) throws VentaInexistenteException {
		for (Venta venta : ventas) {
			if (venta.getCodigo().equals(codigoVenta)) {
				return venta;
			}
		}
		throw new VentaInexistenteException("Ventanexistente");
	}

	public Boolean saberSiHayStockDeUnProducto(Integer codProd) throws StockInsuficienteException {
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if (key.getCodigo().equals(codProd)) {
				if (val > 0) {
					return true;
				}
			}

		}
		throw new StockInsuficienteException("No hay stock capo");
	}

	public void restarStock(Producto producto, Integer decremento) {
		// TODO: se debe agregar stock a un producto existente
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if (key.getCodigo().equals(producto.getCodigo())) {
				entry.setValue(val - decremento);
			}

		}
	}

	public Integer getStockDeUnProducto(Producto producto) {
		// TODO: se debe agregar stock a un producto existente
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if (key.getCodigo().equals(producto.getCodigo())) {
				return val;
			}

		}
		return 0;
	}

}
