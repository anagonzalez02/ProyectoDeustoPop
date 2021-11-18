package DeustoPopVentanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Clases.Lugar;
import Clases.Usuario;


public class VentanaRegistrar extends JFrame implements ActionListener{

	private JLabel textoNombre;
	private JLabel textoPassword;
	private JTextField cajaNombre;
	private JPasswordField password;
	private JButton botonRegistrar;
	private JFrame ventanaActual;
	private JLabel textoTelefono;
	private JTextField cajaTelefono;
	private JLabel textoEmail;
	private JTextField cajaEmail;
	private JLabel textoTarjeta;
	private JTextField cajaTarjeta;
	private JLabel textoCiudad;
	private JTextField cajaCiudad;
	private JLabel textoPais;
	private JTextField cajaPais;
	private JLabel textoDireccion;
	private JTextField cajaDireccion;
	private JButton botonVolver;
	
    public VentanaRegistrar() {
        super();    				// usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();	// inicializamos los atributos o componentes
        ventanaActual = this;
    }
    
    private void configurarVentana() {
        this.setTitle("DeustoPop");                   			// colocamos titulo a la ventana
        this.setSize(450, 550);                                	// colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
    }
    
    private void inicializarComponentes() {
    	textoNombre = new JLabel();
    	textoPassword = new JLabel();
    	cajaNombre = new JTextField();
    	password = new JPasswordField();
    	textoTelefono= new JLabel();
    	cajaTelefono= new JTextField();
    	textoEmail= new JLabel();
    	cajaEmail= new JTextField();
    	textoTarjeta= new JLabel();
    	cajaTarjeta= new JTextField();
    	textoCiudad= new JLabel();
    	cajaCiudad= new JTextField();
    	textoPais= new JLabel();
    	cajaPais= new JTextField();
    	textoDireccion= new JLabel();
    	cajaDireccion= new JTextField();
    	botonVolver = new JButton();
    	
   
    	botonRegistrar = new JButton("REGISTRARME");
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String d = password.getText();
				String er = "[0-9]{1,}[A-Z]{1,}[a-z]{1,}";
				boolean correcto = Pattern.matches(er, d);
				if(correcto) {
					String n= cajaNombre.getText();
					int telf = Integer.parseInt(cajaTelefono.getText());
					int tarj = Integer.parseInt(cajaTarjeta.getText());
					String email= cajaEmail.getText();
					String ciudad= cajaCiudad.getText();
					String direccion= cajaDireccion.getText();
					String pais = cajaPais.getText();
					Lugar l = new Lugar(ciudad, direccion, pais);
					Usuario u = new Usuario(n, telf, tarj, email, d, l);
					JOptionPane.showMessageDialog(null, "Registro realizado con exito", "REGISTRO", JOptionPane.INFORMATION_MESSAGE);
					vaciarCampos();
				}else {
					JOptionPane.showMessageDialog(null, "La contrasenia debe tener al menos 1 mayuscula, 1 minuscula, 1 numero", "¡¡ERROR!!",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
    	
        textoNombre.setText("Nombre de usuario:");   // colocamos un texto a la etiqueta
        textoNombre.setBounds(50, 30, 130, 30);		// colocamos posicion y tamanio del texto (x, y, ancho, alto)
        
        textoPassword.setText("Contrasenia:");   	// colocamos un texto a la etiqueta
        textoPassword.setBounds(50, 80, 130, 30);	// colocamos posicion y tamanio del texto (x, y, ancho, alto)
    	
        
        textoTelefono.setText("Telefono");   // colocamos un texto a la etiqueta
        textoTelefono.setBounds(50, 130, 130, 30);	
        
        textoEmail.setText("Email:");   // colocamos un texto a la etiqueta
        textoEmail.setBounds(50, 180, 130, 30);
        
        
        textoTarjeta.setText("Tarjeta:");   // colocamos un texto a la etiqueta
        textoTarjeta.setBounds(50, 230, 130, 30);
        
        textoDireccion.setText("Direccion:");   // colocamos un texto a la etiqueta
        textoDireccion.setBounds(50, 280, 130, 30);
        
        cajaDireccion.setBounds(175,280,200,30);
        
        textoCiudad.setText("Ciudad:");   // colocamos un texto a la etiqueta
        textoCiudad.setBounds(50, 330, 130, 30);
        
        cajaCiudad.setBounds(175,330,200,30);
        
        textoPais.setText("Pais:");   // colocamos un texto a la etiqueta
        textoPais.setBounds(50, 380, 130, 30);
        
        cajaPais.setBounds(175,380,200,30);
        
        cajaTarjeta.setBounds(175,230,200,30);
        
        cajaEmail.setBounds(175,180,200,30);
        
        cajaNombre.setBounds(175, 30, 200, 30);
        
        password.setBounds(175, 80, 200, 30);
        
        cajaTelefono.setBounds(175, 130, 200, 30);
        
        botonRegistrar.setText("Registrarme");
        botonRegistrar.setBounds(250, 430, 150, 40);
        
        botonVolver.setText("Volver");
        botonVolver.setBounds(50, 430, 150, 40);

        
        this.add(textoNombre);
        this.add(textoPassword);
        this.add(cajaNombre);
        this.add(password);
        this.add(botonRegistrar);
        this.add(textoEmail);
        this.add(cajaEmail);
        this.add(textoTelefono);
        this.add(cajaTelefono);
        this.add(textoDireccion);
        this.add(cajaDireccion);
        this.add(textoCiudad);
        this.add(cajaCiudad);
        this.add(textoPais);
        this.add(cajaPais);
        this.add(botonVolver);
        this.add(textoTarjeta);
        this.add(cajaTarjeta);
        
        
    }

 
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
}

    public static void main(String[] args) {
    	VentanaRegistrar L = new VentanaRegistrar();      // creamos una ventana
        L.setVisible(true);             // hacemos visible la ventana creada
    }

	private void vaciarCampos() {
		cajaNombre.setText("");
		password.setText("");
		cajaEmail.setText("");
		cajaTelefono.setText("");
		cajaDireccion.setText("");
		cajaCiudad.setText("");
		cajaPais.setText("");
		cajaTarjeta.setText("");
		
	}

}
