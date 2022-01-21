package clases;

import java.awt.Image;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

/**
 * La clase Calzado es una clase hija de producto, que tiene sus mismos atributos y un atributo de la tallaCalzado.
 * Esta talla, a diferencia de la de Ropa, es de tipo double.
 * 
 * Tiene un constructor de calzado con todos sus atrbutos y uno con los atributos que solo puede meter el usuario.
 * La diferencia es que en el segundo no tiene:
 * 		id -> ya que la base de datos lo crea una vez registrado el producto
 * 		fechaSubida - ya que el programa mismo coge la fecha, para asegurarse de que el usuario no miente
 * 		enVenta -> esto cambiará solo, sin la ayuda del usuario
 * **/

public class Calzado extends Producto {
	
	private double tallaCalzado;
	
	public Calzado(int id, String nombre, Date fechaSubida, String etiquetas, double precio, String imagen, Estado estado, Colores color, Usuario usuario, boolean enVenta, boolean esRopa, double tallaCalzado, HashMap <Usuario, String> comentario) {
		super(id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, esRopa, comentario);
		this.tallaCalzado = tallaCalzado;
	}
	
	public Calzado(String nombre, String etiquetas, double precio, String imagen, Estado estado, Colores color, Usuario usuario, double tallaCalzado) {
		super(nombre, etiquetas, precio, imagen, estado, color, usuario);
		this.tallaCalzado = tallaCalzado;
	}

	
	public double getTallaCalzado() {
		return tallaCalzado;
	}

	public void setTallaCalzado(double tallaCalzado) {
		this.tallaCalzado = tallaCalzado;
	}



	@Override
	public String toString() {
		return "Calzado [id=" + id + ", nombre=" + nombre + ", fechaSubida=" + fechaSubida + ", etiquetas="
				+ etiquetas + ", precio=" + precio + ", imagen=" + imagen + ", estado=" + estado
				+ ", color=" + color + ", usuario=" + usuario + "tallaCalzado=" + tallaCalzado + "]";
	}
	
	


}
