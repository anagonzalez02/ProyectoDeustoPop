package ClasesTest;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Test;

import clases.Colores;
import clases.CuentaBancaria;
import clases.Estado;
import clases.Lugar;
import clases.Producto;
import clases.Usuario;

public class ProductoTest {
	
	/**
	 * Crearemos dos usuarios vendedores y un producto, que nos ayudarán con las pruebas unitarias. Usaremos estos ejemplos para verificar que las funciones funcionan
	 * Hemos creado un producto, cuyo usuario vendedor es el uVendedor
	 * Al igual que hemos creado otro usuario, que lo utilizaremos posteriormente
	 * **/
	
	private static final Image Image = null;
	Usuario uVendedor = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
	Usuario uVendedor2 = new Usuario ("peeepiitaa", 611111111, new CuentaBancaria(8727136, 5252.00), "pepa@email.com", "jeje", new Lugar("Gran Via 54", "Bilbao", "Españita"));
	
	Producto producto = new Producto ("Zapatilla guay", "Cool", 10.65, Image, Estado.MALO, Colores.Azul, uVendedor);
	
	
	/**
	 * El método getNombre devuelve el nombre registrado del producto. Por ello lo compararemos con su nombre.
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testGetNombre() {
		assertEquals("Zapatilla guay", producto.getNombre());
	}
	
	/**
	 * El método getEtiquetas devuelve las etiquetas registradas del producto. Por ello lo compararemos con sus etiquetas.
	 * De ser las mismas, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testGetEtiquetas() {
		assertEquals("Cool", producto.getEtiquetas());
	}
	
	/**
	 * El método getPrecio devuelve el precio registrado del producto. Por ello lo compararemos con su precio.
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	//@Test
	//public void testGetPrecio() {
	//	assertEquals(10.65, producto.getPrecio());
	//}
	
	/**
	 * El método getEstado devuelve el estado registrado del producto, uno de los de la enumeracion Estado. Por ello lo compararemos con su estado.
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testGetEstado() {
		assertEquals(Estado.MALO, producto.getEstado());
	}
	
	/**
	 * El método getColor devuelve el color registrado del producto, uno de los estados de la enumeración Color. Por ello lo compararemos con su color.
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testGetColor() {
		assertEquals(Colores.Azul, producto.getColor());
	}
	
	/**
	 * El método getUsuario devuelve el usuario registrado del producto. Por ello lo compararemos con su usuario, uVendedor.
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testGetUsuario() {
		assertEquals(uVendedor, producto.getUsuario());
	}
	
	/**
	 * El método isVenta devuelve un booleano en caso de estar en venta el producto. Por ello lo compararemos con un TRUE, con el assertTrue, ya que hemos definido así el producto.
	 * De ser true, la prueba nos dará correcta, en caso de ser false, será rojo.
	 * **/
	
	@Test
	public void testIsEnVenta() {
		assertTrue(producto.isEnVenta());
	}
	
	/**
	 * El método setNombre inicializa el nombre del producto. 
	 * Por ello lo renombraremos y lo compararemos con este nuevo nombre, mediante el getNombre().
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testSetNombre() {
		producto.setNombre("Zapatillita");
		assertEquals("Zapatillita", producto.getNombre());
	}
	
	/**
	 * El método setEtiquetas inicializa las etiquetas del producto. 
	 * Por ello pondremos como ejemplo de etiquetas "Vintage" y lo compararemos con las etiquetas actuales mediante el getEtiquetas().
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testSetEtiquetas() {
		producto.setEtiquetas("Vintage");
		assertEquals("Vintage", producto.getEtiquetas());
	}
	
	/**
	 * El método setPrecio inicializa el precio del producto. 
	 * Por ello le pondremos un precio total de 66.54€ y lo compararemos con el precio del producto ya actualizado, mediante el getPrecio().
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	//@Test
	//public void testSetPrecio() {
	//	producto.setPrecio(66.54);
	//	assertEquals(66.54, producto.getPrecio());
	//}
	
	/**
	 * El método setEstado inicializa el estado del producto. 
	 * Para comprobarlo, le pondremos un estado bueno, y lo compararemos con el estado del producto mediante el getEstado().
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testSetEstado() {
		producto.setEstado(Estado.BUENO);
		assertEquals(Estado.BUENO, producto.getEstado());
	}
	
	/**
	 * El método setColor inicializa el color físico del producto. 
	 * Por ello lo veremos como amarillo y lo compararemos con el nuevo color ya actualizado, mediante el getColor().
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testSetColor() {
		producto.setColor(Colores.Amarillo);
		assertEquals(Colores.Amarillo, producto.getColor());
	}
	
	/**
	 * El método setUsuario inicializa el usuario del producto. 
	 * Cambiaremos el usuariio vendedor por uno nuevo (uVendedor2) y veremos si se ha ejecutado biene l cambio mediante getUsuario().
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testSetUsuario() {
		producto.setUsuario(uVendedor2);
		assertEquals(uVendedor2, producto.getUsuario());
	}
	
	/**
	 * El método setEnVenta pone en venta el producto o lo quita del mercado. 
	 * Por ello lo pondremos a false (no está en venta) y lo compararemos con el enVenta mediante isEnVenta().
	 * De ser el mismo, la prueba nos dará correcta, en caso incorrecto, será rojo.
	 * **/
	
	@Test
	public void testSetEnVenta() {
		producto.setEnVenta(false);
		assertFalse(producto.isEnVenta());
	}
	
}
