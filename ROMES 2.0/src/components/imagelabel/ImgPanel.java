package components.imagelabel;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import pages.utils.ColoredPanel;

public class ImgPanel extends ColoredPanel
{
	private ImageIcon img;
	public ImgPanel(ImageIcon icon)
    {
		this.img = icon;
        //code
    }
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
