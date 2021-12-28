package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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

public class VentanaFavoritos {

	private JFrame frame;
    private JTable CompTable = null;
    private PanelTableModelFav CompModel = null;

	
	
	public static void main(String args[]) {
	     try {
	         UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	         //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	     } catch (Exception fail) {
	     }
	     SwingUtilities.invokeLater(new Runnable() {

	         @Override
	         public void run() {
	             new VentanaFavoritos().makeUI();
	         }
	     });
	}
	
    //como si fuese el main (sin serlo)
    //esta ventana no tiene main, y solo se crea al ser ejecutada
    //al pasar de otra ventana a esta habra que volver a ejecutar
    //la ventana invocando al main de esta "VentanaFavoritos.main(null);"
	
	public void makeUI() {
        CompTable = CreateCompTable();
        //Crea el JScrollPane
        JScrollPane CompTableScrollpane = new JScrollPane(CompTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        //gestion de el frame
        frame = new JFrame("DeustoPop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(CompTableScrollpane, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(550, 700));
        frame.setLocation(400, 25);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
	}
	
	
    //creacion de la tabla
    public JTable CreateCompTable() {
        CompModel = new PanelTableModelFav();
        JTable table = new JTable(CompModel);
        table.setRowHeight(300);
        table.setTableHeader(null);
        PanelCellEditorRendererFav PanelCellEditorRendererFav = new PanelCellEditorRendererFav();
        table.setDefaultRenderer(Object.class, PanelCellEditorRendererFav);
        table.setDefaultEditor(Object.class, PanelCellEditorRendererFav);
        
        //Se a�aden los paneles al JTable
        //Los paneles son del mismo tipo CompCellPanelFav y con cada instancia
        //de la clase Comp le pasamos los datos que va a mostrar por el constructor
        CompModel.addRow(new CompFav(1), new CompFav(2));
        CompModel.addRow(new CompFav(3), new CompFav(4));
        CompModel.addRow(new CompFav(5), new CompFav(6));
        CompModel.addRow(new CompFav(7), new CompFav(0));
        CompModel.addRow(new CompFav(0), new CompFav(8));
        
        return table;
    }
  
}
        
      //necesario para el correcto funcionamiento de la tabla y en general de la ventana entera
        class PanelCellEditorRendererFav extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

            private static final long serialVersionUID = 1L;
            private CompCellPanelFav renderer = new CompCellPanelFav();
            private CompCellPanelFav editor = new CompCellPanelFav();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
           		renderer.setCompFav((CompFav) value);
            	
                return renderer;
            }

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
           		editor.setCompFav((CompFav) value);
            	
                return editor;
            }

            @Override
            public Object getCellEditorValue() {
                return editor.getCompFav();
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

        class PanelTableModelFav extends DefaultTableModel {

            private static final long serialVersionUID = 1L;

            //crea 2 colunmas en la tabla
            @Override
            public int getColumnCount() {
                return 2;
            }
            //crea las filas que hagan falta teniendo en cuenta
            //que la cantidad de columnas sera siempre 2
            public void addRow(CompFav c1, CompFav c2) {
            	super.addRow(new Object[]{c1, c2});
            }
        }

        //Clase con los datos a pasar a cada panel creado
        class CompFav {

            public int numero;
         
            public CompFav(int num) {
                this.numero = num;
            }
        }

        class CompCellPanelFav extends JPanel {
        	
        	//creacion de los elementos que apareceran en cada
        	//casilla de la tabla
        	private static final long serialVersionUID = 1L;
        	private int numero;
        	private JPanel pSup = new JPanel(new BorderLayout());
        	private JLabel lTexto1 = new JLabel("", JLabel.CENTER);
        	private JLabel precio = new JLabel();
        	private ImageIcon imagen = new ImageIcon("src/DeustoPopVentanas/patata.png", null);
        	private JLabel lImagen = new JLabel(imagen, JLabel.CENTER);
        	

        	CompCellPanelFav() {
        		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        		//anyade los elementos a cada casilla de la tabla
        		pSup.add(lImagen, BorderLayout.CENTER);
        		pSup.add(lTexto1, BorderLayout.NORTH);
        		pSup.add(precio, BorderLayout.SOUTH);
        		add(pSup);
        	}
        	//crea un panel(casilla en la tabla) vacio en caso de ser necesario
        	public void setCompFav(CompFav compFav) {
        		numero = compFav.numero;
        		if (compFav.numero == 0) {
        			lImagen.setVisible(false);
        			lTexto1.setText("");
        			precio.setVisible(false);
        			return;
        		}
        		//disenyo del resto de casillas en la tabla
        		lImagen.setVisible(true);
        		lImagen.setPreferredSize(new Dimension(10, 10));
        		lTexto1.setText("Producto " + compFav.numero);
        		precio.setVisible(true);
        		precio.setPreferredSize(new Dimension(0, 30));
        		precio.setText("precio" + " �");

        	}

        	public CompFav getCompFav() {
        		return new CompFav(numero);
        	}
        
    }

	

