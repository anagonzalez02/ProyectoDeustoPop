package ClasesTest;

import static org.junit.Assert.*;

import java.awt.Image;
import java.util.Calendar;

import org.junit.Test;

import clases.Colores;
import clases.Estado;
import clases.FuncionesGenerales;
import clases.Lugar;
import clases.Producto;
import clases.Tipo;
import clases.Usuario;

public class FuncionesGeneralesTest {
	
	
	private static final Image Image = null;
	Usuario uComprador = new Usuario ("peeepiitaa", 611111111, 63527191, "pepa@email.com", "jeje", new Lugar("Gran Via 54", "Bilbao", "Españita"));
	Usuario uVendedor = new Usuario ("peepee", 600000000, 8727193, "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Españita"));
	Producto producto = new Producto ("Zapatilla guay", "Cool", 10.67, Image, new Tipo("Zapatilla", 002), Estado.MALO, Colores.Azul, uVendedor);
	
	// RestarDinero da error por la condicional de si restar dinero al saldo o a la tarjeta directamente
	@Test
	public void testRestarDinero () {
		double dineroAntes = uComprador.getSaldo();
		FuncionesGenerales.restarDinero(producto, uComprador);
		double dineroDespues = uComprador.getSaldo();
		
		assertTrue(dineroAntes == dineroDespues + producto.getPrecio());
	}
	
	
	@Test
	public void testSumarDinero () {
		double dineroAntes = uVendedor.getSaldo();
		FuncionesGenerales.sumarDinero(producto);
		double dineroDespues = uVendedor.getSaldo();
		
		assertTrue(dineroAntes == dineroDespues - producto.getPrecio());
	}
	
	

	// Esto es un buen ejemplo para ver como la clave introducida que pasa a ser codificada por el metodo code y guardada de esa manera en la base de datos
	// Al descodificarla, nos devuelve la misma clave introducida en un principio sin errores
	
	
	@Test
	public void testCode() {
		assertEquals("defg", FuncionesGenerales.code("abcd"));
		assertEquals("6935", FuncionesGenerales.code("3602"));
		assertEquals("Kh|#:6q", FuncionesGenerales.code("Hey 73n"));
		assertEquals("D:#Je", FuncionesGenerales.code("A7 Gb"));
	}
	
	
	@Test
	public void testDecode() {
		assertEquals("abcd", FuncionesGenerales.decode("defg"));
		assertEquals("3602", FuncionesGenerales.decode("6935"));
		assertEquals("Hey 73n", FuncionesGenerales.decode("Kh|#:6q"));
		assertEquals("A7 Gb", FuncionesGenerales.decode("D:#Je"));
	}
	
	
	
}
