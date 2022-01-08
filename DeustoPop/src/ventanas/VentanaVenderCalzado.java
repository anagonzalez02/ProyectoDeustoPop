package ventanas;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import BaseDatos.BaseDeDatos;
import clases.Calzado;
import clases.Colores;
import clases.Estado;
import clases.FuncionesGenerales;
import clases.Producto;
import clases.Usuario;

/**
 * La VentanaVenderCalzado se encarga de recopilar información sobre el producto, en este caso calzado, que se quiere vender en DeustoPop.
 * Para llegar aquí, el usuario vendedor deberá haber clickado en vender de la ventana principal y en la VentanaVender, vender calzado.
 * Si un usuario quiere vender un calzado, deberá completar toda la información pedida de este JFrame. 
 * Una vez hecho esto, el calzado será parte de la base de datos de la plataforma y se añadirá al ArrayList de ProductosEnVenta del usuario.
 * Seguirá visible para todos los usuarios hasta que un comprador realice un pedido con el producto.
 * **/

public class VentanaVenderCalzado extends JFrame implements ActionListener {
	
	/**
	 * Los componentes que usaremos son los siguientes:
	 * Para empezar tendremos los JLabels que se encargarán de señalizar de qué se trata el campo que hay al lado para completar.
	 * Esos campos serán distintos dependiendo de cada uno.
	 * El nombre y las etiquetas serán JTextField para que el usuario introduzca lo que desee con total libertad.
	 * Luego tenemos también dos spinners que usaremos uno para el precio (irá cada 0,05€) aunque también se podrá escribir directamente en él;
	 * al igual que para la talla que tendrá un máximo y un mínimo.
	 * Tenemos también dos JComboBox que serán para elegir entre una serie de opciones sobre el estado del producto y el color.
	 * Estas opciones serán opciones de las enumeraciones de dichos campos.
	 * Y por último tendremos el JButton para subir la imagen. Y otros dos para publicar el calzado y para volver a la página anterior.
	 * **/
	private JLabel textoNombre;       		// Texto no editable indicando el espacio para el nombre
    private JLabel textoFt;          		// Texto no editable indicando dónde subir la foto
    private JLabel textoEstado;        	 	// Texto no editable indicando las opciones de distintos estados
    private JLabel textoEtiqueta;     		// Texto no editable indicando el espacio para las etiquetas
    private JLabel textoPrecio;        	 	// Texto no editable indicando dónde poner el precio
    private JLabel textoColores;       		// Texto no editable indicando las opciones de color
    private JLabel textoTalla;				// Texto no editable indicando dónde elegir la talla
    private JTextField cajaNombre;    	 	// Caja de texto, para insertar el nombre del calzado
    private JTextField cajaEtiqueta;  		// Caja de texto, para insertar las etiquetas
    private JSpinner spinnerPrecio;   	  	// Spinner, para insertar el precio
    private JButton botonFt;          		// Botón hecho para subir la imagen del producto
    private JButton botonPublicar;      	// Botón para publicar el calzado en DeustoPop
    private JButton botonVolver;        	// Botón que servirá para volver a la ventana anterior
    private JComboBox comboEstado;			// Para elegir entre las opciones de la enumeración de Estado
    private JComboBox comboColor;			// Para elegir entre las opciones de la enumeración de Colores
    private JSpinner spinnerTalla;      	// Spinner, para insertar numero de talla

    /**
     * El constructor de la ventana se completará llamando a dos funciones que rellenen la ventana con los componentes y la configuren.
     * **/
    public VentanaVenderCalzado() {
        super();                    		// Usamos el contructor de la clase padre JFrame
        configurarVentana();        		// Configuramos la ventana mediante el método que hemos creado llamado configurarVentana()
        inicializarComponentes();   		// Inicializamos los atributos o componentes mediante el método creado inicializarComponentes()
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
	    textoPrecio = new JLabel();
	    textoColores = new JLabel();
	    textoTalla = new JLabel();
	    cajaNombre = new JTextField();
	    cajaEtiqueta = new JTextField();
	    
	    botonFt = new JButton();
	    botonPublicar = new JButton();
	    botonVolver = new JButton();
	    comboEstado = new JComboBox();
	    comboColor = new JComboBox();
	
	    /**
	     * Para inicializar el JSpinner de las tallas, le proporcionaremos una serie de números exactos por los que podrá decantarse el usuario.
	     * Estas opciones irán desde el 15 (talla mínima) hasta el 50 (talla máxima). Y no podrá salir de esos extremos. 
	     * Aquí podrán entrar las tellas de niños y de adultos.
	     * Inicializaremos el modelo del spinner (SpinnerModel) con estos números y se lo añadiremos al JSpinner.
	     * **/
	    final String numbers1[] = {"15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50"};
	    SpinnerModel model1 = new SpinnerListModel(numbers1);
	    spinnerTalla = new JSpinner(model1);
	    
	    /**
	     * Haremos algo parecido con el JSpinner del precio, aunque, en este caso, le pondremos un mínimo de 0.00€ y un máximo de 10000.00€.
	     * Así evitaremos que haya precios negativos y que tenga 2 decimales (para los céntimos), cumpliendo nuestro objetivo para el precio.
	     * Podrá subir y bajar cada 0.05€, pero también podrá escribir el precio directamente.
	     * **/
	    SpinnerNumberModel model2 = new SpinnerNumberModel(0.00, 0.00, 10000.00, 0.05);
	    spinnerPrecio = new JSpinner(model2);
	
	    
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
	     * Haremos lo mismo con los JTextField a la hora de colocarlos en la ventana.
	     * **/
	    cajaNombre.setBounds(25, 50, 425, 30);
	    cajaEtiqueta.setBounds(25, 330, 425, 30);
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
	    /**
	     * Terminamos de colocar todos los componentes.
	     * **/
	    spinnerTalla.setBounds(25, 190, 425, 30);
	    spinnerPrecio.setBounds(25, 400, 425, 30);
	    
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
	    this.add(spinnerTalla);
	    
	    this.add(comboEstado);
	    comboEstado.setModel(new DefaultComboBoxModel(Estado.values()));
	    this.add(comboColor);
	    comboColor.setModel(new DefaultComboBoxModel(Colores.values()));
	
	    
	    /**
	     * Aunque los botones estén colocados y añadidos en la ventana, les quedará reaccionar si son pinchados. Para ello, les añadiremos un ActionListener a cada uno.
	     * **/
	    
	    /**
	     * Al hacer click en el botonPublicar, recogeremos toda la información pedida en la ventana y crearemos un nuevo objeto Calzado, además de producto.
	     * Deberemos contactar con la Base de Datos (tendremos que abrir la conexion) e insertar tanto el zapato como el producto.
	     * Se meterán en las tablas. No necesitaremos meterlo en el ArrayList de productosEnVenta del usuario, ya que no hay ningún atributo de esa clase en la base de datos,
	     * y además, podremos conseguirlo (los productos en venta del usuario en cuesión) mediante una consulta.
	     * Al estar enVenta = true, será visible para el resto de usuarios y seguirá así hasta que alguien compre el zapato o el vendedor lo borre de la plataforma.
	     * **/
	    botonPublicar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreCalzado = cajaNombre.getText();
				String etiquetaCalzado = cajaEtiqueta.getText();
				double precioCalzado = (double) spinnerPrecio.getValue();
				// HAY QUE CAMBIAR ESTO (imagen)
				Image imagenCalzado = null;
				Estado estadoCalzado = (Estado) comboEstado.getSelectedItem();
				Colores colorCalzado = (Colores) comboColor.getSelectedItem();
				// HAY QUE CAMBIAR ESTO (usuario)
				Usuario usuarioCalzado = new Usuario();
				double tallaCalzado = (double) spinnerTalla.getValue();
				
				Calzado zapato = new Calzado (nombreCalzado, etiquetaCalzado, precioCalzado, imagenCalzado, estadoCalzado, colorCalzado, usuarioCalzado, tallaCalzado);
				
				zapato.getUsuario().getProductosEnVenta().add(zapato);
				
				BaseDeDatos.insertarProducto(zapato);
				
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
	    
	    
    
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    public static void main(String[] args) {
    	VentanaVenderCalzado C = new VentanaVenderCalzado();      // creamos una ventana
        C.setVisible(true);             // hacemos visible la ventana creada
    }
    

}