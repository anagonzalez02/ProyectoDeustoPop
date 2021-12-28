package clases;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Esta es la clase Pedido, a la que se recurrirá cada vez que un usuario le compre a otro un producto.
 * Por cada compra, se creará un pedido nuevo, cuyos atributos serán:
 * 		precioTotal -> será el precio del producto más los gastos de envíos
 * 		fechaCompra -> la fecha en la que el producto ha sido comprado
 * 		fechaEntrega -> una fecha estimada en la que debería llegar el producto comprado, se estima una espera de 15 días
 * 		numeroPedido -> será el identificativo del pedido, que se creará en la base de datos
 * 		usuarioCompador -> el usuario que compre el producto
 * 		usuarioVendedor -> usuario que venda el producto
 * 		productoComprado -> el producto por el que se hace la compra
 * 
 * Se registrará un pedido en la base de datos, y podrá accederse a él en caso de haber algún error.
 * **/

public class Pedido { 
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int precioTotal = 0;
	private Date fechaCompra;
	private Date fechaEntrega;
	private int numeroPedido = 0;
	private Usuario usuarioComprador;
	private Usuario usuarioVendedor;
	private Producto productoComprado;
	
	

	public Pedido(int precioTotal, Date fechaCompra, Date fechaEntrega, int numeroPedido, Usuario usuarioComprador,
			Usuario usuarioVendedor, Producto productoComprado) {
		super();
		this.precioTotal = precioTotal;
		this.fechaCompra = fechaCompra;
		this.fechaEntrega = fechaEntrega;
		this.numeroPedido = numeroPedido;
		this.usuarioComprador = usuarioComprador;
		this.usuarioVendedor = usuarioVendedor;
		this.productoComprado = productoComprado;
	}

	public int getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(int precioTotal) {
		this.precioTotal = precioTotal;
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

	public Usuario getUsuarioVendedor() {
		return usuarioVendedor;
	}

	public void setUsuarioVendedor(Usuario usuarioVendedor) {
		this.usuarioVendedor = usuarioVendedor;
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
				+ usuarioComprador + ", usuarioVendedor=" + usuarioVendedor + ", productoComprado=" + productoComprado
				+ "]";
	}

	
	
	
}
