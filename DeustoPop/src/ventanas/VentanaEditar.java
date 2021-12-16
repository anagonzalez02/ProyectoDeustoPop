package ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Lugar;
import clases.Usuario;

public class VentanaEditar extends JFrame {
	
	private JTextField nombreUsuario;
	private JTextField emailUsuario;
	private JTextField telefonoUsuario;
	private JTextField tarjetaUsuario;
	private JTextField direccionUsuario;
	private JTextField ciudadUsuario;
	private JTextField paisUsuario;
	private JLabel nombreEtiqueta;
	private JLabel emailEtiqueta;
	private JLabel telefonoEtiqueta;
	private JLabel tarjetaEtiqueta;
	private JLabel direccionEtiqueta;
	private JLabel ciudadEtiqueta;
	private JLabel paisEtiqueta;
	
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	
	public VentanaEditar (Usuario u) {
		
		btnCancelar = new JButton("Cancelar");
		btnGuardar = new JButton("Guardar");
		
		
		
		Font letra = new Font("Times New Roman", Font.BOLD, 16);
		
		nombreEtiqueta = new JLabel("Nombre: ");
		nombreEtiqueta.setFont(letra);
		emailEtiqueta = new JLabel("Email: ");
		emailEtiqueta.setFont(letra);
		telefonoEtiqueta = new JLabel("Telefono: ");
		telefonoEtiqueta.setFont(letra);
		tarjetaEtiqueta = new JLabel("nº tarjeta: ");
		tarjetaEtiqueta.setFont(letra);
		direccionEtiqueta = new JLabel("Direccion: ");
		direccionEtiqueta.setFont(letra);
		ciudadEtiqueta = new JLabel("Ciudad: ");
		ciudadEtiqueta.setFont(letra);
		paisEtiqueta = new JLabel("Pais: ");
		paisEtiqueta.setFont(letra);
		
		
		// PANELES
		
		Container cPanel = this.getContentPane();
		cPanel.setLayout(new BorderLayout());
	
		JPanel panelBotonera = new JPanel(new GridLayout(1, 2));
		panelBotonera.add(btnCancelar);
		panelBotonera.add(btnGuardar);
		cPanel.add(panelBotonera, BorderLayout.SOUTH);
		
		JPanel panelGeneral = new JPanel(new GridLayout(7, 2));
		panelGeneral.add(nombreEtiqueta);
		panelGeneral.add(nombreUsuario);
		panelGeneral.add(emailEtiqueta);
		panelGeneral.add(emailUsuario);
		panelGeneral.add(telefonoEtiqueta);
		panelGeneral.add(telefonoUsuario);
		panelGeneral.add(tarjetaEtiqueta);
		panelGeneral.add(tarjetaUsuario);
		panelGeneral.add(direccionEtiqueta);
		panelGeneral.add(direccionUsuario);
		panelGeneral.add(ciudadEtiqueta);
		panelGeneral.add(ciudadUsuario);
		panelGeneral.add(direccionEtiqueta);
		panelGeneral.add(paisEtiqueta);
		
		cPanel.add(panelGeneral);
		
		
		// RECOGER INFORMACIÓN
		String nuevoNombre;
		if (nombreUsuario.getText() == null) {
			nuevoNombre = u.getNombre();
		} else {
			nuevoNombre = nombreUsuario.getText();
		}
		String nuevoEmail;
		if (emailUsuario.getText() == null) {
			nuevoEmail = u.getEmail();
		} else {
			nuevoEmail = emailUsuario.getText();
		}
		int nuevoTelefono;
		if (telefonoUsuario.getText() == null) {
			nuevoTelefono = u.getTelefono();
		} else {
			nuevoTelefono = Integer.parseInt(telefonoUsuario.getText());
		}
		int nuevoTarjeta;
		if (tarjetaUsuario.getText() == null) {
			nuevoTarjeta = u.getTarjeta();
		} else {
			nuevoTarjeta = Integer.parseInt(tarjetaUsuario.getText());
		}
		String nuevoDireccion;
		String nuevoCiudad;
		String nuevoPais;
		if (direccionUsuario.getText() == null && ciudadUsuario.getText() == null && paisUsuario.getText() == null) {
			nuevoDireccion = u.getVivienda().getDireccion();
			nuevoCiudad = u.getVivienda().getNomCiu();
			nuevoPais = u.getVivienda().getNomPais();
		} else {
			nuevoDireccion = direccionUsuario.getText();
			nuevoCiudad = ciudadUsuario.getText();
			nuevoPais = paisUsuario.getText();
		}
		
		
		
		// BOTONES
		
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaUsuario ventana = new VentanaUsuario(u);
				ventana.setVisible(true);
				dispose();
			}
		});
		

		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				VentanaUsuario ventana = new VentanaUsuario(u);
				ventana.setVisible(true);
				dispose();
			}
		});
		
		this.setTitle("Editar @" + u.getNombre());                   			 // colocamos titulo a la ventana
	    this.setSize(500, 700);                                 // colocamos tamanio a la ventana (ancho, alto)
	    this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
	    this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
		
		
	}
	
	public static void main(String[] args) {
		Usuario usuario = new Usuario ("peepee", 600000000, 8727193, "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		VentanaEditar ventana = new VentanaEditar(usuario);      // creamos una ventana, de momento con producto nulo
        ventana.setVisible(true);             // hacemos visible la ventana creada
    }

}
