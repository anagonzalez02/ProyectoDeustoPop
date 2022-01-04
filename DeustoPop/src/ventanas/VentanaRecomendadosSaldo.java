package ventanas;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class VentanaRecomendadosSaldo {
	
	private JFrame frame;
    private JTable CompTable = null;
    private PanelTableModelFav CompModel = null;

	
    // HAY QUE HACER
	
	public static void main(String args[]) {
	     try {
	         UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	         //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	     } catch (Exception fail) {
	     }
	     SwingUtilities.invokeLater(new Runnable() {

	         @Override
	         public void run() {
	             new VentanaRecomendadosSaldo().makeUI();
	         }
	     });
	}
	
	public void makeUI() {}

}
