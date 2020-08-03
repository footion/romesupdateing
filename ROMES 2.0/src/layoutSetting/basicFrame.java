package layoutSetting;

import java.awt.Color;

import javax.swing.JFrame;

import factory.colorFactory;

public class basicFrame extends JFrame{
	public basicFrame(String title) {
		this.setTitle(title);
		this.getContentPane().setBackground(colorFactory.PANEL_COLOR);
	}
	public basicFrame(String title,String NULL) {
		this.setTitle(title);
	}
}
