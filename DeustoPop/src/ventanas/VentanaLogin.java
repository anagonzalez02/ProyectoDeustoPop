package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clases.BaseDeDatos;
import clases.FuncionesGenerales;
import clases.Usuario;


public class VentanaLogin extends JFrame implements ActionListener{

	private JLabel textoNombre;
	private JLabel textoPassword;
	private JTextField cajaNombre;
	private JPasswordField password;
	private JButton botonEntrar;
	private JButton botonRegistrar;

	
	
    public VentanaLogin() {
        super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }
    
    private void configurarVentana() {
        this.setTitle("DeustoPop");                   			// colocamos titulo a la ventana
        this.setSize(450, 250);                                	// colocamos tamanio a la ventana (ancho, alto)
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
    	botonEntrar = new JButton();
    	botonRegistrar = new JButton();

    	
        textoNombre.setText("Nombre de usuario:");   // colocamos un texto a la etiqueta
        textoNombre.setBounds(50, 30, 130, 30);		// colocamos posicion y tamanio del texto (x, y, ancho, alto)
        
        textoPassword.setText("Contrase�a:");   	// colocamos un texto a la etiqueta
        textoPassword.setBounds(50, 80, 130, 30);	// colocamos posicion y tamanio del texto (x, y, ancho, alto)
    	
        cajaNombre.setBounds(175, 30, 200, 30);
        
        password.setBounds(175, 80, 200, 30);
        
        botonEntrar.setText("Iniciar Sesion");
        botonEntrar.setBounds(250, 130, 150, 40);
        
        botonRegistrar.setText("Registrarme");
        botonRegistrar.setBounds(50, 130, 150, 40);

        
        this.add(textoNombre);
        this.add(textoPassword);
        this.add(cajaNombre);
        this.add(password);
        this.add(botonEntrar);
        this.add(botonRegistrar);
        
        
	    botonRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRegistrar ventana = new VentanaRegistrar();
		        ventana.setVisible(true);
				dispose();
			}
		});
	    
	    
	    botonEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Usuario usuario : BaseDeDatos.getUsuarios()) {
					if (cajaNombre.getText() == usuario.getNombre() && password.getText() == FuncionesGenerales.decode(usuario.getContrasenia())) {
						/**
						SIGUIENTEVENTANA ventana = new SIGUIENTEVENTANA();
				        ventana.setVisible(true);
						dispose();
						**/
					}
					else {
						JOptionPane.showMessageDialog(null, "El usuario o la contrasenya con incorrectos");
					}
				}
			}
		});
		
    }
    

    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

    public static void main(String[] args) {
    	VentanaLogin L = new VentanaLogin();      // creamos una ventana
        L.setVisible(true);             // hacemos visible la ventana creada
    }
}
