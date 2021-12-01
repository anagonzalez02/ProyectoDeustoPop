package Clases;

import java.util.ArrayList;

public class Tipo {
	
	private String nombre;
	private int codigo;
	
	
	public Tipo(String nombre, int codigo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	@Override
	public String toString() {
		return "Tipo [nombre=" + nombre + ", codigo=" + codigo + "]";
	}
	
	
	static ArrayList <Tipo> TiposProductos = new ArrayList();
	
	// HAY QUE AÑADIR LOS TIPOS CREADOS AL ARRAYLIST, PERO DA ERROR
	// Todavía no se sabe si serán estos los definitivos o no
	
	public static Tipo ropa = new Tipo("Ropa", 01);
	// TiposProductos.add(ropa);
	public static Tipo calzado = new Tipo("Calzado", 02);
	// TiposProductos.add(calzado);
	
	/**
	public static Tipo tecnologia = new Tipo("Tecnología", 02);
	// TiposProductos.add(tecnologia);
	public static Tipo deportes = new Tipo("Deportes", 03);
	// TiposProductos.add(deportes);
	public static Tipo jardineria = new Tipo("Jardinería", 04);
	// TiposProductos.add(jardineria);
	public static Tipo decoracion = new Tipo("Decoración", 05);
	// TiposProductos.add(decoracion);
	public static Tipo libros = new Tipo("Libros", 06);
	// TiposProductos.add(libros);
	**/

}
