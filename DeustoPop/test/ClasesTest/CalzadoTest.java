package ClasesTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Calzado;
import clases.Colores;
import clases.CuentaBancaria;
import clases.Estado;
import clases.Lugar;
import clases.Usuario;

public class CalzadoTest {
	
	Calzado calzado;
	
	@Before
	public void iniciar() {
		Usuario uVendedor = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		calzado = new Calzado("nombre", "vintage", 50.45, "ruta", Estado.BUENO, Colores.Azul, uVendedor, 39);
	}

	/**
	 * Prueba el método getTallaCalzado de Calzado
	 * **/
	@Test
	public void testGetTallaCalzado() {
		assertTrue(39 == calzado.getTallaCalzado());
	}
	
	/**
	 * Prueba el método setTallaCalzado de Calzado
	 * **/
	@Test
	public void testSetTallaCalzado() {
		calzado.setTallaCalzado(41);
		assertTrue(41 == calzado.getTallaCalzado());
	}

}
