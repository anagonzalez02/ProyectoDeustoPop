package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaAnimacion extends JFrame implements ActionListener{
	
	private Timer t;
	private JButton botonIniciar;
	private JLabel labelTexto;
	private int x = 0;
	private final String titulo1 = "\n Deusto  o  \n";
	private final String titulo2 = "\n Deusto -o- \n";
	private final String titulo3 = "\n Deusto Pop \n";
	
	public VentanaAnimacion() {
	    configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }
	
	
	public void configurarVentana() {

		this.setTitle("DeustoPop");                   			// colocamos titulo a la ventana
	    this.setSize(500, 700);                                	// colocamos tamanio a la ventana (ancho, alto)
	    this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
	    this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
	    this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
	}
	
	private void inicializarComponentes() {
		//labelTexto.setText(String.copyValueOf(titulo1.length()));
		
		botonIniciar = new JButton();
		
		
		botonIniciar.setText("Iniciar");
	    botonIniciar.setBounds(140, 570, 200, 50);
	    
	    this.add(botonIniciar);
	    
	    
	    
	    
	    
	    
	    /*
	    botonIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				x = x+1;
				if (x == titulo1.length()) {
					t.stop();
				}
				try {
					((Appendable) labelTexto).append(titulo1.substring(x,x+1));
				} catch(Exception err) {
					System.out.println("ERROR");
				}
				
				
				
				VentanaPrincipal.main(null);
				dispose();
			}
		});
	    t = new Timer(50);
	    
	    
	*/   
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
