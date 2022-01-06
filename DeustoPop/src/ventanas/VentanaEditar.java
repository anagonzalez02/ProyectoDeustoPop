package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BaseDatos.BaseDeDatos;
import clases.CuentaBancaria;
import clases.Lugar;
import clases.Usuario;

/**
 * La VentanaEditar se encarga de editar los datos personales del cliente. 
 * Es decir, cuando un cliente esté navegando en la plataforma DeustoPop, podrá acceder a su perfil
 * 		y, en caso de querer cambiar algún dato personal (nombre de usuario, email, telefono...)
 * Desde el perfil del usuario (VentanaUsuario), al clickar en el botón "Editar datos" (btnEditar), el usuario será redirigido a este JFrame.
 * Aquí tendrá un espacio para actualizar/cambiar algunos campos: nombre de usuario, email, número de telefono, número de tarjeta, direccion y pais.
 * Aunque estos no son todos los atributos de un tipo Usuario, ya que quedarían por cambiar:
 * 		el identificativo (idUsuario), el saldo propio de DeustoPop, la contraseña, y todos los ArrayList de productos (en venta, vendidos, comprados y favoritos).
 * Las razones son las siguientes:
 * 		idUsuario -> 		Cada usuario tiene un identificativo único que no podrá cambiarse, porque será la manera de identificar sin problema al cliente.
 * 							Nadie podrá ver ni saber cuál es su id propio, solamente se verá en la base de datos.
 * 		saldo ->			El saldo es un campo que se irá actualizando a medida que se compren/vendan productos,
 * 							pero no es editable por el usuario ya que, de serlo, podría sumarse dinero que no tiene.
 * 		contrasenia ->		En caso de que una persona ajena hackee la cuenta de un usuario, no sabríamos si la edición de la contraseña sería de parte del usuario propio
 * 							o de alguien que quiere robar la cuenta. Por ello, la contraseña introducida al registrarse, será siempre la misma.
 * 		los ArrayList -> 	Estos ArrayList irán actualizándose a medida que el usuario compre, venda, suba productos, o le gusten artículos. Pero no serán editables por parte del dueño.
 * **/

public class VentanaEditar extends JFrame {
	
	/**
	 * Los siguientes JTextFields serán los campos de en los que el usuario podrá actualizar los datos.
	 * **/
	private JTextField nombreUsuario;			// Campo para el nuevo nombre de usuario
	private JTextField emailUsuario;			// Campo para el nuevo email del usuario
	private JTextField telefonoUsuario;			// Campo para el nuevo teléfono del usuario
	private JTextField tarjetaUsuario;			// Campo para el nuevo número de tarjeta del usuario
	private JTextField direccionUsuario;		// Campo para la nueva dirección del usuario
	private JTextField ciudadUsuario;			// Campo para la nueva ciudad del usuario
	private JTextField paisUsuario;				// Campo para el nuevo pais del usuario
	/**
	 * Los siguientes JLabels serán para indicar al usuario a qué atributo pertenece cada campo del JFrame
	 * **/
	private JLabel nombreEtiqueta;				// Indica dónde escribir el nuevo nombre de usuario
	private JLabel emailEtiqueta;				// Indica dónde escribir el nuevo email del usuario
	private JLabel telefonoEtiqueta;			// Indica dónde escribir el nuevo teléfono del usuario
	private JLabel tarjetaEtiqueta;				// Indica dónde escribir el nuevo número de tarjeta del usuario
	private JLabel direccionEtiqueta;			// Indica dónde escribir la nueva dirección del usuario
	private JLabel ciudadEtiqueta;				// Indica dónde escribir la nueva ciudad del usuario
	private JLabel paisEtiqueta;				// Indica dónde escribir el nuevo país del usuario
	// En caso de que el usuario se mude, necesitaremos preguntar tanto por la nueva dirección, como por la nueva ciudad como por el nuevo país.
	// No sabremos si se ha mudado de ciudad o de país o simplemente a la casa de al lado o a otro barrio. Por eso preguntamos los tres campos.
	// En caso de ser solamente de direccion o de ciudad, no hará falta que complete los compos restantes: país o incluso ciudad.
	
	/**
	 * Los siguientes JButtons serán los botones que el usuario dispondrá en la ventana
	 * **/
	private JButton btnCancelar;				// Botón para cancelar la actualización de los datos personales
	private JButton btnGuardar;					// Botón para guardar los datos actualizados. 
												// Una vez clickado, ya no habrá vuelta atrás, a no ser que se vuelvan a actualizar
	
	/**
	 * La ventana recibirá como parámetro el usuario que querrá actualizar/cambiar sus datos personales.
	 * Esta ventana, a diferencia de otras, nunca recibirá un usuario como null, ya que para llegar hasta aquí, deberá entrar primero a VentanaUsuario (es decir, el perfil).
	 * Y ahí, en caso de ser null, el programa hará lo necesario para que el cliente inicie sesión o se registre. 
	 * Por lo tanto, en caso de entrar en VentanaEditar, el usuario ya estará metido en su cuenta y no será null.
	 * **/
	public VentanaEditar (Usuario u) {
		
		btnCancelar = new JButton("Cancelar");		// El botón btnCancelar se distinguirá en la ventana como "Cancelar"
		btnGuardar = new JButton("Guardar");		// El botón btnGuardar se distinguirá en la ventana como "Guardar"
		
		/**
		 * Creamos una fuente nueva que usaremos en los JLabels del JFrame.
		 * Esta fuente será con letra de tipo Times New Roman, escrito en negrita y tamaño 16.
		 * **/
		Font letra = new Font("Times New Roman", Font.BOLD, 16);
		
		/**
		 * Inicializamos los JLabels, asignándoles su etiqueta.
		 * y les ponemos la fuente recientemente creada mediante un .setFont().
		 * **/
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
		
		/**
		 * Inicializamos los JTextField para mostrarlos en la ventana y poder usarlos en el programa.
		 * **/
		nombreUsuario = new JTextField();
		emailUsuario = new JTextField();
		telefonoUsuario = new JTextField();
		tarjetaUsuario = new JTextField();
		direccionUsuario = new JTextField();
		ciudadUsuario = new JTextField();
		paisUsuario = new JTextField();
		
		
		// PANELES
		/**
		 * Creamos un contenedor al que llamaremos "cPanel".
		 * Se encargará de recuperar la capa del panel de contenido para se que puedan añadir, en este caso, los JLabels, JTextField y los Botones.
		 * Completaremos el contenedor con dos paneles distintos: panelBotonera y panelGeneral.
		 * **/
		Container cPanel = this.getContentPane();
		cPanel.setLayout(new BorderLayout());
	
		/**
		 * El panelGeneral se encargará de contener los JLabels y los JTextFields.
		 * Para empezar, haremos que el panelGeneral, sea un GridLayout de 7 filas y 2 columnas; en total, 14 huecos.
		 * Donde cada fila será un campo; y en caso de las columnas, la primera columna será para los JLabels y la segunda para sus respectivos JTextFields.
		 * Pondremos un fondo blanco para sustituir el color gtris que nos sale por defecto.
		 * Iremos añadiendo de uno en uno cada componente, asegurándonos de ir en el orden correcto.
		 * Y por último, añadiremos el panel al contenedor de la ventana.
		 * **/
		JPanel panelGeneral = new JPanel(new GridLayout(7, 2));
		panelGeneral.setBackground(Color.WHITE);
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
		
		/**
		 * En cuanto al panelBotonera, será el encargado de tener los dos botones del JFrame.
		 * Al igual que el general, lo convertiremos en un GridLayout de una fila y dos columnas (cada una para un botón).
		 * Volveremos a poner el fondo de blanco para sustituir el gris e ir a juego con el panelGeneral.
		 * Añadimos los botones btnCancelar y btnGuardar.
		 * Y por último, colocamos el panel en el contenedor, pero en la parte sur (parte inferior), para darle mayor espacio al panelGeneral.
		 * **/
		JPanel panelBotonera = new JPanel(new GridLayout(1, 2));
		panelBotonera.setBackground(Color.WHITE);
		panelBotonera.add(btnCancelar);
		panelBotonera.add(btnGuardar);
		cPanel.add(panelBotonera, BorderLayout.SOUTH);
		
		
		// BOTONES
		/**
		 * El JButton btnCancelar se encarga de cancelar la operación de actualizar los datos y volver a la ventanaUsuario.
		 * Le añadimos un ActionListener para que reaccione al pulsarse.
		 * Al pulsarlo, el programa recorrerá todos los JTextFields. Si todos están vacíos (habrá que compararlos con null), no habrá habido cambios nuevos.
		 * Por lo tanto, se saldrá de este JFrame para ir al perfil del usuario.
		 * Si alguno de los JTextFields no está vacío, será que ha habido algún posible cambio que igual no se quiere borrar. Para asegurarnos de eso, saldrá un mensaje de confirmación.
		 * Este preguntará si está seguro de que quiere borrar los cambios. Si la respuesta es sí, se saldrá de esta ventana para ir a la del perfil de usuario.
		 * En caso de clickar en no, no se hará nada, es decir, ni saldrá de la ventana, ni se borrarán los cambios, ni se guardarán. 
		 * **/
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nombreUsuario.getText() == null && emailUsuario.getText() == null && telefonoUsuario.getText() == null && tarjetaUsuario.getText() == null
						&& direccionUsuario.getText() == null && ciudadUsuario.getText() == null && paisUsuario.getText() == null) {
					VentanaUsuario ventana = new VentanaUsuario(u);
					ventana.setVisible(true);
					dispose();
				} else {
					int seguroMensaje = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quieres salir sin guardar los cambios?", "Alerta!", JOptionPane.YES_NO_OPTION);
					if (seguroMensaje == JOptionPane.YES_OPTION) {
						VentanaUsuario ventana = new VentanaUsuario(u);
						ventana.setVisible(true);
						dispose();
					}
				}
			}
		});
		
		/**
		 * El JButton guardar se encarga de realizar los cambios en la base de datos. 
		 * Para que reaccione cuando se hace click en él, le añadimos un ActionListener que recorrerá los JTextFields de la ventana y, dependiendo de ellos, se comportará de una manera u otra.
		 * En caso de que todos los JTextFields estén vacíos, es decir que no haya dato cambio que actualizar, saldrá un mensaje por pantalla
		 * avisando al usuario de que no ha habido ningún cambio.
		 * Si en algún JTextField se ha escrito algo (no es igual a null), eso es que hay algún cambio que hacer.
		 * Saldrá un mensaje de confirmación para preguntar al cliente si está seguro de hacer los cambios. Si responde que sí, empezarán a cambiarse los datos no nulos.
		 * Para ello se abrirá la conexión con la BaseDeDatos, con ayuda de un TryCatch para evitar errores.
		 * Por cada campo, se creará una nueva variable: este puede ser el texto introducido en el JTextField (si se ha introducido algo) o el del usuario propio.
		 * 		EJEMPLO:
		 * 			Se crea una variable para el nombre, que llamaremos nuevoNombre. El programa mirará si se ha introducido algo en el JTextField "nombreUsuario".
		 * 			Si es así, nuevoNombre será lo escito en ese espacio. Si no, será el nombre propio del usuario.
		 * En el caso del nombre de usuario y de email, nos aseguraremos de que los nuevos campos introducidos no existen en la base de datos (ya que deben ser únicos).
		 * Además, si el usuario introduce signos de puntuación extraños, saldrá un mensaje diciendo que no puede meterlo.
		 * Una vez hecho esto con todos los parámetros, llegará el turno de contactar con la base de datos.
		 * Para ello se llamará a los métodos modificarLugar, modificarUsuario y modificarCuentaBancaria. 
		 * Habrá que introducir todas las nuevas variables recién creadas (pueden ser los campos viejos (no se ha introducido nada en sus respectivos JTextField) o los nuevos).
		 * Una vez actualizado todo, cerraremos la conexión con la base de datos.
		 * LLamaremos a la VentanaUsuario (de donde venía anteriormente el usuario) donde podrá ver los datos editados y cerraremos esta ventana.
		 * **/
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (nombreUsuario.getText() == null && emailUsuario.getText() == null && telefonoUsuario.getText() == null && tarjetaUsuario.getText() == null
						&& direccionUsuario.getText() == null && ciudadUsuario.getText() == null && paisUsuario.getText() == null) {
					JOptionPane.showMessageDialog(null, "No ha habido ningún cambio.");
				} 
				else {
				
					int seguroPanel = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quieres actualizar los datos?", "Alerta!", JOptionPane.YES_NO_OPTION);
					
					if (seguroPanel == JOptionPane.YES_OPTION) {
						
						try {
							BaseDeDatos.abrirConexion("DeustoPop.bd", false);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						// RECOGER INFORMACIÓN
						String nuevoNombre;
						if (nombreUsuario.getText() == null) {
							nuevoNombre = u.getNombre();
						} else {
							if (BaseDeDatos.existeNombreUsuario(nombreUsuario.getText())) {
								JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe. Prueba con otro.");
							} else {
								int i = 0;
								while (nombreUsuario.getText().length() < i) {
									if (String.valueOf(nombreUsuario.getText().charAt(i)) == " ") {
										JOptionPane.showMessageDialog(null, "El nombre de usuario no puede tener espacios.");
										break;
									} else if (String.valueOf(nombreUsuario.getText().charAt(i)) == "!" || String.valueOf(emailUsuario.getText().charAt(i)) == "¡") {
										JOptionPane.showMessageDialog(null, "El nombre de usuario no puede tener signos de exclamación.");
										break;
									} else if (String.valueOf(nombreUsuario.getText().charAt(i)) == "?" || String.valueOf(emailUsuario.getText().charAt(i)) == "¿") {
										JOptionPane.showMessageDialog(null, "El nombre de usuario no puede tener signos de interrogación.");
										break;
									} else if (String.valueOf(nombreUsuario.getText().charAt(i)) == "-") {
										JOptionPane.showMessageDialog(null, "El nombre de usuario no puede tener el signo '-'");
										break;
									} 
								}
							}
							nuevoNombre = nombreUsuario.getText();
						}
						String nuevoEmail;
						if (emailUsuario.getText() == null) {
							nuevoEmail = u.getEmail();
						} else {
							if (BaseDeDatos.existeNombreUsuario(emailUsuario.getText())) {
								JOptionPane.showMessageDialog(null, "El email ya existe. Prueba con otro.");
							} else {
								int i = 0;
								while (emailUsuario.getText().length() < i) {
									if (String.valueOf(emailUsuario.getText().charAt(i)) == " ") {
										JOptionPane.showMessageDialog(null, "El email no puede tener espacios.");
										break;
									} else if (String.valueOf(emailUsuario.getText().charAt(i)) == "!" || String.valueOf(emailUsuario.getText().charAt(i)) == "¡") {
										JOptionPane.showMessageDialog(null, "El email no puede tener signos de exclamación.");
										break;
									} else if (String.valueOf(emailUsuario.getText().charAt(i)) == "?" || String.valueOf(emailUsuario.getText().charAt(i)) == "¿") {
										JOptionPane.showMessageDialog(null, "El email no puede tener signos de interrogación.");
										break;
									} else if (String.valueOf(emailUsuario.getText().charAt(i)) == "-") {
										JOptionPane.showMessageDialog(null, "El email no puede tener el signo '-'");
										break;
									} 
								}
							}
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
							nuevoTarjeta = u.getCuentaB().getnTarjeta();
						} else {
							nuevoTarjeta = Integer.parseInt(tarjetaUsuario.getText());
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
						
						BaseDeDatos.modificarLugar(viejaDireccion, nuevoDireccion, nuevoCiudad, nuevoPais);
						BaseDeDatos.modificarUsuario(u.getIdUsuario(), nuevoNombre, nuevoTelefono, nuevoTarjeta, u.getSaldo(), nuevoEmail, nuevoDireccion);
						BaseDeDatos.modificarCuentaBancaria(u.getCuentaB().getnTarjeta(), u.getIdUsuario(), nuevoTarjeta, u.getCuentaB().getDineroTotal());
						
						BaseDeDatos.cerrarConexion();
						
						VentanaUsuario ventana = new VentanaUsuario(u);
						ventana.setVisible(true);
						dispose();
					}
				}
			}
		});
		
		
		/**
		 * Por último, solo nos quedará aportarle unos últimos detalles a la ventana.
		 * **/
		this.setTitle("Editar @" + u.getNombre());              // La ventana tendrá como título: "Editar @..." donde ... será el nombre de usuario
	    this.setSize(450, 400);                                 // Le daremos una anchura de 450 y una altura de 400
	    this.setLocationRelativeTo(null);                       // Centramos la ventana en el centro de la pantalla del ordenador, pero el usuario podrá cambiar la posición si lo desea
	    this.setResizable(false);                               // No dejaremos que el usuario pueda redimensionar/agrandar la ventana
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Una vez cerrada la ventana, se terminará todo el proceso
		
		
	}
	
	public static void main(String[] args) {
		Usuario usuario = new Usuario ("peepee", 600000000, new CuentaBancaria(8727193, 3), "pepeee@email.com", "contrasenya", new Lugar("Calle Dato 4", "Vitoria", "Espana"));
		VentanaEditar ventana = new VentanaEditar(usuario);      // creamos una ventana, de momento con producto nulo
        ventana.setVisible(true);             // hacemos visible la ventana creada
    }

}
