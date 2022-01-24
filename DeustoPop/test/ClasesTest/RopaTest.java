package ClasesTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Colores;
import clases.CuentaBancaria;
import clases.Estado;
import clases.Lugar;
import clases.Ropa;
import clases.TallasRopa;
import clases.Usuario;

public class RopaTest {
	
	Ropa ropa;
	
	@Before
	public void iniciar() {
		Usuario uVendedor = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		ropa = new Ropa("nombre", "vintage", 50.45, "ruta", Estado.BUENO, Colores.Azul, uVendedor, TallasRopa.M);
	}

	/**
	 * Prueba el método getTallaRopa de Ropa
	 * **/
	@Test
	public void testGetTallaCalzado() {
		assertEquals(TallasRopa.M, ropa.getTallaRopa());
	}
	
	/**
	 * Prueba el método setTallaRopa de Ropa
	 * **/
	@Test
	public void testSetTallaCalzado() {
		ropa.setTallaRopa(TallasRopa.XS);
		assertEquals(TallasRopa.XS, ropa.getTallaRopa());
	}

}
