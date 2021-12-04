package clases;

import java.awt.Image;
import java.util.Calendar;

public class Calzado extends Producto {
	
	private double tallaCalzado;
	
	public Calzado(int id, String nombre, Calendar fechaSubida, String etiquetas, double precio, Image imagen, Estado estado, Colores color, Usuario usuario, boolean enVenta, double tallaCalzado) {
		super(id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta);
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
		return "Calzado [tallaCalzado=" + tallaCalzado + "]";
	}
	
	


}
