package BaseDatos;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import clases.Calzado;
import clases.Colores;
import clases.CuentaBancaria;
import clases.Estado;
import clases.FuncionesGenerales;
import clases.Lugar;
import clases.Pedido;
import clases.Producto;
import clases.Ropa;
import clases.TallasRopa;
import clases.Usuario;

public class BaseDeDatos {

	private static Connection conexion;
	private static Logger logger = Logger.getLogger("BaseDeDatos");
	static String consulta;

	/**
	 * Abre conexión con la base de datos
	 * @param nombreBD   Nombre del fichero de base de datos
	 * @param reiniciaBD true si se quiere reiniciar la base de datos (se borran sus contenidos si los tuviera y se crean datos por defecto)
	 * @return true si la conexión ha sido correcta, false en caso contrario
	 * @throws SQLException
	 */

	public static boolean abrirConexion(String nombreBD, boolean conexionBD) throws SQLException {

		String consulta;
		Scanner scanner;

		try {

			logger.log(Level.INFO, "Carga de librería org.sqlite.JDBC");
			Class.forName("org.sqlite.JDBC"); // Carga la clase de BD para sqlite
			logger.log(Level.INFO, "Abriendo conexión con " + nombreBD);
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);

			if (!conexionBD) {

				Statement statement = conexion.createStatement();

				System.out.println("x3");
				crearTablaBDUsuario();
				System.out.println("xCuenta1");
				crearTablaBDCuentaBancaria();
				System.out.println("xCuenta2");
				crearTablaBDLugar();
				System.out.println("x1");
				crearTablaBDProducto();
				crearTablaBDCalzado();
				crearTablaBDRopa();
				crearTablaBDPedido();
				crearTablaBDFavoritos();
				crearTablaBDComentarios();
				System.out.println("x2");
				
				cargarUsuario();
				cargarCuentaBancaria();
				cargarLugar();
				cargarProducto();
				cargarCalzado();
				cargarRopa();
				cargarPedido();
				cargarFavoritos();
				cargarComentarios();

			}
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Cierra la conexión abierta de Base de Datos
	 */

	public static void cerrarConexion() {
		try {
			conexion.close();
			System.out.println("Conexión cerrada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*********************************************************************************************************************************************************
	 * 																CREACIÓN DE TABLAS
	 **********************************************************************************************************************************************************/

	/**
	 * Crea la tabla Usuario
	 **/

	public static void crearTablaBDUsuario() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Usuario";
		logger.log(Level.INFO, "Statement: " + consulta);
		statement.executeUpdate(consulta);

		consulta = "CREATE TABLE Usuario "
				+ "(idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono INTEGER, nTarjeta INTEGER, saldo REAL DEFAULT 5, email TEXT, "
				+ " contrasenia TEXT, direccion TEXT, FOREIGN KEY (nTarjeta) REFERENCES CuentaBancaria (nTarjeta), FOREIGN KEY (direccion) REFERENCES Lugar (direccion));";

		if (statement == null)
			return;
		try {
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();
		}
	}

	/**
	 * Crea la tabla CuentaBancaria
	 **/

	public static void crearTablaBDCuentaBancaria() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS CuentaBancaria";
		logger.log(Level.INFO, "Statement: " + consulta);
		statement.executeUpdate(consulta);

		consulta = "CREATE TABLE CuentaBancaria "
				+ "(idUsuario INTEGER, nTarjeta INTEGER PRIMARY KEY, dineroTotal REAL, "
				+ "FOREIGN KEY (idUsuario) REFERENCES CuentaBancaria (idUsuario));";

		if (statement == null)
			return;
		try {
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();
		}
	}

	/**
	 * Crea la tabla Lugar
	 **/

	public static void crearTablaBDLugar() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Lugar";
		logger.log(Level.INFO, "Statement: " + consulta);
		statement.executeUpdate(consulta);

		consulta = "CREATE TABLE Lugar "
				+ "(direccion INTEGER PRIMARY KEY, nomCiud TEXT, nomPais TEXT);";

		if (statement == null)
			return;
		try {
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();
		}
	}

	/**
	 * Crea la tabla Pedido
	 **/

	public static void crearTablaBDPedido() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Pedido";
		logger.log(Level.INFO, "Statement: " + consulta);
		statement.executeUpdate(consulta);

		consulta = "CREATE TABLE Pedido "
				+ "(numeroPedido INTEGER, precioTotal REAL, fechaCompra NUMERIC, fechaEntrega NUMERIC, idUsuario INT, idProducto INT,"
				+ "FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario), FOREIGN KEY (idProducto) REFERENCES Producto (idProducto));";

		if (statement == null)
			return;
		try {
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepci�n es que la tabla ya exist�a (lo cual es correcto)
			// e.printStackTrace();
		}
	}

	/**
	 * Crea la tabla Calzado
	 **/

	public static void crearTablaBDCalzado() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Calzado";
		logger.log(Level.INFO, "Statement: " + consulta);
		statement.executeUpdate(consulta);

		consulta = "CREATE TABLE Calzado "
				+ "(idProducto INTEGER PRIMARY KEY, nombre TEXT, fechaSubida NUMERIC, etiquetas TEXT, precio REAL, "
				+ "imagen TEXT, estado TEXT, color TEXT, idUsuario INTEGER, enVenta TEXT, tallaCalzado REAL, "
				+ "FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario));";

		if (statement == null)
			return;
		try {
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();
		}
	}

	/**
	 * Crea la tabla Ropa
	 **/

	public static void crearTablaBDRopa() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Ropa";
		logger.log(Level.INFO, "Statement: " + consulta);
		statement.executeUpdate(consulta);

		consulta = "CREATE TABLE Ropa "
				+ "(idProducto INTEGER PRIMARY KEY, nombre TEXT, fechaSubida NUMERIC, etiquetas NUMERIC, precio REAL, "
				+ "imagen TEXT, estado TEXT, color TEXT, idUsuario INTEGER, enVenta TEXT, tallaCalzado TEXT, "
				+ "FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario));";

		if (statement == null)
			return;
		try {
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();
		}
	}

	/**
	 * Crea la tabla Producto Esta tabla tendrá todas las tuplas tanto de calzado como de ropa.
	 **/

	public static void crearTablaBDProducto() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Producto";
		logger.log(Level.INFO, "Statement: " + consulta);
		statement.executeUpdate(consulta);

		consulta = "CREATE TABLE Producto "
				+ "(idProducto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, fechaSubida NUMERIC, etiquetas TEXT,  "
				+ "precio REAL, imagen TEXT, estado TEXT, color TEXT, idUsuario INTEGER, enVenta TEXT, tipoProducto TEXT, "
				+ "FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario));";

		if (statement == null)
			return;
		try {
			logger.log(Level.INFO, "Statement: " + consulta);
			System.out.println("xxx");
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			e.printStackTrace();
			//System.out.println();
		}
	}

	/**
	 * Crea la tabla Favoritos
	 **/

	public static void crearTablaBDFavoritos() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Favoritos";
		logger.log(Level.INFO, "Statement: " + consulta);
		statement.executeUpdate(consulta);

		consulta = "CREATE TABLE Favoritos "
				+ "(idUsuario INTEGER PRIMARY KEY, idProducto INTEGER, fechaInsertada NUMERIC, "
				+ "FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario), FOREIGN KEY (idProducto) REFERENCES Producto (idProducto));";

		if (statement == null)
			return;
		try {
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();
		}
	}

	/**
	 * Crea la tabla Comentarios
	 **/

	public static void crearTablaBDComentarios() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Comentarios";
		logger.log(Level.INFO, "Statement: " + consulta);
		statement.executeUpdate(consulta);

		consulta = "CREATE TABLE Comentarios "
				+ "(idUsuario INTEGER, idProducto INTEGERL, comentario TEXT, "
				+ "FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario), FOREIGN KEY (idProducto) REFERENCES Producto (idProducto));";

		if (statement == null)
			return;
		try {
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
		} catch (SQLException e) {
			// Si hay excepción es que la tabla ya existía (lo cual es correcto)
			// e.printStackTrace();
		}
	}
	
	
	/*********************************************************************************************************************************************************
	 * 																CARGAR DATOS
	 **********************************************************************************************************************************************************/

	/**
	 * Carga los datos del archivo Usuario.csv y los mete en la tabla Usuario
	 * @throws SQLException 
	 * @throws IOException 
	 * **/
	
	public static void cargarUsuario () throws SQLException, IOException {
		
		Statement statement = conexion.createStatement();
		
		BufferedReader reader = new BufferedReader(new FileReader("Usuario.csv"));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			
			consulta = "INSERT INTO Usuario (idUsuario, nombre, telefono, nTarjeta, saldo, email, contrasenia, direccion)"
					+ "VALUES (" + tokens[0] + ", '" + tokens[1] + "', " + tokens[2] + ", " + tokens[3] + ", "
					+ tokens[4] + ", '" + tokens[5] + "', '" + tokens[6] + "', '" + tokens[7] + "');";
			
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			
		}
		reader.close();
		
	}
	
	
	/**
	 * Carga los datos del archivo CuentaBancaria.csv y los mete en la tabla CuentaBancaria
	 * @throws SQLException 
	 * @throws IOException 
	 * **/
	
	public static void cargarCuentaBancaria () throws SQLException, IOException {
		
		Statement statement = conexion.createStatement();
		
		BufferedReader reader = new BufferedReader(new FileReader("CuentaBancaria.csv"));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			
			consulta = "INSERT INTO CuentaBancaria (idUsuario, nTarjeta, dineroTotal)" + "VALUES ("
					+ tokens[0] + ", " + tokens[1] + ", " + tokens[2] + ");";
			
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			
		}
		reader.close();
		
	}
	
	
	/**
	 * Carga los datos del archivo Lugar.csv y los mete en la tabla Lugar
	 * @throws SQLException 
	 * @throws IOException 
	 * **/
	
	public static void cargarLugar () throws SQLException, IOException {
		
		Statement statement = conexion.createStatement();
		
		BufferedReader reader = new BufferedReader(new FileReader("Lugar.csv"));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			
			consulta = "INSERT INTO Lugar (direccion, nomCiud, nomPais)" + "VALUES ('" 
					+ tokens[0] + "', " + tokens[1] + ", '" + tokens[2] + "');";
			
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			
		}
		reader.close();
		
	}
	
	
	/**
	 * Carga los datos del archivo Producto.csv y los mete en la tabla Producto
	 * @throws SQLException 
	 * @throws IOException 
	 * **/
	
	public static void cargarProducto () throws SQLException, IOException {
		
		Statement statement = conexion.createStatement();
		
		BufferedReader reader = new BufferedReader(new FileReader("Producto.csv"));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			
			consulta = "INSERT INTO Producto (idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, idUsuario, enVenta, tipoProducto)"
					+ "VALUES (" + tokens[0] + ", '" + tokens[1] + "', '" + tokens[2] + "', '" + tokens[3] + "', " + tokens[4] + ", '" + tokens[5] 
					+ "', '" + tokens[6] + "', '" + tokens[7] + "', " + tokens[8] + ", " + tokens[9] + ", '" + tokens[10] + "');";
			
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			
		}
		reader.close();
		
	}
	
	
	/**
	 * Carga los datos del archivo Calzado.csv y los mete en la tabla Calzado
	 * @throws SQLException 
	 * @throws IOException 
	 * **/
	
	public static void cargarCalzado () throws SQLException, IOException {
		
		Statement statement = conexion.createStatement();
		
		BufferedReader reader = new BufferedReader(new FileReader("Calzado.csv"));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			
			consulta = "INSERT INTO Calzado (idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, idUsuario, enVenta, tallaCalzado)"
					+ "VALUES (" + tokens[0] + ", '" + tokens[1] + "', '" + tokens[2] + "', '" + tokens[3] + "', " + tokens[4] + ", '" + tokens[5] + "', '"
					+ tokens[6] + "', '" + tokens[7] + "', " + tokens[8] + ", " + tokens[9] + ", " + tokens[10] + ");";
			
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			
		}
		reader.close();
		
	}
	
	
	/**
	 * Carga los datos del archivo Ropa.csv y los mete en la tabla Ropa
	 * @throws SQLException 
	 * @throws IOException 
	 * **/
	
	public static void cargarRopa () throws SQLException, IOException {
		
		Statement statement = conexion.createStatement();
		
		BufferedReader reader = new BufferedReader(new FileReader("Ropa.csv"));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			
			consulta = "INSERT INTO Ropa (idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, idUsuario, enVenta, tallaRopa)"
					+ "VALUES (" + tokens[0] + ", '" + tokens[1] + "', '" + tokens[2] + "', '" + tokens[3] + "', " + tokens[4] + ", '"
					+ tokens[5] + "', '" + tokens[6] + "', '" + tokens[7] + "', " + tokens[8] + ", " + tokens[9] + ", '" + tokens[10] + "');";
			
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			
		}
		reader.close();
		
	}
	
	
	/**
	 * Carga los datos del archivo Pedido.csv y los mete en la tabla Pedido
	 * @throws SQLException 
	 * @throws IOException 
	 * **/
	
	public static void cargarPedido () throws SQLException, IOException {
		
		Statement statement = conexion.createStatement();
		
		BufferedReader reader = new BufferedReader(new FileReader("Pedido.csv"));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			
			consulta = "INSERT INTO Pedido (precioTotal, fechaCompra, fechaEntrega, numeroPedido, idUsuario, idComprador)"
					+ "VALUES (" + tokens[0] + ", '" + tokens[1] + "', '" + tokens[2] + "', " + tokens[3] + ", " + tokens[4] + ", " + tokens[5] + ");";
			
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			
		}
		reader.close();
		
	}
	
	
	/**
	 * Carga los datos del archivo Favoritos.csv y los mete en la tabla Favoritos
	 * @throws SQLException 
	 * @throws IOException 
	 * **/
	
	public static void cargarFavoritos () throws SQLException, IOException {
		
		Statement statement = conexion.createStatement();
		
		BufferedReader reader = new BufferedReader(new FileReader("Favoritos.csv"));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			
			consulta = "INSERT INTO Favoritos (idProducto, idUsuario)"
					+ "VALUES (" + tokens[0] + ", " + tokens[1] + ", '" + tokens[2] + "');";
			
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			
		}
		reader.close();
		
	}
	
	
	/**
	 * Carga los datos del archivo Comentarios.csv y los mete en la tabla Comentarios
	 * @throws SQLException 
	 * @throws IOException 
	 * **/
	
	public static void cargarComentarios () throws SQLException, IOException {
		
		Statement statement = conexion.createStatement();
		
		BufferedReader reader = new BufferedReader(new FileReader("Comentarios.csv"));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			
			consulta = "INSERT INTO Comentarios (idProducto, idUsuario, comentario) "
					+ "VALUES (" + tokens[0] + ", " + tokens[1] + ", '" + tokens[2] + "');";
			
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			
		}
		reader.close();
		
	}
	
	

	/*********************************************************************************************************************************************************
	 * 																TABLA USUARIO
	 **********************************************************************************************************************************************************/


public static ArrayList<Usuario> getUsuarios() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Usuario> listaUsuarios = new ArrayList<>();
			consulta = "SELECT * FROM Usuario;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			while (rs.next()) { // Leer el resultset

				int idUsuario = rs.getInt("idUsuario");
				String nombre = rs.getString("nombre");
				int telefono = rs.getInt("telefono");
				int nTarjeta1 = rs.getInt("nTarjeta");
				double saldo = rs.getDouble("saldo");
				String email = rs.getString("email");
				String contrasenia = rs.getString("contrasenia");
				String direccion = rs.getString("direccion");
				Date fechaRegistro = rs.getDate("fechaRegistro");

				CuentaBancaria cuenta = getCuentaBancaria(idUsuario);

				Lugar vivienda = getLugar(direccion);

				ArrayList<Producto> listaProductos = getProductosCompleto(idUsuario);
				ArrayList<Producto> productosEnVenta = new ArrayList<>();
				ArrayList<Producto> productosVendidos = new ArrayList<>();

				for (Producto p : listaProductos) {
					if (p.isEnVenta()) {
						productosEnVenta.add(p);
					} else {
						productosVendidos.add(p);
					}
				}

				ArrayList<Producto> productosComprados = new ArrayList<>();

				ArrayList<Producto> productosFavoritos = getFavoritosUsuario(idUsuario);
				

				listaUsuarios.add(new Usuario(idUsuario, nombre, telefono, cuenta, saldo, email, contrasenia, vivienda,
						productosEnVenta, productosVendidos, productosComprados, productosFavoritos, fechaRegistro));
			}
			return listaUsuarios;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Usuario de la base de datos con un identificativo concreto
	 * @param idU 		Identificativo del usuario buscado.
	 * @return 			Lista completa de los Usuarios de nuestra plataforma, null si hay  algún error
	 */

	public static Usuario getUsuario(int idU) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT * FROM Usuario WHERE idUsuario = " + idU + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);

			int idUsuario = rs.getInt("idUsuario");
			String nombre = rs.getString("nombre");
			int telefono = rs.getInt("telefono");
			int nTarjeta1 = rs.getInt("nTarjeta");
			double saldo = rs.getDouble("saldo");
			String email = rs.getString("email");
			String contrasenia = rs.getString("contrasenia");
			String direccion = rs.getString("direccion");

			Date fechaRegistro = rs.getDate("fechaRegistro");
			CuentaBancaria cuenta = getCuentaBancaria(idUsuario);

			Lugar vivienda = getLugar(direccion);

			ArrayList<Producto> listaProductos = getProductosCompleto(idUsuario);
			ArrayList<Producto> productosEnVenta = new ArrayList<>();
			ArrayList<Producto> productosVendidos = new ArrayList<>();

			for (Producto p : listaProductos) {
				if (p.isEnVenta()) {
					productosEnVenta.add(p);
				} else {
					productosVendidos.add(p);
				}
			}

			ArrayList<Producto> productosComprados = new ArrayList<>();
			ArrayList<Producto> productosFavoritos = getFavoritosUsuario(idUsuario);

			Usuario usuario = new Usuario(idUsuario, nombre, telefono, cuenta, saldo, email, contrasenia, vivienda,
					productosEnVenta, productosVendidos, productosComprados, productosFavoritos, fechaRegistro);

			return usuario;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Se busca un usuario concreto de la base de datos que cumpla los requisitos de
	 * un nombre y una contraseña Nos ayudará a la hora de iniciar sesión. El
	 * usuario meterá estos parametros y si este método devuelve un usuario será que
	 * existe. De no ser así, será que el nombre o la contraseña están mal o dicho
	 * usuario no está registrado en la base de datos. Por lo tanto tendrá que
	 * crearse una cuenta nueva.
	 * 
	 * @param nombre 		Nombre del usuario
	 * @param contra 		Contraseña del usuario
	 * @return 				Lista completa de los Usuarios de nuestra plataforma, null si hay  algún error
	 */

	public static Usuario getUsuarios(String nombre, String contra) {
		try (Statement statement = conexion.createStatement()) {
			String contraseña = FuncionesGenerales.code(contra);
			consulta = "SELECT * FROM Usuario WHERE nombre = '" + nombre + "' AND contrasenia = '" + contraseña + "';";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);

			if (rs != null) {

				int idUsuario = rs.getInt("idUsuario");
				String nom = rs.getString("nombre");
				int telefono = rs.getInt("telefono");
				int nTarjeta1 = rs.getInt("nTarjeta");
				double saldo = rs.getDouble("saldo");
				String email = rs.getString("email");
				String contrasenia = rs.getString("contrasenia");
				String direccion = rs.getString("direccion");

				CuentaBancaria cuenta = getCuentaBancaria(idUsuario);

				Date fechaRegistro = rs.getDate("fechaRegistro");
				Lugar vivienda = getLugar(direccion);

				ArrayList<Producto> listaProductos = getProductosCompleto(idUsuario);
				ArrayList<Producto> productosEnVenta = new ArrayList<>();
				ArrayList<Producto> productosVendidos = new ArrayList<>();

				for (Producto p : listaProductos) {
					if (p.isEnVenta()) {
						productosEnVenta.add(p);
					} else {
						productosVendidos.add(p);
					}
				}

				ArrayList<Producto> productosComprados = new ArrayList<>();

				ArrayList<Producto> productosFavoritos = getFavoritosUsuario(idUsuario);

				Usuario usuario = new Usuario(idUsuario, nombre, telefono, cuenta, saldo, email, contrasenia, vivienda,
						productosEnVenta, productosVendidos, productosComprados, productosFavoritos, fechaRegistro);

				return usuario;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}



	/**
	 * Inserta un usuario en la base de datos abierta
	 * @param usuario 		Un nuevo usuario recién registrado que se introducirá en la base de Datos
	 * @return 				True si la inserción es correcta, false en caso contrario
	 */
	public static boolean insertarUsuario(Usuario usuario) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO Usuario (idUsuario, nombre, telefono, nTarjeta, saldo, email, contrasenia, direccion)"
					+ "VALUES (" + usuario.getIdUsuario() + ", '" + usuario.getNombre() + "', " + usuario.getTelefono() + ", " + usuario.getCuentaB().getnTarjeta() 
					+ ", " + usuario.getSaldo() + ", '" + usuario.getEmail() + "', '" + usuario.getContrasenia() + "', '" + usuario.getVivienda().getDireccion() + "')";
			// Al ser un usuario nuevo, no tendrá ningún producto en venta, venidido,
			// comprado ni en favoritos

			logger.log(Level.INFO, "Statement: " + consulta);
			int insertados = statement.executeUpdate(consulta);
			if (insertados != 1)
				return false; // Error en inserción
			// Búsqueda de la fila insertada - para ello hay que recuperar la clave
			// autogenerada. Hay varias maneras, vemos dos diferentes:
			// Se hace utilizando método del propio objeto statement
			ResultSet rrss = statement.getGeneratedKeys(); // Genera un resultset ficticio con las claves generadas del
															// último comando
			rrss.next(); // Avanza a la única fila
			int pk = rrss.getInt(1); // Coge la única columna (la primary key autogenerada)
			usuario.setIdUsuario(pk);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Eliminar un usuario de la base de datos abierta
	 * @param usuario 		El usuario que quiere borrar su cuenta de DeustoPop
	 * @return 				True si se ha eliminado correctamente, false en caso contrario
	 */

	public static boolean eliminarUsuario(Usuario usuario) {
		int id = usuario.getIdUsuario();
		try {
			Statement statement = conexion.createStatement();
			consulta = "DELETE FROM Usuario WHERE id = " + id + ";";

			// ELIMINAR LOS PRODUCTOS

			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Modificar un usuario en la base de datos abierta
	 * @param 			CASI todos los atributos del Usuario actualiza todos (excepto el ID (ya que ese es definitivo) y la contrasenia), sin importar que sean iguales
	 * @return 			True si se ha modificado correctamente, false en caso contrario
	 */

	public static boolean modificarUsuario(int id, String nombre, int telefono, int nTarjeta, double saldo,
			String email, String direccion) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "UPDATE Usuario SET nombre = '" + nombre + "', telefono = " + telefono + ", nTarjeta = " + nTarjeta
					+ ", saldo = " + saldo + ", email = '" + email + "', direccion = '" + direccion + "' WHERE idUsuario = " + id + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Revisa en la base de datos si hay algún usuario en DeustoPop con los parametros introducidos
	 * @param nombre 		Revisa si existe algún nombre de usuario como el introducido
	 * @return 				True si existe, false si no existe
	 **/

	public static boolean existeNombreUsuario(String nombre) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT * FROM Usuario WHERE nombre = '" + nombre + "';";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			if (rs == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Revisa en la base de datos si hay algún usuario en DeustoPop con los parametros introducidos
	 * @param email 		Revisa si existe algún email de usuario como el introducido
	 * @return 				True si existe, false si no existe
	 **/

	public static boolean existeEmailUsuario(String email) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT * FROM Usuario WHERE email = '" + email + "';";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			if (rs == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/*********************************************************************************************************************************************************
	 * 																TABLA CUENTABANCARIA
	 **********************************************************************************************************************************************************/

	/**
	 * Para obtener un listado de todas las cuentas bancarias de la base de datos
	 * @return			Lista completa de las cuentas bancarias de nuestra plataforma, null si hay algún error
	 */

	public static ArrayList<CuentaBancaria> getCuentaBancaria() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<CuentaBancaria> listaCuentasB = new ArrayList<>();
			consulta = "SELECT nTarjeta, dineroTotal FROM CuentaBancaria;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			while (rs.next()) { // Leer el resultset
				int nTarjeta = rs.getInt("nTarjeta");
				double dineroTotal = rs.getDouble("dineroTotal");
				listaCuentasB.add(new CuentaBancaria(nTarjeta, dineroTotal));
			}
			return listaCuentasB;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Para obtener una cuenta bancaria concreta de uno de los usuarios de la base de datos
	 * @param idUsuario 		Identificativo del usuario del que se quiere obtener la cuenta
	 * @return 					Cuenta bancaria de una persona registrada en nuestra plataforma, null si hay algún error
	 */

	public static CuentaBancaria getCuentaBancaria(int idUsuario) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT nTarjeta, dineroTotal FROM CuentaBancaria WHERE idUsuario = " + idUsuario + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			int nTarjeta = rs.getInt("nTarjeta");
			double dineroTotal = rs.getDouble("dineroTotal");
			CuentaBancaria cuenta = new CuentaBancaria(nTarjeta, dineroTotal);
			return cuenta;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Inserta una nueva cuenta bancaria en la base de datos abierta
	 * @param cuenta 		Una nueva cuenta bancaria recién registrada mediante un nuevo usuario que se introducirá en la base de Datos o que desea cambiar su cuenta
	 * @param usuario		Usuario al que estará relacionada la cuenta
	 * @return 				True si la inserción es correcta, false en caso contrario
	 */

	public static boolean insertarCuentaBancaria(CuentaBancaria cuenta, Usuario usuario) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO CuentaBancaria (idUsuario, nTarjeta, dineroTotal)" + "VALUES ("
					+ usuario.getIdUsuario() + ", " + cuenta.getnTarjeta() + "', '" + cuenta.getDineroTotal() + "')";
			logger.log(Level.INFO, "Statement: " + consulta);
			int insertados = statement.executeUpdate(consulta);
			if (insertados != 1)
				return false; // Error en inserción
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Eliminar una cuenta de la base de datos
	 * @param cuenta 		La cuenta que se desea eliminar
	 * @return 				True si se ha eliminado correctamente, false en caso contrario
	 */

	public static boolean eliminarCuentaBancaria(CuentaBancaria cuenta) {
		int nTarjeta = cuenta.getnTarjeta();
		try {
			Statement statement = conexion.createStatement();
			consulta = "DELETE FROM CuentaBancaria WHERE nTarjeta = " + nTarjeta + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Modificar una cuenta en la base de datos abierta
	 * @param idUsuario   	Identificativo del usuario de la cuenta 
	 * @para, nTarjeta		Numero de tarjeta de la cuenta que se puede llegar a cambiar
	 * @param dineroTotal 	Dinero que tiene la cuenta. Esto podrá variar en caso de comprar o vender un producto
	 * @return 				True si se ha modificado correctamente, false en caso contrario
	 */

	public static boolean modificarCuentaBancaria(int idUsuario, int nTarjeta, double dineroTotal) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "UPDATE CuentaBancaria SET nTarjeta = " + nTarjeta + ", dineroTotal = " + dineroTotal
					+ "  WHERE idUsuario = " + idUsuario + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/*********************************************************************************************************************************************************
	 * 																	TABLA LUGAR
	 **********************************************************************************************************************************************************/

	/**
	 * Para obtener un listado de todos los lugares de la base de datos
	 * @return 		Lista completa de los Lugares/Viviendas de nuestra plataforma, null si hay algún error
	 */

	public static ArrayList<Lugar> getLugar() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Lugar> listaLugares = new ArrayList<>();
			consulta = "SELECT * FROM Lugar;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			while (rs.next()) { // Leer el resultset
				String direccion = rs.getString("direccion");
				String nomCiud = rs.getString("nomCiud");
				String nomPais = rs.getString("nomPais");
				listaLugares.add(new Lugar(direccion, nomCiud, nomPais));
			}
			return listaLugares;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Para obtener un lugar concreto de la base de datos
	 * @param direccion			Direccion concreta que se busca para obtener el lugar
	 * @return 					Lugar/Vivienda de una persona registrada en nuestra plataforma, null si hay algún error
	 */

	public static Lugar getLugar(String direccion) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT nomCiud, nomPais FROM Lugar WHERE direccion = '" + direccion + "';";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			String nomCiud = rs.getString("nomCiud");
			String nomPais = rs.getString("nomPais");
			Lugar vivienda = new Lugar(direccion, nomCiud, nomPais);
			return vivienda;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Inserta un lugar/vivienda en la base de datos abierta
	 * @param lugar 		Un nuevo luegar recién registrado (o actualizado) mediante un usuario que se introducirá en la base de Datos o que se mude
	 * @return 				True si la inserción es correcta, false en caso contrario
	 */

	public static boolean insertarLugar(Lugar vivienda) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO Lugar (direccion, nomCiud, nomPais) "
					+ "VALUES ('" + vivienda.getDireccion() + "', '" + vivienda.getNomCiud() + "', '" + vivienda.getNomPais() + "')";

			logger.log(Level.INFO, "Statement: " + consulta);
			int insertados = statement.executeUpdate(consulta);
			if (insertados != 1)
				return false; // Error en inserción
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Eliminar un Lugar de la base de datos
	 * @param vivienda 		El lugar que se desea eliminar
	 * @return 				True si se ha eliminado correctamente, false en caso contrario
	 */

	public static boolean eliminarLugar(Lugar vivienda) {
		String direccion = vivienda.getDireccion();
		try {
			Statement statement = conexion.createStatement();
			consulta = "DELETE FROM Lugar WHERE direccion = '" + direccion + "';";
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Modificar un usuario en la base de datos abierta
	 * @param direccionV 		Direccion vieja, será el identificativo para saber qué Lugar modificar
	 * @param direccion  		Direccion que se vaya a cambiar en la base de datos
	 * @param nomCiud 			Nombre de la ciudad que se vaya a cambiar en la base de datos
	 * @param nomPais   		Nombre del país que se vaya a cambiar en la base de datos
	 * @return 					True si se ha modificado correctamente, false en caso contrario
	 */

	public static boolean modificarLugar(String direccionV, String direccion, String nomCiud, String nomPais) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "UPDATE Lugar SET direccion = '" + direccion + "', nomCiud = '" + nomCiud + "', nomPais = "
					+ nomPais + "'  WHERE direccion = '" + direccionV + "';";
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/*********************************************************************************************************************************************************
	 * 																	TABLA PRODUCTO
	 **********************************************************************************************************************************************************/

	/**
	 * Obtener un producto concreto de la base de datos
	 * @param idProducto 		El identificativo del producto que se quiere conseguir.
	 * @return 					El producto buscado.
	 **/

	public static Producto getProducto(int idProducto) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT * FROM Producto WHERE idProducto =" + idProducto + " ;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			String nombre = rs.getString("nombre");
			Date fechaSubida = rs.getDate("fechaSubida");
			String etiquetas = rs.getString("etiquetas");
			Double precio = rs.getDouble("precio");
			String imagen = rs.getString("imagen");
			String estadoStr = rs.getString("estado");
			Estado estado = null;
			if (estadoStr == "MALO") {
				estado = Estado.MALO;
			} else if (estadoStr == "MEDIO") {
				estado = Estado.MEDIO;
			} else if (estadoStr == "BUENO") {
				estado = Estado.BUENO;
			}
			String colorStr = rs.getString("color");
			Colores color = null;
			if (colorStr == "Negro") {
				color = Colores.Negro;
			} else if (colorStr == "Blanco") {
				color = Colores.Blanco;
			} else if (colorStr == "Rojo") {
				color = Colores.Rojo;
			} else if (colorStr == "Azul") {
				color = Colores.Azul;
			} else if (colorStr == "Verde") {
				color = Colores.Verde;
			} else if (colorStr == "Gris") {
				color = Colores.Gris;
			} else if (colorStr == "Rosa") {
				color = Colores.Rosa;
			} else if (colorStr == "Amarillo") {
				color = Colores.Amarillo;
			} else if (colorStr == "Multicolor") {
				color = Colores.Multicolor;
			} else if (colorStr == "Otro") {
				color = Colores.Otro;
			}
			int idUsuario = rs.getInt("idUsuario");
			Usuario usuario = getUsuario(idUsuario);
			String enVentaStr = rs.getString("enVenta");
			Boolean enVenta;
			if (enVentaStr == "true") {
				enVenta = true;
			} else {
				enVenta = false;
			}
			HashMap<Usuario, String> comentarios = getComentariosProducto(idProducto);

			Producto p = new Producto(idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, comentarios);

			return p;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Esta función devolverá TODOS los productos que hay en la base de datos, y por lo tanto, en DeustoPop.
	 * Devolverá tanto los que están en venta como los queya están comprados. 
	 * Los devolverá todos de tipo Producto.
	 * @return 		Lista de productos
	 **/

	public static ArrayList<Producto> getProductos() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Producto> listaProductos = new ArrayList<>();
			consulta = "SELECT * FROM Producto;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			while (rs.next()) { // Leer el resultset
				int idProducto = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				Date fechaSubida = rs.getDate("fechaSubida");
				String etiquetas = rs.getString("etiquetas");
				Double precio = rs.getDouble("precio");
				String imagen = rs.getString("imagen");
				String estadoStr = rs.getString("estado");
				Estado estado = null;
				if (estadoStr == "MALO") {
					estado = Estado.MALO;
				} else if (estadoStr == "MEDIO") {
					estado = Estado.MEDIO;
				} else if (estadoStr == "BUENO") {
					estado = Estado.BUENO;
				}
				String colorStr = rs.getString("color");
				Colores color = null;
				if (colorStr == "Negro") {
					color = Colores.Negro;
				} else if (colorStr == "Blanco") {
					color = Colores.Blanco;
				} else if (colorStr == "Rojo") {
					color = Colores.Rojo;
				} else if (colorStr == "Azul") {
					color = Colores.Azul;
				} else if (colorStr == "Verde") {
					color = Colores.Verde;
				} else if (colorStr == "Gris") {
					color = Colores.Gris;
				} else if (colorStr == "Rosa") {
					color = Colores.Rosa;
				} else if (colorStr == "Amarillo") {
					color = Colores.Amarillo;
				} else if (colorStr == "Multicolor") {
					color = Colores.Multicolor;
				} else if (colorStr == "Otro") {
					color = Colores.Otro;
				}
				int idUsuario = rs.getInt("idUsuario");
				Usuario usuario = getUsuario(idUsuario);
				String enVentaStr = rs.getString("enVenta");
				Boolean enVenta;
				if (enVentaStr == "true") {
					enVenta = true;
				} else {
					enVenta = false;
				}
				HashMap<Usuario, String> comentarios = getComentariosProducto(idProducto);

				listaProductos.add(new Producto(idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, comentarios));
			}
			return listaProductos;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * DIFERENCIA ENTRE ESTA FUNCIÓN Y LA ANTERIOR: 
	 * La función anterior devolvía un ArrayList de tipo Producto (únicamente nivel de producto) de todos los productos de la plataforma.
	 * Esta función, en cambio, devuleve un ArrayList de tipo Producto, pero estos productos serán de las clases hijas.
	 * Es decir, devolverá o de tipo Calzado o de tipo Ropa.
	 **/

	public static ArrayList<Producto> getProductosCompleto() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Producto> listaProductos = new ArrayList<>();
			consulta = "SELECT * FROM Producto;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			while (rs.next()) { // Leer el resultset
				int idProducto = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				Date fechaSubida = rs.getDate("fechaSubida");
				String etiquetas = rs.getString("etiquetas");
				Double precio = rs.getDouble("precio");
				String imagen = rs.getString("imagen");
				String estadoStr = rs.getString("estado");
				Estado estado = null;
				if (estadoStr == "MALO") {
					estado = Estado.MALO;
				} else if (estadoStr == "MEDIO") {
					estado = Estado.MEDIO;
				} else if (estadoStr == "BUENO") {
					estado = Estado.BUENO;
				}
				String colorStr = rs.getString("color");
				Colores color = null;
				if (colorStr == "Negro") {
					color = Colores.Negro;
				} else if (colorStr == "Blanco") {
					color = Colores.Blanco;
				} else if (colorStr == "Rojo") {
					color = Colores.Rojo;
				} else if (colorStr == "Azul") {
					color = Colores.Azul;
				} else if (colorStr == "Verde") {
					color = Colores.Verde;
				} else if (colorStr == "Gris") {
					color = Colores.Gris;
				} else if (colorStr == "Rosa") {
					color = Colores.Rosa;
				} else if (colorStr == "Amarillo") {
					color = Colores.Amarillo;
				} else if (colorStr == "Multicolor") {
					color = Colores.Multicolor;
				} else if (colorStr == "Otro") {
					color = Colores.Otro;
				}
				int idUsuario = rs.getInt("idUsuario");
				Usuario usuario = getUsuario(idUsuario);
				String enVentaStr = rs.getString("enVenta");
				Boolean enVenta;
				if (enVentaStr == "true") {
					enVenta = true;
				} else {
					enVenta = false;
				}
				HashMap<Usuario, String> comentarios = getComentariosProducto(idProducto);
				String tipoProducto = rs.getString("tipoProducto");

				if (tipoProducto == "Calzado") {
					double talla = getCalzado(idProducto).getTallaCalzado();
					listaProductos.add(new Calzado(idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, talla, comentarios));
				} else if (tipoProducto == "Ropa") {
					TallasRopa talla = getRopa(idProducto).getTallaRopa();
					listaProductos.add(new Ropa(idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, talla, comentarios));
				}
			}
			return listaProductos;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Devuelve todos los productos (tanto en venta como vendidos) de un usuario concreto
	 * @param idUsuario 	El identificativo del usuario del que queremos saber sus productos
	 * @return 				Todos los productos del usuario
	 **/

	public static ArrayList<Producto> getProductosCompleto(int idUsuario) {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Producto> listaProductos = new ArrayList<>();
			consulta = "SELECT * FROM Producto WHERE idUsuario = " + idUsuario + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			while (rs.next()) { // Leer el resultset
				int idProducto = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				Date fechaSubida = rs.getDate("fechaSubida");
				String etiquetas = rs.getString("etiquetas");
				Double precio = rs.getDouble("precio");
				String imagen = rs.getString("imagen");
				String estadoStr = rs.getString("estado");
				Estado estado = null;
				if (estadoStr == "MALO") {
					estado = Estado.MALO;
				} else if (estadoStr == "MEDIO") {
					estado = Estado.MEDIO;
				} else if (estadoStr == "BUENO") {
					estado = Estado.BUENO;
				}
				String colorStr = rs.getString("color");
				Colores color = null;
				if (colorStr == "Negro") {
					color = Colores.Negro;
				} else if (colorStr == "Blanco") {
					color = Colores.Blanco;
				} else if (colorStr == "Rojo") {
					color = Colores.Rojo;
				} else if (colorStr == "Azul") {
					color = Colores.Azul;
				} else if (colorStr == "Verde") {
					color = Colores.Verde;
				} else if (colorStr == "Gris") {
					color = Colores.Gris;
				} else if (colorStr == "Rosa") {
					color = Colores.Rosa;
				} else if (colorStr == "Amarillo") {
					color = Colores.Amarillo;
				} else if (colorStr == "Multicolor") {
					color = Colores.Multicolor;
				} else if (colorStr == "Otro") {
					color = Colores.Otro;
				}
				int id = rs.getInt("idUsuario");
				Usuario usuario = getUsuario(idUsuario);
				String enVentaStr = rs.getString("enVenta");
				Boolean enVenta;
				if (enVentaStr == "true") {
					enVenta = true;
				} else {
					enVenta = false;
				}
				HashMap<Usuario, String> comentarios = getComentariosProducto(idProducto);
				String tipoProducto = rs.getString("tipoProducto");

				if (tipoProducto == "Calzado") {
					double talla = getCalzado(idProducto).getTallaCalzado();
					listaProductos.add(new Calzado(idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, talla, comentarios));
				} else if (tipoProducto == "Ropa") {
					TallasRopa talla = getRopa(idProducto).getTallaRopa();
					listaProductos.add(new Ropa(idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, talla, comentarios));
				}
			}
			return listaProductos;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Devuelve un ArrayList de todos los productos que están en venta, es decir que nadie ha comprado aún, en DeustoPop
	 * @return La lista de todos los productos (tanto Ropa como Calzado) de la base de datos (tanto en venta como vendidos)
	 **/

	public static ArrayList<Producto> getProductosEnVenta() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Producto> listaProductosEnVenta = new ArrayList<>();
			consulta = "SELECT * FROM Producto;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			while (rs.next()) { // Leer el resultset
				int idProducto = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				Date fechaSubida = rs.getDate("fechaSubida");
				String etiquetas = rs.getString("etiquetas");
				Double precio = rs.getDouble("precio");
				String imagen = rs.getString("imagen");
				String estadoStr = rs.getString("estado");
				Estado estado = null;
				if (estadoStr == "MALO") {
					estado = Estado.MALO;
				} else if (estadoStr == "MEDIO") {
					estado = Estado.MEDIO;
				} else if (estadoStr == "BUENO") {
					estado = Estado.BUENO;
				}
				String colorStr = rs.getString("color");
				Colores color = null;
				if (colorStr == "Negro") {
					color = Colores.Negro;
				} else if (colorStr == "Blanco") {
					color = Colores.Blanco;
				} else if (colorStr == "Rojo") {
					color = Colores.Rojo;
				} else if (colorStr == "Azul") {
					color = Colores.Azul;
				} else if (colorStr == "Verde") {
					color = Colores.Verde;
				} else if (colorStr == "Gris") {
					color = Colores.Gris;
				} else if (colorStr == "Rosa") {
					color = Colores.Rosa;
				} else if (colorStr == "Amarillo") {
					color = Colores.Amarillo;
				} else if (colorStr == "Multicolor") {
					color = Colores.Multicolor;
				} else if (colorStr == "Otro") {
					color = Colores.Otro;
				}
				int idUsuario = rs.getInt("idUsuario");
				Usuario usuario = getUsuario(idUsuario);
				String enVentaStr = rs.getString("enVenta");
				Boolean enVenta;
				if (enVentaStr == "true") {
					enVenta = true;
				} else {
					enVenta = false;
				}
				HashMap<Usuario, String> comentarios = getComentariosProducto(idProducto);

				if (enVenta) {
					listaProductosEnVenta.add(new Producto(idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, comentarios));
				}
			}
			return listaProductosEnVenta;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Para insertar un producto en DeustoPop, es decir, cuando se sube un artículo para venderlo en DeustoPop,
	 * se llamará a esta función para meterla en la base de datos.
	 * @param ropa    		Ropa que puede que se inserte
	 * @param calzado 		Calzado que puede que se inserte
	 * @return 				True si la inserción ha salido bien, false en caso contrario.
	 **/

	public static boolean insertarProducto(Ropa ropa, Calzado calzado) {
		try (Statement statement = conexion.createStatement()) {
			String tipo;
			// Solo se insertará o una prenda o un calzado. Para eso, uno será null y el otro no.
			// Habrá que revisar cuál de los dos es nulo y cuál no. El que no sea, null será el producto que se insertará.
			if (ropa != null) {
				tipo = "Ropa";
				consulta = "INSERT INTO Producto (idProducto, nombre, fechaSubida, etiquetas, precio, idUsuario, enVenta)"
						+ "VALUES (" + ropa.getId() + ", '" + ropa.getNombre() + "', '" + ropa.getFechaSubida() + "', '"
						+ ropa.getEtiquetas() + "', " + ropa.getPrecio() + ", " + ropa.getUsuario().getIdUsuario()
						+ ", '" + ropa.isEnVenta() + "', '" + tipo + "')";
			} else if (calzado != null) {
				tipo = "Calzado";
				consulta = "INSERT INTO Producto (idProducto, nombre, fechaSubida, etiquetas, precio, idUsuario, enVenta)"
						+ "VALUES (" + calzado.getId() + ", '" + calzado.getNombre() + "', '" + calzado.getFechaSubida()
						+ "', '" + calzado.getEtiquetas() + "', " + calzado.getPrecio() + ", "
						+ calzado.getUsuario().getIdUsuario() + ", '" + calzado.isEnVenta() + "', '" + tipo + "')";
			}
			logger.log(Level.INFO, "Statement: " + consulta);
			int insertados = statement.executeUpdate(consulta);
			if (insertados != 1)
				return false; // Error en inserción
			insertarCalzado(calzado);
			insertarRopa(ropa);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Un producto que esté en la base de datos, no podrá modificarse. Solamente podrá ser o comprado o eliminado totalmente. 
	 * Tenemos un método para eliminar el producto, solo nos queda uno para que cambie la situción del producto una vez se compra.
	 * Si se llama a este método, significará que el producto se habrá compraddo y por lo tanto, ya no está en venta.
	 * @param id 		Identificativo del producto que se quiere cambiar el atributo enVenta
	 * @return 			True si se ha modificado correctamente, false en caso contrario
	 **/

	public static boolean modificarProductoEnVenta(int id) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "UPDATE Producto SET enVenta = 'false' WHERE idProducto = " + id + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			modificarCalzadoEnVenta(id);
			modificarRopaEnVenta(id);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Si se quiere borrar completamente un producto de la base de datos y por lo tanto, de DeustoPop, se llamará a este método.
	 * @param producto 			Producto que se quiere eliminar de la plataforma
	 * @return 					True si se ha eliminado correctamente, false en caso contrario
	 **/

	public static boolean eliminarProducto(Producto producto) {
		int id = producto.getId();
		try {
			Statement statement = conexion.createStatement();
			consulta = "DELETE FROM Producto WHERE id = " + id + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			eliminarCalzado(producto.getId());
			eliminarRopa(producto.getId());
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/*********************************************************************************************************************************************************
	 * 																	TABLA CALZADO
	 **********************************************************************************************************************************************************/

	/**
	 * Devuelve el Calzado buscado
	 * @param idCalzado 		El identificativo del calzado que se busca
	 * @return 					El calzado buscado
	 **/

	public static Calzado getCalzado(int idProducto) {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Producto> listaProductos = new ArrayList<>();
			consulta = "SELECT * FROM Calzado WHERE idProducto = " + idProducto + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			int id = rs.getInt("idProducto");
			String nombre = rs.getString("nombre");
			Date fechaSubida = rs.getDate("fechaSubida");
			String etiquetas = rs.getString("etiquetas");
			Double precio = rs.getDouble("precio");
			String imagen = rs.getString("imagen");
			String estadoStr = rs.getString("estado");
			Estado estado = null;
			if (estadoStr == "MALO") {
				estado = Estado.MALO;
			} else if (estadoStr == "MEDIO") {
				estado = Estado.MEDIO;
			} else if (estadoStr == "BUENO") {
				estado = Estado.BUENO;
			}
			String colorStr = rs.getString("color");
			Colores color = null;
			if (colorStr == "Negro") {
				color = Colores.Negro;
			} else if (colorStr == "Blanco") {
				color = Colores.Blanco;
			} else if (colorStr == "Rojo") {
				color = Colores.Rojo;
			} else if (colorStr == "Azul") {
				color = Colores.Azul;
			} else if (colorStr == "Verde") {
				color = Colores.Verde;
			} else if (colorStr == "Gris") {
				color = Colores.Gris;
			} else if (colorStr == "Rosa") {
				color = Colores.Rosa;
			} else if (colorStr == "Amarillo") {
				color = Colores.Amarillo;
			} else if (colorStr == "Multicolor") {
				color = Colores.Multicolor;
			} else if (colorStr == "Otro") {
				color = Colores.Otro;
			}
			int idUsuario = rs.getInt("idUsuario");
			Usuario usuario = getUsuario(idUsuario);
			String enVentaStr = rs.getString("enVenta");
			Boolean enVenta;
			if (enVentaStr == "true") {
				enVenta = true;
			} else {
				enVenta = false;
			}
			double talla = rs.getDouble("tallaCalzado");

			HashMap<Usuario, String> comentarios = getComentariosProducto(idProducto);

			Calzado calzado = new Calzado(idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, talla, comentarios);

			return calzado;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Para obtener todos los productos de tipo Calzado de la plataforma DeustoPop
	 * @return 		Un ArrayList con todos los calzados
	 */

	public static ArrayList<Calzado> getTodosCalzados() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Calzado> listaCalzado = new ArrayList<>();
			consulta = "SELECT * FROM Calzado;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			while (rs.next()) {
				int id = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				Date fechaSubida = rs.getDate("fechaSubida");
				String etiquetas = rs.getString("etiquetas");
				Double precio = rs.getDouble("precio");
				String imagen = rs.getString("imagen");
				String estadoStr = rs.getString("estado");
				Estado estado = null;
				if (estadoStr == "MALO") {
					estado = Estado.MALO;
				} else if (estadoStr == "MEDIO") {
					estado = Estado.MEDIO;
				} else if (estadoStr == "BUENO") {
					estado = Estado.BUENO;
				}
				String colorStr = rs.getString("color");
				Colores color = null;
				if (colorStr == "Negro") {
					color = Colores.Negro;
				} else if (colorStr == "Blanco") {
					color = Colores.Blanco;
				} else if (colorStr == "Rojo") {
					color = Colores.Rojo;
				} else if (colorStr == "Azul") {
					color = Colores.Azul;
				} else if (colorStr == "Verde") {
					color = Colores.Verde;
				} else if (colorStr == "Gris") {
					color = Colores.Gris;
				} else if (colorStr == "Rosa") {
					color = Colores.Rosa;
				} else if (colorStr == "Amarillo") {
					color = Colores.Amarillo;
				} else if (colorStr == "Multicolor") {
					color = Colores.Multicolor;
				} else if (colorStr == "Otro") {
					color = Colores.Otro;
				}
				int idUsuario = rs.getInt("idUsuario");
				Usuario usuario = getUsuario(idUsuario);
				String enVentaStr = rs.getString("enVenta");
				Boolean enVenta;
				if (enVentaStr == "true") {
					enVenta = true;
				} else {
					enVenta = false;
				}
				double talla = rs.getDouble("tallaCalzado");

				HashMap<Usuario, String> comentarios = getComentariosProducto(id);

				Calzado calzado = new Calzado(id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, talla, comentarios);

				listaCalzado.add(calzado);
			}

			return listaCalzado;

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}

	}
	
	/**
	 * Un producto que esté en la base de datos, no podrá modificarse. Solamente podrá ser o comprado o eliminado totalmente. 
	 * Tenemos un método para eliminar el producto, solo nos queda uno para que cambie la situción del producto una vez se compra.
	 * Si se llama a este método, significará que el producto, concretamente uno de tipo calzado, se habrá compraddo y por lo tanto, ya no está en venta.
	 * Este método se llamará desde modificarProductoEnVenta, al igual que al metodo modificarRopaEnVenta.
	 * Por eso habrá que asegurarse que el producto existe en la tabla Calzado
	 * @param id 		Identificativo del producto que se quiere cambiar el atributo enVenta
	 * @return 			True si se ha modificado correctamente, false en caso contrario
	 **/

	public static boolean modificarCalzadoEnVenta(int id) {
		try {
			if (getCalzado(id) != null) {
				Statement statement = conexion.createStatement();
				consulta = "UPDATE Calzado SET enVenta = 'false' WHERE idProducto = " + id + ";";
				logger.log(Level.INFO, "Statement: " + consulta);
				statement.executeUpdate(consulta);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}
	
	/**
	 * Elimina un calzado concreto
	 * Este método lo llamará eliminarProducto, al igual que llamará a eliminarRopa. Por lo tanto, habrá que asegurarse de que el producto sí es un Calzado.
	 * Si al llamar al método getCalzado(idProducto) la respuesta no da null, será que sí es un calzado.
	 * @param idProducto 		El identificativo del calzado que se quiere eliminar de DeustoPop
	 * @return 					True si se ha eliminado correctamente, false en caso contrario
	 **/

	public static boolean eliminarCalzado(int idProducto) {
		try {
			if (getCalzado(idProducto) != null) {
				Statement statement = conexion.createStatement();
				consulta = "DELETE FROM Calzado WHERE idProducto = " + idProducto + ";";
				logger.log(Level.INFO, "Statement: " + consulta);
				statement.executeUpdate(consulta);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Para insertar un calzado en DeustoPop, es decir, cuando se sube un artículo para venderlo en DeustoPop, se llamará a esta función para meterla en la base de datos.
	 * Este método lo llamará eliminarProducto, al igual que llamará a eliminarRopa. Por lo tanto, habrá que asegurarse de que el producto sí es un Calzado.
	 * Si al llamar al método getCalzado(idProducto) la respuesta no da null, será que sí es un calzado.
	 * @param calzado 		Calzado que se inserte
	 * @return 				True si la inserción ha salido bien, false en caso contrario.
	 **/

	public static boolean insertarCalzado(Calzado calzado) {
		try (Statement statement = conexion.createStatement()) {
			if (getCalzado(calzado.getId()) != null) {
				consulta = "INSERT INTO Producto (idProducto, nombre, fechaSubida, etiquetas, precio, idUsuario, enVenta, tallaCalzado)"
					+ "VALUES (" + calzado.getId() + ", '" + calzado.getNombre() + "', '" + calzado.getFechaSubida() + "', '" + calzado.getEtiquetas() + "', " 
					+ calzado.getPrecio() + ", " + calzado.getUsuario().getIdUsuario() + ", '" + calzado.isEnVenta() + "', " + calzado.getTallaCalzado() + ")";

				logger.log(Level.INFO, "Statement: " + consulta);
				int insertados = statement.executeUpdate(consulta);
				if (insertados != 1)
					return false; // Error en inserción
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/*********************************************************************************************************************************************************
	 * 																	TABLA ROPA
	 **********************************************************************************************************************************************************/

	/**
	 * Devuelve el Calzado buscado
	 * @param idCalzado 		El identificativo del calzado que se busca
	 * @return 					El calzado buscado
	 **/

	public static Ropa getRopa(int idProducto) {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Producto> listaProductos = new ArrayList<>();
			consulta = "SELECT * FROM Ropa WHERE idProducto = " + idProducto + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			int id = rs.getInt("idProducto");
			String nombre = rs.getString("nombre");
			Date fechaSubida = rs.getDate("fechaSubida");
			String etiquetas = rs.getString("etiquetas");
			Double precio = rs.getDouble("precio");
			String imagen = rs.getString("imagen");
			String estadoStr = rs.getString("estado");
			Estado estado = null;
			if (estadoStr == "MALO") {
				estado = Estado.MALO;
			} else if (estadoStr == "MEDIO") {
				estado = Estado.MEDIO;
			} else if (estadoStr == "BUENO") {
				estado = Estado.BUENO;
			}
			String colorStr = rs.getString("color");
			Colores color = null;
			if (colorStr == "Negro") {
				color = Colores.Negro;
			} else if (colorStr == "Blanco") {
				color = Colores.Blanco;
			} else if (colorStr == "Rojo") {
				color = Colores.Rojo;
			} else if (colorStr == "Azul") {
				color = Colores.Azul;
			} else if (colorStr == "Verde") {
				color = Colores.Verde;
			} else if (colorStr == "Gris") {
				color = Colores.Gris;
			} else if (colorStr == "Rosa") {
				color = Colores.Rosa;
			} else if (colorStr == "Amarillo") {
				color = Colores.Amarillo;
			} else if (colorStr == "Multicolor") {
				color = Colores.Multicolor;
			} else if (colorStr == "Otro") {
				color = Colores.Otro;
			}
			int idUsuario = rs.getInt("idUsuario");
			Usuario usuario = getUsuario(idUsuario);
			String enVentaStr = rs.getString("enVenta");
			Boolean enVenta;
			if (enVentaStr == "true") {
				enVenta = true;
			} else {
				enVenta = false;
			}
			String tallaR = rs.getString("tallaRopa");
			TallasRopa talla = null;
			if (tallaR == "XS") {
				talla = TallasRopa.XS;
			} else if (tallaR == "S") {
				talla = TallasRopa.S;
			} else if (tallaR == "M") {
				talla = TallasRopa.M;
			} else if (tallaR == "L") {
				talla = TallasRopa.L;
			} else if (tallaR == "XL") {
				talla = TallasRopa.XL;
			}

			HashMap<Usuario, String> comentarios = getComentariosProducto(idProducto);

			Ropa ropa = new Ropa(idProducto, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, talla, comentarios);

			return ropa;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Para obtener todos los productos de tipo Ropa de la plataforma DeustoPop
	 * @return 			Arraylist con toda la ropa
	 */

	public static ArrayList<Ropa> getTodaRopa() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Ropa> listaRopa = new ArrayList<>();
			consulta = "SELECT * FROM Ropa;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			while (rs.next()) {
				int id = rs.getInt("idProducto");
				String nombre = rs.getString("nombre");
				Date fechaSubida = rs.getDate("fechaSubida");
				String etiquetas = rs.getString("etiquetas");
				Double precio = rs.getDouble("precio");
				String imagen = rs.getString("imagen");
				String estadoStr = rs.getString("estado");
				Estado estado = null;
				if (estadoStr == "MALO") {
					estado = Estado.MALO;
				} else if (estadoStr == "MEDIO") {
					estado = Estado.MEDIO;
				} else if (estadoStr == "BUENO") {
					estado = Estado.BUENO;
				}
				String colorStr = rs.getString("color");
				Colores color = null;
				if (colorStr == "Negro") {
					color = Colores.Negro;
				} else if (colorStr == "Blanco") {
					color = Colores.Blanco;
				} else if (colorStr == "Rojo") {
					color = Colores.Rojo;
				} else if (colorStr == "Azul") {
					color = Colores.Azul;
				} else if (colorStr == "Verde") {
					color = Colores.Verde;
				} else if (colorStr == "Gris") {
					color = Colores.Gris;
				} else if (colorStr == "Rosa") {
					color = Colores.Rosa;
				} else if (colorStr == "Amarillo") {
					color = Colores.Amarillo;
				} else if (colorStr == "Multicolor") {
					color = Colores.Multicolor;
				} else if (colorStr == "Otro") {
					color = Colores.Otro;
				}
				int idUsuario = rs.getInt("idUsuario");
				Usuario usuario = getUsuario(idUsuario);
				String enVentaStr = rs.getString("enVenta");
				Boolean enVenta;
				if (enVentaStr == "true") {
					enVenta = true;
				} else {
					enVenta = false;
				}
				String tallaR = rs.getString("tallaRopa");
				TallasRopa talla = null;
				if (tallaR == "XS") {
					talla = TallasRopa.XS;
				} else if (tallaR == "S") {
					talla = TallasRopa.S;
				} else if (tallaR == "M") {
					talla = TallasRopa.M;
				} else if (tallaR == "L") {
					talla = TallasRopa.L;
				} else if (tallaR == "XL") {
					talla = TallasRopa.XL;
				}

				HashMap<Usuario, String> comentarios = getComentariosProducto(id);

				Ropa ropa = new Ropa(id, nombre, fechaSubida, etiquetas, precio, imagen, estado, color, usuario, enVenta, talla, comentarios);

				listaRopa.add(ropa);
			}
			return listaRopa;

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}
	
	/**
	 * Elimina una prenda concreta de la base de datos, y por lo tanto, de DeustoPop.
	 * Este método lo llamará eliminarProducto, al igual que llamará a eliminarCalzado. Por lo tanto, habrá que asegurarse de que el producto sí es de tipo Ropa.
	 * Si al llamar al método getRopa(idProducto) la respuesta no da null, será que sí es de tipo ropa.
	 * @param idProducto 		El identificativo de la prenda que se quiere eliminar de DeustoPop
	 * @return 					True si se ha eliminado correctamente, false en caso contrario
	 **/

	public static boolean eliminarRopa(int idProducto) {
		try {
			if (getRopa(idProducto) != null) {
				Statement statement = conexion.createStatement();
				consulta = "DELETE FROM Ropa WHERE idProducto = " + idProducto + ";";
				logger.log(Level.INFO, "Statement: " + consulta);
				statement.executeUpdate(consulta);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}
	
	/**
	 * Un producto que esté en la base de datos, no podrá modificarse. Solamente podrá ser o comprado o eliminado totalmente. 
	 * Tenemos un método para eliminar el producto, solo nos queda uno para que cambie la situción del producto una vez se compra.
	 * Si se llama a este método, significará que el producto, concretamente uno de tipo ropa, se habrá comprado y por lo tanto, ya no está en venta.
	 * Este método se llamará desde modificarProductoEnVenta, al igual que al metodo modificarCalzadoEnVenta.
	 * Por eso habrá que asegurarse que el producto existe en la tabla Ropa
	 * @param id 		Identificativo del producto que se quiere cambiar el atributo enVenta
	 * @return 			True si se ha modificado correctamente, false en caso contrario
	 **/

	public static boolean modificarRopaEnVenta(int id) {
		try {
			if (getRopa(id) != null) {
				Statement statement = conexion.createStatement();
				consulta = "UPDATE Ropa SET enVenta = 'false' WHERE idProducto = " + id + ";";
				logger.log(Level.INFO, "Statement: " + consulta);
				statement.executeUpdate(consulta);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}
	
	/**
	 * Para insertar un calzado en DeustoPop, es decir, cuando se sube un artículo para venderlo en DeustoPop, se llamará a esta función para meterla en la base de datos.
	 * Este método lo llamará eliminarProducto, al igual que llamará a eliminarCalzado. Por lo tanto, habrá que asegurarse de que el producto sí es de tipo Ropa.
	 * Si al llamar al método getRopa(idProducto) la respuesta no da null, será que sí es de tipo.
	 * @param calzado 		Ropa que se inserte
	 * @return 				True si la inserción ha salido bien, false en caso contrario.
	 **/

	public static boolean insertarRopa(Ropa ropa) {
		try (Statement statement = conexion.createStatement()) {
			if (getRopa(ropa.getId()) != null) {
				consulta = "INSERT INTO Ropa (idProducto, nombre, fechaSubida, etiquetas, precio, idUsuario, enVenta, tallaCalzado)"
					+ "VALUES (" + ropa.getId() + ", '" + ropa.getNombre() + "', '" + ropa.getFechaSubida() + "', '" + ropa.getEtiquetas() + "', " 
					+ ropa.getPrecio() + ", " + ropa.getUsuario().getIdUsuario() + ", '" + ropa.isEnVenta() + "', '" + ropa.getTallaRopa().toString() + "')";

				logger.log(Level.INFO, "Statement: " + consulta);
				int insertados = statement.executeUpdate(consulta);
				if (insertados != 1)
					return false; // Error en inserción
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/*********************************************************************************************************************************************************
	 * 																	TABLA PEDIDO
	 **********************************************************************************************************************************************************/

	/**
	 * Cuando un usuario le de a me gusta a un producto, se creará una tupla en la tabla Favoritos.
	 * @param usuario  		El usuario que le ha dado me gusta a un producto
	 * @param producto 		El producto que le ha gustado al usuario
	 * @param fecha    		Cuándo le ha dado me gusta
	 * @return 				True si la inserción es correcta, false en caso contrario
	 */
	public static boolean insertarPedido(Pedido pedido) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO Pedido (numeroPedido, precioTotal, fechaCompra, fechaEntrega, idUsuario, idProducto)"
					+ "VALUES (" + pedido.getNumeroPedido() + ", " + pedido.getPrecioTotal() + ", '"
					+ pedido.getFechaCompra() + "', '" + pedido.getFechaEntrega() + "', "
					+ pedido.getUsuarioComprador().getIdUsuario() + ", " + pedido.getProductoComprado().getId() + ")";
			logger.log(Level.INFO, "Statement: " + consulta);
			int insertados = statement.executeUpdate(consulta);
			if (insertados != 1)
				return false; // Error en inserción
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Obtener un pedido concreto
	 * @param numeroPedido 			El numero o identificativo del pedido del que se quiere saber la información
	 * @return 						El pedido buscado
	 */

	public static Pedido getPedido(int numeroPedido) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT * FROM Ropa WHERE numeroPedido = " + numeroPedido + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			int numPedido = rs.getInt("numeroPedido");
			double precioTotal = rs.getDouble("precioTotal");
			Date fechaCompra = rs.getDate("fechaCompra");
			Date fechaEntrega = rs.getDate("fechaEntrega");
			int idUsuario = rs.getInt("idUsuario");
			int idProducto = rs.getInt("idProducto");

			Usuario usuario = getUsuario(idUsuario);
			Producto producto = getProducto(idProducto);

			Pedido pedido = new Pedido(precioTotal, fechaCompra, fechaEntrega, numeroPedido, usuario, producto);
			return pedido;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}
	
	/**
	 * Obtener un pedido concreto
	 * @param numeroPedido 			El numero o identificativo del pedido del que se quiere saber la información
	 * @return 						El pedido buscado
	 */

	public static Pedido getPedidoProducto(int idP) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT * FROM Ropa WHERE idProducto = " + idP + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			int numeroPedido = rs.getInt("numeroPedido");
			double precioTotal = rs.getDouble("precioTotal");
			Date fechaCompra = rs.getDate("fechaCompra");
			Date fechaEntrega = rs.getDate("fechaEntrega");
			int idUsuario = rs.getInt("idUsuario");
			int idProducto = rs.getInt("idProducto");

			Usuario usuario = getUsuario(idUsuario);
			Producto producto = getProducto(idProducto);

			Pedido pedido = new Pedido(precioTotal, fechaCompra, fechaEntrega, numeroPedido, usuario, producto);
			return pedido;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/**
	 * Para obtener todos los pedidos hechos por todos los usuarios de DeustoPop
	 * @return 				Arraylist de todos los pedidos de la base de datos
	 */

	public static ArrayList<Pedido> getPedidos() {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT * FROM Pedido;";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
			while (rs.next()) {
				int numPedido = rs.getInt("numeroPedido");
				double precioTotal = rs.getDouble("precioTotal");
				Date fechaCompra = rs.getDate("fechaCompra");
				Date fechaEntrega = rs.getDate("fechaEntrega");
				int idUsuario = rs.getInt("idUsuario");
				int idProducto = rs.getInt("idProducto");

				Usuario usuario = getUsuario(idUsuario);
				Producto producto = getProducto(idProducto);

				Pedido pedido = new Pedido(precioTotal, fechaCompra, fechaEntrega, numPedido, usuario, producto);
				listaPedidos.add(pedido);
			}
			return listaPedidos;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}

	/*********************************************************************************************************************************************************
	 * 																	TABLA FAVORITOS
	 **********************************************************************************************************************************************************/

	/**
	 * Devuelve un ArrayList de todos los Productos favoritos del usuario introducido
	 * @param usuario 		Usuario del que se quiere saber qué productos tiene en Favoritos
	 * @return 				El ArrayList de los productos favoritos del usuario. Lo que para él/ella sería su atributo productosFavoritos.
	 **/

	public static ArrayList<Producto> getFavoritosUsuario(int idUsuario) {
		ArrayList<Producto> productosFavoritos = new ArrayList<>();
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT idProducto FROM Favoritos WHERE idUsuario = " + idUsuario + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) { // Leer el resultset
				int idProducto = rs.getInt("idProducto");
				if (getCalzado(idProducto) == null) {
					Ropa prenda = getRopa(idProducto);
					productosFavoritos.add(prenda);
				} else {
					Calzado calzado = getCalzado(idProducto);
					productosFavoritos.add(calzado);
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
		return productosFavoritos;
	}

	/**
	 * Devuelve un ArrayList de todos los Usuarios a los que les ha gustado un producto
	 * @param producto 		Producto del que se quiere saber a quién les gusta
	 * @return 				El ArrayList de los usuarios
	 **/

	public static ArrayList<Usuario> getFavoritosProducto(Producto producto) {
		ArrayList<Usuario> usuariosConFavorito = new ArrayList<>();
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT idUsuario FROM Favoritos WHERE idProducto = " + producto.getId() + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) { // Leer el resultset
				int idUsuario = rs.getInt("idUsuario");
				Usuario u = getUsuario(idUsuario);
				usuariosConFavorito.add(u);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
		return usuariosConFavorito;
	}

	/**
	 * Cuando un usuario le de a me gusta a un producto, se creará una tupla en la tabla Favoritos.
	 * @param usuario  		El usuario que le ha dado me gusta a un producto
	 * @param producto 		El producto que le ha gustado al usuario
	 * @param fecha    		Cuándo le ha dado me gusta
	 * @return 				True si la inserción es correcta, false en caso contrario
	 */
	public static boolean insertarFavorito(Usuario usuario, Producto producto) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO Favoritos (idUsuario, idProducto, fechaInsertada)" + "VALUES ("
					+ usuario.getIdUsuario() + ", " + producto.getId() + ")";

			logger.log(Level.INFO, "Statement: " + consulta);
			int insertados = statement.executeUpdate(consulta);
			if (insertados != 1)
				return false; // Error en inserción
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Cuando un usuario quiere sacar un producto de su lista de favoritos
	 * @param usuario  		El usuario que quiere borrar el producto de su cuenta
	 * @param producto 		El producto que ya no le gusta
	 * @return				True si se ha eliminado correctamente, false en caso contrario
	 */

	public static boolean eliminarFavoritos(Usuario usuario, Producto producto) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "DELETE FROM Favoritos WHERE idUsuario = " + usuario.getIdUsuario() + " AND idProducto = "
					+ producto.getId() + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/**
	 * Revisa en la base de datos si existe la relación Favorito entre un usuario y un producto
	 * @param usuario  		Usuario del que se quiere saber si hay relación con un producto
	 * @param producto 		Producto del que se quiere saber si hay relacion con un usuario
	 * @return 				True si existe, false si no existe
	 **/

	public static boolean existeFavorito(Usuario usuario, Producto producto) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT * FROM Usuario WHERE idUsuario = " + usuario.getIdUsuario() + "AND idProducto = " + producto.getId() + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);
			if (rs == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

	/*********************************************************************************************************************************************************
	 * 																	TABLA COMENTARIOS
	 **********************************************************************************************************************************************************/

	/**
	 * Devuelve un HashMap de todos los comentarios que ha hecho un usuario concreto
	 * @param idUsuario 		Identificativo del usuario del que se quiere saber qué comentarios ha hecho
	 * @return 					El HashMap de los comentarios hechos
	 **/

	public static HashMap<Producto, String> getComentariosUsuario(int idUsuario) {
		HashMap<Producto, String> comentariosHechos = new HashMap<Producto, String>();
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT idProducto, comentario FROM Comentarios WHERE idUsuario = " + idUsuario + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) { // Leer el resultset
				int idProducto = rs.getInt("idProducto");
				String comentario = rs.getString("comentario");
				if (getCalzado(idProducto) == null) {
					comentariosHechos.put(getRopa(idProducto), comentario);
				} else {
					comentariosHechos.put(getCalzado(idProducto), comentario);
				}
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
		return comentariosHechos;
	}

	/**
	 * Devuelve un HashMap de todos los comentarios que ha tenido un producto concreto
	 * @param idProducto 		Identificativo del producto del que se quiere saber los comentarios que ha tenido
	 * @return 					El HashMap de los comentarios y los usuarios que han comentado
	 **/

	public static HashMap<Usuario, String> getComentariosProducto(int idProducto) {
		HashMap<Usuario, String> comentariosObtenidos = new HashMap<Usuario, String>();
		try (Statement statement = conexion.createStatement()) {
			consulta = "SELECT idUsuario, comentario FROM Comentarios WHERE idProducto = " + idProducto + ";";
			logger.log(Level.INFO, "Statement: " + consulta);
			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) { // Leer el resultset
				int idUsuario = rs.getInt("idUsuario");
				Usuario u = getUsuario(idUsuario);
				String comentario = rs.getString("comentario");
				comentariosObtenidos.put(u, comentario);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
		return comentariosObtenidos;
	}

	/**
	 * Cuando un usuario comenta una opinión sobre un producto, se llamará a esta función
	 * @param usuario    		El usuario que ha comentado
	 * @param producto   		El producto comentado
	 * @param comentario 		Comentario hecho
	 * @param fecha      		Cuándo ha comentado
	 * @return 					True si la inserción es correcta, false en caso contrario
	 */
	
	public static boolean insertarComentario(Usuario usuario, Producto producto, String comentario, Date fecha) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO Favoritos (idUsuario, idProducto, comentario, fechaInsertada)" + "VALUES ("
					+ usuario.getIdUsuario() + ", " + producto.getId() + ", '" + comentario + "', '" + fecha + "')";

			logger.log(Level.INFO, "Statement: " + consulta);
			int insertados = statement.executeUpdate(consulta);
			if (insertados != 1)
				return false; // Error en inserción
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}

}
