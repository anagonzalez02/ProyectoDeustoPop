package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import BaseDatos.BaseDeDatos;
import clases.Colores;
import clases.CuentaBancaria;
import clases.Estado;
import clases.FuncionesGenerales;
import clases.Lugar;
import clases.Producto;
import clases.Usuario;

/**
 * La VentanaProducto es la JFrame que muestra el producto elegido por el usuario. Este producto deberá estar en venta.
 * La ventana será un poco distintinto si el usuario que ha elegido ver el producto es un comprador o el vendedor del producto mismo.
 * Un comprador podrá comentar el producto, añadirlo a favoritos y, claramente, comprarlo.
 * El vendedor en cambio, no podrá llevar a cabo esas opciones, pero será capaz de borrar el producto completamente de la web:
 * tanto para ser visible para el resto como de la base de datos.
 * **/

public class VentanaProducto extends JFrame {
	
	private Image imagen;
	
	// Necesitaremos unos JLabels para enseñar en la ventana cierta información.
	// En este caso mostraremos: el nombre del producto, las etiquetas que este tenga, el nombre de usuario del vendedor y el precio del producto.
	private JLabel nombreProducto;		
	private JLabel etiquetasProducto;	
	private JLabel nombreVendedor;		
	private JLabel precioProducto;
	
	// Estos serán los JButtons que tendrá el JFrame
	private JButton btnFavorito;
	private JButton btnComprar;			
	private JButton btnVolver;
	private JButton btnEliminar;
	private JButton btnComentar;
	
	Usuario uComprador = new Usuario ("peeepiitaa", 611111111, new CuentaBancaria(8727193, 3), "pepa@email.com", "jeje", new Lugar("Gran Via 54", "Bilbao", "Españita"));
	
	/**
	 * Para inicializar la VentanaProducto se necesitará recibir un producto y un usuarios (el usuario que esté viendo el producto).
	 * El producto se necesitará para coger la información que mostraremos en la ventana (mediante los JLabels).
	 * El usuario se necesitará para saber quién es está viendo el producto.
	 * En caso de ser null el usuario, no pasará nada, excepto si hace click en los botones: btnFavorito, btnComprar y btnComentario.
	 * Se requerirá el usuario ya que en caso de querer meterlo a la favoritos, habrá que saber de quién es la lista; 
	 * en caso de querer comprar, habrá que saber quién es el comprador; y en caso de querer comentar, habrá que saber de quién es el comentario.
	 * El String volver nos indicará a dónde tendrá que volver el usuario (de dónde venía) al salir de la ventana.
	 * **/
	public VentanaProducto (Producto p, Usuario u, String ventanaVolver) {
		
		// Se inicializarán los botones, eliguiendo el texto que se verá por pantalla.
		btnFavorito = new JButton("<3");
		btnComprar = new JButton("Comprar");
		btnVolver = new JButton("Volver");
		btnEliminar = new JButton("Eliminar producto");
		btnComentar = new JButton("Comentar");
		
		
		// Creamos una nueva fuente de letra para el usuario (us).
		// Esta tendrá letra Times New Roman, estará escrito en cursiva y tendrá un tamaño de 15.
		Font us = new Font("Times New Roman", Font.ITALIC, 15);
		
		// Inicializaremos el JLabel nombreVendedor. Lo colocaremos a la derecha, con una letra grisacea y al fuente previamente creada.
		nombreVendedor = new JLabel("@" + p.getUsuario().getNombre(), SwingConstants.RIGHT);
		nombreVendedor.setForeground(Color.GRAY);
		nombreVendedor.setFont(us);
	
		
		
		// PANELES
		
		// Creamos un contenedor llamado "cPanel".
		// Gracias a él, podremos añadir componentes a la ventana.
		Container cPanel = this.getContentPane();
		cPanel.setLayout(new BorderLayout());
		
		// Creamos el panelInf, que será un GridLayout con 1 fila y 2 columnas.
		// Al ser el nombre, y lo más importante junto a la imagen para que el comprador se haga una idea del producto, hacemos una fuente nueva
		// exclusivamente para el producto (prod). Esta estará escrita en Times New Roman, en negrita y en tamaño 20.
		// Inicializamos el JLabel nombreProducto (previamente creado) con el nombre del producto. Y le ponemos la fuente que hemos hecho.
		// Añadimos el JLabel al panelInf.
		JPanel panelInf = new JPanel(new GridLayout(1, 2));
		Font prod = new Font("Times New Roman", Font.BOLD, 20);
		nombreProducto = new JLabel(p.getNombre());
		nombreProducto.setFont(prod);
		panelInf.add(nombreProducto);
		// Hacemos lo mismo con el precio del producto: lo inicializamos y lo añadimos al panelInf.
		// Aunque esta vez no le ponemos ninguna fuente, dejamos la que trae por defecto. Y cuando lo metemos al panel, nos aseguramos de que esté a la derecha.
		precioProducto = new JLabel(p.getPrecio() + "€", SwingConstants.RIGHT);
		panelInf.add(precioProducto);
		
		
		// Crearemos otro panel llamado panelInfGeneral, que tendrá dos filas y una única columna.
		// A este le añadiremos el oanel anterior como primera fila y el JLabel (ya inicializado) de las etiquetas.
		JPanel panelInfGeneral = new JPanel(new GridLayout(2, 1));
		panelInfGeneral.add(panelInf);
		etiquetasProducto = new JLabel("" + p.getEtiquetas());
		panelInfGeneral.add(etiquetasProducto);
		
		
		// Para ver los comentarios (los podrán ver tanto los usuarios compradores como el vendedor), crearemos un DefaultListModel de Strings.
		// Para rellenarlo, cogeremos todos los comentarios que tiene el producto. Obtendremos un HashMap (clave usuario y valor el comentario).
		// Recorreremos el usuario creando nuevos comentarios, indicando el usuario que ha comentado y el comentario.
		// Crearemos un JList de String que tendrá en DefaultListModel que hemos creado.
		// Meteremos el JList a un scrollList
		DefaultListModel<String> datosLista = new DefaultListModel<String>();
		// RELLENAR EL DefaultListModel
		HashMap <Usuario, String> comentarios = p.getComentario();
		for (Usuario usuario : comentarios.keySet()) {
		    String c = comentarios.get(usuario);
		    String nuevoComentario = usuario.getNombre() + ": " + c;
		    datosLista.addElement(nuevoComentario);
		}
		JList<String> listaComentarios = new JList<String>(datosLista);
		JScrollPane scrollLista = new JScrollPane(listaComentarios);
		
		
		// Crearemos un panelInferior que será el que esté debajo de la imagen del objeto. A este le añadiremos el JPanel panelInfGeneral y el scrollList creado
		JPanel panelInferior = new JPanel(new GridLayout(2, 1));
		panelInferior.add(panelInfGeneral);	
		panelInferior.add(scrollLista, BorderLayout.CENTER);
		
		
		
		// Establecemos otro panel, llamado panelPrincipal, que tendrá dos filas.
		// La primera será para la imagen del producto y la segunda será diferente dependiendo del comprador o del vendedor.
		JPanel panelPrincipal = new JPanel (new GridLayout(2, 1));
		// AQUÍ HAY QUE METER LA IMAGEN
		panelPrincipal.add(new JLabel("Imagen"));
		
		if (u == p.getUsuario()) {
			// En caso de que el usuario que está en esta ventana es igual al usuario vendedor del producto,
			// añadimos el panelIngGeneral tal cual al panelPrincipal.
			panelPrincipal.add(panelInferior, BorderLayout.CENTER);
		} else {
			// De no ser así, es decir, que el usuario no es igual al vendedor del producto y, por lo tanto, se trata de un comprador, tendremos otro diseño.
			// Para empezar crearemos un panel llamado panelBotonera, con 1 fila y dos columnas, al que se le añadirán los botones btnComprar y btnFavorito.
			// Además de tener otro panel (panelResto) al que se le añadirá en la primera fila el panelInfGeneral, en la segunda fila un JLabel que hará de espacio,
			// y por último, el panelBotonera anterior.
			// Este panel se le añadirá al panelPrincipal.
			JPanel panelBotonera1 = new JPanel(new GridLayout(1, 2));
			panelBotonera1.add(btnComentar);
			panelBotonera1.add(btnFavorito);
			
			JPanel panelResto = new JPanel (new GridLayout(2, 1));
			panelResto.add(panelInfGeneral);
			panelResto.add(panelBotonera1);
			
			panelPrincipal.add(panelResto);
		}
		
		
		// Crearemos un último panel, para colocarlo en la parte inferior del JFrame. Dispondrá de una sola fila y dos columnas.
		// Si el usuario es el vendedor, en primer lugar irá el botón eliminar. En cambio, si es un comprador, irá el botón comentario.
		// Para ambos casos, el segundo componente será el botón volver.
		JPanel panelBotonera = new JPanel(new GridLayout(1, 2));
		if (p.getUsuario()==u) {
			panelBotonera.add(btnEliminar);
		} else {
			panelBotonera.add(btnComprar);
		}
		panelBotonera.add(btnVolver);
	
		// Añadimos al contenedor del JFrame, el panelPrincipal. Este se situará en medio.
		// En la parte superior, gracias al BorderLayout.NORTH, pondremos en nombre del vendedor. A la derecha, como ya hemos dicho antes.
		// Y por último, en la parte inferior irá este último panel.
		cPanel.add(panelPrincipal);
		cPanel.add(nombreVendedor, BorderLayout.NORTH);
		cPanel.add(panelBotonera, BorderLayout.SOUTH);
		
		
		// BOTONES
		/**
		 * Aunque los botones sean diferentes dependiendo del usuario,
		 * a todos les añadiremos un ActionListener para asegurarnos de que cumplen con su deber.
		 * **/
		
		// El botón btnVolver, aunque es el único igual para todos, actuará de forma distinta.
		// En caso de ser el vendedor, volverá a la ventana anterior, la VentanaUsuario.
		// En caso de ser un comprador, volverá a la ventana anterior, pero eso solo lo sabremos con uno de los tres parámetros introducidos a VentanaProducto.
		// Pueden ser: VentanaPrincipal, VentanaFavorito o VentanaRecomendado.
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (p.getUsuario() == u) {
					VentanaUsuario ventana = new VentanaUsuario(u);
					ventana.setVisible(true);
					dispose();
				} else {
					if (ventanaVolver == "VentanaPrincipal") {
						VentanaPrincipal.main(null);
						dispose();
					} else if (ventanaVolver == "VentanaFavoritos") {
						VentanaFavoritos.main(null);
						dispose();
					} else if (ventanaVolver == "VentanaRecomendados") {
						VentanaRecomendadosSaldo.main(null);
						dispose();
					}
				}
			}
		});
		
		// El botón comprar, solo apto para los compradores, hace el proceso de comprar el producto.
		// Para ello, necesitará tener un usuario comprador, por en caso de que el usuario introducido es igual a null, el botón se encargará de llevar
		// al usuario a la VentanaLogin para que inicie sesión o, en caso de no tener cuenta, se registre. Una vez hecho eso, podrá volver y comprar el producto.
		// Para comprar el producto, saldrá un mensaje de confirmación, que, si el usuario le da click a sí, se llevará a cabo la compra.
		// Para ello, llamará al método metodoComprarProducto de FuncionesGenerales, indicando que el producto es el producto actual y el usuario el usuario que lo quiere comprar.
		// Una vez hecho esto, redigirá al usuario a la ventana de la que ha venido (teniendo en cuenta el parametro ventanaVolver).
		btnComprar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (u == null) {
					VentanaLogIn ventana = new VentanaLogIn("VentanaProducto", p);
			        ventana.setVisible(true);
					dispose();	
				} else {
					int atencionPanel = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quieres comprar este producto?", "Alerta!", JOptionPane.YES_NO_OPTION);
					if (atencionPanel == JOptionPane.YES_OPTION) {
						FuncionesGenerales.metodoComprarProducto(p, uComprador);
						if (ventanaVolver == "VentanaPrincipal") {
							VentanaPrincipal.main(null);
							dispose();
						} else if (ventanaVolver == "VentanaFavoritos") {
							VentanaFavoritos.main(null);
							dispose();
						} else if (ventanaVolver == "VentanaRecomendados") {
							VentanaRecomendadosSaldo.main(null);
							dispose();
						}
					}
				}
			}
		});
		
		// Cada usuario tiene una serie de productos destacados. Este botón es el encargado de destacarlo o quietarlo de destacados. 
		// El botón será visible para todo aquel que no sea el dueño del producto, es decir, para los compradores.
		// En caso de querer destacarlo, al pinchar en el botón, se añadirá a la lista de productosFavoritos del comprador y saldrá un mensaje afirmando que se ha añadido.
		// En caso de querer quitarlo de destacados, pasará lo contrario.
		// Para ver estos productos, habrá que ir al perfil (VentanaUsuario) y hacer click en el botón "Favoritos" (btnFavoritos).
		btnFavorito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uComprador.getProductosFavoritos().contains(p)) {
					uComprador.getProductosFavoritos().remove(p);
					BaseDeDatos.eliminarFavoritos(u, p);
					JOptionPane.showMessageDialog(null, p.getNombre() + "eliminado de favoritos");
				} else {
					uComprador.getProductosFavoritos().add(p);
					BaseDeDatos.insertarFavorito(u, p);
					JOptionPane.showMessageDialog(null, p.getNombre() + "añadido a favoritos");
				}
			}
		});
		
		// Un usuario comprador (cualquiera que no sea el vendedor) podrá acceder al botón comentar
		// Una vez pinchado, al usuario le saldrá un showInputDialog donde deberá introducir su opinión del producto
		// La opinion se guardará como un string, que se añadirá a la base de datos introduciendo también el producto que se ha comentado, el usuario comprador y la fecha actual
		btnComentar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String comentario = JOptionPane.showInputDialog("Escribe tu opinion");
				BaseDeDatos.insertarComentario(u, p, comentario, new Date (Calendar.getInstance().getTimeInMillis()));
				
			}
		});
		

		// El JButton eliminar es únicamente visible para el vendedor.
		// Como el propio nombre indica, este botón será el encargado de borrar definitivamente el producto de la plataforma. No quedará registro alguno de él.
		// Por ello, sabiendo lo que esto implica, al hacer click en el botón, saldrá un mensaje de confirmación para borrarlo. Si se pincha en no, no pasará nada,
		// pero si se pincha en sí, el programa llamará a la base de datos para eliminar el producto.
		// Si se eliminar correctamente, aparecrerá por pantalla un mensaje. Si no, aparecerá otro mensaje explicando que ha habido un error.
		
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int cuidadoPanel = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quieres eliminar este producto?", "Alerta!", JOptionPane.YES_NO_OPTION);
				if (cuidadoPanel == JOptionPane.YES_OPTION) {
					boolean correcto = BaseDeDatos.eliminarProducto(p); //no existe eliminarProducto, habria que crearlo en la clase "BaseDeDatos"
					if (correcto) {
						JOptionPane.showMessageDialog(null, p.getNombre() + "ha sido eliminado.");
					} else {
						JOptionPane.showMessageDialog(null, "¡Oh! Ha habido un error. Vuelve a intentarlo más tarde.");
					}
				}
			}
		});
		
		
		// Solo nos queda dar unos últimos retoques a la ventana.
		 
		 this.setTitle(p.getNombre());                   	 	// Ponemos de título el nombre del producto
	     this.setSize(500, 700);                                // Le asignamos un tamaño de 500 de ancho y 700 de alto
	     this.setLocationRelativeTo(null);                      // Centramos la ventana en la pantalla
	     this.setResizable(false);                              // La ventana no se podrá agrandar
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Cuando se cierre la ventana, terminá todo proceso
		
		
	}
	

	
	public static void main(String[] args) {
		final Image Image = null;
		Usuario uVendedor = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		Producto producto = new Producto ("Zapatilla guay", "Cool", 10.65, Image, Estado.MALO, Colores.Azul, uVendedor);
    	
		VentanaProducto C = new VentanaProducto(producto, uVendedor, null);      // creamos una ventana, de momento con producto nulo
        C.setVisible(true);             // hacemos visible la ventana creada
    }
	
	
	
}
