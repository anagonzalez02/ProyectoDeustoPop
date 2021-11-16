package DeustoPopVentanas;

import java.awt.*;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.table.*;

public class PanelInTable {

    private JFrame frame;
    private JTable CompTable = null;
    private PanelTableModel CompModel = null;

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception fail) {
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PanelInTable().makeUI();
            }
        });
    }

    public void makeUI() {
        CompTable = CreateCompTable();
        JScrollPane CompTableScrollpane = new JScrollPane(CompTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        frame = new JFrame("JTable de Paneles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(CompTableScrollpane, BorderLayout.CENTER);

        frame.setPreferredSize(new Dimension(550, 700));
        frame.setLocation(400, 25);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public JTable CreateCompTable() {
        CompModel = new PanelTableModel();
        
        JTable table = new JTable(CompModel);
        table.setRowHeight(new CompCellPanel().getPreferredSize().height);
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

    @Override
    public int getColumnCount() {
        return 2;
    }

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

	private static final long serialVersionUID = 1L;
	private int numero;
	private JPanel pSup = new JPanel(new BorderLayout());
	private JLabel lTexto1 = new JLabel("", JLabel.CENTER);
	private JTextArea taMensajes = new JTextArea(15, 10);
	private JButton boton = new JButton();
	private ImageIcon imagen = new ImageIcon();

	CompCellPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		pSup.add(taMensajes, BorderLayout.CENTER);
		pSup.add(lTexto1, BorderLayout.NORTH);
		pSup.add(boton, BorderLayout.SOUTH);
		add(pSup);
	}

	public void setComp(Comp comp) {
		numero = comp.numero;
		if (comp.numero == 0) {
			taMensajes.setVisible(false);
			taMensajes.setText("");
			lTexto1.setText("");
			boton.setVisible(false);
			return;
		}
		taMensajes.setVisible(true);
		taMensajes.setText("Panel BorderLayout de prueba numero " + comp.numero);
		lTexto1.setText("Panel " + comp.numero);
		boton.setVisible(true);
		boton.setPreferredSize(new Dimension(0, 20));
		boton.setText("hola");

	}

	public Comp getComp() {
		return new Comp(numero);
	}

}

