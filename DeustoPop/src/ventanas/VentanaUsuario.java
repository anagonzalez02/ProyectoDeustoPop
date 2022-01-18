package ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import clases.CuentaBancaria;
import clases.Lugar;
import clases.Producto;
import clases.Usuario;

/**
 * La VentanaUsuario es un JFrame que muestra al usuario su perfil.
 * Muestra la información relevante/datos personales del usuario y da la opción de cambiarlos o actualizarlos.
 * La ventana serádistinta para cada usuario ya que variarán los datos. Cada usuario podrá ver únicamente su propio perfil.
 * **/

public class VentanaUsuario extends JFrame {
	
	// Creamos los JLabels que indicarán qué campo se está viendo y la información del campo como tal.
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
	
	// Creamos los botones que se podrán clickar en la ventana.
	private JButton btnVolver;
	private JButton btnEditar;
	private JButton btnFavoritos;
	private JButton btnVerProducto;
	private JButton btnRecomendadosSaldo;
	
	/**
	 * Para abrir el JFrame VentanaUsuario habrá que indicarle la cuenta iniciada, es decir, el usuario que quiere acceder a su perfil.
	 * **/
	public VentanaUsuario (Usuario u) {
		
		if (u == null) {
			// Antes de enseñar el JFrame VentanaUsuario, habrá que saber qué usuario quiere acceder a su perfil. Para ello habrá que iniciar la ventana con un usuario.
			// En caso de que dicho usuario sea null (un usuario puede usar DeustoPop sin haber iniciado sesión, pero necesitará iniciar sesión para algunas funciones, como esta: ver el perfil)
			// Es decir, que el cliente todavía no introducido su cuenta, no podrá ver sus datos hasta hacerlo. Por reso, se le redigirá a VentanaLogin, indicando que luego deberá volver a esta ventana.
			
			VentanaLogIn ventana = new VentanaLogIn("VentanaUsuario", null);
	        ventana.setVisible(true);
			dispose();
			
		} else {
			// En caso de que el usuario no sea null, significará que ya habrá introducido su cuenta DeustoPop anteriormente, por lo que podrá entrar a su perfil.
		
			// Definimos los botones con sus respectivos textos
			btnVolver = new JButton("Volver");
			btnEditar = new JButton("Cambiar datos");
			btnVerProducto = new JButton("Ver producto");
			btnFavoritos = new JButton("Favoritos");
			btnRecomendadosSaldo = new JButton("Productos recomendados por saldo");
			
			// Añadiremos a los JLabels de tipo ****Usuario la información del usuario (la información que puede cambiarse y sea visible)
			nombreUsuario = new JLabel("" + u.getNombre());
			emailUsuario = new JLabel("" + u.getEmail());
			telefonoUsuario = new JLabel("" + u.getTelefono());
			saldoUsuario = new JLabel("" + u.getSaldo());
			tarjetaUsuario = new JLabel("" + u.getCuentaB().getnTarjeta());
			direccionUsuario = new JLabel("" + u.getVivienda().getDireccion() + ", " + u.getVivienda().getNomCiud() + ", " + u.getVivienda().getNomPais());
			
			// Definimos una nueva fuente que usaremos para las etiquetas de los campos de la ventana
			Font letra = new Font("Times New Roman", Font.BOLD, 16);
			
			// Definiremos los JLabels de tipo ****Etiqueta con el nombre del campo que representa. Y le pondremos la fuente creada.
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
			
			// Creamos un Container que contendrá todos los componentes de la ventana.
			Container cPanel = this.getContentPane();
			cPanel.setLayout(new BorderLayout());
			
			// Creamos un JPanel que usaremos para enseñar los datos del usuario, para ello, haremos del JPanel un GridLayout de 7 filas y 2 columnas.
			// Nos aseguraremos de meter los componentes en un orden de modo que las etiquetas vayan con sus respectivos datos.
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
			
			// Por último, dejaremos al usuario que pueda editar los datos. Para ello, meteremos un botón al JPanel, que clickándolo, lo llevará a otra ventana.
			panelGeneral.add(btnEditar);
			
			// Creamos otro JPanel que será el panel de productos
			JPanel panelProductos = new JPanel();
			// Creamos un DefaultListModel de tipo Producto
			// Para completarlo, cogeremos todos los productos que tiene en venta el usuario y los meteremos en una lista.
			// Habrá que recorrerla e insertar el elemento al DefaultListModel.
			DefaultListModel<Producto> datosLista = new DefaultListModel<Producto>();
			ArrayList <Producto> productos = u.getProductosEnVenta();
			for (Producto p : productos) {
				datosLista.addElement(p);
			}
			// Creamos un JList de tipo Productos del DefaultListModel anteriormente creado y hacemos un JScrollPane con este.
			// Habrá que darle unas dimensiones y añadirlo al panel, junto al JButton btnVerProducto.
			JList<Producto> listaProductos = new JList<Producto>(datosLista);
			JScrollPane scrollLista = new JScrollPane(listaProductos);
			scrollLista.setPreferredSize(new Dimension(450, 260));
			panelProductos.add(scrollLista, BorderLayout.CENTER);
			panelProductos.add(btnVerProducto);
			
			// Creamos un JPanel de dos filas y una columna que contendrá el panel general y el panel de productos.
			// Lo añadiremos al Container de la ventana.
			JPanel panelGrande = new JPanel(new GridLayout(2, 1));
			panelGrande.add(panelGeneral);
			panelGrande.add(panelProductos);
			cPanel.add(panelGrande);
			
			// Crearemos un panel de dos filas y una columna que añadiremos al sur del Contenedor, es decir, a la parte inferior.
			// En la primera fila meteremos un JPanel de dos columnas que contendrá los JButtons btnFavoritos y btnRecomendadosSaldo
			// En la segunda fila pondremos unicamente el JButton btnVolver
			JPanel panelInferior = new JPanel(new GridLayout(2, 1));
			JPanel panel1 = new JPanel(new GridLayout(1, 2));
			panel1.add(btnFavoritos);
			panel1.add(btnRecomendadosSaldo);
			panelInferior.add(panel1);
			panelInferior.add(btnVolver);
			cPanel.add(panelInferior, BorderLayout.SOUTH);
			
			
			// BOTONES
			
			// El JButton volver se encarga de abrir el JFrame VentanaPrincipal y cerrar la ventana actual.
			btnVolver.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaPrincipal.main(null);
					dispose();
				}
			});
			
			// El JButton volver se encarga de abrir el JFrame VentanaEditar y cerrar la ventana actual.
			btnEditar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaEditar ventanaE = new VentanaEditar(u);
			        ventanaE.setVisible(true);  
			        dispose();
				}
			});
			
			// El JButton volver se encarga de abrir el JFrame VentanaProducto y cerrar la ventana actual.
			// Para ello tendremos que definir el producto seleccionado en listaProductos.
			// Si no se ha seleccionado ninguno, saldrá un aviso.
			btnVerProducto.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Producto p = (Producto) listaProductos.getSelectedValue();
					if (p == null) {
						JOptionPane.showMessageDialog(null, "No has seleccionado ningún producto");
					} else {
						VentanaProducto ventana = new VentanaProducto(p, u, null);
						ventana.setVisible(true);
						dispose();
					}
				}
			});
			
			// El JButton volver se encarga de abrir el JFrame VentanaFavoritos y cerrar la ventana actual.
			btnFavoritos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaFavoritos.main(null);
					dispose();
				}
			});
			
			// El JButton volver se encarga de abrir el JFrame VentanaRecomendadosSaldo y cerrar la ventana actual.
			btnRecomendadosSaldo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaRecomendadosSaldo.main(null);
					dispose();
				}
			});
			
			
			// Solo nos queda dar unos últimos retoques a la ventana.
			
			this.setTitle("@" + u.getNombre());                   			// Ponemos como titulo de la ventana el nombre de usuario
		    this.setSize(550, 700);                                 		// Le asignamos un tamaño de 500 de ancho y 700 de alto
		    this.setLocationRelativeTo(null);                       		// Centramos la ventana en la pantalla
		    this.setResizable(false);                               		// Hacemos que la ventana no sea redimiensionable
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    		// Cuando se cierre la ventana, terminá todo proceso
		
		}
	}
	
	public static void main(String[] args) {
		Usuario usuario = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		VentanaUsuario C = new VentanaUsuario(usuario);      // creamos una ventana, de momento con producto nulo
        C.setVisible(true);             // hacemos visible la ventana creada
    }
	
	
}
