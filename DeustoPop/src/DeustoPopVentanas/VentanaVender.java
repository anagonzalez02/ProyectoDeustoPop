package DeustoPopVentanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class VentanaVender extends JFrame implements ActionListener {

    private JLabel textoVender;           // etiqueta o textoBuscador no editable
    private JButton botonVenderRopa;          // botonVolver con una determinada accion
    private JButton botonVenderCalzado;          // botonBuscar con una determinada accion
    private JButton botonVolver;          // botonBuscar con una determinada accion

    
    public VentanaVender() {
        super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }

    private void configurarVentana() {
        this.setTitle("Vender");                     			// colocamos titulo a la ventana
        this.setSize(320, 220);                                	// colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
    }

    private void inicializarComponentes() {
        // creamos los componentes
        textoVender = new JLabel();
        botonVenderRopa = new JButton();
        botonVenderCalzado = new JButton();
        botonVolver = new JButton();
        
        // configuramos los componentes
        textoVender.setText("¿Que producto desea vender?");   // colocamos un texto a la etiqueta
        textoVender.setBounds(50, 20, 200, 30);				// colocamos posicion y tamanio al texto (x, y, ancho, alto)
        
        botonVenderRopa.setText("Ropa");					// colocamos un texto al botonVolver
        botonVenderRopa.setBounds(50, 50, 100, 45);			// colocamos posicion y tamanio al botonVolver (x, y, ancho, alto)
//      botonVenderRopa.addActionListener(this);			// hacemos que el botonVolver tenga una accion y esa accion estara en esta clase
        
        botonVenderCalzado.setText("Calzado");					// colocamos un texto al botonBuscar
        botonVenderCalzado.setBounds(160, 50, 100, 45);			// colocamos posicion y tamanio al botonBuscar (x, y, ancho, alto)
//      botonVenderCalzado.addActionListener(this);			// hacemos que el botonBuscar tenga una accion y esa accion estara en esta clase
        
        botonVolver.setText("Volver");					// colocamos un texto al botonBuscar
        botonVolver.setBounds(50, 120, 210, 35);			// colocamos posicion y tamanio al botonBuscar (x, y, ancho, alto)
//      botonVolver.addActionListener(this);			// hacemos que el botonBuscar tenga una accion y esa accion estara en esta clase
        
        // anyadimos los componentes a la ventana
        this.add(textoVender);
        this.add(botonVenderRopa);
        this.add(botonVenderCalzado);
        this.add(botonVolver);
        
        botonVolver.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			VentanaPrincipal.main(null);
    			dispose();
    		}
    	});
        
        botonVenderRopa.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			VentanaVenderRopa ventana = new VentanaVenderRopa();
    	        ventana.setVisible(true);
    			dispose();
    		}
    	});
        
        botonVenderCalzado.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			VentanaVenderCalzado ventana = new VentanaVenderCalzado();
    	        ventana.setVisible(true);
    			dispose();
    		}
    	});
        
        
    }


    public static void main(String[] args) {
    	VentanaVender B = new VentanaVender();      // creamos una ventana
        B.setVisible(true);             // hacemos visible la ventana creada
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

//PRUEBA PULL DESDE PC NUEVO
