package clases;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import BaseDatos.BaseDeDatos;

public class FuncionesGenerales { 
	
	/**
	 * Este método es a lo que se llama cada vez que un cliente de DeustoPop compra un producto.
	 * 
	 * Para empezar, como el producto no seguirá en venta, se pone a False el atributo del producto comprado, para indicarnos que ese producto ya no debe estar accesible al resto de usuarios.
	 * Después, metemos en la variable usuarioVendedor el vendedor de dicho producto, para facilitarnos la función.
	 * Luego recorremos el ArrayList productosEnVenta del usuario para encontrar el producto que se acaba de comprar y asi poder borrarlo de la lista (porque ya no estará más en venta).
	 * Seguido de eso, añadimos el producto al ArrayList de productosVendidos del vendedor, al igual que al ArrayList de productosCompradosdel comprador.
	 * Se llamará a las funciones restarDinero y sumarDinero, para llevar a cabo los trámites de la compra.
	 * Por último, al comprador, le saldrá un mensaje de que ha comprado el producto, si se ha hecho correctamente, o uno de que ha habido un error en caso de haberlo
	 * 
	 * @param productoComprar		el producto que se quiere comprar
	 * @param usuarioComprador		el usuario que quiere comprar el producto
	 * **/
	
	public static void metodoComprarProducto(Producto productoComprar, Usuario usuarioComprador) {
		
		if (new File("deustopop.bd").exists()) {
			// Poner el parámetro a true si se quiere reiniciar la base de datos
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", false );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Abrir base de datos existente
		} else {
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", true );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Crear base de datos con datos iniciales
		}
		
		BaseDeDatos.modificarProductoEnVenta(productoComprar.getId());
		restarDinero(productoComprar, usuarioComprador);
		sumarDinero(productoComprar);
		
		Pedido pedido = new Pedido(usuarioComprador, productoComprar);
		BaseDeDatos.insertarPedido(pedido);
		
		JOptionPane.showMessageDialog(null, "Has comprado " + productoComprar.getNombre() + ". Nos pondremos en contacto con el vendedor", "Enhorabuena", JOptionPane.DEFAULT_OPTION, null);
	}
	
	
	/**
	 * Método al que se llama al comprar un producto. 
	 * Se encarga de restar el precio del producto al saldo o a la tarjeta del comprador.
	 * 
	 * Para ello, revisa si el comprador tiene suficiente dinero en el saldo como para comprar el producto. En caso de ser así, se le resta al saldo del comprador el precio del producto
	 * En caso de no ser así, se harán los trámites necesarios con la tarjeta.
	 * 
	 * @param productoComprar		el producto comprado
	 * @param usuarioComprador		el usuario comprador al que se le restará el dinero por la compra
	 * **/
		
	public static void restarDinero (Producto productoComprar, Usuario usuarioComprador) {
		
		if (new File("deustopop.bd").exists()) {
			// Poner el parámetro a true si se quiere reiniciar la base de datos
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", false );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Abrir base de datos existente
		} else {
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", true );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Crear base de datos con datos iniciales
		}
		
		if (usuarioComprador.getSaldo() - productoComprar.getPrecio() >= 0) {
			usuarioComprador.setSaldo(usuarioComprador.getSaldo() - productoComprar.getPrecio());
			BaseDeDatos.modificarUsuario(usuarioComprador.getIdUsuario(), usuarioComprador.getNombre(), usuarioComprador.getTelefono(), usuarioComprador.getCuentaB().getnTarjeta(), usuarioComprador.getSaldo() - productoComprar.getPrecio(), usuarioComprador.getEmail(), usuarioComprador.getVivienda().getDireccion(), usuarioComprador.getFechaRegistro());
		} else {
			usuarioComprador.getCuentaB().setDineroTotal(usuarioComprador.getCuentaB().getDineroTotal() - productoComprar.getPrecio());
			BaseDeDatos.modificarCuentaBancaria(usuarioComprador.getIdUsuario(), usuarioComprador.getCuentaB().getnTarjeta(), usuarioComprador.getCuentaB().getDineroTotal() - productoComprar.getPrecio());
		}
	}
	
	
	/**
	 * Metodo al que se llama a la hora de comprar un producto.
	 * Se encarga de añadir el precio del producto comprado al saldo del vendedor.
	 * 
	 * Para ello creamos la variable usuarioVendedor, que se define cogiendo el usuario del producto comprado.
	 * Una vez hecho esto, solo habrá que añadirle el precio del producto al saldo del usuario.
	 * 
	 * @param productoComprar 		producto que se ha comprado
	 * **/
	
	public static void sumarDinero (Producto productoComprar) {
		
		if (new File("deustopop.bd").exists()) {
			// Poner el parámetro a true si se quiere reiniciar la base de datos
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", false );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Abrir base de datos existente
		} else {
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", true );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Crear base de datos con datos iniciales
		}
		
		Usuario usuarioVendedor = productoComprar.getUsuario();
		usuarioVendedor.setSaldo(usuarioVendedor.getSaldo() + productoComprar.getPrecio());
		BaseDeDatos.modificarUsuario(usuarioVendedor.getIdUsuario(), usuarioVendedor.getNombre(), usuarioVendedor.getTelefono(), usuarioVendedor.getCuentaB().getnTarjeta(), usuarioVendedor.getSaldo() + productoComprar.getPrecio(), usuarioVendedor.getEmail(), usuarioVendedor.getVivienda().getDireccion(), usuarioVendedor.getFechaRegistro());
	}
	
	
	/*********************************************************************************************************************************************************
	 * 																		FUNCIÓN RECURSIVA
	 * **********************************************************************************************************************************************************/
	/**
	 * Se trata de una función recursiva donde, al pulsar el botón de recomendaciones, 
	 * el programa se encarga de buscar todos los productos en venta de la web que el usuario pueda comprar con el saldo que tiene en DeustoPop.
	 * 
	 * @param u				Usuario para el que buscaremos los productos recomendados basándonos en su saldo
	 * @param n				Será la variable que recorra toda la lista de los productos en venta que hay en DeustoPop. Este irá incrementándose a medida que se llame a la función recursivamente
	 * @param posibles		Será el ArrayList al que se irá añadiendo los productos aptos basándose en el saldo del usuario.
	 * @return				Devuelve el ArrayList de los productos recomendados para el usuario, basado en el saldo del usuario.
	 * **/
	
	
	public static ArrayList<Producto> buscarProductosRecomendadosSaldo (Usuario u, int n, ArrayList<Producto> posibles) {
		
		if (new File("deustopop.bd").exists()) {
			// Poner el parámetro a true si se quiere reiniciar la base de datos
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", false );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Abrir base de datos existente
		} else {
			try {
				BaseDeDatos.abrirConexion( "deustopop.bd", true );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // Crear base de datos con datos iniciales
		}
		
		if (n + 1 <= BaseDeDatos.getProductos().size()) {
			if (BaseDeDatos.getProductosCompleto().get(n).getPrecio() <= u.getSaldo() && BaseDeDatos.getProductosCompleto().get(n).isEnVenta() == true && BaseDeDatos.getProductosCompleto().get(n).getUsuario() != u) {
				posibles.add(BaseDeDatos.getProductosCompleto().get(n));
			}
			buscarProductosRecomendadosSaldo (u, n + 1, posibles);
		}
		return posibles;
	}
	
	
	/**
	 * El método code se encarga de codificar la contraseña que el usuario meta nada más registrarse.
	 * Esto hará que, al meter al usuario a la base de datos, incluyendo la contraseña, nadie pueda acceder a la verdadera contraseña ya que estará codificada.
	 * 
	 * Para ello, recogemos la contraseña metida y vamos cambiando caracter por caracter hasta codificarla.
	 * Cada caracter se incrementará en 3. Esto se basará en la tabla ASCII, ya que cada caracter tiene un número identificativo, y será ESE número el que se incremente.
	 * Por último, solo nos quedará introducir en la posición de este caracter, el nuevo caracter.
	 * 
	 * @param contrasenya		la contraseña que se querrá codificar
	 * @return					la contraseña ya codificada
	 * **/
	
	public static String code (String contrasenya) {
		StringBuilder contra = new StringBuilder(contrasenya);
		for (int i = 0; i < contrasenya.length(); i++) {
			int letra = contrasenya.charAt(i);
			letra = letra + 3;
			char letraC = (char) letra;
			contra.setCharAt(i, letraC);
		}
		return contra.toString();
	}
	
	
	/**
	 * El método decode se encarga de decodificar la contraseña de un usuario de la base de datos.
	 * Este método generalmente se usará a la hora de que un Usuario ya registrado quiera hacer log in. Para ello, habrá que comparar la contraseña que meta con la que hay registrada, y para ello, habrá que decodificarla
	 * Esto hará que, aunque la misteriosa contraseña se pueda decodificar, aunque siga sin guardarse ni registrarse, siga siendo un misterio para el resto.
	 * 
	 * Para ello, recogemos la contraseña registrada para ir decodificándola caracter por caracter.
	 * Cada caracter se decrementará en 3, lo contrario a la función code(), basándose en los índices de cada caracter de la tabla ASCII
	 * Por último, solo nos quedará introducir en la posición de este caracter, el nuevo caracter.
	 * 
	 * @param contrasenya		la contraseña que se querrá decodificar, será la que tendremos en la base de datos.
	 * @return					la contraseña ya decodificada
	 * **/
	
	public static String decode (String contrasenya) {
		StringBuilder contra = new StringBuilder(contrasenya);
		for (int i = 0; i < contrasenya.length(); i++) {
			int letra = contrasenya.charAt(i);
			letra = letra - 3;
			char letraC = (char) letra;
			contra.setCharAt(i, letraC);
		}
		return contra.toString();
	}
	
	

}
