package DeustoPopVentanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class VentanaVenderCalzado extends JFrame implements ActionListener {
	
	private JLabel textoNombre;           // etiqueta o texto no editable
    private JLabel textoFt;           // etiqueta o texto no editable
    private JLabel textoEstado;           // etiqueta o texto no editable
    private JLabel textoEtiqueta;           // etiqueta o texto no editable
    private JLabel textoPrecio;           // etiqueta o texto no editable
    private JLabel textoColores;           // etiqueta o texto no editable
    private JLabel textoTalla;			// etiqueta o texto no editable
    private JTextField cajaNombre;        // caja de texto, para insertar datos
    private JTextField cajaEtiqueta;        // caja de texto, para insertar datos
    private JSpinner spinnerPrecio;        // spinner, para insertar precio
    private JButton botonFt;          // botonVolver con una determinada accion
    private JButton botonPublicar;          // botonBuscar con una determinada accion
    private JButton botonVolver;          // botonBuscar con una determinada accion
    private JComboBox comboEstado;
    private JComboBox comboColor;
    private JSpinner spinnerTalla;        // spinner, para insertar numero de talla

    
    public VentanaVenderCalzado() {
        super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }
    
    private void configurarVentana() {
        this.setTitle("DeustoPop");                   			// colocamos titulo a la ventana
        this.setSize(500, 700);                                	// colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
    }
    
    private void inicializarComponentes() {
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
    spinnerTalla = new JSpinner();
    spinnerPrecio = new JSpinner();

    
    //configuracion de componentes
    
    textoNombre.setText("Nombre del producto:");
    textoNombre.setBounds(25, 30, 300, 20);								//(x, y, ancho, alto)
    textoFt.setText("Anyade aqui la foto:");
    textoFt.setBounds(25, 100, 300, 20);									//(x, y, ancho, alto)
    textoEstado.setText("Seleccione el estado de su producto:");
    textoEstado.setBounds(25, 240, 300, 20);								//(x, y, ancho, alto)
    textoEtiqueta.setText("Etiqueta tu producto:");
    textoEtiqueta.setBounds(25, 310, 300, 20);							//(x, y, ancho, alto)
    textoPrecio.setText("Seleccione el precio de su producto:");
    textoPrecio.setBounds(25, 380, 300, 20);								//(x, y, ancho, alto)
    textoColores.setText("Color del producto:");
    textoColores.setBounds(25, 450, 300, 20);								//(x, y, ancho, alto)
    textoTalla.setText("Talla del producto:");
    textoTalla.setBounds(25, 170, 300, 20);								//(x, y, ancho, alto)
    
    
    cajaNombre.setBounds(25, 50, 425, 30);
    cajaEtiqueta.setBounds(25, 330, 425, 30);
    
    botonFt.setText("Inserte una foto");
    botonFt.setBounds(25, 120, 425, 30);
    botonPublicar.setText("Publicar");
    botonPublicar.setBounds(250, 550, 200, 70);
    botonVolver.setText("Volver");
    botonVolver.setBounds(25, 550, 200, 70);
    
    comboEstado.setBounds(25, 260, 425, 30);
    comboColor.setBounds(25, 470, 425, 30);
    
    spinnerTalla.setBounds(25, 190, 425, 30);
    spinnerPrecio.setBounds(25, 400, 425, 30);
    
    //anyadimos los componentes
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
    comboEstado.addItem("Malo");
    comboEstado.addItem("Medio");
    comboEstado.addItem("Bueno");
    this.add(comboColor);
    comboColor.addItem("Negro");
    comboColor.addItem("Blanco");
    comboColor.addItem("Rojo");
    comboColor.addItem("Azul");
    comboColor.addItem("Verde");
    comboColor.addItem("Gris");
    comboColor.addItem("Otro");

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