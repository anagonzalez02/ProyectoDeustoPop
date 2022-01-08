package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.ScrollPane;

public class VentanaAnimacion extends JFrame implements ActionListener{
	
	private JButton botonIniciar;
	private JLabel labelTexto;
	
	
	/*
	 * 
	 * al pulsar botonIniciar, en la ventana va apareciendo el titulo1
	 * 
	 * D
	 * De
	 * Deu
	 * Deus
	 * Deust
	 * Deusto
	 * DeustoP
	 * DeustoPo
	 * DeustoPop
	 * Deusto Pop
	 */
	
	public VentanaAnimacion() {
	    configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }
	
	
	public void configurarVentana() {

		this.setTitle("DeustoPop");                   			// colocamos titulo a la ventana
	    this.setSize(600, 400);                                	// colocamos tamanio a la ventana (ancho, alto)
	    this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
	    getContentPane().setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
	    this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
	}
	
	private void inicializarComponentes() {
		
		botonIniciar = new JButton();
		
		
		botonIniciar.setText("Iniciar");
	    botonIniciar.setBounds(200, 250, 200, 50);
	    
	    getContentPane().add(botonIniciar);
	    
	    labelTexto = new JLabel();
	    
	    labelTexto.setText("Deusto -Pop-");
	    labelTexto.setFont(new Font("Arial", Font.PLAIN, 50));
	    labelTexto.setBounds(160, 80, 300, 100);
	    
	    getContentPane().add(labelTexto);
	    
	    this.getContentPane().setBackground(Color.lightGray);
	    
	    
	    botonIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilo = new Thread(new Runnable() {
					public void run() {
						String palabra = "DeustoPop";
						String frase = "";
						for(char c : palabra.toCharArray()) {
							frase+= c;
							labelTexto.setText(frase);
							try {
								Thread.sleep(400);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						labelTexto.setText("Deusto Pop");
						try {
							Thread.sleep(600);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						
						VentanaPrincipal.main(null);
						dispose();
					}
				});
				
				hilo.start();
			}
		});
	    	    
	   
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    public static void main(String[] args) {
    	VentanaAnimacion C = new VentanaAnimacion();      // creamos una ventana
        C.setVisible(true);             // hacemos visible la ventana creada
    }
}
