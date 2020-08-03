package layoutSetting;

import java.awt.Font;

import javax.swing.JLabel;

import factory.fontFactory;

public class basicLabel extends JLabel{
	public basicLabel(String string) {
		super(string);
		//this.setFont(new Font("Microsoft YaHei",Font.BOLD,14));
		this.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,16));
	}
}
