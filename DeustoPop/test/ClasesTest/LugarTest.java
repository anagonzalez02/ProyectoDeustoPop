package ClasesTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Lugar;

public class LugarTest {
	
	Lugar lugar;
	
	@Before
	public void iniciar() {
		lugar = new Lugar("Una calle", "NYC", "USA");
	}

	/**
	 * Prueba el método getDireccion de Lugar.
	 * **/
	@Test
	public void testGetDireccion() {
		assertEquals("Una calle", lugar.getDireccion());
	}
	
	/**
	 * Prueba el método setDireccion de Lugar.
	 * **/
	@Test
	public void testSetDireccion() {
		lugar.setDireccion("Otra calle");
		assertEquals("Otra calle", lugar.getDireccion());
	}
	
	/**
	 * Prueba el método getNomCiudn de Lugar.
	 * **/
	@Test
	public void testGetNomCiud() {
		assertEquals("NYC", lugar.getNomCiud());
	}
	
	/**
	 * Prueba el método setNomCiud de Lugar.
	 * **/
	@Test
	public void testSetNomCiud() {
		lugar.setNomCiud("Miami");
		assertEquals("Miami", lugar.getNomCiud());
	}
	
	/**
	 * Prueba el método getNomPais de Lugar.
	 * **/
	@Test
	public void testGetNomPais() {
		assertEquals("USA", lugar.getNomPais());
	}
	
	/**
	 * Prueba el método setNomPais de Lugar.
	 * **/
	@Test
	public void testSetNomPais() {
		lugar.setDireccion("Paris");
		assertEquals("Paris", lugar.getDireccion());
	}
	
	/**
	 * Prueba el método toString de Lugar.
	 * **/
	@Test
	public void testToString() {
		String string = "Lugar [direccion=" + lugar.getDireccion() + ", nomCiud=" + lugar.getNomCiud() + ", nomPais=" + lugar.getNomPais() + "]";
		assertEquals(string, lugar.toString());
	}

}
