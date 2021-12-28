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
				crearTablaBDCuentaBancaria();
				crearTablaBDLugar();
				crearTablaBDCalzado();
				crearTablaBDRopa();
				
				try {
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Usuario.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Usuario (idUsuario, nombre, telefono, tarjeta, saldo, email, contrasenia, vivienda, productosEnVenta, productosVendidos, productosComprados, productosFavoritos)"
								+ "VALUES (" + datos[0] + ", '" + datos[1] + "', " + datos[2] + ", " + datos[3] + ", " + datos[4] + ", '" + datos[5] + "', '" + datos[6]
								+ "', (SELECT * FROM Lugar WHERE L.idUsuario = '" + datos[7] + "');";
						
						// QUEDA LA LISTA FAVORITOS Y LA LISTA COMPRADOS
						
						logger.log( Level.INFO, "Statement: " + consulta );
						statement.executeUpdate( consulta );
					}
					scanner.close();
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("CuentaBancaria.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO CuentaBancaria (idUsuario, nTarjeta, dineroTotal)"
								+ "VALUES (" + datos[0] + ", " + datos[1] + ", " + datos[2] + ");";
						logger.log( Level.INFO, "Statement: " + consulta );
						statement.executeUpdate( consulta );
					}
					scanner.close();
					
					scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("Lugar.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						consulta = "INSERT INTO Lugar (direccion, nomCiud, nomPais)"
								+ "VALUES ('" + datos[0] + "', " + datos[1] + ", '" + datos[2] + "');";
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
				"(INT[6] idUsuario AUTO_INCREMENT NOT NULL, VARCHAR[20] nombre NOT NULL, INT[12] telefono, INT[18] nTarjeta, DOUBLE[6,2] saldo DEFAULT 0, VARCHAR[70] email, "
				+ " VARCHAR[20] contrasenia NOT NULL, VARCHAR[100] direccion, "
				+ "PRIMARY KEY (idUsuario), UNIQUE KEY (nombre), FOREIGN KEY (nTarjeta) REFERENCES CuentaBancaria (nTarjeta), FOREIGN KEY (direccion) REFERENCES Lugar (direccion));";
		
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
	 * Crea la tabla CuentaBancaria
	 * **/
	
	public static void crearTablaBDCuentaBancaria() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS CuentaBancaria";
		logger.log( Level.INFO, "Statement: " + consulta );
		statement.executeUpdate( consulta );
		
		consulta = "CREATE TABLE CuentaBancaria " +
				"(INT[6] idUsuario NOT NULL, INT[18] nTarjeta NOT NULL, DOUBLE[8.2] dineroTotal, "
				+ "PRIMARY KEY (nTarjeta), FOREIGN KEY (idUsuario) REFERENCES CuentaBancaria (idUsuario));";
		
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
				"(VARCHAR[100] direccion NOT NULL, VARCHAR[35] nomCiud, VARCHAR[25] nomPais, PRIMARY KEY (direccion));";
		
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
	
	
	public static void crearTablaBDProducto() throws SQLException {
		Statement statement = conexion.createStatement();
		consulta = "DROP TABLE IF EXISTS Producto";
		logger.log( Level.INFO, "Statement: " + consulta );
		statement.executeUpdate( consulta );
		
		consulta = "CREATE TABLE Producto " +
				"(INT[6] id AUTO_INCREMENT NOT NULL, VARCHAR[35] nombre, DATE fechaSubida, VARCHAR[60] etiquetas,  \"\n"
				+ "	+ \"DOUBLE[4, 2] precio, INT[6] idUsuario NOT NULL, BIT enVenta, PRIMARY KEY(id));";
		
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
	 * Lista los usuarios de la base de datos
	 * @return	Lista completa de los Usuarios de nuestra plataforma, null si hay algún error
	 */
	
	public static Usuario getUsuarios(String nombre, String contra) {
		try (Statement statement = conexion.createStatement()) {
			String contraseña = FuncionesGenerales.code(contra);
			consulta = "SELECT * FROM Usuario WHERE nombre = '" + nombre + "' AND contrasenia = '" + contraseña + "';";
			logger.log( Level.INFO, "Statement: " + consulta );
			ResultSet rs = statement.executeQuery( consulta );
			
			if (rs != null) {
				
				int idUsuario = rs.getInt("idUsuario");
				String nom = rs.getString("nombre");
				int telefono = rs.getInt("telefono");
				int tarjeta = rs.getInt("tarjeta");
				double saldo = rs.getDouble("saldo");
				String email = rs.getString("email");
				String contrasenia = rs.getString("contrasenia");
				String direccion = rs.getString("direccion");	
				
				
				String consultaCuenta = "SELECT * FROM CuentaBancaria WHERE idUsuario = " + idUsuario + ";";
				logger.log( Level.INFO, "Statement: " + consultaCuenta );
				ResultSet rsCuenta = statement.executeQuery( consultaCuenta );
				int idUsuarioCuenta = rsCuenta.getInt("idUsuario");
				int nTarjeta = rsCuenta.getInt("nTarjeta");
				double dineroTotal = rsCuenta.getDouble("dineroTotal");
				CuentaBancaria cuenta = new CuentaBancaria(nTarjeta, dineroTotal);
				
				
				String consultaLugar = "SELECT * FROM Lugar WHERE direccion = '" + direccion + "';";
				logger.log( Level.INFO, "Statement: " + consultaLugar );
				ResultSet rsLugar = statement.executeQuery( consultaLugar );
				String direc = rsLugar.getString("direccion");
				String nomCiud = rsLugar.getString("nomCiud");
				String nomPais = rsLugar.getString("nomPais");
				Lugar vivienda = new Lugar(direc, nomCiud, nomPais);
				
				
				
				
				Usuario usuario = new Usuario();
				
				return usuario;
			
			} else {
				return null;
			}
			
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
					+ "VALUES (" + usuario.getIdUsuario() + ", '" + usuario.getNombre() + "', " + usuario.getTelefono() + ", " + usuario.getCuentaB().getnTarjeta() + ", " + usuario.getSaldo() + ", '" + usuario.getEmail() + "', '" + usuario.getContrasenia() + "', " + usuario.getVivienda() + ", '" + usuario.getProductosEnVenta() + "', '" + usuario.getProductosVendidos() + "', '" + usuario.getProductosComprados() + "', '" + usuario.getProductosFavoritos() + "')";

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
	
	public static boolean modificarUsuario(int id, String nombre, int telefono, int tarjeta, double saldo, String email, Lugar vivienda, ArrayList<Producto> productosEnVenta, ArrayList<Producto> productosVendidos, ArrayList<Producto> productosComprados, ArrayList<Producto> productosFavoritos) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "UPDATE Usuario SET nombre = '" + nombre + "', telefono = " + telefono + ", tarjeta = " + tarjeta + ", saldo = " + saldo + ", email = '" + email + "', direccion = '" + vivienda.getDireccion()
			+ "', productosEnVenta = '" + productosEnVenta + "', productosVendidos = '" + productosVendidos + "', productosComprados = '" + productosComprados + "', productosFavoritos = '" + productosFavoritos 
			+ "' WHERE idUsuario = " + id + ";";
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	} 
	
	/**
	 * Revisa en la base de datos si hay algún usuario en DeustoPop con los parametros introducidos
	 * @param nombre	Revisa si existe algún nombre de usuario como el introducido
	 * @return			True si existe, false si no existe
	 * **/
	
	public static boolean existeNombreUsuario(String nombre) {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Usuario> listaUsuarios = new ArrayList<>();
			consulta = "SELECT * FROM Usuario WHERE nombre = '" + nombre + "';";
			logger.log( Level.INFO, "Statement: " + consulta );
			ResultSet rs = statement.executeQuery( consulta );
			if (rs == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/**
	 * Revisa en la base de datos si hay algún usuario en DeustoPop con los parametros introducidos
	 * @param email		Revisa si existe algún email de usuario como el introducido
	 * @return			True si existe, false si no existe
	 * **/
	
	public static boolean existeEmailUsuario(String email) {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Usuario> listaUsuarios = new ArrayList<>();
			consulta = "SELECT * FROM Usuario WHERE email = '" + email + "';";
			logger.log( Level.INFO, "Statement: " + consulta );
			ResultSet rs = statement.executeQuery( consulta );
			if (rs == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	
	/** 
	 * Para obtener un listado de todas las cuentas bancarias de la base de datos
	 * @return	Lista completa de las cuentas bancarias de nuestra plataforma, null si hay algún error
	 */
	
	public static ArrayList<CuentaBancaria> getCuentaBancaria() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<CuentaBancaria> listaCuentasB = new ArrayList<>();
			consulta = "SELECT * FROM CuentaBancaria;";
			logger.log( Level.INFO, "Statement: " + consulta );
			ResultSet rs = statement.executeQuery( consulta );
			while( rs.next() ) { // Leer el resultset
				int idUsuario = rs.getInt("idUsuario");
				int nTarjeta = rs.getInt("nTarjeta");
				double dineroTotal = rs.getDouble("dineroTotal");
				listaCuentasB.add(new CuentaBancaria (nTarjeta, dineroTotal) );
			}
			return listaCuentasB;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	/** 
	 * Para obtener una cuenta bancaria concreta de uno de los usuarios de la base de datos
	 * @return	Cuenta bancaria de una persona registrada en nuestra plataforma, null si hay algún error
	 */
	
	public static CuentaBancaria getCuentaBancaria(Usuario usuario) {
		try (Statement statement = conexion.createStatement()) {
			int nTarjeta = usuario.getCuentaB().getnTarjeta();
			consulta = "SELECT * FROM CuentaBancaria WHERE nTarjeta = " + nTarjeta + ";";
			logger.log( Level.INFO, "Statement: " + consulta );
			CuentaBancaria cuenta = (CuentaBancaria) statement.executeQuery( consulta );
			return cuenta;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	/**
	 * Inserta un lugar/vivienda en la base de datos abierta 
	 * @param lugar			un nuevo luegar recién registrado mediante un nuevo usuario que se introducirá en la base de Datos
	 * @return				true si la inserción es correcta, false en caso contrario
	 */
	
	public static boolean insertarCuentaBancaria( CuentaBancaria cuenta, Usuario usuario ) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO CuentaBancaria (idUsuario, nTarjeta, dineroTotal)"
					+ "VALUES (" + usuario.getIdUsuario() + ", " + cuenta.getnTarjeta() + "', '" + cuenta.getDineroTotal() + "')";
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
	 * @param vivienda		el lugar que se desea eliminar
	 * @return				true si se ha eliminado correctamente, false en caso contrario
	 */
	
	public static boolean eliminarCuentaBancaria(CuentaBancaria cuenta) {
		int nTarjeta = cuenta.getnTarjeta();
		try {
			Statement statement = conexion.createStatement();
			consulta = "DELETE FROM CuentaBancaria WHERE nTarjeta = '" + nTarjeta + "';";
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
	 * @param direccionV	Direccion Vieja, será el identificativo para saber qué Lugar modificar
	 * @param direccion		Direccion que se vaya a cambiar en la base de datos
	 * @para, nomCiud		Nombre de la ciudad que se vaya a cambiar en la base de datos
	 * @param nomPais 		Nombre del país que se vaya a cambiar en la base de datos
	 * @return				true si se ha modificado correctamente, false en caso contrario
	 */
	
	public static boolean modificarCuentaBancaria(int nTarjetaV, int idUsuario, int nTarjeta, double dineroTotal) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "UPDATE CuentaBancaria SET idUsuario = " + idUsuario + ", nTarjeta = " + nTarjeta + ", dineroTotal = " + dineroTotal + "  WHERE nTarjeta = " + nTarjetaV + ";";
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
				String nomCiud = rs.getString("nomCiud");
				String nomPais = rs.getString("nomPais");
				listaLugares.add(new Lugar (direccion, nomCiud, nomPais) );
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
	
	/**
	 * Inserta un lugar/vivienda en la base de datos abierta 
	 * @param lugar			un nuevo luegar recién registrado mediante un nuevo usuario que se introducirá en la base de Datos
	 * @return				true si la inserción es correcta, false en caso contrario
	 */
	
	public static boolean insertarLugar( Lugar vivienda ) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO Lugar (direccion, nomCiud, nomPais)"
					+ "VALUES ('" + vivienda.getDireccion() + "', '" + vivienda.getNomCiud() + "', '" + vivienda.getNomPais() + "')";

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
	 * @param vivienda		el lugar que se desea eliminar
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
	 * @param direccionV	Direccion Vieja, será el identificativo para saber qué Lugar modificar
	 * @param direccion		Direccion que se vaya a cambiar en la base de datos
	 * @para, nomCiud		Nombre de la ciudad que se vaya a cambiar en la base de datos
	 * @param nomPais 		Nombre del país que se vaya a cambiar en la base de datos
	 * @return				true si se ha modificado correctamente, false en caso contrario
	 */
	
	public static boolean modificarLugar(String direccionV, String direccion, String nomCiud, String nomPais) {
		try {
			Statement statement = conexion.createStatement();
			consulta = "UPDATE Lugar SET direccion = '" + direccion + "', nomCiud = '" + nomCiud + "', nomPais = " + nomPais + "'  WHERE direccion = '" + direccionV + "';";
			logger.log( Level.INFO, "Statement: " + consulta );
			statement.executeUpdate(consulta);
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	

	
	public static ArrayList<Producto> getProducto() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Producto> listaProductos = new ArrayList<>();
			consulta = "SELECT * FROM Producto;";
			logger.log( Level.INFO, "Statement: " + consulta );
			ResultSet rs = statement.executeQuery( consulta );
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				Date fechaSubida = rs.getDate("fechaSubida");
				String etiquetas = rs.getString("etiquetas");
				Double precio = rs.getDouble("precio");
				int idUsuario = rs.getInt("idUsuario");
				Boolean enVenta = rs.getBoolean("enVenta");
				
//				listaProductos.add(new Producto(id, nombre, fechaSubida, etiquetas, precio, idUsuario, enVenta) );
			}
			return listaProductos;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	public static boolean insertarProducto( Producto producto) {
		try (Statement statement = conexion.createStatement()) {
			consulta = "INSERT INTO Producto (id, nombre, fechaSubida, etiquetas, precio, idUsuario, enVenta)"
					+ "VALUES ('" + producto.getId() + "', '" + producto.getNombre() + "', '" + producto.getFechaSubida() + 
					"', '" + producto.getEtiquetas() + "', '" + producto.getPrecio() + "', '" + producto.getUsuario() + "', '" + producto.enVenta + "')";

			logger.log( Level.INFO, "Statement: " + consulta );
			int insertados = statement.executeUpdate( consulta );
			if (insertados!=1) return false;  // Error en inserción
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	

}
