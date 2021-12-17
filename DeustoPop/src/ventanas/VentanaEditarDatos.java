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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.BaseDeDatos;
import clases.CuentaBancaria;
import clases.Lugar;
import clases.Usuario;

public class VentanaEditarDatos extends JFrame {
	
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
	
	public VentanaEditarDatos (Usuario u)  {
		
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
		
		nombreUsuario = new JTextField();
		emailUsuario = new JTextField();
		telefonoUsuario = new JTextField();
		tarjetaUsuario = new JTextField();
		direccionUsuario = new JTextField();
		ciudadUsuario = new JTextField();
		paisUsuario = new JTextField();
		
		// PANELES
		
		nombreEtiqueta.setBounds(50, 30, 130, 30);
		nombreUsuario.setBounds(150, 30, 250, 30);
		emailEtiqueta.setBounds(50, 80, 130, 30);
		emailUsuario.setBounds(150, 80, 250, 30);
		telefonoEtiqueta.setBounds(50, 130, 130, 30);
		telefonoUsuario.setBounds(150, 130, 250, 30);
		tarjetaEtiqueta.setBounds(50, 180, 130, 30);
		tarjetaUsuario.setBounds(150, 180, 250, 30);
		direccionEtiqueta.setBounds(50, 230, 130, 30);
		direccionUsuario.setBounds(150, 230, 250, 30);
		ciudadEtiqueta.setBounds(50, 280, 130, 30);
		ciudadUsuario.setBounds(150, 280, 250, 30);
		paisEtiqueta.setBounds(50, 330, 130, 30);
		paisUsuario.setBounds(150, 330, 250, 30);
		
		btnCancelar.setBounds(50, 450, 150, 30);
		btnGuardar.setBounds(225, 450, 150, 30);
		
		this.add(nombreEtiqueta);
		this.add(nombreUsuario);
		this.add(emailEtiqueta);
		this.add(emailUsuario);
		this.add(telefonoEtiqueta);
		this.add(telefonoUsuario);
		this.add(tarjetaEtiqueta);
		this.add(tarjetaUsuario);
		this.add(direccionEtiqueta);
		this.add(direccionUsuario);
		this.add(ciudadEtiqueta);
		this.add(ciudadUsuario);
		this.add(direccionEtiqueta);
		this.add(direccionEtiqueta);
		this.add(paisEtiqueta);
		this.add(paisUsuario);
		
		
		
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
				
				int seguroPanel = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quieres actualizar los datos?", "Alerta!", JOptionPane.YES_NO_OPTION);
				
				if (seguroPanel == JOptionPane.YES_OPTION) {
					// RECOGER INFORMACIÓN
					String nuevoNombre;
					if (nombreUsuario.getText() == null) {
						nuevoNombre = u.getNombre();
					} else {
						nuevoNombre = nombreUsuario.getText();
						u.setNombre(nuevoNombre);
					}
					String nuevoEmail;
					if (emailUsuario.getText() == null) {
						nuevoEmail = u.getEmail();
					} else {
						nuevoEmail = emailUsuario.getText();
						u.setEmail(nuevoEmail);
					}
					int nuevoTelefono;
					if (telefonoUsuario.getText() == null) {
						nuevoTelefono = u.getTelefono();
					} else {
						nuevoTelefono = Integer.parseInt(telefonoUsuario.getText());
						u.setTelefono(nuevoTelefono);
					}
					int nuevoTarjeta;
					if (tarjetaUsuario.getText() == null) {
						nuevoTarjeta = u.getCuentaB().getnTarjeta();
					} else {
						nuevoTarjeta = Integer.parseInt(tarjetaUsuario.getText());
						u.setCuentaB(new CuentaBancaria(nuevoTarjeta, 0));;
					}
					String nuevoDireccion;
					String nuevoCiudad;
					String nuevoPais;
					String viejaDireccion = u.getVivienda().getDireccion();
					if (direccionUsuario.getText() == null && ciudadUsuario.getText() == null && paisUsuario.getText() == null) {
						nuevoDireccion = u.getVivienda().getDireccion();
						nuevoCiudad = u.getVivienda().getNomCiud();
						nuevoPais = u.getVivienda().getNomPais();
					} else {
						nuevoDireccion = direccionUsuario.getText();
						nuevoCiudad = ciudadUsuario.getText();
						nuevoPais = paisUsuario.getText();
					}
					Lugar nuevoVivienda = new Lugar(nuevoDireccion, nuevoCiudad, nuevoPais);
					u.setVivienda(nuevoVivienda);
					
					BaseDeDatos.modificarLugar(viejaDireccion, nuevoDireccion, nuevoCiudad, nuevoPais);
					BaseDeDatos.modificarUsuario(u.getIdUsuario(), nuevoNombre, nuevoTelefono, nuevoTarjeta, u.getSaldo(), nuevoEmail, nuevoVivienda, u.getProductosEnVenta(), u.getProductosVendidos(), u.getProductosComprados(), u.getProductosFavoritos());
					
					VentanaUsuario ventana = new VentanaUsuario(u);
					ventana.setVisible(true);
					dispose();
				}
			}
		});
		
		this.setTitle("Editar @" + u.getNombre());              // colocamos titulo a la ventana
	    this.setSize(450, 600);                                 // colocamos tamanio a la ventana (ancho, alto)
	    this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
	    this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
		
	}
	
	
	
	public static void main(String[] args) {
		Usuario usuario = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		VentanaEditarDatos ventana = new VentanaEditarDatos(usuario);      // creamos una ventana, de momento con producto nulo
        ventana.setVisible(true);             // hacemos visible la ventana creada
    }

}
