package Clases;

import java.sql.Date;
import java.text.SimpleDateFormat;
public class Pedido {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int precioTotal = 0;
	Date fechaCompra;
	Date fechaEntrega;
	private int numeroPedido = 0;
	
	public Pedido(int precioTotal, Date fechaCompra, Date fechaEntrega, int numeroPedido) {
		super();
		this.precioTotal = precioTotal;
		this.fechaCompra = fechaCompra;
		this.fechaEntrega = fechaEntrega;
		this.numeroPedido = numeroPedido;
		this.fechaCompra = fechaCompra;
		this.fechaEntrega = fechaEntrega;
		
	
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
}
