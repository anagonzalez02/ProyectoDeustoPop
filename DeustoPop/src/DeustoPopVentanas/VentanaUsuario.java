package DeustoPopVentanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Clases.Usuario;

public class VentanaUsuario extends JFrame {
	
	private JLabel nombreUsuario;
	private JLabel telefonoUsuario;
	private JLabel emailUsuario;
	private JLabel saldoUsuario;
	private JLabel tartjetaUsuario;
	private JButton btnVolver;	
	
	public VentanaUsuario (Usuario s) {
		
		
		
		
		
		
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal.main(null);
				dispose();
			}
		});
		
		
		this.setTitle("" + s.getNombre());                   			 // colocamos titulo a la ventana
	    this.setSize(500, 700);                                 // colocamos tamanio a la ventana (ancho, alto)
	    this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
	    this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
		
	}
	
	public static void main(String[] args) {
		VentanaUsuario C = new VentanaUsuario(null);      // creamos una ventana, de momento con producto nulo
        C.setVisible(true);             // hacemos visible la ventana creada
    }
	

}
