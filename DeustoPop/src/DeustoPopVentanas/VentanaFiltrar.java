//SIN HACER

package DeustoPopVentanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class VentanaFiltrar extends JFrame implements ActionListener {
	
	private JLabel textoFiltrarCategoria;           // etiqueta o texto no editable
    private JLabel textoFiltrarTexto;           // etiqueta o texto no editable
    private JLabel textoCategoria;           // etiqueta o texto no editable
    private JLabel textoTallaCategoria;			// etiqueta o texto no editable
    private JLabel textoBuscarPorTexto;         // etiqueta o texto no editable

    private JTextField cajaBuscarPorTexto;        // caja de texto, para insertar datos
    private JButton botonBuscarPorCategoria;          // botonVolver con una determinada accion
    private JButton botonBuscarPorTexto;          // botonBuscar con una determinada accion
    private JButton botonVolver;          // botonBuscar con una determinada accion
    private JComboBox comboCategoria;
    private JComboBox comboTalla;		//este combo servira tanto para las tallas de ropa como para las de calzado, rellenandose de los valores pertinentes

    
    public VentanaFiltrar() {
        super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }
    
    private void configurarVentana() {
        this.setTitle("Filtrar");                   			// colocamos titulo a la ventana
        this.setSize(500, 700);                                	// colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
    }
    
    private void inicializarComponentes() {
    textoFiltrarCategoria = new JLabel();
    textoFiltrarTexto = new JLabel();
    textoCategoria = new JLabel();
    textoTallaCategoria = new JLabel();
    textoBuscarPorTexto = new JLabel();
    
    
    cajaBuscarPorTexto = new JTextField();
    
    botonBuscarPorCategoria = new JButton();
    botonBuscarPorTexto = new JButton();
    botonVolver = new JButton();
    comboCategoria = new JComboBox();
    comboTalla = new JComboBox();

    
    //configuracion de componentes
    
    textoFiltrarCategoria.setText("FILTRAR POR CATEGORIA");
    textoFiltrarCategoria.setBounds(25, 30, 300, 20);								//(x, y, ancho, alto)
    textoFiltrarTexto.setText("FILTRAR POR TEXTO");
    textoFiltrarTexto.setBounds(25, 300, 300, 20);									//(x, y, ancho, alto)
    textoCategoria.setText("Seleccione la categoría del producto que desea:");
    textoCategoria.setBounds(25, 60, 300, 20);								//(x, y, ancho, alto)
    textoTallaCategoria.setText("Talla del producto:");
    textoTallaCategoria.setBounds(25, 130, 300, 20);							//(x, y, ancho, alto)
    textoBuscarPorTexto.setText("Seleccione palabras clave del producto:");
    textoBuscarPorTexto.setBounds(25, 330, 300, 20);								//(x, y, ancho, alto)
    


    cajaBuscarPorTexto.setBounds(25, 360, 425, 30);
    
    botonBuscarPorCategoria.setText("Buscar");
    botonBuscarPorCategoria.setBounds(25, 210, 425, 30);
    botonBuscarPorTexto.setText("Buscar");
    botonBuscarPorTexto.setBounds(25, 410, 425, 30);
    botonVolver.setText("Volver");
    botonVolver.setBounds(25, 550, 425, 70);
    
    comboCategoria.setBounds(25, 90, 425, 30);
    comboTalla.setBounds(25, 160, 425, 30);
    
    //anyadimos los componentes
    this.add(textoFiltrarCategoria);
    this.add(textoFiltrarTexto);
    this.add(textoCategoria);
    this.add(textoTallaCategoria);
    this.add(textoBuscarPorTexto);
    this.add(cajaBuscarPorTexto);
    this.add(botonBuscarPorCategoria);
    this.add(botonBuscarPorTexto);
    this.add(botonVolver);
    this.add(comboCategoria);
    this.add(comboTalla);

    this.add(comboCategoria);
    comboCategoria.addItem("Ropa");
    comboCategoria.addItem("Calzado");
    this.add(comboTalla);
    comboTalla.addItem("S");
    
    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * AQUI FALTARA HACER QUE CUANDO SE SELECCIONE ROPA APAREZCA EN EL COMBO XS S M L XL Y CUANDO CALZADO, NUMS DEL 30 AL 50
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    public static void main(String[] args) {
    	VentanaFiltrar C = new VentanaFiltrar();      // creamos una ventana
        C.setVisible(true);             // hacemos visible la ventana creada
    }
    

}