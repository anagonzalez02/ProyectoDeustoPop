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
	static String consulta;
	

	/** Abre conexión con la base de datos
	 * @param nombreBD	Nombre del fichero de base de datos
	 * @param reiniciaBD	true si se quiere reiniciar la base de datos (se borran sus contenidos si los tuviera y se crean datos por defecto)
	 * @return	true si la conexión ha sido correcta, false en caso contrario
	 * @throws SQLException 
	 */
	
	
	public static boolean abrirConexion( String nombreBD, boolean conexionBD ) throws SQLException {
		
		String consulta;
		Statement statement = conexion.createStatement();
		Scanner scanner;
		
		try {
			
			logger.log( Level.INFO, "Carga de librería org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexión con " + nombreBD );
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			
			if (conexionBD) {
				
				crearTablaBDUsuario();
				crearTablaBDCalzado();
				crearTablaBDRopa();
				crearTablaBDLugar();
				
				try {
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Usuario-inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Usuario (idUsuario, nombre, telefono, tarjeta, saldo, email, contrasenia, vivienda, productosEnVenta, productosVendidos, productosComprados, productosFavoritos)"
								+ "VALUES (" + datos[0] + ", '" + datos[1] + "', " + datos[2] + ", " + datos[3] + ", " + datos[4] + ", '" + datos[5] + "', '" + datos[6] + "', " + datos[7] + ", '" + datos[8] + "', '" + datos[9] + "', '" + datos[10] + "', '" + datos[11] + "');";
						logger.log( Level.INFO, "Statement: " + consulta );
						statement.executeUpdate( consulta );
					}
					scanner.close();
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Calzado-inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Calzado (id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, tallaCalzado)"
								+ "VALUES (" + datos[0] + ", '" + datos[1] + "', '" + datos[2] + "', '" + datos[3] + "', " + datos[4] + ", '" + datos[5] + "', '" + datos[6] + "', '" + datos[7] + "', " + datos[8] + ", " + datos[9] + ", " + datos[10] + ");";
						logger.log( Level.INFO, "Statement: " + consulta );
						statement.executeUpdate( consulta );
					}
					scanner.close();
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Ropa-inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Ropa (id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, tallaRopa)"
								+ "VALUES (" + datos[0] + ", '" + datos[1] + "', '" + datos[2] + "', '" + datos[3] + "', " + datos[4] + ", '" + datos[5] + "', '" + datos[6] + "', '" + datos[7] + "', " + datos[8] + ", " + datos[9] + ", '" + datos[10] + "');";
						logger.log( Level.INFO, "Statement: " + consulta );
						statement.executeUpdate( consulta );
					}
					scanner.close();
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Lugar-inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Lugar (direccion, codCiu, nomCiu, codPais, nomPais)"
								+ "VALUES ('" + datos[0] + "', " + datos[1] + ", '" + datos[2] + "', " + datos[3] + ", '" + datos[4] + "');";
						logger.log( Level.INFO, "Statement: " + consulta );
						statement.executeUpdate( consulta );
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
	
	/**
	 * Cierra la conexión abierta de Base de Datos
	 */
	
	public static void cerrarConexion() {
		try {
			conexion.close();
			System.out.println( "Conexión cerrada" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void crearTablaBDUsuario() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Usuario";
		logger.log( Level.INFO, "Statement: " + consulta );
		statement.executeUpdate( consulta );
		
		consulta = "CREATE TABLE Usuario " +
				"(INT[6] idUsuario AUTO_INCREMENT NOT NULL, VARCHAR[20] nombre NOT NULL, INT[12] telefono, INT[18] tarjeta, DOUBLE[6,2] saldo DEFAULT 0, VARCHAR[70] email, "
				+ " VARCHAR[20] contrasenia NOT NULL, VARCHAR[100] direccion, ArrayList<Producto> productosEnVenta, "
				+ "ArrayList<Producto> productosVendidos, ArrayList<Producto> productosComprados, ArrayList<Producto> productosFavoritos, "
				+ "PRIMARY KEY (idUsuario), + UNIQUE KEY (nombre), FOREIGN KEY (direccion) REFERENCES Lugar (direccion));";
		
		if (statement==null) return;
		try {
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	
	
	// FALTAN LA IMAGEN, ESTADO Y COLORES
	
	public static void crearTablaBDCalzado() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Calzado";
		logger.log( Level.INFO, "Statement: " + consulta );
		statement.executeUpdate( consulta );
		
		consulta = "CREATE TABLE Calzado " +
				"(INT[10] id AUTO_INCREMENT NOT NULL, VARCHAR[40] nombre NOT NULL, DATE fechaSubida, VARCHAR[60] etiquetas, "
				+ "DOUBLE[4, 2] precio, "
				+ "INT[6] idUsuario NOT NULL, BIT enVenta, DOUBLE[2,1] tallaCalzado, "
				+ "PRIMARY KEY (id), + UNIQUE KEY (nombre), FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario));";
		
		if (statement==null) return;
		try {
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	
	
	// FALTAN LA IMAGEN, ESTADO, COLORES Y TALLA
	
	public static void crearTablaBDRopa() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Ropa";
		logger.log( Level.INFO, "Statement: " + consulta );
		statement.executeUpdate( consulta );
		
		consulta = "CREATE TABLE Ropa " +
				"(INT[10] id AUTO_INCREMENT NOT NULL, VARCHAR[40] nombre NOT NULL, DATE fechaSubida, VARCHAR[60] etiquetas, "
				+ "DOUBLE[4, 2] precio, "
				+ "INT[6] idUsuario NOT NULL, BIT enVenta, DOUBLE[2,1] tallaCalzado, "
				+ ""
				+ "PRIMARY KEY (id), + UNIQUE KEY (nombre), FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario));";
		
		if (statement==null) return;
		try {
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	
	
	public static void crearTablaBDLugar() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Lugar";
		logger.log( Level.INFO, "Statement: " + consulta );
		statement.executeUpdate( consulta );
		
		consulta = "CREATE TABLE Lugar " +
				"(VARCHAR[100] direccion NOT NULL, INT[7] codCiu, VARCHAR[35] nomCiud, INT[5] codPais, VARCHAR[25] nomPais, PRIMARY KEY (direccion));";
		
		if (statement==null) return;
		try {
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	
	
	
	
	
	
	

}
