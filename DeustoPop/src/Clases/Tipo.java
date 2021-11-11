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
	
	/*
	static ArrayList <Tipo> TiposProductos = new ArrayList();
	
	Tipo ropa = new Tipo("ropa", 01);
	TiposProductos.add(ropa);
	Tipo tecnologia = new Tipo("Tecnología", 02);
	TiposProductos.add(tecnologia);
	Tipo deportes = new Tipo("Deportes", 03);
	TiposProductos.add(deportes);
	Tipo jardineria = new Tipo("Jardinería", 04);
	TiposProductos.add(jardineria);
	Tipo decoracion = new Tipo("Decoración", 05);
	TiposProductos.add(decoracion);
	Tipo libros = new Tipo("Libros", 06);
	TiposProductos.add(libros);
	*/

}
