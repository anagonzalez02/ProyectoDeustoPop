package ventanas;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class panelImagenes extends JPanel {
	
	public panelImagenes(String imagen) {
		// TODO Auto-generated constructor stub
		paint(null, imagen);
	}

	/**
	public panelImagenes (String nombre) {
	}
	**/
	public void paint(Graphics g, String nombre){
        Dimension dimension = this.getSize();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Fotos/" + nombre));
        g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
        setOpaque(false);
        super.paintChildren(g);
	}

}
