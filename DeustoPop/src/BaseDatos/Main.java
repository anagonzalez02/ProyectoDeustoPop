package BaseDatos;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Usuario;


public class Main extends JFrame {
	
	private static Main ventana;  // Ventana única principal
	public static void main(String[] args) {
		ventana = new Main();
		ventana.setVisible( true );
	}
	
	private JLabel lInfo;
	private DefaultTableModel mDatos;
	private JTable tDatos;
	private ArrayList<Usuario> listaUsuarios;
	private JPanel pNorte;
	private JPanel panelBotonera;
	
	private JButton btnUsuarios;
	
	
	public Main () {
		
		tDatos = new JTable();
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (new File("deustopop.bd").exists()) {
					// Poner el parámetro a true si se quiere reiniciar la base de datos
					try {
						BaseDeDatos.abrirConexion( "deustopop.bd", false );
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  // Abrir base de datos existente
				} else {
					try {
						BaseDeDatos.abrirConexion( "deustopop.bd", true );
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  // Crear base de datos con datos iniciales
				}
				verUsuarios();  // Según se inicia la ventana se visualizan los clientes registrados
			}
			@Override
			public void windowClosed(WindowEvent e) {
				BaseDeDatos.cerrarConexion();
			}
		});
		
		btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lInfo = new JLabel("Clientes");
				verUsuarios();
			}
		});
		
		panelBotonera.add(btnUsuarios);
		
		
		Container cPanel = this.getContentPane();
		cPanel.setLayout(new BorderLayout());
		
		cPanel.add(pNorte, BorderLayout.NORTH);
		cPanel.add(new JScrollPane(tDatos));
		cPanel.add(panelBotonera);
		
		
		
		
		
		this.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		this.setSize (1400, 400);
		this.setTitle ("Visualización de la base de datos de DeustoPop");
		
		
	}
	
	
	private void verUsuarios() {
		Vector<String> cabeceras = new Vector<String>(
				Arrays.asList("idUsuario", "nombre", "telefono", "nTarjeta", "saldo", "email", "contrasenia", "direccion"));
		mDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceras);
		listaUsuarios = BaseDeDatos.getUsuarios();

		for (Usuario u : listaUsuarios) {
			mDatos.addRow(new Object[] {u.getIdUsuario(), u.getNombre(), u.getTelefono(), u.getCuentaB().getnTarjeta(), u.getSaldo(), u.getEmail(), u.getContrasenia(), u.getVivienda().getDireccion()});
		}
		tDatos.setModel(mDatos);
	}
	

}
