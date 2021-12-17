package ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import clases.CuentaBancaria;
import clases.Lugar;
import clases.Producto;
import clases.Usuario;

public class VentanaUsuario extends JFrame {
	
	private JLabel nombreUsuario;
	private JLabel emailUsuario;
	private JLabel telefonoUsuario;
	private JLabel saldoUsuario;
	private JLabel tarjetaUsuario;
	private JLabel direccionUsuario;
	private JLabel nombreEtiqueta;
	private JLabel emailEtiqueta;
	private JLabel telefonoEtiqueta;
	private JLabel saldoEtiqueta;
	private JLabel tarjetaEtiqueta;
	private JLabel direccionEtiqueta;
	
	private JButton btnVolver;
	private JButton btnEditar;
	private JButton btnFavoritos;
	private JButton btnVerProducto;
	
	
	public VentanaUsuario (Usuario u) {
		
		btnVolver = new JButton("Volver");
		btnEditar = new JButton("Cambiar datos");
		btnVerProducto = new JButton("Ver producto");
		btnFavoritos = new JButton("Favoritos");
		
		nombreUsuario = new JLabel("" + u.getNombre());
		emailUsuario = new JLabel("" + u.getEmail());
		telefonoUsuario = new JLabel("" + u.getTelefono());
		saldoUsuario = new JLabel("" + u.getSaldo());
		tarjetaUsuario = new JLabel("" + u.getCuentaB().getnTarjeta());
		direccionUsuario = new JLabel("" + u.getVivienda().getDireccion() + ", " + u.getVivienda().getNomCiud() + ", " + u.getVivienda().getNomPais());
		
		Font letra = new Font("Times New Roman", Font.BOLD, 16);
		
		nombreEtiqueta = new JLabel("Nombre: ");
		nombreEtiqueta.setFont(letra);
		emailEtiqueta = new JLabel("Email: ");
		emailEtiqueta.setFont(letra);
		telefonoEtiqueta = new JLabel("Telefono: ");
		telefonoEtiqueta.setFont(letra);
		saldoEtiqueta = new JLabel("Saldo: ");
		saldoEtiqueta.setFont(letra);
		tarjetaEtiqueta = new JLabel("nº tarjeta: ");
		tarjetaEtiqueta.setFont(letra);
		direccionEtiqueta = new JLabel("Direccion: ");
		direccionEtiqueta.setFont(letra);
		
		
		
		
		
		// PANELES
		
		Container cPanel = this.getContentPane();
		cPanel.setLayout(new BorderLayout());
		
		
		JPanel panelGeneral = new JPanel(new GridLayout(7, 2));
		panelGeneral.add(nombreEtiqueta);
		panelGeneral.add(nombreUsuario);
		panelGeneral.add(emailEtiqueta);
		panelGeneral.add(emailUsuario);
		panelGeneral.add(telefonoEtiqueta);
		panelGeneral.add(telefonoUsuario);
		panelGeneral.add(saldoEtiqueta);
		panelGeneral.add(saldoUsuario);
		panelGeneral.add(tarjetaEtiqueta);
		panelGeneral.add(tarjetaUsuario);
		panelGeneral.add(direccionEtiqueta);
		panelGeneral.add(direccionUsuario);
		
		// HACER QUE EL BOTÓN SE PONGA EN MEDIO
		panelGeneral.add(btnEditar);
		
		cPanel.add(panelGeneral, BorderLayout.SOUTH);
		
		
		JPanel panelProductos = new JPanel();
		DefaultListModel datosLista = new DefaultListModel();
		JList listaProductos = new JList(datosLista);
		JScrollPane scrollLista = new JScrollPane(listaProductos);
		scrollLista.setPreferredSize(new Dimension(450, 260));
		panelProductos.add(scrollLista, BorderLayout.CENTER);
		panelProductos.add(btnVerProducto);
		
		
		JPanel panelGrande = new JPanel(new GridLayout(2, 1));
		panelGrande.add(panelGeneral);
		panelGrande.add(panelProductos);
		cPanel.add(panelGrande);
		
		JPanel panelInferior = new JPanel(new GridLayout(1, 2));
		panelInferior.add(btnFavoritos);
		panelInferior.add(btnVolver);
		cPanel.add(panelInferior, BorderLayout.SOUTH);
		
		
		// BOTONES
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal.main(null);
				dispose();
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaEditar ventanaE = new VentanaEditar(u);      // creamos una ventana, de momento con producto nulo
		        ventanaE.setVisible(true);  
		        dispose();
			}
		});
		
		btnVerProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Producto p = (Producto) listaProductos.getSelectedValue();
				VentanaProducto ventana = new VentanaProducto(p, u);
				ventana.setVisible(true);
				dispose();
			}
		});
		
		btnFavoritos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// OTRA VENTANA
			}
		});
		
		
		this.setTitle("@" + u.getNombre());                   			 // colocamos titulo a la ventana
	    this.setSize(500, 700);                                 // colocamos tamanio a la ventana (ancho, alto)
	    this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
	    this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
		
	}
	
	public static void main(String[] args) {
		Usuario usuario = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		VentanaUsuario C = new VentanaUsuario(usuario);      // creamos una ventana, de momento con producto nulo
        C.setVisible(true);             // hacemos visible la ventana creada
    }
	
	
}
