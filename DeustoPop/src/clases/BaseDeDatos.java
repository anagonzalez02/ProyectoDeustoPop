package clases;

import java.awt.Image;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class BaseDeDatos {
	
	private static Connection connection = null;
	private static Statement statement = null;
	

	// Inicializa un Base de Datos
	
	public static Connection initBD( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	
	// Cierra la conexion con la Base De Datos
	
	public static void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() {
		return connection;
	}
	
	
	public static Statement getStatement() {
		return statement;
	}
	
	
	
	public static void crearTablaBDUsuario() {
		if (statement==null) return;
		try {
			statement.executeUpdate("CREATE TABLE Usuario " +
				"(int idUsuario, , String nombre, int telefono, int tarjeta, double saldo, String email,\n"
				+ " String contrasenia, String direccion, ArrayList<Producto> productosEnVenta, \n"
				+ "ArrayList<Producto> productosVendidos, ArrayList<Producto> productosComprados),\n"
				+ "Primary Key (idUsuario), + Unique Key (nombre), Foreign Key (direccion) references Lugar (direccion)");
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	// Casi hecho
	
	
	public static void crearTablaBDProducto() {
		if (statement==null) return;
		try {
			statement.executeUpdate("CREATE TABLE Producto " +
				"(int id, String nombre, Calendar fechaSubida, String etiquetas, double precio, Image imagen, Tipo tipo, String estado, String color, Usuario usuario, boolean enVenta)");
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	
	
	public static void crearTablaBDPedido() {
		if (statement==null) return;
		try {
			statement.executeUpdate("CREATE TABLE Pedido " +
				"(int precioTotal, Date fechaCompra, Date fechaEntrega, int numeroPedido, Usuario usuarioComprador,\n"
				+ "Usuario usuarioVendedor, ArrayList<Producto> productosComprados)");
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	
	
	

}
