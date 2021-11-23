package Clases;

import java.util.ArrayList;

public class Usuario{
	
	private static int contadorU = 0;
	
	private int idUsuario;
	private String nombre;
	private int telefono;
	private int tarjeta;
	private double saldo;
	private int DNI;
	private String email;
	private String contrasenia;
	private Lugar vivienda;
	private ArrayList<Producto> productos;
	
	
	
	public Usuario(int idUsuario, String nombre, int telefono, int tarjeta, double saldo,
			int dNI, String email, String contrasenia, Lugar vivienda, ArrayList<Producto> productos) {
		super();
		contadorU++;
		this.idUsuario = contadorU;
		this.nombre = nombre;
		this.telefono = telefono;
		this.tarjeta = tarjeta;
		this.saldo = saldo;
		DNI = dNI;
		this.email = email;
		this.contrasenia = contrasenia;
		this.vivienda = vivienda;
		this.productos = productos;
	}

	// Luego esta habría que cambiarla, es solo para ayuda a la hora de una ventana
	
	public Usuario() {
		super();
		contadorU++;
		this.idUsuario = contadorU;
		this.nombre = nombre;
		this.telefono = telefono;
		this.tarjeta = tarjeta;
		this.saldo = saldo;
		DNI = getDNI();
		this.email = email;
		this.contrasenia = contrasenia;
		this.vivienda = vivienda;
		this.productos = productos;
	}
	

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = contadorU;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getTarjeta() {
		return tarjeta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setTarjeta(int tarjeta) {
		this.tarjeta = tarjeta;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Lugar getVivienda() {
		return vivienda;
	}

	public void setVivienda(Lugar vivienda) {
		this.vivienda = vivienda;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + idUsuario + ", nombre=" + nombre + ", telefono=" + telefono + ", tarjeta=" + tarjeta + ", DNI=" + DNI
				+ ", email=" + email + ", contrasenia=" + contrasenia + ", vivienda=" + vivienda + ", productos="
				+ productos + "]";
	}
	
	
	
	
	
}
	
	

