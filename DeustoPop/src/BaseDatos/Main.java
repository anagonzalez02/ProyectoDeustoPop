package BaseDatos;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
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
import javax.swing.table.DefaultTableModel;

import clases.Calzado;
import clases.CuentaBancaria;
import clases.Lugar;
import clases.Pedido;
import clases.Producto;
import clases.Ropa;
import clases.Usuario;


public class Main extends JFrame {
	
	private static Main ventana;  // Ventana única principal
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
	
	
	public Main () {
		
		tDatos = new JTable();
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
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
				verUsuarios();  // Según se inicia la ventana se visualizan los clientes registrados
			}
			@Override
			public void windowClosed(WindowEvent e) {
				BaseDeDatos.cerrarConexion();
			}
		});
		
		btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Usuarios");
				verUsuarios();
			}
		});

		btnCuentaBancaria = new JButton("Cuentas bancarias");
		btnCuentaBancaria.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Cuentas bancarias");
				verCuentasBancarias();
			}
		});
		
		btnLugar = new JButton("Lugares");
		btnLugar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Lugares");
				verLugar();
			}
		});
		
		btnProducto = new JButton("Productos");
		btnProducto.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Productos");
				verProducto();
			}
		});
		
		btnCalzado = new JButton("Calzados");
		btnCalzado.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Calzados");
				verCalzado();
			}
		});
		
		btnRopa = new JButton("Ropa");
		btnRopa.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Ropa");
				verRopa();
			}
		});
		
		btnFavorito = new JButton("Favoritos");
		btnFavorito.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Favoritos");
				verFavorito();
			}
		});
		
		btnComentario = new JButton("Comentarios");
		btnComentario.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Comentarios");
				verComentario();
			}
		});
		
		btnPedido = new JButton("Pedidos");
		btnPedido.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Pedidos");
				verPedido();
			}
		});
		
		
		panelBotonera = new JPanel();
		
		panelBotonera.add(btnUsuarios);
		panelBotonera.add(btnCuentaBancaria);
		panelBotonera.add(btnLugar);
		panelBotonera.add(btnProducto);
		panelBotonera.add(btnCalzado);
		panelBotonera.add(btnRopa);
		panelBotonera.add(btnFavorito);
		panelBotonera.add(btnComentario);
		panelBotonera.add(btnPedido);
		
		lInfo = new JLabel("Usuarios");
		pNorte = new JPanel();
		pNorte.add(lInfo);
		
		Container cPanel = this.getContentPane();
		cPanel.setLayout(new BorderLayout());
		
		cPanel.add(pNorte, BorderLayout.NORTH);
		cPanel.add(new JScrollPane(tDatos), BorderLayout.CENTER);
		cPanel.add(panelBotonera, BorderLayout.SOUTH);
		
		
		
		
		
		this.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		this.setSize (1500, 800);
		this.setTitle ("Visualización de la base de datos de DeustoPop");
		
		
	}
	
	
	private void verUsuarios() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("idUsuario", "nombre", "telefono", "nTarjeta", "saldo", "email", "contrasenia", "direccion"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		listaUsuarios = BaseDeDatos.getUsuarios();

		for (Usuario u : listaUsuarios) {
			mDatos.addRow (new Object[] {u.getIdUsuario(), u.getNombre(), u.getTelefono(), u.getCuentaB().getnTarjeta(), u.getSaldo(), u.getEmail(), u.getContrasenia(), u.getVivienda().getDireccion()});
		}
		tDatos.setModel(mDatos);
	}
	
	private void verCuentasBancarias() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("idUsuario", "nTarjeta", "dineroTotal"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <CuentaBancaria> listaCuentas = BaseDeDatos.getCuentaBancaria();

		for (CuentaBancaria c : listaCuentas) {
			mDatos.addRow (new Object[] {c.getnTarjeta(), c.getDineroTotal()});
		}
		tDatos.setModel(mDatos);
	}
	
	private void verLugar() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("direccion", "nomCiud", "nomPais"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Lugar> listaLugares = BaseDeDatos.getLugar();

		for (Lugar l : listaLugares) {
			mDatos.addRow (new Object[] {l.getDireccion(), l.getNomCiud(), l.getNomPais()});
		}
		tDatos.setModel(mDatos);
	}
	
	private void verProducto() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("idProducto", "nombre", "fechaSubida", "etiquetas", "precio", "imagen", "estado", "color", "idUsuario", "enVenta", "tipoProucto"));
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
	
	private void verCalzado() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("idProducto", "nombre", "fechaSubida", "etiquetas", "precio", "imagen", "estado", "color", "idUsuario", "enVenta", "tallaCalzado"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Calzado> listaCalzados = BaseDeDatos.getTodosCalzados();

		for (Calzado c : listaCalzados) {
			String venta = null;
			if (c.isEnVenta() == true) {venta = "true";}
			else {venta = "false";}
			mDatos.addRow (new Object[] {c.getId(), c.getNombre(), c.getFechaSubida(), c.getEtiquetas(), c.getPrecio(), c.getImagen(), c.getEstado().toString(), c.getColor().toString(), c.getUsuario().getIdUsuario(), venta, c.getTallaCalzado()});
		
		}
		tDatos.setModel(mDatos);
	}
	
	private void verRopa() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("idProducto", "nombre", "fechaSubida", "etiquetas", "precio", "imagen", "estado", "color", "idUsuario", "enVenta", "tallaRopa"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Ropa> listaRopas = BaseDeDatos.getTodaRopa();

		for (Ropa r : listaRopas) {
			String venta = null;
			if (r.isEnVenta() == true) {venta = "true";}
			else {venta = "false";}
			mDatos.addRow (new Object[] {r.getId(), r.getNombre(), r.getFechaSubida(), r.getEtiquetas(), r.getPrecio(), r.getImagen(), r.getEstado().toString(), r.getColor().toString(), r.getUsuario().getIdUsuario(), venta, r.getTallaRopa().toString()});
		
		}
		tDatos.setModel(mDatos);
	}
	
	private void verFavorito() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("idProducto", "idUsuario"));
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
	
	private void verComentario() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("idProducto", "idUsuario", "comentario"));
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
	
	private void verPedido() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("precioTotal", "fechaCompra", "fechaEntrega", "numeroPedido", "usuarioComprador", "productoComprado"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		ArrayList <Pedido> listaPedidos = BaseDeDatos.getPedidos();

		for (Pedido p : listaPedidos) {
			mDatos.addRow (new Object[] {p.getPrecioTotal(), p.getFechaCompra(), p.getFechaEntrega(), p.getNumeroPedido(), p.getUsuarioComprador().getIdUsuario(), p.getProductoComprado().getId()});
		}
		tDatos.setModel(mDatos);
	}
	

}
