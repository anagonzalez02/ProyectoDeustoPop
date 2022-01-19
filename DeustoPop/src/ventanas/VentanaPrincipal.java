package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.table.*;

import clases.Producto;



public class VentanaPrincipal extends JFrame{
	public VentanaPrincipal() {
	}

    private JFrame frame;
    private JTable CompTable = null;
    private PanelTableModel CompModel = null;
    private JPanel panelTop;
    private JPanel panelBot;
    private JButton bVender;
    private JButton bFiltrar;
    private JButton bPosibles;
    private JLabel tPrecio;

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception fail) {
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new VentanaPrincipal().makeUI();
            }
        });
    }
    //como si fuese el main (sin serlo)
    //esta ventana no tiene main, y solo se crea al ser ejecutada
    //al pasar de otra ventana a esta habra que volver a ejecutarla
    //la ventana invocando al main de esta "VentanaPrincipal.main(null);"
    public void makeUI() {
        CompTable = CreateCompTable();
        //Crea el JScrollPane
        JScrollPane CompTableScrollpane = new JScrollPane(CompTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        //gestion de el frame
        frame = new JFrame("DeustoPop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(CompTableScrollpane, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(550, 700));
        frame.setLocation(400, 25);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
        //creacion de un panel para ponerlo arriba,
        //y que contenga los botones vender, filtrar y el saldo
        panelTop = new JPanel(new BorderLayout());
        panelTop.setVisible(true);
        panelTop.setPreferredSize(new Dimension(50, 50));
        frame.getContentPane().add(panelTop, BorderLayout.NORTH);
        
        //creacion de un panel para ponerlo debajo de la tabla,
        //y que contenga el boton posibles
        panelBot = new JPanel(new BorderLayout());
        panelBot.setVisible(true);
        panelBot.setPreferredSize(new Dimension(50, 50));
        frame.getContentPane().add(panelBot, BorderLayout.SOUTH);
        
        //creacion del boton vender e implementacion en el panelTop
        bVender = new JButton();
        bVender.setText("Vender");
        bVender.setPreferredSize(new Dimension(175, 50));
        panelTop.add(bVender, BorderLayout.WEST);
        
      //creacion del boton filtar e implementacion en el panelTop
        bFiltrar = new JButton();
        bFiltrar.setText("Filtrar");
        panelTop.add(bFiltrar, BorderLayout.CENTER);
        
      //creacion del texto saldo e implementacion en el panelTop
        tPrecio = new JLabel();
        tPrecio.setText("Saldo: *getSaldo*");
        tPrecio.setPreferredSize(new Dimension(175, 50));
        panelTop.add(tPrecio, BorderLayout.EAST);
        
      //creacion del boton posibles compras e implementacion en el panelTop
        bPosibles = new JButton();
        bPosibles.setText("Posibles compras");
        bPosibles.setPreferredSize(new Dimension(175, 50));
        panelBot.add(bPosibles, BorderLayout.CENTER);
        
        //implementacion del action listener en el
        //boton vender que te transporta a la ventana vender
        //al clicar el boton "vender"
        bVender.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			VentanaVender ventana = new VentanaVender();
    	        ventana.setVisible(true);
    			dispose();
    			frame.setVisible(false);
    		}
    	});
        
        //implementacion del action listener en el
        //boton filtrar que te transporta a ventana filtrar
        //al clicar el boton "filtrar"
        bFiltrar.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			VentanaBuscador ventana = new VentanaBuscador();
    	        ventana.setVisible(true);
    			dispose();
    			frame.setVisible(false);
    		}
    	});
        
        //implementacion del action listener en el
        //boton posibles que  crea una funcion recursiva que
        //al clicar el boton "vender"
        
        bPosibles.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {


        	//	private void calcularComprasPosibles( double disponible ) {
        	//		ArrayList<Producto> lProds = new ArrayList<>();
        	//		calcularComprasPosibles( listaProds, disponible, lProds );
        	//	}
        	//	private void calcularComprasPosibles( ArrayList<Producto> prods, double dineroQueda, ArrayList<Producto> lProdsComprados ) {
        	//		if (dineroQueda < 0 ) {  // Caso base: compra imposible  (no hay suficiente dinero)
        	//			return;
        	//			
        	//			//hay que cambiar el 50 de abajo por el precio del producto con menor precio
        	//			
        	//			
        	//		} else if (dineroQueda < 50) {  // Caso base: compra posible con menos del precio del producto con menor precio
        	//			System.out.println( "Posible compra (sobran " + String.format("%.2f",dineroQueda) + " euros): " + lProdsComprados );
        	//		} else {  // Caso general - probar por combinatoria todos los productos posibles para comprar
        	//			for (Producto p : prods) {
        	//				lProdsComprados.add( p );
        	//				calcularComprasPosibles( prods, dineroQueda - p.getPrecio(), lProdsComprados );
        	//				lProdsComprados.remove( lProdsComprados.size()-1 );
        	//				}
        	//		}
        		}
        	
        });


    }
        

    //creacion de la tabla
    public JTable CreateCompTable() {
        CompModel = new PanelTableModel();
        JTable table = new JTable(CompModel);
        table.setRowHeight(350);
        //table.setRowHeight(new CompCellPanel().getPreferredSize().height);
        table.setTableHeader(null);
        PanelCellEditorRenderer PanelCellEditorRenderer = new PanelCellEditorRenderer();
        table.setDefaultRenderer(Object.class, PanelCellEditorRenderer);
        table.setDefaultEditor(Object.class, PanelCellEditorRenderer);
        
        //Se añaden los paneles al JTable
        //Los paneles son del mismo tipo CompCellPanel y con cada instancia
        //de la clase Comp le pasamos los datos que va a mostrar por el constructor
        CompModel.addRow(new Comp(1), new Comp(2));
        CompModel.addRow(new Comp(3), new Comp(4));
        CompModel.addRow(new Comp(5), new Comp(6));
        CompModel.addRow(new Comp(7), new Comp(0));
        CompModel.addRow(new Comp(0), new Comp(8));
        
        return table;
    }

}
//necesario para el correcto funcionamiento de la tabla y en general de la ventana entera
class PanelCellEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private static final long serialVersionUID = 1L;
    private CompCellPanel renderer = new CompCellPanel();
    private CompCellPanel editor = new CompCellPanel();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
   		renderer.setComp((Comp) value);
    	
        return renderer;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
   		editor.setComp((Comp) value);
    	
        return editor;
    }

    @Override
    public Object getCellEditorValue() {
        return editor.getComp();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }
}

class PanelTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    //crea 2 colunmas en la tabla
    @Override
    public int getColumnCount() {
        return 2;
    }
    //crea las filas que hagan falta teniendo en cuenta
    //que la cantidad de columnas sera siempre 2
    public void addRow(Comp c1, Comp c2) {
    	super.addRow(new Object[]{c1, c2});
    }
}

//Clase con los datos a pasar a cada panel creado
class Comp {

    public int numero;
 
    public Comp(int num) {
        this.numero = num;
    }
}

class CompCellPanel extends JPanel {
	
	//creacion de los elementos que apareceran en cada
	//casilla de la tabla
	private static final long serialVersionUID = 1L;
	private int numero;
	private JPanel pSup = new JPanel(new BorderLayout());
	private JLabel lTexto1 = new JLabel("", JLabel.CENTER);
	private JLabel precio = new JLabel();
	private ImageIcon imagen = new ImageIcon("C:\\Users\\aitor\\git\\ProyectoDeustoPop\\DeustoPop\\src\\ventanas\\defaultImage.png", null);
	private JLabel lImagen = new JLabel(imagen, JLabel.CENTER);
	

	CompCellPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		//anyade los elementos a cada casilla de la tabla
		pSup.add(lImagen, BorderLayout.CENTER);
		pSup.add(lTexto1, BorderLayout.NORTH);
		pSup.add(precio, BorderLayout.SOUTH);
		add(pSup);
	}
	//crea un panel(casilla en la tabla) vacio en caso de ser necesario
	public void setComp(Comp comp) {
		numero = comp.numero;
		if (comp.numero == 0) {
			lImagen.setVisible(false);
			lTexto1.setText("");
			precio.setVisible(false);
			return;
		}
		//disenyo del resto de casillas en la tabla
		lImagen.setVisible(true);
		lImagen.setIcon(imagen);
		lImagen.setPreferredSize(new Dimension(10, 10));
		lTexto1.setText("Producto " + comp.numero);
		precio.setVisible(true);
		precio.setPreferredSize(new Dimension(0, 30));
		precio.setText("precio " + "€");
		
	}

	public Comp getComp() {
		return new Comp(numero);
	}

}

