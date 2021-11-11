package Clases;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;

public class Producto {
	
	private static int contador = 0;
	
	private int id;
	private String nombre;
	private Date fechaSubida;
	private String descripcion;
	private double precio;
	private Image imagen;
	private Tipo tipo;
	
	
	public Producto (int id, String nombre, Date fechaSubida, String descripcion, double precio, Image imagen, Tipo tipo) {
		super();
		contador++;
		this.id = contador;
		this.nombre = nombre;
		this.fechaSubida = fechaSubida;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen = imagen;
		this.tipo = tipo;
	}


	public Producto (String nombre, String descripcion, double precio, Image imagen, Tipo tipo) {
		super();
		contador++;
		this.id = contador;
		this.nombre = nombre;
		this.fechaSubida = fechaSubida;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen = imagen;
		this.tipo = tipo;
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
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
/*
	public void setTipo(Tipo tipo) {
		if (Tipo.TiposProductos.contains(this.tipo)) {
			this.tipo = tipo;
			
		}
	}*/


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", fechaSubida=" + fechaSubida + ", descripcion="
				+ descripcion + ", precio=" + precio + ", imagen=" + imagen + ", tipo=" + tipo + "]";
	}

	

	
	
	
	
	

}
