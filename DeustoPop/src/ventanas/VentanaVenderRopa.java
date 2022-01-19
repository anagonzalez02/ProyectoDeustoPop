package ventanas;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import BaseDatos.BaseDeDatos;
import clases.Colores;
import clases.Estado;
import clases.FuncionesGenerales;
import clases.Producto;
import clases.Ropa;
import clases.TallasRopa;
import clases.Usuario;

/**
 * La VentanaVenderRopa se encarga de recopilar información sobre el producto, en este caso una prenda de ropa, que se quiere vender en DeustoPop.
 * Para llegar aquí, el usuario vendedor deberá haber clickado en vender de la ventana principal y en la VentanaVender, elegir la opción de vender ropa.
 * Si un usuario quiere vender una prenda, deberá completar toda la información pedida de este JFrame. 
 * Una vez hecho esto, la prenda será parte de la base de datos de la plataforma y se añadirá al ArrayList de ProductosEnVenta del usuario.
 * Seguirá visible para todos los usuarios hasta que un comprador realice un pedido con el producto.
 * **/

public class VentanaVenderRopa extends JFrame implements ActionListener {
	
	/**
	 * Los componentes que usaremos son los siguientes:
	 * Para empezar tendremos los JLabels que se encargarán de señalizar de qué se trata el campo que hay al lado para completar.
	 * Esos campos serán distintos dependiendo de cada uno.
	 * El nombre y las etiquetas serán JTextField para que el usuario introduzca lo que desee con total libertad.
	 * Luego tenemos también un spinner que usaremos uno para el precio (irá cada 0,05€) aunque también se podrá escribir directamente en él.
	 * Tenemos también tres JComboBox que serán para elegir entre una serie de opciones sobre el estado del producto, el color y la talla.
	 * Estas opciones serán opciones de las enumeraciones de dichos campos.
	 * Y por último tendremos el JButton para subir la imagen. Y otros dos para publicar el calzado y para volver a la página anterior.
	 * **/
	private JLabel textoNombre;			// Texto no editable indicando el espacio para el nombre
    private JLabel textoFt;				// Texto no editable indicando dónde subir la foto
    private JLabel textoEstado;			// Texto no editable indicando las opciones de los distintos estados
    private JLabel textoEtiqueta;		// Texto no editable indicando el espacio para las etiquetas
    private JLabel textoPrecio;			// Texto no editable indicando el dónde poner el precio
    private JLabel textoColores;		// Texto no editable indicando el espacio para el nombre
    private JLabel textoTalla;			// Texto no editable indicando las opciones de talla
    private JTextField cajaNombre;		// Caja de texto, para insertar el nombre de la prenda
    private JTextField cajaEtiqueta;	// Caja de texto, para insertar el nombre del calzado
    private JSpinner spinnerPrecio;		// Spinner, para insertar el precio
    private JButton botonFt;			// Botón hecho para subir la imagen del producto
    private JButton botonPublicar;		// Botón para publicar la prenda de ropa en la plataforma
    private JButton botonVolver;		// Botón que servirá para volver a la ventana anterior
    private JComboBox comboEstado;		// Para elegir entre las opciones de la enumeración de Estado
    private JComboBox comboColor;		// Para elegir entre las opciones de la enumeración de Colores
    private JComboBox comboTalla;		// Para elegir entre las opciones de la enumeración de TallasRopa
    
    
    // No sé qué es esto, así que el que lo esté haciendo que lo comente
    /*
    //INTENTO LOGGER PRENDA VENDIDA
    
    static Logger log;
    try {
    	log = Logger.getLogger( "RopaVendida" );
    	log.addHandler( new FileHandler( "ropaVendida.xml") );
    } catch (Exception e) {
    }
    log.log( Level.INFO,  "Ropa vendida" + (textoNombre) + (textoPrecio));
    
    */
    
    
    /**
     * El constructor de la ventana se completará llamando a dos funciones que rellenen la ventana con los componentes y la configuren.
     * **/
    public VentanaVenderRopa() {
        super();                    	// Usamos el contructor de la clase padre JFrame
        configurarVentana();        	// Configuramos la ventana
        inicializarComponentes();   	// Inicializamos los atributos o componentes
    }
    
    /**
     * El método configurarVentana() configurará los detalles no tan visibles de la ventana.
     * Le aportará título, tamaño, localización...
     * **/
    private void configurarVentana() {
        this.setTitle("DeustoPop");                   			// colocamos titulo a la ventana
        this.setSize(500, 700);                                	// colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
    }
    
    /**
     * El método inicializarComponentes() como el propio nombre indica inicializa los componentes del JFrame.
     * Además de colocar cada componente en su lugar correspondiente.
     * **/
	private void inicializarComponentes() {
		/**
		 * Inicializamos los JLabels. JTextFields, JComboBox y JButtons
		 * **/
		textoNombre = new JLabel();
		textoFt = new JLabel();
		textoEstado = new JLabel();
		textoEtiqueta = new JLabel();
		textoColores = new JLabel();
		textoTalla = new JLabel();
		textoPrecio = new JLabel();
		
		cajaNombre = new JTextField();
		cajaEtiqueta = new JTextField();
		
		botonFt = new JButton();
		botonPublicar = new JButton();
		botonVolver = new JButton();
		
		comboEstado = new JComboBox();
		comboColor = new JComboBox();
		comboTalla = new JComboBox();

		/**
	     * Para inicializar el JSpinner del precio habrá que indicar su máximo, su mínimo y cada cuánto sube y baja.
	     * Le pondremos un mínimo de 0.00€ y un máximo de 10000.00€. 
	     * Así evitaremos que haya precios negativos y que tenga 2 decimales (para los céntimos), cumpliendo nuestro objetivo para el precio.
	     * Podrá subir y bajar cada 0.05€, pero también podrá escribir el precio directamente.
	     * **/
		SpinnerNumberModel model = new SpinnerNumberModel(0.00, 0.00, 10000.00, 0.05);
		spinnerPrecio = new JSpinner(model);

		
		 /**
	     * Configuraremos los componentes de la ventana.
	     * Para ello, asignaremos a cada JLabel el texto que debe mostrar. Y les otorgaremos su posición en la ventana.
	     * Esta posición se hará mediante un .setBounds() que definirá (x, y, ancho, alto).
	     * Es decir, definirá la posición (x, y) y el tamaño (ancho, alto).
	     * **/
		textoNombre.setText("Nombre del producto:");
		textoNombre.setBounds(25, 30, 300, 20);
		textoFt.setText("Anyade aqui la foto:");
		textoFt.setBounds(25, 100, 300, 20);
		textoEstado.setText("Seleccione el estado de su producto:");
		textoEstado.setBounds(25, 240, 300, 20);
		textoEtiqueta.setText("Etiqueta tu producto:");
		textoEtiqueta.setBounds(25, 310, 300, 20);
		textoPrecio.setText("Seleccione el precio de su producto:");
		textoPrecio.setBounds(25, 380, 300, 20);
		textoColores.setText("Color del producto:");
		textoColores.setBounds(25, 450, 300, 20);
		textoTalla.setText("Talla del producto:");
		textoTalla.setBounds(25, 170, 300, 20);

		 /**
	     * Haremos lo mismo con los JTextFieldy el JSpinner a la hora de colocarlos en la ventana.
	     * **/
		cajaNombre.setBounds(25, 50, 425, 30);
		cajaEtiqueta.setBounds(25, 330, 425, 30);
		spinnerPrecio.setBounds(25, 400, 425, 30);
		 /**
	     * Les daremos el texto que deben mostrar a los botones y los pondremos en sus lugares del JFrame.
	     * **/
		botonFt.setText("Inserte una foto");
		botonFt.setBounds(25, 120, 425, 30);
		botonPublicar.setText("Publicar");
		botonPublicar.setBounds(250, 550, 200, 70);
		botonVolver.setText("Volver");
		botonVolver.setBounds(25, 550, 200, 70);
		 /**
	     * Colocamos los JComboBox con la misma forma que el resto de componentes.
	     * **/
		comboEstado.setBounds(25, 260, 425, 30);
		comboColor.setBounds(25, 470, 425, 30);
		comboTalla.setBounds(25, 190, 425, 30);

		/**
	     * Aunque todo esté inicializado, con su propio texto y sus modelos, y su lugar y tamaño en la ventana, todavía no están completamente dentro.
	     * Por eso, deberemos añadir TODOS los componentes a la ventana mediante un this.add().
	     * **/
		this.add(textoNombre);
		this.add(textoFt);
		this.add(textoEstado);
		this.add(textoEtiqueta);
		this.add(textoPrecio);
		this.add(textoColores);
		this.add(textoTalla);
		this.add(cajaNombre);
		this.add(cajaEtiqueta);
		this.add(spinnerPrecio);
		this.add(botonFt);
		this.add(botonPublicar);
		this.add(botonVolver);
		this.add(comboTalla);

		this.add(comboEstado);
		comboEstado.setModel(new DefaultComboBoxModel(Estado.values()));
		this.add(comboColor);
		comboColor.setModel(new DefaultComboBoxModel(Colores.values()));
		this.add(comboTalla);
		comboTalla.setModel(new DefaultComboBoxModel(TallasRopa.values()));

		
		/**
	     * Aunque los botones estén colocados y añadidos en la ventana, les quedará reaccionar si son pinchados. Para ello, les añadiremos un ActionListener a cada uno.
	     * **/
	    
	    /**
	     * Al hacer click en el botonPublicar, recogeremos toda la información pedida en la ventana y crearemos un nuevo objeto Ropa, además de producto.
	     * Deberemos contactar con la Base de Datos (tendremos que abrir la conexion) e insertar tanto la prenda como el producto.
	     * Se meterán en las tablas. No necesitaremos meterlo en el ArrayList de productosEnVenta del usuario, ya que no hay ningún atributo de esa clase en la base de datos,
	     * y además, podremos conseguirlo (los productos en venta del usuario en cuesión) mediante una consulta.
	     * Al estar enVenta = true, será visible para el resto de usuarios y seguirá así hasta que alguien compre el zapato o el vendedor lo borre de la plataforma.
	     * **/
		botonPublicar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreRopa = cajaNombre.getText();
				String etiquetaRopa = cajaEtiqueta.getText();
				double precioRopa = (double) spinnerPrecio.getValue();
				// HAY QUE CAMBIAR ESTO (imagen)
				Image imagenRopa = null;
				Estado estadoRopa = (Estado) comboEstado.getSelectedItem();
				Colores colorRopa = (Colores) comboColor.getSelectedItem();
				// HAY QUE CAMBIAR ESTO (usuario)
				Usuario usuarioRopa = new Usuario();
				TallasRopa tallaRopa = (TallasRopa) comboTalla.getSelectedItem();

				Ropa prenda = new Ropa(nombreRopa, etiquetaRopa, precioRopa, imagenRopa, estadoRopa, colorRopa,
						usuarioRopa, tallaRopa);

				prenda.getUsuario().getProductosEnVenta().add(prenda);

				BaseDeDatos.insertarProducto(prenda, null);
				// HAY QUE CREAR EN ALGUN LADO UN ARRAYLIST DE TODOS LOS PRODUCTOS DE DEUSTOPOP
				// Y METER AHI EL PRODUCTO CREADO

				// INTENTO LOGGER PRENDA VENDIDA
				// log.log(Level.INFO "Ropa vendida." );

			}
		});

		 /**
	     * El botonVolver se encargará de redirigir al cliente a la ventana anterior a la que estaba, es decir, la principal
	     * **/
		botonVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal.main(null);
				dispose();
			}
		});
		
		// NO FUNCIONA
		// necesita convertir el File en imagen
		botonFt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector=new JFileChooser();
			    FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG & PNG", "jpg","png");
			    selector.setFileFilter(filtroImagen);
			    int seleccion=selector.showOpenDialog(null);
			    if (seleccion == JFileChooser.APPROVE_OPTION) {
			    	File file=selector.getSelectedFile();
			    	/**
			    	try {
			    	    // Retrieve Image
			    	    BufferedImage buffer = ImageIO.read(new File("old.png"));;
			    	    // Here you can rotate your image as you want (making your magic)
			    	    File outputfile = new File("saved.png");
			    	    ImageIO.write(buffer, "png", outputfile); // Write the Buffered Image into an output file
			    	    Image image  = ImageIO.read(new File("saved.png")); // Opening again as an Image
			    	} catch (IOException e2) {
			    	    
			    	}
			    	**/
			    }
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		VentanaVenderRopa R = new VentanaVenderRopa(); // creamos una ventana
		R.setVisible(true); // hacemos visible la ventana creada
	}

}
