package DeustoPopVentanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class VentanaBuscador extends JFrame implements ActionListener {

    private JLabel textoBuscador;           // etiqueta o textoBuscador no editable
    private JTextField cajaBuscador;        // caja de texto, para insertar datos
    private JButton botonVolver;          // botonVolver con una determinada accion
    private JButton botonBuscar;          // botonBuscar con una determinada accion

    
    public VentanaBuscador() {
        super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }

    private void configurarVentana() {
        this.setTitle("DeustoPop");                   			// colocamos titulo a la ventana
        this.setSize(500, 200);                                	// colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
    }

    private void inicializarComponentes() {
        // creamos los componentes
        textoBuscador = new JLabel();
        cajaBuscador = new JTextField();
        botonVolver = new JButton();
        botonBuscar = new JButton();
        
        // configuramos los componentes
        textoBuscador.setText("¿Que producto desea buscar?");   // colocamos un texto a la etiqueta
        textoBuscador.setBounds(50, 50, 200, 30);				// colocamos posicion y tamanio al texto (x, y, ancho, alto)
        cajaBuscador.setBounds(50, 80, 300, 35);				// colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        botonVolver.setText("Volver");					// colocamos un texto al botonVolver
        botonVolver.setBounds(375, 30, 75, 35);			// colocamos posicion y tamanio al botonVolver (x, y, ancho, alto)
//        botonVolver.addActionListener(this);			// hacemos que el botonVolver tenga una accion y esa accion estara en esta clase
        botonBuscar.setText("Buscar");					// colocamos un texto al botonBuscar
        botonBuscar.setBounds(375, 80, 75, 35);			// colocamos posicion y tamanio al botonBuscar (x, y, ancho, alto)
//        botonBuscar.addActionListener(this);			// hacemos que el botonBuscar tenga una accion y esa accion estara en esta clase
        // anyadimos los componentes a la ventana
        this.add(textoBuscador);
        this.add(cajaBuscador);
        this.add(botonVolver);
        this.add(botonBuscar);
        
        
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
        //String busacado = cajaBuscador.getText();                                 // obtenemos el contenido de la caja de texto
    }
    
    
    
    

    public static void main(String[] args) {
    	VentanaBuscador B = new VentanaBuscador();      // creamos una ventana
        B.setVisible(true);             // hacemos visible la ventana creada
    }
}