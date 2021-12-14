package clases;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class Pedido {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int precioTotal = 0;
	private Date fechaCompra;
	private Date fechaEntrega;
	private int numeroPedido = 0;
	private Usuario usuarioComprador;
	private Usuario usuarioVendedor;
	private ArrayList <Producto> productosComprados;
	
	

	public Pedido(int precioTotal, Date fechaCompra, Date fechaEntrega, int numeroPedido, Usuario usuarioComprador,
			Usuario usuarioVendedor, ArrayList<Producto> productosComprados) {
		super();
		this.precioTotal = precioTotal;
		this.fechaCompra = fechaCompra;
		this.fechaEntrega = fechaEntrega;
		this.numeroPedido = numeroPedido;
		this.usuarioComprador = usuarioComprador;
		this.usuarioVendedor = usuarioVendedor;
		this.productosComprados = productosComprados;
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

	public ArrayList<Producto> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(ArrayList<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}

	@Override
	public String toString() {
		return "Pedido [precioTotal=" + precioTotal + ", fechaCompra=" + fechaCompra + ", fechaEntrega=" + fechaEntrega
				+ ", numeroPedido=" + numeroPedido + ", usuarioComprador=" + usuarioComprador + ", usuarioVendedor="
				+ usuarioVendedor + ", productosComprados=" + productosComprados + "]";
	}
	
	
}
