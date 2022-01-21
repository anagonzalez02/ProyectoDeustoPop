package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import clases.Colores;
import clases.Estado;
import clases.Producto;
import clases.Usuario;

public class VentanaRecomendadosSaldo extends JFrame {

	private JFrame frame;
	private JTable CompTable = null;
	private PanelTableModel CompModel = null;
	private JPanel panelInferior;
	private JButton btnVolver;

	// HAY QUE HACER

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception fail) {
		}
		
		ArrayList<Producto> listaProductos = new ArrayList<>();
		String imagen = null;
		Producto producto1 = new Producto("Nomre1", "cosa", 8.66, imagen, Estado.BUENO, Colores.Blanco, new Usuario());
		Producto producto2 = new Producto("Nomre2", "cosa", 6, imagen, Estado.MALO, Colores.Amarillo, new Usuario());
		listaProductos.add(producto1);
		listaProductos.add(producto2);
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaRecomendadosSaldo().makeUI(null, listaProductos);
			}
		});
	}

	public void makeUI(Usuario u, ArrayList<Producto> listaProductos) {

		CompTable = CreateCompTable(u, listaProductos);
		// Crea el JScrollPane
		JScrollPane CompTableScrollpane = new JScrollPane(CompTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// gestion de el frame
		frame = new JFrame("DeustoPop");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(CompTableScrollpane, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(550, 700));
		frame.setLocation(400, 25);
		
		frame.setResizable(false);

		panelInferior = new JPanel(new BorderLayout());
		panelInferior.setVisible(true);
		panelInferior.setPreferredSize(new Dimension(50, 50));
		frame.getContentPane().add(panelInferior, BorderLayout.SOUTH);

		btnVolver = new JButton();
		btnVolver.setText("Volver");
		btnVolver.setPreferredSize(new Dimension(175, 50));
		panelInferior.add(btnVolver, BorderLayout.SOUTH);

		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (u == null) {
					VentanaLogIn ventana = new VentanaLogIn("VentanaUsuario", null);
					ventana.setVisible(true);
					dispose();
				} else {
					VentanaUsuario ventana = new VentanaUsuario(u);
					ventana.setVisible(true);
					dispose();
				}
			}
		});
		
		frame.pack();
		frame.setVisible(true);

	}

	//creacion de la tabla
    public JTable CreateCompTable(Usuario u, ArrayList<Producto> listaProductos) {
        CompModel = new PanelTableModel();
        JTable table = new JTable(CompModel);
        table.setRowHeight(350);
        //table.setRowHeight(new CompCellPanel().getPreferredSize().height);
        table.setTableHeader(null);
        PanelCellEditorRenderer PanelCellEditorRenderer = new PanelCellEditorRenderer();
        table.setDefaultRenderer(Object.class, PanelCellEditorRenderer);
        table.setDefaultEditor(Object.class, PanelCellEditorRenderer);
        
        JButton btnProd1 = null;
        JButton btnProd2 = null;
        
        if (listaProductos.size() % 2 == 0) {
	        for (int i=0; i < listaProductos.size(); i=i+2) {
	        	
	        	Producto producto1 = listaProductos.get(i);
	        	JPanel panelProducto1 = new JPanel(new BorderLayout());
	        	
	        	JPanel panelInfProd1 = new JPanel(new GridLayout(2, 1));
	        	panelInfProd1.add(new JLabel(producto1.getNombre()));
	        	panelInfProd1.add(new JLabel(producto1.getPrecio() + ""));
	        	panelProducto1.add(panelInfProd1, BorderLayout.NORTH);
	        	//panelProducto1.add(producto1.getImagen(), BorderLayout.CENTER);
	        	
	        	btnProd1 = new JButton();
	        	btnProd1.setText("Comprar producto " + producto1.getId());
	        	panelProducto1.add(btnProd1);
	        	
	        	btnProd1.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				VentanaProducto ventana = new VentanaProducto(producto1, u, "VentanaRecomendadosSaldo");
	    				ventana.setVisible(true);
	    				dispose();
	    			}
	    		});
	        	
	        	
	        	Producto producto2 = listaProductos.get(i+1);
	        	JPanel panelProducto2 = new JPanel(new BorderLayout());
	        	
	        	JPanel panelInfProd2 = new JPanel(new GridLayout(2, 1));
	        	panelInfProd2.add(new JLabel(producto2.getNombre()));
	        	panelInfProd2.add(new JLabel(producto2.getPrecio() + ""));
	        	panelProducto2.add(panelInfProd2, BorderLayout.NORTH);
	        	//panelProducto2.add(producto2.getImagen(), BorderLayout.CENTER);
	        	
	        	btnProd2 = new JButton();
	        	btnProd2.setText("Comprar producto " + producto2.getId());
	        	panelProducto2.add(btnProd2);
	        	
	        	btnProd2.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				VentanaProducto ventana = new VentanaProducto(producto2, u, "VentanaRecomendadosSaldo");
	    				ventana.setVisible(true);
	    				dispose();
	    			}
	    		});
	        	
	        	
	        	CompModel.addRow(panelProducto1, panelProducto2);
	        	
	        }
	        
        } else {
        	
        	for (int i=0; i < listaProductos.size() - 1; i=i+2) {
	        	
        		if (i != listaProductos.size()) {
        			
		        	Producto producto1 = listaProductos.get(i);
		        	JPanel panelProducto1 = new JPanel(new BorderLayout());
		        	
		        	JPanel panelInfProd1 = new JPanel(new GridLayout(2, 1));
		        	panelInfProd1.add(new JLabel(producto1.getNombre()));
		        	panelInfProd1.add(new JLabel(producto1.getPrecio() + ""));
		        	panelProducto1.add(panelInfProd1, BorderLayout.NORTH);
		        	//panelProducto1.add(producto1.getImagen(), BorderLayout.CENTER);
		        	
		        	btnProd1 = new JButton();
		        	btnProd1.setText("Comprar producto " + producto1.getId());
		        	panelProducto1.add(btnProd1);
		        	
		        	btnProd1.addActionListener(new ActionListener() {
		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				VentanaProducto ventana = new VentanaProducto(producto1, u, "VentanaRecomendadosSaldo");
		    				ventana.setVisible(true);
		    				dispose();
		    			}
		    		});
		        	
		        	
		        	Producto producto2 = listaProductos.get(i+1);
		        	JPanel panelProducto2 = new JPanel(new BorderLayout());
		        	
		        	JPanel panelInfProd2 = new JPanel(new GridLayout(2, 1));
		        	panelInfProd2.add(new JLabel(producto2.getNombre()));
		        	panelInfProd2.add(new JLabel(producto2.getPrecio() + ""));
		        	panelProducto2.add(panelInfProd2, BorderLayout.NORTH);
		        	//panelProducto2.add(producto2.getImagen(), BorderLayout.CENTER);
		        	
		        	btnProd2 = new JButton();
		        	btnProd2.setText("Comprar producto " + producto2.getId());
		        	panelProducto2.add(btnProd2);
		        	
		        	btnProd2.addActionListener(new ActionListener() {
		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				VentanaProducto ventana = new VentanaProducto(producto2, u, "VentanaRecomendadosSaldo");
		    				ventana.setVisible(true);
		    				dispose();
		    			}
		    		});
		        	
		        	
		        	CompModel.addRow(panelProducto1, panelProducto2);
		        	
        		} else {
        			
        			Producto producto1 = listaProductos.get(i);
		        	JPanel panelProducto1 = new JPanel(new BorderLayout());
		        	
		        	JPanel panelInfProd1 = new JPanel(new GridLayout(2, 1));
		        	panelInfProd1.add(new JLabel(producto1.getNombre()));
		        	panelInfProd1.add(new JLabel(producto1.getPrecio() + ""));
		        	panelProducto1.add(panelInfProd1, BorderLayout.NORTH);
		        	//panelProducto1.add(producto1.getImagen(), BorderLayout.CENTER);
		        	
		        	btnProd1 = new JButton();
		        	btnProd1.setText("Comprar producto " + producto1.getId());
		        	panelProducto1.add(btnProd1);
		        	
		        	btnProd1.addActionListener(new ActionListener() {
		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				VentanaProducto ventana = new VentanaProducto(producto1, u, "VentanaRecomendadosSaldo");
		    				ventana.setVisible(true);
		    				dispose();
		    			}
		    		});
		        	
		        	
		        	CompModel.addRow(panelProducto1, null);
        			
        		}
	        }
        	
        }
        
        return table;
    }

}




