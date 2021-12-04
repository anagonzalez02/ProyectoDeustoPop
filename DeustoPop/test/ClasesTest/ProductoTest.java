package ClasesTest;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Test;

import clases.Lugar;
import clases.Producto;
import clases.Tipo;
import clases.Usuario;

public class ProductoTest {
	
	
	private static final Image Image = null;
	Usuario uVendedor = new Usuario ("peepee", 600000000, 8727193, "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Españita"));
	Producto producto = new Producto ("Zapatilla guay", "Cool", 10.65, Image, new Tipo("Zapatilla", 002), "Nuevo", "Azul", uVendedor);
	
	
	@Test
	public void testGetNombre() {
		assertEquals("Zapatilla guay", producto.getNombre());
	}
	
	@Test
	public void testGetEtiquetas() {
		assertEquals("Cool", producto.getEtiquetas());
	}
	
	/**
	@Test
	public void testGetPrecio() {
		assertEquals(10.65, producto.getPrecio());
	}
	**/
	
	@Test
	public void testGetEstado() {
		assertEquals("Nuevo", producto.getEstado());
	}
	
	@Test
	public void testGetColor() {
		assertEquals("Azul", producto.getColor());
	}
	
	@Test
	public void testGetUsuario() {
		assertEquals(uVendedor, producto.getUsuario());
	}
	
	@Test
	public void testSetNombre() {
		producto.setNombre("Zapatillita");
		assertEquals("Zapatillita", producto.getNombre());
	}
	
}
