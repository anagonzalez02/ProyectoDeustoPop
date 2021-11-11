//ventan a principal con diferente layout, no esta acabada y porbablemente la hare sin el gridBag que mme resulta mas facil
package DeustoPopVentanas;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

	public class GridBagLayoutVentanaPrincipal {
	    

		final static boolean shouldFill = true;
	    final static boolean shouldWeightX = true;
	    final static boolean RIGHT_TO_LEFT = false;
	    

	    public static void addComponentsToPane(Container pane) {
	        if (RIGHT_TO_LEFT) {
	            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	        }

	        JButton button;
	        JLabel text;
	        JPanel panel;
	        JMenuBar menuBar;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
		//natural height, maximum width
		c.fill = GridBagConstraints.HORIZONTAL;
		}

		button = new JButton("Vender");
		if (shouldWeightX) {
		c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 20;	//ipady hace que el boton sea mas alto y afecta a todos los elementos de su fila "gridy=0"
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		pane.add(button, c);

		button = new JButton("Filtrar");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		//c.insets = new Insets(0,10,0,0);	//para darle espacio entre elementos
		c.anchor = GridBagConstraints.PAGE_START;
		pane.add(button, c);

		text = new JLabel("*Saldo: *");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		pane.add(text, c);
		
		panel = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 70;      //make this component tall
		//c.ipadx = 50;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(panel, c);
		pane.setVisible(true);
		
		button = new JButton("Patata");
		button.setPreferredSize(new Dimension(400, 480));
		
		panel.add(button);
		
		/*
		button = new JButton("Long-Named Button 4");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(button, c);
		*/
		
		button = new JButton("hola");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 2;       //third row
		pane.add(button, c);
	    
		 
	    }
	    private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("DeustoPop");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setPreferredSize(new Dimension(500, 700));
	        frame.setResizable(false);

	        //Set up the content pane.
	        addComponentsToPane(frame.getContentPane());

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	        
	    }
}
