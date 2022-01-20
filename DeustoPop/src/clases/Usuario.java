package clases;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JPasswordField;

/**
 * La clase usuario define todos los usuarios registrados en DeustoPop.
 * Estos podrán comprar productos subidos a la web, vender productos propios (tanto ropa como calzado), comentar en productos, tener una lista de favoritos, 
 * 		eliminar los productos propios subidos, usar el dinero (para comprar o vender) bpropio de la propia cuenta bancaria o de un saldo propio de la web,
 * 		buscar entre los productos con precio menor o igual al saldo de la web...
 * 
 * De los usuarios deberemos saber:
 * 		idUsuario ->			Identificativo único que tendrán los usuarios. Esto se autogenerará al registrar el usuario en la base de datos.
 * 		nombre ->				El nombre de usuario, también será distinto al resto (como por ejemplo suele pasar en las redes sociales).
 * 		telefono ->				Número de teléfono del cliente, deberá ser único.
 * 		cuentaB ->				Esto hará referencia a la clase CuentaBancaria, donde el usuario tendrá un número de tarjeta y una cantidad de dinero.
 * 		saldo ->				Cada usuario tendrá un saldo en la web. Nada más registrarse, el usuario recibirá 5.00€ para gastarlo en DeustoPop.
 * 								Al comprar un producto, en caso de tener dinero suficiente, se descontará del saldo (en caso contrario, se descontará de la cuenta bancaria).
 * 								Si alguno de los productos del usuario se vende, el precio del producto se añadirá al saldo.
 * 		email ->				Correo electrónico diferente al del resto de Usuarios.
 * 		contrasenia ->			Contraseña necesaria que deberá introducirse al registrarse por primera vez, y que se necesitará cada vez que se quiera iniciar sesión.
 * 		vivienda ->				Dirreccion, ciudad y país del usuario. Esto se deberá registrar para que, al vender un producto, el comprador pueda mandárselo a la vivienda del vendedor.
 * 		productosEnVenta ->		ArrayList de todos los productos que el usuario tiene en venta (y por lo tanto, son visibles para el resto de usuarios)
 * 		productosVendidos -> 	ArrayList de todos los productos que el usuario YA ha vendido por DeustoPop (y por lo tanto, ya no son visibles para el resto, pero siguen registrados)
 * 		productosComprados ->	ArrayList de todos los productos que el usuario ha comprado a otros vendedores de DesutoPop por nuestra web (y por lo tanto, ya no son visibles).
 * 		productosFavoritos ->	ArrayList de todos los productos a los que el usuario les ha dado al botón de favorito.
 * 
 * Tenemos dos constructores.
 * El primero: hay que meter todos los atributos de la clase Usuario.
 * El segundo: hay que meter solamente los atributos que el usuario introduce al registrarse. 
 * 				El resto se inicializarán como vacíos en caso de los ArrayList de productos y 5.00 en caso de saldo.
 * **/

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
	Date fechaRegistro;
	

	public Usuario(int idUsuario, String nombre, int telefono, CuentaBancaria cuentaB, double saldo, String email, String contrasenia, Lugar vivienda,
			ArrayList<Producto> productosEnVenta, ArrayList<Producto> productosVendidos, ArrayList<Producto> productosComprados, ArrayList<Producto> productosFavoritos, Date fechaRegistro) {
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
		this.fechaRegistro = fechaRegistro;
	}
	

	public Usuario(String nombre, int telefono, CuentaBancaria cuentaB, double saldo, String email, String contrasenia, Lugar vivienda) {
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
		this.fechaRegistro = fechaRegistro;
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
		this.fechaRegistro = fechaRegistro;
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
		this.fechaRegistro = fechaRegistro;
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

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", telefono=" + telefono + ", cuentaB="
				+ cuentaB + ", saldo=" + saldo + ", email=" + email + ", contrasenia=" + contrasenia + ", vivienda="
				+ vivienda + ", productosEnVenta=" + productosEnVenta + ", productosVendidos=" + productosVendidos
				+ ", productosComprados=" + productosComprados + ", productosFavoritos=" + productosFavoritos + "]";
	}


	
	
	
}
