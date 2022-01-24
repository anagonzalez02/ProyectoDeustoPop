package ClasesTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.CuentaBancaria;

public class CuentaBancariaTest {
	
	CuentaBancaria cuenta;
	
	@Before
	public void iniciar() {
		cuenta = new CuentaBancaria(9876543, 88);
	}
	
	/**
	 * Prueba el método getnTarjeta de CuentaBancaria.
	 * **/
	@Test
	public void testGetnTarjeta() {
		assertEquals(cuenta.getnTarjeta(), 9876543);
	}
	
	/**
	 * Prueba el método setnTarjeta de CuentaBancaria.
	 * **/
	@Test
	public void testSetnTarjeta() {
		cuenta.setnTarjeta(3456178);
		assertTrue(cuenta.getnTarjeta() == 3456178);
	}
	
	/**
	 * Prueba el método getDineroTotal de CuentaBancaria.
	 * **/
	@Test
	public void testGetDineroTotal() {
		assertTrue(cuenta.getDineroTotal() == 88);
	}
	
	/**
	 * Prueba el método setDineroTotal de CuentaBancaria.
	 * **/
	@Test
	public void testSetDineroTotal() {
		cuenta.setDineroTotal(44);
		assertTrue(cuenta.getDineroTotal() == 44);
	}

	/**
	 * Prueba el método toString de CuentaBancaria.
	 * **/
	@Test
	public void testToString() {
		String string = "CuentaBancaria [nTarjeta=" + cuenta.getnTarjeta() + ", dineroTotal=" + cuenta.getDineroTotal() + "]";
		assertEquals(string, cuenta.toString());
	}
	
}
