package clases;

import java.awt.Image;
import java.util.Calendar;

public class Ropa extends Producto {
	
	private TallasRopa TallaRopa;
	
	public Ropa(int id, String nombre, Calendar fechaSubida, String etiquetas, double precio, Image imagen, Estado estado, Colores color, Usuario usuario, boolean enVenta, TallasRopa TallaRopa) {
	super(id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta);
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
		return "Ropa [TallaRopa=" + TallaRopa + "]";
	}
	
	


}
