package clases;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Esta es la clase Pedido, a la que se recurrirá cada vez que un usuario le compre a otro un producto.
 * Por cada compra, se creará un pedido nuevo, cuyos atributos serán:
 * 		precioTotal -> 			Será el precio del producto más los gastos de envíos (3.90€).
 * 		fechaCompra -> 			La fecha en la que el producto ha sido comprado, el programa será el encargado de coger la fecha actual será el programa.
 * 		fechaEntrega -> 		Una fecha estimada en la que debería llegar el producto comprado, esto variará dependiendo de la distancia entre el vendedor y el comprador.
 * 		numeroPedido -> 		Será el identificativo del pedido, que se creará en la base de datos.
 * 		usuarioCompador -> 		El usuario que compre el producto.
 * 		productoComprado -> 	El producto por el que se hace la compra.
 * 
 * Se registrará un pedido en la base de datos, y podrá accederse a él en caso de haber algún error.
 * **/

public class Pedido { 
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private double precioTotal = 0;
	private Date fechaCompra;
	private Date fechaEntrega;
	private int numeroPedido = 0;
	private static Usuario usuarioComprador;
	private static Producto productoComprado;
	
	

	public Pedido(double precioTotal, Date fechaCompra, Date fechaEntrega, int numeroPedido, Usuario usuarioComprador, Producto productoComprado) {
		super();
		this.precioTotal = productoComprado.getPrecio() + 3.90;
		this.fechaCompra = (Date) Calendar.getInstance().getTime();
		this.fechaEntrega = sumarDiasFecha(fechaCompra);
		this.numeroPedido = numeroPedido;
		this.usuarioComprador = usuarioComprador;
		this.productoComprado = productoComprado;
	}
	
	public Pedido(Usuario usuarioComprador, Producto productoComprado) {
		super();
		this.precioTotal = productoComprado.getPrecio() + 3.90;
		this.fechaCompra = (Date) Calendar.getInstance().getTime();
		this.fechaEntrega = sumarDiasFecha(fechaCompra);
		this.numeroPedido = numeroPedido;
		this.usuarioComprador = usuarioComprador;
		this.productoComprado = productoComprado;
	}
	
	/**
	 * El tiempo de espera del pedido (desde que se compra hasta que llega) varía dependiendo de la distancia entre el usuario comprador y el vendedor.
	 * En caso de vivir en la misma ciudad y en el mismo país (ya que puede haber ciudades con el mismo nombre en diferentes paises), el tiempo de espera será de 3 días.
	 * Por otro lado, en caso de vivir en distinta ciudad, pero en el mismo país, el tiempo de espera será de una semana.
	 * Por último, en caso de vivir en distintos paises, habrá que esperar 15 días.
	 * 
	 * **/
	
	public static Date sumarDiasFecha(Date fecha){
		int dias = 0;
		if (productoComprado.getUsuario().getVivienda().getNomCiud() == usuarioComprador.getVivienda().getNomCiud() && productoComprado.getUsuario().getVivienda().getNomPais() == usuarioComprador.getVivienda().getNomPais()) {
			dias = 3;
		} else if (productoComprado.getUsuario().getVivienda().getNomCiud() != usuarioComprador.getVivienda().getNomCiud() && productoComprado.getUsuario().getVivienda().getNomPais() == usuarioComprador.getVivienda().getNomPais()) {
			dias = 7;
		} else if (productoComprado.getUsuario().getVivienda().getNomPais() != usuarioComprador.getVivienda().getNomPais()) {
			dias = 15;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return (Date) calendar.getTime();
	} 

	public double getPrecioTotal() {
		return precioTotal;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Usuario getUsuarioComprador() {
		return usuarioComprador;
	}

	public void setUsuarioComprador(Usuario usuarioComprador) {
		this.usuarioComprador = usuarioComprador;
	}

	public Producto getProductoComprado() {
		return productoComprado;
	}

	public void setProductoComprado(Producto productoComprado) {
		this.productoComprado = productoComprado;
	}

	@Override
	public String toString() {
		return "Pedido [precioTotal=" + precioTotal + ", fechaCompra=" + fechaCompra
				+ ", fechaEntrega=" + fechaEntrega + ", numeroPedido=" + numeroPedido + ", usuarioComprador="
				+ usuarioComprador + ", productoComprado=" + productoComprado
				+ "]";
	}

	
	
	
}
