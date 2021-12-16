package clases;

import java.awt.Image;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Usuario.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Usuario (idUsuario, nombre, telefono, tarjeta, saldo, email, contrasenia, vivienda, productosEnVenta, productosVendidos, productosComprados, productosFavoritos)"
								+ "VALUES (" + datos[0] + ", '" + datos[1] + "', " + datos[2] + ", " + datos[3] + ", " + datos[4] + ", '" + datos[5] + "', '" + datos[6]
								+ "', (SELECT * FROM Lugar WHERE L.idUsuario = '" + datos[7] + "'), (SELECT P.* FROM Producto P, Usuario U WHERE P.idUsuario = U.idUsuario AND P.enVenta = 1), '"
								+ "(SELECT P.* FROM Producto P, Usuario U WHERE P.idUsuario = U.idUsuario AND P.enVenta = 0), '" + datos[10] + "', '" + datos[11] + "');";
						
						// QUEDA LA LISTA FAVORITOS Y LA LISTA COMPRADOS
						
						logger.log( Level.INFO, "Statement: " + consulta );
						statement.executeUpdate( consulta );
					}
					scanner.close();
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Calzado.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Calzado (id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, idUsuario, enVenta, tallaCalzado)"
								+ "VALUES (" + datos[0] + ", '" + datos[1] + "', '" + datos[2] + "', '" + datos[3] + "', " + datos[4] + ", '" + datos[5] + "', '" + datos[6] + "', '" + datos[7] + "', " + datos[8] + ", " + datos[9] + ", " + datos[10] + ");";
						logger.log( Level.INFO, "Statement: " + consulta );
						statement.executeUpdate( consulta );
					}
					scanner.close();
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Ropa.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Ropa (id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, idUsuario, enVenta, tallaRopa)"
								+ "VALUES (" + datos[0] + ", '" + datos[1] + "', '" + datos[2] + "', '" + datos[3] + "', " + datos[4] + ", '" + datos[5] + "', '" + datos[6] + "', '" + datos[7] + "', " + datos[8] + ", " + datos[9] + ", '" + datos[10] + "');";
						logger.log( Level.INFO, "Statement: " + consulta );
						statement.executeUpdate( consulta );
					}
					scanner.close();
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Lugar.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Lugar (direccion, nomCiu, nomPais)"
								+ "VALUES ('" + datos[0] + "', " + datos[1] + ", '" + datos[2] + "');";
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
	
	/**
	 * Crea la tabla Usuario
	 * **/
	
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
	
	/**
	 * Crea la tabla Calzado
	 * **/
	
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
	
	/**
	 * Crea la tabla Ropa
	 * **/
	
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
	
	/**
	 * Crea la tabla Lugar
	 * **/
	
	public static void crearTablaBDLugar() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Lugar";
		logger.log( Level.INFO, "Statement: " + consulta );
		statement.executeUpdate( consulta );
		
		consulta = "CREATE TABLE Lugar " +
				"(VARCHAR[100] direccion NOT NULL, VARCHAR[35] nomCiu, VARCHAR[25] nomPais PRIMARY KEY (direccion));";
		
		if (statement==null) return;
		try {
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();  
		}
	}
	
	
	/** 
	 * Lista los usuarios de la base de datos
	 * @return	Lista completa de los Usuarios de nuestra plataforma, null si hay algún error
	 */
	
	public static ArrayList<Usuario> getUsuarios() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Usuario> listaUsuarios = new ArrayList<>();
			consulta = "SELECT * FROM Usuario;";
			logger.log( Level.INFO, "Statement: " + consulta );
			ResultSet rs = statement.executeQuery( consulta );
			while( rs.next() ) { // Leer el resultset
				int idUsuario = rs.getInt("idUsuario");
				String nombre = rs.getString("nombre");
				int telefono = rs.getInt("telefono");
				int tarjeta = rs.getInt("tarjeta");
				double saldo = rs.getDouble("saldo");
				String email = rs.getString("email");
				String contrasenia = rs.getString("contrasenia");
				
				// QUEDAN EL LUGAR Y LOS ARRAYLISTS
				
				
				
				//listaUsuarios.add( new Producto (id, nombre, precio, new ArrayList<Compra>() ) );
			}
			return listaUsuarios;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	
	/**
	 * Inserta un usuario en la base de datos abierta 
	 * @param usuario		un nuevo usuario recién registrado que se introducirá en la base de Datos
	 * @return				true si la inserción es correcta, false en caso contrario
	 */
	public static boolean insertarUsuario( Usuario usuario ) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO Usuario (idUsuario, nombre, telefono, tarjeta, saldo, email, contrasenia, vivienda, productosEnVenta, productosVendidos, productosComprados, productosFavoritos)"
					+ "VALUES (" + usuario.getIdUsuario() + ", '" + usuario.getNombre() + "', " + usuario.getTelefono() + ", " + usuario.getTarjeta() + ", " + usuario.getSaldo() + ", '" + usuario.getEmail() + "', '" + usuario.getContrasenia() + "', " + usuario.getVivienda() + ", '" + usuario.getProductosEnVenta() + "', '" + usuario.getProductosVendidos() + "', '" + usuario.getProductosComprados() + "', '" + usuario.getProductosFavoritos() + "')";

			logger.log( Level.INFO, "Statement: " + consulta );
			int insertados = statement.executeUpdate( consulta );
			if (insertados!=1) return false;  // Error en inserción
			// Búsqueda de la fila insertada - para ello hay que recuperar la clave autogenerada. Hay varias maneras, vemos dos diferentes:
			// Se hace utilizando método del propio objeto statement
			ResultSet rrss = statement.getGeneratedKeys();  // Genera un resultset ficticio con las claves generadas del último comando
			rrss.next();  // Avanza a la única fila 
			int pk = rrss.getInt( 1 );  // Coge la única columna (la primary key autogenerada)
			usuario.setIdUsuario( pk );
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	
	/**
	 * Eliminar un usuario de la base de datos abierta 
	 * @param usuario		el usuario que quiere borrar su cuenta de DeustoPop
	 * @return				true si se ha eliminado correctamente, false en caso contrario
	 */
	
	public static boolean eliminarUsuario(Usuario usuario) {
		int id = usuario.getIdUsuario();
		try {
			Statement statement = conexion.createStatement();
			consulta = "DELETE FROM Usuario WHERE id = " + id + ";";
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	
	// REVISAR LOS ARRAYLISTS
	
	
	/**
	 * Modificar un usuario en la base de datos abierta 
	 * @param CASI todos los atributos del Usuario		actualiza todos (excepto el ID (ya que ese es definitivo) y la contrasenia), sin importar que sean iguales
	 * @return											true si se ha modificado correctamente, false en caso contrario
	 */
	
	public static boolean modificarUsuario(String nombre, int telefono, int tarjeta, String email, String contrasenia, Lugar vivienda, ArrayList<Producto> productosEnVenta, ArrayList<Producto> productosVendidos, ArrayList<Producto> productosComprados, ArrayList<Producto> productosFavoritos) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "UPDATE Usuario SET nombre = '" + nombre + "', telefono = " + telefono + ", tarjeta = " + tarjeta + ", email = '" + email + "', contrasenia = '" + contrasenia + "', direccion = '" + vivienda.getDireccion() + "', productosEnVenta = '" + productosEnVenta + "', productosVendidos = '" + productosVendidos + "', productosComprados = '" + productosComprados + "', productosFavoritos = '" + productosFavoritos + "';";
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	} 
	
	
	/** 
	 * Para obtener un listado de todos los lugares de la base de datos
	 * @return	Lista completa de los Lugares/Viviendas de nuestra plataforma, null si hay algún error
	 */
	
	public static ArrayList<Lugar> getLugar() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Lugar> listaLugares = new ArrayList<>();
			consulta = "SELECT * FROM Lugar;";
			logger.log( Level.INFO, "Statement: " + consulta );
			ResultSet rs = statement.executeQuery( consulta );
			while( rs.next() ) { // Leer el resultset
				String direccion = rs.getString("direccion");
				String nomCiu = rs.getString("nomCiu");
				String nomPais = rs.getString("nomPais");
				listaLugares.add(new Lugar (direccion, nomCiu, nomPais) );
			}
			return listaLugares;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	/** 
	 * Para obtener un lugar concreto de uno de los usuarios de la base de datos
	 * @return	Lugar/Vivienda de una persona registrada en nuestra plataforma, null si hay algún error
	 */
	
	public static Lugar getLugar(Usuario usuario) {
		try (Statement statement = conexion.createStatement()) {
			String direccion = usuario.getVivienda().getDireccion();
			consulta = "SELECT * FROM Lugar WHERE direccion = " + direccion + ";";
			logger.log( Level.INFO, "Statement: " + consulta );
			Lugar vivienda = (Lugar) statement.executeQuery( consulta );
			return vivienda;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	
	// QUEDA HACER INSERTARLUGAR pero es bastante complejo porque solo se crea un lugar mediante un Usuario
	
	/**
	 * Inserta un lugar/vivienda en la base de datos abierta 
	 * @param lugar			un nuevo luegar recién registrado mediante un nuevo usuario que se introducirá en la base de Datos
	 * @return				true si la inserción es correcta, false en caso contrario
	 */
	public static boolean insertarLugar( Lugar lugar ) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO Lugar (direccion, nomCiu, nomPais)"
					+ "VALUES ('" + lugar.getDireccion() + "', '" + lugar.getNomCiu() + "', '" + lugar.getNomPais() + "')";

			logger.log( Level.INFO, "Statement: " + consulta );
			int insertados = statement.executeUpdate( consulta );
			if (insertados!=1) return false;  // Error en inserción
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	
	/**
	 * Eliminar un Lugar de la base de datos 
	 * @param vivienda			el lugar que se desea eliminar
	 * @return				true si se ha eliminado correctamente, false en caso contrario
	 */
	
	public static boolean eliminarLugar(Lugar vivienda) {
		String direccion = vivienda.getDireccion();
		try {
			Statement statement = conexion.createStatement();
			consulta = "DELETE FROM Lugar WHERE direccion = '" + direccion + "';";
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	
	/**
	 * Modificar un usuario en la base de datos abierta 
	 * @param 
	 * @return											true si se ha modificado correctamente, false en caso contrario
	 */
	
	public static boolean modificarLugar(String direccion, String nomCiu, String nomPais) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "UPDATE Lugar SET direccion = '" + direccion + "', nomCiu = '" + nomCiu + "', nomPais = " + nomPais + "';";
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	} 

}
