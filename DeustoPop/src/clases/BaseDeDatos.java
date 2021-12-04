package clases;

import java.awt.Image;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDeDatos {
	
	private static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	

	/** Abre conexión con la base de datos
	 * @param nombreBD	Nombre del fichero de base de datos
	 * @param reiniciaBD	true si se quiere reiniciar la base de datos (se borran sus contenidos si los tuviera y se crean datos por defecto)
	 * @return	true si la conexión ha sido correcta, false en caso contrario
	 */
	
	/**
	public static boolean abrirConexion( String nombreBD, boolean conexionBD ) {
		String consulta;
		try {
			logger.log( Level.INFO, "Carga de librería org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexión con " + nombreBD );
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			if (conexionBD) {
				Statement statement = conexion.createStatement();
				consulta = "CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(100), precio real);";
				logger.log( Level.INFO, "Statement: " + consulta );
				statement.executeUpdate( consulta );
				sent = "DROP TABLE IF EXISTS compra";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE compra (id INTEGER PRIMARY KEY AUTOINCREMENT, idProducto int, cliente varchar(40), fecha bigint, cantidad int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				try {
					Scanner scanner = new Scanner( BaseDatos.class.getResourceAsStream("productos-inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into producto (id, nombre, precio) values (" + datos[0] + ",'" + datos[1] + "'," + datos[2] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					scanner = new Scanner( BaseDatos.class.getResourceAsStream("compras-inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into compra (id, idProducto, cliente, fecha, cantidad) values (" + datos[0] + "," + datos[1] + ",'" + datos[2] + "'," + datos[3] + "," + datos[4] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
				} catch(Exception e) {
					logger.log( Level.SEVERE, "Excepción", e );
				}
			}
			return true;
		} catch(Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	**/
	/**
	 * Cierra la conexión abierta de Base de Datos
	 */
	/**
	public static void cerrarConexion() {
		try {
			conexion.close();
			System.out.println( "Conexión cerrada" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConexion() {
		return conexion;
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
	
	
	**/
	
	
	

}
