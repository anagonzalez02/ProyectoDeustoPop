package clases;

import java.awt.Image;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

/**
 * La clase Ropa es una clase hija de producto, que tiene sus mismos atributos y un atributo de la tallaRopa.
 * Esta talla, a diferencia de la de Calzado, es de tipo TallasRopa.
 * Las tallas de calzado suelen ser (en España, al menos) entre 15-50 (incluyendo tanto las de adultos como las de niños). 
 * En cambio, las de ropa suelen ser letras (XS: extra-small, S: small, M: medium, L: large, XL: extra-large).
 * Por ello, hemos creado una enumeración de estas tallas. Esto será para que, a la hora de introducir los datos de la prenda de ropa que se quiere subir, el vendedor tenga unas opciones limitadas de la talla y no pueda mentir.
 * 
 * Tiene un constructor de calzado con todos sus atrbutos y uno con los atributos que solo puede meter el usuario.
 * La diferencia es que en el segundo no tiene:
 * 		id -> ya que la base de datos lo crea una vez registrado el producto
 * 		fechaSubida - ya que el programa mismo coge la fecha, para asegurarse de que el usuario no miente
 * 		enVenta -> esto cambiará solo, sin la ayuda del usuario
 * 
 * **/

public class Ropa extends Producto {
	
	private TallasRopa TallaRopa;
	
	public Ropa(int id, String nombre, Date fechaSubida, String etiquetas, double precio, String imagen, Estado estado, Colores color, Usuario usuario, boolean enVenta, TallasRopa TallaRopa, HashMap <Usuario, String> comentario) {
		super(id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, comentario);
		this.TallaRopa = TallaRopa;
	}
	
	public Ropa(String nombre, String etiquetas, double precio, String imagen, Estado estado, Colores color, Usuario usuario, TallasRopa TallaRopa) {
		super(nombre, etiquetas, precio, imagen, estado, color, usuario);
		this.TallaRopa = TallaRopa;
	}
	

	public TallasRopa getTallaRopa() {
		return TallaRopa;
	}

	public void setTallaRopa(TallasRopa tallaRopa) {
		TallaRopa = tallaRopa;
	}

	
	@Override
	public String toString() {
		return "Ropa [id=" + id + ", nombre=" + nombre + ", fechaSubida=" + fechaSubida + ", etiquetas="
				+ etiquetas + ", precio=" + precio + ", imagen=" + imagen + ", estado=" + estado
				+ ", color=" + color + ", usuario=" + usuario + "TallaRopa=" + TallaRopa + "]";
	}
	
	


}
