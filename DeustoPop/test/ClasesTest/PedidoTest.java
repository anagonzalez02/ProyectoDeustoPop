package ClasesTest;

import static org.junit.Assert.*;

import java.awt.Image;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import clases.Colores;
import clases.CuentaBancaria;
import clases.Estado;
import clases.Lugar;
import clases.Pedido;
import clases.Producto;
import clases.Usuario;

public class PedidoTest {
	
	/**
	 * Se crea el usuario vendedor para así crear el producto del pedido.
	 * Además de crear el usuario comprador y el producto.
	 * **/
	
	private static final String Image = null;
	Usuario uVendedor = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
	Usuario uComprador = new Usuario ("peeepiitaa", 611111111, new CuentaBancaria(8727136, 5252.00), "pepa@email.com", "jeje", new Lugar("Gran Via 54", "Bilbao", "Españita"));
	Usuario uComprador2 = new Usuario ("luukeee", 622222222, new CuentaBancaria(7377383, 34.00), "luke@email.com", "contra", new Lugar("Oh la la", "Paris", "Francia"));
	
	Producto producto = new Producto ("Zapatilla guay", "Cool", 10.65, Image, Estado.MALO, Colores.Azul, uVendedor);
	Producto producto2 = new Producto ("Camiseta", "Vintage", 22.50, Image, Estado.BUENO, Colores.Multicolor, uVendedor);
	
	//private java.util.Date date1 = Calendar.getInstance().getTime();
	//private java.util.Date date2 = Calendar.getInstance().getTime();

	
	
	Pedido pedido = new Pedido(uComprador, producto);
	//Pedido pedido2 = new Pedido(14.55, date1, date2, 1, uComprador, producto);
	
	
	/**
	 * Prueba la función sumarDiasFecha.
	 * Se suman 7 días porque el usuario vendedor y el comprador viven en el mismo país, pero en diferente ciudad.
	 * **/
	
	@Test
	public void testSumarDiasFecha() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pedido.getFechaCompra());
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		assertEquals((Date) calendar.getTime(), pedido.getFechaEntrega());
	}
	
	@Test
	public void testGetPrecioTotal() {
		assertTrue(14.55 - pedido.getPrecioTotal() == 0);
	}
	
	@Test
	public void testGetFechaCompra() {
		
	}
	
	@Test
	public void testGetFechaEntrega() {
		Date fechaEntrega = Pedido.sumarDiasFecha(pedido.getFechaCompra());
		assertEquals(fechaEntrega, pedido.getFechaEntrega());
	}
	
	@Test
	public void testGetNumeroPedido() {
		
	}
	
	@Test
	public void testSetNumeroPedido() {
		
	}
	
	@Test
	public void testGetUsuarioComprador() {
		assertEquals(uComprador, pedido.getUsuarioComprador());
	}
	
	@Test
	public void testSetUsuarioComprador() {
		pedido.setUsuarioComprador(uComprador2);
		assertEquals(uComprador2, pedido.getUsuarioComprador());
	}

	@Test
	public void testGetProductoComprado() {
		assertEquals(producto, pedido.getProductoComprado());
	}
	
	@Test
	public void testSetProductoComprado() {
		pedido.setProductoComprado(producto2);
		assertEquals(producto2, pedido.getProductoComprado());
	}
	
	@Test
	public void testToString() {
		String string = "Pedido [precioTotal=" + pedido.getPrecioTotal() + ", fechaCompra=" + pedido.getFechaCompra()
				+ ", fechaEntrega=" + pedido.getFechaEntrega() + ", numeroPedido=" + pedido.getNumeroPedido() + ", usuarioComprador="
				+ pedido.getUsuarioComprador() + ", productoComprado=" + pedido.getProductoComprado() + "]";
		assertEquals(string, pedido.toString());
	}

}
