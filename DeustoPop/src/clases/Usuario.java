package clases;

import java.util.ArrayList;

import javax.swing.JPasswordField;

public class Usuario{ 
	
	private static int contadorU = 0;
	
	private int idUsuario;
	private String nombre;
	private int telefono;
	private CuentaBancaria cuentaB;
	private double saldo;
	private String email;
	private String contrasenia;
	private Lugar vivienda;
	ArrayList<Producto> productosEnVenta;
	ArrayList<Producto> productosVendidos;
	ArrayList<Producto> productosComprados;
	ArrayList<Producto> productosFavoritos;
	

	public Usuario(int idUsuario, String nombre, int telefono, CuentaBancaria cuentaB, double saldo, String email, String contrasenia, Lugar vivienda,
			ArrayList<Producto> productosEnVenta, ArrayList<Producto> productosVendidos, ArrayList<Producto> productosComprados, ArrayList<Producto> productosFavoritos) {
		super();
		contadorU++;
		this.idUsuario = contadorU;
		this.nombre = nombre;
		this.telefono = telefono;
		this.cuentaB = cuentaB;
		this.saldo = saldo;
		this.email = email;
		this.contrasenia = contrasenia;
		this.vivienda = vivienda;
		this.productosEnVenta = productosEnVenta;
		this.productosVendidos = productosVendidos;
		this.productosComprados = productosComprados;
		this.productosFavoritos = productosFavoritos;
	}

	

	public Usuario(String nombre, int telefono, CuentaBancaria cuentaB, String email, String contrasenia, Lugar vivienda) {
		super();
		contadorU++;
		this.idUsuario = contadorU;
		this.nombre = nombre;
		this.telefono = telefono;
		this.cuentaB = cuentaB;
		this.saldo = saldo;
		this.email = email;
		this.contrasenia = contrasenia;
		this.vivienda = vivienda;
		this.productosEnVenta = productosEnVenta;
		this.productosVendidos = productosVendidos;
		this.productosComprados = productosComprados;
		this.productosFavoritos = productosFavoritos;
	}



	// Luego esta habría que quitarla, es solo para ayuda a la hora de una ventana
	
	public Usuario() {
		super();
		contadorU++;
		this.idUsuario = contadorU;
		this.nombre = nombre;
		this.telefono = telefono;
		this.cuentaB = cuentaB;
		this.saldo = saldo;
		this.email = email;
		this.contrasenia = contrasenia;
		this.vivienda = vivienda;
		this.productosEnVenta = productosEnVenta;
		this.productosVendidos = productosVendidos;
		this.productosComprados = productosComprados;
		this.productosFavoritos = productosFavoritos;
	}
	

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	public CuentaBancaria getCuentaB() {
		return cuentaB;
	}

	public void setCuentaB(CuentaBancaria cuentaB) {
		this.cuentaB = cuentaB;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
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

	public ArrayList<Producto> getProductosEnVenta() {
		return productosEnVenta;
	}

	public void setProductosEnVenta(ArrayList<Producto> productosEnVenta) {
		this.productosEnVenta = productosEnVenta;
	}
	
	public ArrayList<Producto> getProductosVendidos() {
		return productosVendidos;
	}

	public void setProductosVendidos(ArrayList<Producto> productosVendidos) {
		this.productosVendidos = productosVendidos;
	}
	
	public ArrayList<Producto> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(ArrayList<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}
	
	public ArrayList<Producto> getProductosFavoritos() {
		return productosFavoritos;
	}

	public void setProductosFavoritos(ArrayList<Producto> productosFavoritos) {
		this.productosFavoritos = productosFavoritos;
	}



	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", telefono=" + telefono + ", cuentaB="
				+ cuentaB + ", saldo=" + saldo + ", email=" + email + ", contrasenia=" + contrasenia + ", vivienda="
				+ vivienda + ", productosEnVenta=" + productosEnVenta + ", productosVendidos=" + productosVendidos
				+ ", productosComprados=" + productosComprados + ", productosFavoritos=" + productosFavoritos + "]";
	}


	
	
	
}
	
	

