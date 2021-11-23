package Clases;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Producto {
	
	private static int contador = 0;
	
	private int id;
	private String nombre;
	private Calendar fechaSubida;
	private String etiquetas;
	private double precio;
	private Image imagen;
	private Tipo tipo;
	private String estado;
	private String color;
	private Usuario usuario;
	
	
	public Producto (int id, String nombre, Calendar fechaSubida, String etiquetas, double precio, Image imagen, Tipo tipo, String estado, String color, Usuario usuario) {
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
	}


	public Producto (String nombre, String etiquetas, double precio, Image imagen, Tipo tipo, String estado, String color, Usuario usuario) {
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
	}


	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Producto.contador = contador;
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
		if (Tipo.TiposProductos.contains(this.tipo)) {
			this.tipo = tipo;
		}
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", fechaSubida=" + fechaSubida + ", etiquetas="
				+ etiquetas + ", precio=" + precio + ", imagen=" + imagen + ", tipo=" + tipo + ", estado=" + estado
				+ ", color=" + color + ", usuario=" + usuario + "]";
	}


	
	
	
	

}
