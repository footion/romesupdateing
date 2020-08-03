package Menu;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import factory.fontFactory;

public class MainMenu extends JLabel{
	public MainMenu(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		setFont(new Font(fontFactory.TITLE_FONT, Font.BOLD, 21));
	}

}
