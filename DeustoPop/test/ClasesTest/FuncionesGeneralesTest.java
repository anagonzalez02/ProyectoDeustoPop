package ClasesTest;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BaseDatos.BaseDeDatos;
import clases.Colores;
import clases.CuentaBancaria;
import clases.Estado;
import clases.FuncionesGenerales;
import clases.Lugar;
import clases.Pedido;
import clases.Producto;
import clases.Usuario;

public class FuncionesGeneralesTest {
	
	Image image;
	Usuario uComprador1;
	Usuario uComprador2;
	Usuario uVendedor;
	Producto producto;
	
	@Before
	public void iniciar () {
		image = null;
		uComprador1 = new Usuario ("peeepiitaa", 611111111, new CuentaBancaria(63527191, 3), 73, "pepa@email.com", "jeje", new Lugar("Gran Via 54", "Bilbao", "Españita"));
		uComprador2 = new Usuario ("anitaaa", 622222222, new CuentaBancaria(9662829, 65), 6, "anita@email.com", "jujuu", new Lugar("Gran Via 54", "Bilbao", "Españita"));
		uVendedor = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 625.50), 15, "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Españita"));
		producto = new Producto ("Zapatilla guay", "Cool", 10.67, image, Estado.MALO, Colores.Azul, uVendedor);
		
		if (new File("deustopop.bd").exists()) {
			// Poner el parámetro a true si se quiere reiniciar la base de datos
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", false );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Abrir base de datos existente
		} else {
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", true );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Crear base de datos con datos iniciales
		}
	}
	
	
	// REVISAR
	// Error por la importación de Date, tiene que ser sql.Date pero no veo dónde no está así
	@Test
	public void testMetodoComprarProducto () {
		FuncionesGenerales.metodoComprarProducto(producto, uComprador1);
		Pedido pedido = BaseDeDatos.getPedidoProducto(producto.getId());
		
		assertFalse(producto.isEnVenta());
		assertTrue(uVendedor.getSaldo() == 25.67);
		assertTrue(uComprador1.getSaldo() == 62.33);
		assertEquals(pedido.getUsuarioComprador(), uComprador1);
		assertEquals(pedido.getProductoComprado(), producto);
	}
	
	
	/**
	 *  Restaremos el dinero del comprador 1. Al tener un saldo mayor al del precio del producto, el dinero se descontará del saldo de DeustoPop.
	 **/
	@Test
	public void testRestarDinero1 () {
		double saldoAntes = uComprador1.getSaldo();							// El saldo de antes era de 73€
		double dineroAntes = uComprador1.getCuentaB().getDineroTotal();		// El dinero de la cuenta antes era de 3€
		FuncionesGenerales.restarDinero(producto, uComprador1);
		double saldoDespues = uComprador1.getSaldo();
		double dineroDespues = uComprador1.getCuentaB().getDineroTotal();
		// Al tener saldo suficiente para pagar el producto, se le descontará el precio desde ahí directamente. Sin tener que recurrir a la cuenta bancaria.
		// El saldo final será 73€ - 10.67€ = 62.33€
		

		assertTrue(saldoAntes - producto.getPrecio() == saldoDespues);
		assertTrue(saldoDespues == 62.33);
		assertTrue(dineroAntes == dineroDespues);
		assertTrue(dineroDespues == 3);
	}
	
	
	// Restaremos el dinero del comprador 2. Al tener un saldo menor al del precio del producto, el dinero se descontará de la cuenta bancaria.
	@Test
	public void testRestarDinero2 () {
		double saldoAntes = uComprador2.getSaldo();							// El saldo antes era de 6€
		double dineroAntes = uComprador2.getCuentaB().getDineroTotal();		// El dinero de la cuenta antes era de 65€
		FuncionesGenerales.restarDinero(producto, uComprador2);
		double saldoDespues = uComprador2.getSaldo();
		double dineroDespues = uComprador2.getCuentaB().getDineroTotal();
		// Al no tener saldo suficiente para pagar el producto, se le descontará el precio desde la cuenta bancaria
		// El dinero de la cuenta será 65€ - 10.67€ = 54.33€
		
		assertTrue(saldoAntes == saldoDespues);
		assertTrue(saldoDespues == 6);
		assertTrue(dineroAntes - producto.getPrecio() == dineroDespues);
		assertTrue(dineroDespues == 54.33);
		assertFalse(dineroAntes == dineroDespues + producto.getPrecio());
	}
	
	
	@Test
	public void testSumarDinero () {
		double dineroAntes = uVendedor.getSaldo();
		FuncionesGenerales.sumarDinero(producto);
		double dineroDespues = uVendedor.getSaldo();
		
		assertTrue(dineroAntes == dineroDespues - producto.getPrecio());
	}
	
	
	@Test
	public void testBuscarProductosRecomendadosSaldo () {
		
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
	
	@After
	public void testfinal () {
		BaseDeDatos.cerrarConexion();
	}
	
}
