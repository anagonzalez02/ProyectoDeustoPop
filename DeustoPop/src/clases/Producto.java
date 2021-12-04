package clases;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Producto {
	
	private static final Image Image = null;

	private static int contador = 0;
	
	private int id;
	private String nombre;
	private Calendar fechaSubida;
	private String etiquetas;
	private double precio;
	private Image imagen;
	private Tipo tipo;
	private Estado estado;
	private Colores color;
	private Usuario usuario;
	private boolean enVenta;
	
	
	public Producto (int id, String nombre, Calendar fechaSubida, String etiquetas, double precio, Image imagen, Tipo tipo, Estado estado, Colores color, Usuario usuario, boolean enVenta) {
		super();
		contador++;
		this.id = contador;
		this.nombre = nombre;
		this.fechaSubida = Calendar.getInstance();
		this.etiquetas = etiquetas;
		this.precio = precio;
		this.imagen = imagen;
		this.tipo = tipo;
		this.estado = estado;
		this.color = color;
		this.usuario = usuario;
		this.enVenta = true;
	}


	public Producto (String nombre, String etiquetas, double precio, Image imagen, Tipo tipo, Estado estado, Colores color, Usuario usuario) {
		super();
		contador++;
		this.id = contador;
		this.nombre = nombre;
		this.fechaSubida = Calendar.getInstance();
		this.etiquetas = etiquetas;
		this.precio = precio;
		this.imagen = imagen;
		this.tipo = tipo;
		this.estado = estado;
		this.color = color;
		this.usuario = usuario;
		this.enVenta = true;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = contador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Calendar getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(Calendar fechaSubida) {
		this.fechaSubida = Calendar.getInstance();
	}

	public String getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		if (tipo.TiposProductos.contains(this.tipo)) {
			this.tipo = tipo;
		}
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Colores getColor() {
		return color;
	}

	public void setColor(Colores color) {
		this.color = color;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Este atributo nos ayudara a saber si dicho producto esta en venta o no
	// Este producto estara visible para los usuario de DeustoPop en caso de ser true
	// en cambio, si es false, ningun cliente podra acceder a el, aunque seguira estando en la base de datos y los archivos de la empres
	public boolean isEnVenta() {
		return enVenta;
	}

	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", fechaSubida=" + fechaSubida + ", etiquetas="
				+ etiquetas + ", precio=" + precio + ", imagen=" + imagen + ", tipo=" + tipo + ", estado=" + estado
				+ ", color=" + color + ", usuario=" + usuario + "]";
	}
	
	
	

}
