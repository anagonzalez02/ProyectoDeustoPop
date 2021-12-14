package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import clases.Colores;
import clases.Estado;
import clases.FuncionesGenerales;
import clases.Lugar;
import clases.Producto;
import clases.Usuario;

public class VentanaProducto extends JFrame {
	
	private Image imagen;				
	private JLabel nombreProducto;		
	private JLabel etiquetasProducto;	
	private JLabel nombreVendedor;		
	private JLabel precioProducto;
	private JButton btnFavorito;
	private JButton btnComprar;			
	private JButton btnVolver;			
	private JButton btnChat;
	
	Usuario uComprador = new Usuario ("peeepiitaa", 611111111, 63527191, "pepa@email.com", "jeje", new Lugar("Gran Via 54", "Bilbao", "Españita"));
	
	
	public VentanaProducto (Producto p) {
		
		btnFavorito = new JButton("<3");
		btnComprar = new JButton("Comprar");
		btnVolver = new JButton("Volver");
		btnChat = new JButton("Chat");
		nombreVendedor = new JLabel("@" + p.getUsuario().getNombre(), SwingConstants.RIGHT);
		nombreVendedor.setForeground(Color.GRAY);
	
		// PANELES
		
		Container cPanel = this.getContentPane();
		cPanel.setLayout(new BorderLayout());
		
		
		JPanel panelInformacion = new JPanel(new GridLayout(1, 2));
		nombreProducto = new JLabel("" + p.getNombre());
		panelInformacion.add(nombreProducto);
		precioProducto = new JLabel("" + p.getPrecio() + "€");
		panelInformacion.add(precioProducto);
		
		
		JPanel panelInfGeneral = new JPanel(new GridLayout(2, 1));
		panelInfGeneral.add(panelInformacion);
		etiquetasProducto = new JLabel("" + p.getEtiquetas());
		panelInfGeneral.add(etiquetasProducto);
		
		
		JPanel panelBotonera = new JPanel(new GridLayout(1, 2));
		panelBotonera.add(btnComprar);
		panelBotonera.add(btnFavorito);
		
		
		JPanel panelResto = new JPanel (new GridLayout(3, 1));
		panelResto.add(panelInfGeneral);
		panelResto.add(new JLabel(""));
		panelResto.add(panelBotonera);
		
		
		JPanel panelPrincipal = new JPanel (new GridLayout(2, 1));
		// AQUÍ HAY QUE METER LA IMAGEN
		panelPrincipal.add(new JLabel("Imagen"), BorderLayout.CENTER);
		panelPrincipal.add(panelResto, BorderLayout.CENTER);
		
		
		JPanel panelInferior = new JPanel(new GridLayout(1, 2));
		panelInferior.add(btnChat);
		panelInferior.add(btnVolver);
	
		
		// BOTONES
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal.main(null);
				dispose();
			}
		});
		
		
		// Hay que cambiar el usuario
		btnComprar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int atencionPanel = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quieres comprar este producto?", "Alerta!", JOptionPane.YES_NO_OPTION);
				if (atencionPanel == JOptionPane.YES_OPTION) {
					FuncionesGenerales.metodoComprarProducto(p, uComprador);
				}
			}
		});
		
		
		// Hay que cambiar el usuario
		btnFavorito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uComprador.getProductosFavoritos().contains(p)) {
					uComprador.getProductosFavoritos().remove(p);
					JOptionPane.showMessageDialog(null, p.getNombre() + "eliminado de favoritos");
				} else {
					uComprador.getProductosFavoritos().add(p);
					JOptionPane.showMessageDialog(null, p.getNombre() + "añadido a favoritos");
				}
			}
		});
		
		// Hay que cambiar el usuario
		btnChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// LLEVAR A CHAT O HACER COMENTARIO
			}
		});
				
		
		
		cPanel.add(panelPrincipal);
		cPanel.add(nombreVendedor, BorderLayout.NORTH);
		cPanel.add(panelInferior, BorderLayout.SOUTH);
		
		
		// Quedan el botón Chat y el Favorito
		
		// Hay que poner un corazon o rojo o vacio dependiendo de si es favorito o no
		
		
		 this.setTitle("" + p.getNombre());                   	 // colocamos titulo a la ventana
	     this.setSize(500, 700);                                 // colocamos tamanio a la ventana (ancho, alto)
	     this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
	     this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
		
		
	}
	

	
	public static void main(String[] args) {
		final Image Image = null;
		Usuario uVendedor = new Usuario ("peepee", 600000000, 8727193, "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		Producto producto = new Producto ("Zapatilla guay", "Cool", 10.65, Image, Estado.MALO, Colores.Azul, uVendedor);
    	
		VentanaProducto C = new VentanaProducto(producto);      // creamos una ventana, de momento con producto nulo
        C.setVisible(true);             // hacemos visible la ventana creada
    }
	
	// Falta que al clickar en un producto concreto de la VentanaPrincipal, quede registrado ese producto y lo introduzcamos como variable para esta ventana
	
	
	
}
