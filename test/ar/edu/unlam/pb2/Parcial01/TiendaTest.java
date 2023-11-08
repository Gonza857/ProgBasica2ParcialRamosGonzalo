package ar.edu.unlam.pb2.Parcial01;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TiendaTest {

	/**
	 * Resolver los siguientes tests
	 * 
	 * @throws VendedorDeLicenciaException
	 * @throws StockInsuficienteException
	 */

	@Test(expected = VendedorDeLicenciaException.class)
	public void queAlIntentarAgregarUnaVentaParaUnVendedorDeLicenciaSeLanceUnaVendedorDeLicenciaException()
			throws VendedorDeLicenciaException, StockInsuficienteException {
		// creamos tienda y vendedor
		Tienda tienda = new Tienda("20120008883", "Tiendita");
		Vendedor vendedor1 = new Vendedor("Gonzalo", "12123123");
		tienda.agregarVendedor(vendedor1);
		vendedor1.setDeLicencia(true); // seteamos a licencia
		
		// test de si se agregó
		Integer vo = tienda.getCantidadDeVendedoresRegistrados();
		Integer ve = 1;
		assertEquals(ve, vo);

		// agregamos cliente y testeamos su existencia
		Cliente cliente1 = new Cliente("20998887770", "Cualquiera");
		tienda.agregarCliente(cliente1);
		vo = tienda.getCantidadDeClientesRegistrados();
		assertEquals(ve, vo);

		// creamos producto y agregamos
		Producto producto1 = new Producto(0, "Samsung S23+", 700000.0, 5);
		tienda.agregarProducto(producto1, 10);

		// creamos venta y agregamos producto
		Venta v1 = new Venta("0", cliente1, vendedor1);
		v1.agregarRenglon(producto1, 10);
		
		// generamos venta
		tienda.agregarVenta(v1);

	}

	@Test(expected = VendibleInexistenteException.class)
	public void queAlIntentarAgregarUnVendibleInexistenteAUnaVentaSeLanceUnaVendibleInexistenteException()
			throws VendedorDeLicenciaException, VentaInexistenteException, VendibleInexistenteException,
			StockInsuficienteException {
		// instanciamos tienda y agregamos vendedor, tambien verificamos
		Tienda tienda = new Tienda("20120008883", "Tiendita");
		Vendedor vendedor1 = new Vendedor("Gonzalo", "12123123");
		tienda.agregarVendedor(vendedor1);
		Integer vo = tienda.getCantidadDeVendedoresRegistrados();
		Integer ve = 1;
		assertEquals(ve, vo);

		// instanciamos cliente, agregamos y verificamos
		Cliente cliente1 = new Cliente("20998887770", "Cualquiera");
		tienda.agregarCliente(cliente1);
		vo = tienda.getCantidadDeClientesRegistrados();
		assertEquals(ve, vo);

		// instanciamos agregamos producto y verificamos
		Producto producto1 = new Producto(0, "Samsung S23+", 700000.0, 5);
		tienda.agregarProducto(producto1, 10);
		tienda.agregarStock(producto1, 10);

		// instanciamos producto pero no se agrega
		Producto producto2 = new Producto(1, "Notebook Asus I7", 450000.0, 10);

		// instanciamos venta y agregamos
		Venta v1 = new Venta("0", cliente1, vendedor1);
		tienda.agregarVenta(v1);

		// agregamos producto
		tienda.agregarProductoAVenta("0", producto1);
		// este producto lanza excepcion
		tienda.agregarProductoAVenta("0", producto2);
	}

	@Test
	public void queSePuedaObtenerUnaListaDeProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion()
			throws VentaInexistenteException, VendibleInexistenteException, VendedorDeLicenciaException,
			StockInsuficienteException {
		Tienda tienda = new Tienda("20120008883", "Tiendita");
		Vendedor vendedor1 = new Vendedor("Gonzalo", "12123123");
		tienda.agregarVendedor(vendedor1);
		Integer vo = tienda.getCantidadDeVendedoresRegistrados();
		Integer ve = 1;
		assertEquals(ve, vo);

		Cliente cliente1 = new Cliente("20998887770", "Cualquiera");
		tienda.agregarCliente(cliente1);
		vo = tienda.getCantidadDeClientesRegistrados();
		assertEquals(ve, vo);

		Producto producto1 = new Producto(0, "Samsung S23+", 700000.0, 5);
		tienda.agregarProducto(producto1, 6);

		Venta v1 = new Venta("0", cliente1, vendedor1);
		tienda.agregarVenta(v1);

		tienda.agregarProductoAVenta("0", producto1);

		assertEquals(ve, tienda.obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion().size(), 0.0);

		ve = 2;

		Producto producto2 = new Producto(1, "Notebook Asus I7", 450000.0, 4);
		tienda.agregarProducto(producto2, 5);

		tienda.agregarProductoAVenta("0", producto2);

		assertEquals(ve, tienda.obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion().size(), 0.0);

	}

	@Test
	public void queSePuedaObtenerUnaListaDeClientesOrdenadosPorRazonSocialDescendente() {
		Tienda tienda = new Tienda("20120008883", "Tiendita");
		Cliente cliente1 = new Cliente("20998887771", "Cualquiera");
		Cliente cliente2 = new Cliente("20998887772", "BCualquiera");
		Cliente cliente3 = new Cliente("20998887773", "ACualquiera");
		tienda.agregarCliente(cliente1);
		tienda.agregarCliente(cliente2);
		tienda.agregarCliente(cliente3);

		Integer i = 0;
		for (Cliente cliente : tienda.obtenerClientesOrdenadosPorRazonSocialDescendente()) {
			System.out.println(cliente.getRazonSocial());
			switch (i) {
			case 0:
				assertEquals("ACualquiera", cliente.getRazonSocial());
				i++;
				break;
			case 1:
				assertEquals("BCualquiera", cliente.getRazonSocial());
				i++;
				break;
			case 2:
				assertEquals("Cualquiera", cliente.getRazonSocial());
				i++;
				break;
			}
		}
	}

	@Test
	public void queSePuedaObtenerUnMapaDeVentasRealizadasPorCadaVendedor() throws VentaInexistenteException,
			VendibleInexistenteException, VendedorDeLicenciaException, StockInsuficienteException {
		// TODO: usar como key el vendedor y Set<Venta> para las ventas
		Tienda tienda = new Tienda("20120008883", "Tiendita");

		Vendedor vendedor1 = new Vendedor("Gonzalo", "12123123");
		tienda.agregarVendedor(vendedor1);

		Vendedor vendedor2 = new Vendedor("Leonel", "40123000");
		tienda.agregarVendedor(vendedor2);

		Cliente cliente1 = new Cliente("20998887770", "Cualquiera");
		tienda.agregarCliente(cliente1);

		Producto producto1 = new Producto(0, "Samsung S23+", 700000.0, 5);
		tienda.agregarProducto(producto1, 6);

		Producto producto2 = new Producto(1, "Notebook Asus I7", 450000.0, 4);
		tienda.agregarProducto(producto2, 5);

		Venta v1 = new Venta("0", cliente1, vendedor1);
		tienda.agregarVenta(v1);
		tienda.agregarProductoAVenta("0", producto1);

		Venta v2 = new Venta("1", cliente1, vendedor1);
		tienda.agregarVenta(v2);
		tienda.agregarProductoAVenta("1", producto1);
		tienda.agregarProductoAVenta("1", producto2);

		Venta v3 = new Venta("0", cliente1, vendedor2);
		tienda.agregarVenta(v3);
		tienda.agregarProductoAVenta("1", producto1);
		tienda.agregarProductoAVenta("1", producto1);
		tienda.agregarProductoAVenta("1", producto1);
		tienda.agregarProductoAVenta("1", producto1);

		Integer ve = 3;
		Integer vo = tienda.getCantidadDeVentas();
		assertEquals(ve, vo);

		Integer i = 0;
		for (Map.Entry<Vendedor, Set<Venta>> entry : tienda.obtenerVentasPorVendedor().entrySet()) {
			Vendedor key = entry.getKey();
			Set<Venta> val = entry.getValue();
			System.out.println(key.getDni() + " | " + val.size());

			switch (i) {
			case 0:
				assertEquals(key.getDni(), vendedor1.getDni());
				assertEquals(val.size(), 2);
				i++;
				break;
			case 1:
				assertEquals(key.getDni(), vendedor2.getDni());
				assertEquals(val.size(), 1);
				i++;
				break;
			}
		}
	}

	@Test
	public void queSePuedaObtenerElTotalDeVentasDeServicios()
			throws VentaInexistenteException, VendibleInexistenteException, VendedorDeLicenciaException {
		Tienda tienda = new Tienda("20120008883", "Tiendita");
		Vendedor vendedor1 = new Vendedor("Gonzalo", "12123123");
		tienda.agregarVendedor(vendedor1);

		Servicio servicio1 = new Servicio(0, "Reparar celu", 1000.0, "20200101", "20200102");
		tienda.agregarServicio(servicio1);

		Servicio servicio2 = new Servicio(1, "Reparar ventilador", 1000.0, "20200102", "20200103");
		tienda.agregarServicio(servicio2);

		Integer vo = tienda.getCantidadDeServiciosAgregados();
		Integer ve = 2;
		assertEquals(ve, vo);

		Cliente cliente1 = new Cliente("20998887770", "Cualquiera");
		tienda.agregarCliente(cliente1);

		vo = tienda.getCantidadDeClientesRegistrados();
		ve = 1;
		assertEquals(ve, vo);

		Venta v1 = new Venta("0", cliente1, vendedor1);
		tienda.agregarVenta(v1);
		tienda.agregarServicioAVenta("0", servicio1);

		Venta v2 = new Venta("1", cliente1, vendedor1);
		tienda.agregarVenta(v2);
		tienda.agregarServicioAVenta("1", servicio2);

		vo = tienda.getCantidadDeVentas();
		ve = 2;
		assertEquals(ve, vo);

		Double totalEsperado = 2000.0;
		Double totalObtenido = tienda.obtenerTotalDeVentasDeServicios();
		assertEquals(totalEsperado, totalObtenido);

	}

	@Test
	public void queAlRealizarLaVentaDeUnProductoElStockSeActualiceCorrectamente() throws VendedorDeLicenciaException, VentaInexistenteException, VendibleInexistenteException, StockInsuficienteException {
		// TODO: usar como key el vendedor y Set<Venta> para las ventas
		Tienda tienda = new Tienda("20120008883", "Tiendita");

		Vendedor vendedor1 = new Vendedor("Gonzalo", "12123123");
		tienda.agregarVendedor(vendedor1);

		Cliente cliente1 = new Cliente("20998887770", "Cualquiera");
		tienda.agregarCliente(cliente1);

		Producto producto1 = new Producto(0, "Samsung S23+", 700000.0, 5);
		tienda.agregarProducto(producto1, 7);

		Integer ve = 7;
		Integer vo = tienda.getStockDeUnProducto(producto1);
		assertEquals(ve, vo);

		Venta v1 = new Venta("0", cliente1, vendedor1);
		tienda.agregarVenta(v1);
		tienda.agregarProductoAVenta("0", producto1);

		ve = 6;
		vo = tienda.getStockDeUnProducto(producto1);
		assertEquals(ve, vo);
	}

	// TEST AGREGADOS
	@Test(expected = StockInsuficienteException.class)
	public void queAlNoHaberStockSuficienteLanceStockInsuficienteException() throws VentaInexistenteException,
			VendibleInexistenteException, VendedorDeLicenciaException, StockInsuficienteException {
		Tienda tienda = new Tienda("20120008883", "Tiendita");
		Vendedor vendedor1 = new Vendedor("Gonzalo", "12123123");
		tienda.agregarVendedor(vendedor1);

		Cliente cliente1 = new Cliente("20998887770", "Cualquiera");
		tienda.agregarCliente(cliente1);

		Producto producto1 = new Producto(0, "Samsung S23+", 700000.0, 0);
		tienda.agregarProducto(producto1, 1);

		Venta v1 = new Venta("0", cliente1, vendedor1);
		tienda.agregarVenta(v1);
		tienda.agregarProductoAVenta("0", producto1);

		Venta v2 = new Venta("1", cliente1, vendedor1);
		tienda.agregarVenta(v2);
		tienda.agregarProductoAVenta("0", producto1);

	}
}
