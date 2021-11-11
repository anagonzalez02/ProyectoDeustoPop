//no esta acabada
package DeustoPopVentanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private JScrollPane sPanelPrincipal;
	private JPanel panelMain;
	private JPanel panelTop;
	private JPanel panelMid;
	private JButton botonVender;
	private JButton botonFiltrar;
	private JComboBox comboFiltrar;
	private JLabel textoSaldo;
	//No se como hacer lo de las imagenes y que se vayan generando/apareciendo nuevos porductos
	
	
    public VentanaPrincipal() {
        super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }
    
    private void configurarVentana() {
        this.setTitle("DeustoPop");                   			// colocamos titulo a la ventana
        this.setSize(500, 700);                                	// colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(new BorderLayout());                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termine todo proceso
        
    }
    
    private void inicializarComponentes() {
    	panelMain = new JPanel(new BorderLayout());
    	panelMain.setPreferredSize(new Dimension(466, 650));	//pongo el x en 466 y no en 500 pq si no se me activa el scrollPane y no me ape tenerlo ah� activao
    	panelMain.setVisible(true);
    	
    	panelTop = new JPanel(new BorderLayout());
    	panelTop.setPreferredSize(new Dimension(500, 50));
    	panelTop.setVisible(true);
    	
    	//crear nuevo panel con gridLayout y meterlo en el CENTER para postear ah� los productos
    	panelMid = new JPanel(new GridLayout(0,2));
    	panelMid.setPreferredSize(new Dimension(500, 650));
    	panelMid.setVisible(true);
    	
    	this.add(panelMain);
    	panelMain.add(panelTop, BorderLayout.NORTH);
    	panelMain.add(panelMid, BorderLayout.CENTER);
    	
    	//el orden impporta pq sta todo dentro del Jpanel
    	
    	botonVender = new JButton();
    	botonVender.setText("Vender");
    	botonVender.setPreferredSize(new Dimension(100, 40));
    	//sPanelPrincipal.add(botonVender);
    	panelTop.add(botonVender, BorderLayout.WEST);
    	
    	
    	botonFiltrar = new JButton();
    	botonFiltrar.setText("Filtrar");
    	botonFiltrar.setPreferredSize(new Dimension(100, 40));
    	//sPanelPrincipal.add(botonFiltrar);
    	panelTop.add(botonFiltrar, BorderLayout.CENTER);
    	
    	/*
    	//si descomentas esta parte del c�digo tiene q comentar "botonFiltrar" pq si no se solapan
    	comboFiltrar = new JComboBox();
    	comboFiltrar.addItem("Color");
    	comboFiltrar.addItem("Ropa");
    	comboFiltrar.addItem("Libros");
    	panelTop.add(comboFiltrar, BorderLayout.CENTER);
    	*/
    	
    	textoSaldo = new JLabel();
    	textoSaldo.setText("*getSaldo* �");
    	//textoSaldo.setSize(100, 100);
    	textoSaldo.setPreferredSize(new Dimension(100, 40));
    	//sPanelPrincipal.add(textoSaldo);
    	panelTop.add(textoSaldo, BorderLayout.EAST);
    	
    	/*
    	botonPatata = new JButton();
    	botonPatata.setText("Patata");
    	botonPatata.setPreferredSize(new Dimension(100, 40));
    	//sPanelPrincipal.add(botonFiltrar);
    	panelMain.add(botonPatata, BorderLayout.CENTER);
    	*/
    	
        panelMid.add(new JButton("Button 1"));
        panelMid.add(new JButton("Button 2"));
        panelMid.add(new JButton("Button 3"));
        panelMid.add(new JButton("Button 4"));
        //panelMid.add(new JButton("Button 5"));


    	
    	
    	sPanelPrincipal = new JScrollPane(panelMain);
    	sPanelPrincipal.setPreferredSize(new Dimension(500, 700));
    	this.add(sPanelPrincipal, BorderLayout.CENTER);
    	sPanelPrincipal.setVisible(true);
    	
    	
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    public static void main(String[] args) {
    	VentanaPrincipal P = new VentanaPrincipal();      // creamos una ventana
        P.setVisible(true);             // hacemos visible la ventana creada
    }
}
