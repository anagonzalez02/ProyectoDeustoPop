package ClasesTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Clases.FuncionesGenerales;

public class FuncionesGeneralesTest {

	// Esto es un buen ejemplo para ver como la clave introducida que pasa a ser codificada por el metodo code y guardada de esa manera en la base de datos
	// Al descodificarla, nos devuelve la misma clave introducida en un principio sin errores
	
	@Test
	public void code() {
		assertEquals("defg", FuncionesGenerales.code("abcd"));
		assertEquals("6935", FuncionesGenerales.code("3602"));
		assertEquals("Kh|#:6q", FuncionesGenerales.code("Hey 73n"));
		assertEquals("D:#Je", FuncionesGenerales.code("A7 Gb"));
	}
	
	@Test
	public void decode() {
		assertEquals("abcd", FuncionesGenerales.decode("defg"));
		assertEquals("3602", FuncionesGenerales.decode("6935"));
		assertEquals("Hey 73n", FuncionesGenerales.decode("Kh|#:6q"));
		assertEquals("A7 Gb", FuncionesGenerales.decode("D:#Je"));
	}
	
	
	
	
}
