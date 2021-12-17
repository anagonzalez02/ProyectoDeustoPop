package ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.BaseDeDatos;
import clases.CuentaBancaria;
import clases.Lugar;
import clases.Producto;
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
		
		nombreUsuario = new JTextField();
		emailUsuario = new JTextField();
		telefonoUsuario = new JTextField();
		tarjetaUsuario = new JTextField();
		direccionUsuario = new JTextField();
		ciudadUsuario = new JTextField();
		paisUsuario = new JTextField();
		
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
		panelGeneral.add(paisEtiqueta);
		panelGeneral.add(paisUsuario);
		
		cPanel.add(panelGeneral);
		
		
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
					// QUEDA MODIFICARCUENTABANCARIA
					
					
					VentanaUsuario ventana = new VentanaUsuario(u);
					ventana.setVisible(true);
					dispose();
				}
			}
		});
		
		this.setTitle("Editar @" + u.getNombre());              // colocamos titulo a la ventana
	    this.setSize(450, 500);                                 // colocamos tamanio a la ventana (ancho, alto)
	    this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
	    this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
		
		
	}
	
	public static void main(String[] args) {
		Usuario usuario = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		VentanaEditar ventana = new VentanaEditar(usuario);      // creamos una ventana, de momento con producto nulo
        ventana.setVisible(true);             // hacemos visible la ventana creada
    }

}
