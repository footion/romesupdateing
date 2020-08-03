package layoutSetting;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import factory.colorFactory;
import image.url;

public class basicFrame extends JFrame{
	public basicFrame(String title) {
		this.setTitle(title);
		this.getContentPane().setBackground(colorFactory.PANEL_COLOR);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(url.LOGO);
        this.setIconImage(image);
	}
	public basicFrame(String title,String NULL) {
		this.setTitle(title);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(url.LOGO);
        this.setIconImage(image);
	}
}
