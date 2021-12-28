package clases;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Esta es la clase Pedido, a la que se recurrirá cada vez que un usuario le compre a otro un producto.
 * Por cada compra, se creará un pedido nuevo, cuyos atributos serán:
 * 		precioTotal -> 			Será el precio del producto más los gastos de envíos (3.90€)
 * 		fechaCompra -> 			La fecha en la que el producto ha sido comprado
 * 		fechaEntrega -> 		Una fecha estimada en la que debería llegar el producto comprado, se estima una espera de 15 días
 * 		numeroPedido -> 		Será el identificativo del pedido, que se creará en la base de datos
 * 		usuarioCompador -> 		El usuario que compre el producto
 * 		productoComprado -> 	El producto por el que se hace la compra
 * 
 * Se registrará un pedido en la base de datos, y podrá accederse a él en caso de haber algún error.
 * **/

public class Pedido { 
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private double precioTotal = 0;
	private Date fechaCompra;
	private Date fechaEntrega;
	private int numeroPedido = 0;
	private Usuario usuarioComprador;
	private Producto productoComprado;
	
	

	public Pedido(double precioTotal, Date fechaCompra, Date fechaEntrega, int numeroPedido, Usuario usuarioComprador, Producto productoComprado) {
		super();
		this.precioTotal = precioTotal;
		this.fechaCompra = fechaCompra;
		this.fechaEntrega = fechaEntrega;
		this.numeroPedido = numeroPedido;
		this.usuarioComprador = usuarioComprador;
		this.productoComprado = productoComprado;
	}
	
	public Pedido(Usuario usuarioComprador, Producto productoComprado) {
		super();
		this.precioTotal = precioTotal;
		this.fechaCompra = fechaCompra;
		this.fechaEntrega = fechaEntrega;
		this.numeroPedido = numeroPedido;
		this.usuarioComprador = usuarioComprador;
		this.productoComprado = productoComprado;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = productoComprado.getPrecio() + 3.90;
	}

	
	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
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
