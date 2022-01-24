
package BaseDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.Calzado;
import clases.CuentaBancaria;
import clases.Lugar;
import clases.Pedido;
import clases.Producto;
import clases.Ropa;
import clases.Usuario;

/**
 * Este JFrame muestra, mediante JTables, la información que hay guardada en la base de datos.
 * Al tener distintas tablas, podremos cambiar de unas a otras para verlas mediante los botones que tendremos en la parte inferior de la ventana.
 * **/

public class Main extends JFrame {
	
	private static Main ventana;
	public static void main(String[] args) {
		ventana = new Main();
		ventana.setVisible( true );
	}
	
	private JLabel lInfo;
	private DefaultTableModel mDatos;
	private JTable tDatos;
	private ArrayList<Usuario> listaUsuarios;
	private JPanel pNorte;
	private JPanel panelBotonera;
	
	private JButton btnUsuarios;
	private JButton btnCuentaBancaria;
	private JButton btnLugar;
	private JButton btnProducto;
	private JButton btnCalzado;
	private JButton btnRopa;
	private JButton btnFavorito;
	private JButton btnComentario;
	private JButton btnPedido;
	
	private JButton btnEnVenta;
	private JButton btnCuentaPorCliente;
	private JButton btnLugarPorCliente;
	private JButton btnProductosPorCliente;
	private JButton btnFavoritosPorCliente;
	private JButton btnPedidosPorCliente;
	
	
	public Main () {
		
		/**
		 * La tabla se llamará tDatos.
		 * Será la misma para todos, solo que irá cambiando a medida que queramos ver otra.
		 * **/
		tDatos = new JTable();
		
		addWindowListener( new WindowAdapter() {
			/**
			 * Nada más abrir la ventana, se iniciará la conexión con la base de datos.
			 * Si el archivo deustopop.bd existe, la conexión se abrirá pero no tendrá que reiniciarse toda la base de datos.
			 * En cambio si no existe, tendrá que reiniciarse.
			 * **/
			@Override
			public void windowOpened(WindowEvent e) {
				if (new File("deustopop.bd").exists()) {
					try {
						BaseDeDatos.abrirConexion( "deustopop.bd", false );
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						BaseDeDatos.abrirConexion( "deustopop.bd", true );
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				verUsuarios();									// Nada más abrir la ventana, se verá la tabla de Usuarios
			}
			
			/**
			 * Al cerrarse la JFrame, se cerrará la conexión con la base de datos.
			 * **/
			@Override
			public void windowClosed(WindowEvent e) {
				BaseDeDatos.cerrarConexion();
			}
		});
		
		// El JButton btnUsuario muestra la lista de usuarios que hay registrados en la base de datos
		btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Usuarios");
				verUsuarios();
			}
		});

		// El JButton btnUsuario muestra la lista de cuentas banncarias que hay registradas en la base de datos
		btnCuentaBancaria = new JButton("Cuentas bancarias");
		btnCuentaBancaria.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Cuentas bancarias");
				verCuentasBancarias();
			}
		});
		
		// El JButton btnUsuario muestra la lista de lugares que hay registrados en la base de datos
		btnLugar = new JButton("Lugares");
		btnLugar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Lugares");
				verLugar();
			}
		});
		
		// El JButton btnUsuario muestra la lista de productos que hay registrados en la base de datos
		btnProducto = new JButton("Productos");
		btnProducto.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Productos");
				verProducto();
			}
		});
		
		// El JButton btnUsuario muestra la lista de calzados que hay registrados en la base de datos
		btnCalzado = new JButton("Calzados");
		btnCalzado.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Calzados");
				verCalzado();
			}
		});
		
		// El JButton btnUsuario muestra la lista de ropa que hay registrada en la base de datos
		btnRopa = new JButton("Ropa");
		btnRopa.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Ropa");
				verRopa();
			}
		});
		
		// El JButton btnUsuario muestra la lista de favoritos que hay registrados en la base de datos
		btnFavorito = new JButton("Favoritos");
		btnFavorito.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Favoritos");
				verFavorito();
			}
		});
		
		// El JButton btnUsuario muestra la lista de comentarios que hay registrados en la base de datos
		btnComentario = new JButton("Comentarios");
		btnComentario.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Comentarios");
				verComentario();
			}
		});
		
		// El JButton btnUsuario muestra la lista de pedidos que hay registrados en la base de datos
		btnPedido = new JButton("Pedidos");
		btnPedido.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Pedidos");
				verPedido();
			}
		});
		
		// Boton que pondrá en verde las filas de productos que están en venta
		btnEnVenta = new JButton("Productos en venta");
		btnEnVenta.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Pedidos");
				verProducto();
				tDatos.setDefaultRenderer( Object.class, new DefaultTableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
							int row, int column) {
						Component elementoActual = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
						if(table.getValueAt(row, 9).toString().equals("true")){
							elementoActual.setBackground( Color.green );
						} 
						return elementoActual;
					}
				});
			}
		});
		
		// El JButton btnCuentaPorCliente muestra la cuenta bancaria del cliente seleccionado.
		btnCuentaPorCliente = new JButton("Cuenta por cliente");
		btnCuentaPorCliente.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Cuenta bancaria");
				verCuentaPorCliente();
			}
		});
		
		// El JButton btnCuentaPorCliente muestra la vivienda del cliente seleccionado.
		btnLugarPorCliente = new JButton("Lugar por cliente");
		btnLugarPorCliente.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Cuenta bancaria");
				verLugarPorCliente();
			}
		});
		
		// El JButton btnProductosPorCliente muestra los productos del cliente seleccionado.
		btnProductosPorCliente = new JButton("Productos por cliente");
		btnProductosPorCliente.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Productos");
				verProductosPorCliente();
			}
		});
		
		// El JButton btnFavoritosPorCliente muestra los productos que le han gustado al cliente seleccionado.
		btnFavoritosPorCliente = new JButton("Favoritos por cliente");
		btnFavoritosPorCliente.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Productos");
				verFavoritosPorCliente();
			}
		});
		
		// El JButton btnPedidosPorCliente muestra los pedidos hechos por el cliente seleccionado.
		btnPedidosPorCliente = new JButton("Pedidos por cliente");
		btnPedidosPorCliente.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Pedidos");
				verPedidosPorCliente();
			}
	});
		
		
		// Creamos un JPanel que servirá como botonera. Se situará en la parte inferior de la ventana y tendrá dos filas.
		panelBotonera = new JPanel(new GridLayout(2, 1));
		
		JPanel panel1 = new JPanel();
		panelBotonera.add(panel1);
		JPanel panel2 = new JPanel();
		panelBotonera.add(panel2);
		
		// Añadimos todos los paneles que hemos creado para completar el panelBotonera.
		panel1.add(btnUsuarios);
		panel1.add(btnCuentaBancaria);
		panel1.add(btnLugar);
		panel1.add(btnProducto);
		panel1.add(btnCalzado);
		panel1.add(btnRopa);
		panel1.add(btnPedido);
		panel1.add(btnFavorito);
		panel1.add(btnComentario);
		
		panel2.add(btnEnVenta);
		panel2.add(btnCuentaPorCliente);
		panel2.add(btnLugarPorCliente);
		panel2.add(btnProductosPorCliente);
		panel2.add(btnFavoritosPorCliente);
		panel2.add(btnPedidosPorCliente);
		
		
		// Iicializamos el JLabel lInfo como "Usuarios", para que, al abrir la ventana y enseñar la tabla Usuarios, tenga su título bien puesto.
		// Lo añadimos al panel superior de la ventana.
		lInfo = new JLabel("Usuarios");
		pNorte = new JPanel();
		pNorte.add(lInfo);
		
		// Creamos el Container de la ventana.
		Container cPanel = this.getContentPane();
		cPanel.setLayout(new BorderLayout());
		
		// Añadimos sus componentes en el sitio correcto.
		cPanel.add(pNorte, BorderLayout.NORTH);
		cPanel.add(new JScrollPane(tDatos), BorderLayout.CENTER);
		cPanel.add(panelBotonera, BorderLayout.SOUTH);
		
		
		
		
		this.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		this.setSize (1500, 800);
		this.setTitle ("Visualización de la base de datos de DeustoPop");
		
	}
	
	
	/**
	 * Cambia la cabecera de la lista por la de usuario y muestra toda la lista de usuarios que tiene la plataforma.
	 * **/
	private void verUsuarios() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("idUsuario", "nombre", "telefono", "nTarjeta", "saldo", "email", "contrasenia", "direccion", "fechaRegistro"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		listaUsuarios = BaseDeDatos.getUsuarios();

		for (Usuario u : listaUsuarios) {
			mDatos.addRow (new Object[] {u.getIdUsuario(), u.getNombre(), u.getTelefono(), u.getCuentaB().getnTarjeta(), u.getSaldo(), u.getEmail(), u.getContrasenia(), u.getVivienda().getDireccion(), u.getFechaRegistro()});
		}
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de cuenta bancaria y muestra toda la lista de cuentas bancarias que tiene la plataforma.
	 * **/
	private void verCuentasBancarias() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("nTarjeta", "dineroTotal"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <CuentaBancaria> listaCuentas = BaseDeDatos.getCuentaBancaria();

		for (CuentaBancaria c : listaCuentas) {
			mDatos.addRow (new Object[] {c.getnTarjeta(), c.getDineroTotal()});
		}
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de lugar y muestra toda la lista de lugares que tiene la plataforma.
	 * **/
	private void verLugar() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("direccion", "nomCiud", "nomPais"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Lugar> listaLugares = BaseDeDatos.getLugar();

		for (Lugar l : listaLugares) {
			mDatos.addRow (new Object[] {l.getDireccion(), l.getNomCiud(), l.getNomPais()});
		}
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de producto y muestra toda la lista de productos que tiene la plataforma.
	 * **/
	private void verProducto() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("idProducto", "nombre", "fechaSubida", "etiquetas", "precio", "imagen", "estado", "color", "idUsuario", "enVenta", "tipoProucto"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Producto> listaProductos = BaseDeDatos.getProductos();

		for (Producto p : listaProductos) {
			String tipo = null;
			String venta = null;
			if (p.getClass().toString() == "Calzado") {tipo = "Calzado";}
			else {tipo = "Ropa";}
			if (p.isEnVenta() == true) {venta = "true";}
			else {venta = "false";}
			mDatos.addRow (new Object[] {p.getId(), p.getNombre(), p.getFechaSubida(), p.getEtiquetas(), p.getPrecio(), p.getImagen(), p.getEstado().toString(), p.getColor().toString(), p.getUsuario().getIdUsuario(), venta, tipo});
		}
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de calzado y muestra toda la lista de calzados que tiene la plataforma.
	 * **/
	private void verCalzado() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("idProducto", "tallaCalzado"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Calzado> listaCalzados = BaseDeDatos.getTodosCalzados();

		for (Calzado c : listaCalzados) {
			String venta = null;
			if (c.isEnVenta() == true) {venta = "true";}
			else {venta = "false";}
			mDatos.addRow (new Object[] {c.getId(), c.getTallaCalzado()});
		
		}
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de ropa y muestra toda la lista de prendas de ropa que tiene la plataforma.
	 * **/
	private void verRopa() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("idProducto", "tallaRopa"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Ropa> listaRopas = BaseDeDatos.getTodaRopa();

		for (Ropa r : listaRopas) {
			String venta = null;
			if (r.isEnVenta() == true) {venta = "true";}
			else {venta = "false";}
			mDatos.addRow (new Object[] {r.getId(), r.getTallaRopa().toString()});
		
		}
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de favorito y muestra toda la lista de favoritos que tiene la plataforma.
	 * **/
	private void verFavorito() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("idProducto", "idUsuario"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Usuario> listaUsuarios = BaseDeDatos.getUsuarios();

		for (Usuario u : listaUsuarios) {
			ArrayList<Producto> listaProductos = BaseDeDatos.getFavoritosUsuario(u.getIdUsuario());
			if (listaProductos.size() != 0) {
				for (Producto p : listaProductos) {
					mDatos.addRow (new Object[] {u.getIdUsuario(), p.getId()});
				}
			}
		
		}
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de comentario y muestra toda la lista de comentarios que tiene la plataforma.
	 * **/
	private void verComentario() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("idProducto", "idUsuario", "comentario"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Producto> listaProductos = BaseDeDatos.getProductos();

		for (Producto p : listaProductos) {
			HashMap <Usuario, String> comentarios = BaseDeDatos.getComentariosProducto(p.getId());
			if (comentarios.size() != 0) {
				for (Usuario u : comentarios.keySet()) {
				    String c = comentarios.get(u);
				    mDatos.addRow (new Object[] {p.getId(), u.getIdUsuario(), c});
				}
			}
		}
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de pedido y muestra toda la lista de pedidos que tiene la plataforma.
	 * **/
	private void verPedido() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("numeroPedido", "precioTotal", "fechaCompra", "fechaEntrega", "usuarioComprador", "productoComprado"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Pedido> listaPedidos = BaseDeDatos.getPedidos();

		for (Pedido p : listaPedidos) {
			mDatos.addRow (new Object[] {p.getNumeroPedido(), p.getPrecioTotal(), p.getFechaCompra(), p.getFechaEntrega(), p.getUsuarioComprador().getIdUsuario(), p.getProductoComprado().getId()});
		}
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de cuenta bancaria y muestra la cuenta bancaria que tiene registrada un usuario de la plataforma.
	 * **/
	private void verCuentaPorCliente() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("nTarjeta", "dineroTotal"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		
		String id = tDatos.getModel().getValueAt(tDatos.getSelectedRow(), 0).toString();
		CuentaBancaria c = BaseDeDatos.getCuentaBancaria(Integer.parseInt(id));

		mDatos.addRow (new Object[] {c.getnTarjeta(), c.getDineroTotal()});
		
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de lugar y muestra la vivienda que tiene registrada un usuario de la plataforma.
	 * **/
	private void verLugarPorCliente() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("direccion", "nomCiud", "nomPais"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		
		String direccion = tDatos.getModel().getValueAt(tDatos.getSelectedRow(), 7).toString();
		Lugar l = BaseDeDatos.getLugar(direccion);

		mDatos.addRow (new Object[] {l.getDireccion(), l.getNomCiud(), l.getNomPais()});
		
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de productos y muestra los productos (en venta y/o vendidos) que tiene registrada un usuario de la plataforma.
	 * **/
	private void verProductosPorCliente() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("idProducto", "nombre", "fechaSubida", "etiquetas", "precio", "imagen", "estado", "color", "idUsuario", "enVenta", "tipoProucto"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		
		String id = tDatos.getModel().getValueAt(tDatos.getSelectedRow(), 0).toString();
		ArrayList <Producto> listaProductos = BaseDeDatos.getProductosCompleto(Integer.parseInt(id));

		for (Producto p : listaProductos) {
			String tipo = null;
			String venta = null;
			if (p.getClass().toString() == "Calzado") {tipo = "Calzado";}
			else {tipo = "Ropa";}
			if (p.isEnVenta() == true) {venta = "true";}
			else {venta = "false";}
			mDatos.addRow (new Object[] {p.getId(), p.getNombre(), p.getFechaSubida(), p.getEtiquetas(), p.getPrecio(), p.getImagen(), p.getEstado().toString(), p.getColor().toString(), p.getUsuario().getIdUsuario(), venta, tipo});
		}
		
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de productos y muestra los productos que tiene en favoritos un usuario de la plataforma.
	 * **/
	private void verFavoritosPorCliente() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("idProducto", "nombre", "fechaSubida", "etiquetas", "precio", "imagen", "estado", "color", "idUsuario", "enVenta", "tipoProucto"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		
		String id = tDatos.getModel().getValueAt(tDatos.getSelectedRow(), 0).toString();
		ArrayList <Producto> listaProductos = BaseDeDatos.getFavoritosUsuario(Integer.parseInt(id));

		for (Producto p : listaProductos) {
			String tipo = null;
			String venta = null;
			if (p.getClass().toString() == "Calzado") {tipo = "Calzado";}
			else {tipo = "Ropa";}
			if (p.isEnVenta() == true) {venta = "true";}
			else {venta = "false";}
			mDatos.addRow (new Object[] {p.getId(), p.getNombre(), p.getFechaSubida(), p.getEtiquetas(), p.getPrecio(), p.getImagen(), p.getEstado().toString(), p.getColor().toString(), p.getUsuario().getIdUsuario(), venta, tipo});
		}
		
		tDatos.setModel(mDatos);
	}
	
	/**
	 * Cambia la cabecera de la lista por la de pedidos y muestra los pedidos hechos un usuario de la plataforma.
	 * **/
	private void verPedidosPorCliente() {
		Vector<String> cabeceras = new Vector<String>(Arrays.asList("numeroPedido", "precioTotal", "fechaCompra", "fechaEntrega", "usuarioComprador", "productoComprado"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		
		String id = tDatos.getModel().getValueAt(tDatos.getSelectedRow(), 0).toString();
		ArrayList <Pedido> listaPedidos = BaseDeDatos.getPedidoUsuario(Integer.parseInt(id));

		for (Pedido p : listaPedidos) {
			mDatos.addRow (new Object[] {p.getNumeroPedido(), p.getPrecioTotal(), p.getFechaCompra(), p.getFechaEntrega(), p.getUsuarioComprador().getIdUsuario(), p.getProductoComprado().getId()});
		}
		
		tDatos.setModel(mDatos);
	}
	
	

}
