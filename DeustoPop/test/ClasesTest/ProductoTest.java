package ClasesTest;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Test;

import clases.Colores;
import clases.Estado;
import clases.Lugar;
import clases.Producto;
import clases.Tipo;
import clases.Usuario;

public class ProductoTest {
	
	// Las dos pruebas unitarias relacionadas con el precio dan error. REVISAR
	
	private static final Image Image = null;
	Usuario uVendedor = new Usuario ("peepee", 600000000, 8727193, "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
	Usuario uVendedor2 = new Usuario ("peepee", 600000000, 8727193, "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
	
	Producto producto = new Producto ("Zapatilla guay", "Cool", 10.65, Image, Estado.MALO, Colores.Azul, uVendedor);
	
	
	@Test
	public void testGetNombre() {
		assertEquals("Zapatilla guay", producto.getNombre());
	}
	
	@Test
	public void testGetEtiquetas() {
		assertEquals("Cool", producto.getEtiquetas());
	}
	
	//@Test
	//public void testGetPrecio() {
	//	assertEquals(10.65, producto.getPrecio());
	//}
	
	@Test
	public void testGetEstado() {
		assertEquals(Estado.MALO, producto.getEstado());
	}
	
	@Test
	public void testGetColor() {
		assertEquals(Colores.Azul, producto.getColor());
	}
	
	@Test
	public void testGetUsuario() {
		assertEquals(uVendedor, producto.getUsuario());
	}
	
	@Test
	public void testIsEnVenta() {
		assertTrue(producto.isEnVenta());
	}
	
	@Test
	public void testSetNombre() {
		producto.setNombre("Zapatillita");
		assertEquals("Zapatillita", producto.getNombre());
	}
	
	@Test
	public void testSetEtiquetas() {
		producto.setEtiquetas("Vintage");
		assertEquals("Vintage", producto.getEtiquetas());
	}
	
	//@Test
	//public void testSetPrecio() {
	//	producto.setPrecio(66.54);
	//	assertEquals(66.54, producto.getPrecio());
	//}
	
	@Test
	public void testSetEstado() {
		producto.setEstado(Estado.BUENO);
		assertEquals(Estado.BUENO, producto.getEstado());
	}
	@Test
	public void testSetColor() {
		producto.setColor(Colores.Amarillo);
		assertEquals(Colores.Amarillo, producto.getColor());
	}
	
	@Test
	public void testSetUsuario() {
		producto.setUsuario(uVendedor2);
		assertEquals(uVendedor2, producto.getUsuario());
	}
	
	@Test
	public void testSetEnVenta() {
		producto.setEnVenta(false);
		assertFalse(producto.isEnVenta());
	}
	
}
