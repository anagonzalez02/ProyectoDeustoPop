package Clases;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FuncionesGenerales {
	
	public static ArrayList<Usuario> listaUsuarios = new ArrayList();
	
	public static ArrayList<Producto> listaProductos = new ArrayList();
	
	public static void metodoComprarProducto (Producto productoComprar, Usuario usuarioComprador) {
		productoComprar.setEnVenta(false);
		Usuario usuarioVendedor = productoComprar.getUsuario();
		int num = 0;
		for (Producto prod : usuarioVendedor.productosEnVenta) {
			if (prod == productoComprar) {
				usuarioVendedor.productosEnVenta.remove(num);
			}
			num++;
		}
		usuarioVendedor.productosVendidos.add(productoComprar);
		usuarioComprador.productosComprados.add(productoComprar);
		restarDinero (productoComprar, usuarioComprador);
		sumarDinero (productoComprar, usuarioVendedor);
		JOptionPane.showMessageDialog(null, "Has comprado " + productoComprar.getNombre() + ". Nos pondremos en contacto con el vendedor", "Enhorabuena", JOptionPane.DEFAULT_OPTION, null);
	}
	
	public static void restarDinero (Producto productoComprar, Usuario usuarioComprador) {
		if (usuarioComprador.getSaldo() - productoComprar.getPrecio() >= 0) {
			usuarioComprador.setSaldo(usuarioComprador.getSaldo() - productoComprar.getPrecio());
		} else {
			// CON LA TARJETA
		}
	}
	
	public static void sumarDinero (Producto productoComprar, Usuario usuarioVendedor) {
		usuarioVendedor.setSaldo(usuarioVendedor.getSaldo() + productoComprar.getPrecio());
	}
	
	
	public static String code (String contraseña) {
		StringBuilder contra = new StringBuilder(contraseña);
		for (int i = 0; i < contraseña.length(); i++) {
			int letra = contraseña.charAt(i);
			letra = letra + 3;
			char letraC = (char) letra;
			contra.setCharAt(i, letraC);
		}
		return contra.toString();
	}
	
	public static String decode (String contraseña) {
		StringBuilder contra = new StringBuilder(contraseña);
		for (int i = 0; i < contraseña.length(); i++) {
			int letra = contraseña.charAt(i);
			letra = letra - 3;
			char letraC = (char) letra;
			contra.setCharAt(i, letraC);
		}
		return contra.toString();
	}
	
	
	public static void main (String arg[]) {
		System.out.println(code("A7 Gb"));
		//System.out.println(decode("Hey 73n"));
	}

}
