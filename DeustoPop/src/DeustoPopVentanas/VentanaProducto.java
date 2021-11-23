package DeustoPopVentanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Clases.Producto;

public class VentanaProducto extends JFrame {
	
	private Image imagen;				
	private JLabel nombreProducto;		
	private JLabel etiquetasProducto;	
	private JLabel nombreVendedor;		
	private JLabel precioProducto;		
	private JButton btnComprar;			
	private JButton btnVolver;			
	private JButton btnChat;
	
	private void configurarVentana() {
        this.setTitle("DeustoPop");                   			// colocamos titulo a la ventana
        this.setSize(500, 700);                                	// colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
    }
	
	public VentanaProducto (Producto p) {
	
		// PANELES
		
		JPanel panelPrincipal = new JPanel(new GridLayout(2,1));
		JPanel panelFoto = new JPanel();
		panelPrincipal.add(panelFoto);
		JPanel panelInformacion = new JPanel();
		panelPrincipal.add(panelInformacion);
		
		
		JPanel panelProducto = new JPanel(new GridLayout(1,3));
		panelInformacion.add(panelProducto, BorderLayout.NORTH);
		nombreProducto = new JLabel(p.getNombre());
		panelProducto.add(nombreProducto);
		precioProducto = new JLabel("" + p.getPrecio());
		panelProducto.add(precioProducto);
		etiquetasProducto = new JLabel(p.getEtiquetas());
		panelProducto.add(etiquetasProducto);
		
		
		JPanel panelComprar = new JPanel(new GridLayout(2,2));
		panelInformacion.add(panelComprar, BorderLayout.CENTER);
		nombreVendedor = new JLabel(p.getUsuario().getNombre());
		panelComprar.add(nombreVendedor);
		panelComprar.add(new JLabel());
		panelComprar.add(btnChat);
		panelComprar.add(btnComprar);
		
		JPanel panelVolver = new JPanel();
		panelInformacion.add(panelVolver, BorderLayout.SOUTH);
		panelVolver.add(btnVolver);
	
		
		// BOTONES
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal.main(null);
				dispose();
			}
		});
		
		// Quedan el botón Comprar y el botón Chat
		
		
		
		configurarVentana();
		
		
	}
	
	// No va
	
	public static void main(String[] args) {
    	VentanaProducto C = new VentanaProducto(null);      // creamos una ventana, de momento con producto nulo
        C.setVisible(true);             // hacemos visible la ventana creada
    }
	
	// Falta que al clickar en un producto concreto de la VentanaPrincipal, quede registrado ese producto y lo introduzcamos como variable para esta ventana
	
	
	
}
