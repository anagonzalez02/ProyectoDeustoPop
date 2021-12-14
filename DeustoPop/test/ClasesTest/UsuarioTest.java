package ClasesTest;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Test;

import clases.Colores;
import clases.Estado;
import clases.Lugar;
import clases.Producto;
import clases.Usuario;

public class UsuarioTest {

	private static final Image Image = null;
	Usuario uVendedor = new Usuario ("peepee", 600000000, 8727193, "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
	
	Producto producto = new Producto ("Zapatilla guay", "Cool", 10.65, Image, Estado.MALO, Colores.Azul, uVendedor);
	
	//test de getters de usuario
	
	
	@Test
	public void testGetNombre() {
		assertEquals("peepee", uVendedor.getNombre());
	}
	
	
	@Test
	public void testGetTelefono() {
		assertEquals(600000000, uVendedor.getTelefono());
	}
	
	
	@Test
	public void testGetTarjeta() {
		assertEquals(8727193, uVendedor.getTarjeta());
	}
	
	
	@Test
	public void testGetEmail() {
		assertEquals("pepeee@email.com", uVendedor.getEmail());
	}
	
	
	
	@Test
	public void testGetContrasenia() {
		assertEquals("contrasenya", uVendedor.getContrasenia());
	}
	
	
	
	//test de getters de usuario
	
	
	@Test
	public void testSetNombre() {
		uVendedor.setNombre("emilia");
		assertEquals("emilia", uVendedor.getNombre());
	}
	
	@Test
	public void testSetTelefono() {
		uVendedor.setTelefono(600000001);
		assertEquals(600000001, uVendedor.getTelefono());
	}

	@Test
	public void testSetTarjeta() {
		uVendedor.setTarjeta(1234567);
		assertEquals(1234567, uVendedor.getTarjeta());
	}
	
	@Test
	public void testSetEmail() {
		uVendedor.setEmail("lalisa@email.com");
		assertEquals("lalisa@email.com", uVendedor.getEmail());
	}
	
	@Test
	public void testSetContrasenia() {
		uVendedor.setContrasenia("1dos3cuatro");
		assertEquals("1dos3cuatro", uVendedor.getContrasenia());
	}
}
