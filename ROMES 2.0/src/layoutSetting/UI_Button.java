package layoutSetting;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import com.jgoodies.looks.plastic.theme.LightGray;

import factory.colorFactory;


public class UI_Button extends JButton{
	public UI_Button(String text) {
		super(text);
		this.setBackground(colorFactory.UIBUTTON_COLOR);
		//this.setForeground(colorFactory.UIBUTTON_COLOR);
		this.setUI(new basicBtnUI());
		this.setPreferredSize(new Dimension(80,30));
		
		//this.setBorderPainted(false);
		//this.setFocusPainted(false);
		//this.setContentAreaFilled(false);
	}
}
